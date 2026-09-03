package com.bit.mango.salesmarketing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This is the "main" file - the one that actually starts your whole
 * backend when you run it. You will basically never touch this file
 * again once it's set up. Everything else (customers, orders, etc.)
 * lives in its own package/folder next to this one.
 */
@SpringBootApplication
public class SalesMarketingBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(SalesMarketingBackendApplication.class, args);
    }

}
