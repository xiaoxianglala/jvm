package com.ysy.jvm;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * @ClassName Mytest4
 * @Description TODO
 * @Author ysy
 * @Date 2020/5/15 12:08
 * 方法区产生内存溢出的错误 元空间的设置：-XX:MaxMetaspaceSize=10m -XX:+TraceClassLoading
 * 错误信息Exception in thread "main" java.lang.OutOfMemoryError: Metaspace
 * 文章：java永久代去哪儿了
 **/
public class Mytest4 {
    public static void main(String[] args) {
        for (; ;){
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(Mytest4.class);
            enhancer.setUseCache(false);
            enhancer.setCallback((MethodInterceptor) (obj,method,args1,proxy) -> proxy.invokeSuper(obj,args1));

            System.out.println("hello xiaoxiang");

            enhancer.create();
        }
    }
}
