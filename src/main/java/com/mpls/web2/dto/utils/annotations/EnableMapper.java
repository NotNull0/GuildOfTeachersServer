package com.mpls.web2.dto.utils.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface EnableMapper {

    public String dto();

    public String model();
}
