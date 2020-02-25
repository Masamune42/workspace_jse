package fr.eni.papeterie.ihm.ecrCatalogue;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import fr.eni.papeterie.bo.Article;

public class TableCatalogue extends JTable {
	private static final long serialVersionUID = 1L;

	public static final int COL_REFERENCE = 0;
	public static final int COL_MARQUE = 1;
	public static final int COL_DESIGNATION = 2;
	public static final int COL_PU = 3;
	public static final int COL_STOCK = 4;

//	private List<Article> catalogue;
	
	public TableCatalogue(List<Article> catalogue) {
		
		super(new TableCatalogueModel(catalogue));
		
        setPreferredScrollableViewportSize(new Dimension(500, 70));
        setFillsViewportHeight(true);

		
		this.getColumnModel().getColumn(COL_REFERENCE).setPreferredWidth(100);
		this.getColumnModel().getColumn(COL_MARQUE).setPreferredWidth(100);
		this.getColumnModel().getColumn(COL_DESIGNATION).setPreferredWidth(200);
		this.getColumnModel().getColumn(COL_PU).setPreferredWidth(50);
		this.getColumnModel().getColumn(COL_STOCK).setPreferredWidth(50);
		
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
	
	
	

	
}
