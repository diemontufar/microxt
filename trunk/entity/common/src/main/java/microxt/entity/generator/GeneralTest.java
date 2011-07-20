package microxt.entity.generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.persistence.internal.jpa.parsing.UpperNode;

public class GeneralTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//System.out.println(a.toString().replaceAll("[\\[\\]]", ""));//a.toString().re
		
		System.out.println(lowerCamelCase("ASDFSADF"));
		System.out.println(lowerCamelCase("ASDFSADF_ASDF_ADF"));
	}
	
	private static String upperCamelCase(String string){
		StringBuilder sb = new StringBuilder();
		for( String s : string.split("_") ) {
		    sb.append( s.substring(0,1).toUpperCase() );
		    sb.append( s.substring(1).toLowerCase() );
		}		
		return sb.toString();
	}
	private static String lowerCamelCase(String string){
		StringBuilder sb = new StringBuilder();
		Boolean primera = true;
		for( String s : string.split("_") ) {
			if(primera){
				sb.append( s.substring(0,1).toLowerCase() );
				primera=false;
			}else{
				sb.append( s.substring(0,1).toUpperCase() );
			}
			sb.append( s.substring(1).toLowerCase() );
		}		
		return sb.toString();
	}

}
