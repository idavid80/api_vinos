package com.api.api_vinos.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionRepository {

public Connection conexion; 
	
	public Connection abrirConexion() {
		String connectionUrl =
                "jdbc:mariadb://localhost:3306/bbdd";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conexion = DriverManager.getConnection(
                    connectionUrl, "guilla", "guilla");
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
