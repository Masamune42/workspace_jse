package fr.eni_ecole.cours.ihm.catalogue;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import fr.eni_ecole.cours.bo.Eleve;

@SuppressWarnings("serial")
public class TableCatalogue extends JTable {

	public TableCatalogue(List<Eleve> catalogue) {
		super(new TableCatalogueModel(catalogue));
        setPreferredScrollableViewportSize(new Dimension(450, 70));
         setFillsViewportHeight(true);
		
        for (int i=0; i<TableCatalogueModel.COLUMN_NAMES.length;i++){
        	this.getColumnModel().getColumn(i).setPreferredWidth(TableCatalogueModel.COLUMN_HEIGHT[i]);
        }
        
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
	
	
	

	
}
