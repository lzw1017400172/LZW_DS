package com.lzw.goods.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by LZW on 2019/5/12.
 */
public class ThreadUtils {

    private static final ExecutorService es = Executors.newFixedThreadPool(10);
    //初始化一个静态线程池，专门用户生成 静态页面使用
    //静态的类变量，类初始化的时候，jdk1.8之后在堆空间开辟内存来存储。jdk1.7之前在永久代
    //一般线程池需要关闭的，这个不关闭，类似 ibase4j中用来收集日志的用法。

    //线程池中取一个线程执行
    public static void execute(Runnable runnable) {
        es.submit(runnable);
    }

}
