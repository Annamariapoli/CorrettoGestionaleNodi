package bean;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import db.Dao;

public class Nodo {


	private Dao dao = new Dao();
	private SimpleGraph<Nodo, DefaultEdge> grafo ;
	
	
	public List<Studente> getAllStudenti(){
		List<Studente> studenti = dao.getAlls();
		return studenti;
	}
	
	public List<Corso> getAllCorsi(){
		List<Corso> corsi = dao.getAllC();
		return corsi;
	}
	
	public void buildGraph(){                                              //ok parte
		grafo = new SimpleGraph<Nodo, DefaultEdge> (DefaultEdge.class);
		List<Nodo> nodi = new LinkedList<>();
		nodi.addAll(getAllStudenti());
		nodi.addAll(getAllCorsi());
		Graphs.addAllVertices(grafo,  nodi);
		for(Nodo n : grafo.vertexSet()){
			if(n instanceof Studente){
				List<Corso> corsiDelloStudente= dao.getTuttiCorsiStudente((Studente) n);
				for(Corso c : corsiDelloStudente){
					grafo.addEdge(n,  c);
				}
			}
		}
		System.out.println(grafo.toString());
	}
	
	
	public int getGradoNodo(Nodo vertice){            //a quanti corsi è iscritto uno studente
		if(vertice instanceof Studente){              //se il nodo è uno studente
		    int numeroDiCorsi=0;
			numeroDiCorsi = grafo.degreeOf(vertice);
			return numeroDiCorsi;
		}
		return -1;
	}
	
	public int getNumeroStudentiIscrittiUnCorso(){
		List<Studente> studentiUnCorso = new LinkedList<>();
		for(Nodo nodoVertice : grafo.vertexSet()){
			if(nodoVertice instanceof Studente){
				int numeroCorsi = grafo.degreeOf(nodoVertice);
				if(numeroCorsi==1){
					studentiUnCorso.add((Studente) nodoVertice);
				}
			}
		}
		return studentiUnCorso.size();
	}
	
	
	public int getNumeroStudentiIscrittiDueCorsi(){
		List<Studente> studentiDueCorsi = new LinkedList<>();
		for(Nodo nodoVertice : grafo.vertexSet()){
			if(nodoVertice instanceof Studente){
				int numeroCorsi = grafo.degreeOf(nodoVertice);
				if(numeroCorsi==2){
					studentiDueCorsi.add((Studente) nodoVertice);
				}
			}
		}
		return studentiDueCorsi.size();
	}
	
	
	public int getLunghezzaLista(int num){                       //prendo studenti iscritti a i corsi e ritorno il numero della lista
		List<Studente> numero  = new LinkedList<>();
		for(Nodo nodoVertice : grafo.vertexSet()){
			if(nodoVertice instanceof Studente){
				int numeroCorsi = grafo.degreeOf(nodoVertice);
				if(numeroCorsi==num){
					numero.add((Studente) nodoVertice);
				}
			}}
		return numero.size();
	}
	
	public Map<Integer, Integer> getMappe(){
		Map<Integer, Integer> map = new HashMap<>();
		int numeroCorsi= getNumeroCorsiTotali();
		for(int i=1; i<=numeroCorsi; i++){                     //numero max di corsi , so studente non puo seguire di piu
			int dimensioneLista = getLunghezzaLista(i);
			map.put(i, dimensioneLista);
		}
		return map;
	}
	
	public int getNumeroCorsiTotali(){           //ok
		int numeroCorsi= dao.getAllC().size();
		System.out.println(numeroCorsi);
		return numeroCorsi;
	}
	
	
	public List<Nodo> getVicini(Studente s ){                     //dato uno studente, i vicini sono i corsi ai quali è iscritto
		List<Nodo> vicini= Graphs.neighborListOf(grafo,  s);
		//System.out.println(vicini);
		return vicini;
		
	}
	
	public static void main(String [] args){
		Nodo nodo = new Nodo();
		//nodo.buildGraph();
	//	Studente s = new Studente(203500, "Migliorini", "Andrea", "INF1T3");
	//	nodo.getVicini(s);
		nodo.getNumeroCorsiTotali();
	}
}
