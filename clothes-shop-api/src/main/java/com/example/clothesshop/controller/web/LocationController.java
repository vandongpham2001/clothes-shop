package com.example.clothesshop.controller.web;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "locationApiOfWeb")
@CrossOrigin
@RequestMapping(path = "api/location")
public class LocationController {
}
