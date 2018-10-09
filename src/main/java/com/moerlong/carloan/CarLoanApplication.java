package com.moerlong.carloan;

import com.moerlong.carloan.config.properties.FundProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * SpringBoot启动类
 * @Date 2017/5/21 12:06
 */
//@EnableAspectJAutoProxy(proxyTargetClass = true)
@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement
public class CarLoanApplication extends WebMvcConfigurerAdapter{

    protected final static Logger logger = LoggerFactory.getLogger(CarLoanApplication.class);

    @Autowired
    FundProperties fundProperties;

    /**
     * 增加静态资源 */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if(fundProperties.getSwaggerOpen()){
            registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
            registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        }
        registry.addResourceHandler("/loantest/**").addResourceLocations("file:" + "/nodedata/node/loantest/");
    }

    public static void main(String[] args) {
        SpringApplication.run(CarLoanApplication.class, args);
        logger.info("################# Application is success! #################");
    }
    /*@Bean
    public RestTemplate restTemplate() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

        SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
                .loadTrustMaterial(null, acceptingTrustStrategy)
                .build();

        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(csf)
                .build();

        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory();

        requestFactory.setHttpClient(httpClient);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        return restTemplate;
    }*/
}
