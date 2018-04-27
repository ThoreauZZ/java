package com.thoreauz.bootlearn.custorm;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 2018/4/23 下午10:32.
 *
 * @author zhaozhou
 */
public class DiskClassLoader extends ClassLoader {
    private String libPath;

    public DiskClassLoader(String libPath) {
        libPath = libPath;
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String fileName = getFileName(name);
        File file = new File(libPath, fileName);
        FileInputStream fileInputStream = null;
        ByteArrayOutputStream bos = null;
        try {
            fileInputStream = new FileInputStream(file);
            bos = new ByteArrayOutputStream();
            int len = 0;
            try {
                while ((len = fileInputStream.read()) != -1) {
                    bos.write(len);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            byte[] data = bos.toByteArray();
            return defineClass(name,data,0,data.length);
        }catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return super.findClass(name);
    }

    /**
     * 获取类名
     *
     * @param name
     * @return
     */
    private String getFileName(String name) {
        int index = name.lastIndexOf('.');
        if (index == -1) {
            return name + ".class";
        } else {
            return name.substring(index + 1) + ".class";
        }
    }
}
