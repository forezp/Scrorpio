[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg?label=license)](https://github.com/forezp/scrorpio/blob/master/LICENSE)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.forezp/scrorpio.svg?label=maven%20central)](http://mvnrepository.com/artifact/io.github.forezp/scrorpio)

## 这个项目干嘛的?

这项目是一个常见的工具增强类

## 怎么使用？

在pom文件引用：

```$xslt
<!-- https://mvnrepository.com/artifact/io.github.forezp/scrorpio -->
<dependency>
    <groupId>io.github.forezp</groupId>
    <artifactId>scrorpio</artifactId>
    <version>1.0.0</version>
</dependency>


```

- 常见功能aop，可见[https://github.com/Nepxion/Matrix](https://github.com/Nepxion/Matrix)
- AbstractBeanPostProcessor是Bean后置增强，抽象出来了，可以对加了注解的Bean功能进行增强

比如以下案例，在Referentity变量加上注解@Reference，就可以将@Reference的一些信息设置到referentity变量中。
有点类似于Dubbo的 @Reference注解的功能。更多细节请查看examples案例。

```$xslt
@Service
public class FieldPostProcessorService {

    @Reference(name = "forezp", url = "https://github.com/forezp")
    Referentity referentity;

    public String test() {
        return referentity.getName() + "==" + referentity.getUrl();
    }
}
```


## 联系我

如果有任何问题，可以联系我，miles02@163.com


## 后续计划



