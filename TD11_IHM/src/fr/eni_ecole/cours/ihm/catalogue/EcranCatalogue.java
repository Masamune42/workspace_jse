package fr.eni_ecole.cours.ihm.catalogue;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import fr.eni_ecole.cours.bll.BLLException;
import fr.eni_ecole.cours.bll.EleveManager;


@SuppressWarnings("serial")
public class EcranCatalogue extends JFrame {
	
	private EleveManager manager;
	
	public EcranCatalogue() {
		super("Catalogue");
		
		try {
			manager = EleveManager.getInstance();
		} catch (BLLException e) {
			e.printStackTrace();
		}
		setSize(700, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		Toolkit tk =Toolkit.getDefaultToolkit();
//		this.setIconImage(tk.getImage(getClass().getResource("../resources/aim.png")));
		initComposants();
	}
	
	private void initComposants(){
		JPanel mainContent = new JPanel();
		mainContent.setOpaque(true);
		mainContent.setLayout(new GridLayout(1, 0));
		TableCatalogue tableauCatalogue = new TableCatalogue(manager.getEleves());

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(tableauCatalogue);
        
        //Add the scroll pane to this panel.
        mainContent.add(scrollPane);

        this.setContentPane(mainContent);
	}
	

}
