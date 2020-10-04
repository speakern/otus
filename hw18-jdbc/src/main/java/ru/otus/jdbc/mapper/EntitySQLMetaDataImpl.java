package ru.otus.jdbc.mapper;

import java.lang.reflect.Field;
import java.util.List;

public class EntitySQLMetaDataImpl<T> implements EntitySQLMetaData {

    private final EntityClassMetaData<T> entityClassMetaData;

    public EntitySQLMetaDataImpl(EntityClassMetaData<T> entityClassMetaData) {
        this.entityClassMetaData = entityClassMetaData;
    }

    @Override
    public String getSelectAllSql() {
        StringBuilder sqlAll = new StringBuilder();
        sqlAll.append("select ");
        sqlAll.append(getFields(entityClassMetaData.getAllFields(), ""));
        sqlAll.append(" from ");
        sqlAll.append(entityClassMetaData.getName());
        return sqlAll.toString();
    }

    @Override
    public String getSelectByIdSql() {
        return getSelectAllSql() + getConditionWhereId();
    }

    @Override
    public String getInsertSql() {
        StringBuilder sqlInsert = new StringBuilder();
        sqlInsert.append("insert into ");
        sqlInsert.append(entityClassMetaData.getName());
        sqlInsert.append("(");
        sqlInsert.append(getFields(entityClassMetaData.getFieldsWithoutId(), ""));
        sqlInsert.append(") values (");
        sqlInsert.append(getQuestions(entityClassMetaData.getFieldsWithoutId().size()));
        sqlInsert.append(")");
        return sqlInsert.toString();
    }

    @Override
    public String getUpdateSql() {
        //update Customers set rating = 200 where snum = 1001;
        StringBuilder sqlUpdate = new StringBuilder();
        sqlUpdate.append("update ");
        sqlUpdate.append(entityClassMetaData.getName());
        sqlUpdate.append(" set ");
        sqlUpdate.append(getFields(entityClassMetaData.getFieldsWithoutId(), " = ?"));
        // sqlUpdate.append(" where id = ?");
        sqlUpdate.append(getConditionWhereId());
        return sqlUpdate.toString();
    }

    private String getFields(List<Field> fieldList, String extended) {
        StringBuilder str = new StringBuilder();
        int lengthArray = fieldList.size();
        int count = 0;
        for (Field field : fieldList) {
            count++;
            str.append(field.getName());
            str.append(extended);
            if (count != lengthArray) str.append(", ");
        }
        return str.toString();
    }

    private String getConditionWhereId() {
        StringBuilder str = new StringBuilder();
        str.append(" where ");
        str.append(entityClassMetaData.getIdField().getName());
        str.append(" = ?");
        return str.toString();
    }

    private String getQuestions(int count) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < count; i++) {
            str.append("?");
            if (i != count - 1) str.append(", ");
        }
        return str.toString();
    }
}
