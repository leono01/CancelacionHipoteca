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

import com.gisnet.cancelacion.persistance.domain.Notario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author marco-g8
 */
public interface NotarioRepository extends CrudRepository<Notario, Long> {

    Iterable<Notario> findAllByEntidad2(String entidad2);

    @Query("select n from Notario n where n.usuario.nombreUsuario = :nombreUsuario")
    Notario findByNombreUsuario(@Param("nombreUsuario") String nombreUsuario);
    
    @Query("select n from Notario n where n.codigoNotario = :codigoNotario")
    Notario findByCodigoNotario(@Param("codigoNotario") String codigoNotario);

    @Query("select n from Notario n where n.usuario.id = :usuarioid")
    Notario findByUsuarioId(@Param("usuarioid") long usuarioid);

    @Query("select n from Notario n where n.notariaNumero = :notariaNumero and n.entidad.id = :entidadId")
    Notario findByUsuarioId(@Param("notariaNumero") String notariaNumero, @Param("entidadId") long entidadId);
    
    @Query("select n from Notario n where n.nombre like %:nombre%")
    Iterable<Notario> findLikeNombre(@Param("nombre") String nombre);
    
  //Busca con entidad con ID
    @Query("select n from Notario n where n.notariaNumero = :notariaNumero and n.entidad.id = :entidadId and n.nombre= :nombreNotario")
    Notario findByNombreNotarioEntidadyNumeroNotaria(@Param("notariaNumero") String notariaNumero, @Param("entidadId") long entidadId, @Param("nombreNotario") String nombreNotario);
    
    //Busca con entidad en texto
    @Query("select n from Notario n where n.notariaNumero = :notariaNumero and n.entidad.clave = :entidadClave and n.nombre= :nombreNotario")
    Notario findByNombreNotarioEntidadyNumeroNotariaTxt(@Param("notariaNumero") String notariaNumero, @Param("entidadClave") String entidadClave, @Param("nombreNotario") String nombreNotario);

}
