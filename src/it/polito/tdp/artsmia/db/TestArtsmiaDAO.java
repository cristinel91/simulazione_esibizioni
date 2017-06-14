package it.polito.tdp.artsmia.db;

import java.util.List;

import it.polito.tdp.artsmia.model.ArtObject;
import it.polito.tdp.artsmia.model.Exhibition;
import it.polito.tdp.artsmia.model.Model;

public class TestArtsmiaDAO {

	public static void main(String[] args) {
		
		ArtsmiaDAO dao = new ArtsmiaDAO() ;
		Model model=new Model();
		/*List<ArtObject> objects = dao.listObject() ;
		System.out.println(objects.size());*/
		model.creaGrafo(2000);
	}
}
