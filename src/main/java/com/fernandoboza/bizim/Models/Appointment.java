package com.fernandoboza.bizim.Models;

public class Appointment {
    private String date;
    private Client client;
    private Employee employee;
    private Service service;

    public Appointment(String date, Client client, Employee employee, Service service) {
        this.date = date;
        this.client = client;
        this.employee = employee;
        this.service = service;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
