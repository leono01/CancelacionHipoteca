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
package com.gisnet.cancelacion.webservices;

import com.gisnet.cancelacion.core.services.CasoService;
import com.gisnet.cancelacion.core.services.CartaCancelacionService;
import com.gisnet.cancelacion.core.services.StatusCasoService;
import com.gisnet.cancelacion.events.FindByRequest;
import com.gisnet.cancelacion.events.FindResponse;
import com.gisnet.cancelacion.events.SaveRequest;
import com.gisnet.cancelacion.events.SaveResponse;
import com.gisnet.cancelacion.events.UpdateRequest;
import com.gisnet.cancelacion.events.info.CasoInfo;
import com.gisnet.cancelacion.events.info.CartaCancelacionInfo;
import com.gisnet.cancelacion.events.info.StatusCasoInfo;
import com.gisnet.cancelacion.webservices.dto.InfoDeActualizacion;
import com.gisnet.cancelacion.webservices.dto.InfoDeConsulta;
import com.gisnet.cancelacion.webservices.dto.StatusCarta;
import com.gisnet.cancelacion.webservices.dto.StatusCaso;
import com.gisnet.cancelacion.webservices.dto.StatusOperacion;



import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;



/**
 *
 * @author leonel
 */
public class RegistraActualizaYConsultaCaso extends SpringBeanAutowiringSupport {
	@Autowired
    private CasoService service;

	@Autowired
	private StatusCasoService statusService;
	
	@Autowired
	private CartaCancelacionService ccService;
	
    public StatusOperacion registraCaso(String numeroDeCredito,
                                        String numeroDeCaso,
                                        String nombreAcreditado,
                                        String entidad
    ) {

        StatusOperacion so = new StatusOperacion();


        Date fechaDeCreacion = new Date();

        CasoInfo caso = new CasoInfo();        
        
        try{
	        caso.setNombreAcreditado(nombreAcreditado);
	        caso.setNumeroCaso(numeroDeCaso);
	        caso.setNumeroCredito(numeroDeCredito);
	        caso.setFechaCreacion(fechaDeCreacion);
	        caso.setFechaActualizacion(fechaDeCreacion);
	        caso.setProcedeCredito("NO");
	        caso.setEntidad(entidad);
	        
	        int s = 1;
	        
	        FindByRequest clave = new FindByRequest("clave",s);
	        
	        //System.out.println("Clave " + clave);
	        
	        
	        if(clave != null){
	        	
	        	FindResponse<StatusCasoInfo> scresponse = statusService.find(clave);
	        	caso.setStatusCaso(scresponse.getInfo());
	        
	        }
        
        	
        	SaveRequest<CasoInfo> saveRequest = new SaveRequest<>();
        	saveRequest.setInfo(caso);
        	SaveResponse<CasoInfo> save = service.save(saveRequest);
        
        	so.setCodigo(0);
        	so.setDescripcion("Caso registrado con éxito");
        
    	}catch(Exception e){
    		
    		//System.out.println("ERRORRRRRRRRRRR    "+e.getMessage());
    		
    		if(e.getMessage() != null){
	    		if (e.getMessage().equals("Could not open connection; nested exception is org.hibernate.exception.JDBCConnectionException: Could not open connection")){
	    			so.setCodigo(2);
	        		so.setDescripcion("No hay conexión con la base de datos.");
	    		}else{
	    			so.setCodigo(1);
	        		so.setDescripcion("No se hizo registro del caso");
	    		}
    		}
    		else{
    			so.setCodigo(2);
        		so.setDescripcion("No hay conexión con la base de datos.");
    		}
    	}
        
        
        return so;

    }


