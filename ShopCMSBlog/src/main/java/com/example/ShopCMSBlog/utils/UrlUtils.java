package com.example.ShopCMSBlog.utils;

import java.util.Arrays;
import java.util.List;

public class UrlUtils {
    public static final String API_VERSION = "/v1";
    public static final String API_PREFIX = "/api";
    public static final String BASE_URL = API_PREFIX + API_VERSION;


    // nameService_nameEntity or nameFunction_(prefix Url) example. MAIN_SERVICE_URL
    public static final String Supplier_URL = BASE_URL + "/suppliers";
    public static final String USER_URL = BASE_URL + "/users";
    public static final String Role_URL = BASE_URL + "/roles";
    public static final String Product_URL = BASE_URL + "/products";
    public static final String ProductReview_URL = BASE_URL + "/productreviews";
    public static final String Posts_URL = BASE_URL + "/posts";
    public static final String Order_URL = BASE_URL + "/orders";
    public static final String Customer_URL = BASE_URL + "/customers";
    public static final String Comment_URL = BASE_URL + "/comments";
    public static final String Category_URL = BASE_URL + "/categories";
    public static final String Cart_URL = BASE_URL + "/carts";
    public static final String CartItem_URL = BASE_URL + "/cartitems";
    public static final String BlogLike_URL = BASE_URL + "/blogLikes";
    public static final String AUTH_URL = BASE_URL + "/auth";
    public static final List<String> PUBLIC_URLS = Arrays.asList(
            AUTH_URL+ "/register",
            AUTH_URL+ "/login",
            "/error"
    );
}
