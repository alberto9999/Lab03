package it.polito.tdp.spellchecker.model;

public class RichWord {
private boolean corretto;
private String word;

public RichWord(String word) {
	super();
	this.word = word;
	this.corretto=true;
}

public boolean isCorretto() {
	return corretto;
}

public void setCorretto(boolean corretto) {
	this.corretto = corretto;
}

public String getWord() {
	return word;
}


	




}
