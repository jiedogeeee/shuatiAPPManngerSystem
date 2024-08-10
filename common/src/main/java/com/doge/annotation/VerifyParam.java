package com.doge.annotation;

import com.doge.entity.enums.VerifyRegexEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface VerifyParam {
    /*
     * 检验正则
     * */
    VerifyRegexEnum regex() default VerifyRegexEnum.NO;

    /*
     *  最小长度
     * */

    int min() default -1;


    /*
     *   最大长度
     * */

    int max() default -1;

    /*
     * required
     * */

    boolean required() default false;
}
