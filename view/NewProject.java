package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import com.michaelbaranov.microba.calendar.DatePicker;

public class NewProject extends JDialog {

	private static final long serialVersionUID = 1L;

	private JTextField pjNameTextField;
	private JTextArea messageTextArea;
	private DatePicker datePicker;
	private ImageButton createProjectButton;

	public NewProject() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

		this.setResizable(false);
		this.setTitle(CommonString.NEWPJ_TITLE);
		this.setBounds((dim.width - 600) / 2, 10, 600, 410);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		PatternPanel pjPanel = new PatternPanel(CommonString.ISSBG);
		pjPanel.setLayout(new BorderLayout());
		this.setContentPane(pjPanel);

		JLabel pjName = new JLabel(CommonString.CREATE_PROJECT, JLabel.CENTER);
		pjName.setOpaque(false);
		pjName.setPreferredSize(new Dimension(600, 50));
		pjName.setFont(Theme.BIGGER_FONT);
		pjName.setForeground(Theme.getColor(1));

		pjPanel.add(pjName, BorderLayout.NORTH);

		JPanel centerPanel = new JPanel();
		centerPanel.setOpaque(false);
		pjPanel.add(centerPanel, BorderLayout.CENTER);
		GridBagLayout gbl_centerPanel = new GridBagLayout();
		gbl_centerPanel.columnWidths = new int[] { 0, 0, 0 };
		gbl_centerPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_centerPanel.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_centerPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		centerPanel.setLayout(gbl_centerPanel);

		JLabel nameLabel = new JLabel(ObjectPropertyString.TEN + " Project: ", JLabel.RIGHT);
		nameLabel.setForeground(Theme.getColor(1));
		nameLabel.setFont(Theme.SMALLER_FONT);
		nameLabel.setPreferredSize(new Dimension(200, 30));
		GridBagConstraints gbc_nameLabel = new GridBagConstraints();
		gbc_nameLabel.insets = new Insets(0, 0, 5, 0);
		gbc_nameLabel.fill = GridBagConstraints.EAST;
		gbc_nameLabel.gridx = 0;
		gbc_nameLabel.gridy = 0;
		centerPanel.add(nameLabel, gbc_nameLabel);

		pjNameTextField = new JTextField();
		pjNameTextField.setForeground(Theme.getColor(1));
		pjNameTextField.setFont(Theme.SMALLER_FONT);
		pjNameTextField.setPreferredSize(new Dimension(370, 30));
		GridBagConstraints gbc_pjNameTextField = new GridBagConstraints();
		gbc_pjNameTextField.insets = new Insets(0, 0, 5, 0);
		gbc_pjNameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_pjNameTextField.gridx = 1;
		gbc_pjNameTextField.gridy = 0;
		centerPanel.add(pjNameTextField, gbc_pjNameTextField);

		JLabel dateLabel = new JLabel(CommonString.ESTIMATEAT + ": ", JLabel.RIGHT);
		dateLabel.setForeground(Theme.getColor(1));
		dateLabel.setFont(Theme.SMALLER_FONT);
		dateLabel.setPreferredSize(new Dimension(200, 30));
		GridBagConstraints gbc_dateLabel = new GridBagConstraints();
		gbc_dateLabel.insets = new Insets(0, 0, 5, 0);
		gbc_dateLabel.fill = GridBagConstraints.EAST;
		gbc_dateLabel.gridx = 0;
		gbc_dateLabel.gridy = 1;
		centerPanel.add(dateLabel, gbc_dateLabel);

		datePicker = new DatePicker(new Date(), new SimpleDateFormat("dd/MM/yyyy"));
		datePicker.setForeground(Theme.getColor(1));
		datePicker.setFont(Theme.SMALLER_FONT);
		datePicker.setPreferredSize(new Dimension(370, 30));
		GridBagConstraints gbc_datePicker = new GridBagConstraints();
		gbc_datePicker.insets = new Insets(0, 0, 5, 0);
		gbc_datePicker.fill = GridBagConstraints.HORIZONTAL;
		gbc_datePicker.gridx = 1;
		gbc_datePicker.gridy = 1;
		centerPanel.add(datePicker, gbc_datePicker);

		JLabel messageLabel = new JLabel(CommonString.MOTA + ": ", JLabel.RIGHT);
		messageLabel.setForeground(Theme.getColor(1));
		messageLabel.setFont(Theme.SMALLER_FONT);
		messageLabel.setPreferredSize(new Dimension(200, 30));
		GridBagConstraints gbc_messageLabel = new GridBagConstraints();
		gbc_messageLabel.insets = new Insets(0, 0, 5, 5);
		gbc_messageLabel.anchor = GridBagConstraints.EAST;
		gbc_messageLabel.gridx = 0;
		gbc_messageLabel.gridy = 3;
		centerPanel.add(messageLabel, gbc_messageLabel);

		messageTextArea = new JTextArea();
		messageTextArea.setForeground(Theme.getColor(1));
		messageTextArea.setFont(Theme.EXTRA_SMALLER_FONT);
		messageTextArea.setLineWrap(true);
		messageTextArea.setWrapStyleWord(true);
		// messageTextArea.setPreferredSize(new Dimension(300,100));
		GridBagConstraints gbc_messageTextArea = new GridBagConstraints();
		gbc_messageTextArea.insets = new Insets(0, 0, 5, 0);
		gbc_messageTextArea.fill = GridBagConstraints.BOTH;
		gbc_messageTextArea.gridx = 1;
		gbc_messageTextArea.gridy = 3;
		centerPanel.add(messageTextArea, gbc_messageTextArea);

		JPanel bottomPanel = new JPanel(new FlowLayout());
		bottomPanel.setOpaque(false);
		bottomPanel.setPreferredSize(new Dimension(550, 70));
		pjPanel.add(bottomPanel, BorderLayout.SOUTH);

		createProjectButton = new ImageButton(CommonString.CREATEPJ, CommonString.CREATEPJH);
		createProjectButton.setPreferredSize(new Dimension(200, 50));
		bottomPanel.add(createProjectButton);
	}

	public void addCreatePjButtonActionListener(ActionListener listener) {
		createProjectButton.addActionListener(listener);
	}

	public String getEstimatedCloseDateAsString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String formattedDate = sdf.format(datePicker.getDate());
		return formattedDate;
	}

	public String getPjNameFromTextField() {
		return pjNameTextField.getText();
	}

	public String getDescriptionFromTextArea() {
		return messageTextArea.getText();
	}
}
