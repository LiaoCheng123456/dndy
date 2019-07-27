package com.sdm.service.cloud.interceptor;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthJwt {
    String[] value() default {};

   // String Authorization();

    String[] roles() default {};
}
