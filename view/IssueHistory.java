package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import model.Issue;

public class IssueHistory extends JDialog {
	
	private static final long serialVersionUID = 1L;

	public IssueHistory(Issue i, String history) {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		this.setResizable(false);
		this.setTitle(CommonString.ADDEMPLOYEE);
		this.setBounds((dim.width-400)/2, 10, 400, 510);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		PatternPanel issPanel = new PatternPanel(CommonString.ISSBG);
		issPanel.setLayout(new BorderLayout());
		this.setContentPane(issPanel);
		
		JLabel issName = new JLabel(i.getName(), JLabel.CENTER);
		issName.setOpaque(false);
		issName.setPreferredSize(new Dimension(380, 50));
		issName.setFont(Theme.BIGGER_FONT);
		issName.setForeground(Theme.getColor(1));
		
		issPanel.add(issName, BorderLayout.NORTH);
		
		JScrollPane employeeScrollPane = new JScrollPane();
		employeeScrollPane.setOpaque(false);
		issPanel.add(employeeScrollPane, BorderLayout.CENTER);
		
		JTextArea historyTextArea = new JTextArea();
		historyTextArea.setForeground(Theme.getColor(1));
		historyTextArea.setFont(Theme.SMALLER_FONT);
		historyTextArea.setPreferredSize(new Dimension(450, 260));
		historyTextArea.setLineWrap(true);
		historyTextArea.setWrapStyleWord(true);
		historyTextArea.setEditable(false);
		historyTextArea.setText(history);
		employeeScrollPane.setViewportView(historyTextArea);
	}

}
