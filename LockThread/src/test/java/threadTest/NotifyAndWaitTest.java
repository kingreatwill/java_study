package threadTest;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class NotifyAndWaitTest {
    @Test
    public void Test1() throws InterruptedException {
        Message msg = new Message("process it");
        Waiter waiter = new Waiter(msg);
        new Thread(waiter,"waiter").start();

        Waiter waiter1 = new Waiter(msg);
        new Thread(waiter1, "waiter1").start();

        Notifier notifier = new Notifier(msg);
        new Thread(notifier, "notifier").start();
//        Notifier notifier2 = new Notifier(msg);
//        new Thread(notifier2, "notifier").start();
        System.out.println("All the threads are started");
        //可以看到两个线程在对象msg上进行等待，调用notify方法时，只有一个线程被唤醒，此时程序并没有退出，因为还有一个线程在等待。
        //
        //如果把notify方法改成notifyAll，运行结果为：
        TimeUnit.SECONDS.sleep(10);
        /*
        * 线程在Running的过程中可能会遇到阻塞(Blocked)情况
    调用join()和sleep()方法，sleep()时间结束或被打断，join()中断,IO完成都会回到Runnable状态，等待JVM的调度。
    调用wait()，使该线程处于等待池(wait blocked pool),直到notify()/notifyAll()，线程被唤醒被放到锁定池(lock blocked pool )，释放同步锁使线程回到可运行状态（Runnable）
    对Running状态的线程加同步锁(Synchronized)使其进入(lock blocked pool ),同步锁被释放进入可运行状态(Runnable)。
    此外，在runnable状态的线程是处于被调度的线程，此时的调度顺序是不一定的。Thread类中的yield方法可以让一个running状态的线程转入runnable。
    *
    * //当前线程可转让cpu控制权，让别的就绪状态线程运行（切换）
public static Thread.yield()
//暂停一段时间
public static Thread.sleep()
//在一个线程中调用other.join(),将等待other执行完后才继续本线程。　　　　
public join()
//后两个函数皆可以被打断
public interrupte()
        * */
    }

    public class Message {
        private String msg;

        public Message(String str){
            this.msg=str;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String str) {
            this.msg=str;
        }

    }
    public class Notifier implements Runnable {

        private Message msg;

        public Notifier(Message msg) {
            this.msg = msg;
        }

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            System.out.println(name+" started");
            try {
                Thread.sleep(1000);
                synchronized (msg) {
                    msg.setMsg(name+" Notifier work done");
                    msg.notify();
//                 msg.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
    public class Waiter implements Runnable{

        private Message msg;

        public Waiter(Message m){
            this.msg=m;
        }

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            synchronized (msg) {
                try{
                    System.out.println(name+" waiting to get notified at time:"+System.currentTimeMillis());
                    msg.wait();
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println(name+" waiter thread got notified at time:"+System.currentTimeMillis());
                //process the message now
                System.out.println(name+" processed: "+msg.getMsg());
            }
        }

    }
}
