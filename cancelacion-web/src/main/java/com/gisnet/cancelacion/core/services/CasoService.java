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
import com.gisnet.cancelacion.events.info.CancelacionArchivoInfo;
import com.gisnet.cancelacion.events.info.CasoInfo;
import java.util.List;

/**
 *
 * @author leonel
 */

public interface CasoService {

    // persistencia

    public FindResponse<CasoInfo> find(FindByIdRequest event);
    
    public FindResponse<CasoInfo> find(FindByRequest event);
    
    public ListResponse<CasoInfo> list(ListRequest event);
    
    public SaveResponse<CasoInfo> save(SaveRequest<CasoInfo> event);
    
    public UpdateResponse<CasoInfo> update(UpdateRequest<CasoInfo> event);
    
    public DeleteResponse<CasoInfo> delete(DeleteRequest event);
    
    // notario infonavit
    
    public ListResponse<CasoInfo> notarioInfonavitListaCasosPendientes(long notarioId);
    
    public ListResponse<CasoInfo> notarioInfonavitListaCasosAsignados(long notarioId);
    
    public StatusResponse notarioInfonavitAceptaCaso(long notarioId, long casoId, long empleadoGerenteCobranzaId, List<CancelacionArchivoInfo> archivosCancelacion);
    
    public StatusResponse notarioInfonavitRechazaCaso(long notarioId, long casoId, String motivoRechazo);
    
    // gerente cobranza
    
    //public StatusResponse gerenteCobranzaValidaCaso();
    
    //public StatusResponse gerenteCobranzaProgramaFechaFirma();
    
    //public StatusResponse gerenteCobranzaRegistraFechaFirma();

    // microflujo
    
    public SaveResponse<CasoInfo> validarCredito(SaveRequest<CasoInfo> event);
    
    public UpdateResponse<CasoInfo> actualizarCaso(UpdateRequest<CasoInfo> event);
}
