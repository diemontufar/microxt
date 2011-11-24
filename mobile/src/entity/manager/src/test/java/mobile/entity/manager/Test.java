package mobile.entity.manager;

public class Test {

	public static void main(String[] args) {
		try {
			// Initialize Jpa
			JPManagerFactory.createEntityManagerFactory();
			JPManager.createEntityManager();
			System.out.println("Hola mundo");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JPManager.close();
			JPManagerFactory.close();
		}
	}
}
