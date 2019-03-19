package com.skyforce.SkyForceWebService.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SHOP_ID")
    private Shop shop;

    @Column(name = "ARRV_TIME", nullable = false)
    private Date arrivalTime;

    public Reservation() {
    }

    public Reservation(Long id, Date arrivalTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;

    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", arrivalTime='" + arrivalTime + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}