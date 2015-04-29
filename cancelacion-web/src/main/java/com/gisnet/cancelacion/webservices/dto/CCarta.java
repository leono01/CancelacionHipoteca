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
package com.gisnet.cancelacion.webservices.dto;

import java.io.File;

/**
 *
 * @author leonel
 */
public class CCarta {

    String  codigoCarta;
    File    pdfCarta;
    File    xmlCarta;
    
    public String getCodigoCarta() {
        return codigoCarta;
    }

    public void setCodigoCarta(String codigoCarta) {
        this.codigoCarta = codigoCarta;
    }

    public File getPdfCarta() {
        return pdfCarta;
    }

    public void setPdfCarta(File pdfCarta) {
        this.pdfCarta = pdfCarta;
    }

    public File getXmlCarta() {
        return xmlCarta;
    }

    public void setXmlCarta(File xmlCarta) {
        this.xmlCarta = xmlCarta;
    }
}
