package it.polito.tdp.artsmia.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import it.polito.tdp.artsmia.db.ArtsmiaDAO;

public class Model {
	
	private List<Exhibition>exhibitions;
	private SimpleDirectedGraph<Exhibition,DefaultEdge>grafo;
	
	public Model(){
		
	}
	
	public List<Exhibition>getExhibitions(){
		if(this.exhibitions==null){
			ArtsmiaDAO dao=new ArtsmiaDAO();
			this.exhibitions=dao.listExhibition();
		}
		return this.exhibitions;
	}
	
	public List<Integer>getBegins(){
		List<Integer>begins=new ArrayList<Integer>();
		for(Exhibition e:getExhibitions()){
			if(!begins.contains(e.getBegin()))
				begins.add(e.getBegin());
		}
		return begins;
	}
	
	public void creaGrafo(int begin){
		List<Exhibition>listaAPartireDa=new ArrayList<Exhibition>();
		for(Exhibition e:getExhibitions())
			if(e.getBegin()>begin)
				listaAPartireDa.add(e);
		
		grafo=new SimpleDirectedGraph<>(DefaultEdge.class);
		Graphs.addAllVertices(grafo,listaAPartireDa);
		for(Exhibition e1:grafo.vertexSet()){
			for(Exhibition e2:grafo.vertexSet())
				if(!e1.equals(e2)&&e2.getBegin()>e1.getBegin()&&e1.getEnd()>e2.getBegin()){
					grafo.addEdge(e1,e2);
					System.out.println("aggiunto arco tra "+e1.getId()+" e "+e2.getId());
				}
		}
		System.out.println("\nvertici #: "+grafo.vertexSet().size());
	}
	
	public boolean getIsConnected(){
		ConnectivityInspector<Exhibition, DefaultEdge> con=new ConnectivityInspector<Exhibition, DefaultEdge>(grafo);
		return con.isGraphConnected();
	}
	
	public int getNumOggetti(){
		int max=0;
		ArtsmiaDAO dao=new ArtsmiaDAO();
		for(Exhibition e:grafo.vertexSet())
			if(dao.numOggettiExhibition(e)>max){
				max=dao.numOggettiExhibition(e);
				return max;
			}
		return 0;
	}
}
