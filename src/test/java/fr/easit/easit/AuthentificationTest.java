package fr.easit.easit;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthentificationTest {

    @Test
    public void givenUserDoesNotExist_whenUserLogin_thenErrorCodeReceive()
    {
/*
        String usr = "blabla";
        HttpUriRequest request  new HttpGet("")
*/
    }

    @Test
    public void anonymousTriesToAccessUnauthorizedResource() throws IOException
    {
        HttpUriRequest request = new HttpGet("http://localhost:8080/api/articles");
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
        assertThat(
                response.getStatusLine().getStatusCode(),
                equalTo(HttpStatus.UNAUTHORIZED.value())
        );
    }
}
