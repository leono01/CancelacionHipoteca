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
import com.gisnet.cancelacion.events.info.StatusCasoInfo;
import com.gisnet.cancelacion.persistance.domain.StatusCaso;
import com.gisnet.cancelacion.persistance.repository.StatusCasoRepository;
import org.springframework.beans.factory.annotation.Autowired;


public class StatusCasoPersistanceServiceHandler implements StatusCasoPersistanceService {
    
    @Autowired
    private PersistanceDomainFactory factory;
    
    @Autowired
    private StatusCasoRepository repository;

    @Override
    public FindResponse<StatusCasoInfo> find(FindByIdRequest event) {
        return new FindResponse<>(repository.findOne(event.getId()).asInfo());
    }

    @Override
    public FindResponse<StatusCasoInfo> find(FindByRequest<StatusCasoInfo, Object> event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ListResponse<StatusCasoInfo> list(ListRequest event) {
        return Query.list(repository.findAll());
    }

    @Override
    public SaveResponse<StatusCasoInfo> save(SaveRequest<StatusCasoInfo> event) {
        if (event.getInfo().getId() > 0) {
            throw new IllegalArgumentException("ID invalido");
        }
        return new SaveResponse<>(saveOrUpdate(event.getInfo()));
    }

    @Override
    public UpdateResponse<StatusCasoInfo> update(UpdateRequest<StatusCasoInfo> event) {
        return new UpdateResponse<>(saveOrUpdate(event.getInfo()));
    }
    
    private StatusCasoInfo saveOrUpdate(StatusCasoInfo info) {
        StatusCaso u = factory.buildStatusCaso(info);
        return repository.save(u).asInfo();
    }

    @Override
    public DeleteResponse<StatusCasoInfo> delete(DeleteRequest event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
