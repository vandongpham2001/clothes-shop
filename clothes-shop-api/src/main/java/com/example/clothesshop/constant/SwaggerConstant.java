package com.example.clothesshop.constant;


import com.example.clothesshop.utils.SecurityUtils;

public class SwaggerConstant {
//    public static final String BASE_URL = SecurityUtils.makeUrl();
    public static final String BASE_URL = "http://localhost:8080";
    public static final String SECURITY_REFERENCE = "Token Access";
    public static final String AUTHORIZATION_DESCRIPTION = "Full API Permission";
    public static final String AUTHORIZATION_SCOPE = "Unlimited";
    public static final String CONTACT_EMAIL = "email@outlook.com";
    public static final String CONTACT_URL = "https://www.clothes-shop.com";
    public static final String LOGIN_URL = BASE_URL + "/login";
    public static final String CONTACT_NAME = "Clothes Shop API Support";
    public static final String API_TITLE = "Clothes Shop Management open API";
    public static final String API_DESCRIPTION = "In the description property, in addition to describing your " +
            "overall API, you might want to provide some basic instructions to users on how to use Swagger UI. " +
            "If there's a test account they should use, you can provide the information they need in this space. " +
            "In the description property, in addition to describing your " +
            "overall API, you might want to provide some basic instructions to users on how to use Swagger UI. " +
            "If there's a test account they should use, you can provide the information they need in this space. " +
            "</br></br><h3> **Note**: Some API requires an `API KEY`, please log into your account to access your key <a target='_blank' href=" + LOGIN_URL + ">here</a> </h3>.";
    public static final String TERM_OF_SERVICE = "Your term of service will go here";
    public static final String API_VERSION = "1.1";
    public static final String LICENSE = "Apache License 2.1.0";
    public static final String LICENSE_URL = "https://www.apache.org/licenses/LICENSE-2.0";
    public static final String SECURE_PATH = "/*/.*";
    public static final String API_TAG = "Clothes Shop Service";
    public static final String AUTHORIZATION = "Authorization";
}
//static {
//        HttpServletRequest request = null; // get the request object from somewhere, e.g. by autowiring
//        String baseUrl = request.getRequestURL().toString();
//        String uri = request.getRequestURI();
//        String contextPath = request.getContextPath();
//        baseUrl = baseUrl.replace(uri, "");
//        baseUrl += contextPath;
//        BASE_URL = baseUrl;
//    }