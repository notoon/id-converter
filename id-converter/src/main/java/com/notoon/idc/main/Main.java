package com.notoon.idc.main;

import com.notoon.idc.services.impl.ConverterServiceImpl;
import com.notoon.idc.ui.UserInterface;

/**
 * Classe principale
 * 
 * @author Toon
 */
public class Main {

	/**
	 * Méthode principale
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Création de l'interface
		new UserInterface(new ConverterServiceImpl());
	}
}
