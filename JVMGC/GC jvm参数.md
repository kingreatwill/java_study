

# 串行垃圾回收器
JDK 1.3之前的垃圾回收器，单线程回收，并且会有stop theworld（下文会简称STW），也即GC时，暂停所有用户线程。
其运行方式是单线程的，适合Client模式的应用，适合单CPU环境。串行的垃圾收集器有两种，Serial以及SerialOld，一般会搭配使用。
新生代使用Serial采取复制算法，老年代使用Serial Old采取标记整理算法。Client应用或者命令行程序可以，
通过-XX:+UseSerialGC可以开启上述回收模式。
Serial：用于新生代垃圾收集，复制算法
SerialOld：用于老年代垃圾收集，标记整理算法


# 并行垃圾回收器
整体来说，并行垃圾回收相对于串行，是通过多线程运行垃圾收集的。也会stop-the-world。适合Server模式以及多CPU环境。一般会和jdk1.5之后出现的CMS搭配使用。并行的垃圾回收器有以下几种：
1. ParNew：Serial收集器的多线程版本，默认开启的收集线程数和cpu数量一样，运行数量可以通过修改ParallelGCThreads设定。用于新生代收集，复制算法。使用-XX:+UseParNewGC,和Serial Old收集器组合进行内存回收。
2. Parallel Scavenge: 关注吞吐量,吞吐量优先，吞吐量=代码运行时间/(代码运行时间+垃圾收集时间),也就是高效率利用cpu时间，尽快完成程序的运算任务 
3. 可以设置最大停顿时间MaxGCPauseMillis以及，吞吐量大小GCTimeRatio。如果设置了-XX:+UseAdaptiveSizePolicy参数，则随着GC,会动态调整新生代的大小，Eden,Survivor比例等，以提供最合适的停顿时间或者最大的吞吐量。用于新生代收集，复制算法。通过-XX:+UseParallelGC参数，Server模式下默认提供了其和SerialOld进行搭配的分代收集方式。
4. Parllel Old：Parallel Scavenge的老年代版本。JDK 1.6开始提供的。在此之前Parallel Scavenge的地位也很尴尬，而有了Parllel Old之后，通过-XX:+UseParallelOldGC参数使用Parallel Scavenge + Parallel Old器组合进行内存回收。

-Xmx12g -Xms12g -Xmn8g
-XX:-UseAdaptiveSizePolicy
-XX:MaxTenuringThreshold=15-XX:InitialTenuringThreshold=15
-XX:+UseParallelOldGC
-XX:+PrintGCDetails
-XX:+PrintGCTimeStamps -XX:+PrintFlagsFinal

# 并发标记扫描垃圾回收器(CMS)
CMS（Concurrent Mark Sweep）基于“标记—清除”算法，用于老年代,所以其关注点在于减少“pause time”也即因垃圾回收导致的stop the world时间。对于重视服务的响应速度的应用可以使用CMS。因为CMS是“并发”运行的，也即垃圾收集线程可以和用户线程同时运行。 缺点就是会产生内存碎片。 
CMS的回收分为几个阶段：

1. 初始标记：标记一下GC Roots能直接关联到的对象，会“Stop The World”
2. 并发标记：GC Roots Tracing，可以和用户线程并发执行。
3. 重新标记：标记期间产生的对象存活的再次判断，修正对这些对象的标记，执行时间相对并发标记短，会“Stop The World”。
4. 并发清除：清除对象,可以和用户线程并发执行。
CMS最主要解决了pause time，但是会占用CPU资源，牺牲吞吐量。CMS默认启动的回收线程数是（CPU数量+3）/ 4，当CPU<4个时，会影响用户线程的执行。另外一个缺点就是内存碎片的问题了，碎片会给大对象的内存分配造成麻烦，如果老年代的可用的连续空间也无法分配时，会触发full gc。并且full gc时如果发生young gc会被young gc打断，执行完young gc之后再继续执行full gc。 
-XX:UseConcMarkSweepGC参数可以开启CMS,年轻代使用ParNew，老年代使用CMS，同时Serial Old收集器将作为CMS收集器出现Concurrent Mode Failure失败后的后备收集器使用。
JDK 9 已经弃用该垃圾回收器

