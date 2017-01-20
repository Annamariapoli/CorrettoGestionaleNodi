package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import bean.Corso;
import bean.Studente;

public class Dao {

	
	public List<Studente> getAlls(){
		List<Studente> stu = new LinkedList<>();
		Connection conn = DBConnect.getConnection();
		String query="select distinct * from studente";
		try{
			PreparedStatement st = conn.prepareStatement(query);
			ResultSet res = st.executeQuery();
			while(res.next()){
				Studente s = new Studente(res.getInt("matricola"), res.getString("cognome"),  res.getString("nome"),  res.getString("cds"));
				stu.add(s);
			}
			conn.close();
			return stu;
		}catch(SQLException e ){
			e.printStackTrace();
			return null;
		}
	}
	
	
	public List<Corso> getAllC(){
		List<Corso> corsi = new LinkedList<>();
		Connection conn = DBConnect.getConnection();
		String query="select distinct * from corso";
		try{
			PreparedStatement st = conn.prepareStatement(query);
			ResultSet res = st.executeQuery();
			while(res.next()){
				Corso c = new Corso( res.getString("codins"),  res.getString("nome"), res.getInt("crediti"), res.getInt("pd"));
				corsi.add(c);
			}
			conn.close();
			return corsi;
		}catch(SQLException e ){
			e.printStackTrace();
			return null;
		}
	}

	
	public List<Corso> getTuttiCorsiStudente(Studente studente ){
		List<Corso> corsiStudente = new LinkedList<>();
		Connection conn = DBConnect.getConnection();
		String query="select distinct  c.codins, c.nome, c.crediti, c.pd    "
				+ "from  iscrizione i, corso c   "
				+ "where i.codins=c.codins and i.matricola=?";
		try{
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1,  studente.getMatricola());
			ResultSet res = st.executeQuery();
			while(res.next()){
				Corso c = new Corso( res.getString("codins"),  res.getString("nome"), res.getInt("crediti"), res.getInt("pd"));
				corsiStudente.add(c);
			}
			conn.close();
			return corsiStudente;
		}catch(SQLException e ){
			e.printStackTrace();
			return null;
		}
	}

	
	// numero di corsi tot
}
