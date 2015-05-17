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
import com.gisnet.cancelacion.events.info.ProyectoCancelacionInfo;
import com.gisnet.cancelacion.events.info.ProyectoRPPInfo;

/**
 *
 * @author marco-g8
 */
public class CasoCompleto {
    
    private CasoInfo caso;
    private ProyectoCancelacionInfo proyectoCancelacion;
    private ProyectoRPPInfo proyectoRPP;

    public CasoCompleto() {
    }

    public CasoInfo getCaso() {
        return caso;
    }

    public void setCaso(CasoInfo caso) {
        this.caso = caso;
    }

    public ProyectoCancelacionInfo getProyectoCancelacion() {
        return proyectoCancelacion;
    }

    public void setProyectoCancelacion(ProyectoCancelacionInfo proyectoCancelacion) {
        this.proyectoCancelacion = proyectoCancelacion;
    }

    public ProyectoRPPInfo getProyectoRPP() {
        return proyectoRPP;
    }

    public void setProyectoRPP(ProyectoRPPInfo proyectoRPP) {
        this.proyectoRPP = proyectoRPP;
    }
    
    
    
}