# G1垃圾收集器
G1（Garbage-First）是在JDK 7u4版本之后发布的垃圾收集器，并在jdk9中成为默认垃圾收集器。
通过“-XX:+UseG1GC”启动参数即可指定使用G1 GC。从整体来说，G1也是利用多CPU来缩短stop the world时间，并且是高效的并发垃圾收集器。
但是G1不再像上文所述的垃圾收集器，需要分代配合不同的垃圾收集器，因为G1中的垃圾收集区域是“分区”（Region）的。
G1的分代收集和以上垃圾收集器不同的就是除了有年轻代的ygc，全堆扫描的fullgc外，还有包含所有年轻代以及部分老年代Region的MixedGC。
G1的优势还有可以通过调整参数，指定垃圾收集的最大允许pause time。下面会详细阐述下G1分区以及分代的概念，以及G1 GC的几种收集过程的分类。

在G1垃圾收集器中，最好的优化状态就是通过不断调整分区空间，避免进行full gc，可以大幅提高吞吐量。
-XX:+UseG1GC	                                                使用 G1 (Garbage First) 垃圾收集器
-XX:MaxGCPauseMillis=n	                                        设置最大GC停顿时间(GC pause time)指标(target). 这是一个软性指标(soft goal), JVM 会尽量去达成这个目标.
-XX:InitiatingHeapOccupancyPercent=n	                        启动并发GC周期时的堆内存占用百分比. G1之类的垃圾收集器用它来触发并发GC周期,基于整个堆的使用率,而不只是某一代内存的使用比. 值为 0 则表示"一直执行GC循环". 默认值为 45.
-XX:NewRatio=n	                                                新生代与老生代(new/old generation)的大小比例(Ratio). 默认值为 2.
-XX:SurvivorRatio=n	                                            eden/survivor 空间大小的比例(Ratio). 默认值为 8.
-XX:MaxTenuringThreshold=n	                                    提升年老代的最大临界值(tenuring threshold). 默认值为 15.
-XX:ParallelGCThreads=n	设                                      置垃圾收集器在并行阶段使用的线程数,默认值随JVM运行的平台不同而不同.
-XX:ConcGCThreads=n	                                            并发垃圾收集器使用的线程数量. 默认值随JVM运行的平台不同而不同.
-XX:G1ReservePercent=n	                                        设置堆内存保留为假天花板的总量,以降低提升失败的可能性. 默认值是 10.
-XX:G1HeapRegionSize=n	                                        使用G1时Java堆会被分为大小统一的的区(region)。此参数可以指定每个heap区的大小. 默认值将根据 heap size 算出最优解. 最小值为 1Mb, 最大值为 32Mb.


# ZGC https://www.jianshu.com/p/60d9e125dcf3
JDK 11   https://wiki.openjdk.java.net/display/zgc/Main
JDK 13   http://openjdk.java.net/jeps/351 JEP 351: ZGC: Uncommit Unused Memory
Use the -XX:+UnlockExperimentalVMOptions -XX:+UseZGC options to enable ZGC.
Setting Heap Size (-Xmx<size>)
Setting Concurrent GC Threads (-XX:ConcGCThreads=<number>).your system should never have more than 70% CPU utilization.
1. 像G1一样划分Region，但更加灵活
ZGC将堆划分为Region作为清理，移动，以及并行GC线程工作分配的单位。
不过G1一开始就把堆划分成固定大小的Region，而ZGC 可以有2MB，32MB，N× 2MB 三种Size Groups，动态地创建和销毁Region，动态地决定Region的大小。
256k以下的对象分配在Small Page
4M以下对象在Medium Page
4M以上在Large Page。
所以ZGC能更好的处理大对象的分配。

2. 和G1一样会做Compacting－压缩
CMS是Mark-Sweep标记过期对象后原地回收，这样就会造成内存碎片，越来越难以找到连续的空间，直到发生Full GC才进行压缩整理。
ZGC是Mark-Compact ，会将活着的对象都移动到另一个Region，整个回收掉原来的Region。
而G1 是 incremental copying collector，一样会做压缩。

3. 没分代，应该是ZGC唯一的弱点了。
分代原本是因为most object die young的假设，而让新生代和老生代使用不同的GC算法。


# No-Op GC
jdk 11 http://openjdk.java.net/jeps/318
-XX:+UseEpsilonGC.

# Low-Pause-Time GC
jdk 12 http://openjdk.java.net/jeps/189
To enable/use Shenandoah GC, the following JVM options will be needed: -XX:+UnlockExperimentalVMOptions -XX:+UseShenandoahGC.