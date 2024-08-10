package com.doge.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateUtils {
    private static final Object lockObj = new Object();
    private static Map<String,ThreadLocal<SimpleDateFormat>> sdMap = new HashMap<String,ThreadLocal<SimpleDateFormat>>();

    private static SimpleDateFormat getSdf(final String pattern){
        ThreadLocal<SimpleDateFormat> tl = sdMap.get(pattern);
        if(tl==null){
            synchronized(lockObj){
                tl = sdMap.get(pattern);
                if(tl==null){
                    tl=new ThreadLocal<SimpleDateFormat>(){
                        @Override
                        protected  SimpleDateFormat initialValue(){

                            return new SimpleDateFormat(pattern);
                        }
                    };
                    sdMap.put(pattern,tl);
                }
            }
        }
        return  tl.get();
    }

    public static String format(Date date,String pattern){
        return  getSdf(pattern).format(date);
    }

    public static Date parse(String DateStr,String pattern){
        try {
            return  getSdf(pattern).parse(DateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }
}
