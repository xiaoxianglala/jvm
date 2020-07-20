package com.ysy.io;

import org.junit.Test;

import java.io.*;

/**
 * @ClassName IOTest
 * @Description TODO
 * @Author ysy
 * @Date 2020/7/17 10:56
 **/
public class IOTest {

    public static void test01() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("请输入一个字符");
        char c;

        c = (char) bufferedReader.read();
        System.out.println("你输入的字符为：" + c );
    }

    public static void test02() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("请输入一个字符，按q介绍");
        char c;
        do {
            c = (char) bufferedReader.read();
            System.out.println("你输入的字符为:" + c);
        }while (c != 'q');
    }

    public static void test03() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("请输入一行字符");
        String str = bufferedReader.readLine();
        System.out.println("你输入的字符串为：" + str);
    }

    @org.junit.Test
    public void test04() throws IOException {
        byte[] bytes = {12,21,34,11,21};
        FileOutputStream fileOutputStream = new FileOutputStream("E:\\workspace\\space2\\zjky\\ysy-jvm\\src\\main\\java\\com\\ysy\\io\\test.txt");
        // 写入二进制文件，直接打开会出现乱码
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }

    @org.junit.Test
    public void test05() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("E:\\workspace\\space2\\zjky\\ysy-jvm\\src\\main\\java\\com\\ysy\\io\\test.txt");
        int c;
        // 读取写入的二进制文件，输出字节数组
        while ((c = fileInputStream.read()) != -1) {
            System.out.print(c);
        }
    }

    @Test
    public void test06() throws IOException {
        FileWriter fileWriter = new FileWriter("E:\\workspace\\space2\\zjky\\ysy-jvm\\src\\main\\java\\com\\ysy\\io\\test.txt");
        fileWriter.write("Hello，world！\n欢迎来到 java 世界\n");
        fileWriter.write("不会覆盖文件原本的内容\n");
//        fileWriter.write(null); 不能直接写入 null
        fileWriter.append("并不是追加一行内容，不要被方法名迷惑\n");
        fileWriter.append(null);
        fileWriter.flush();
        System.out.println("文件的默认编码为" + fileWriter.getEncoding());
        fileWriter.close();
    }
    @Test
    public void test07() throws IOException {
        FileWriter fileWriter = new FileWriter("E:\\workspace\\space2\\zjky\\ysy-jvm\\src\\main\\java\\com\\ysy\\io\\test.txt", false); // 关闭追加模式，变为覆盖模式
        fileWriter.write("Hello，world！欢迎来到 java 世界\n");
        fileWriter.write("我来覆盖文件原本的内容");
        fileWriter.append("我是下一行");
        fileWriter.flush();
        System.out.println("文件的默认编码为" + fileWriter.getEncoding());
        fileWriter.close();
    }

    @Test
    public void test08() throws IOException {
        FileReader fileReader = new FileReader("E:\\workspace\\space2\\zjky\\ysy-jvm\\src\\main\\java\\com\\ysy\\io\\test.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            System.out.println(str);
        }
        fileReader.close();
        bufferedReader.close();
    }

    @Test
    public void test09() throws IOException {
        FileReader fileReader = new FileReader("E:\\workspace\\space2\\zjky\\ysy-jvm\\src\\main\\java\\com\\ysy\\io\\test.txt");
        int c;
        while ((c = fileReader.read()) != -1) {
            System.out.print((char) c);
        }
    }

    public static void main(String[] args) throws IOException {
       /* IOTest.test01();*/
      /*  IOTest.test02();*/
        IOTest.test03();
    }
}
