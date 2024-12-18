package model;

import java.util.Objects;

public class Product {
    private String name;
    private String uuid;

    public Product() {

    }

    public Product(String name, String uuid) {
        this.name = name;
        this.uuid = uuid;
    }

    public String getUUID() {
        return uuid;
    }

    public void setUUID(String tags) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
