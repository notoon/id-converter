package com.notoon.idc.services.impl;

import java.text.ParseException;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.notoon.idc.exceptions.InvalidInputException;
import com.notoon.idc.services.ConverterService;
import com.notoon.idc.services.impl.ConverterServiceImpl;

public class ConverterServiceImplTest {

	/** Le service à tester */
	private static ConverterService converterService;

	/** La date */
	private static String dateString;

	/** Le timestamp */
	private static String decimal;

	/** La base 32 */
	private static String base32;

	/** La requête SQL SELECT */
	private static String expectedSelectquery;

	/** La requête SQL UPDATE */
	private static String expectedUpdatequery;

	/** La requête SQL DELETE */
	private static String expectedDeletequery;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		converterService = new ConverterServiceImpl();

		expectedSelectquery = "SELECT * FROM table WHERE id = '2013-07-28-11.23.52.931000'";
		expectedUpdatequery = "UPDATE table SET column = value WHERE id = '2013-07-28-11.23.52.931000'";
		expectedDeletequery = "DELETE FROM table WHERE id = '2013-07-28-11.23.52.931000'";

		dateString = "2013-07-28-11.23.52.931";
		decimal = "1375010632931";
		base32 = "180IGAD73";
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		converterService = null;

		expectedSelectquery = null;
		expectedUpdatequery = null;
		expectedDeletequery = null;

		dateString = null;
		decimal = null;
		base32 = null;
	}

	@Test
	public void testToDateFormat() {
		Assert.assertEquals(dateString, converterService.toDateFormat(decimal));
	}

	@Test
	public void testToBase10() throws ParseException, InvalidInputException {
		Assert.assertEquals(decimal, converterService.toBase10(dateString));
	}

	@Test
	public void testToBase32() {
		Assert.assertEquals(base32, converterService.toBase32(decimal));
	}

	@Test
	public void testFromBase32() {
		Assert.assertEquals(decimal, converterService.fromBase32(base32));
	}

	@Test
	public void testGetSelectQuery() {
		Assert.assertEquals(expectedSelectquery,
				converterService.getSelectQuery(dateString));
	}

	@Test
	public void testGetUpdateQuery() {
		Assert.assertEquals(expectedUpdatequery,
				converterService.getUpdateQuery(dateString));
	}

	@Test
	public void testGetDeleteQuery() {
		Assert.assertEquals(expectedDeletequery,
				converterService.getDeleteQuery(dateString));
	}
}