package com.notoon.idc.ui;

import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.notoon.idc.exceptions.InvalidInputException;
import com.notoon.idc.services.ConverterService;
import com.notoon.idc.util.constants.Constants;
import com.notoon.idc.util.log.TextAreaAppender;

/**
 * Interface utilisateur de l'application
 *
 * @author t_dc
 */
public class UserInterface implements ActionListener, FocusListener {

	private static Logger log = LoggerFactory.getLogger(UserInterface.class.getName());

	/** Service de conversion */
	private ConverterService service;

	/** UI Fields */
	private JFrame frmIdConverter;
	private JTextField txtBase10;
	private JTextField txtBase32;
	private JTextField txtDate;
	private JTextField txtSelect;
	private JTextField txtUpdate;
	private JTextField txtDelete;
	private static JTextArea txtAreaLog;
	JButton btnClearLog;
	JButton btnReset;

	/**
	 * Constructeur
	 */
	public UserInterface(ConverterService service) {
		// Récupération du service
		this.service = service;

		// Initialisation de la fenêtre
		initialize();
		frmIdConverter.setVisible(true);

		// Initialisation de la log
		TextAreaAppender.setTextArea(txtAreaLog);

		log.info("ID Converter par Tuan Do Cao");
		log.info("How-to : ENTREE - CTRL+C - CTRL+V\n");
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		// Action lorsque ENTREE sur le champ BASE 10
		if (e.getSource() == txtBase10) {
			convertFromRadix10();
		}
		// Action lorsque ENTREE sur le champ BASE 32
		else if (e.getSource() == txtBase32) {
			convertFromRadix32();
		}
		// Action lorsque ENTREE sur le champ DATE
		else if (e.getSource() == txtDate) {
			convertFromDate();
		}
		// Action sur bouton "Clear log"
		else if (e.getSource() == btnClearLog) {
			txtAreaLog.setText(Constants.EMPTY_STR);
		}
		// Action sur bouton "Reset"
		else if (e.getSource() == btnReset) {
			emptyTextFields();
		}

		// Scroll vers le dernier événement de la log
		txtAreaLog.setCaretPosition(txtAreaLog.getDocument().getLength());

		// Positionne le curseur au début dans chaque JTextField (utile pour
		// voir le début des requêtes SQL surtout)
		positionCursor();
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see java.awt.event.FocusListener#focusGained(FocusEvent)
	 */
	@Override
	public void focusGained(FocusEvent e) {
		// Un focus sur n'importe quel champ sélectionne tout le texte
		((JTextField) e.getSource()).selectAll();
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see java.awt.event.FocusListener#focusLost(FocusEvent)
	 */
	@Override
	public void focusLost(FocusEvent e) {
	}

	/**
	 * Initialise le contenu de la frame
	 */
	private void initialize() {
		frmIdConverter = new JFrame();
		frmIdConverter.setResizable(false);
		frmIdConverter.setTitle("ID Converter");
		frmIdConverter.setBounds(100, 100, 490, 475);
		frmIdConverter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmIdConverter.getContentPane().setLayout(null);

		JLabel lblBase10 = new JLabel("BASE 10 :");
		lblBase10.setBounds(10, 14, 70, 14);
		frmIdConverter.getContentPane().add(lblBase10);

		txtBase10 = new JTextField();
		txtBase10.setBounds(90, 11, 289, 20);
		txtBase10.setColumns(10);
		txtBase10.addActionListener(this);
		txtBase10.addFocusListener(this);
		frmIdConverter.getContentPane().add(txtBase10);

		JLabel lblBase32 = new JLabel("BASE 32 :");
		lblBase32.setBounds(10, 45, 70, 14);
		frmIdConverter.getContentPane().add(lblBase32);

		txtBase32 = new JTextField();
		txtBase32.setBounds(90, 42, 289, 20);
		txtBase32.setColumns(10);
		txtBase32.addActionListener(this);
		txtBase32.addFocusListener(this);
		frmIdConverter.getContentPane().add(txtBase32);

		JLabel lblDate = new JLabel("DATE DB2 :");
		lblDate.setBounds(10, 76, 70, 14);
		frmIdConverter.getContentPane().add(lblDate);

		txtDate = new JTextField();
		txtDate.setBounds(90, 73, 289, 20);
		txtDate.setColumns(10);
		txtDate.addActionListener(this);
		txtDate.addFocusListener(this);
		frmIdConverter.getContentPane().add(txtDate);

		ImageIcon resetButtonIcon = createImageIcon("reset.png");
		btnReset = new JButton(resetButtonIcon);
		btnReset.setBounds(389, 11, 85, 81);
		btnReset.addActionListener(this);
		frmIdConverter.getContentPane().add(btnReset);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 104, 463, 2);
		frmIdConverter.getContentPane().add(separator);

		JLabel lblSelect = new JLabel("SELECT :");
		lblSelect.setBounds(10, 120, 70, 14);
		frmIdConverter.getContentPane().add(lblSelect);

		txtSelect = new JTextField();
		txtSelect.setToolTipText("SELECT");
		txtSelect.setFont(new Font("Consolas", Font.PLAIN, 11));
		txtSelect.setBounds(90, 117, 384, 20);
		txtSelect.setColumns(10);
		txtSelect.addFocusListener(this);
		frmIdConverter.getContentPane().add(txtSelect);

		JLabel lblUpdate = new JLabel("UPDATE :");
		lblUpdate.setBounds(10, 150, 70, 14);
		frmIdConverter.getContentPane().add(lblUpdate);

		txtUpdate = new JTextField();
		txtUpdate.setToolTipText("UPDATE");
		txtUpdate.setFont(new Font("Consolas", Font.PLAIN, 11));
		txtUpdate.setBounds(90, 148, 384, 20);
		txtUpdate.setColumns(10);
		txtUpdate.addFocusListener(this);
		frmIdConverter.getContentPane().add(txtUpdate);

		JLabel lblDelete = new JLabel("DELETE :");
		lblDelete.setBounds(10, 181, 70, 14);
		frmIdConverter.getContentPane().add(lblDelete);

		txtDelete = new JTextField();
		txtDelete.setToolTipText("DELETE");
		txtDelete.setFont(new Font("Consolas", Font.PLAIN, 11));
		txtDelete.setBounds(90, 179, 384, 20);
		txtDelete.setColumns(10);
		txtDelete.addFocusListener(this);
		frmIdConverter.getContentPane().add(txtDelete);

		txtAreaLog = new JTextArea();
		txtAreaLog.setLineWrap(true);
		txtAreaLog.setFont(new Font("Consolas", Font.PLAIN, 13));
		txtAreaLog.setMargin(new Insets(2, 3, 2, 3));

		JScrollPane scrollPane = new JScrollPane(txtAreaLog);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 210, 464, 196);
		frmIdConverter.getContentPane().add(scrollPane);

		ImageIcon clearButtonIcon = createImageIcon("clear.png");
		btnClearLog = new JButton("Clear log", clearButtonIcon);
		btnClearLog.setBounds(353, 415, 120, 23);
		btnClearLog.addActionListener(this);
		frmIdConverter.getContentPane().add(btnClearLog);
	}

