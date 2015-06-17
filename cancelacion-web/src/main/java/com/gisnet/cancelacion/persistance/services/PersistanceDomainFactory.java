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
package com.gisnet.cancelacion.persistance.services;

import com.gisnet.cancelacion.events.info.*;
import com.gisnet.cancelacion.persistance.domain.*;
import com.gisnet.cancelacion.persistance.repository.*;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author marco-g8
 */
public class PersistanceDomainFactory {
    
    @Autowired
    private BitacoraCasoRepository bitacoraCasoRepository;
    @Autowired
    private BitacoraProyectoRepository bitacoraProyectoRepository;
    @Autowired
    private BitacoraRPPRepository bitacoraRPPRepository;
    @Autowired
    private BitacoraRegistroNotarioRepository bitacoraRegistroNotarioRepository;
    @Autowired
    private CancelacionArchivoRepository cancelacionArchivoRepository;
    @Autowired
    private CartaCancelacionRepository cartaCancelacionRepository;
    @Autowired
    private CasoRepository casoRepository;
    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Autowired
    private EntidadRepository entidadRepository;
    @Autowired
    private MunicipioRepository municipioRepository;
    @Autowired
    private NotarioRepository notarioRepository;
    @Autowired
    private ProyectoCancelacionRepository proyectoCancelacionRepository;
    @Autowired
    private ProyectoRPPRepository proyectoRPPRepository;
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private StatusCasoRepository statusCasoRepository;
    @Autowired
    private StatusProyectoRepository statusProyectoRepository;
    @Autowired
    private StatusRPPRepository statusRPPRepository;
    @Autowired
    private StatusNotarioRepository statusNotarioRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    public BitacoraCaso buildBitacoraCaso(BitacoraCasoInfo info) {
        BitacoraCaso b = new BitacoraCaso();
        b.setId(info.getId());
        if (info.getBitacoraCasoId() > 0) {
            b.setBitacoraCaso(casoRepository.findOne(info.getBitacoraCasoId()));
        }
        b.setFechaActualizacion(info.getFechaActualizacion());
        if (info.getStatusCaso() != null) {
            if (info.getStatusCaso().getId() > 0) {
                b.setStatusCaso(statusCasoRepository.findOne(info.getStatusCaso().getId()));
            }
            else if (info.getStatusCaso().getNombre() != null) {
                throw new UnsupportedOperationException("no soportado aun");
            }
        }
        b.setUsuario(info.getUsuario());
        return b;
    }
    
    public BitacoraProyecto buildBitacoraProyecto(BitacoraProyectoInfo info) {
        BitacoraProyecto b = new BitacoraProyecto();
        b.setId(info.getId());
        b.setFechaActualizacion(info.getFechaActualizacion());
        if (info.getProyectoCancelacionId() > 0) {
            b.setProyectoCancelacion(proyectoCancelacionRepository.findOne(info.getProyectoCancelacionId()));
        }
        if (info.getStatusProyecto() != null) {
            if (info.getStatusProyecto().getId() > 0) {
                b.setStatusProyecto(statusProyectoRepository.findOne(info.getStatusProyecto().getId()));
            }
            else if (info.getStatusProyecto().getNombre() != null) {
                throw new UnsupportedOperationException("no soportado aun");
            }
        }
        b.setUsuario(info.getUsuario());
        return b;
    }
    
    public BitacoraRPP buildBitacoraRPP(BitacoraRPPInfo info) {
        BitacoraRPP b = new BitacoraRPP();
        b.setId(info.getId());
        b.setFechaActualizacion(info.getFechaActualizacion());
        if (info.getProyectoRPPId() > 0) {
            b.setProyectoRPP(proyectoRPPRepository.findOne(info.getId()));
        }
        if (info.getStatusRPP()!= null) {
            if (info.getStatusRPP().getId() > 0) {
                b.setStatusRPP(statusRPPRepository.findOne(info.getStatusRPP().getId()));
            }
            else if (info.getStatusRPP().getNombre() != null) {
                throw new UnsupportedOperationException("no soportado aun");
            }
        }
        b.setUsuario(info.getUsuario());
        return b;
    }
    
    public BitacoraRegistroNotario buildBitacoraRegistroNotario(BitacoraRegistroNotarioInfo info) {
        BitacoraRegistroNotario b = new BitacoraRegistroNotario();
        b.setId(info.getId());
        b.setFechaActualizacion(info.getFechaActualizacion());
        if (info.getNotarioId() > 0) {
            b.setNotario(notarioRepository.findOne(info.getNotarioId()));
        }
        if (info.getStatusNotario() != null) {
            if (info.getStatusNotario().getId() > 0) {
                b.setStatusNotario(statusNotarioRepository.findOne(info.getStatusNotario().getId()));
            }
            else if (info.getStatusNotario().getClave() > 0) {
                throw new UnsupportedOperationException("no soportado aun");
            }
        }
        b.setUsuario(info.getUsuario());
        return b;
    }
    