    public InfoDeActualizacion actualizaCaso(	String  numeroDeCredito,
                                            	String  numeroDeCaso,                                            
                                            	Date    fecha,
                                            	int     status,
                                            	byte[]  cartaDeCancelacionPdf,
                                            	Date    fechaEmisionCarta,
                                            	String  numeroDeFolio,
                                            	String	md5
                                        ) {

        InfoDeActualizacion ida = new InfoDeActualizacion();
        
        /**com.gisnet.cancelacion.wsclient.pms.Pms_Service pmsService = new com.gisnet.cancelacion.wsclient.pms.Pms_Service();
        com.gisnet.cancelacion.wsclient.pms.Pms pmsPort = pmsService.getPmsPort();
        com.gisnet.cancelacion.wsclient.pms.InfoStatusCaso isc = new com.gisnet.cancelacion.wsclient.pms.InfoStatusCaso(); 
        return port.suma(sumador1, sumador2);
         * **/
        
        com.gisnet.cancelacion.wsclient.microflujo.StartFlowBindingPortProxy microService = new com.gisnet.cancelacion.wsclient.microflujo.StartFlowBindingPortProxy();
        com.gisnet.cancelacion.wsclient.microflujo.Input entradas = new com.gisnet.cancelacion.wsclient.microflujo.Input();
        com.gisnet.cancelacion.wsclient.microflujo.Estatus salidas = new com.gisnet.cancelacion.wsclient.microflujo.Estatus();
        
        
        
	    try{
	    //Busca número de caso a actualizar	
	    FindByRequest fbr = new FindByRequest("numeroCaso",numeroDeCaso);
	    FindResponse<CasoInfo> casoresponse = service.find(fbr);
	    
	    //System.out.println("CASO ID:      "+ casoresponse.getInfo().getId());
	    
	    // si existe el caso
	    if(casoresponse.getInfo() != null){
	    	
	    	// busca la clave del status de caso a actualizar
	        FindByRequest clave = new FindByRequest("clave",status);
	        FindResponse<StatusCasoInfo> sciResponse = statusService.find(clave);
	        
	        //System.out.println("CLAVE 1:    "+ sciResponse.getInfo().getId());
	        
	        if(sciResponse.getInfo() != null){
	        	//System.out.println("sci Response"+sciResponse.getInfo());
	        	casoresponse.getInfo().setStatusCaso(sciResponse.getInfo());
	        
	        }
	        
	        casoresponse.getInfo().setFechaActualizacion(fecha);
	        
	        long idCarta = casoresponse.getInfo().getCartaCancelacionId();
	                
	        //System.out.println("CARTA ID:      "+idCarta);
	        
	        if(idCarta != 0){
	        	
		        FindByRequest fbr2 = new FindByRequest(idCarta);
		        FindResponse<CartaCancelacionInfo> response2 = ccService.find(fbr2);
		        
		        response2.getInfo().setCodigoCarta(numeroDeFolio);
		        response2.getInfo().setPdf(cartaDeCancelacionPdf);
		        response2.getInfo().setFechaEmisionCarta(fechaEmisionCarta);
		        
		        UpdateRequest<CartaCancelacionInfo> updateCarta = new UpdateRequest();
		        updateCarta.setInfo(response2.getInfo());
		        ccService.update(updateCarta);
		        //System.out.println("Carta de cancelación actualizada");
	        }
	        else{
	        	
	        	CartaCancelacionInfo cci = new CartaCancelacionInfo();
	        	SaveRequest<CartaCancelacionInfo> saveRequest = new SaveRequest<>();
	        	cci.setCodigoCarta(numeroDeFolio);
	        	cci.setPdf(cartaDeCancelacionPdf);
	        	cci.setFechaEmisionCarta(fechaEmisionCarta);
	        	saveRequest.setInfo(cci);
	        	SaveResponse<CartaCancelacionInfo> save = ccService.save(saveRequest);
	        	
	        	casoresponse.getInfo().setCartaCancelacionId(save.getInfo().getId());
	        	
	        	//System.out.println("Carta de cancelación creada y asociada.");
	    		
	        	/**ida.setCodigo(4);
	    		ida.setDescripcion("No se actualizó la información del caso");
	    		ida.setNumeroDeCaso(numeroDeCaso);
	        	ida.setNumeroDeCredito(numeroDeCredito);**/        	
	    	}
	        
	        UpdateRequest<CasoInfo> update = new UpdateRequest();
	        update.setInfo(casoresponse.getInfo());
	        service.update(update);
	        
	        ida.setCodigo(0);
	        ida.setDescripcion("Se actualizó correctamente el caso.");
	        ida.setNumeroDeCaso(numeroDeCaso);
            ida.setNumeroDeCredito(numeroDeCredito);
	        
	        //System.out.println("IDA CODIGO:   "+ ida.getCodigo());
	        
	        if(ida.getCodigo() == 0){
	        	
	        	try{
	        		
	        		//System.out.println("CLAVE 2:    "+ sciResponse.getInfo().getId());
	        		
		        	if(sciResponse.getInfo().getId() != 0){	        
			        	
			        	entradas.setEstatus(sciResponse.getInfo().getClave());
			        	entradas.setDescripcion(sciResponse.getInfo().getDescripcion());
			        }
		        	
		        	entradas.setNumeroCredito(numeroDeCredito);
		        	entradas.setNumeroCaso(numeroDeCaso);
		        	entradas.setTipoOperacion(4);
		        	
		        	entradas.setCarta(null);
		        	entradas.setFechaEmision(null);
		        	entradas.setNombreAcreditado("");
		        	entradas.setEntidad(null);
		        	
		        	salidas = microService.cambioEstatus(entradas);
		        	//isc = pmsPort.statusCaso(numeroDeCredito, numeroDeCaso, null, status, null, null, null, null, 4);
			        
			        System.out.println("Salida del WS PMS -> status : " + salidas.getEstatus());
			        System.out.println("Salida del WS PMS -> descripción : " + salidas.getDescripcion());
	        	}
	        	
	        	catch(Exception e){
	        		
	    			ida.setCodigo(2);
	        		ida.setDescripcion("Error conexión con web service actualizar status del caso.");
	        		ida.setNumeroDeCaso(numeroDeCaso);
	            	ida.setNumeroDeCredito(numeroDeCredito);
	        		
	        		
		        }
	        }
        
	    }else{
	    	ida.setCodigo(3);
        	ida.setDescripcion("No se existe el caso");
        	ida.setNumeroDeCaso(numeroDeCaso);
            ida.setNumeroDeCredito(numeroDeCredito);
	    }
        
        
    
    	}catch(Exception e){
    		
    		//System.out.println("ACTUALIZA ERROR:               "+e.getMessage());
    		
        	if (e.getMessage().equals("Could not open connection; nested exception is org.hibernate.exception.JDBCConnectionException: Could not open connection")){
    			ida.setCodigo(2);
        		ida.setDescripcion("No hay conexión con la base de datos.");
        		ida.setNumeroDeCaso(numeroDeCaso);
                ida.setNumeroDeCredito(numeroDeCredito);
                //System.out.println(e.toString());
    		}
    		else{
    			
    			ida.setCodigo(4);
            	ida.setDescripcion("No se actualizó la información del caso");
            	ida.setNumeroDeCaso(numeroDeCaso);
                ida.setNumeroDeCredito(numeroDeCredito);
            	//System.out.println(e);
    		}
        	
        }
       
        
        return ida;
    }
    
    
    
