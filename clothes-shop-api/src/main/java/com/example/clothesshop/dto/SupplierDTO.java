package com.example.clothesshop.dto;

import java.util.ArrayList;
import java.util.List;


public class SupplierDTO extends AbstractDTO<SupplierDTO> {
    private String name;
    private String address;
    private String phone_number;
    private List<ReceiptDTO> receipts = new ArrayList<>();

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

    public List<ReceiptDTO> getReceipts() {
        return receipts;
    }

    public void setReceipts(List<ReceiptDTO> receipts) {
        this.receipts = receipts;
    }
}
