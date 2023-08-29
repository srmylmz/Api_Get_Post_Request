package post_request;

import base_url.Base_Url;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C04_get04_HamcrestM_Map extends Base_Url {
    @Test
    public void test01() {
        /*
        Given https://reqres.in/api/unknown/3
        When User send a GET request to the URL
        Then HTTP Status Code should be 200
        And Response content type is “application/json; charset=utf-8”
        And Response body should be like;(Soft Assertion)
        {
        "data": {
            "id": 3,
            "name": "true red",
            "year": 2002,
            "color": "#BF1932",
            "pantone_value": "19-1664"
        },
        "support": {
            "url": "https://reqres.in/#support-heading",
            "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
        }
}*/
        spec.pathParams("first", "unknown", "second",3);

        Response response = given(spec).when().get("{first}/{second}");

        JsonPath jsonPath=response.jsonPath();
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(200,response.statusCode());
        softAssert.assertEquals("application/json; charset=utf-8",response.contentType());
        softAssert.assertEquals(3,jsonPath.getInt("data.id"));
        softAssert.assertEquals("true red",jsonPath.getString("data.name"));
        softAssert.assertEquals(2002,jsonPath.getInt("data.year"));
        softAssert.assertEquals("#BF1932",jsonPath.getString("data.color"));
        softAssert.assertEquals("19-1664",jsonPath.getString("data.pantone_value"));
        softAssert.assertEquals("https://reqres.in/#support-heading",jsonPath.getString("support.url"));
        softAssert.assertEquals("To keep ReqRes free, contributions towards server costs are appreciated!",
                jsonPath.getString("support.text"));

        softAssert.assertAll();

    }

    @Test
    public void test02() throws JsonProcessingException {
        spec.pathParams("first", "unknown", "second",3);

        Response response = given(spec).when().get("{first}/{second}");

        Map<String,Object> ınnerData=new HashMap<>();
        ınnerData.put("id",3);
        ınnerData.put("name","true red");
        ınnerData.put("year",2002);
        ınnerData.put("color","#BF1932");
        ınnerData.put("pantone_value","19-1664");
        System.out.println(ınnerData);

        Map<String,Object>expectedData=new HashMap<>(ınnerData);
        expectedData.put("url","https://reqres.in/#support-heading");
        expectedData.put("text","To keep ReqRes free, contributions towards server costs are appreciated!");
        System.out.println(expectedData);

        Response response1=given(spec).when().get("{first}/{second}");
        ObjectMapper objectMapper=new ObjectMapper();
        Map<String,Object> actualData=objectMapper.readValue(response1.asString(), HashMap.class);
        System.out.println(actualData);

        assertEquals(200,response1.statusCode());
        assertEquals(expectedData.get("id"),((Map)actualData.get("data")).get("id"));
        assertEquals(expectedData.get("name"),((Map)actualData.get("data")).get("name"));
        assertEquals(expectedData.get("year"),((Map)actualData.get("data")).get("year"));
        assertEquals(expectedData.get("color"),((Map)actualData.get("data")).get("color"));
        assertEquals(expectedData.get("pantone_value"),((Map)actualData.get("data")).get("pantone_value"));
        assertEquals(expectedData.get("url"),((Map)actualData.get("support")).get("url"));
        assertEquals(expectedData.get("text"),((Map)actualData.get("support")).get("text"));

    }
}
