package com.example.clothesshop.controller.web;

import com.example.clothesshop.constant.SystemConstant;
import com.example.clothesshop.dto.CollectionDTO;
import com.example.clothesshop.service.ICollectionService;
import com.example.clothesshop.utils.PagingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController(value = "apiCollectionOfWeb")
@CrossOrigin
@RequestMapping(path = "api/collection")
public class CollectionController {
    @Autowired
    ICollectionService collectionService;
    @GetMapping()
    public ResponseEntity<Map<String, Object>> getAllCollection(){
        Map<String, Object> response = new HashMap<>();
        Sort sortable = PagingUtils.sortByCreatedDate("asc");
        List<CollectionDTO> listCollection = collectionService.findAll(SystemConstant.ACTIVE_STATUS, sortable);
        response.put("collections", listCollection);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/detail/{id}")
    public CollectionDTO getCollectionById(@PathVariable("id") long id) {
        return collectionService.findById(id);
    }
}
