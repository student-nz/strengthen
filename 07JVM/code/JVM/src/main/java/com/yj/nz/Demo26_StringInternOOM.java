package cn.tx;

import java.util.ArrayList;
import java.util.List;

public class StringInternOOM {

    public static void main (String[] args) {
        //List<String> list = new ArrayList<String>() ;
        String s = "";
        while (true) {
            //list. add (String.valueOf (i++).intern()) ;
             s = s + "拓薪教育拓薪教育拓薪教育拓薪教育拓薪教育拓薪教育拓薪教育拓薪教育拓薪教育拓薪教育拓薪教育拓薪教育拓薪教育拓薪教育";
        }
    }
}
