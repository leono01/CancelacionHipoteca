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
package com.gisnet.cancelacion.persistance.repository;

import com.gisnet.cancelacion.persistance.domain.Municipio;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author marco-g8
 */
public interface MunicipioRepository extends CrudRepository<Municipio, Long> {

    @Query("select m from Municipio m where m.entidad.id = :id")
    Iterable<Municipio> findAllByEntidadId(@Param("id") long id);

    @Query("select m from Municipio m where m.entidad.clave = :clave")
    Iterable<Municipio> findAllByEntidadClave(@Param("clave") String clave);

}
