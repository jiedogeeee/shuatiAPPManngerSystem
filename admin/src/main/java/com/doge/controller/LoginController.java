package com.doge.controller;

import com.doge.annotation.GlobalInterceptor;
import com.doge.annotation.VerifyParam;
import com.doge.entity.constants.Constants;
import com.doge.entity.dto.Captcha;
import com.doge.entity.dto.SessionUserAdminDto;
import com.doge.entity.enums.VerifyRegexEnum;
import com.doge.entity.po.SysAccount;
import com.doge.entity.vo.ResponseVO;
import com.doge.exception.BusinessException;
import com.doge.service.SysAccountService;
import com.doge.utils.StringTools;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Map;

@RestController
public class LoginController extends ABaseController {
    @Resource
    private SysAccountService sysAccountService;

    @RequestMapping("/checkCode")
    public void checkCode(HttpServletResponse response, HttpSession session) throws IOException {

        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        OutputStream os = response.getOutputStream();
        //返回验证码和图片的map
        Map<String, Object> map = Captcha.getImageCode(86, 37, os);

        session.setAttribute(Constants.CHECK_CODE_KEY, map.get("strEnsure").toString().toLowerCase());
        session.setAttribute("codeTime", new Date().getTime());
        try {
            ImageIO.write((BufferedImage) map.get("image"), "jpg", os);
        } catch (IOException e) {

        } finally {
            if (os != null) {
                os.flush();
                os.close();
            }
        }

    }


    /**
     * @param checkCode 前端用户输入返回的验证码
     *                  参数若需要，自行添加
     */
    @RequestMapping(value = "/login")
    @ResponseBody
    @GlobalInterceptor(checkLogin = false)
    public ResponseVO login(
            HttpSession session,
            @VerifyParam(regex = VerifyRegexEnum.PHONE) String phone,
            @VerifyParam(required = true) String password,
            @VerifyParam(required = true) String checkCode) throws Exception {
        // 判断验证码输入是否正确
        if (!(checkCode.equalsIgnoreCase((String) session.getAttribute(Constants.CHECK_CODE_KEY)))) {
            throw new BusinessException("验证码错误，请重新输入！");
        }

        // 在这里可以处理自己需要的事务，比如验证登陆等
//        调用service层的login方法验证账号密码
        SessionUserAdminDto userAdminDto = sysAccountService.login(phone, password);
        session.setAttribute(Constants.SESSION_KEY, userAdminDto);
        return getSuccessResponseVO(userAdminDto);
    }


    @RequestMapping(value = "/logout")
    @GlobalInterceptor(checkLogin = false)
    public ResponseVO logout(HttpSession session) {
        session.invalidate();
        return getSuccessResponseVO(null);
    }

    @RequestMapping(value = "/updateMyPwd")
    @GlobalInterceptor
    public ResponseVO updateMyPwd(HttpSession session,
                                  @VerifyParam(required = true, regex = VerifyRegexEnum.PASSWORD) String password) {
        SessionUserAdminDto sessionUserAdminDto = getUserAdminFromSession(session);
        SysAccount sysAccount = new SysAccount();
        sysAccount.setPassword(StringTools.encodeByMD5(password));
        sysAccountService.updateByUserId(sysAccount, sessionUserAdminDto.getUserId());
        return getSuccessResponseVO(null);
    }

}
