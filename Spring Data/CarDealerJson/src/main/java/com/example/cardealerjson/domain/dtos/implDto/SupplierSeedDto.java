package com.example.cardealerjson.domain.dtos.implDto;

import com.google.gson.annotations.Expose;

public class SupplierSeedDto {
    @Expose
    private String name;
    @Expose
    private boolean isImporter;

    public SupplierSeedDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getImporter() {
        return isImporter;
    }

    public void setImporter(Boolean importer) {
        isImporter = importer;
    }
}
