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
import com.gisnet.cancelacion.events.info.MunicipioInfo;
import com.gisnet.cancelacion.persistance.domain.Municipio;
import com.gisnet.cancelacion.persistance.repository.MunicipioRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author marco-g8
 */
public class MunicipioPersistanceServiceHandler implements MunicipioPersistanceService {

    @Autowired
    private PersistanceDomainFactory factory;

    @Autowired
    private MunicipioRepository repository;

    @Override
    public FindResponse<MunicipioInfo> find(FindByRequest event) {
        switch (event.getKey()) {
            case "ID":
                if (!(event.getValue() instanceof Long)) {
                    throw new IllegalArgumentException("Valor de llave incorrecto");
                }
                Municipio findOne = repository.findOne((long) event.getValue());
                return new FindResponse<>(findOne != null ? findOne.asInfo() : null);
        }
        throw new IllegalArgumentException("Llave desconocida o no disponible para busqueda");
    }

    @Override
    public ListResponse<MunicipioInfo> list(ListRequest event) {
        switch (event.getKey()) {
            case "ALL":
                return Query.list(repository.findAll());

            case "entidadId":
                if (!(event.getValue() instanceof Long)) {
                    throw new IllegalArgumentException("Valor de llave incorrecto");
                }
                return Query.list(repository.findAllByEntidadId((long) event.getValue()));

            case "entidadClave":
                if (!(event.getValue() instanceof String)) {
                    throw new IllegalArgumentException("Valor de llave incorrecto");
                }
                return Query.list(repository.findAllByEntidadClave((String) event.getValue()));
        }
        throw new IllegalArgumentException("Llave desconocida o no disponible para busqueda");
    }

    @Override
    public SaveResponse<MunicipioInfo> save(SaveRequest<MunicipioInfo> event) {
        if (event.getInfo().getId() > 0) {
            throw new IllegalArgumentException("ID invalido");
        }
        return new SaveResponse<>(saveOrUpdate(event.getInfo()));
    }

    @Override
    public UpdateResponse<MunicipioInfo> update(UpdateRequest<MunicipioInfo> event) {
        return new UpdateResponse<>(saveOrUpdate(event.getInfo()));
    }

    private MunicipioInfo saveOrUpdate(MunicipioInfo info) {
        Municipio u = factory.buildMunicipio(info);
        return repository.save(u).asInfo();
    }

    @Override
    public DeleteResponse<MunicipioInfo> delete(DeleteRequest event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
