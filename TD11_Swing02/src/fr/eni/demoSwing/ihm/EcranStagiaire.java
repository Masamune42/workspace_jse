package fr.eni.demoSwing.ihm;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class EcranStagiaire extends JFrame {

	private JTextField txtNom;
	private JPanel panBoutons;
	private JButton btnAjouter, btnAnnuler;
	
	public EcranStagiaire() {
		this.setTitle("Stagiaire");
		this.setVisible(true);
		setLocationRelativeTo(null);
		this.setSize(new Dimension(500, 250));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initIHM();
	}
	
	private void initIHM(){
		JPanel panel = new JPanel();
		panel.setOpaque(true);
		//Ajouter les composants au panel
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		//Ligne 1
		gbc.gridy = 0;
		gbc.gridx = 0;
		panel.add(new JLabel("Nom: "), gbc );
		gbc.gridx = 1;
		panel.add(getTxtNom());
		
		//Ligne 2
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		panel.add(getPanBoutons());
		
		//Attacher le panel à l'écran
		this.setContentPane(panel);
	}

	public JTextField getTxtNom() {
		if( txtNom ==null){
			txtNom = new JTextField(30);
		}
		return txtNom;
	}

	public JPanel getPanBoutons() {
		if(panBoutons == null){
			panBoutons = new JPanel();
			panBoutons.add(getBtnAjouter());
			panBoutons.add(getBtnAnnuler());
		}
		return panBoutons;
	}

	public JButton getBtnAjouter() {
		if(btnAjouter == null){
			btnAjouter = new JButton("Ajouter");
			btnAjouter.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("Ajouter");
					
				}
			});
		}
		return btnAjouter;
	}

	public JButton getBtnAnnuler() {
		if(btnAnnuler==null){
			btnAnnuler = new  JButton("Annuler");
		}
		return btnAnnuler;
	}
	
	
}
