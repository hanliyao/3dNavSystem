package com.litop.motorroom.sys;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class ErrorPageConfig {

    /**
     * 自定义内嵌服务器配置
     *
     * @return
     */
    @Bean
    public EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer() {
        return new MyCustomizer();
    }

    /**
     * 内嵌服务器配置
     */
    private static class MyCustomizer implements EmbeddedServletContainerCustomizer {


            public void customize(ConfigurableEmbeddedServletContainer container) {
                container.setDocumentRoot(new File(System.getProperty("user.dir")+File.separator+"webapps"+File.separator));
        }
    }


}