    public InfoDeConsulta consultaCaso(
                                       	String 	numeroDeCaso
    ) {
        InfoDeConsulta idc = new InfoDeConsulta();

        try{
        	
        	FindByRequest fbr = new FindByRequest("numeroCaso",numeroDeCaso);        	
            FindResponse<CasoInfo> casoresponse = service.find(fbr);
            
            //System.out.println("CASO ID     " + casoresponse.getInfo().getId());
            
            if(casoresponse.getInfo() != null){  
            	
	            long idCarta = casoresponse.getInfo().getCartaCancelacionId();
	            
	            //System.out.println("CARTA ID  .-.-.-.-.. " + idCarta);
	            
	            if(idCarta != 0){
	            	
		            FindByRequest fbr2 = new FindByRequest(idCarta);
		            FindResponse<CartaCancelacionInfo> responseCarta = ccService.find(fbr2);
		            
		            idc.setNumeroDeFolio(responseCarta.getInfo().getCodigoCarta());
		            idc.setFechaDeEmisionDeCarta(responseCarta.getInfo().getFechaEmisionCarta());
		            idc.setCartaDeCancelacion(responseCarta.getInfo().getPdf());
		            
	            }
	            
	            idc.setFecha(casoresponse.getInfo().getFechaCreacion());            
	            idc.setNombreAcreditado(casoresponse.getInfo().getNombreAcreditado());
	            idc.setEntidad(casoresponse.getInfo().getEntidad());
	            idc.setNumeroDeCaso(casoresponse.getInfo().getNumeroCaso());
	            idc.setNumeroDeCredito(casoresponse.getInfo().getNumeroCredito());
	    
	            if (casoresponse.getInfo().getStatusCaso().getId() != 0){
	            	idc.setStatus(casoresponse.getInfo().getStatusCaso().getClave());
	            	idc.setDescripcionConsulta(casoresponse.getInfo().getStatusCaso().getDescripcion());
	            }
	            idc.setInfoConsulta(0);
	            idc.setDescripcionConsulta("Caso consultado satisfactoriamente.");
            
            }else{
            	idc.setInfoConsulta(6);
            	idc.setDescripcionConsulta("No existe el caso " + numeroDeCaso);
            }
            
        }catch(Exception e){
        	//System.out.println("CONSULTA ERROR:               "+e.getMessage());
    		if(e.getMessage() != null){
	        	if (e.getMessage().equals("Could not open connection; nested exception is org.hibernate.exception.JDBCConnectionException: Could not open connection")){
	    			
	    			idc.setInfoConsulta(2);
	        		idc.setDescripcionConsulta("No hay conexión con la base de datos.");
	                //System.out.println(e.toString());
	    		}
	    		else{
	    			
	    			idc.setInfoConsulta(5);
	            	idc.setDescripcionConsulta("Caso no consultado.");
	        		//System.out.println(e.toString());
	    		}
    		}
    		else{
    			idc.setInfoConsulta(6);
            	idc.setDescripcionConsulta("No existe el caso " + numeroDeCaso);
    		}
    	}
        return idc;
    }
    
    
    
