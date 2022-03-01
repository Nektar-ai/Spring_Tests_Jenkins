package fr.easit.dto;

import fr.easit.models.Article;
import fr.easit.models.Client;

import java.text.DecimalFormat;

public class ArticleDTO {

    public ArticleDTO() { }

    public ArticleDTO(Article article, Client client) {
        this.setName(article.getName());
        this.setDescription(article.getDescription());
        this.setPrice(article.getProductionPrice(), client.getContract().getPercentage());
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

    private Double price;
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price, Integer percentage) {
        Double afterClientContract = price + (price / 100) * percentage;
        Double afterVAT = afterClientContract + (afterClientContract / 100) * 20;
        this.price = Math.round(afterVAT*100.0)/100.0;
    }
}
