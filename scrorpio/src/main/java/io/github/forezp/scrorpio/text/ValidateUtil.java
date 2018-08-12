package io.github.forezp.scrorpio.text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Email miles02@163.com
 *
 * @author fangzhipeng
 * create 2018-07-06
 **/
public class ValidateUtil {



    public static boolean isEmail(String value) {
        Pattern p ;
        Matcher m ;
        boolean b ;
        p = Pattern.compile("^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$");
        m = p.matcher(value);
        b = m.matches();
        return b;
    }


    public static boolean isUrl(String value) {
        Pattern p ;
        Matcher m ;
        boolean b ;
        p = Pattern.compile("^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?$");
        m = p.matcher(value);
        b = m.matches();
        return b;
    }


    public static boolean isChinese(String value) {
        Pattern p;
        Matcher m ;
        boolean b ;
        p = Pattern.compile("^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]+$");
        m = p.matcher(value);
        b = m.matches();
        return b;
    }


    public static boolean isTel(String value) {
        Pattern p ;
        Matcher m ;
        boolean b ;
        p = Pattern.compile("^(([0\\+]\\d{2,3}-)?(0\\d{2,3})-)?(\\d{7,8})(-(\\d{3,}))?$");
        m = p.matcher(value);
        b = m.matches();
        return b;
    }


    public static boolean isMobile(String value) {
        Pattern p ;
        Matcher m ;
        boolean b ;
        p = Pattern.compile("^(13|14|15|17|18)[0-9]{9}$");
        m = p.matcher(value);
        b = m.matches();
        return b;
    }


    public static boolean isDate(String value) {
        Pattern p ;
        Matcher m ;
        boolean b ;
        p = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
        m = p.matcher(value);
        b = m.matches();
        return b;
    }



}
