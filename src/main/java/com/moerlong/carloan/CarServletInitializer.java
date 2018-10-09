package com.moerlong.carloan;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * fund Web程序启动类
 *
 * @author hwl
 * @date 2017-05-21 9:43
 */
public class CarServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(CarLoanApplication.class);
    }

}
