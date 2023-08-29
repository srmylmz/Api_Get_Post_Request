package base_url;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;


public class Base_Url {
    protected RequestSpecification spec;

    @Before
    public void setUp() throws Exception {
        spec=new RequestSpecBuilder().
                setContentType(ContentType.JSON).
                setBaseUri("https://reqres.in/api").
                build();
    }
}
