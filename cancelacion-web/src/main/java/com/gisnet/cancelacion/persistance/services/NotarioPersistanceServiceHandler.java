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
package com.gisnet.cancelacion.persistance.services;

import com.gisnet.cancelacion.events.DeleteRequest;
import com.gisnet.cancelacion.events.DeleteResponse;
import com.gisnet.cancelacion.events.FindRequest;
import com.gisnet.cancelacion.events.FindResponse;
import com.gisnet.cancelacion.events.ListRequest;
import com.gisnet.cancelacion.events.ListResponse;
import com.gisnet.cancelacion.events.SaveRequest;
import com.gisnet.cancelacion.events.SaveResponse;
import com.gisnet.cancelacion.events.UpdateRequest;
import com.gisnet.cancelacion.events.UpdateResponse;
import com.gisnet.cancelacion.events.info.NotarioInfo;
import com.gisnet.cancelacion.persistance.domain.Notario;
import com.gisnet.cancelacion.persistance.repository.NotarioRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author marco-g8
 */
public class NotarioPersistanceServiceHandler implements NotarioPersistanceService {

    @Autowired
    private NotarioRepository repository;

    @Override
    public FindResponse<NotarioInfo> find(FindRequest event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ListResponse<NotarioInfo> list(ListRequest event) {
        Iterable<Notario> query = repository.findAll();
        List<NotarioInfo> list = new ArrayList<>();
        for (Notario notario : query) {
            list.add(notario.asInfo());
        }
        ListResponse<NotarioInfo> response = new ListResponse<>(list);
        return response;
    }

    @Override
    public SaveResponse<NotarioInfo> save(SaveRequest<NotarioInfo> event) {
        Notario notario = new Notario();
        NotarioInfo info = event.getInfo();
        
        notario.setNombre(info.getNombre());
        notario.setCodigo(info.getCodigo());
        notario.setNotariaNumero(info.getNotariaNumero());
        notario.setConvenioInfonavit(info.isConvenioInfonavit());
        notario.setEmail(info.getEmail());
        notario.setTelefono(info.getTelefono());
        notario.setCalleNotaria(info.getCalleNotaria());
        notario.setNumeroCalle(info.getNumeroCalle());
        notario.setColoniaNotaria(info.getColoniaNotaria());
        notario.setCodigoPostalNotaria(info.getCodigoPostalNotaria());
        
        notario = repository.save(notario);
        
        SaveResponse<NotarioInfo> response = new SaveResponse<>(notario.asInfo());
        return response;
    }

    @Override
    public UpdateResponse<NotarioInfo> update(UpdateRequest event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public DeleteResponse<NotarioInfo> delete(DeleteRequest event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
