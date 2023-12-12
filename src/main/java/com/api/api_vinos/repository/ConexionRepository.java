package com.api.api_vinos.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;


@Repository		//CLASE DE PRUEBA PARA LA CONEXION DE LA BASE DE DATOS, NO SE USA EN EL RESTO DEL PROGRAMA, SOLO ES PARA PROBAR LO QUE LUEGO SE INSERTA EN ConexionBD
public class ConexionRepository {

public Connection conexion; 
	
	public Connection abrirConexion() {
		String connectionUrl = "jdbc:mysql://localhost:3306/";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(
                    connectionUrl, "root", "Se13jjl46");
            System.out.println("Conectado...");
        } catch (SQLException | ClassNotFoundException ce) {
            ce.printStackTrace();
        }
        return conexion;
	}
	
	public void cerrarConexion(Connection conexion) {
		if (conexion != null) {
			try {
				conexion.close();
			} catch ( SQLException sqle) {
				sqle.printStackTrace();
			}
		}
	}
}
