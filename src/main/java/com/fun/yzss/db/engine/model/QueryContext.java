package com.fun.yzss.db.engine.model;

import com.fun.yzss.db.entity.DataObject;

import java.util.Arrays;
import java.util.List;

/**
 * Created by fanqq on 2016/9/23.
 */
public class QueryContext {

    private String query;
    private String sqlStatement;
    private DataObject dataObject;
    private int fetchSize;

    public String getSqlStatement() {
        return sqlStatement;
    }

    public void setSqlStatement(String sqlStatement) {
        this.sqlStatement = sqlStatement;
    }


    public QueryType getType() {
        return type;
    }

    public void setType(QueryType type) {
        this.type = type;
    }

    public List<String> getObjNames() {
        return objNames;
    }

    public void setObjNames(List<String> objNames) {
        this.objNames = objNames;
    }

    private QueryType type;
    private List<String> objNames;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public DataObject getDataObject() {
        return dataObject;
    }

    public void setDataObject(DataObject dataObject) {
        this.dataObject = dataObject;
    }

    public int getFetchSize() {
        return fetchSize;
    }

    public void setFetchSize(int fetchSize) {
        this.fetchSize = fetchSize;
    }

    public void setResultSetColumnName(String[] names) {
        objNames = Arrays.asList(names);
    }

    public List<String> getResultSetColumnName() {
        return objNames;
    }


    class IndexNameClass {
        int index;
        String name;
        Class classObj;

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Class getClassObj() {
            return classObj;
        }

        public void setClassObj(Class classObj) {
            this.classObj = classObj;
        }

        IndexNameClass(int i, String n, Class obj) {
            index = i;
            name = n;
            classObj = obj;
        }
    }

}
