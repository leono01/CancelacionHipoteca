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
import com.gisnet.cancelacion.events.info.NotarioInfo;
import com.gisnet.cancelacion.persistance.domain.Notario;
import com.gisnet.cancelacion.persistance.repository.CasoRepository;
import com.gisnet.cancelacion.persistance.repository.NotarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author marco-g8
 */
public class NotarioPersistanceServiceHandler implements NotarioPersistanceService {

    @Autowired
    private PersistanceDomainFactory factory;

    @Autowired
    private CasoRepository casoRepository;

    @Autowired
    private NotarioRepository repository;

    @Override
    public FindResponse<NotarioInfo> find(FindByRequest event) {
        switch (event.getKey()) {
            case "ID":
                if (!(event.getValue() instanceof Long)) {
                    throw new IllegalArgumentException("Valor de llave incorrecto");
                }
                Notario findOne = repository.findOne((long) event.getValue());
                return new FindResponse<>(findOne != null ? findOne.asInfo() : null);

            case "numeroCaso":
                if (!(event.getValue() instanceof String)) {
                    throw new IllegalArgumentException("Valor de llave incorrecto");
                }
                Notario numeroCaso = casoRepository.findByNumeroCaso((int) event.getValue()).getNotario();
                return new FindResponse<>(numeroCaso != null ? numeroCaso.asInfo() : null);

            case "numeroCredito":
                if (!(event.getValue() instanceof String)) {
                    throw new IllegalArgumentException("Valor de llave incorrecto");
                }
                Notario numeroCredito = casoRepository.findByNumeroCredito((int) event.getValue()).getNotario();
                return new FindResponse<>(numeroCredito != null ? numeroCredito.asInfo() : null);

            case "nombreUsuario":
                if (!(event.getValue() instanceof String)) {
                    throw new IllegalArgumentException("Valor de llave incorrecto");
                }
                Notario nombreUsuario = repository.findByNombreUsuario((String) event.getValue());
                return new FindResponse<>(nombreUsuario != null ? nombreUsuario.asInfo() : null);
                
            case "usuarioId":
                if (!(event.getValue() instanceof Long)) {
                    throw new IllegalArgumentException("Valor de llave incorrecto");
                }
                Notario usuarioId = repository.findByUsuarioId((long) event.getValue());
                return new FindResponse<>(usuarioId != null ? usuarioId.asInfo() : null);
                
            case "notariaNumeroYentidadId":
                if (!(event.getValue() instanceof MultipleParams)) {
                    throw new IllegalArgumentException("Valor de llave incorrecto");
                }
                MultipleParams params = (MultipleParams) event.getValue();
                if (params.containsKey("notariaNumero")) {
                    if (!(params.get("notariaNumero") instanceof String)) {
                        throw new IllegalArgumentException("Valor de llave incorrecto");
                    }
                }
                else {
                    throw new IllegalArgumentException("Valor de llave incorrecto");
                }
                if (params.containsKey("entidadId")) {
                    if (!(params.get("entidadId") instanceof Long)) {
                        throw new IllegalArgumentException("Valor de llave incorrecto");
                    }
                }
                else {
                    throw new IllegalArgumentException("Valor de llave incorrecto");
                }
                Notario notariaNoEntidad = repository.findByUsuarioId((String) params.get("notariaNumero"), (long) params.get("entidadId"));
                return new FindResponse<>(notariaNoEntidad != null ? notariaNoEntidad.asInfo() : null);
                
        }
        throw new IllegalArgumentException("Llave desconocida o no disponible para busqueda");
    }

    @Override
    public ListResponse<NotarioInfo> list(ListRequest event) {
        switch (event.getKey()) {
            case "ALL":
                return Query.list(repository.findAll());

            case "entidad":
                if (!(event.getValue() instanceof String)) {
                    throw new IllegalArgumentException("Valor de llave incorrecto");
                }
                return Query.list(repository.findAllByEntidad2((String) event.getValue()));
                
            case "likeNombre":
                if (!(event.getValue() instanceof String)) {
                    throw new IllegalArgumentException("Valor de llave incorrecto");
                }
                return Query.list(repository.findLikeNombre((String) event.getValue()));
        }
        throw new IllegalArgumentException("Llave desconocida o no disponible para busqueda");
    }

    @Override
    public SaveResponse<NotarioInfo> save(SaveRequest<NotarioInfo> event) {
        if (event.getInfo().getId() > 0) {
            throw new IllegalArgumentException("ID invalido");
        }
        return new SaveResponse<>(saveOrUpdate(event.getInfo()));
    }

    @Override
    public UpdateResponse<NotarioInfo> update(UpdateRequest<NotarioInfo> event) {
        return new UpdateResponse<>(saveOrUpdate(event.getInfo()));
    }

    private NotarioInfo saveOrUpdate(NotarioInfo info) {
        Notario u = factory.buildNotario(info);
        return repository.save(u).asInfo();
    }

    @Override
    public DeleteResponse<NotarioInfo> delete(DeleteRequest event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
