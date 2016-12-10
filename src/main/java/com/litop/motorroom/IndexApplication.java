package com.litop.motorroom;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

 /**
 * Created by litop on 2016/7/18.
 */

@SpringBootApplication


public class IndexApplication extends SpringBootServletInitializer{

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(IndexApplication.class);
    }


	public static void main(String[] args) throws InterruptedException {

		SpringApplication.run(IndexApplication.class, args);

	}

}
