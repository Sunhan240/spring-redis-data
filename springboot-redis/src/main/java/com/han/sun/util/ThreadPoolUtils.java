/*
 * Copyright (C), 2001-2019, xiaoi机器人
 * Author:   han.sun
 * Date:     2019/3/7 15:34
 * History:
 * <author>          <time>          <version>         <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.han.sun.util;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * <p>获取常用的一些线程池</p><br>
 * TODO(线程池工具类)
 *
 * @author han.sun
 * @version 1.0.0
 * @since 2019/6/28 10:34
 */
public class ThreadPoolUtils {

    private ThreadPoolUtils() {
    }

    /*ThreadPoolExecutor mExecutor = new ThreadPoolExecutor(
            corePoolSize,// 核心线程数
            maximumPoolSize, // 最大线程数
            keepAliveTime, // 闲置线程存活时间
            TimeUnit.MILLISECONDS,// 时间单位
            new LinkedBlockingDeque<Runnable>(),// 线程队列
            Executors.defaultThreadFactory(),// 线程工厂
            new AbortPolicy()// 队列已满,而且当前线程数已经超过最大线程数时的异常处理策略
    );*/

    private static ThreadPoolExecutor threadPool;


    /**
     * 【获取线程池对象】
     *
     * @return 线程池对象
     * @since 2020/3/9 18:25 han.sun
     */
    public static ThreadPoolExecutor getThreadPool() {
        if (threadPool != null) {
            return threadPool;
        } else {
            synchronized (ThreadPoolUtils.class) {
                if (threadPool == null) {
                    ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().
                            setNameFormat("cache-thread-call-runner-%d").build();
                    threadPool = new ThreadPoolExecutor(10, 100,
                            60, TimeUnit.SECONDS,
                            new LinkedBlockingQueue<>(), namedThreadFactory);
                }
                return threadPool;
            }
        }
    }

    /**
     * 【无返回值直接执行】
     *
     * @param runnable Runnable任务对象
     * @since 2020/3/9 15:41 han.sun
     */
    public static void execute(Runnable runnable) {
        getThreadPool().execute(runnable);
    }


    /**
     * 【返回值直接执行】
     *
     * @param callable Callable任务对象
     * @param <T>      泛型
     * @return 有返回值
     * @since 2020/3/9 15:48 han.sun
     */
    public static <T> Future<T> submit(Callable<T> callable) {
        return getThreadPool().submit(callable);
    }

    /**
     * 【取消线程任务】
     *
     * @param runnable Runnable任务对象
     * @since 2020/3/9 15:47 han.sun
     */
    public static void cancel(Runnable runnable) {
        if (runnable != null) {
            getThreadPool().getQueue().remove(runnable);
        }
    }


    /**
     * 【设置有状态的超时任务---有返回值】
     *
     * @param timeOut 超时时长
     * @return 任务的处理结果
     * @since 2020/3/10 15:23 han.sun
     */
    public static <T> T executeTimeOutCallableTask(Callable<T> callable, Integer timeOut) throws Exception {

        T result;
        FutureTask<T> futureTask = new FutureTask<T>(callable);
        try {
            execute(futureTask);
            result = futureTask.get(timeOut, TimeUnit.MILLISECONDS);
            return result;
        } catch (Exception e) {
            futureTask.cancel(true);
            throw new Exception("###" + Thread.currentThread().getName() + "-time out!");
        }
    }


    /**
     * 【设置无状态的超时任务---无返回值】
     *
     * @param timeOut 超时时长
     * @since 2020/3/10 15:23 han.sun
     */
    public static void executeTimeOutRunnableTask(Runnable callable, Integer timeOut) throws Exception {

        FutureTask<String> futureTask = new FutureTask<>(callable, "");
        try {
            execute(futureTask);
            futureTask.get(timeOut, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            futureTask.cancel(true);
            throw new Exception("###" + Thread.currentThread().getName() + "-time out!");
        }
    }

}
