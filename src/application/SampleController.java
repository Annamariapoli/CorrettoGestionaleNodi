package application;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import bean.Nodo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class SampleController {
	
	private Nodo n = new Nodo();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnVisualizza;

    @FXML
    private Button btnCorsi;

    @FXML
    private TextArea txtResult;

    @FXML
    void doCorsi(ActionEvent event) {
    	txtResult.clear();
    	n.buildGraph();
    	
    	int studentiUnCorsoSolo = n.getNumeroStudentiIscrittiUnCorso();
    	int studentiDueCorsiSoli= n.getNumeroStudentiIscrittiDueCorsi();
    	txtResult.appendText("Gli studenti iscritti ad un corso sono :  "+ studentiUnCorsoSolo+" \n");
    	txtResult.appendText("Gli studenti iscritti a due corsi sono  : "+studentiDueCorsiSoli+" \n");
    	
    	Map<Integer, Integer> map = n.getMappe();
//    
//    	for(int i = 0; i< 10 ;i++){
//    		
//    		txtResult.appendText("Studenti iscritti ad " + i + " corsi: " + n.get(i));   // evitare mappa
//    		
//    	}
    	
    	txtResult.appendText(map.toString());

    }

    @FXML
    void doVisualizza(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert btnVisualizza != null : "fx:id=\"btnVisualizza\" was not injected: check your FXML file 'Sample.fxml'.";
        assert btnCorsi != null : "fx:id=\"btnCorsi\" was not injected: check your FXML file 'Sample.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Sample.fxml'.";

    }
}
