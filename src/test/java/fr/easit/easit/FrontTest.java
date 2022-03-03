package fr.easit.easit;

import fr.easit.models.Article;
import fr.easit.models.User;
import fr.easit.repositories.ArticleRepository;
import fr.easit.repositories.UserRepository;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

@DataJpaTest
//@SpringBootTest(classes = FrontTest.class)
public class FrontTest {

    @Autowired
    ArticleRepository artRepo;

    @Autowired
    UserRepository userRepo;

    String user = "dbrewse0@gnu.org";
    String pas = "a";

    @Test
    public void pageWithArticlesInJsonIsPresent() throws IOException
    {
        String user = "";
        String pass = "a";
        String jsonMimeType = "application/json";
        HttpUriRequest request = new HttpGet("http://localhost:8080/api/articles");
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
        String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
        assertEquals(jsonMimeType, mimeType);
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

    @Test
    public void verifyArticlesPriceWithClientMargin()
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
