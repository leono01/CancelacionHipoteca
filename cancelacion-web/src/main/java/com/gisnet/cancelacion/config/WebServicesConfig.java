package com.gisnet.cancelacion.config;

import com.gisnet.cancelacion.wsclient.autenticacion.ClienteAutenticacionService;
import com.gisnet.cancelacion.wsclient.autenticacion.ClienteAutenticacionServiceHandler;
import com.gisnet.cancelacion.wsclient.pms.ClienteMicroflujoService;
import com.gisnet.cancelacion.wsclient.pms.ClienteMicroflujoServiceHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebServicesConfig {
    
    @Bean
    ClienteAutenticacionService clienteAutenticacionService() {
        return new ClienteAutenticacionServiceHandler();
    }

    @Bean
    ClienteMicroflujoService clienteMicroflujoService() {
        return new ClienteMicroflujoServiceHandler();
    }
    
}
