package get_post_request;

import base_url.Base_Url;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C02_get02 extends Base_Url {
    @Test
    public void tes01() {
   /*
        Given https://reqres.in/api/users/23
        When User send a GET Request to the url
        Then HTTP Status code should be 404
        And Status Line should be HTTP/1.1 404 Not Found
        And Server is "cloudflare"
        And Response body should be empty
     */
        spec.pathParams("first","users","second",23);

        Response response=given(spec).when().get("{first}/{second}");
        response.
                then().
                statusCode(404).
                statusLine("HTTP/1.1 404 Not Found").
                header("Server",equalTo("cloudflare")).
                body(Matchers.hasItems());//body should be empty
    }
}
