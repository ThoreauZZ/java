package com.thoreau.springboot.sample.test;

import com.jayway.jsonpath.JsonPath;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * 17/7/20 上午12:06.
 *
 * @author zhaozhou
 */
public class JsonAssertSample {
    @Test
    public void test() throws Exception {
        //get json from server
        //language=JSON
        String jsonStr ="{\"id\":1,\"name\":\"thoreau\",\"hobbies\":[\"writing\",\"music\",\"tennis\"]}";
        String hobby = JsonPath.parse(jsonStr).read("$.hobbies[2]");
        assertThat(hobby).isEqualTo("tennis");

    }
}
