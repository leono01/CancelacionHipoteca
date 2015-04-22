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
import com.gisnet.cancelacion.events.info.ProyectoRPPInfo;


public class ProyectoRPPPersistanceServiceHandler implements ProyectoRPPPersistanceService {

    @Override
    public FindResponse<ProyectoRPPInfo> find(FindRequest event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ListResponse<ProyectoRPPInfo> list(ListRequest event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public SaveResponse<ProyectoRPPInfo> save(SaveRequest<ProyectoRPPInfo> event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public UpdateResponse<ProyectoRPPInfo> update(UpdateRequest<ProyectoRPPInfo> event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public DeleteResponse<ProyectoRPPInfo> delete(DeleteRequest event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
