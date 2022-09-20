package com.example.clothesshop.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SupplierDTO extends AbstractDTO<SupplierDTO> {
    private String name;
    private String address;
    private String phone_number;
    private Integer status;
//    @JsonIgnore
    private List<ReceiptDTO> receipts = new ArrayList<>();
}
