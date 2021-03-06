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
import com.gisnet.cancelacion.events.info.ProyectoRPPInfo;
import com.gisnet.cancelacion.persistance.domain.ProyectoRPP;
import com.gisnet.cancelacion.persistance.repository.ProyectoRPPRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author marco-g8
 */
public class ProyectoRPPPersistanceServiceHandler implements ProyectoRPPPersistanceService {

    @Autowired
    private PersistanceDomainFactory factory;

    @Autowired
    private ProyectoRPPRepository repository;

    @Override
    public FindResponse<ProyectoRPPInfo> find(FindByRequest event) {
        switch (event.getKey()) {
            case "ID":
                if (!(event.getValue() instanceof Long)) {
                    throw new IllegalArgumentException("Valor de llave incorrecto");
                }
                ProyectoRPP findOne = repository.findOne((long) event.getValue());
                return new FindResponse<>(findOne != null ? findOne.asInfo() : null);
        }
        throw new IllegalArgumentException("Llave desconocida o no disponible para busqueda");
    }

    @Override
    public ListResponse<ProyectoRPPInfo> list(ListRequest event) {
        return Query.list(repository.findAll());
    }

    @Override
    public SaveResponse<ProyectoRPPInfo> save(SaveRequest<ProyectoRPPInfo> event) {
        if (event.getInfo().getId() > 0) {
            throw new IllegalArgumentException("ID invalido");
        }
        return new SaveResponse<>(saveOrUpdate(event.getInfo()));
    }

    @Override
    public UpdateResponse<ProyectoRPPInfo> update(UpdateRequest<ProyectoRPPInfo> event) {
        return new UpdateResponse<>(saveOrUpdate(event.getInfo()));
    }

    private ProyectoRPPInfo saveOrUpdate(ProyectoRPPInfo info) {
        ProyectoRPP u = factory.buildProyectoRPP(info);
        return repository.save(u).asInfo();
    }

    @Override
    public DeleteResponse<ProyectoRPPInfo> delete(DeleteRequest event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
