package com.example.clothesshop.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Receipts")
public class ReceiptEntity extends BaseEntity{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    private SupplierEntity supplier;
    @OneToMany(
            mappedBy = "receipt",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ReceiptDetailEntity> receipt_detail = new ArrayList<>();

    public SupplierEntity getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierEntity supplier) {
        this.supplier = supplier;
    }

    public List<ReceiptDetailEntity> getReceipt_detail() {
        return receipt_detail;
    }

    public void setReceipt_detail(List<ReceiptDetailEntity> receipt_detail) {
        this.receipt_detail = receipt_detail;
    }
}
