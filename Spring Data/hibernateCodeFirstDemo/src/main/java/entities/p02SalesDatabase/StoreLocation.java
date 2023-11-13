package entities.p02SalesDatabase;

import entities.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "store_location")
public class StoreLocation extends BaseEntity {

    private String locationName;

    //Set<Sale>sales


    public StoreLocation() {
    }

    @Column(name = "location_name")
    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}
