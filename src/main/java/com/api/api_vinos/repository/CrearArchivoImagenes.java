package com.api.api_vinos.repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import com.api.api_vinos.entity.VinoDTO;

public class CrearArchivoImagenes {

	public void obtenerImagen(VinoDTO vino) {

		try {
			/* definimos la URL de la cual vamos a leer */
			URL rutaImagen = new URL(vino.getImagen());

			String pathImagenes = "./src/imagenes/";
			String extensionImagen = ".jpg";

			// metodo para leer de la URL y escribir fichero en la ruta
			writeTo(rutaImagen.openStream(),
					new FileOutputStream(new File(pathImagenes + vino.getModeloVino() + extensionImagen)));

			System.out.println("Imagen leida y guardada!");

		} catch (MalformedURLException e) {
			e.printStackTrace();

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	public void writeTo(InputStream in, OutputStream out) throws IOException {
		try {
			int c;
			while ((c = in.read()) != -1) {
				out.write(c);
			}

		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (Exception e) {
				}
			}
		}

		return;
	}

}
