package mobile.core.common.test;

import mobile.entity.manager.JPManager;
import mobile.entity.manager.JPManagerFactory;
import mobile.entity.manager.util.LocalParameter;
import mobile.entity.manager.util.ParameterEnum;
import mobile.entity.security.UserAccess;
import mobile.entity.security.UserAccessPk;
import mobile.entity.security.UserAccount;
import mobile.entity.security.UserAccountPk;

public class Loggin {

	public static void main(String[] args) {
		String user = "ADMIN";
		String password = "ADMIN";
		(new Loggin()).loggin(user, password);
	}
	
	public Boolean loggin(String user, String password){
		Boolean exists = false;
		try {
			// Initialize Jpa
			JPManagerFactory.createEntityManagerFactory("central");
			JPManager.createEntityManager();
			// Initialize global parameters
			LocalParameter.set(ParameterEnum.COMPANY, "MXT");
			LocalParameter.set(ParameterEnum.LANGUAGE, "ES");
			
			// Process
			// Find the user-account 
			UserAccountPk userPk = new UserAccountPk(user);
			UserAccount userAcc = JPManager.find(UserAccount.class, userPk);
			
			if (userAcc != null){
				System.out.println("Usuario encontrado:");
				System.out.println(userAcc);
				
				// Find the password and compare 
				UserAccessPk accessPk = new UserAccessPk(user);
				UserAccess userAccess = JPManager.find(UserAccess.class,accessPk);
				
				if(userAccess!=null && userAccess.getUserKey().compareTo(password)==0){
					System.out.println("Password correcto");
					System.out.println(userAccess);
					exists = true;
				} else{
					System.out.println("Error de autenticacion");
				}
			} 
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JPManager.close();
			JPManagerFactory.close();
		}

		return exists;
	}

}
