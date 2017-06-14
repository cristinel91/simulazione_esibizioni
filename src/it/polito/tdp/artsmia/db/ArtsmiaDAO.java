package it.polito.tdp.artsmia.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.artsmia.model.ArtObject;
import it.polito.tdp.artsmia.model.Exhibition;

public class ArtsmiaDAO {

	public List<ArtObject> listObject() {
		
		String sql = "SELECT * from objects";

		List<ArtObject> result = new ArrayList<>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				
				result.add(new ArtObject(res.getInt("object_id"), res.getString("classification"), res.getString("continent"), 
						res.getString("country"), res.getInt("curator_approved"), res.getString("dated"), res.getString("department"), 
						res.getString("medium"), res.getString("nationality"), res.getString("object_name"), res.getInt("restricted"), 
						res.getString("rights_type"), res.getString("role"), res.getString("room"), res.getString("style"), res.getString("title")));
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Exhibition> listExhibition() {
		
		String sql = "SELECT * FROM exhibitions ORDER BY begin ASC";

		List<Exhibition> result = new ArrayList<>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(new Exhibition(res.getInt("exhibition_id"),res.getString("exhibition_department"),
										  res.getString("exhibition_title"),res.getInt("begin"),res.getInt("end")));
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<ArtObject> getOpereEsibizione(Exhibition e) {
		
		String sql = "SELECT COUNT(*) FROM exhibition_objects WHERE exhibition_id=?";

		List<Exhibition> result = new ArrayList<>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1,e.getId());
			ResultSet res = st.executeQuery();
			int num=0;
			if(res.next()) {
				num=res.getInt("COUNT(*)");
			}

			conn.close();
			return num;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return 0;
		}
	}
}
