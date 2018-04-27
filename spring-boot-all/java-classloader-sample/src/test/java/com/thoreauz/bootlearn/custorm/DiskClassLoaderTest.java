package com.thoreauz.bootlearn.custorm;

import org.testng.annotations.Test;

import java.lang.reflect.Method;

/**
 * 2018/4/23 下午10:39.
 *
 * @author zhaozhou
 */
public class DiskClassLoaderTest {
    @Test
    public void testLoader() {
        DiskClassLoader diskLoader = new DiskClassLoader(".");
        try {
            Class<?> aClass = diskLoader.loadClass("com.thoreauz.bootlearn.custorm.LoadedSample");
            Method method = aClass.getDeclaredMethod("say",null);
            if (aClass != null) {
                Object instance = aClass.newInstance();
                method.invoke(instance, null);
            }
        } catch (Exception e) {

        }
    }

}