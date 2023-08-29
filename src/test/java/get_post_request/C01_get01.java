package get_post_request;

import base_url.Base_Url;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class C01_get01 extends Base_Url {
    @Test
    public void test01() {
        /*
        Given https://reqres.in/api/users/3
        When User sends a GET Request to the url
        Then HTTP Status Code should be 200
        And Content Type should be JSON
        And Status Line should be HTTP/1.1 200 OK
     */

      spec.pathParams("first","users","second",3);
      Response response=given(spec).when().get("{first}/{second}");

      response.
              then().
              statusCode(200).
              contentType(ContentType.JSON).
              statusLine("HTTP/1.1 200 OK");
    }
    @Test
    public void test02() {
        spec.pathParams("first","users","second",3);

        Response response=given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        assertEquals(200,response.statusCode());
        assertTrue(response.contentType().contains("json"));
        assertEquals("HTTP/1.1 200 OK",response.statusLine());


    }
}
