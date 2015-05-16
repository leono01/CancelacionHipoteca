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

import com.gisnet.cancelacion.events.*;
import com.gisnet.cancelacion.events.info.StatusNotarioInfo;
import com.gisnet.cancelacion.persistance.services.StatusNotarioPersistanceService;
import org.springframework.beans.factory.annotation.Autowired;

public class StatusNotarioServiceHandler implements StatusNotarioService {

    @Autowired
    private StatusNotarioPersistanceService service;

    @Override
    public FindResponse<StatusNotarioInfo> find(FindByRequest event) {
        return service.find(event);
    }

    @Override
    public ListResponse<StatusNotarioInfo> list(ListRequest event) {
        return service.list(event);
    }

}
