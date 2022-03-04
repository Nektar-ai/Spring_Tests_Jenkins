package fr.easit.easit;

import fr.easit.EasitApplication;
import fr.easit.dto.ArticleDTO;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


@SpringBootTest(classes = EasitApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FrontTest {

    String user = "dbrewse0@gnu.org";
    String pas = "a";

    static Stream<Arguments> chargerListeArticlesPrixMarge()
    {
        return Stream.of(
                Arguments.of(10, 5, 12.60),
                Arguments.of(10, 10, 13.20),
                Arguments.of(10, 15, 13.80),
                Arguments.of(10, 20, 14.40),
                Arguments.of(10, 25, 15)
        );
    }

    @Test
    public void pageWithArticlesInJsonIsPresent() throws IOException
    {
        String jsonMimeType = "application/json";
        HttpUriRequest request = new HttpGet("/api/articles?username="+user+"&password="+pas);
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
        String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
        if (response.getStatusLine().getStatusCode() == HttpStatus.UNAUTHORIZED.value())
        {
            fail("Echec d'authentification");
        } else {
            assertEquals(jsonMimeType, mimeType);
        }
    }

    @Test
    public void siteIsUpAndLoginPageIsShowed() throws IOException
    {
        HttpUriRequest request = new HttpGet("http://localhost:8080/login");
        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        assertThat(
                response.getStatusLine().getStatusCode(),
                equalTo(HttpStatus.OK.value())
        );
    }

    @ParameterizedTest(name = "Test Calcul prix {index} : Prix article HT = {0}, marge = {1}%, resultat attendu = {2}")
    @MethodSource("chargerListeArticlesPrixMarge")
    public void verifyArticlesPriceWithClientMargin(double prix, int marge, double res)
    {
        ArticleDTO art = new ArticleDTO();
        art.setPrice(prix, marge);

        Assertions.assertEquals(art.getPrice(), res);
    }

    @Test
    public void verifyArticleListIsPresent()
    {
        File currentDirFile = new File("");
        String path = currentDirFile.getAbsolutePath();

        System.setProperty("webdriver.chrome.driver",path + "\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "http://localhost:8080";
        driver.get(url);

        WebElement login = driver.findElement(By.name("username"));
        login.sendKeys(user);

        WebElement pass = driver.findElement(By.name("password"));
        pass.sendKeys(pas);

        WebElement button = driver.findElement(By.id("login"));
        button.click();

        List<WebElement> artList = driver.findElements(By.className("articles-row"));
        System.out.println("List size : " + artList.size());

        if (artList.size() == 0)
            fail("Liste d'articles non présente");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}

