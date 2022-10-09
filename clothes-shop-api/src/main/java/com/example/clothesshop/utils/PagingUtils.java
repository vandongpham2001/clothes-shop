package com.example.clothesshop.utils;

import org.springframework.data.domain.Sort;

public class PagingUtils<T> {
//    public Page ConvertListToPage(Pageable pageable, List<T> list){
//        final int start = (int) pageable.getOffset();
//        final int end = Math.min((start + pageable.getPageSize()), list.size());
//        final Page<T> pages = new PageImpl<>(list.subList(start, end), pageable, list.size());
//        return pages;
//    }

    public static Sort sort(String sort){
        if (sort.equals("asc")) {
            return Sort.by(Sort.Direction.ASC, "createdDate");
        }
        return Sort.by(Sort.Direction.DESC, "createdDate");
    }
}
