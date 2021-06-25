package com.bookcity;

import org.junit.Test;

import java.io.File;

public class TestClass {
    @Test
    public void read(){
        File file=new File("E:\\IntelliJ-IDEA-2019\\IDEAworkspace\\javaWeb\\book\\web\\static\\img");

        System.out.println(file.getName());
        String[] listArray = file.list();
        File[] listFile = file.listFiles();
        System.out.println(listArray.length);
        System.out.println(listFile.length);

    }
}
