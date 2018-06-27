package io.github.forezp.examples.aop;

import org.springframework.stereotype.Service;

/**
 * Email miles02@163.com
 *
 * @author fangzhipeng
 * create 2018-06-27
 **/
@Service
public class FieldPostProcessorService {

    @Reference(name = "forezp", url = "https://github.com/forezp")
    Referentity referentity;

    public String test() {
        return referentity.getName() + "==" + referentity.getUrl();
    }

}
