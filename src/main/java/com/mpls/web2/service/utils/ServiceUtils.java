package com.mpls.web2.service.utils;

public class ServiceUtils {

    public static String getPassFromPhone(String phone){
        return phone
                .replace("+", "")
                .replace("-", "")
                .replace(" ", "")
                .replace("(", "")
                .replace(")", "");
    }

}
