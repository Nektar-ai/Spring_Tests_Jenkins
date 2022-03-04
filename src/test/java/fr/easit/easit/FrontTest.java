package fr.easit.easit;

import fr.easit.EasitApplication;
import fr.easit.dto.ArticleDTO;
import fr.easit.models.Article;
import fr.easit.models.Client;
import fr.easit.models.Contract;
import fr.easit.models.User;
import fr.easit.repositories.ArticleRepository;
import fr.easit.repositories.ContractRepository;
import fr.easit.repositories.UserRepository;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.internal.runners.statements.Fail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@SpringBootTest(classes = EasitApplication.class)
public class FrontTest {

    @Autowired
    private ArticleRepository artRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ContractRepository contRepo;

    String user = "dbrewse0@gnu.org";
    String pas = "a";

    public List<Contract> getContracts()
    {
        return contRepo.findAll();
    }

    static Stream<Arguments> chargerListeArticlesPrixMarge() throws Throwable
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
        HttpUriRequest request = new HttpGet("http://localhost:8080/api/articles?username="+user+"&password="+pas);
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
    public void verifyArticleListIsPresent() throws IOException
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

/*
    Map<String, String> list = new HashMap<String, String>();

    User usr = userRepo.findUserByUsername(user).get();
    Integer per = usr.getClient().getContract().getPercentage();

        for (WebElement e : artList)
                {
                Article art = artRepo.getById(Integer.valueOf(e.findElement(By.className("id")).getText()));
                Double artPrice = art.getProductionPrice();
                Double artPriceCli = artPrice * (1+(per/100)) * (1+(tva/100));
                System.out.println("WebPrice : " + e.findElement(By.className("price")).getText());
                System.out.println("BDDPrice : " + artPriceCli);
                list.put(e.findElement(By.className("id")).getText(), e.findElement(By.className("price")).getText());
                }
*/
