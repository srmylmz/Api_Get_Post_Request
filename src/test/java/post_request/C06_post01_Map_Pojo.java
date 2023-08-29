package post_request;

import base_url.Base_Url;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.ResponsePojo;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static utilities.ObjectMapperUtils.convertJsonToJava;

public class C06_post01_Map_Pojo extends Base_Url {
    @Test
    public void test01() {
        /*
        Given
            1) https://reqres.in/api/users
            2) {
                "name": "morpheus",
                "job": "leader"
                }
        When I send POST Request to the Url
        Then Status code is 201
            And response body should be like {
                                                "name": "morpheus",
                                                "job": "leader",
                                                "id": "496",
                                                "createdAt": "2022-10-04T15:18:56.372Z"
                                              } */

        spec.pathParams("first", "users");

        Map<String,Object> payLoad=new HashMap<>();
        payLoad.put("name","morpheus");
        payLoad.put("job","leader");

        Response response = given(spec).body(payLoad).when().post("{first}");

        Map<String,Object> actualData=response.as(HashMap.class);
        System.out.println(actualData);
        //{createdAt=2023-08-26T16:38:27.438Z, name=morpheus, id=352, job=leader}

        assertEquals(201,response.statusCode());
        assertEquals(payLoad.get("name"),actualData.get("name"));
        assertEquals(payLoad.get("job"),actualData.get("job"));

    }

    @Test
    public void test02() throws JsonProcessingException {
        spec.pathParams("first", "users");

        ResponsePojo payLoad=new ResponsePojo("morpheus","leader");

        Response response = given(spec).body(payLoad).when().post("{first}");

        ResponsePojo actualData=convertJsonToJava(response.asString(),ResponsePojo.class);

        assertEquals(201,response.statusCode());
        assertEquals(payLoad.getName(),actualData.getName());
        assertEquals(payLoad.getJob(),actualData.getJob());

    }
}