    public CancelacionArchivo buildCancelacionArchivo(CancelacionArchivoInfo info) {
        CancelacionArchivo b = new CancelacionArchivo();
        b.setId(info.getId());
        b.setArchivo(info.getArchivo());
        b.setNombre(info.getNombre());
        if (info.getProyectoCancelacionId() > 0) {
            b.setProyectoCancelacion(proyectoCancelacionRepository.findOne(info.getProyectoCancelacionId()));
        }
        b.setMimetype(info.getMimetype());
        return b;
    }
    
    public CartaCancelacion buildCartaCancelacion(CartaCancelacionInfo info) {
        CartaCancelacion b = new CartaCancelacion();
        b.setId(info.getId());
        b.setCodigoCarta(info.getCodigoCarta());
        b.setPdf(info.getPdf());
        b.setXml(info.getXml());
        b.setFechaEmisionCarta(info.getFechaEmisionCarta());
        b.setFolio(info.getFolio());
        b.setEntidad(info.getEntidad());
        b.setNombreAcreditado(info.getNombreAcreditado());
        b.setNombreNotario(info.getNombreNotario());
        b.setNotaria(info.getNotaria());
        b.setDireccion(info.getDireccion());
        b.setFolioEscritura(info.getFolioEscritura());
        b.setFojaEscritura(info.getFojaEscritura());
        b.setLibroEscritura(info.getLibroEscritura());
        
        return b;
    }
    
    public Caso buildCaso(CasoInfo info) {
        Caso b = new Caso();
        b.setId(info.getId());
        if (info.getCartaCancelacionId() > 0) {
            b.setCartaCancelacion(cartaCancelacionRepository.findOne(info.getCartaCancelacionId()));
        }
        b.setFechaCierre(info.getFechaCierre());
        b.setFechaCreacion(info.getFechaCreacion());
        b.setNombreAcreditado(info.getNombreAcreditado());
        if (info.getNotarioId() > 0) {
            b.setNotario(notarioRepository.findOne(info.getNotarioId()));
        }
        b.setNumeroCaso(info.getNumeroCaso());
        b.setNumeroCredito(info.getNumeroCredito());
        if (info.getProyectoCancelacionId() > 0) {
            b.setProyectoCancelacion(proyectoCancelacionRepository.findOne(info.getProyectoCancelacionId()));
        }
        if (info.getProyectoRPPId() > 0) {
            b.setProyectoRPP(proyectoRPPRepository.findOne(info.getProyectoRPPId()));
        }
        if (info.getStatusCaso() != null) {
            if (info.getStatusCaso().getId() > 0) {
                b.setStatusCaso(statusCasoRepository.findOne(info.getStatusCaso().getId()));
            }
            else if (info.getStatusCaso().getNombre() != null) {
                throw new UnsupportedOperationException("no soportado aun");
            }
        }
        b.setFechaActualizacion(info.getFechaActualizacion());
        b.setProcedeCredito(info.getProcedeCredito());
        b.setEntidad(info.getEntidad());
        b.setDescripcionCredito(info.getDescripcionCredito());
        b.setSaldoCredito(info.getSaldoCredito());
        b.setFechaLiquidacionCredito(info.getFechaLiquidacionCredito());
        b.setSaldoCreditoVSM(info.getSaldoCreditoVSM());
        return b;
    }
    
    public Empleado buildEmpleado(EmpleadoInfo info) {
        Empleado b = new Empleado();
        b.setId(info.getId());
        b.setApellidoMaterno(info.getApellidoMaterno());
        b.setApellidoPaterno(info.getApellidoPaterno());
        b.setClave(info.getClave());
        b.setDelegacion(info.getDelegacion());
        b.setNombre(info.getNombre());
        b.setSubdireccion(info.getSubdireccion());
        if (info.getUsuarioId() > 0) {
            b.setUsuario(usuarioRepository.findOne(info.getUsuarioId()));
        }
        return b;
    }
    
    public Entidad buildEntidad(EntidadInfo info) {
        Entidad b = new Entidad();
        b.setId(info.getId());
        b.setClave(info.getClave());
        b.setConvenio(info.isConvenio());
        b.setNombre(info.getNombre());
        return b;
    }
    
    public Municipio buildMunicipio(MunicipioInfo info) {
        Municipio b = new Municipio();
        b.setId(info.getId());
        if (info.getEntidadId() > 0) {
            b.setEntidad(entidadRepository.findOne(info.getEntidadId()));
        }
        b.setNombre(info.getNombre());
        return b;
    }
    
    public Notario buildNotario(NotarioInfo info) {
        Notario b = new Notario();
        b.setId(info.getId());
        b.setCalleNotaria(info.getCalleNotaria());
        b.setCodigoNotario(info.getCodigoNotario());
        b.setCodigoPostalNotaria(info.getCodigoPostalNotaria());
        b.setColoniaNotaria(info.getColoniaNotaria());
        b.setEmail(info.getEmail());
        if (info.getEntidadId() > 0) {
            b.setEntidad(entidadRepository.findOne(info.getEntidadId()));
        }
        if (info.getMunicipioId() > 0) {
            b.setMunicipio(municipioRepository.findOne(info.getMunicipioId()));
        }
        b.setNombre(info.getNombre());
        b.setNotariaNumero(info.getNotariaNumero());
        b.setNumeroCalle(info.getNumeroCalle());
        b.setTelefono(info.getTelefono());
        if (info.getUsuarioId() > 0) {
            b.setUsuario(usuarioRepository.findOne(info.getUsuarioId()));
        }
        b.setRfc(info.getRfc());
        b.setCurp(info.getCurp());
        b.setHabilitado(info.isHabilitado());
        b.setConvenio(info.getConvenio());
        b.setEntidad2(info.getEntidad2());
        return b;
    }
    
