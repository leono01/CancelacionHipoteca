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
import com.gisnet.cancelacion.events.info.CasoInfo;
import com.gisnet.cancelacion.persistance.domain.Caso;
import com.gisnet.cancelacion.persistance.repository.CasoRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author marco-g8
 */
public class CasoPersistanceServiceHandler implements CasoPersistanceService {

    @Autowired
    private PersistanceDomainFactory factory;

    @Autowired
    private CasoRepository repository;

    @Override
    public FindResponse<CasoInfo> find(FindByRequest event) {
        switch (event.getKey()) {
            case "ID":
                if (!(event.getValue() instanceof Long)) {
                    throw new IllegalArgumentException("Valor de llave incorrecto");
                }
                return new FindResponse<>(repository.findOne((long) event.getValue()).asInfo());

            case "numeroCaso":
                if (!(event.getValue() instanceof Integer)) {
                    throw new IllegalArgumentException("Valor de llave incorrecto");
                }
                return new FindResponse<>(repository.findByNumeroCaso((int) event.getValue()).asInfo());

            case "numeroCredito":
                if (!(event.getValue() instanceof Integer)) {
                    throw new IllegalArgumentException("Valor de llave incorrecto");
                }
                return new FindResponse<>(repository.findByNumeroCredito((int) event.getValue()).asInfo());
        }
        throw new IllegalArgumentException("Llave desconocida o no disponible para busqueda");
    }

    @Override
    public ListResponse<CasoInfo> list(ListRequest event) {
        switch (event.getKey()) {
            case "ALL":
                return Query.list(repository.findAll());

            case "entidad":
                if (!(event.getValue() instanceof String)) {
                    throw new IllegalArgumentException("Valor de llave incorrecto");
                }
                return Query.list(repository.findAllByEntidad((String) event.getValue()));
                
            case "notarioId":
                if (!(event.getValue() instanceof Long)) {
                    throw new IllegalArgumentException("Valor de llave incorrecto");
                }
                Iterable<Caso> findAllByNotarioId = repository.findAllByNotarioId((long) event.getValue());
                return Query.list(findAllByNotarioId);
        }
        throw new IllegalArgumentException("Llave desconocida o no disponible para busqueda");
    }

    @Override
    public SaveResponse<CasoInfo> save(SaveRequest<CasoInfo> event) {
        if (event.getInfo().getId() > 0) {
            throw new IllegalArgumentException("ID invalido");
        }
        return new SaveResponse<>(saveOrUpdate(event.getInfo()));
    }

    @Override
    public UpdateResponse<CasoInfo> update(UpdateRequest<CasoInfo> event) {
        return new UpdateResponse<>(saveOrUpdate(event.getInfo()));
    }

    private CasoInfo saveOrUpdate(CasoInfo info) {
        Caso u = factory.buildCaso(info);
        return repository.save(u).asInfo();
    }

    @Override
    public DeleteResponse<CasoInfo> delete(DeleteRequest event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
