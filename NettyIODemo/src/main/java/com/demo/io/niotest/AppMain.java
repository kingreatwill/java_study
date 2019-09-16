package com.demo.io.niotest;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.*;
import java.sql.SQLOutput;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class AppMain {
    // https://blog.csdn.net/u011381576/article/details/79876754
    public static void main(String[] args) throws IOException {

    }

    public static void method() throws IOException{
        // 设置输入源 & 输出地 = 文件
        String infile = "E:\\copy.sql";
        String outfile = "E:\\copy.txt";

        // 1. 获取数据源 和 目标传输地的输入输出流（此处以数据源 = 文件为例）
        FileInputStream fin = new FileInputStream(infile);
        FileOutputStream fout = new FileOutputStream(outfile);

        // 2. 获取数据源的输入输出通道
        FileChannel fcin = fin.getChannel();
        FileChannel fcout = fout.getChannel();


        // 3. 创建 缓冲区 对象：Buffer（共有2种方法）
        // 方法1：使用allocate()静态方法
        ByteBuffer buff = ByteBuffer.allocate(256);
        // 上述方法创建1个容量为256字节的ByteBuffer
        // 注：若发现创建的缓冲区容量太小，则重新创建一个大小合适的缓冲区

        // 方法2：通过包装一个已有的数组来创建
        // 注：通过包装的方法创建的缓冲区保留了被包装数组内保存的数据
        //ByteBuffer buff = ByteBuffer.wrap(byteArray);

        // 额外：若需将1个字符串存入ByteBuffer，则如下
        String sendString="你好,服务器. ";
        ByteBuffer sendBuff = ByteBuffer.wrap(sendString.getBytes("UTF-16"));

        while (true) {
            // 4. 从通道读取数据 & 写入到缓冲区
            // 注：若 以读取到该通道数据的末尾，则返回-1
            int r = fcin.read(buff);
            if (r == -1) {
                break;
            }
            // 5. 传出数据准备：调用flip()方法   将缓存区的写模式 转换->> 读模式
            buff.flip();

            // 6. 从 Buffer 中读取数据 & 传出数据到通道
            fcout.write(buff);

            // 7. 重置缓冲区
            // 目的：重用现在的缓冲区,即 不必为了每次读写都创建新的缓冲区，在再次读取之前要重置缓冲区
            // 注：不会改变缓冲区的数据，只是重置缓冲区的主要索引值
            buff.clear();

        }
    }

    // java nio
    public static void method1(){
        RandomAccessFile aFile = null;
        try{
            aFile = new RandomAccessFile("src/nio.txt","rw");
            FileChannel fileChannel = aFile.getChannel();
            ByteBuffer buf = ByteBuffer.allocate(1024);
            int bytesRead = fileChannel.read(buf);
            System.out.println(bytesRead);
            while(bytesRead != -1)
            {
                buf.flip();
                while(buf.hasRemaining())
                {
                    System.out.print((char)buf.get());
                }
                buf.compact();
                bytesRead = fileChannel.read(buf);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally{
            try{
                if(aFile != null){
                    aFile.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    // java io
    public static void method2(){
        InputStream in = null;
        try{
            in = new BufferedInputStream(new FileInputStream("src/nomal_io.txt"));
            byte [] buf = new byte[1024];
            int bytesRead = in.read(buf);
            while(bytesRead != -1)
            {
                for(int i=0;i<bytesRead;i++)
                    System.out.print((char)buf[i]);
                bytesRead = in.read(buf);
            }
        }catch (IOException e)
        {
            e.printStackTrace();
        }finally{
            try{
                if(in != null){
                    in.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    // java BIO
    public static void server1(){
        ServerSocket serverSocket = null;
        InputStream in = null;
        try
        {
            serverSocket = new ServerSocket(8080);
            int recvMsgSize = 0;
            byte[] recvBuf = new byte[1024];
            while(true){
                Socket clntSocket = serverSocket.accept();
                SocketAddress clientAddress = clntSocket.getRemoteSocketAddress();
                System.out.println("Handling client at "+clientAddress);
                in = clntSocket.getInputStream();
                while((recvMsgSize=in.read(recvBuf))!=-1){
                    byte[] temp = new byte[recvMsgSize];
                    System.arraycopy(recvBuf, 0, temp, 0, recvMsgSize);
                    System.out.println(new String(temp));
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally{
            try{
                if(serverSocket!=null){
                    serverSocket.close();
                }
                if(in!=null){
                    in.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    // java NIO
    public static void client1(){
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        SocketChannel socketChannel = null;
        // open
        // connect
        // ...write
        // close
        try
        {
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress("127.0.0.1",8080));
            if(socketChannel.finishConnect())
            {
                int i=0;
                while(true)
                {
                    TimeUnit.SECONDS.sleep(1);
                    String info = "I'm "+i+++"-th information from client";
                    buffer.clear();
                    buffer.put(info.getBytes());
                    buffer.flip();
                    while(buffer.hasRemaining()){
                        System.out.println(buffer);
                        socketChannel.write(buffer);
                    }
                }
            }
        }
        catch (IOException | InterruptedException e)
        {
            e.printStackTrace();
        }
        finally{
            try{
                if(socketChannel!=null){
                    socketChannel.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    // java NIO
    public static void server() throws IOException {
        // 1. 创建Selector对象
        Selector sel = Selector.open();

// 2. 向Selector对象绑定通道
        // a. 创建可选择通道，并配置为非阻塞模式
        ServerSocketChannel server = ServerSocketChannel.open();
        server.configureBlocking(false);

        // b. 绑定通道到指定端口
        ServerSocket socket = server.socket();
        InetSocketAddress address = new InetSocketAddress(8083);
        socket.bind(address);

        // c. 向Selector中注册感兴趣的事件(四种)
        SelectionKey selectionKey = server.register(sel, SelectionKey.OP_ACCEPT);
        //1. SelectionKey.OP_CONNECT
        //2. SelectionKey.OP_ACCEPT
        //3. SelectionKey.OP_READ
        //4. SelectionKey.OP_WRITE
        // 与Selector一起使用时，Channel必须处于非阻塞模式下。这意味着不能将FileChannel与Selector一起使用，因为FileChannel不能切换到非阻塞模式。而套接字通道都可以。
        //selectionKey.isAcceptable();
        //selectionKey.isConnectable();
        //selectionKey.isReadable();
        //selectionKey.isWritable();
        //Channel  channel  = selectionKey.channel();
        //Selector selector = selectionKey.selector();
        //可以将一个对象或者更多信息附着到SelectionKey上，这样就能方便的识别某个给定的通道。例如，可以附加 与通道一起使用的Buffer，或是包含聚集数据的某个对象。使用方法如下：
        //
        //selectionKey.attach(theObject);
        //Object attachedObj = selectionKey.attachment();
        //还可以在用register()方法向Selector注册Channel的时候附加对象。如：
        //
        //SelectionKey key = channel.register(selector, SelectionKey.OP_READ, theObject);

        // 3. 处理事件
        try {
            while(true) {
                // 该调用会阻塞，直到至少有一个事件就绪、准备发生
                sel.select();
                // 一旦上述方法返回，线程就可以处理这些事件
                Set<SelectionKey> keys = sel.selectedKeys();
                Iterator<SelectionKey> iter = keys.iterator();
                while (iter.hasNext()) {
                    SelectionKey key = (SelectionKey) iter.next();
                    iter.remove();
                    System.out.println(key);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ByteBuffer
    public static void method4(){
        RandomAccessFile aFile = null;
        FileChannel fc = null;
        try{
            aFile = new RandomAccessFile("src/1.ppt","rw");
            fc = aFile.getChannel();
            long timeBegin = System.currentTimeMillis();
            ByteBuffer buff = ByteBuffer.allocate((int) aFile.length());
            buff.clear();
            fc.read(buff);
            //System.out.println((char)buff.get((int)(aFile.length()/2-1)));
            //System.out.println((char)buff.get((int)(aFile.length()/2)));
            //System.out.println((char)buff.get((int)(aFile.length()/2)+1));
            long timeEnd = System.currentTimeMillis();
            System.out.println("Read time: "+(timeEnd-timeBegin)+"ms");
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                if(aFile!=null){
                    aFile.close();
                }
                if(fc!=null){
                    fc.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    // MappedByteBuffer 读取大文件有绝对的优势
    public static void method3(){
        RandomAccessFile aFile = null;
        FileChannel fc = null;
        try{
            aFile = new RandomAccessFile("src/1.ppt","rw");
            fc = aFile.getChannel();
            long timeBegin = System.currentTimeMillis();
            MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_ONLY, 0, aFile.length());
            //READ_ONLY,（只读）： 试图修改得到的缓冲区将导致抛出 ReadOnlyBufferException.(MapMode.READ_ONLY)
            //
            //READ_WRITE（读/写）： 对得到的缓冲区的更改最终将传播到文件；该更改对映射到同一文件的其他程序不一定是可见的。 (MapMode.READ_WRITE)
            //
            //PRIVATE（专用）： 对得到的缓冲区的更改不会传播到文件，并且该更改对映射到同一文件的其他程序也不是可见的；相反，会创建缓冲区已修改部分的专用副本。 (MapMode.PRIVATE)
            //
            //MappedByteBuffer是ByteBuffer的子类，其扩充了三个方法：
            // mbb.force();
            //force()：缓冲区是READ_WRITE模式下，此方法对缓冲区内容的修改强行写入文件；
            // mbb.load();
            //load()：将缓冲区的内容载入内存，并返回该缓冲区的引用；
            // mbb.isLoaded();
            //isLoaded()：如果缓冲区的内容在物理内存中，则返回真，否则返回假；
            // System.out.println((char)mbb.get((int)(aFile.length()/2-1)));
            // System.out.println((char)mbb.get((int)(aFile.length()/2)));
            //System.out.println((char)mbb.get((int)(aFile.length()/2)+1));
            long timeEnd = System.currentTimeMillis();
            System.out.println("Read time: "+(timeEnd-timeBegin)+"ms");
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                if(aFile!=null){
                    aFile.close();
                }
                if(fc!=null){
                    fc.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

}
