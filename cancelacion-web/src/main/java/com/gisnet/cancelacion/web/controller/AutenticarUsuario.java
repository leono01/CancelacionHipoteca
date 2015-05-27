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
import com.gisnet.cancelacion.events.FindResponse;
import com.gisnet.cancelacion.events.info.UsuarioInfo;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author marco-g8
 */
public class AutenticarUsuario implements AuthenticationProvider {
    
    @Autowired
    private UsuarioService service;

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		FindResponse<UsuarioInfo> find = service.findByUsername(username);
		UsuarioInfo usuario = find.getInfo();
		
		if (usuario != null) {
			if (service.loguear(new FindByRequest(username, password))) {
				List<GrantedAuthority> grants = new ArrayList<>();
				for (String rol : usuario.getRoles()) {
					grants.add(new SimpleGrantedAuthority(rol));
				}
				return new UsernamePasswordAuthenticationToken(username, password, grants);
			}
			throw new AuthenticationServiceException("Autenticacion fallida");
		}
		throw new UsernameNotFoundException("Usuario no encontrado.");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
    
}
