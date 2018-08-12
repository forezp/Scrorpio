package io.github.forezp.scrorpio.text;


import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * Email miles02@163.com
 * 只适用于cglib代码
 * @author fangzhipeng
 * create 2018-06-27
 **/
public class SpelUtil {

    public static String parseKey(String key, Method method, Object[] args) {

        //如果不含# 则判断不为spel表达式，这有一定的误差，但是够用
        // fixme
        //TODO
        if (!key.contains( "#" ) || StringUtils.isEmpty( key )) {
            return key;
        }
        //获取被拦截方法参数名列表(使用Spring支持类库)
        LocalVariableTableParameterNameDiscoverer u =
                new LocalVariableTableParameterNameDiscoverer();
        String[] paraNameArr = u.getParameterNames( method );

        //使用SPEL进行key的解析
        ExpressionParser parser = new SpelExpressionParser();
        //SPEL上下文
        StandardEvaluationContext context = new StandardEvaluationContext();
        //把方法参数放入SPEL上下文中
        for (int i = 0; i < paraNameArr.length; i++) {
            context.setVariable( paraNameArr[i], args[i] );
        }
        return parser.parseExpression( key ).getValue( context, String.class );
    }
}
