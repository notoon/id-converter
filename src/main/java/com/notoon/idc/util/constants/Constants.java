package com.notoon.idc.util.constants;

/**
 * Classe contenant toutes les constantes
 *
 * @author t_dc
 */
public class Constants {

	/** String vide */
	public final static String EMPTY_STR = "";

	/** Position du curseur au début du champ */
	public final static int POS_START = 0;

	/** Aucune base = DATE */
	public final static int NONE = 0;

	/** BASE 10 */
	public final static int BASE_10 = 10;

	/** BASE 32 */
	public final static int BASE_32 = 32;

	/** Format de la date */
	public final static String DATE_FORMAT = "yyyy-MM-dd-HH.mm.ss.SSS";

	/** Suffixe des micro-secondes */
	public final static String MICROSECONDS = "000";

	/** RegExp pour matcher le format de la date */
	public final static String DATE_FORMAT_REGEX = "[0-9]{4}-([0-9]{2}-){2}([0-9]{2}.){3}([0-9]{3}|[0-9]{6})";

	/** RegExp pour matcher le format de la date */
	public final static String DATE_FORMAT_REGEX_MICRO = "[0-9]{4}-([0-9]{2}-){2}([0-9]{2}.){3}[0-9]{6}";

	/** TimeZone */
	public final static String TIME_ZONE = "GMT";
}
