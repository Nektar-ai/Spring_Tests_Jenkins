package fr.easit.easit;

import fr.easit.EasitApplication;
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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

//@DataJpaTest
//@SpringBootTest(classes = FrontTest.class)

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment =  SpringBootTest.WebEnvironment.MOCK, classes = EasitApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application.properties")
public class FrontTest {

    @Autowired
    ArticleRepository artRepo;

    @Autowired
    UserRepository userRepo;

//    @Autowired
//    static
//    ContractRepository contRepo;

    String user = "dbrewse0@gnu.org";
    String pas = "a";

//    static List<Contract> contracts = contRepo.findAll();
//
//    static Stream<Arguments> chargerListeUtilisateurs() throws Throwable
//    {
//        List<Contract> contratList = contracts;
//        List<Client> clientList = new ArrayList<>();
//
//        for (Contract c : contratList)
//        {
//            clientList.add(c.getClients().get(0));
//        }
//
//        return Stream.of(
//                Arguments.of(clientList.get(0)),
//                Arguments.of(clientList.get(1)),
//                Arguments.of(clientList.get(2)),
//                Arguments.of(clientList.get(3)),
//                Arguments.of(clientList.get(4))
//        );
//    }

    @Test
    public void TestAntoine() {
        List<Article> articles = artRepo.findAll();
        for (Article article:
                articles) {
            System.out.println(article.getName());
        }
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

    @ParameterizedTest()
    @MethodSource("chargerListeUtilisateurs")
    public void verifyArticlesPriceWithClientMargin(Client c)
    {
        // TODO
    }

    @Test
    public void verifyArticleListIsPresent() throws IOException
    {

        Integer tva = 20;

        File currentDirFile = new File("");
        String path = currentDirFile.getAbsolutePath();
//        System.out.println("Path : " + path);
//        String currentDir = path.substring(0, path.length() - currentDirFile.getCanonicalPath().length());

        System.setProperty("webdriver.chrome.driver",path + "\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "http://localhost:8080";
        driver.get(url);

        WebElement login = driver.findElement(By.name("username"));
        login.sendKeys(user);

        WebElement pass = driver.findElement(By.name("password"));
        pass.sendKeys(pas);

//        WebElement button = driver.findElement(By.name("password"));
        WebElement button = driver.findElement(By.id("login"));
        button.click();

        List<WebElement> artList = driver.findElements(By.className("articles-row"));
        System.out.println("List size : " + artList.size());
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
//            if (artPriceCli != )
            list.put(e.findElement(By.className("id")).getText(), e.findElement(By.className("price")).getText());
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}
