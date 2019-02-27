package com.cf.sessiontest.io;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;

public class WriterTest {
    public static void main(String[] args) throws Exception {
        // 第1步：使用File类找到一个文件
        File f = new File("d:" + File.separator + "test1.txt");// 声明File 对象
        // 第2步：通过子类实例化父类对象
        Writer out = null;
        Reader in = null;
// 准备好一个输出的对象
        out = new FileWriter(f);
        in = new FileReader(f);
//        in.read()

// 通过对象多态性进行实例化
        // 第3步：进行写操作
        String str = "哈哈Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!Hello World!!!";
// 准备一个字符串
        out.write(str);
// 将内容输出
        // 第4步：关闭输出流
//         out.close();
// 此时没有关闭
    }
}
