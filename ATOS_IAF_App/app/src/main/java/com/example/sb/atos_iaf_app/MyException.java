package com.example.sb.atos_iaf_app;

/**
 * Created by PC on 11/07/2017.
 */
import java.io.*;
public class MyException extends Exception {
 int a;
    public MyException(int a)
    {
        this.a=a;
    }
    public String toString()
    {
        return "MyException["+a+"]";
    }

}
