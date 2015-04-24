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

import com.gisnet.cancelacion.events.*;
import com.gisnet.cancelacion.events.info.StatusProyectoInfo;
import com.gisnet.cancelacion.persistance.domain.StatusProyecto;
import com.gisnet.cancelacion.persistance.repository.StatusProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;


public class StatusProyectoPersistanceServiceHandler implements StatusProyectoPersistanceService {
    
    @Autowired
    private PersistanceDomainFactory factory;
    
    @Autowired
    private StatusProyectoRepository repository;

    @Override
    public FindResponse<StatusProyectoInfo> find(FindByIdRequest event) {
        return new FindResponse<>(repository.findOne(event.getId()).asInfo());
    }
    
    @Override
    public FindResponse<StatusProyectoInfo> find(FindByRequest<StatusProyectoInfo, Object> event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ListResponse<StatusProyectoInfo> list(ListRequest event) {
        return Query.list(repository.findAll());
    }

    @Override
    public SaveResponse<StatusProyectoInfo> save(SaveRequest<StatusProyectoInfo> event) {
        if (event.getInfo().getId() > 0) {
            throw new IllegalArgumentException("ID invalido");
        }
        return new SaveResponse<>(saveOrUpdate(event.getInfo()));
    }

    @Override
    public UpdateResponse<StatusProyectoInfo> update(UpdateRequest<StatusProyectoInfo> event) {
        return new UpdateResponse<>(saveOrUpdate(event.getInfo()));
    }
    
    private StatusProyectoInfo saveOrUpdate(StatusProyectoInfo info) {
        StatusProyecto u = factory.buildStatusProyecto(info);
        return repository.save(u).asInfo();
    }

    @Override
    public DeleteResponse<StatusProyectoInfo> delete(DeleteRequest event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
