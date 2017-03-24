package it.polito.tdp.spellchecker.controller;

import java.net.URL;

import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class SpellCheckerController {

 
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> cmbboxLanguage;

    @FXML
    private TextArea txtInsert;

    @FXML
    private Button btnCheck;

    @FXML
    private TextArea txtWrong;

    @FXML
    private Label lblError;

    @FXML
    private Button btnClear;

    @FXML
    private Label lblTime;

	private Dictionary model;
	private boolean dizionariCaricati;
	

    @FXML
    void doCheck(ActionEvent event) {
    	if(dizionariCaricati==false){
    	model.loadDictionary("English");
    	model.loadDictionary("Italiano");
    	dizionariCaricati=true;}
    	
    	model.setLingua(cmbboxLanguage.getValue());
    	String frase=txtInsert.getText();
    	String s= model.Operator(frase.toLowerCase());	
    	txtWrong.setText(s);
    	lblError.setText(model.errorCalculator());
    	lblTime.setText(model.timeCalculator());
    	
       

    }

    @FXML
    void doClear(ActionEvent event) {
    	txtWrong.setText("");
    	txtInsert.setText("");
    	lblError.setText("");
    	lblTime.setText("");
    }
    
    
    
    
    public void setModel(Dictionary model) {
  	  this.model=model;
  		
  	}

    @FXML
    void initialize() {
        assert cmbboxLanguage != null : "fx:id=\"cmbboxLanguage\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtInsert != null : "fx:id=\"txtInsert\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnCheck != null : "fx:id=\"btnCheck\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtWrong != null : "fx:id=\"txtWrong\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert lblError != null : "fx:id=\"lblError\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert lblTime != null : "fx:id=\"lblTime\" was not injected: check your FXML file 'SpellChecker.fxml'.";

        
        
        cmbboxLanguage.getItems().addAll("English","Italiano");
        cmbboxLanguage.setValue(  cmbboxLanguage.getItems().get(0));
        
        
    }

	
}

