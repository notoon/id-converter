package com.notoon.idc.util.log;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import org.apache.log4j.WriterAppender;
import org.apache.log4j.spi.LoggingEvent;

/**
 * Classe utilitaire pour l'utilisation de log4j dans un JTextArea
 *
 * @author t_dc
 */
public class TextAreaAppender extends WriterAppender {

	private static JTextArea jTextArea;

	/**
	 * Set the target JTextArea for the logging information to appear
	 *
	 * @param jTextArea
	 *            La zone de texte où écrire la log
	 */
	public static void setTextArea(JTextArea jTextArea) {
		TextAreaAppender.jTextArea = jTextArea;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.apache.log4j.WriterAppender#append(LoggingEvent)
	 */
	@Override
	public void append(LoggingEvent loggingEvent) {
		final String message = this.layout.format(loggingEvent);

		// Append formatted message to textarea using the Swing Thread.
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				jTextArea.append(message);
			}
		});
	}
}