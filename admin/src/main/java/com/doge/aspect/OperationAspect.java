package com.doge.aspect;

import com.doge.annotation.GlobalInterceptor;
import com.doge.annotation.VerifyParam;
import com.doge.entity.constants.Constants;
import com.doge.entity.dto.SessionUserAdminDto;
import com.doge.entity.enums.PermissionCodeEnum;
import com.doge.entity.enums.ResponseCodeEnum;
import com.doge.exception.BusinessException;
import com.doge.utils.StringTools;
import com.doge.utils.VerifyRegexUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;

/**
 * 切面类
 */


@Aspect
@Component("operationAspect")
public class OperationAspect {
    private final Logger logger = LoggerFactory.getLogger(OperationAspect.class);

    private static final String[] BASE_TYPE_ARRAY = new String[]{"java.lang.String", "java.lang.Integer", "java.lang.Long"};


    @Pointcut("@annotation(com.doge.annotation.GlobalInterceptor)")
    public void pointCut() {

    }

    @Before("pointCut()")

    public void interceptor(JoinPoint point) throws BusinessException {
        Object[] args = point.getArgs();
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        GlobalInterceptor interceptor = method.getAnnotation(GlobalInterceptor.class);
        if (interceptor == null) {
            return;
        }

        /**
         * 登录校验
         */
        if (interceptor.checkLogin()) {
            checkLogin();
        }

        /**
         * 校验权限
         */

        if (interceptor.permissionCode() != null && interceptor.permissionCode() != PermissionCodeEnum.NO_PERMISSION) {
            checkPermission(interceptor.permissionCode());
        }
        /**
         * 参数校验
         */
        if (interceptor.checkParams()) {
            validateParams(method, args);
        }
    }

    void checkLogin() throws BusinessException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        SessionUserAdminDto sessionUserAdminDto = (SessionUserAdminDto) request.getSession().getAttribute(Constants.SESSION_KEY);
        if (sessionUserAdminDto == null) {
            throw new BusinessException(ResponseCodeEnum.CODE_901);
        }
    }

    void checkPermission(PermissionCodeEnum permissionCode) throws BusinessException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        SessionUserAdminDto sessionUserAdminDto = (SessionUserAdminDto) request.getSession().getAttribute(Constants.SESSION_KEY);
        List<String> permissionCodeList = sessionUserAdminDto.getPermissionCodeList();
        if (!permissionCodeList.contains(permissionCode.getCode())) {
            throw new BusinessException(ResponseCodeEnum.CODE_902);
        }
    }


    private void validateParams(Method method, Object[] args) throws BusinessException {
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            Object value = args[i];
            /**
             * 获取参数注解
             */
            VerifyParam verifyParam = parameter.getAnnotation(VerifyParam.class);
            if (verifyParam == null) {
                continue;
            }
            String paramTypeName = parameter.getParameterizedType().getTypeName();
//            思考：在校验参数的时候每个参数的参数类型可能不一样，不同类型的参数的校验方法可能不一样,校验的参数也可能是一个对象
            if (ArrayUtils.contains(BASE_TYPE_ARRAY, paramTypeName)) {
                checkValue(value, verifyParam);
            } else {
                checkObjValue(parameter, value);
            }
        }
    }

    private void checkObjValue(Parameter parameter, Object value) {

        try {
            String typeName = parameter.getParameterizedType().getTypeName();
            Class clazz = Class.forName(typeName);
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                VerifyParam FieldVerifyParam = field.getAnnotation(VerifyParam.class);
                if (FieldVerifyParam == null) {
                    continue;
                }
                field.setAccessible(true);
                Object resultValue = field.get(value);
                checkValue(resultValue, FieldVerifyParam);
            }
        } catch (Exception e) {
            logger.error("参数校验错误");
            try {
                throw new BusinessException(ResponseCodeEnum.CODE_600);
            } catch (BusinessException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private void checkValue(Object value, VerifyParam verifyParam) throws BusinessException {

        Boolean isEmpty = value == null || StringTools.isEmpty(value.toString());
        Integer length = value == null ? 0 : value.toString().length();

        /**
         * 校验空
         */

        if (isEmpty && (verifyParam.required())) {
            throw new BusinessException(ResponseCodeEnum.CODE_600);
        }

        /**
         * 校验长度
         */

        if (!isEmpty && (verifyParam.max() != -1 && verifyParam.max() < length || verifyParam.min() != -1 && verifyParam.min() > length)) {
            throw new BusinessException(ResponseCodeEnum.CODE_600);
        }

        /**
         * 校验正则
         */
        if (!isEmpty && !StringTools.isEmpty(verifyParam.regex().getRegex()) && !VerifyRegexUtils.verify(verifyParam.regex(), String.valueOf(value))) {
            throw new BusinessException(ResponseCodeEnum.CODE_600);
        }

    }
}
