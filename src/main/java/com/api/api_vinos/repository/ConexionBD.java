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
import com.api.api_vinos.entity.Vino_DatosTecnicos_aux;

@Repository
public class ConexionBD {	//Esta clase se usa para la conexion, creacion, insercion y consultas con la base de datos

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

	//Metodo de insercion de vinos en la base de datos, tirando de entidad propia pasada por parametros
	public void insertarVino(VinoDTO vinoDTO) {
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO Api_vino.Vinos (modelo, url, imagen ) VALUES (?, ?, ?);";
		abrirConexion();

		try {

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

	//Metodo de insercion de datos tecnicos en la base de datos, tirando de entidad propia pasada por parametros
	public void insertarDatosTecnicos(DatosTecnicosDTO datosTecnicos) {
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO Api_vino.Datos_tecnicos (pais, region, id_vino) VALUES (?, ?, ?);";
		abrirConexion();

		try {

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
	
	
	
	/////////////////////////////// PARTE DE PROGRAMACION DE PROCESOS/////////////////////////////////////////////////////////////
	public void insertarVinoConTimerTask(VinoDTO vinoDTO) {
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
	/////////////////////////////// PARTE DE PROGRAMACION DE PROCESOS/////////////////////////////////////////////////////////////

	
	
	/*Este metodo recoge datos de todos los vinos insertados en la Base de datos, dichos datos son ID, nombre, region, pais y url
	lo recoge por medio de un ResultSet y una ArrayList de tipo propio*/
	public ArrayList<Vino_DatosTecnicos_aux> sacarDatosVino() {
		
		ArrayList<Vino_DatosTecnicos_aux> listaDatos = new ArrayList<Vino_DatosTecnicos_aux>();
		
		Statement stmt = null;
		abrirConexion();
		try {
			
			stmt = conexion.createStatement();
			ResultSet rs = stmt.executeQuery("select * from api_vino.vinos \r\n"
								+ "inner join datos_tecnicos on datos_tecnicos.id_vino = vinos.id_vino ");
			while (rs.next()) {
				Vino_DatosTecnicos_aux vino = new Vino_DatosTecnicos_aux();
				vino.setId(rs.getInt("id_vino"));
				vino.setNombre(rs.getString("modelo"));
				vino.setRegion(rs.getString("region"));
				vino.setPais(rs.getString("pais"));
				vino.setUrl(rs.getString("url"));
				listaDatos.add(vino);
			}
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		cerrarConexion();
		
		return listaDatos;
	}
	
	/*Este metodo es similar al anterior, pero con la diferencia de que solo recoge los nombres de los vinos seleccionados por pais,
	 * recogiendo por tanto solo el nombre en un ArrayList de tipo String*/
	public ArrayList<String> sacarVinosPorPais(String pais) {
		ArrayList<String> listaNombre = new ArrayList<String>();
		ResultSet rs;
		abrirConexion();
		try {
			Statement stmt = conexion.createStatement();
			rs = stmt.executeQuery("Select Vinos.modelo from Vinos "
							+ "inner join datos_tecnicos on datos_tecnicos.id_vino = vinos.id_vino"
							+ "where datos_tecnicos.pais = 'francia';");
			
			while (rs.next()) {
				listaNombre.add(rs.getString("modelo")); 
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		cerrarConexion();
		
		return listaNombre;
	}
}
