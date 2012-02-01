
package mobile.web.webxt.client.form.validations;

public class ValidateIdentification {
	
	public enum IdType {
	    CED, RUC 
	}
	
    public static final int NUMERO_DE_PROVINCIAS = 24;//22;   
    
    public boolean isValid(String cedula, IdType type) {
    	
    	if(type==IdType.RUC){
    		if(cedula.length()<13){
    			return false;
    		}else{
    			cedula=cedula.substring(0, 10);
    		}
    	}
    	
        //verifica que tenga 10 d�gitos y que contenga solo valores num�ricos
        if (!((cedula.length() == 10) && cedula.matches("^[0-9]{10}$"))) {
            return false;
        }

        //verifica que los dos primeros d�gitos correspondan a un valor entre 1 y NUMERO_DE_PROVINCIAS
        int prov = Integer.parseInt(cedula.substring(0, 2));

        if (!((prov > 0) && (prov <= NUMERO_DE_PROVINCIAS))) {
            return false;
        }

        //verifica que el �ltimo d�gito de la c�dula sea v�lido
        int[] d = new int[10];

        //Asignamos el string a un array
        for (int i = 0; i < d.length; i++) {
            d[i] = Integer.parseInt(cedula.charAt(i) + "");
        }

        int imp = 0;
        int par = 0;

        //sumamos los duplos de posici�n impar
        for (int i = 0; i < d.length; i += 2) {
            d[i] = ((d[i] * 2) > 9) ? ((d[i] * 2) - 9) : (d[i] * 2);
            imp += d[i];
        }

        //sumamos los digitos de posici�n par
        for (int i = 1; i < (d.length - 1); i += 2) {
            par += d[i];
        }

        //Sumamos los dos resultados
        int suma = imp + par;
        
        //Restamos de la decena superior
        int d10 = Integer.parseInt(String.valueOf(suma + 10).substring(0, 1) +
                "0") - suma;
        
        //Si es diez el d�cimo d�gito es cero        
        d10 = (d10 == 10) ? 0 : d10;

        //si el d�cimo d�gito calculado es igual al digitado la c�dula es correcta
        return d10 == d[9];
    }    
}