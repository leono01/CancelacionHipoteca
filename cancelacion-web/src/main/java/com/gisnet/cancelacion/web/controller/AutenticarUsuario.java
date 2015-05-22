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
package com.gisnet.cancelacion.web.controller;

import com.gisnet.cancelacion.core.services.UsuarioService;
import com.gisnet.cancelacion.events.FindByRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author marco-g8
 */
public class AutenticarUsuario implements AuthenticationUserDetailsService {
    
    @Autowired
    private UsuarioService service;

    @Override
    public UserDetails loadUserDetails(Authentication token) throws UsernameNotFoundException {
        System.err.println("user:" + token.getName() + " pass:" + (String) token.getCredentials());
        if (token.getName().equals("notario")) {
            Collection<GrantedAuthority> roles = new ArrayList<>();
            roles.add(new SimpleGrantedAuthority("ROLE_NOTARIO"));
            return new User(token.getName(), "", true, true, true, true, roles);
        }
        /*
        if (service.loguear(new FindByRequest(token.getName(), (String) token.getCredentials()))) {
            Collection<GrantedAuthority> roles = new ArrayList<>();
            roles.add(new SimpleGrantedAuthority("ROLE_NOTARIO"));
            return new User(token.getName(), "", true, true, true, true, roles);
        }
        */
        throw new UsernameNotFoundException("No encontrado");
    }
    
}