package com.library.core.mapper;

import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;
import tk.mybatis.mapper.util.StringUtil;

import java.util.Set;

public class UpdateListProvider  extends MapperTemplate {

    public UpdateListProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    /**
     * 根据主键批量更新实体所有属性值，使用case when 方式，支持联合主键
     *
     * @param ms MappedStatement
     * @return sql
     */
//    public String updateListByPrimaryKey(MappedStatement ms) {
//        return this.sqlHelper(ms, false);
//    }

    /**
     *根据主键批量更新实体中不是null的属性值，使用case when方式，支持联合主键
     *
     * @param ms MappedStatement
     * @return sql
     */
    public String updateList(MappedStatement ms) {
        return this.sqlHelper(ms, true);
    }

    private String sqlHelper(MappedStatement ms, boolean notNull) {
        final Class<?> entityClass = getEntityClass(ms);
        // 开始拼sql
        StringBuilder sql = new StringBuilder();
        sql.append(SqlHelper.updateTable(entityClass,tableName(entityClass)));
        sql.append("<trim prefix=\"set\" suffixOverrides= \",\">");
        // 获取全部列
        Set<EntityColumn> allColumns = EntityHelper.getColumns(entityClass);
        // 找到主键列
        Set<EntityColumn> pkColumns = EntityHelper.getPKColumns(entityClass);
        for (EntityColumn column : allColumns) {
            if (!column.isId() && column.isUpdatable()) {
                sql.append("  <trim prefix=\"").append(column.getColumn()).append(" = case\" suffix= \"end,\">");
                sql.append("     <foreach collection= \"list\" item= \"i\" index= \"index\">");
                if (notNull) {
                    sql.append(this.getIfNotNull("i", column, isNotEmpty()));
                }
                sql.append("         when ");
                int count = 0;
                for (EntityColumn pk  : pkColumns) {
                    if (count != 0) {
                        sql.append("and ");
                    }
                    sql.append(pk.getColumn()).append("=#{i.").append(pk.getProperty()).append("} ");
                    count++;
                }
                sql.append("then ").append(column.getColumnHolder("i"));
                if (notNull) {
                    sql.append("        </if>");
                }
                sql.append("        </foreach>");
                sql.append("    </trim>");
            }
        }
        sql.append("</trim>");
        sql.append("WHERE (");
        int count = 0;
        for (EntityColumn pk : pkColumns) {
            sql.append(pk.getColumn());
            if (count < pkColumns.size() - 1) {
                sql.append(", ");
            }
            count++;
        }
        sql.append(") IN");
        sql.append("<trim prefix= \"(\" suffix= \")\">");
        sql.append("<foreach collection=\"list\" separator=\"), (\" item=\"i\" index=\"index\" open=\"(\" close=\")\" >");
        count = 0;
        for (EntityColumn pk : pkColumns) {
            sql.append("#{i.").append(pk.getProperty()).append("}");
            if (count < pkColumns.size() - 1) {
                sql.append(", ");
            }
            count++;
        }
        sql.append("</foreach>");
        sql.append("</trim>");
        return sql.toString();
    }

    private String getIfNotNull(String entityName,EntityColumn column, boolean empty) {
        StringBuilder sql = new StringBuilder();
        sql.append("   <if test=\"");
        if (StringUtil.isNotEmpty(entityName)) {
            sql.append(entityName).append(".");
        }
        sql.append(column.getProperty()).append(" != null");
        if (empty && column.getJavaType().equals(String.class)) {
            sql.append(" and ");
            if (StringUtil.isNotEmpty(entityName)) {
                sql.append(entityName).append(".");
            }
            sql.append(column.getProperty()).append(" != '' ");
        }
        sql.append("\">");
        return sql.toString();
    }
}