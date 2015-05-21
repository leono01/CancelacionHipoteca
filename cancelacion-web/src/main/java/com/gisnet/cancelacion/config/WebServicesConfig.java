package com.gisnet.cancelacion.config;

import com.gisnet.cancelacion.wsclient.pms.ClienteMicroflujoService;
import com.gisnet.cancelacion.wsclient.pms.ClienteMicroflujoServiceHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebServicesConfig {

    @Bean
    ClienteMicroflujoService clienteMicroflujoService() {
        return new ClienteMicroflujoServiceHandler();
    }
    
}
