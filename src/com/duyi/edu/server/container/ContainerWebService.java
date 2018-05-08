package com.duyi.edu.server.container;

import com.duyi.edu.server.common.DYWebService;
import com.duyi.edu.server.common.http.HttpRequest;
import com.duyi.edu.server.common.http.HttpResponse;

import javax.tools.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ContainerWebService implements DYWebService {


    public static void main (String[] args) throws ClassNotFoundException {
        File file = new File("./root/com/duyi/test/Test.java");
        List<File> files = Arrays.asList(file);

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        //获取java文件管理类
        StandardJavaFileManager manager = compiler.getStandardFileManager(null, null, null);
        //获取java文件对象迭代器
        Iterable<? extends JavaFileObject> it = manager.getJavaFileObjects(files.toArray(new File[1]));
        //设置编译参数
        ArrayList<String> ops = new ArrayList<String>();
        ops.add("-Xlint:unchecked");
        //设置classpath
//        ops.add("-classpath ");
//        ops.add(CLASS_PATH);
        ops.add("-d");
        ops.add("./classes");
        //获取编译任务
        JavaCompiler.CompilationTask task = compiler.getTask(null, manager, null, ops, null, it);
        //执行编译任务
        task.call();

//        DiagnosticCollector<JavaFileObject> collector = new DiagnosticCollector<>();
//// 该JavaFileManager实例是com.sun.tools.javac.file.JavacFileManager
//        JavaFileManager manager= compiler.getStandardFileManager(collector, null, null);

//        File file = new File("");
//        System.out.println(file.getAbsolutePath() + "/root/Test.java");
//        Class clazz = ClassLoader.getSystemClassLoader().loadClass(file.getAbsolutePath() + "/root/Test.java");
//        System.out.println(clazz.getName());
        System.out.println(file.getAbsolutePath());
        System.out.println(System.getProperty("user.dir"));
        System.out.println(System.getProperty("os.version"));
        System.out.println(System.getProperty("os.name"));


    }

    @Override
    public void init() {

    }

    @Override
    public void action(HttpRequest httpRequest, HttpResponse httpResponse) {

    }
}
