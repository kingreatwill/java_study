import java.math.BigInteger;
import java.util.Formatter;

//%[argument_index$][flags][width][.precision]conversion
// width:最小长度
// precision:最大长度
//在将precision应用于String时，它表示打印string时输出字符的最大数量。
// 而在将precision应用于浮点数时，它表示小数部分要显示出来的位数（默认是6位小数），如果小数位数过多则舍入，太少则在尾部补零。
// 由于整数没有小数部分，所以precision无法应用于整数，如果你对整数应用precision，则会触发异常。
// - 左对齐，默认有对齐
public class StringReceiptBuilder {
    private double total = 0;
    private Formatter f = new Formatter(new StringBuilder());
    public StringReceiptBuilder() {
        f.format("%-15s %5s %10s%n", "Item", "Qty", "Price");
        f.format("%-15s %5s %10s%n", "----", "---", "-----");
    }
    public void add(String name, int qty, double price) {
        f.format("%-15.15s %5d %10.2f%n", name, qty, price);
        total += price * qty;
    }
    public String build() {
        f.format("%-15s %5s %10.2f%n", "Tax", "",total * 0.06);
        f.format("%-15s %5s %10s%n", "", "", "-----");
        f.format("%-15s %5s %10.2f%n", "Total", "",
                total * 1.06);
        return f.toString();
    }
    public static void main(String[] args) {
        StringReceiptBuilder receiptBuilder =  new StringReceiptBuilder();
        receiptBuilder.add("Jack's Magic Beans", 4, 4.25);
        receiptBuilder.add("Princess Peas", 3, 5.1);
        receiptBuilder.add(
                "Three Bears Porridge", 1, 14.29);
        System.out.println(receiptBuilder.build());
        System.out.println("-----------------");
        {
            Formatter f = new Formatter(System.out);

            char u = 'a';
            System.out.println("u = 'a'");
            f.format("s: %s%n", u);
            // f.format("d: %d%n", u);
            f.format("c: %c%n", u);
            f.format("b: %b%n", u);
            // f.format("f: %f%n", u);
            // f.format("e: %e%n", u);
            // f.format("x: %x%n", u);
            f.format("h: %h%n", u);

            int v = 121;
            System.out.println("v = 121");
            f.format("d: %d%n", v);
            f.format("c: %c%n", v);
            f.format("b: %b%n", v);
            f.format("s: %s%n", v);
            // f.format("f: %f%n", v);
            // f.format("e: %e%n", v);
            f.format("x: %x%n", v);
            f.format("h: %h%n", v);

            BigInteger w = new BigInteger("50000000000000");
            System.out.println(
                    "w = new BigInteger(\"50000000000000\")");
            f.format("d: %d%n", w);
            // f.format("c: %c%n", w);
            f.format("b: %b%n", w);
            f.format("s: %s%n", w);
            // f.format("f: %f%n", w);
            // f.format("e: %e%n", w);
            f.format("x: %x%n", w);
            f.format("h: %h%n", w);

            double x = 179.543;
            System.out.println("x = 179.543");
            // f.format("d: %d%n", x);
            // f.format("c: %c%n", x);
            f.format("b: %b%n", x);
            f.format("s: %s%n", x);
            f.format("f: %f%n", x);
            f.format("e: %e%n", x);
            // f.format("x: %x%n", x);
            f.format("h: %h%n", x);

            StringReceiptBuilder y = new StringReceiptBuilder();
            System.out.println("y = new StringReceiptBuilder()");

            // f.format("d: %d%n", y);
            // f.format("c: %c%n", y);
            f.format("b: %b%n", y);
            f.format("s: %s%n", y);
            // f.format("f: %f%n", y);
            // f.format("e: %e%n", y);
            // f.format("x: %x%n", y);
            f.format("h: %h%n", y);

            boolean z = false;
            System.out.println("z = false");
            // f.format("d: %d%n", z);
            // f.format("c: %c%n", z);
            f.format("b: %b%n", z);
            f.format("s: %s%n", z);
            // f.format("f: %f%n", z);
            // f.format("e: %e%n", z);
            // f.format("x: %x%n", z);
            f.format("h: %h%n", z);


            System.out.println("------%b-----");
            f.format("0: %b%n", 0);
            f.format("1: %b%n", 1);
            f.format("true: %b%n",true);
            f.format("false: %b%n",false);
            f.format("null: %b%n",null);
            //对于boolean基本类型或Boolean对象，其转换结果是对应的true或false。
            // 但是，对其他类型的参数，只要该参数不为null，其转换结果永远都是true。
            // 即使是数字0，转换结果依然为true，而这在其他语言中（包括C），往往转换为false。
            // 所以，将b应用于非布尔类型的对象时请格外小心。


            // 转十六进制
            //String.format("%05X: ", n)
            //String.format("%02X: ", n)
            //String.format("%X: ", n)
        }
    }
}
/*
类型	含义
d	整型（十进制）
c	Unicode字符
b	Boolean值
s	String
f	浮点数（十进制）
e	浮点数（科学计数）
x	整型（十六进制）
h	散列码（十六进制）
%	字面值“%”
* */
