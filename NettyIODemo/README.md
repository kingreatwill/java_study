Java源码分析分布式Netty框架之BIO、NIO、AIO底层详解(阿里羽鲲) https://www.ixigua.com/i6734911801504301576/

Java中的TransferTo()实现了Zero-Copy
Zero-Copy技术的使用场景有很多，比如Kafka, 又或者是Netty等，可以大大提升程序的性能。

I/O动作可以分为以下五种模式：
阻塞I/O (Blocking I/O)
非阻塞I/O (Non-Blocking I/O)
I/O复用（I/O Multiplexing)
信号驱动的I/O (Signal Driven I/O)
异步I/O (Asynchrnous I/O) 
