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
package com.gisnet.cancelacion.web.domain;

import com.gisnet.cancelacion.events.info.CasoInfo;
import com.gisnet.cancelacion.events.info.EmpleadoInfo;
import com.gisnet.cancelacion.events.info.UsuarioInfo;

/**
 *
 * @author marco-g8
 */
public class SesionJCobranza {

    private EmpleadoInfo empleado;
    private UsuarioInfo info;

    private CasoInfo caso;

    public SesionJCobranza() {
    }

    public EmpleadoInfo getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmpleadoInfo empleado) {
        this.empleado = empleado;
    }

    public UsuarioInfo getInfo() {
        return info;
    }

    public void setInfo(UsuarioInfo info) {
        this.info = info;
    }

    public CasoInfo getCaso() {
        return caso;
    }

    public void setCaso(CasoInfo caso) {
        this.caso = caso;
    }

}