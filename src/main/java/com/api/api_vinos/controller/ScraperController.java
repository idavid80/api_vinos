package com.api.api_vinos.controller;


import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.api_vinos.entity.DatosTecnicosDTO;
import com.api.api_vinos.entity.VinoDTO;
import com.api.api_vinos.service.ScraperService;
import com.api.api_vinos.service.ScraperServiceImpl;



@RestController
@RequestMapping(path = "/")
public class ScraperController {

    @Autowired
    ScraperService scraperService;
    ScraperServiceImpl scraperServiceImpl;

    @GetMapping(path = "/scraper/prueba-scrapper")
    public String comprobarConexion() {
        return "Controlador funcionando correctamente";
    }
    @GetMapping(path = "/scraper/vino{pagina}")
    public Set<VinoDTO> getVinoPorPagina(@PathVariable String pagina) {
    	
        return  scraperService.getVinoPorPagina(pagina);
    }
    
    @GetMapping(path = "scraper/obtener_vinos{desde}{hasta}")
    public Set<VinoDTO> getVinoDesdeHasta(@PathVariable String desde, @PathVariable String hasta) {

        return  scraperService.getVinoDesdeHasta(desde, hasta);
    }
    // Obtiene todo los vinos que hay en el html
    @GetMapping(path = "/todos_los_vinos")
    public Set<VinoDTO> getTodosLosVino() {

        return  scraperService.getTodosLosVino();
    }
    // Inserta los vinos por pagina del html en la base de datos
    @GetMapping(path = "/scraper/insertar-vinos{pagina}")
    public int insertaVinosPorPagHTML(String pagina) {
    	return  scraperService.insertaVinosPorPagHTML(pagina);
    	
    }
    // Inserta los datos tecnicos de los vinos que existen en la base de datos
    @GetMapping(path = "/scraper/insertar-datos-tecnicos")
    int insertaDatosTecnicosBD(){
    	return scraperService.insertaDatosTecnicosBD();
    }
    // Muestra los datos tecnicos de los vinos que hay en la pagina del html
    @GetMapping(path = "/scraper/datos-tecnicos")
    List<DatosTecnicosDTO> getDatosTecnicosDTOPorPagina(){
    	return scraperService.getListDatosTecnicosDTO();
    }
    
    @GetMapping(path = "/scraper/guardar-imagen")
    String guardarImagen(){
    	return scraperService.guardarImagen();
    };
    
	/////////////////////////////// PARTE DE PROGRAMACION DE PROCESOS/////////////////////////////////////////////////////////////
	    
	@ResponseBody
	@GetMapping("/insertarVinoConTimerTask")
	public void insertarVinoConTimerTask() {
		
		// Crear una instancia de Timer
        Timer timer = new Timer();

        // Crear una instancia de TimerTask
        TimerTask tarea = new MiTimerTask();

        // Programar la tarea para ejecutarse cada 5000 milisegundos (5 segundos)
        timer.schedule(tarea, 0, 20000);

		//scraperService.insertarVinoConTimerTask(pagina);
	}
	
	public class MiTimerTask extends TimerTask {
        @Override
        public void run() {
        	String pagina = "https://wineissocial.com/1719-vinos?page=3";
        	scraperService.insertaVinosPorPagHTMLTimerTask(pagina);
        }
    }
	
	/////////////////////////////// PARTE DE PROGRAMACION DE PROCESOS/////////////////////////////////////////////////////////////
}