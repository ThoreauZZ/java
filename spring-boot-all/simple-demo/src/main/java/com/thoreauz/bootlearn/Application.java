package com.thoreauz.bootlearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.security.ProtectionDomain;

/**
 * Hello world!
 *
 * @author zhaozhou
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) throws URISyntaxException {

        getURI();
        System.out.println(deduceMainApplicationClass());
        SpringApplication.run(Application.class, args);
    }
    private static URI getURI() throws URISyntaxException {
        ProtectionDomain protectionDomain = Application.class.getProtectionDomain();
        CodeSource codeSource = protectionDomain.getCodeSource();
        URI location = codeSource == null ? null : codeSource.getLocation().toURI();
        return location;
    }
    private static Class<?> deduceMainApplicationClass() {
        try {
            StackTraceElement[] stackTrace = new RuntimeException().getStackTrace();
            for (StackTraceElement stackTraceElement : stackTrace) {
                if ("main".equals(stackTraceElement.getMethodName())) {
                    return Class.forName(stackTraceElement.getClassName());
                }
            }
        }
        catch (ClassNotFoundException ex) {
            // Swallow and continue
        }
        return null;
    }
}
