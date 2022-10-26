package com.example.clothesshop.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ReceiptDTO extends AbstractDTO<ReceiptDTO> {
    private Long supplier_id;
    private List<ReceiptDetailDTO> receipt_detail = new ArrayList<>();
}
