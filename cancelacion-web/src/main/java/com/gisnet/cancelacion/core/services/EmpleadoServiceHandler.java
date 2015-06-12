/*
 * Copyright (C) 2015 marco-g8
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
package com.gisnet.cancelacion.core.services;

import com.gisnet.cancelacion.events.*;
import com.gisnet.cancelacion.events.info.EmpleadoInfo;
import com.gisnet.cancelacion.events.info.UsuarioInfo;
import com.gisnet.cancelacion.persistance.services.EmpleadoPersistanceService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;


public class EmpleadoServiceHandler implements EmpleadoService {
    
    @Autowired
    private EmpleadoPersistanceService service;
    
    @Autowired
    private UsuarioService usuarioService;

    @Override
    public FindResponse<EmpleadoInfo> find(FindByRequest event) {
        return service.find(event);
    }

    @Override
    public ListResponse<EmpleadoInfo> list(ListRequest event) {
        return service.list(event);
    }

    @Override
    public SaveResponse<EmpleadoInfo> save(SaveRequest<EmpleadoInfo> event) {
        return service.save(event);
    }

    @Override
    public UpdateResponse<EmpleadoInfo> update(UpdateRequest<EmpleadoInfo> event) {
        return service.update(event);
    }

    @Override
    public DeleteResponse<EmpleadoInfo> delete(DeleteRequest event) {
        return service.delete(event);
    }

    @Override
    public ListResponse<EmpleadoInfo> listarGerentesCobranza() {
        ListResponse<UsuarioInfo> list = usuarioService.list(
                new ListRequest("rol", "JEFE_COBRANZA"));
        List<EmpleadoInfo> gerentes = new ArrayList<>();
        for (UsuarioInfo usuario : list.getList()) {
            FindResponse<EmpleadoInfo> find = service.find(
                    new FindByRequest("usuarioId", usuario.getId()));
            gerentes.add(find.getInfo());
        }
        return new ListResponse<>(gerentes);
    }
    
}
