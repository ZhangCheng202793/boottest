package com.cz.boot.utils;

import java.io.PrintStream;

public class NullUtil
{
    public static boolean IsAllNotNullOfObject(Object[] objs)
    {
        for (Object obj : objs) {
            if (null == obj) {
                return false;
            }
        }
        return true;
    }

    public static boolean IsAllNotNullOfString(String[] strs)
    {
        for (Object str : strs) {
            if ((null == str) || ("".equals(str.toString().trim().replaceAll("\t", "").replaceAll("\r\n", ""))))
            {
                return false;
            }
        }
        return true;
    }

//    public static void main(String[] args) {
//        Object o = null;
//        System.out.println(IsAllNotNullOfString(new String[] { (String)o }));
//    }
}