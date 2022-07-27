package com.example.clothesshop.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Suppliers")
public class SupplierEntity extends BaseEntity {
    private String name;
    private String address;
    private String phone_number;
    @OneToMany(
            mappedBy = "supplier",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ReceiptEntity> receipts = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public List<ReceiptEntity> getReceipts() {
        return receipts;
    }

    public void setReceipts(List<ReceiptEntity> receipts) {
        this.receipts = receipts;
    }
}
