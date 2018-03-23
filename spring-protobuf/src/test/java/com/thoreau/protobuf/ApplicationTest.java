package com.thoreau.protobuf;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * 2018/3/23 13:55.
 *
 * @author zhaozhou
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationTest {
    private static final String COURSE1_URL = "http://localhost:8080/user";
    @Autowired
    private RestTemplate restTemplate;
    private int port = 8080;
    @Configuration
    public static class RestClientConfiguration {
        @Bean
        RestTemplate restTemplate(ProtobufHttpMessageConverter hmc) {
            return new RestTemplate(Arrays.asList(hmc));
        }
        @Bean
        ProtobufHttpMessageConverter protobufHttpMessageConverter() {
            return new ProtobufHttpMessageConverter();
        }
    }

    @Test
    public void getUserTest() {
        ResponseEntity<UserVO> customer = restTemplate.getForEntity(
                "http://127.0.0.1:" + port + "/customers/2", CustomerProtos.Customer.class);
    }
}