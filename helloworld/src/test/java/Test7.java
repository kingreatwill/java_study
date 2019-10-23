import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

public class Test7 {
    @Test
    public void test() throws InterruptedException {

        Book novel = new Book(true);
        // Proper cleanup:
        novel.checkIn();
        // Drop the reference, forget to clean up:
         new Book(true);
        //Book novel2 = new Book(true); 不会回收
        // Force garbage collection & finalization:
        System.gc();
        TimeUnit.MILLISECONDS.sleep((int)(1000 * 10));
    }

    public class MethodInit2 {
        int i = f();
        int j = g(i);

        int f() {
            return 11;
        }

        int g(int n) {
            return n * 10;
        }
    }

    public class MethodInit3 {
        // int j = g(i); // Illegal forward reference
        int i = f();

        int f() {
            return 11;
        }

        int g(int n) {
            return n * 10;
        }
    }

    public class InitialValues2 {
        boolean bool = true;
        char ch = 'x';
        byte b = 47;
        short s = 0xff;
        int i = 999;
        long lng = 1;
        float f = 3.14f;
        double d = 3.14159;
    }
    class Book {
        boolean checkedOut = false;

        Book(boolean checkOut) {
            checkedOut = checkOut;
        }

        void checkIn() {
            checkedOut = false;
        }

        @Override
        protected void finalize() throws Throwable {
            if (checkedOut) {
                System.out.println("Error: checked out");
            }
            // Normally, you'll also do this:
            // super.finalize(); // Call the base-class version
        }
    }
}
