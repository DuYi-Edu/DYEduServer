package com.duyi.edu.server;

import java.io.File;
import java.io.FileOutputStream;

public class Main {

    public static void main(String[] args) throws Exception {
        File file = new File("a.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(1);
        fileOutputStream.close();
    }

}
