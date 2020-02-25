package manipSwingBoitesDialogue;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class BoiteInput extends JDialog implements ActionListener{

	private JButton boutonOui, boutonAnnul;
	private JTextField saisie;
	private String info = null;
	
	public String getInfo(){
		return info;
	}
	
	public BoiteInput(JFrame parent, String message) {
		super(parent, "SAISIE", true);
		setSize(240, 150);
		//mise en place des composants (boutons, etiquette)
		Container contenu = getContentPane();
		contenu.setLayout(new FlowLayout());
		JLabel texte = new JLabel(message);
		contenu.add(texte);
		saisie = new JTextField(20);
		contenu.add(saisie);
		boutonOui = new JButton("Oui");
		boutonOui.addActionListener(this);
		contenu.add(boutonOui);
		boutonAnnul = new JButton("Annul");
		boutonAnnul.addActionListener(this);
		contenu.add(boutonAnnul);
	}
	
	public void actionPerformed(ActionEvent e){
		if (e.getSource()== boutonOui) info=saisie.getText();
		setVisible(false);
	}

}
