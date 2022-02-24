package fr.easit.models;

import org.springframework.web.bind.annotation.Mapping;

import javax.persistence.*;

@Entity
@Table(name = "articles")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    @Column(length = 50)
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(columnDefinition="TEXT")
    private String description;
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name="production_price")
    private Double productionPrice;
    public Double getProductionPrice() {
        return productionPrice;
    }
    public void setProductionPrice(Double productionPrice) {
        this.productionPrice = productionPrice;
    }

    @Override
    public String toString(){
        return "Article : {name:"+getName()+", description:"+getDescription()+", price:"+getProductionPrice()+"}";
    }
}