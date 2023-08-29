package get_post_request;

import base_url.Base_Url;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class C03_get03_HamcrestM_JsonPath extends Base_Url {
    @Test
    public void test01() {
        /*
       Given https://reqres.in/api/users/2
       When User send GET Request to the URL
       Then HTTP Status Code should be 200
       And Response format should be “application/json”
       And “email” is “janet.weaver@reqres.in”,
       And “first_name” is "Janet"
       And “last_name” is "Weaver"
       And "text" is "To keep ReqRes free, contributions towards server costs are appreciated!"
    */

        spec.pathParams("first", "users", "second", 2);

        Response response = given(spec).when().get("{first}/{second}");
        response.
                then().
                statusCode(200).
                contentType(ContentType.JSON).
                header("Content-Type", containsString("application/json")).
                body("data.email", equalTo("janet.weaver@reqres.in")).
                body("data.first_name", equalTo("Janet")).
                body("data.last_name", equalTo("Weaver")).
                body("support.text", equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));


    }

    @Test
    public void test02() {
        spec.pathParams("first", "users", "second", 2);

        Response response = given(spec).when().get("{first}/{second}");
        JsonPath jsonPath = response.jsonPath();

        assertEquals(200, response.statusCode());
        assertTrue(response.contentType().contains("application/json"));
        assertEquals("janet.weaver@reqres.in", jsonPath.getString("data.email"));
        assertEquals("Janet", jsonPath.getString("data.first_name"));
        assertEquals("Weaver", jsonPath.getString("data.last_name"));
        assertEquals("To keep ReqRes free, contributions towards server costs are appreciated!"
                , jsonPath.getString("support.text"));
    }

}
