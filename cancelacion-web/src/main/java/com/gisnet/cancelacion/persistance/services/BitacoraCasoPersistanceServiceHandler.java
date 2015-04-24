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
import com.gisnet.cancelacion.events.info.BitacoraCasoInfo;
import com.gisnet.cancelacion.persistance.domain.BitacoraCaso;
import com.gisnet.cancelacion.persistance.repository.BitacoraCasoRepository;
import org.springframework.beans.factory.annotation.Autowired;


public class BitacoraCasoPersistanceServiceHandler implements BitacoraCasoPersistanceService {
    
    @Autowired
    private PersistanceDomainFactory factory;
    
    @Autowired
    private BitacoraCasoRepository repository;

    @Override
    public FindResponse<BitacoraCasoInfo> find(FindByIdRequest event) {
        return new FindResponse<>(repository.findOne(event.getId()).asInfo());
    }

    @Override
    public FindResponse<BitacoraCasoInfo> find(FindByRequest<BitacoraCasoInfo, Object> event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ListResponse<BitacoraCasoInfo> list(ListRequest event) {
        return Query.list(repository.findAll());
    }

    @Override
    public SaveResponse<BitacoraCasoInfo> save(SaveRequest<BitacoraCasoInfo> event) {
        if (event.getInfo().getId() > 0) {
            throw new IllegalArgumentException("ID invalido");
        }
        return new SaveResponse<>(saveOrUpdate(event.getInfo()));
    }

    @Override
    public UpdateResponse<BitacoraCasoInfo> update(UpdateRequest<BitacoraCasoInfo> event) {
        return new UpdateResponse<>(saveOrUpdate(event.getInfo()));
    }
    
    private BitacoraCasoInfo saveOrUpdate(BitacoraCasoInfo info) {
        BitacoraCaso u = factory.buildBitacoraCaso(info);
        return repository.save(u).asInfo();
    }

    @Override
    public DeleteResponse<BitacoraCasoInfo> delete(DeleteRequest event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
