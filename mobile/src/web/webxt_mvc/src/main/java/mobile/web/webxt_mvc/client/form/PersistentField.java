package mobile.web.webxt_mvc.client.form;

public interface PersistentField {
	
	public String getEntity();
	public String getField();
	public int getRegister();
	
	public String setEntity(String entity);
	public String setField(String field);
	public int setRegister(int register);
		
}
