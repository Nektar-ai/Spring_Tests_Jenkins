package fr.easit.easit;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.HttpStatus;
import java.io.IOException;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthentificationTest {

    static String username = "dbrewse0@gnu.org";
    static String pass = "a";

    static Stream<Arguments> chargerListeMessageErreurAuthent()
    {
        return Stream.of(
                Arguments.of("", "", 401, "Field username or password not filled"),
                Arguments.of(username, "b", 401, "Bad password"),
                Arguments.of("dbrewse0@gnu.or", pass, 401, "User not found"),
                Arguments.of(null, null, 401, "User not found")
        );
    }

    @Test
    public void givenUserDoesExist_whenUserLogin_thenCode200IsReceive() throws IOException
    {
        HttpUriRequest request = new HttpGet("http://localhost:8080/api/articles?username="+username+"&password="+pass);
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
        assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.OK.value());
    }

    @ParameterizedTest(name = "Test d'authentification N°{index} : user = {0}, pass = {1}, status code = {2}, message attendu = {3}")
    @MethodSource("chargerListeMessageErreurAuthent")
    public void mauvaiseAuthentificationTest(String usr, String pass, int code, String msg) throws IOException, JSONException
    {
        HttpUriRequest request = new HttpGet("http://localhost:8080/api/articles?username="+usr+"&password="+pass);
        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        JSONObject json = new JSONObject(EntityUtils.toString(response.getEntity()));
        System.out.println(json);

       if (response.getStatusLine().getStatusCode() == code)
       {
           assertThat(
                   json.getString("message"),
                   equalTo(msg));
       } else {
           fail("Unexpected Status Code");
       }
    }

    @Test
    public void anonymousTriesToAccessUnauthorizedResource() throws IOException
    {
        HttpUriRequest request = new HttpGet("http://localhost:8080/api/articles");
        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        String json = EntityUtils.toString(response.getEntity());
        System.out.println(json);

        assertThat(
                response.getStatusLine().getStatusCode(),
                equalTo(HttpStatus.UNAUTHORIZED.value()));
    }
}
