package com.fernandoboza.bizim.Models;

import java.util.List;

public class Category {
    private String name;
    private List<Service> services;

    public Category(String name, List<Service> services) {
        this.name = name;
        this.services = services;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }
}