	/**
	 * Test de validité sur la valeur passée en entrée
	 *
	 * @param value
	 *            La valeur à tester (doit être déjà trimmée)
	 * @param base
	 *            Dans quelle base est renseignée la valeur
	 * @return true La valeur est valide
	 * @throws InvalidInputException
	 *             La valeur est invalide
	 */
	private boolean validate(String value, int base) throws InvalidInputException {
		// Si valeur non vide
		if (StringUtils.isNotBlank(value)) {
			// Check sur base 10
			if (base == Constants.BASE_10) {
				// OK si tout numérique
				if (StringUtils.isNumeric(value)) {
					return true;
				}
				// KO -> exception
				else {
					throw new InvalidInputException("La valeur en B10 doit être numérique !\n");
				}
			}
			// Check sur base 32
			else if (base == Constants.BASE_32) {
				// OK si tout alpha-numérique
				if (StringUtils.isAlphanumeric(value)) {
					return true;
				}
				// KO -> exception
				else {
					throw new InvalidInputException("La valeur en B32 doit être alpha-numérique !\n");
				}
			}
			// Check sur la date
			else {
				if(validateDateFormat(value)) {
					return true;
				}
				// KO -> exception
				else {
					throw new InvalidInputException("Le format de la date est invalide !\n");
				}
			}
		}
		// Vide -> exception
		else {
			throw new InvalidInputException("La valeur ne peut pas être vide !\n");
		}
	}

	/**
	 * Vérification du format de la date
	 *
	 * @param date
	 *            La date entrée
	 * @return true Le format de la date est correct
	 */
	private boolean validateDateFormat(String date) {
		Pattern datePattern = Pattern.compile(Constants.DATE_FORMAT_REGEX);
		Matcher matcher = datePattern.matcher(date);
		return matcher.matches();
	}

	/**
	 * Opérations lancées lorsque que ENTREE sur le champ BASE 10
	 */
	private void convertFromRadix10() {
		try {
			// trim car getText() ne renvoit pas null
			String base10 = txtBase10.getText().trim();

			// validation -> EntreeInvalideException si fail
			validate(base10, Constants.BASE_10);

			// conversion en B32
			String base32 = service.toBase32(base10);

			// conversion en date
			String formatDate = service.toDateFormat(base10);

			// Remplissage des champs résultats
			txtBase32.setText(base32);
			txtDate.setText(formatDate + Constants.MICROSECONDS);

			// Remplissage des champs requêtes
			txtSelect.setText(service.getSelectQuery(formatDate));
			txtUpdate.setText(service.getUpdateQuery(formatDate));
			txtDelete.setText(service.getDeleteQuery(formatDate));

			// log
			log.info(" IN B10 : " + base10);
			log.info("OUT B32 : " + base32);
			log.info("OUT DB2 : " + formatDate + Constants.MICROSECONDS + "\n");
		} catch (InvalidInputException ie) {
			log.error(ie.getMessage(), ie);
		} catch (NumberFormatException ne) {
			log.error("La valeur en B10 est hors limites !\n", ne);
		}
	}

