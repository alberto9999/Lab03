package it.polito.tdp.spellchecker.model;

import java.util.*;
import java.lang.Math;
import java.io.*;

public class Dictionary {
LinkedList <String> listaItaliano = new LinkedList<String>();
LinkedList <String> listaEnglish= new LinkedList<String>() ;
private String lingua;
private int contErrori;
private double time;


	public void loadDictionary(String language) {
		if(language.compareTo("English")==0){	
			try { 
				 FileReader fr = new FileReader("rsc/English.txt");  
				 BufferedReader br = new BufferedReader(fr); 
				 String word; 
				 while ((word = br.readLine()) != null) { 
				 
					 listaEnglish.add(word.toLowerCase());
				 }
			
			   br.close(); }
				 
				catch (IOException e){ 
				 System.out.println("Errore nella lettura del file");  
				}
			
			
		}
			
        if(language.compareTo("Italiano")==0){
        	
			
        	try { 
				 FileReader fr = new FileReader("rsc/Italian.txt");  
				 BufferedReader br = new BufferedReader(fr); 
				 String word; 
				 while ((word = br.readLine()) != null) { 
				 
					 listaItaliano.add(word.toLowerCase());
				 }
				     
		      	br.close(); }
				 
				catch (IOException e){ 
				 System.out.println("Errore nella lettura del file");  
				}
			
		}	
}	
	
	public static double log2(double d) {
		return Math.log(d)/Math.log(2.0);
		}
	
	public String spellCheckText(List<String> inputTextList) {
		contErrori=0;
		LinkedList<RichWord> listaWord = new LinkedList <RichWord>();
		boolean temp=false;
	
		for(String parola :inputTextList){
			RichWord w = new RichWord(parola);
			listaWord.add(w);
			
		/*	
			if(lingua.compareTo("English")==0){	
				
			temp=false;
				int delMin=0;
				int delMax=listaEnglish.size()-1;
				int centro;
				while (temp==false&&delMin<=delMax){
					centro=(delMin+delMax)/2;
					int result=listaEnglish.get(centro).compareTo(parola); 
					
					if(result==0){
						temp=true;	
					}                                                        //trovato su internet ma non mi trova alcune parole
					else{
						if(result >0){
							delMax=centro-1;
							
							
						}
						else{
							delMin=centro+1;
							
						}
					}					
				}	
				
			}
			*/

			
		
			if(lingua.compareTo("English")==0){	
				temp= false;
				int indice = (int)listaEnglish.size()/2;
				int delMin=0;
				int delMax=listaEnglish.size();
				int diff=delMax-delMin;
				for(int i=0;i<(listaEnglish.size())&&temp==false&&diff>=1;i++){
					int result=listaEnglish.get(indice).compareTo(parola); 
					if(result==0){
						temp=true;	
					}
					else{
						if(result >0){
							delMax=indice;
							indice=(int)  ((delMin+indice)/2);
							diff=delMax-delMin;
							
						}
						else{
							delMin=indice;
							indice= (int)((delMax+indice)/2);
							diff=delMax-delMin;
						}
					}					
				}	
				
			}
			if(lingua.compareTo("Italiano")==0){	
				temp= false;
				int indice = (int)listaItaliano.size()/2;
				int delMin=0;
				int delMax=listaItaliano.size();
				int diff=delMax-delMin;
				for(int i=0;i<(listaItaliano.size())&&temp==false&&diff>=1;i++){
					int result=listaItaliano.get(indice).compareTo(parola); 
					if(result==0){
						temp=true;	
					}
					else{
						if(result >0){
							delMax=indice;
							indice=(int) ((delMin+indice)/2);
							diff=delMax-delMin;
							
						}
						else{
							delMin=indice;
							indice=(int)((delMax+indice)/2);
							diff=delMax-delMin;
							
						}
					}					
			}	
				
			}
			
			
		/*
			if(lingua.compareTo("English")==0){	
				temp=false;
			   for(String parolaDizionario : listaEnglish ){  
		
				if(parolaDizionario.compareTo(parola)==0){
					temp=true;	
					
				}
				
			  }
		   }
			if(lingua.compareTo("Italiano")==0){
				temp=false;
				for(String parolaDizionario : listaItaliano ){
				
					if(parolaDizionario.compareTo(parola)==0){
						temp=true;    
						
					}		
				}
			}
		//	*/
			
			if(temp==false){
			w.setCorretto(false);
			contErrori++;
				
			}
		}
		String s2=ParoleSbagliate(listaWord);
		return s2;
		
	}
	
	public String ParoleSbagliate(List<RichWord> lista){
		String str="";
		for(RichWord rw :lista){
			if(rw.isCorretto()==false){
				str+=rw.getWord()+"\n";
			}
		}
		 return str;
		
	}




	public String  Operator(String frase) {
		long time0=System.nanoTime();
		LinkedList<String>tempLista= new LinkedList<String>();
		String fraseAdj= frase.replaceAll("[\\p{Punct}]", "");
		String[]arrayParole= fraseAdj.split(" ");
		
		for(String s: arrayParole){
			tempLista.add(s);
		}
	 
		String s1= spellCheckText(tempLista);
		long time1= System.nanoTime();
		setTime(time0,time1);
		return s1;
		
	}
	
	private void setTime(long time0, long time1) {
		 time=((double)(time1-time0))/1000000000;
	}



	public void setLingua(String lingua) {
	this.lingua = lingua;
}


	public String errorCalculator() {
		if(lingua.compareTo("English")==0)
		return "The text contains "+contErrori+" error/s";
		if(lingua.compareTo("Italiano")==0)
		return "Il testo contiene "+contErrori+" errore/i";
		
		return "";
	}



	public String timeCalculator() {
		if(lingua.compareTo("English")==0)
			return "Spell checked completed in "+time+" seconds";
			if(lingua.compareTo("Italiano")==0)
			return "Rilevamento errori completato in "+time+" secondi";
			
			return "";
	}
	

	
}
