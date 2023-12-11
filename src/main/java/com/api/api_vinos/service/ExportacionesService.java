package com.api.api_vinos.service;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import com.api.api_vinos.entity.Vino_DatosTecnicos_aux;
import com.api.api_vinos.repository.ConsultasJDBCRepository;

@Service
public class ExportacionesService {

	@Autowired
	protected ConsultasJDBCRepository bbddRepositorio;

	public void generarXML() {
			
		ArrayList<Vino_DatosTecnicos_aux> listaVinos = new ArrayList<Vino_DatosTecnicos_aux>();
		listaVinos = bbddRepositorio.sacarDatosVino();
					
		
		 try {
			 
			 // Creo una instancia de DocumentBuilderFactory
			 DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			 // Creo un documentBuilder
			 DocumentBuilder builder = factory.newDocumentBuilder();
			 // Creo un DOMImplementation
			 DOMImplementation implementation = builder.getDOMImplementation();
			 // Creo un documento con un elemento raiz
			 Document documento = implementation.createDocument(null, "vinos", null);
			 documento.setXmlVersion("1.0");	 
			 // Creo los elementos
			 for (int x = 0; x < listaVinos.size(); x++) {
				  
				 Element elementVino = documento.createElement("vino");
				 elementVino.setAttribute("identificador", listaVinos.get(0).toString());
				 Element elementCampoNombre = documento.createElement("nombre");
				 Text textNombre = documento.createTextNode(listaVinos.get(1).toString());
				 elementCampoNombre.appendChild(textNombre);
				 elementVino.appendChild(elementCampoNombre);
				 Element elementCampoRegion = documento.createElement("region");
				 Text textRegion = documento.createTextNode(listaVinos.get(2).toString());
				 elementCampoRegion.setAttribute("pais", listaVinos.get(3).toString());
				 elementCampoRegion.appendChild(textRegion);
				 Element elementCampoURL = documento.createElement("url");
				 Text textURL = documento.createTextNode(listaVinos.get(4).toString());
				 elementVino.appendChild(elementCampoURL);
				 elementVino.appendChild(elementCampoRegion);
				 documento.getDocumentElement().appendChild(elementVino);
	
			 }

			 // Asocio el source con el Document
			 Source source = new DOMSource(documento);
			 long nowMillis = System.currentTimeMillis();
			 String nombreFichero = "Vinos " + nowMillis + ".xml";
			 // Creo el Result, indicado que fichero se va a crear
			 Result result = new StreamResult(new File(nombreFichero));
			 // Creo un transformer, se crea el fichero XML
			 Transformer transformer = TransformerFactory.newInstance().newTransformer();
			 transformer.transform(source, result);
			 
		 } catch (ParserConfigurationException | TransformerException ex) {

			 System.out.println(ex.getMessage());

		 }
	}

}
