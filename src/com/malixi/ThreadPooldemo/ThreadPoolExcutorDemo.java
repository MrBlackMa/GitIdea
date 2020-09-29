package com.malixi.ThreadPooldemo;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池
 * corePoolSize 核心线程   -- 线程生存时间到了 不归还核心线程
 * maxmumPoolSize 最大存在线程数  -- 一般以核心线程为主 不够增加到最大线程
 * keepAliveTime 线程生存时间 60  -- 线程生存时间 到了时间 归还给系统的非核心线程
 * TimeUnit     时间单位
 * BlockingQueue<Runnable> workQueue, 放置队列容器
 * ThreadFactory threadFactory,   线程工厂
 * RejectedExecutionHandler handler 线程池忙 任务队列满 执行拒绝策略
 *
 */
public class ThreadPoolExcutorDemo {
    //ThreadPoolExecutor t1=new ThreadPoolExecutor();
}
