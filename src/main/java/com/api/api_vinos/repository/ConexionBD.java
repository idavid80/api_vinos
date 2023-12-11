package com.api.api_vinos.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.api.api_vinos.entity.DatosTecnicosDTO;
import com.api.api_vinos.entity.VinoDTO;

@Repository
public class ConexionBD {

	public Connection conexion;

	public Connection abrirConexion() {

		String url = "jdbc:mysql://localhost:3306/";
		String usuario = "root";
		String clave = "Se13jjl46";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection(url, usuario, clave);
			System.out.println("Conectado");

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conexion;
	}

	public Connection cerrarConexion() {
		if (conexion != null) {
			try {
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conexion;
	}

	public void crearBaseDatos() {

		abrirConexion();
		try {

			Statement stmt = conexion.createStatement();
			String sql = "CREATE DATABASE Api_vino";

			stmt.execute(sql);

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cerrarConexion();

	}

	public void crearTablaVinos() {

		abrirConexion();
		try {

			Statement stmt = conexion.createStatement();
			String sql = "	create table Api_vino.Vinos(\r\n" 
					+ "			id_vino int NOT NULL AUTO_INCREMENT,\r\n"
					+ "			modelo varchar(1000) NOT NULL,\r\n" 
					+ "			url varchar(1000) NOT NULL,\r\n"
					+ "			imagen varchar(1000) NOT NULL,\r\n" 
					+ "			PRIMARY KEY (id_vino)\r\n"
					+ "			);";

			stmt.execute(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cerrarConexion();
	}

	public void crearTablaDatosTecnicos() {
		abrirConexion();
		try {

			Statement stmt = conexion.createStatement();
			String sql = "	create table Api_vino.Datos_tecnicos(\r\n"
					+ "			id_datos_tecnicos int NOT NULL AUTO_INCREMENT,\r\n"
					+ "			pais varchar(255) NOT NULL,\r\n" 
					+ "			region varchar(255) NOT NULL,\r\n"
					+ "			id_vino int,\r\n" 
					+ "			PRIMARY KEY (id_datos_tecnicos),\r\n"
					+ "			FOREIGN KEY (id_vino) REFERENCES Api_vino.Vinos(id_vino)\r\n" + "			);";

			stmt.execute(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cerrarConexion();
	}

	public List<VinoDTO> getAllVinos() {
		VinoDTO vinoDTO = new VinoDTO();
		List<VinoDTO> listaVinos = new ArrayList<>();
		abrirConexion();
		try {

			Statement stmt = conexion.createStatement();
			String sql = "SELECT * FROM Api_vino.Vinos";

			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				vinoDTO = new VinoDTO();
				vinoDTO.setIdModeloVino(rs.getInt("id_vino"));
				vinoDTO.setModeloVino(rs.getString("modelo"));
				vinoDTO.setUrl(rs.getString("url"));
				vinoDTO.setImagen(rs.getString("imagen"));

				listaVinos.add(rs.getInt("id_vino") - 1, vinoDTO);

			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cerrarConexion();
		return listaVinos;
	}

	public void insertarVino(VinoDTO vinoDTO) {
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO Api_vino.Vinos (modelo, url, imagen ) VALUES (?, ?, ?);";
		// String sql = "insert into preguntas values (?,?)";
		abrirConexion();

		try {
			// Statement stmt = conexion.createStatement();

			pstmt = conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

			pstmt.setString(1, vinoDTO.getModeloVino());
			pstmt.setString(2, vinoDTO.getUrl());
			pstmt.setString(3, vinoDTO.getImagen());

			pstmt.executeUpdate();

			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cerrarConexion();
	}

	public void insertarDatosTecnicos(DatosTecnicosDTO datosTecnicos) {
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO Api_vino.Datos_tecnicos (pais, region, id_vino) VALUES (?, ?, ?);";
		// String sql = "insert into preguntas values (?,?)";
		abrirConexion();

		try {
			// Statement stmt = conexion.createStatement();

			pstmt = conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

			pstmt.setString(1, datosTecnicos.getPais());
			pstmt.setString(2, datosTecnicos.getRegion());
			pstmt.setInt(3, datosTecnicos.getIdModeloVino());

			pstmt.executeUpdate();

			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cerrarConexion();
	}

}
