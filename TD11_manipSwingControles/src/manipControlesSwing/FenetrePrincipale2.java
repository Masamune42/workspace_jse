package manipControlesSwing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class FenetrePrincipale2 extends JFrame{
	private JPanel conteneur,conteneurBoutonRadio;
	private JButton btnEtat;
	private JRadioButton cercle,rectangle,triangle;
	
	public FenetrePrincipale2(){
		setTitle("fenetre bouton radio");
		setSize(new Dimension(500, 400));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		initComposants();
	}// fin du construteur
	
	private void initComposants(){	
		getContentPane().add(getConteneur());
	}

	//************** creation des conteneurs intermediaires *************
	public JPanel getConteneur(){
		if (conteneur == null){
			conteneur = new JPanel();
			conteneur.setLayout(new BorderLayout());
			conteneur.add(getBtnEtat(), BorderLayout.SOUTH);
			conteneur.add(getConteneurBoutonRadio(), BorderLayout.CENTER);
		}
		return conteneur;
	}

	public JPanel getConteneurBoutonRadio(){
		if (conteneurBoutonRadio == null){
			conteneurBoutonRadio = new JPanel();
			ButtonGroup groupe = new ButtonGroup();
			groupe.add(getBRCercle());
			groupe.add(getBRRectangle());
			groupe.add(getBRTriangle());
			conteneurBoutonRadio.add(getBRCercle());
			conteneurBoutonRadio.add(getBRRectangle());
			conteneurBoutonRadio.add(getBRTriangle());
		}
		return conteneurBoutonRadio;
	}
	//************** creation des boutons et checkBox et modification des comportements *************
	
	//bouton Etat
	public JButton getBtnEtat() {
		if(btnEtat == null){
			btnEtat = new JButton("Etat");
			btnEtat.setToolTipText("etat");
			btnEtat.setForeground(Color.BLUE);
			btnEtat.setFont(new Font("Serif", Font.BOLD, 12));
			btnEtat.setCursor(new Cursor(Cursor.HAND_CURSOR));
			btnEtat.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if (cercle.isSelected()) System.out.println("cercle");
					if (rectangle.isSelected()) System.out.println("rectangle");
					if (triangle.isSelected()) System.out.println("triangle");
				}
			});
		}
		return btnEtat;
	}

	public JRadioButton getBRCercle() {
		if(cercle == null){
			cercle = new JRadioButton("Cercle");
			cercle.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					Object source = e.getSource();
					if (source==cercle) System.out.println("item case cercle");
					
				}
			});
		}
		return cercle;
	}

	public JRadioButton getBRRectangle() {
		if(rectangle == null){
			rectangle = new JRadioButton("rectangle");
/*			rectangle.addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent e) {
					Object source = e.getSource();
					if (e.getStateChange()==1) {
						System.out.println("case rectangle selectionnée");
					}else{
						System.out.println("case rectangle déselectionnée");
					}
					
				}
			});
*/
			rectangle.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					Object source = e.getSource();
					if (source==rectangle) System.out.println("item case rectangle");
					
				}
			});
}
		return rectangle;
	}

	public JRadioButton getBRTriangle() {
		if(triangle == null){
			triangle = new JRadioButton("triangle");
			triangle.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					Object source = e.getSource();
					if (source==triangle) System.out.println("item case triangle");
					
				}
			});
		}
		return triangle;
	}

}