    public StatusCaso estadoDelCaso (int numeroDeCaso){
    	StatusCaso sc = new StatusCaso();
    	StatusCasoInfo sci = null;
    	try{
    	
    		FindByRequest fbr = new FindByRequest("numeroCaso",numeroDeCaso);
    		FindResponse<CasoInfo> response = service.find(fbr);
    		
    		if(response.getInfo() != null){
    			
	    		if(response.getInfo().getStatusCaso() != null){
		    		sci = response.getInfo().getStatusCaso();
		    		sc.setClave(sci.getClave());
					sc.setNombre(sci.getNombre());
					sc.setDescripcion(sci.getDescripcion());
					sc.setFechaActualizacion(response.getInfo().getFechaActualizacion());
    			}
    		}else{
    			sc.setClave(6);
    			sc.setNombre("Error en la consulta del status del caso.");
    			sc.setDescripcion("El número de caso no existe en el sistema.");
    			sc.setFechaActualizacion(new Date());
    		}
    	
    	}catch(Exception e){
    		
    		if (e.getMessage().equals("Could not open connection; nested exception is org.hibernate.exception.JDBCConnectionException: Could not open connection")){
    			
    			sc.setClave(2);
    			sc.setNombre("No hay conexión con la base de datos.");
    			sc.setDescripcion("El tiempo de conexión se ha agotado, verifique la conexión de la base de datos o que el servicio se encuentre iniciado.");
    			sc.setFechaActualizacion(new Date());    			
                //System.out.println(e.toString());
    		}
    		else{
    			sc.setClave(5);
    			sc.setNombre("Caso no consultado.");
    			sc.setDescripcion("El número de caso no se logró consultar en la base de datos.");
    			sc.setFechaActualizacion(new Date());
    		}
    		
    	}
    	return sc;
    }
    
    
    public StatusCarta registrarCarta(	int 	numeroDeCaso,
            							String 	codigoCarta,
            							byte[] 	base64,
            							String 	md5,
            							String 	folio,
            							String 	entidad,
            							Date 	fechaEmision,
            							String	nombreAcreditado,
            							String	nombreNotario,
            							String 	notaria,
            							String	direccion,
            							String	folioEscritura,
            							String	fojaEscritura,
            							String	libroEscritura
    								) {

    	StatusCarta sc = new StatusCarta();

		String mimd5 = "AAA";
		
		//if(mimd5.equals(md5)){
			if(base64 != null){
				//if(numeroDeCaso == (int)numeroDeCaso){      
				
				try{
				
				CartaCancelacionInfo cc = new CartaCancelacionInfo();
				
				cc.setCodigoCarta(codigoCarta);
				cc.setPdf(base64);
				cc.setFolioEscritura(folio);
				cc.setEntidad(entidad);
				cc.setFechaEmisionCarta(fechaEmision);
				cc.setNombreAcreditado(nombreAcreditado);
				cc.setNombreNotario(nombreNotario);
				cc.setNotaria(notaria);
				cc.setDireccion(direccion);
				cc.setFolioEscritura(folioEscritura);
				cc.setFojaEscritura(fojaEscritura);
				cc.setLibroEscritura(libroEscritura);
				
				SaveRequest<CartaCancelacionInfo> saveRequest = new SaveRequest<>();
			    saveRequest.setInfo(cc);
			    SaveResponse<CartaCancelacionInfo> ccSave = ccService.save(saveRequest);
				
			    FindByRequest fbr = new FindByRequest("numeroCaso",numeroDeCaso);
		        FindResponse<CasoInfo> casoresponse = service.find(fbr);
		        
		        casoresponse.getInfo().setCartaCancelacionId(ccSave.getInfo().getId());
			    
				sc.setClave(0);
				sc.setNombre("Carta de cancelacion registrada con éxito en caso");
				sc.setDescripcion("La carta de cancelación se ha guardado exitosamente en el caso");
				
				}catch(Exception e){
					
					sc.setClave(4);
					sc.setNombre("No se registró la carta de cancelación.");
					sc.setDescripcion("La carta de cancelación no se ha guardado exitosamente en el caso");
					//System.out.println(e);
				}
				//}
				/**else{
				sc.setClave(3);
				sc.setNombre("No hay número de caso");
				sc.setDescripcion("No se envió el número de caso asociado a la carta");
				}**/
			}
			else{
				sc.setClave(2);
				sc.setNombre("No se recibió carta");
				sc.setDescripcion("La carta de cancelación no se envió");
			}
		/**}else{
			sc.setClave(1);
			sc.setNombre("Corrupción de carta");
			sc.setDescripcion("La carta de cancelación no se envió correctamente debido a que sufrió una corrupción de datos");
		}**/

		return sc;
    }
    
