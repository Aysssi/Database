package com.example.jsonproductsshop.domain.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product extends BaseEntity{

    private String  name;
    private BigDecimal price;
    private User seller;
    private User buyer;
    private Set<Category> categories;

    public Product() {
    }
    @Column(name = "name", nullable = false)
    @Length(min = 3, message = "Wrong product name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(name = "price")
    @DecimalMin( value = "0")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "seller_id", referencedColumnName = "id")
    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }
    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "buyer_id", referencedColumnName = "id")
    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }
    @ManyToMany(targetEntity = Category.class)
    @JoinTable(
            name = "categories_products",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}

