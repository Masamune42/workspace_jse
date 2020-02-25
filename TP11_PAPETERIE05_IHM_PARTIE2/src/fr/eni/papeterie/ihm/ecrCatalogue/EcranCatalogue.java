package fr.eni.papeterie.ihm.ecrCatalogue;

import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import fr.eni.papeterie.bll.BLLException;
import fr.eni.papeterie.bll.CatalogueManager;


@SuppressWarnings("serial")
public class EcranCatalogue extends JFrame {
	
	private CatalogueManager mger;
	
	public EcranCatalogue() {
		super("Catalogue");
		
		try {
			mger = CatalogueManager.getInstance();
		} catch (BLLException e) {
			e.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Toolkit tk =Toolkit.getDefaultToolkit();
		this.setIconImage(tk.getImage(getClass().getResource("../resources/aim.png")));

		
		initComposants();
		 

	        
	}
	
	private void initComposants(){
		JPanel mainContent = new JPanel();
		mainContent.setOpaque(true);
		
		mainContent.setLayout(new GridLayout(1, 0));
		TableCatalogue tblCatalogue = new TableCatalogue(mger.getCatalogue());

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(tblCatalogue);

        //Add the scroll pane to this panel.
        mainContent.add(scrollPane);

       
		
		this.setContentPane(mainContent);
	}
	

}
