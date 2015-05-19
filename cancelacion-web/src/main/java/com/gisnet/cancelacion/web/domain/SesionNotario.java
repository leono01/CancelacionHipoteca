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

import com.gisnet.cancelacion.events.info.CancelacionArchivoInfo;
import com.gisnet.cancelacion.events.info.CasoInfo;
import com.gisnet.cancelacion.events.info.NotarioInfo;
import com.gisnet.cancelacion.events.info.UsuarioInfo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author marco-g8
 */
public class SesionNotario {

    private NotarioInfo notarioInfo;
    private UsuarioInfo usuarioInfo;

    private CasoInfo casoInfo;
    private List<CancelacionArchivoInfo> cancelacionArchivos;

    public SesionNotario() {
        this.cancelacionArchivos = new ArrayList<>();
    }

    public NotarioInfo getNotarioInfo() {
        return notarioInfo;
    }

    public void setNotarioInfo(NotarioInfo notarioInfo) {
        this.notarioInfo = notarioInfo;
    }

    public UsuarioInfo getUsuarioInfo() {
        return usuarioInfo;
    }

    public void setUsuarioInfo(UsuarioInfo usuarioInfo) {
        this.usuarioInfo = usuarioInfo;
    }

    public CasoInfo getCasoInfo() {
        return casoInfo;
    }

    public void setCasoInfo(CasoInfo casoInfo) {
        this.casoInfo = casoInfo;
    }

    public List<CancelacionArchivoInfo> getCancelacionArchivos() {
        return cancelacionArchivos;
    }

    public void setCancelacionArchivos(List<CancelacionArchivoInfo> cancelacionArchivos) {
        this.cancelacionArchivos = cancelacionArchivos;
    }

}
