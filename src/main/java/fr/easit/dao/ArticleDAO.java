package fr.easit.dao;

import fr.easit.models.Article;
import fr.easit.models.Client;
import fr.easit.models.Contract;
import fr.easit.models.User;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.logging.Logger;

public class ArticleDAO {


    Logger logger = Logger.getLogger("DAO");
    public ArticleDAO(){}
    public ArticleDAO(Article article, Client client){
        this.setName(article.getName());
        this.setDescription(article.getDescription());

        this.setUserPercent(client.getContract().getPercentage());
        this.setProductionPrice(article.getProductionPrice());



    }

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


        Double afterClientContract = productionPrice + (productionPrice / 100) * getUserPercent();
        Double afterVAT = afterClientContract + (afterClientContract / 100) * 20;

        logger.info(getName() + " TVA: "+afterVAT);
        logger.info(getName() + " aCC: "+getUserPercent());
        this.setFinalPrice(df.format(afterVAT).toString());
    }

    private Integer userPercent;
    public Integer getUserPercent() {
        return userPercent;
    }
    public void setUserPercent(Integer userPercent) {
        this.userPercent = userPercent;
    }

    private String finalPrice;
    public String getFinalPrice() {
        return finalPrice;
    }
    private void setFinalPrice(String finalPrice) {
        this.finalPrice = finalPrice;
    }
}
