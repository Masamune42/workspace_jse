package manipControlesSwing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

//*************** creer et placer les composants ******************
/* bonne façon dans la conception d'une interface graphique
1 - créer les différents composants
2 - les placer sur un conteneur intermédiaire
3 - placer le conteneur intermédiaire sur le ContentPane de la fenetre (JFrame)
*/

@SuppressWarnings("serial")
public class FenetrePrincipale1 extends JFrame {

	private JPanel conteneur,conteneurCaseCocher;
	private JButton btnRAZ,btnEtat;
	private JCheckBox cercle,rectangle,triangle;
	
	public FenetrePrincipale1(){
		setTitle("fenetre case à cocher");
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
			conteneur.add(getBtnRAZ(), BorderLayout.NORTH);
			conteneur.add(getBtnEtat(), BorderLayout.SOUTH);
			conteneur.add(getConteneurCaseCocher(), BorderLayout.CENTER);
		}
		return conteneur;
	}

	public JPanel getConteneurCaseCocher(){
		if (conteneurCaseCocher == null){
			conteneurCaseCocher = new JPanel();
			conteneurCaseCocher.add(getCBCercle());
			conteneurCaseCocher.add(getCBRectangle());
			conteneurCaseCocher.add(getCBTriangle());
		}
		return conteneurCaseCocher;
	}
	//************** creation des boutons et checkBox et modification des comportements *************
	
	//bouton RAZ
	public JButton getBtnRAZ() {
		if(btnRAZ == null){
			btnRAZ = new JButton("RAZ");
			btnRAZ.setToolTipText("Remis à zéro");
			btnRAZ.setForeground(Color.BLUE);
			btnRAZ.setFont(new Font("Serif", Font.BOLD, 12));
			btnRAZ.setCursor(new Cursor(Cursor.HAND_CURSOR));
			btnRAZ.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					cercle.setSelected(false);
					rectangle.setSelected(false);
					triangle.setSelected(false);
				}
			});
		}
		return btnRAZ;
	}

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

	public JCheckBox getCBCercle() {
		if(cercle == null){
			cercle = new JCheckBox("Cercle");
			cercle.addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent e) {
					Object source = e.getSource();
					if (source==cercle) System.out.println("item case cercle");
				}
			});
		}
		return cercle;
	}

	public JCheckBox getCBRectangle() {
		if(rectangle == null){
			rectangle = new JCheckBox("rectangle");
			rectangle.addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent e) {
					Object source = e.getSource();
					if (source==rectangle) System.out.println("item case rectangle");
				}
			});
		}
		return rectangle;
	}

	public JCheckBox getCBTriangle() {
		if(triangle == null){
			triangle = new JCheckBox("triangle");
			triangle.addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent e) {
					Object source = e.getSource();
					if (source==triangle) System.out.println("item case triangle");
				}
			});
		}
		return triangle;
	}

}// fin de la classe
