package jvm.homework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Base64;

public class HelloXlassLoader extends ClassLoader {
    public static void main(String[] args) {
        try {
            Class<?>classes = new HelloXlassLoader().findClass("Hello"); // 加载并初
            Method method = classes.getMethod("hello");
            Object object = classes.newInstance();
            method.invoke(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        byte[] xlassByte = xlass2Byte("/Users/izaodao/Documents/IdeaProject/jvm-study/src/main/java/jvm/homework/Hello.xlass");
        return defineClass(name, xlassByte, 0, xlassByte.length);
    }
    private static byte[] xlass2Byte(String path) {
        File file = new File(path);
        byte[] buffer = new byte[(int) file.length()];
        try {
            FileInputStream inputStream = new FileInputStream(file);
            int offset = 0;
            int numRead = 0;
            while (offset < buffer.length
                && (numRead = inputStream.read(buffer, offset, buffer.length - offset)) >= 0) {
                offset += numRead;
            }
            inputStream.close();
            for (int i = 0; i < buffer.length; i++) {
                buffer[i] = (byte) (255 - buffer[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buffer;
    }

}
