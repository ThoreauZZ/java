package com.thoreau.protobuf;

import com.googlecode.protobuf.format.JsonFormat;
import com.thoreau.protobuf.generated.vo.User;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.io.InputStream;

/**
 * 2018/3/23 13:55.
 *
 * @author zhaozhou
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getUserTest() {
        ResponseEntity<User> user = restTemplate.getForEntity("/user", User.class);
        // assert
    }

    @Test
    public void getUserJson() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://127.0.0.1:" + port + "/user")
                .build();
        Response response = client.newCall(request).execute();
        InputStream inputStream = null;
        try {
            inputStream = response.body().byteStream();
            JsonFormat jsonFormat = new JsonFormat();
            User user = User.parseFrom(inputStream);
            // assert
            System.out.println(jsonFormat.printToString(user));
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }
}