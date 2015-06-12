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
import com.gisnet.cancelacion.events.info.StatusCasoInfo;
import com.gisnet.cancelacion.events.info.StatusProyectoInfo;
import com.gisnet.cancelacion.persistance.services.CancelacionArchivoPersistanceService;
import com.gisnet.cancelacion.persistance.services.CasoPersistanceService;
import com.gisnet.cancelacion.persistance.services.ProyectoCancelacionPersistanceService;
import com.gisnet.cancelacion.persistance.services.StatusCasoPersistanceService;
import com.gisnet.cancelacion.persistance.services.StatusProyectoPersistanceService;
import com.gisnet.cancelacion.wsclient.microflujo.ClienteMicroflujoService;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author leonel
* @author marco-g8
*/

public class CasoServiceHandler implements CasoService {
	
    @Autowired
    private CancelacionArchivoPersistanceService cancelacionArchivoPService;
    @Autowired
    private CasoPersistanceService service;
    @Autowired
    private ProyectoCancelacionPersistanceService proyectoCancelacionPService;
    @Autowired
    private StatusCasoPersistanceService statusCasoPService;
    @Autowired
    private StatusProyectoPersistanceService statusProyectoPService;

    @Autowired
    private ClienteMicroflujoService microflujo;

    public CasoServiceHandler() {
    }

    @Override
    public FindResponse<CasoInfo> find(FindByIdRequest event) {
        return service.find(new FindByRequest(event.getId()));
    }

    @Override
    public FindResponse<CasoInfo> find(FindByRequest event) {
        return service.find(event);
    }

    @Override
    public ListResponse<CasoInfo> list(ListRequest event) {
        return service.list(event);
    }

    @Override
    public SaveResponse<CasoInfo> save(SaveRequest<CasoInfo> event) {
        return service.save(event);
    }

    @Override
    public UpdateResponse<CasoInfo> update(UpdateRequest<CasoInfo> event) {
        return service.update(event);
    }

    @Override
    public DeleteResponse<CasoInfo> delete(DeleteRequest event) {
        return service.delete(event);
    }

    // notario infonavit

    @Override
    public ListResponse<CasoInfo> notarioInfonavitListaCasosPendientes(long notarioId) {
        ListResponse<CasoInfo> list1 = service.list(
                new ListRequest("notarioId", notarioId));

        // casos pendientes, estado 8
        // casos en espera de firma, estado 14
        Iterator<CasoInfo> iterator = list1.getList().iterator();
        while (iterator.hasNext()) {
            CasoInfo next = iterator.next();
            int clave = next.getStatusCaso().getClave();
            if (clave != 8 || clave != 14) {
                iterator.remove();
            }
        }
        
        return new ListResponse<>(list1.getList());
    }

    @Override
    public ListResponse<CasoInfo> notarioInfonavitListaCasosAsignados(long notarioId) {
        ListResponse<CasoInfo> list1 = service.list(
                new ListRequest("notarioId", notarioId));
        return new ListResponse<>(list1.getList());
    }

    @Override
    public StatusResponse notarioInfonavitAceptaCaso(long notarioId, long casoId, long empleadoGerenteCobranzaId, List<CancelacionArchivoInfo> archivosCancelacion) {
        // crea proyecto cancelacion
        ProyectoCancelacionInfo proyecto = new ProyectoCancelacionInfo();
        proyecto.setEmpleadoId(empleadoGerenteCobranzaId);
        FindResponse<StatusProyectoInfo> find2 = statusProyectoPService.find(
                new FindByRequest("clave", 5));
        proyecto.setStatusProyecto(find2.getInfo());

        SaveResponse<ProyectoCancelacionInfo> saved = proyectoCancelacionPService.save(
                new SaveRequest<>(proyecto));
        proyecto = saved.getInfo();

        // guarda archivos
        for (CancelacionArchivoInfo archivo : archivosCancelacion) {
            archivo.setProyectoCancelacionId(proyecto.getId());
            cancelacionArchivoPService.save(new SaveRequest<>(archivo));
        }

        // Actualiza caso
        FindResponse<CasoInfo> find = service.find(new FindByRequest(casoId));
        CasoInfo caso = find.getInfo();
        
        caso.setNotarioId(notarioId);
        caso.setProyectoCancelacionId(proyecto.getId());
        FindResponse<StatusCasoInfo> find1 = statusCasoPService.find(
                new FindByRequest("clave", 11));
        caso.setStatusCaso(find1.getInfo());
        service.update(new UpdateRequest<>(caso));
        
        return new StatusResponse(1);
    }

    @Override
    public StatusResponse notarioInfonavitRechazaCaso(long notarioId, long casoId, String motivoRechazo) {
        FindResponse<CasoInfo> find = service.find(new FindByRequest(casoId));
        CasoInfo caso = find.getInfo();

        // crea proyecto cancelacion
        ProyectoCancelacionInfo proyecto = new ProyectoCancelacionInfo();
        FindResponse<StatusProyectoInfo> findstatus = statusProyectoPService.find(
                new FindByRequest("clave", 11));
        proyecto.setStatusProyecto(findstatus.getInfo());
        proyecto.setMotivoRechazo(motivoRechazo);

        SaveResponse<ProyectoCancelacionInfo> saved = proyectoCancelacionPService.save(
                new SaveRequest<>(proyecto));
        proyecto = saved.getInfo();

        // actualiza caso
        caso.setProyectoCancelacionId(proyecto.getId());
        caso.setNotarioId(0l);
        FindResponse<StatusCasoInfo> find1 = statusCasoPService.find(
                new FindByRequest("clave", 17));
        caso.setStatusCaso(find1.getInfo());

        service.update(new UpdateRequest<>(caso));
        
        return new StatusResponse(1);
    }
    
    // gerente de cobranza
    
    public StatusResponse gerenteCobranzaValidaCaso() {
        throw new UnsupportedOperationException("no soportado aun");
    }
    
    public StatusResponse gerenteCobranzaProgramaFechaFirma() {
        throw new UnsupportedOperationException("no soportado aun");
    }
    
    public StatusResponse gerenteCobranzaRegistraFechaFirma() {
        throw new UnsupportedOperationException("no soportado aun");
    }

    
    @Override
    public SaveResponse<CasoInfo> validarCredito(SaveRequest<CasoInfo> event) {
        return microflujo.validarCredito(event);
    }

    @Override
    public UpdateResponse<CasoInfo> actualizarCaso(UpdateRequest<CasoInfo> event) {
        return microflujo.actualizarCaso(event);
    }
    
    

}
