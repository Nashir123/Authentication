package com.tcs.hotelMgmt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="hotel")
public class HotelMgmtEntity {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, max = 20, message = "Name must be between 3 and 20 characters")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Phone cannot be blank")
    @Size(min = 10, max = 10, message = "Phone number should be exactly 10 digits")
    @Column(name = "phone")
    private String phone;

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "HotelMgmtEntity [id=" + id + ", name=" + name + ", phone=" + phone + "]";
    }
}
