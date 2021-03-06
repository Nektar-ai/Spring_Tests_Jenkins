package fr.easit.easit;

import java.io.IOException;
import java.util.List;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import fr.easit.dto.ArticleDTO;
import fr.easit.models.Article;
import fr.easit.models.Client;
import fr.easit.models.User;
import fr.easit.repositories.ArticleRepository;
import fr.easit.repositories.UserRepository;

@SpringBootTest
class ApiTest {

	@Autowired
    ArticleRepository artRepo;

    @Autowired
    UserRepository userRepo;

    String user = "dbrewse0@gnu.org";
    String pas = "a";

	@Test
	public void testCompareJSONFromApiAndDatabase() throws JSONException {
		
		User userTest = userRepo.findUserByUsername(user).get();
		Client clientTest = userTest.getClient();
		
		// JSON de l'API
		HttpUriRequest request = new HttpGet("http://localhost:8080/api/articles?username="+user+"&password="+pas);
		CloseableHttpResponse response;
		String jsonApi = "";
		String jsonBdd = "[";
		try {
			response = HttpClientBuilder.create().build().execute(request);
			jsonApi = EntityUtils.toString(response.getEntity());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Récup data de la BDD
		List<Article> articles = artRepo.findAll();
		
		// Formatage du retour BDD en JSON
        for (Article article: articles) {
        	ArticleDTO article2 = new ArticleDTO(article, clientTest);
        	
            jsonBdd = jsonBdd + "{\"id\":" + article.getId()
            			+ ",\"name\":\"" + article.getName() + "\""
            			+ ",\"description\":\"" + article.getDescription() + "\""
            			+ ",\"price\":" + article2.getPrice() + "},";
        }
        
        jsonBdd = jsonBdd + "]";
        
        StringBuilder jsonBddFormat = new StringBuilder(jsonBdd);
        jsonBddFormat.deleteCharAt(jsonBdd.length()-2);
        jsonBdd = jsonBddFormat.toString();
        
		JSONAssert.assertEquals(jsonApi, jsonBdd, false);
	}
}
