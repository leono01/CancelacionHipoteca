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
import com.gisnet.cancelacion.events.info.ProyectoCancelacionInfo;
import java.util.Date;
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
    
    public ListResponse<CasoInfo> gerenteCobranzaListaCasosPendientes(long empleadoGerenteCobranzaId);

    public MapResponse<List<CasoInfo>> gerenteCobranzaMapaCasosPendientes(long empleadoGerenteCobranzaId);
    
    public UpdateResponse<CasoInfo> gerenteCobranzaActualizaProcedeCredito(long casoId);
    
    public UpdateResponse<CasoInfo> gerenteCobranzaActualizaProcedeCredito(CasoInfo caso);
    
    public StatusResponse gerenteCobranzaProgramaFechaFirma(CasoInfo caso, ProyectoCancelacionInfo proyecto, Date fechaAsignada);
    
    public StatusResponse gerenteCobranzaRegistraFechaFirma(CasoInfo caso, ProyectoCancelacionInfo proyecto, Date fechaFirma);
    
    public FindResponse<CasoInfo> gerenteCobranzaBuscaCasoParaValidar(long empleadoGerenteCobranzaId, String numeroCaso, String numeroCredito);
    
    public StatusResponse gerenteCobranzaAutorizaCaso(long empleadoGerenteCobranzaId, CasoInfo caso, ProyectoCancelacionInfo proyecto);

    // microflujo
    
    public SaveResponse<CasoInfo> validarCredito(SaveRequest<CasoInfo> event);
    
    public UpdateResponse<CasoInfo> actualizarCaso(UpdateRequest<CasoInfo> event);
}
