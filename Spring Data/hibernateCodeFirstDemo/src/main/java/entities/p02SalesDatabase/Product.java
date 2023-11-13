package entities.p02SalesDatabase;

import entities.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table (name = "products")
public class Product  extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name ="quantity")
    private Double quantity;
    @Column (name = "price")
    private BigDecimal price;
  //  private Set<Sale> sales;

    public Product() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
