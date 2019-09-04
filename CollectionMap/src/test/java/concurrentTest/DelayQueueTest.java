package concurrentTest;

import org.junit.Test;

import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DelayQueueTest {
    @Test
    public void test1() throws InterruptedException {
        DelayQueue<OrderMessage> delayQueue = new DelayQueue<>();
        delayQueue.add(new OrderMessage("1","1",10){});
        delayQueue.add(new OrderMessage("2","1",15){});
        /*启动一个线程，处理延迟消息**/
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                OrderMessage message = null;
                while (true) {
                    try {
                        message = delayQueue.take();
                        System.out.println(new Date()+"  处理延迟消息:  " +  message.getOrderId());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread.sleep(1000*1000);
    }

    public class OrderMessage implements Delayed {

        private final static long DELAY = 15*60*1000L;//默认延迟15分钟

        private final String orderId;//订单号

        private final long startTime ;//开始时间

        private final long expire ;//到期时间

        private final Date now; //创建时间

        private final String orderMsg;//订单其他信息JSON方式保存，备用字段

        public OrderMessage(String orderId, String startTimeStr14 ,long secondsDelay) {
            super();
            this.orderId = orderId;
            this.startTime = new Date().getTime();
            this.expire = startTime + (secondsDelay*1000);
            this.now = new Date();
            this.orderMsg="";
        }

        public OrderMessage(String orderId, String startTimeStr14, String orderMsg ,long secondsDelay) {
            super();
            this.orderId = orderId;
            this.startTime = new Date().getTime();
            this.expire = startTime + (secondsDelay*1000);
            this.orderMsg = orderMsg;
            this.now = new Date();
        }

        public OrderMessage(String orderId, String startTimeStr14) {
            super();
            this.orderId = orderId;
            this.startTime = new Date().getTime();
            this.expire = startTime + DELAY;
            this.now = new Date();
            this.orderMsg="";
        }

        public OrderMessage(String orderId, String startTimeStr14, String orderMsg) {
            super();
            this.orderId = orderId;
            this.startTime = new Date().getTime();
            this.expire = startTime + DELAY;
            this.orderMsg = orderMsg;
            this.now = new Date();
        }

        @Override
        public int compareTo(Delayed o) {
            return (int) (this.getDelay(TimeUnit.MILLISECONDS) -o.getDelay(TimeUnit.MILLISECONDS));
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(this.expire - System.currentTimeMillis() , TimeUnit.MILLISECONDS);
        }

        public String getOrderId() {
            return orderId;
        }

        public String getOrderMsg() {
            return orderMsg;
        }

        public Date getNow() {
            return now;
        }

        public long getStartTime() {
            return startTime;
        }

        public long getExpire() {
            return expire;
        }

    }
}
