package com.api.api_vinos.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.api.api_vinos.entity.Vino_DatosTecnicos_aux;

@Repository
public class ConsultasJDBCRepository {

	public ConexionRepository conexionRepository;
	private Connection conexion;
	ArrayList<Vino_DatosTecnicos_aux> listaDatos;
	ArrayList<String> listaNombre;
	ResultSet rs;
	
	public ArrayList<Vino_DatosTecnicos_aux> sacarDatosVino() {
		
		Vino_DatosTecnicos_aux vino = new Vino_DatosTecnicos_aux();
		conexion = conexionRepository.abrirConexion();
		try {
			
			Statement stmt = conexion.createStatement();
			rs = stmt.executeQuery("Select * from Vinos inner"
							+ "inner join Datos_Tecnicos on "
							+ "Datos_Tecnicos.id_vino = Vinos.id_vino;");
			
			vino.setId(rs.getInt("id_vino"));
			vino.setNombre(rs.getString("modelo"));
			vino.setRegion(rs.getString("region"));
			vino.setPais(rs.getString("pais"));
			vino.setUrl(rs.getString("url"));
			
			listaDatos.add(vino);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		conexionRepository.cerrarConexion(conexion);
		
		return listaDatos;
	}
	
	public ArrayList<String> sacarVinosPorPais(String pais) {
		conexion = conexionRepository.abrirConexion();
		try {
			Statement stmt = conexion.createStatement();
			rs = stmt.executeQuery("Select Vinos.modelo from Vinos "
							+ "inner join Datos_tecnicos on Vinos.id_vino = Datos_tecnicos.id_vino"
							+ "where Datos_tecnicos.pais = " + pais +";");
			
			while (rs.next()) {
				listaNombre.add(rs.getString("modelo")); 
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		conexionRepository.cerrarConexion(conexion);
		
		return listaNombre;
	}
}
