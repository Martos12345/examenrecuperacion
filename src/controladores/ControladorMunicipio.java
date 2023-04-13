package controladores;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelos.Municipio;

public class ControladorMunicipio {

	public static List<Municipio> filtrarMunicipio(String municipio) {
		List<Municipio> lista = new ArrayList<Municipio>();

		try {

			Statement s = (Statement) ConnectionManager.getConexion().createStatement();

			ResultSet rs = s.executeQuery("select * from municipio where nombre like '%" + municipio + "%'");

			while (rs.next()) {
				Municipio i = new Municipio(rs.getInt("id"), rs.getInt("idProvincia"), rs.getInt("codMunicipio"),
						rs.getString("nombre"));
				lista.add(i);
			}

			rs.close();

		} catch (SQLException ex) {
			System.out.println("Error en la ejecución SQL: " + ex.getMessage());
			ex.printStackTrace();
		}

		return lista;
	}
	public static int modificar(Municipio m) {
		int registrosAfectados = 0;
		try {
			PreparedStatement ps = ConnectionManager.getConexion()
					.prepareStatement("update municipio set nombre = ?, idProvincia = ?");
			ps.setString(1, m.getNombre());
			ps.setInt(2, m.getIdProvincia());
			registrosAfectados = ps.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return registrosAfectados;
	}

}
