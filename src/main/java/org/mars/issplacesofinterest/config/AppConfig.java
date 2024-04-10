package org.mars.issplacesofinterest.config;

import lombok.Data;
import org.mars.issplacesofinterest.service.ISSLocationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableConfigurationProperties
@Data
public class AppConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ISSLocationService issLocationService() {
        return new ISSLocationService(restTemplate(), this);
    }

    @Value("${open.notify.api.baseurl}")
    private  String openNotifyApiBaseUrl;

    @Value("${mediawiki.api.baseurl}")
    private String mediaWikiApiBaseUrl;

    @Value("${photon.api.baseurl.reverse}")
    private String photonApiBaseUrl;

    @Bean
    public InternalResourceViewResolver defaultViewResolver() {
        return new InternalResourceViewResolver();
    }


}
