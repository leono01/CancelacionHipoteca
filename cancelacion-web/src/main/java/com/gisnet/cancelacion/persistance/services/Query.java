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

import com.gisnet.cancelacion.events.DeleteResponse;
import com.gisnet.cancelacion.events.Info;
import com.gisnet.cancelacion.events.ListResponse;
import com.gisnet.cancelacion.persistance.domain.Infoable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author marco-g8
 */
public class Query {
    
    public static <T extends Info, K extends Infoable<T>> ListResponse<T> list(Iterable<K> query) {
        List<T> list = new ArrayList<>();
        for (K k : query) {
            list.add(k.asInfo());
        }
        return new ListResponse<>(list);
    }
    
    public static <T extends Info> T saveOrUpdate(T info, CrudRepository repo) {
        throw new UnsupportedOperationException("not supported yet");
    }
    
    public static DeleteResponse delete() {
        throw new UnsupportedOperationException("not supported yet");
    }
}
