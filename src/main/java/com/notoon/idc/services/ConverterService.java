package com.notoon.idc.services;

import java.text.ParseException;

import com.notoon.idc.exceptions.InvalidInputException;

/**
 * Interface du service à implémenter
 * 
 * @author t_dc
 */
public interface ConverterService {

	/**
	 * Conversion : Format date -> Format base 10
	 * 
	 * @param date
	 *            Date à convertir
	 * @return La valeur en base 10
	 * @throws ParseException
	 *             La valeur en entrée n'est pas au format attendu
	 * @throws InvalidInputException
	 *             La date parsée ne correspond plus à la date entrée (ex : 32
	 *             jan. != 1 fév.)
	 */
	public String toBase10(String dateString) throws ParseException,
			InvalidInputException;

	/**
	 * Conversion Format base 10 -> Format base 32
	 * 
	 * @param decimal
	 *            Valeur à convertir
	 * @return La valeur en base 32
	 * @throws NumberFormatException
	 *             La valeur en entrée n'entre pas dans l'intervalle attendu
	 */
	String toBase32(String decimal) throws NumberFormatException;

	/**
	 * Conversion Format base 32 -> Format base 10
	 * 
	 * @param inBase32
	 *            Valeur à convertir
	 * @return La valeur en base 10
	 * @throws NumberFormatException
	 *             La valeur en entrée n'entre pas dans l'intervalle attendu
	 */
	String fromBase32(String inBase32) throws NumberFormatException;

	/**
	 * Conversion Format base 10 -> Format date
	 * 
	 * @param inBase10
	 *            Valeur à convertir
	 * @return La valeur au format date
	 */
	String toDateFormat(String decimal) throws NumberFormatException;

	/**
	 * Génère la requête SQL SELECT à partir du paramètre Id
	 * 
	 * @param id
	 *            L'Id
	 * @return La requête SELECT
	 */
	String getSelectQuery(String id);

	/**
	 * Génère la requête SQL UPDATE à partir du paramètre Id
	 * 
	 * @param id
	 *            L'Id
	 * @return La requête UPDATE
	 */
	String getUpdateQuery(String id);

	/**
	 * Génère la requête requête SQL DELETE à partir du paramètre Id
	 * 
	 * @param id
	 *            L'Id
	 * @return La requête DELETE
	 */
	String getDeleteQuery(String id);
}
