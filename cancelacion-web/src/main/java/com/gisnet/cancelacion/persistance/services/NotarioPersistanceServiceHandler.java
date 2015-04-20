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

import com.gisnet.cancelacion.persistance.domain.Notario;
import com.gisnet.cancelacion.persistance.repository.NotarioRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author marco-g8
 */
public class NotarioPersistanceServiceHandler implements NotarioPersistanceService {
    
    @Autowired private NotarioRepository repository;

    @Override
    public Map<String, Object> list(Map<String, Object> event) {
        Iterable<Notario> query = repository.findAll();
        List<Map<String, Object>> list = new ArrayList<>();
        event.put("list", list);
        for (Notario n : query) {
            Map<String, Object> row = new HashMap<>();
            row.put("id", n.getId());
            row.put("numeroNotaria", n.getNotariaNumero());
            row.put("nombre", n.getNombre());
            row.put("municipio", n.getMunicipio());
            row.put("estado", "NA");
            row.put("domicilio", n.getCalleNotaria());
            row.put("correoElectronico", n.getCorreoElectronico());
            row.put("telefono", n.getTelefono());
            row.put("convenio", n.isConvenioInfonavit());
            list.add(row);
        }
        return event;
    }

    @Override
    public Map<String, Object> save(Map<String, Object> event) {
        Notario n = new Notario();
        n.setNotariaNumero((String) event.get("numeroNotaria"));
        n.setNombre((String) event.get("nombre"));
        n.setMunicipio((String) event.get("municipio"));
        //n.setEstado((String) event.get("estado"));
        n.setCalleNotaria((String) event.get("domicilio"));
        n.setCorreoElectronico((String) event.get("correoElectronico"));
        n.setTelefono((String) event.get("telefono"));
        n.setConvenioInfonavit((boolean) event.get("convenio"));
        
        repository.save(n);
        event.put("status", "OK");

        return event;
    }

    @Override
    public Map<String, Object> update(Map<String, Object> event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Map<String, Object> delete(Map<String, Object> event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}

