package com.example.clothesshop.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class ReceiptDTO extends AbstractDTO<ReceiptDTO> {
    private SupplierDTO supplier;
    @JsonIgnore
    private List<ReceiptDetailDTO> receipt_detail = new ArrayList<>();

    public SupplierDTO getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierDTO supplier) {
        this.supplier = supplier;
    }

    public List<ReceiptDetailDTO> getReceipt_detail() {
        return receipt_detail;
    }

    public void setReceipt_detail(List<ReceiptDetailDTO> receipt_detail) {
        this.receipt_detail = receipt_detail;
    }
}
