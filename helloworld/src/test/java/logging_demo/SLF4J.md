#1 获取ILoggerFactory的过程

又可以分成3个过程：

1.1 从类路径中寻找org/slf4j/impl/StaticLoggerBinder.class类
```java
ClassLoader.getSystemResources("org/slf4j/impl/StaticLoggerBinder.class")
```
如果找到多个，则输出 Class path contains multiple SLF4J bindings，表示有多个日志实现与slf4j进行了绑定
下面看下当出现多个StaticLoggerBinder的时候的输出日志（简化了一些内容）：
```java
SLF4J: Class path contains multiple SLF4J bindings.
SLF4J: Found binding in [slf4j-log4j12-1.7.12.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: Found binding in [logback-classic-1.1.3.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: Found binding in [slf4j-jdk14-1.7.12.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.
SLF4J: Actual binding is of type [org.slf4j.impl.Log4jLoggerFactory]
```
1.2 "随机选取"一个StaticLoggerBinder.class来创建一个单例
```java
StaticLoggerBinder.getSingleton()
```
这里的"随机选取"可以见官方文档说明：
```
SLF4J API is designed to bind with one and only one underlying logging framework at a time.
 If more than one binding is present on the class path, SLF4J will emit a warning, listing the location of those bindings

The warning emitted by SLF4J is just that, a warning. 
Even when multiple bindings are present,SLF4J will pick one logging framework/implementation and bind with it. 
The way SLF4J picks a binding is determined by the JVM and for all practical purposes should be considered random
```

1.3 根据上述创建的StaticLoggerBinder单例，返回一个ILoggerFactory实例
```java
StaticLoggerBinder.getSingleton().getLoggerFactory()
```
所以slf4j与其他实际的日志框架的集成jar包中，都会含有这样的一个org/slf4j/impl/StaticLoggerBinder.class类文件，并且提供一个ILoggerFactory的实现

https://my.oschina.net/pingpangkuangmo/blog/408382