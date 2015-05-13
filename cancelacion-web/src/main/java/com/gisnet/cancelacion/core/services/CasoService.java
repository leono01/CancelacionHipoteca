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

package com.gisnet.cancelacion.core.services;

import com.gisnet.cancelacion.events.DeleteRequest;
import com.gisnet.cancelacion.events.DeleteResponse;
import com.gisnet.cancelacion.events.FindByIdRequest;
import com.gisnet.cancelacion.events.FindResponse;
import com.gisnet.cancelacion.events.ListRequest;
import com.gisnet.cancelacion.events.ListResponse;
import com.gisnet.cancelacion.events.SaveRequest;
import com.gisnet.cancelacion.events.SaveResponse;
import com.gisnet.cancelacion.events.UpdateRequest;
import com.gisnet.cancelacion.events.UpdateResponse;
import com.gisnet.cancelacion.events.info.CasoInfo;

/**
 *
 * @author leonel
 */

public interface CasoService {
	
	public FindResponse<CasoInfo> find(FindByIdRequest event);
    
    public ListResponse<CasoInfo> list(ListRequest event);
    
    public SaveResponse<CasoInfo> save(SaveRequest<CasoInfo> event);
    
    public UpdateResponse<CasoInfo> update(UpdateRequest<CasoInfo> event);
    
    public DeleteResponse<CasoInfo> delete(DeleteRequest event);

}
