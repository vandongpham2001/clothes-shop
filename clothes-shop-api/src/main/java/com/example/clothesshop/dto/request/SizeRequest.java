package com.example.clothesshop.dto.request;

import lombok.Data;

@Data
public class SizeRequest extends AbstractRequest<SizeRequest> {
    private String size;
    private Integer status;
}
