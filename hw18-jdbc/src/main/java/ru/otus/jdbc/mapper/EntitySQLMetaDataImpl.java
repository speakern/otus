package ru.otus.jdbc.mapper;

import java.lang.reflect.Field;
import java.util.List;

public class EntitySQLMetaDataImpl<T> implements EntitySQLMetaData {

//    private final static String SELECT_BY_ID = "Select";

    private final EntityClassMetaData<T> entityClassMetaData;

    public EntitySQLMetaDataImpl(EntityClassMetaData<T> entityClassMetaData) {
        this.entityClassMetaData = entityClassMetaData;
    }

    @Override
    public String getSelectAllSql() {
        StringBuilder sqlAll = new StringBuilder();
        sqlAll.append("select ");
        sqlAll.append(getFields(entityClassMetaData.getAllFields()));
        sqlAll.append(" from ");
        sqlAll.append(entityClassMetaData.getName());
        //"select id, name from user where id  = ?"
        return sqlAll.toString();
    }

    @Override
    public String getSelectByIdSql() {
        return getSelectAllSql() + " where id = ?";
    }

    @Override
    public String getInsertSql() {
        //"insert into user(name) values (?)"
        StringBuilder sqlInsert = new StringBuilder();
        sqlInsert.append("insert into ");
        sqlInsert.append(entityClassMetaData.getName());
        sqlInsert.append("(");
        sqlInsert.append(getFields(entityClassMetaData.getFieldsWithoutId()));
        sqlInsert.append(") values (");
        sqlInsert.append(getQuestions(entityClassMetaData.getFieldsWithoutId().size()));
        sqlInsert.append(")");
        return sqlInsert.toString();
    }

    @Override
    public String getUpdateSql() {
        return null;
    }

    private String getFields(List<Field> fieldList) {
        StringBuilder str = new StringBuilder();
        int lengthArray = fieldList.size();
        int count = 0;
        for (Field field: fieldList) {
            count++;
            str.append(field.getName());
            if (count != lengthArray) str.append(", ");
            //str.append(" ");
        }
        return str.toString();
    }

    private String getQuestions(int count) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < count; i++) {
            str.append("?");
            if (i != count - 1) str.append(",");
        }
        return str.toString();
    }
}