    public StatusCarta actualizarCarta(	int 	numeroDeCaso,
										String 	codigoCarta,
										byte[] 	base64,
										//String 	md5,
										String 	folio,
										String 	entidad,
										Date 	fechaEmision,
										String	nombreAcreditado,
										String	nombreNotario,
										String 	notaria,
										String	direccion,
										String	folioEscritura,
										String	fojaEscritura,
										String	libroEscritura
		) {
    	
    	StatusCarta sc = new StatusCarta();
    	
    	try{
        	
            FindByRequest fbr = new FindByRequest("numeroCaso",numeroDeCaso);
            FindResponse<CasoInfo> casoresponse = service.find(fbr);
            
            long idCarta = casoresponse.getInfo().getCartaCancelacionId();
            
            //System.out.println(".-.-.-.-.-.-.-.-.-.-.-.-.-.-..-.-.-.-.. " + idCarta);
            
            if(idCarta != 0){
            
	            FindByRequest carta = new FindByRequest(idCarta);
	            FindResponse<CartaCancelacionInfo> cartaresponse = ccService.find(carta);
	            
	            cartaresponse.getInfo().setCodigoCarta(codigoCarta);
	            cartaresponse.getInfo().setPdf(base64);
	            cartaresponse.getInfo().setFolio(folio);
	            cartaresponse.getInfo().setEntidad(entidad);
	            cartaresponse.getInfo().setFechaEmisionCarta(fechaEmision);
	            cartaresponse.getInfo().setNombreAcreditado(nombreAcreditado);
	            cartaresponse.getInfo().setNombreNotario(nombreNotario);
	            cartaresponse.getInfo().setNotaria(notaria);
	            cartaresponse.getInfo().setDireccion(direccion);
	            cartaresponse.getInfo().setFolioEscritura(folioEscritura);
	            cartaresponse.getInfo().setFojaEscritura(fojaEscritura);
	            cartaresponse.getInfo().setLibroEscritura(libroEscritura);
	            
	            UpdateRequest<CartaCancelacionInfo> updateCarta = new UpdateRequest();
		        updateCarta.setInfo(cartaresponse.getInfo());
		        ccService.update(updateCarta);
	            
	            
	            sc.setClave(5);
				sc.setNombre("Se actualizó carta");
				sc.setDescripcion("La carta de cancelación se actualizó correctamente.");
            }else{
            	
            	sc.setClave(6);
				sc.setNombre("No se actualizó carta");
				sc.setDescripcion("La carta de cancelación no se actualizó correctamente.");
            	
            }
            
    	}
    	catch(Exception e){
    		
    		sc.setClave(6);
			sc.setNombre("No se actualizó carta");
			sc.setDescripcion("La carta de cancelación no se actualizó correctamente.");
    		System.out.println(e);
    		
    	}

    	return sc;
    }
    
    public CartaCancelacionInfo consultaCartaCancelacion(int numeroDeCaso){
        
    	FindByRequest fbr = new FindByRequest("numeroCaso",numeroDeCaso);
        FindResponse<CasoInfo> casoresponse = service.find(fbr);
        
        long idCarta = casoresponse.getInfo().getCartaCancelacionId();
        
        System.out.println(".-.-.-.-.-.-.-.-.-.-.-.-.-.-..-.-.-.-.. " + idCarta);
        
        FindResponse<CartaCancelacionInfo> cartaresponse = null;
        
        if(idCarta != 0){
	        
        	FindByRequest carta = new FindByRequest(idCarta);
	        cartaresponse = ccService.find(carta);
        
        }
        return cartaresponse.getInfo();    
    }
    
}