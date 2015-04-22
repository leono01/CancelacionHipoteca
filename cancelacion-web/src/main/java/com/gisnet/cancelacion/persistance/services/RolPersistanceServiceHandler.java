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

import com.gisnet.cancelacion.events.DeleteRequest;
import com.gisnet.cancelacion.events.DeleteResponse;
import com.gisnet.cancelacion.events.FindRequest;
import com.gisnet.cancelacion.events.FindResponse;
import com.gisnet.cancelacion.events.ListRequest;
import com.gisnet.cancelacion.events.ListResponse;
import com.gisnet.cancelacion.events.SaveRequest;
import com.gisnet.cancelacion.events.SaveResponse;
import com.gisnet.cancelacion.events.UpdateRequest;
import com.gisnet.cancelacion.events.UpdateResponse;
import com.gisnet.cancelacion.events.info.RolInfo;


public class RolPersistanceServiceHandler implements RolPersistanceService {

    @Override
    public FindResponse<RolInfo> find(FindRequest event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ListResponse<RolInfo> list(ListRequest event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public SaveResponse<RolInfo> save(SaveRequest<RolInfo> event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public UpdateResponse<RolInfo> update(UpdateRequest<RolInfo> event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public DeleteResponse<RolInfo> delete(DeleteRequest event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
