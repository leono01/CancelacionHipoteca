/*
 * Copyright (C) 2015 GISNET
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.gisnet.cancelacion.config;

import com.gisnet.cancelacion.persistance.services.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author marco-g8
 */
 @Configuration
public class PersistanceConfig {
     
     @Bean
     BitacoraCasoPersistanceService bitacoraCasoPersistanceService() {
         return new BitacoraCasoPersistanceServiceHandler();
     }
     
     @Bean
     BitacoraProyectoPersistanceService bitacoraProyectoPersistanceService() {
         return new BitacoraProyectoPersistanceServiceHandler();
     }
     
     @Bean
     BitacoraRPPPersistanceService bitacoraRPPPersistanceService() {
         return new BitacoraRPPPersistanceServiceHandler();
     }
     
     @Bean
     CancelacionArchivoPersistanceService cancelacionArchivoPersistanceService() {
         return new CancelacionArchivoPersistanceServiceHandler();
     }
     
     @Bean
     CartaCancelacionPersistanceService cancelacionPersistanceService() {
         return new CartaCancelacionPersistanceServiceHandler();
     }
     
     @Bean
     CasoPersistanceService casoPersistanceService() {
         return new CasoPersistanceServiceHandler();
     }
     
     @Bean
     EmpleadoPersistanceService empleadoPersistanceService() {
         return new EmpleadoPersistanceServiceHandler();
     }
     
     @Bean
     EntidadPersistanceService entidadPersistanceService() {
         return new EntidadPersistanceServiceHandler();
     }
     
     @Bean
     MunicipioPersistanceService municipioPersistanceService() {
         return new MunicipioPersistanceServiceHandler();
     }
    
     @Bean
     NotarioPersistanceService notarioPersistanceService() {
         return new NotarioPersistanceServiceHandler();
     }
     
     @Bean
     PersistanceDomainFactory domainFactory() {
         return new PersistanceDomainFactory();
     }
     
     @Bean
     ProyectoCancelacionPersistanceService proyectoCancelacionPersistanceService() {
         return new ProyectoCancelacionPersistanceServiceHandler();
     }
     
     @Bean
     ProyectoRPPPersistanceService proyectoRPPPersistanceService() {
         return new ProyectoRPPPersistanceServiceHandler();
     }
     
     @Bean
     RolPersistanceService rolPersistanceService() {
         return new RolPersistanceServiceHandler();
     }
     
     @Bean
     StatusCasoPersistanceService statusCasoPersistanceService() {
         return new StatusCasoPersistanceServiceHandler();
     }
     
     @Bean
     StatusProyectoPersistanceService statusProyectoPersistanceService() {
         return new StatusProyectoPersistanceServiceHandler();
     }
     
     @Bean
     StatusRPPPersistanceService statusRPPPersistanceService() {
         return new StatusRPPPersistanceServiceHandler();
     }
     
     @Bean
     UsuarioPersistanceService usuarioPersistanceService() {
         return new UsuarioPersistanceServiceHandler();
     }
     
}

