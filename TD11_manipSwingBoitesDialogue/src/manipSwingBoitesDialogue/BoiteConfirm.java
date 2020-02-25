package manipSwingBoitesDialogue;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class BoiteConfirm extends JDialog implements ActionListener {
	private JButton boutonOui, boutonNon, boutonAnnul;
	private int etat=-1;
	
	public int getEtat(){
		return etat;
	}
	
	public BoiteConfirm(JFrame parent, String message) {
		super(parent, "CHOIX", true);
		setSize(400, 200);
		//mise en place des composants (boutons, etiquette)
		Container contenu = getContentPane();
		contenu.setLayout(new FlowLayout());
		JLabel texte = new JLabel(message);
		contenu.add(texte);
		boutonOui = new JButton("Oui");
		boutonOui.addActionListener(this);
		contenu.add(boutonOui);
		boutonNon = new JButton("Non");
		boutonNon.addActionListener(this);
		contenu.add(boutonNon);
		boutonAnnul = new JButton("Annul");
		boutonAnnul.addActionListener(this);
		contenu.add(boutonAnnul);
	}
	
	public void actionPerformed(ActionEvent e){
		if (e.getSource()== boutonOui) etat=0;
		if (e.getSource()== boutonNon) etat=1;
		if (e.getSource()== boutonAnnul) etat=2;
		setVisible(false);
	}

}
