package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import model.Developer;
import model.Employee;
import model.Issue;

public class IssueDetail extends JDialog {

	private static final long serialVersionUID = 1L;

	private JTextArea messageTextArea;
	private ImageButton saveButton;
	private ImageButton reloadButton;
	private ImageButton sendButton;

	public IssueDetail(Issue i, Employee e) {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

		this.setResizable(false);
		this.setTitle(i.getName());
		this.setBounds((dim.width - 600) / 2, 10, 600, 410);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		PatternPanel issPanel = new PatternPanel(CommonString.ISSBG);
		issPanel.setLayout(new BorderLayout());
		this.setContentPane(issPanel);

		JLabel issName = new JLabel(i.getName(), JLabel.CENTER);
		issName.setOpaque(false);
		issName.setPreferredSize(new Dimension(600, 50));
		issName.setFont(Theme.BIGGER_FONT);
		issName.setForeground(Theme.getColor(1));

		issPanel.add(issName, BorderLayout.NORTH);

		JPanel messagePanel = new JPanel(new FlowLayout());
		messagePanel.setOpaque(false);
		issPanel.add(messagePanel, BorderLayout.CENTER);

		messageTextArea = new JTextArea();
		messageTextArea.setForeground(Theme.getColor(1));
		messageTextArea.setFont(Theme.SMALLER_FONT);
		messageTextArea.setPreferredSize(new Dimension(450, 260));
		messageTextArea.setLineWrap(true);
		messageTextArea.setWrapStyleWord(true);
		messageTextArea.setText(i.getDescription());
		messagePanel.add(messageTextArea);

		JPanel messageButtonPanel = new JPanel(new FlowLayout());
		messageButtonPanel.setPreferredSize(new Dimension(100, 250));
		messageButtonPanel.setOpaque(false);
		messagePanel.add(messageButtonPanel);

		saveButton = new ImageButton(CommonString.SAVEMESS, CommonString.SAVEMESSH);
		saveButton.setPreferredSize(new Dimension(100, 50));
		messageButtonPanel.add(saveButton);

		reloadButton = new ImageButton(CommonString.RELOAD, CommonString.RELOADH);
		reloadButton.setPreferredSize(new Dimension(100, 50));
		messageButtonPanel.add(reloadButton);

		JPanel bottomPanel = new JPanel(new FlowLayout());
		bottomPanel.setOpaque(false);
		bottomPanel.setPreferredSize(new Dimension(550, 70));
		issPanel.add(bottomPanel, BorderLayout.SOUTH);

		if (e instanceof Developer) {
			sendButton = new ImageButton(CommonString.SENDTOTEST, CommonString.SENDTOTESTH);
			sendButton.setPreferredSize(new Dimension(200, 50));
			bottomPanel.add(sendButton);
		} else {
			sendButton = new ImageButton(CommonString.SENDTODEV, CommonString.SENDTODEVH);
			sendButton.setPreferredSize(new Dimension(200, 50));
			bottomPanel.add(sendButton);
		}
	}

	public void addSaveButtonActionListener(ActionListener listener) {
		saveButton.addActionListener(listener);
	}

	public void addReloadButtonActionListener(ActionListener listener) {
		reloadButton.addActionListener(listener);
	}

	public void addSendButtonActionListener(ActionListener listener) {
		sendButton.addActionListener(listener);
	}

	public String getMessage() {
		return messageTextArea.getText();
	}

	public void setMessage(String message) {
		messageTextArea.setText(message);
	}
}
