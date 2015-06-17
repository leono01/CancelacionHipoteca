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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
    
    @Override
    public ListResponse<CasoInfo> gerenteCobranzaListaCasosPendientes(long empleadoGerenteCobranzaId) {
        ListResponse<ProyectoCancelacionInfo> listresponse = proyectoCancelacionPService.list(
                new ListRequest("empleadoId", empleadoGerenteCobranzaId));
        List<CasoInfo> casos = new ArrayList<>();
        for (ProyectoCancelacionInfo info : listresponse.getList()) {
            int clave = info.getStatusProyecto().getClave();
            if (clave == 5 || clave == 8) {
                FindResponse<CasoInfo> find = service.find(
                        new FindByRequest("proyectoCancelacionId", info.getId()));
                casos.add(find.getInfo());
            }
        }
        return new ListResponse<>(casos);
    }
    
    @Override
    public MapResponse<List<CasoInfo>> gerenteCobranzaMapaCasosPendientes(long empleadoGerenteCobranzaId) {
        ListResponse<ProyectoCancelacionInfo> listresponse = proyectoCancelacionPService.list(
                new ListRequest("empleadoId", empleadoGerenteCobranzaId));
        List<CasoInfo> casosRevizar = new ArrayList<>();
        List<CasoInfo> casosEspera = new ArrayList<>();
        for (ProyectoCancelacionInfo info : listresponse.getList()) {
            int clave = info.getStatusProyecto().getClave();
            if (clave == 5) {
                FindResponse<CasoInfo> find = service.find(
                        new FindByRequest("proyectoCancelacionId", info.getId()));
                casosRevizar.add(find.getInfo());
            } else if (clave == 8) {
                FindResponse<CasoInfo> find = service.find(
                        new FindByRequest("proyectoCancelacionId", info.getId()));
                casosEspera.add(find.getInfo());
            }
        }
        Map<String, List<CasoInfo>> casos = new HashMap<>();
        casos.put("casosRevizar", casosRevizar);
        casos.put("casosEspera", casosEspera);
        
        return new MapResponse<>(casos);
    }

    @Override
    public UpdateResponse<CasoInfo> gerenteCobranzaActualizaProcedeCredito(long casoId) {
        return gerenteCobranzaActualizaProcedeCredito( service.find(new FindByRequest(casoId)).getInfo() );
    }

    @Override
    public UpdateResponse<CasoInfo> gerenteCobranzaActualizaProcedeCredito(CasoInfo caso) {
        String antes = caso.getProcedeCredito();
        SaveResponse<CasoInfo> validarCredito = microflujo.validarCredito(new SaveRequest<>(caso));
        caso = validarCredito.getInfo();
        if (!antes.equals(caso.getProcedeCredito())) {
            service.update(new UpdateRequest<>(caso));
        }
        return new UpdateResponse<>(caso);
    }
    
    @Override
    public StatusResponse gerenteCobranzaAutorizaCaso(long empleadoGerenteCobranzaId, CasoInfo caso, ProyectoCancelacionInfo proyecto) {
        // TODO revisar
        if (caso.getNotarioId() > 0 && caso.getProyectoCancelacionId() > 0) {
            if (proyecto.getStatusProyecto().getClave() == 2) {
                //Caso asignado a notario, en espera de aceptacion o rechazo.
                return new StatusResponse(0);
            }
            if (proyecto.getEmpleadoId() > 0 && proyecto.getEmpleadoId() != empleadoGerenteCobranzaId) {
                //Caso asignado a otro jefe de cobranza.
                return new StatusResponse(0);
            }
        } else if (caso.getProyectoCancelacionId() > 0) {
            if (proyecto.isAutorizado()) {
                //Proyecto previamente autorizado.
                return new StatusResponse(0);
            }
            if (proyecto.getEmpleadoId() > 0 && proyecto.getEmpleadoId() != empleadoGerenteCobranzaId) {
                //Caso asignado a otro jefe de cobranza.
                return new StatusResponse(0);
            }
        }
        // TODO revisar

        // autoriza caso con notario infonavit
        proyecto.setEmpleadoId(empleadoGerenteCobranzaId);
        proyecto.setAutorizado(true);
        proyecto.setFechaAutorizacion(new Date());
        proyecto.setStatusProyecto(statusProyectoPService.find(
                new FindByRequest("clave", 6)).getInfo());
        UpdateResponse<ProyectoCancelacionInfo> update1 = proyectoCancelacionPService.update(
                new UpdateRequest<>(proyecto));
        proyecto = update1.getInfo();

        caso.setProyectoCancelacionId(proyecto.getId());
        FindResponse<StatusCasoInfo> find = statusCasoPService.find(
                new FindByRequest("clave", 13));
        caso.setStatusCaso(find.getInfo());
        service.update(new UpdateRequest<>(caso));
        //cas

        return new StatusResponse(1);
    }
    
    @Override
    public StatusResponse gerenteCobranzaProgramaFechaFirma(CasoInfo caso, ProyectoCancelacionInfo proyecto, Date fechaAsignada) {
        FindResponse<StatusCasoInfo> find = statusCasoPService.find(
                new FindByRequest("clave", 14));
        caso.setStatusCaso(find.getInfo());
        service.update(new UpdateRequest<>(caso));

        FindResponse<StatusProyectoInfo> find2 = statusProyectoPService.find(
                new FindByRequest("clave", 8));
        proyecto.setStatusProyecto(find2.getInfo());
        proyecto.setFechaAsignadaParaFirma(fechaAsignada);

        proyectoCancelacionPService.update(new UpdateRequest<>(proyecto));
        
        return new StatusResponse(1);
    }
    
    @Override
    public StatusResponse gerenteCobranzaRegistraFechaFirma(CasoInfo caso, ProyectoCancelacionInfo proyecto, Date fechaFirma) {
        FindResponse<StatusCasoInfo> find = statusCasoPService.find(
                new FindByRequest("clave", 15));
        caso.setStatusCaso(find.getInfo());
        service.update(new UpdateRequest<>(caso));

        // TODO guarda fecha firma con notario / actualizacion de estados

        FindResponse<StatusProyectoInfo> find2 = statusProyectoPService.find(
                new FindByRequest("clave", 9));
        proyecto.setStatusProyecto(find2.getInfo());
        proyecto.setFechaFirmaNotario(fechaFirma);

        proyectoCancelacionPService.update(new UpdateRequest<>(proyecto));
        
        return new StatusResponse(1);
    }
    
    @Override
    public FindResponse<CasoInfo> gerenteCobranzaBuscaCasoParaValidar(long empleadoGerenteCobranzaId, String numeroCaso, String numeroCredito) {
        FindResponse<CasoInfo> find1 = service.find(
                new FindByRequest("numeroCaso", numeroCaso));
        CasoInfo caso = find1.getInfo();
        if (caso == null) {
            return new FindResponse<>(null);
        }
        if (!caso.getNumeroCredito().equals(numeroCredito)) {
            return new FindResponse<>(null);
        }
        if (caso.getNotarioId() > 0) {
            if (caso.getProyectoCancelacionId() > 0) {
                FindResponse<ProyectoCancelacionInfo> find = proyectoCancelacionPService.find(
                        new FindByRequest(caso.getProyectoCancelacionId()));
                ProyectoCancelacionInfo info = find.getInfo();
                if (info.getStatusProyecto().getClave() != 11) {
                    if (info.getEmpleadoId() > 0 && info.getEmpleadoId() != empleadoGerenteCobranzaId) {
                        return new FindResponse<>(null);
                    }
                }
            }
        }
        return new FindResponse<>(caso);
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
