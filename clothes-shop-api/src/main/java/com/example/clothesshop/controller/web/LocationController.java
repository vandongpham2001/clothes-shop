package com.example.clothesshop.controller.web;

import com.example.clothesshop.dto.response.*;
import com.example.clothesshop.service.IDistrictService;
import com.example.clothesshop.service.IProvinceService;
import com.example.clothesshop.service.IWardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "locationApiOfWeb")
@CrossOrigin
@RequestMapping(path = "api/location")
public class LocationController {
    @Autowired
    IProvinceService provinceService;
    @Autowired
    IDistrictService districtService;
    @Autowired
    IWardService wardService;
    @GetMapping("province")
    public List<ProvinceDetailResponse> getAllProvince(){
        return provinceService.findAll();
    }

    @GetMapping("province/{id}")
    public ProvinceResponse getProvinceById(@PathVariable("id") String id){
        return provinceService.findById(id);
    }

    @GetMapping("province/detail/{id}")
    public ProvinceDetailResponse getProvinceDetailById(@PathVariable("id") String id){
        return provinceService.findDetailById(id);
    }

    @GetMapping("province/{province_id}/district")
    public List<DistrictResponse> getAllDistrictByProvinceId(@PathVariable("province_id") String province_id){
        return districtService.findByProvinceId(province_id);
    }
    @GetMapping("district/{id}")
    public DistrictResponse getDistrictById(@PathVariable("id") String id){
        return districtService.findById(id);
    }

    @GetMapping("district/detail/{id}")
    public DistrictDetailResponse getDistrictDetailById(@PathVariable("id") String id){
        return districtService.findDetailById(id);
    }

    @GetMapping("district/{district_id}/ward")
    public List<WardResponse> getAllWardByDistrictId(@PathVariable("district_id") String district_id){
        return wardService.findByDistrictId(district_id);
    }
    @GetMapping("ward/{id}")
    public WardResponse getWardById(@PathVariable("id") String id){
        return wardService.findById(id);
    }

    @GetMapping("ward/detail/{id}")
    public WardDetailResponse getWardDetailById(@PathVariable("id") String id){
        return wardService.findDetailById(id);
    }

}
