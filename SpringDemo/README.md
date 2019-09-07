//https://docs.spring.io/spring/docs/5.2.0.BUILD-SNAPSHOT/javadoc-api/
//https://docs.spring.io/spring/docs/5.2.0.BUILD-SNAPSHOT/spring-framework-reference/core.html#spring-core

https://www.springcloud.cc/ 中文

#As of Spring 2.5, you have three options for controlling bean lifecycle behavior:

1. The InitializingBean and DisposableBean callback interfaces

2. Custom init() and destroy() methods

3. The @PostConstruct and @PreDestroy annotations. You can combine these mechanisms to control a given bean.