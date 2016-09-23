package com.fun.yzss.db.engine.impl;

import com.fun.yzss.db.engine.QueryExecutor;
import com.fun.yzss.db.engine.TransactionManager;
import com.fun.yzss.db.engine.model.QueryContext;
import com.fun.yzss.db.engine.model.QueryType;
import com.fun.yzss.db.entity.DataObject;
import com.fun.yzss.exception.DalException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by fanqq on 2016/9/23.
 */
@Service("queryExecutor")
public class DefaultQueryExecutor implements QueryExecutor {

    @Resource
    TransactionManager transactionManager;

    @Override
    public <T extends DataObject> List<T> executeQuery(QueryContext ctx, Class<T> resultClass) throws Exception {
        resolveSqlStatement(ctx);
        PreparedStatement ps = createPreparedStatement(ctx, transactionManager.getConnection());
        if (ctx.getFetchSize() > 0) {
            ps.setFetchSize(ctx.getFetchSize());
        }
        setInParameters(ps, ctx);
        ResultSet rs = ps.executeQuery();
        List<T> result = assembleResultSet(rs, ctx, resultClass);
        return result;
    }


    @Override
    public int executeUpdate(QueryContext ctx) throws Exception {
        resolveSqlStatement(ctx);
        PreparedStatement ps = createPreparedStatement(ctx, transactionManager.getConnection());
        if (ctx.getFetchSize() > 0) {
            ps.setFetchSize(ctx.getFetchSize());
        }
        setInParameters(ps, ctx);
        int rowCount = ps.executeUpdate();
        if (ctx.getType() == QueryType.INSERT) {
            ctx.getDataObject().setId(ps.getGeneratedKeys().getLong(1));
        }
        return rowCount;
    }

    @Override
    public <T extends DataObject> int[] executeUpdateBatch(QueryContext ctx, T[] protos) throws Exception {
        return new int[0];
    }


    protected PreparedStatement createPreparedStatement(QueryContext ctx, Connection conn) throws SQLException {
        QueryType type = ctx.getType();
        PreparedStatement ps;

        if (type == QueryType.SELECT) {
            ps = conn.prepareStatement(ctx.getSqlStatement(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

        } else {
            ps = conn.prepareStatement(ctx.getSqlStatement(), PreparedStatement.RETURN_GENERATED_KEYS);
        }
        return ps;
    }

    protected void resolveSqlStatement(QueryContext ctx) throws DalException {
        Pattern pattern = Pattern.compile("\\{(.*)\\}");
        Matcher matcher = pattern.matcher(ctx.getQuery());
        String sql = ctx.getQuery();
        try {
            while (matcher.find()) {
                String name = matcher.group(1);
                Object obj = ctx.getDataObject().getByName(name);
                int size = 1;
                if (obj instanceof List) {
                    size = ((List) obj).size();
                }
                String to = "";
                for (int i = 0; i < size; i++) {
                    if (i == 0) {
                        to += "?";
                    } else {
                        to += ",?";
                    }
                }
                sql = sql.replaceFirst("\\{" + name + "\\}", to);
            }
            ctx.setSqlStatement(sql);
        } catch (Exception e) {
            throw new DalException("Parser Query failed.", e);
        }
    }

    private void setInParameters(PreparedStatement ps, QueryContext ctx) throws DalException {
        Pattern pattern = Pattern.compile("\\{(.*)\\}");
        Matcher matcher = pattern.matcher(ctx.getQuery());
        int index = 1;
        try {
            while (matcher.find()) {
                String name = matcher.group(1);
                Object obj = ctx.getDataObject().getByName(name);
                if (obj instanceof List) {
                    for (Object o : (List) obj) {
                        ps.setObject(index, o);
                        index++;
                    }
                } else {
                    ps.setObject(index, obj);
                    index++;
                }
            }
        } catch (Exception e) {
            throw new DalException("Add Parameters failed.", e);
        }
    }

    private <T extends DataObject> List<T> assembleResultSet(ResultSet rs, QueryContext ctx, Class<T> resultClass) throws Exception {
        List<T> res = new ArrayList<>();
        int i;
        T row;
        while (rs.next()) {
            row = resultClass.newInstance();
            i = 0;
            for (String name : ctx.getObjNames()) {
                row.setByName(name, rs.getObject(i++));
            }
            res.add(row);
        }

        return null;
    }
}
