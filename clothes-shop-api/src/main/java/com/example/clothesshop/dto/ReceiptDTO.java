package com.example.clothesshop.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ReceiptDTO extends AbstractDTO<ReceiptDTO> {
    private SupplierDTO supplier;
    @JsonIgnore
    private List<ReceiptDetailDTO> receipt_detail = new ArrayList<>();
}
