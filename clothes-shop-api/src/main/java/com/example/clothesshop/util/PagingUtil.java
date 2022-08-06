package com.example.clothesshop.util;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class PagingUtil<T> {
    public Page ConvertListToPage(Pageable pageable, List<T> list){
        final int start = (int) pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), list.size());
        final Page<T> pages = new PageImpl<>(list.subList(start, end), pageable, list.size());
        return pages;
    }
}
