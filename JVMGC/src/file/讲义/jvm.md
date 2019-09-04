#一、JVM内存模型
JVM主要管理两种类型内存：堆(Heap)和非堆（Permanent区域）。
##1、Heap是运行时数据区域，所有类实例和数组的内存均从此处分配。Heap区分两大块，一块是 Young Generation，另一块是Old Generation：
###1）在Young Generation中，有一个叫Eden Space的空间，主要是用来存放新生的对象，还有两个Survivor Spaces（from,to），它们的大小总是一样，它们用来存放每次垃圾回收后存活下来的对象。
###2）在Old Generation中，主要存放应用程序中生命周期长的内存对象。
##2、Permanent区：
Permanent Generation，主要是存储的是java的类信息，包括解析得到的方法、属性、字段等等。永久带基本不参与垃圾回收。Permanent generation 不是Heap的一部。
.
#二、内存大小
##1、Heap内存分配
JVM初始分配的内存由-Xms指定，默认是物理内存的1/64；

JVM最大分配的内存由-Xmx指 定，默认是物理内存的1/4。

默认空余堆内存小于40%时，JVM 就会增大堆直到-Xmx 的最大限制，可以由 -XX:MinHeapFreeRatio 指定。

默认空余堆内存大于70%时，JVM 会减少堆直到-Xms的最小限制，可以由 -XX:MaxHeapFreeRatio 指定，
##2、Permanent区域内存分配
JVM使用-XX:PermSize设置非堆内存初始值，默认是物理内存的1/64；

由XX:MaxPermSize设置最大非堆内存的大小，默认是物理内存的1/4。
#三、JVM内存分配过程
##1、JVM 会试图为相关Java对象在Eden中初始化一块内存区域。
##2、当Eden空间足够时，内存申请结束；否则到下一步。
##3、JVM 试图释放在Eden中所有不活跃的对象（这属于1或更高级的垃圾回收）。释放后若Eden空间仍然不足以放入新对象，则试图将部分Eden中活跃对象放入Survivor区。
##4、Survivor区被用来作为Eden及Old的中间交换区域，当Old区空间足够时，Survivor区的对象会被移到Old区，否则会被保留在Survivor区。
##5、当Old区空间不够时，JVM 会在Old区进行完全的垃圾收集（0级）。
##6、完全垃圾收集后，若Survivor及Old区仍然无法存放从Eden复制过来的部分对象，导致JVM无法在Eden区为新对象创建内存区域，则出现”out of memory”错误。