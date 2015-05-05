/*
 * Copyright (C) 2015 dev02
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

import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 *
 * @author dev02
 */
public class CsrfUrlMatcher implements RequestMatcher {
    // ignorar GET, HEAD, TRACE, OPTIONS
    private final Pattern allowedMethods = Pattern.compile("^(GET)|(HEAD)|(TRACE)|(OPTIONS)$");
    
    private final AntPathRequestMatcher[] requestMatchers = {
          new AntPathRequestMatcher("/CartaCancelacion**"),
          new AntPathRequestMatcher("/ConsultarListaDeNotarios**"),
          new AntPathRequestMatcher("/RegistraActualizaYConsultaCaso**")
    };

    @Override
    public boolean matches(HttpServletRequest request) {
        if (allowedMethods.matcher(request.getMethod()).matches()) {
            return false;
        }
        for (AntPathRequestMatcher ant : requestMatchers) {
            if (ant.matches(request)) {
                return false;
            }
        }
        
        return true;
    }
    
}