	/**
	 * Opérations lancées lorsque que ENTREE sur le champ BASE 32
	 */
	private void convertFromRadix32() {
		try {
			// trim car getText() ne renvoit pas null
			String base32 = txtBase32.getText().trim().toUpperCase();

			// validation -> EntreeInvalideException si fail
			validate(base32, Constants.BASE_32);

			// conversion en B10
			String base10 = service.fromBase32(base32);

			// conversion en date
			String formatDate = service.toDateFormat(base10);

			// Remplissage des champs résultats
			txtBase10.setText(base10);
			txtDate.setText(formatDate + Constants.MICROSECONDS);

			// Remplissage des champs requêtes
			txtSelect.setText(service.getSelectQuery(formatDate));
			txtUpdate.setText(service.getUpdateQuery(formatDate));
			txtDelete.setText(service.getDeleteQuery(formatDate));

			// log
			log.info(" IN B32 : " + base32);
			log.info("OUT B10 : " + base10);
			log.info("OUT DB2 : " + formatDate + Constants.MICROSECONDS + "\n");
		} catch (InvalidInputException ie) {
			log.error(ie.getMessage(), ie);
		} catch (NumberFormatException ne) {
			log.error("La valeur en B32 est hors limites !\n", ne);
		}
	}

	/**
	 * Opérations lancées lorsque que ENTREE sur le champ DATE FORMAT
	 */
	private void convertFromDate() {
		try {
			// trim car getText() ne renvoit pas null
			String inputDate = txtDate.getText().trim();

			// date sans les microsecondes
			String dateToProcess = removeMicroSeconds(inputDate);

			// validation -> EntreeInvalideException si fail
			validate(dateToProcess, Constants.NONE);

			// conversion en B10
			String base10 = service.toBase10(dateToProcess);

			// conversion en B32
			String base32 = service.toBase32(base10);

			// Remplissage des champs résultats
			txtBase10.setText(base10);
			txtBase32.setText(base32);

			// Remplissage des champs requêtes
			txtSelect.setText(service.getSelectQuery(dateToProcess));
			txtUpdate.setText(service.getUpdateQuery(dateToProcess));
			txtDelete.setText(service.getDeleteQuery(dateToProcess));

			// log
			log.info(" IN DB2 : " + inputDate);
			log.info("OUT B10 : " + base10);
			log.info("OUT B32 : " + base32 + "\n");
		} catch (InvalidInputException iie) {
			log.error(iie.getMessage(), iie);
		} catch (ParseException pe) {
			log.error("Le format de la date est invalide !\n", pe);
		} catch (NumberFormatException nfe) {
			log.error("La valeur en B32 est hors limites !\n", nfe);
		}
	}

	/**
	 * Retire les 000 micro-secondes superflues
	 *
	 * @param date
	 *            La date à trimmer
	 * @return La date sans les micro-secondes
	 */
	private String removeMicroSeconds(String date) {
		Pattern datePattern = Pattern.compile(Constants.DATE_FORMAT_REGEX_MICRO);
		Matcher matcher = datePattern.matcher(date);

		// Si la date a bien des micro-secondes, on les retire
		if (matcher.matches()) {
			return date.substring(0, date.length() - 3);
		}

		return date;
	}

	/**
	 * Vide tous les champs de type JTextField
	 */
	private void emptyTextFields() {
		txtBase10.setText(Constants.EMPTY_STR);
		txtBase32.setText(Constants.EMPTY_STR);
		txtDate.setText(Constants.EMPTY_STR);
		txtSelect.setText(Constants.EMPTY_STR);
		txtUpdate.setText(Constants.EMPTY_STR);
		txtDelete.setText(Constants.EMPTY_STR);
	}

	/**
	 * Positionne le curseur au début dans chaque JTextField
	 */
	private void positionCursor() {
		txtBase10.setCaretPosition(Constants.POS_START);
		txtBase32.setCaretPosition(Constants.POS_START);
		txtDate.setCaretPosition(Constants.POS_START);
		txtSelect.setCaretPosition(Constants.POS_START);
		txtUpdate.setCaretPosition(Constants.POS_START);
		txtDelete.setCaretPosition(Constants.POS_START);
	}

	/**
	 * Crée une image à placer sur les JButton
	 *
	 * @param path
	 *            Le chemin vers l'image
	 * @return L'image à placer sur le bouton
	 */
	private static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = UserInterface.class.getResource("/images/" + path);
		return new ImageIcon(imgURL);
	}
}
