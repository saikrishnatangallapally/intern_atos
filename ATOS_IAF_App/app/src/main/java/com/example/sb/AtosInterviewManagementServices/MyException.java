package com.example.sb.AtosInterviewManagementServices;

/**
 * Created by PC on 11/07/2017.
 */

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
