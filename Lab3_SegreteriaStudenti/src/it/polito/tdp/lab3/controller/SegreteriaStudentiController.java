package it.polito.tdp.lab3.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab3.model.Corso;
import it.polito.tdp.lab3.model.SegreteriaModel;
import it.polito.tdp.lab3.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

public class SegreteriaStudentiController {
	
	private SegreteriaModel model;

    public void setModel(SegreteriaModel model) {
		this.model = model;
		
		cmbCorsi.getItems().addAll(model.popolaTendina());
	}

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Corso> cmbCorsi;

    @FXML
    private TextField txtMatricola;

    @FXML
    private Button btnSpunta;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private Button btnIscrivi;

    @FXML
    private Button btnCerca;

    @FXML
    private TextArea txtOutput;

    @FXML
    private Button btnReset;

    @FXML
    void doCerca(ActionEvent event) {
    	String stampa = "";
    	if(txtMatricola.getText().compareTo("")!=0 && cmbCorsi.getValue().getCrediti()==-1){
    		if(model.cercaStudente(txtMatricola.getText())!=null){
    			for(Corso c: model.corsiStudente(txtMatricola.getText())){
    				stampa += c.getCodIns() + " " + c.getCrediti() + " " + c.getNome() + " " + c.getPd() + "\n";
    			}
    			txtOutput.setText(stampa);
    			return;
    		}
    		else{
    			txtOutput.setText("Matricola non trovata");
    			return;
    		}
    	}
    	else{
    	  	String codIns = cmbCorsi.getValue().getCodIns();
        	for(Studente s: model.iscrittiCorso(codIns)){
        		if(s!=null){
        			stampa += s.toString();
        		}
        		else{
        			txtOutput.setText("Nessuno studente iscritto al corso" + codIns);
        			return;
        		}
        	}
        	txtOutput.setText(stampa);
    	}
    }

    @FXML
    void doIscrivi(ActionEvent event) {

    }

    @FXML
    void doReset(ActionEvent event) {
    	txtMatricola.setText("");
    	txtNome.setText("");
    	txtCognome.setText("");
    	txtOutput.clear();
    	btnSpunta.setDisable(false);
    }

    @FXML
    void doSpunta(ActionEvent event) {
    	String matricola = txtMatricola.getText();
    	if(matricola.compareTo("")!=0){
    		Studente tempS = model.cercaStudente(matricola);
    		if(tempS==null){
    			txtOutput.setText("Matricola Inesistente");
    			return;
    		}
    		txtNome.setText(tempS.getNome());
    		txtCognome.setText(tempS.getCognome());
    		btnSpunta.setDisable(true);
    	}
    	else{
    		txtOutput.setText("Matricola errata");
    		return;
    	}
    }

    @FXML
    void initialize() {
        assert cmbCorsi != null : "fx:id=\"cmbCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnSpunta != null : "fx:id=\"btnSpunta\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCerca != null : "fx:id=\"btnCerca\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtOutput != null : "fx:id=\"txtOutput\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";

    }
}
