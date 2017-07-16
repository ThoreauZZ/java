package com.thoreau.springboot.sample;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.junit.Rule;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * 17/7/15 下午2:44.
 *
 * @author zhaozhou
 */
public class SampleControllerTest {


    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);

    @Test
    public void exampleTest() throws IOException, JSONException {
        String restposeJson = "{\"hello\":\"ok http\"}";
        stubFor(get(urlEqualTo("/my/resource"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody(restposeJson)));

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://localhost:8089/my/resource")
                .addHeader("Accept", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        assertTrue(response.isSuccessful());
        assertEquals(response.code(), 200);
        JSONAssert.assertEquals(restposeJson, response.body().string(), false);


        verify(1, getRequestedFor(urlEqualTo("/my/resource"))
                .withHeader("Accept", equalTo("application/json")));
    }
}
