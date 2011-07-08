package microxt.entity.test;

import microxt.entity.session.LocalParameter;
import microxt.entity.session.ParameterEnum;
import microxt.entity.util.JpaManager;
import microxt.entity.util.PersistenceFactory;

public class Main {

    public static void main(String[] args) {
        new Main().procesar();
    }

    public void procesar() {
        try {
        	PersistenceFactory.createEntityManagerFactory();
        	JpaManager.createEntityManager();
        	
        	JpaManager.beginTransaction();
            
            LocalParameter.set(ParameterEnum.COMPANY,"HNDT");
            LocalParameter.set(ParameterEnum.LANGUAGE,"ES");
            System.out.println(LocalParameter.get(ParameterEnum.COMPANY,String.class));
            System.out.println(LocalParameter.get(ParameterEnum.LANGUAGE,String.class));
            
            //Buscar
//            ParameterId id = new ParameterId();
//            id.setParameterId("DIASRETENCION");
//            Parameter param = JpaManager.findActiveEntity(id);
//            System.out.println(param);
            
            //Crear
//            ParameterId id2 = new ParameterId("NUEVO");
//            JpaManager.create(id2);
//            DataType dt = JpaManager.getReference(DataType.class, "STRING");
//            Parameter param2 = new Parameter("NUEVO");
//            param2.setDataType(dt);
//            param2.setDescription("ASFDASFDASDF");
//            param2.setParameterIdBean(id2);
//            param2.setValue("QWREQWREQWRE");
//            JpaManager.create(param2);
            
            //Actualizar
//            ParameterId id3 = new ParameterId("DIASRETENCION");
//            Parameter param3 = JpaManager.findActiveEntity(id3);
//            param3.setValue("88");
//            JpaManager.update(param3);

			// Eliminar
//			ParameterId id4 = new ParameterId("NUEVO");
//			Parameter param4 = JpaManager.findActiveEntity(id4);
//			JpaManager.delete(param4);
            //JpaManager.
  
            JpaManager.commitTransaction();
        } catch (Exception e) {
			e.printStackTrace();
        } finally {
            JpaManager.close();
            PersistenceFactory.close();
        }
    }
}