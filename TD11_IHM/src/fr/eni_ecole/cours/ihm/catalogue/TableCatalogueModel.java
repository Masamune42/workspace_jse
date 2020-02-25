package fr.eni_ecole.cours.ihm.catalogue;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.eni_ecole.cours.bo.Eleve;

@SuppressWarnings("serial")
public class TableCatalogueModel extends AbstractTableModel {

		private List<Eleve> catalogue;
		
		public static final String[] COLUMN_NAMES = {"Nom",
	            "Prenom",
	            "Adresse"};
		public static final int[] COLUMN_HEIGHT = {100, 100, 200};
		
		public TableCatalogueModel(List<Eleve> eleves) {
			catalogue = eleves;
		}
		
		public String getColumnName(int index) {
	        return COLUMN_NAMES[index];
	    }
	
		@Override
		public int getRowCount() {
			return catalogue.size();
		}
	
		@Override
		public int getColumnCount() {
			return COLUMN_NAMES.length;
		}
	
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			Object val = null;
			switch(COLUMN_NAMES[columnIndex]){
				case "Nom":
					val = catalogue.get(rowIndex).getNom();
					break;
				case "Prenom":
					val = catalogue.get(rowIndex).getPrenom();
					break;
				case "Adresse":
					val = catalogue.get(rowIndex).getAdresse();
					break;
			}
			return val;
		}
		 
//	 public void setValueAt(Object value, int row, int col) {
//		 switch(col){
//			case TableCatalogue.COL_NOM:
//				 catalogue.getArticle(row).setReference((String) value);
//				break;
//			case TableCatalogue.COL_MARQUE:
//				catalogue.getArticle(row).setMarque((String) value);
//				break;
//			case TableCatalogue.COL_PU:
//				catalogue.getArticle(row).setPrixUnitaire((Float) value);
//				break;
//			case TableCatalogue.COL_STOCK:
//				catalogue.getArticle(row).setQteStock((Integer) value);
//				break;
//		}
//	     fireTableCellUpdated(row, col);
//	 }
	 
//	 public Class getColumnClass(int c) {
//	     return getValueAt(0, c).getClass();
//	 }
	 
//	 public boolean isCellEditable(int row, int col)
//	 { return false; }
}