    public ProyectoCancelacion buildProyectoCancelacion(ProyectoCancelacionInfo info) {
        ProyectoCancelacion b = new ProyectoCancelacion();
        b.setId(info.getId());
        b.setAutorizado(info.isAutorizado());
        if (info.getEmpleadoId() > 0) {
            b.setEmpleado(empleadoRepository.findOne(info.getEmpleadoId()));
        }
        b.setFechaAutorizacion(info.getFechaAutorizacion());
        b.setFechaCierre(info.getFechaCierre());
        b.setFechaCreacion(info.getFechaCreacion());
        b.setFechaRevision(info.getFechaRevision());
        b.setMotivoRechazo(info.getMotivoRechazo());
        if (info.getStatusProyecto() != null) {
            if (info.getStatusProyecto().getId() > 0) {
                b.setStatusProyecto(statusProyectoRepository.findOne(info.getStatusProyecto().getId()));
            }
            else if (info.getStatusProyecto().getClave() > 0) { // CLAVE
                b.setStatusProyecto(statusProyectoRepository.findByClave(info.getStatusProyecto().getClave()));
            }
        }
        b.setFechaAsignadaParaFirma(info.getFechaAsignadaParaFirma());
        b.setFechaFirmaNotario(info.getFechaFirmaNotario());
        return b;
    }
    
    public ProyectoRPP buildProyectoRPP(ProyectoRPPInfo info) {
        ProyectoRPP b = new ProyectoRPP();
        b.setId(info.getId());
        if (info.getEntidadId() > 0) {
            b.setEntidad(entidadRepository.findOne(info.getEntidadId()));
        }
        b.setFechaIngreso(info.getFechaIngreso());
        b.setFoja(info.getFoja());
        b.setFolioRealElectronico(info.getFolioRealElectronico());
        b.setFolioTramiteRPP(info.getFolioTramiteRPP());
        b.setLibro(info.getLibro());
        b.setObservaciones(info.getObservaciones());
        b.setPartida(info.getPartida());
        if (info.getStatusRPPId() > 0) {
            b.setStatusRPP(statusRPPRepository.findOne(info.getStatusRPPId()));
        }
        b.setTomo(info.getTomo());
        if (info.getUsuarioId() > 0) {
            b.setUsuario(usuarioRepository.findOne(info.getUsuarioId()));
        }
        b.setVolumen(info.getVolumen());
        return b;
    }
    
    public Rol builRol(RolInfo info) {
        Rol b = new Rol();
        b.setId(info.getId());
        b.setDescripcion(info.getDescripcion());
        b.setNombre(info.getNombre());
        return b;
    }
    
    public StatusCaso buildStatusCaso(StatusCasoInfo info) {
        StatusCaso b = new StatusCaso();
        b.setId(info.getId());
        b.setClave(info.getClave());
        b.setDescripcion(info.getDescripcion());
        b.setNombre(info.getNombre());
        return b;
    }
    
    public StatusProyecto buildStatusProyecto(StatusProyectoInfo info) {
        StatusProyecto b = new StatusProyecto();
        b.setId(info.getId());
        b.setClave(info.getClave());
        b.setDescripcion(info.getDescripcion());
        b.setNombre(info.getNombre());
        return b;
    }
    
    public StatusRPP buildStatusRPP(StatusRPPInfo info) {
        StatusRPP b = new StatusRPP();
        b.setId(info.getId());
        b.setClave(info.getClave());
        b.setDescripcion(info.getDescripcion());
        b.setNombre(info.getNombre());
        return b;
    }
    
    public StatusNotario buildStatusNotario(StatusNotarioInfo info) {
        StatusNotario b = new StatusNotario();
        b.setId(info.getId());
        b.setClave(info.getClave());
        b.setNombre(info.getNombre());
        b.setDescripcion(info.getDescripcion());
        return b;
    }
    
    public Usuario buildUsuario(UsuarioInfo info) {
        Usuario b = new Usuario();
        b.setId(info.getId());
        b.setActivo(info.isActivo());
        b.setBloqueado(info.isBloqueado());
        b.setContrasena(info.getContrasena());
        b.setFechaAlta(info.getFechaAlta());
        b.setFechaBaja(info.getFechaBaja());
        b.setNombreUsuario(info.getNombreUsuario());
        Set<Rol> roles = new HashSet<>();
        for (String r : info.getRoles()) {
            roles.add(rolRepository.findByNombre(r));
        }
        b.setRoles(roles);
        return b;
    }
    
}

