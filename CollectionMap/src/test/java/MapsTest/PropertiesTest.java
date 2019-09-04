package MapsTest;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

/*
*
* getProperty(String key)   在此属性列表中搜索具有指定键的属性。如果在此属性列表中找不到该键，则会检查默认属性列表及其默认值（递归）。如果未找到该属性，则该方法返回默认值参数。

list(PrintStream out)  将此属性列表打印到指定的输出流。此方法对于调试很有用。

load(InputStream inStream)  从输入字节流中读取属性列表（键和元素对）。输入流采用加载（Reader）中指定的简单的面向行的格式，并假定使用ISO 8859-1字符编码;即每个字节是一个Latin1字符。不在Latin1中的字符和某些特殊字符在使用Unicode转义符的键和元素中表示。 此方法返回后，指定的流仍保持打开状态。

setProperty(String key, String value) 调用 Hashtable 的方法 put 。他通过调用基类的put方法来设置 键值对。

store(OutputStream out, String comments) 将此Properties表中的此属性列表（键和元素对）以适合使用load（InputStream）方法加载到Properties表的格式写入输出流。 此Properties方法不会写出此Properties表的defaults表中的属性（如果有）。

storeToXML(OutputStream os, String comment, String encoding) 使用指定的编码发出表示此表中包含的所有属性的XML文档。

clear()  清除此哈希表，使其不包含任何键。

stringPropertyNames()  返回此属性列表中的一组键，其中键及其对应的值是字符串，如果尚未从主属性列表中找到相同名称的键，则包括默认属性列表中的不同键。键或键不是String类型的属性将被省略。

* */
public class PropertiesTest {
    @Test
    public void test1(){
        Properties properties = System.getProperties();
        properties.list(System.out);
    }

    @Test
    public void test2() throws IOException {
        Properties pps = new Properties();
        pps.load(new FileInputStream("file.properties"));
        Enumeration<?> fileName = pps.propertyNames();
        while (fileName.hasMoreElements()) {
            String strKey = (String) fileName.nextElement();
            String strValue = pps.getProperty(strKey);
            System.out.println(strKey + "," + strValue);
        }
    }
}
