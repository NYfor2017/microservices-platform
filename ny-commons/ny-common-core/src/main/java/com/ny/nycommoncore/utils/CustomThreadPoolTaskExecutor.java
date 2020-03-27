package com.ny.nycommoncore.utils;

import com.alibaba.ttl.TtlCallable;
import com.alibaba.ttl.TtlRunnable;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * 这是{@link ThreadPoolTaskExecutor}的一个简单替换，可搭配TransmittableThreadLocal实现父子线程之间的数据传递
 *
 * @author N.Y
 * @date 2020-03-27 14:28
 */
public class CustomThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {
    private static final long serialVersionUID = 1L;

    @Override
    public void execute(Runnable task) {
        Runnable ttlRunnable = TtlRunnable.get(task);
        super.execute(ttlRunnable);
    }

    @Override
    public Future<?> submit(Runnable task) {
        Runnable ttlCallable = TtlRunnable.get(task);
        return super.submit(ttlCallable);
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        Callable ttlCallable = TtlCallable.get(task);
        return super.submit(ttlCallable);
    }

    @Override
    public ListenableFuture<?> submitListenable(Runnable task) {
        Runnable ttlRunnable = TtlRunnable.get(task);
        return super.submitListenable(ttlRunnable);
    }

    @Override
    public <T> ListenableFuture<T> submitListenable(Callable<T> task) {
        Callable ttlCallable = TtlCallable.get(task);
        return super.submitListenable(ttlCallable);
    }

}
