package get_post_request;

import base_url.Base_Url;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C05_get05_groovyLanguage extends Base_Url {
    @Test
    public void test01() {
        /*
       Given https://reqres.in/api/unknown/
       When I send GET Request to the URL
       Then
            1)Status code is 200
            2)Print all pantone_values
              (Tüm pantone_value değerlerini yazdırınız)
            3)Print all ids greater than 3 on the console
              (3'ten büyük id'leri yazdırınız)
              Assert that there are 3 ids greater than 3
              (3'ten büyük 3 adet id olduğunu doğrulayınız)
            4)Print all names whose ids are less than 3 on the console
              (id'si 3'ten küçük isimleri yazdırınız)
              Assert that the number of names whose ids are less than 3 is 2
              (id'si 3'ten küçük 2 isim olduğunu doğrulayınız)*/

        spec.pathParams("first", "unknown");
        Response response = given(spec).when().get("{first}");

        assertEquals( 200,response.statusCode());

        List<Integer> pantone=response.jsonPath().getList("data.findAll{it}.pantone_values");
        System.out.println(pantone);

        List<Integer> idList=response.jsonPath().getList("data.findAll{it.id>3}.id");
        System.out.println(idList);//[4, 5, 6]

        assertEquals(3,idList.size());

        List<Integer> idList2=response.jsonPath().getList("data.findAll{it.id<3}.id");
        System.out.println(idList2);//[1,2]

        assertEquals(2,idList2.size());
    }

}
