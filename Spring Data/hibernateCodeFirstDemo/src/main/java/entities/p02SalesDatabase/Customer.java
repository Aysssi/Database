package entities.p02SalesDatabase;

import entities.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table (name = "customer")
public class Customer extends BaseEntity {
    private String name;
    private String email;
    private String creditCarNumber;
    private Set<Sale> sales;

    @OneToMany(mappedBy = "customer",targetEntity = Sale.class)
    public Set<Sale> getSales() {
        return sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }

    public Customer() {
    }
    @Column (name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column (name = "email")
    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }
    @Column (name = "credit_car_number")
    public String getCreditCarNumber() {
        return creditCarNumber;
    }

    public void setCreditCarNumber(String creditCarNumber) {
        this.creditCarNumber = creditCarNumber;
    }
}
