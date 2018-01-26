package com.eric.utils;

import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.Iterables;

import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author miao.yang susing@gmail.com
 * @date 2014-12-10
 */
public class ServiceFinder<T> {

    private static ConcurrentHashMap<Class<?>, ServiceFinder<?>> instanceMap = new ConcurrentHashMap<Class<?>, ServiceFinder<?>>();

    private final Supplier<T> supplier;

    private ServiceFinder(final Class<T> clz) {
        supplier = Suppliers.memoize(new Supplier<T>() {
            @Override
            public T get() {
                ServiceLoader<T> loader = ServiceLoader.load(clz);
                Preconditions.checkNotNull(loader, "无法找到实例SPI[" + clz + "] 请检查 /META-INFO 配置.");
                T instance = Iterables.getFirst(loader, null);
                Preconditions.checkNotNull(instance, "无法获取实例SPI[" + clz + "][" + loader + "] 请检查 /META-INFO 配置.");
                return instance;
            }
        });
    }


    public static <T> T getService(Class<T> clz) {
        Preconditions.checkNotNull(clz);
        Preconditions.checkArgument(clz.isInterface(), "clz is not a interface");
        ServiceFinder<T> serviceFinder = (ServiceFinder<T>) instanceMap.get(clz);
        if (serviceFinder == null) {
            instanceMap.putIfAbsent(clz, new ServiceFinder<T>(clz));
            serviceFinder = (ServiceFinder<T>) instanceMap.get(clz);
        }
        return serviceFinder.supplier.get();
    }

}
