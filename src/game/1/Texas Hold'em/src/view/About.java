package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class About extends JDialog {

	private JButton okButton;
	private ImageIcon icon;
	
	public About(JFrame owner) {
		super(owner, "BrooklynBridgePoker", true); // true -> Modal
		
		okButton = new JButton("Close");
		icon = new ImageIcon("images/loggo.png");
	
		JLabel image = new JLabel(icon);
		JLabel text = new JLabel("<html><center><b>BrooklynBridgePoker</b><br>Version 1.0<br><br>"
		+"Made By<br>Brooklyn Bridge team<br></center></html>");
		
		
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		panel.setPreferredSize(new Dimension(250,180));

		panel.add(image);
		panel.add(text);
		panel.add(okButton);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		this.setLocation(((owner.getWidth()/2)-125), 200);
		this.getContentPane().add(panel);
		this.pack();
		this.setResizable(false);
	}


	private static final long serialVersionUID = 1L;
}
