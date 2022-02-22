package fr.easit.easit.models;

import javax.persistence.*;

@Entity
@Table(name = "articles")
public class Articles {
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

    @Lob
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
}