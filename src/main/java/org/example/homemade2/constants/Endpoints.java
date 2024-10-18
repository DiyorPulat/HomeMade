package org.example.homemade2.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Endpoints {
    public static final String HOME_CREATE = "home";
    public static final String HOME_UPDATE = "home/{id}";
    public static final String HOME_DELETE = "home/{id}";
    public static final String HOME_GET = "home/{id}";
    public static final String HOME_DETAILS_CREATE = "home/details";
    public static final String HOME_DETAILS_UPDATE = "home/details/{id}";
    public static final String HOME_DETAILS_DELETE = "home/details/{id}";
    public static final String HOME_DETAILS_GET = "home/details/{id}";
    public static final String FAVOURITE_PAYMENT_CREATE = "favourite/payment";
    public static final String FAVOURITE_PAYMENT_UPDATE = "favourite/payment/{id}";
    public static final String FAVOURITE_PAYMENT_DELETE = "favourite/payment/{id}";
    public static final String FAVOURITE_PAYMENT_GET = "favourite/payment/{id}";
}
