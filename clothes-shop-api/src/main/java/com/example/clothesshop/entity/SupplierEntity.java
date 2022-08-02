package com.example.clothesshop.entity;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Suppliers")
@Data
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
}
