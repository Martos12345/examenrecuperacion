package controladores;


	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.ArrayList;
	import java.util.List;

import modelos.Provincia;



	public class ControladorProvincia {
		
		/**
		 * 
		 */
		public static List<Provincia> obtenerTodosLasProvincias() {
			List<Provincia> lista = new ArrayList<Provincia>();

			try {
				
				Statement s = (Statement) ConnectionManager.getConexion().createStatement();

				
				ResultSet rs = s.executeQuery("select * from provincia");

				
				while (rs.next()) {
					Provincia p = new Provincia(rs.getInt("id"), rs.getString("provincia"));
					lista.add(p);
				}
				
				rs.close();
				s.close();
			} catch (SQLException ex) {
				System.out.println("Error en la ejecución SQL: " + ex.getMessage());
				ex.printStackTrace();
			}

			return lista;
		}
		


}
