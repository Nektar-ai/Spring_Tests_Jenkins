package fr.easit.easit;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import fr.easit.controllers.ApiController;
import fr.easit.controllers.HomeController;
import fr.easit.dto.ArticleDTO;
import fr.easit.models.User;
import fr.easit.repositories.ArticleRepository;
import fr.easit.repositories.UserRepository;
import fr.easit.services.ArticleServicImpl;
import fr.easit.services.ArticleService;
import junit.framework.Assert;

@SpringBootTest
class EasitApplicationTests {

/*	@Test
	void contextLoads() {
	}*/
	
//	@TestConfiguration
//	static class ArticleServiceTestConfiguration {
//		@Bean
//		public ArticleService articleService() {
//			return new ArticleServicImpl();
//		}
//	}
//	
//	@Autowired
//	ArticleService articlesService;
//	
//	@MockBean
//	ArticleRepository articlesRepo;

	@Test
	public void testCompareJSONFromApiAndDatabase() throws JSONException {
		
		//JSON de l'API
		HttpUriRequest request = new HttpGet("http://localhost:8080/api/articles?username=dbrewse0@gnu.org&password=a");
		CloseableHttpResponse response;
		String jsonApi = "";
		try {
			response = HttpClientBuilder.create().build().execute(request);
			jsonApi = EntityUtils.toString(response.getEntity());
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//HomeController repoJson = new HomeController();
		
		
		//System.out.println(articlesService.getArticles());		
		//System.out.println(apiJson.getArticlesJson("dbrewse0@gnu.org", "a"));
		//JSONArray jsonArray = listArticlesApi.toJSONArray();

		//JSONAssert.assertEquals(jsonArray, jsonArray, false);
		JSONAssert.assertEquals(jsonApi, jsonApi, false);
	}
}
