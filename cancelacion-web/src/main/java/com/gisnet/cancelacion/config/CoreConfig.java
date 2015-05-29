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

import com.gisnet.cancelacion.core.services.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author marco-g8
 */
@Configuration
class CoreConfig {

    @Bean
    CancelacionArchivoService cancelacionArchivoService() {
        return new CancelacionArchivoServiceHandler();
    }

    @Bean
    CartaCancelacionService cartaCancelacionService() {
        return new CartaCancelacionServiceHandler();
    }

    @Bean
    CasoService casoService() {
        return new CasoServiceHandler();
    }

    @Bean
    EmpleadoService empleadoService() {
        return new EmpleadoServiceHandler();
    }
    
    @Bean EntidadService entidadService() {
        return new EntidadServiceHandler();
    }
    
    @Bean MunicipioService municipioService() {
        return new MunicipioServiceHandler();
    }

    @Bean
    NotarioService notarioService() {
        return new NotarioServiceHandler();
    }

    @Bean
    ProyectoCancelacionService proyectoCancelacionService() {
        return new ProyectoCancelacionServiceHandler();
    }

    @Bean
    StatusCasoService statusCasoService() {
        return new StatusCasoServiceHandler();
    }

    @Bean
    StatusNotarioService statusNotarioService() {
        return new StatusNotarioServiceHandler();
    }

    @Bean
    StatusProyectoService statusProyectoService() {
        return new StatusProyectoServiceHandler();
    }

    @Bean
    StatusRPPService statusRPPService() {
        return new StatusRPPServiceHandler();
    }

    @Bean
    UsuarioService usuarioService() {
        return new UsuarioServiceHandler();
    }
}
