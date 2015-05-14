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

/**
 *
 * @author marco-g8
 * @param <T>
 */
public interface BasicCrud<T extends Info> {

    public FindResponse<T> find(FindByRequest event);

    public ListResponse<T> list(ListRequest event);

    public SaveResponse<T> save(SaveRequest<T> event);

    public UpdateResponse<T> update(UpdateRequest<T> event);

    public DeleteResponse<T> delete(DeleteRequest event);

}
