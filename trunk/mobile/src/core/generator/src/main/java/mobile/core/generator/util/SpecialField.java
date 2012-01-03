package mobile.core.generator.util;

import mobile.entity.common.EntityField;
import mobile.entity.common.EntityFieldPk;

public class SpecialField {

	public EntityField getCompanyField() {
		EntityField companyField = new EntityField();
		EntityFieldPk entityFieldPk = new EntityFieldPk();
		entityFieldPk.setFieldId("COMPANY_ID");
		companyField.setPk(entityFieldPk);
		companyField.setFieldOrder(0);
		companyField.setDataTypeId("String");
		companyField.setDataSize(4);
		companyField.setDataScale(0);
		companyField.setPrimaryKey(true);
		companyField.setUniqueKey(false);
		companyField.setNullable(false);
		companyField.setDescription("Company id");
		return companyField;
	}

	public EntityField getLanguageField() {
		EntityField languageField = new EntityField();
		EntityFieldPk entityFieldPk = new EntityFieldPk();
		entityFieldPk.setFieldId("LANGUAGE_ID");
		languageField.setPk(entityFieldPk);
		languageField.setFieldOrder(0);
		languageField.setDataTypeId("String");
		languageField.setDataSize(2);
		languageField.setDataScale(0);
		languageField.setPrimaryKey(true);
		languageField.setUniqueKey(false);
		languageField.setNullable(false);
		languageField.setDescription("Language id");
		return languageField;
	}

	public EntityField getExpiredField() {
		EntityField expiredField = new EntityField();
		EntityFieldPk entityFieldPk = new EntityFieldPk();
		entityFieldPk.setFieldId("EXPIRED");
		expiredField.setPk(entityFieldPk);
		expiredField.setFieldOrder(0);
		expiredField.setDataTypeId("Date");
		expiredField.setDataSize(0);
		expiredField.setDataScale(0);
		expiredField.setPrimaryKey(true);
		expiredField.setUniqueKey(false);
		expiredField.setNullable(false);
		expiredField.setDescription("Expired date");
		return expiredField;
	}

	public EntityField getCreatedField() {
		EntityField createdField = new EntityField();
		EntityFieldPk entityFieldPk = new EntityFieldPk();
		entityFieldPk.setFieldId("CREATED");
		createdField.setPk(entityFieldPk);
		createdField.setFieldOrder(0);
		createdField.setDataTypeId("Date");
		createdField.setDataSize(0);
		createdField.setDataScale(0);
		createdField.setPrimaryKey(false);
		createdField.setUniqueKey(false);
		createdField.setNullable(false);
		createdField.setDescription("Created date");
		return createdField;
	}

	public EntityField getVersionField() {
		EntityField versionField = new EntityField();
		EntityFieldPk entityFieldPk = new EntityFieldPk();
		entityFieldPk.setFieldId("VERSION");
		versionField.setPk(entityFieldPk);
		versionField.setFieldOrder(0);
		versionField.setDataTypeId("Long");
		versionField.setDataSize(10);
		versionField.setDataScale(0);
		versionField.setPrimaryKey(false);
		versionField.setUniqueKey(false);
		versionField.setNullable(false);
		versionField.setDescription("Optimistic locking");
		versionField.setDefaultValue("0");
		return versionField;
	}
}
