package mobile.web.webxt.shared;

public class TestData {

	public static Folder getTreeModel() {
		
		Folder[] folders = new Folder[] {
				new Folder("Generales", new Folder[] {
						new Folder("Parametros de Entorno", new ProcessTreeNode[] {
								new ProcessTreeNode("G1-00 Ingreso Idiomas"),
								new ProcessTreeNode("G1-01 Parametros del Sistema"), }),
						
						new Folder("Parametros Geograficos", new ProcessTreeNode[] {
								new ProcessTreeNode("G2-00 Ingreso Barrios"),
								new ProcessTreeNode("G2-01 Ingreso Ciudades"), 
								new ProcessTreeNode("G2-02 Ingreso Paises"), 
								new ProcessTreeNode("G2-03 Ingreso Regiones"),}), }),

				new Folder("Seguridades",new Folder[] {
						new Folder("Usuarios",new ProcessTreeNode[] {
								new ProcessTreeNode("S1-00 Definicion de Usuarios"),
								new ProcessTreeNode("S1-01 Ingreso de Usuarios"),
								new ProcessTreeNode("S1-02 Ingreso tipos de usuario"), }),
						new Folder(	"Roles",new ProcessTreeNode[] {
								new ProcessTreeNode("S2-01 Definicion Rol"),
								new ProcessTreeNode("S2-02 Ingreso Roles"), }), }),

				new Folder("Personas", new Folder[] { 
						new Folder("Parametros Basicos",new ProcessTreeNode[] {
								new ProcessTreeNode("P1-00 Ingreso Clientes"),
								new ProcessTreeNode("P1-01 Mildred Starnes"),
								new ProcessTreeNode("P1-02 Claudio Engle"), }), 
						new Folder("Datos Personas",new ProcessTreeNode[] {
								new ProcessTreeNode("P2-00 Ingreso Personas"),
								new ProcessTreeNode("P2-01 Ingreso Direcciones"),
								new ProcessTreeNode("P2-02 Ingreso Telefonos"), }),		
				
				}), };

		Folder root = new Folder("root");
		for (int i = 0; i < folders.length; i++) {
			root.add((Folder) folders[i]);
		}

		return root;
	}
	
}
