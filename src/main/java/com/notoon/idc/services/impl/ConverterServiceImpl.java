package com.notoon.idc.services.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.notoon.idc.exceptions.InvalidInputException;
import com.notoon.idc.services.ConverterService;
import com.notoon.idc.util.constants.Constants;

/**
 * Implémentation du service de conversion
 * 
 * @author t_dc
 */
public class ConverterServiceImpl implements ConverterService {

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.notoon.idc.services.ConverterService#toBase10(String)
	 */
	@Override
	public String toBase10(String dateString) throws ParseException,
			InvalidInputException {

		SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT);
		formatter.setTimeZone(TimeZone.getTimeZone(Constants.TIME_ZONE));
		Date date = formatter.parse(dateString);

		// Vérifie que la date est valide (ex : 32 jan. != 1 fév.)
		if (!formatter.format(date).equals(dateString)) {
			throw new InvalidInputException("La date est incorrecte !\n");
		}

		return String.valueOf(date.getTime());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.notoon.idc.services.ConverterService#toBase32(String)
	 */
	@Override
	public String toBase32(String decimal) throws NumberFormatException {

		long longValue = Long.parseLong(decimal);
		return Long.toString(longValue, Constants.BASE_32).toUpperCase();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.notoon.idc.services.ConverterService#fromBase32(String)
	 */
	@Override
	public String fromBase32(String inBase32) throws NumberFormatException {

		long longValue = Long.parseLong(inBase32, Constants.BASE_32);
		return String.valueOf(longValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.notoon.idc.services.ConverterService#toDateFormat(String)
	 */
	@Override
	public String toDateFormat(String decimal) throws NumberFormatException {

		long longValue = Long.parseLong(decimal);

		Date date = new Date(longValue);
		DateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT);
		formatter.setTimeZone(TimeZone.getTimeZone(Constants.TIME_ZONE));

		return formatter.format(date);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.notoon.idc.services.ConverterService#getSelectQuery(String)
	 */
	@Override
	public String getSelectQuery(String id) {
		return "SELECT * FROM table WHERE id = '" + id + Constants.MICROSECONDS
				+ "'";
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.notoon.idc.services.ConverterService#getUpdateQuery(String)
	 */
	@Override
	public String getUpdateQuery(String id) {
		return "UPDATE table SET column = value WHERE id = '" + id
				+ Constants.MICROSECONDS + "'";
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.notoon.idc.services.ConverterService#getDeleteQuery(String)
	 */
	@Override
	public String getDeleteQuery(String id) {
		return "DELETE FROM table WHERE id = '" + id + Constants.MICROSECONDS
				+ "'";
	}
}