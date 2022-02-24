package fr.easit.dao;

import fr.easit.models.Article;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class ArticleDAO {

    public ArticleDAO(){}
    public ArticleDAO(Article article){
        this.setName(article.getName());
        this.setDescription(article.getDescription());
        this.setProductionPrice(article.getProductionPrice());
    }

    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    private String description;
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    private Double productionPrice;
    public Double getProductionPrice() {
        return productionPrice;
    }
    public void setProductionPrice(Double productionPrice) {
        this.productionPrice = productionPrice;
        DecimalFormat df = new DecimalFormat("#.##");
        Double afterVAT = productionPrice * (1 + 0.2);
        this.setFinalPrice(df.format(afterVAT).toString());

    }

    private String finalPrice;
    public String getFinalPrice() {
        return finalPrice;
    }
    private void setFinalPrice(String finalPrice) {
        this.finalPrice = finalPrice;
    }
}
