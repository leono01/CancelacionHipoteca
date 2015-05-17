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
package com.gisnet.cancelacion.web.controller;

import com.gisnet.cancelacion.core.services.CancelacionArchivoService;
import com.gisnet.cancelacion.core.services.CartaCancelacionService;
import com.gisnet.cancelacion.core.services.CasoService;
import com.gisnet.cancelacion.core.services.EmpleadoService;
import com.gisnet.cancelacion.core.services.NotarioService;
import com.gisnet.cancelacion.core.services.ProyectoCancelacionService;
import com.gisnet.cancelacion.core.services.StatusProyectoService;
import com.gisnet.cancelacion.core.services.UsuarioService;
import com.gisnet.cancelacion.events.*;
import com.gisnet.cancelacion.events.info.*;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author marco-g8
 */
@Controller
public class JCobranzaController {

    @Autowired
    private CancelacionArchivoService cancelacionArchivoService;
    @Autowired
    private CartaCancelacionService cartaCancelacionService;
    @Autowired
    private CasoService casoService;
    @Autowired
    private EmpleadoService empleadoService;
    @Autowired
    private NotarioService notarioService;
    @Autowired
    private ProyectoCancelacionService proyectoCancelacionService;
    @Autowired
    private StatusProyectoService statusProyectoService;
    @Autowired
    private UsuarioService usuarioService;

    public String index(Model model, Principal principal) {
        FindResponse<EmpleadoInfo> findresponse = empleadoService.find(
                new FindByRequest("nombreUsuario", principal.getName()));
        EmpleadoInfo empleado = findresponse.getInfo();
        if (empleado == null) {
            // ERROR empleado no encontrado
            model.addAttribute("casos", new ArrayList<CasoInfo>());
            return "/notario/index";
        }
        ListResponse<ProyectoCancelacionInfo> listresponse = proyectoCancelacionService.list(
                new ListRequest("empleadoId", empleado.getId()));
        List<CasoInfo> casos = new ArrayList<>();
        for (ProyectoCancelacionInfo info : listresponse.getList()) {
            if (info.getStatusProyecto().getClave() == 5) {
                FindResponse<CasoInfo> find = casoService.find(
                        new FindByRequest("proyectoCancelacionId", info.getId()));
                casos.add(find.getInfo());
            }
        }
        model.addAttribute("casos", casos);
        return "/jcobranza/index";
    }

    @RequestMapping(value = "/cobranza/caso/{numeroCaso}", method = RequestMethod.GET)
    public String ver(@PathVariable int numeroCaso, Model model) {
        FindResponse<CasoInfo> find = casoService.find(new FindByRequest("numeroCaso", numeroCaso));
        CasoInfo caso = find.getInfo();
        model.addAttribute("caso", caso);

        String validaCredito = casoService.validaCredito(caso);
        model.addAttribute("validaCredito", validaCredito);

        ListResponse<CancelacionArchivoInfo> listresp = cancelacionArchivoService.list(new ListRequest());
        model.addAttribute("archivos", listresp.getList());

        return "/jcobranza/ver";
    }

    @RequestMapping(value = "/archivoss/{id}/{filename}", method = RequestMethod.GET, produces = MediaType.ALL_VALUE)
    public @ResponseBody
    byte[] descargar(@PathVariable long id, @PathVariable String filename) {
        FindResponse<CancelacionArchivoInfo> file = cancelacionArchivoService.findBy(new FindByRequest(id));
        CancelacionArchivoInfo info = file.getInfo();
        return info.getArchivo();
    }

    @RequestMapping(value = "/cobranza/caso/{numeroCaso}/fechafirma", method = RequestMethod.GET)
    public String asignarFechaFirmaNotario(@PathVariable int numeroCaso, Model model) {
        model.addAttribute("numeroCaso", numeroCaso);
        return "/jcobranza/firma";
    }

    @RequestMapping(value = "/cobranza/caso/{numeroCaso}/fechafirma", method = RequestMethod.POST)
    public String guardaFechaFirmaNotario(@PathVariable int numeroCaso, Model model) {
        FindResponse<CasoInfo> find = casoService.find(new FindByRequest("numeroCaso", numeroCaso));
        CasoInfo caso = find.getInfo();
        model.addAttribute("caso", caso);

        // TODO guarda fecha firma con notario / actualizacion de estados
        return "/jcobranza/firma";
    }
}
