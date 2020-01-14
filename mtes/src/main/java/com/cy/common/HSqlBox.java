package com.cy.common;

import com.cy.common.utils.CommonTools;

import java.util.List;
import java.util.Objects;

/**
 * SQL/HQL拼接类
 *
 * @author Cy
 * @date 2019/03/16
 */
public class HSqlBox {

    private String select; // SQL语句SELECT部分 如：SELECT A，B，C as CC
    private String from; // SQL语句FROM部分 如： FROM tb_a a,tb_b b
    private String where;// SQL 语句WHERE部分 如：WHERE a.aid=b.bid
    private String bottom;// SQL 语句结束语句：此处特指GROUP BY 、 ORDER BY 和 LIMIT 子句

    public HSqlBox() {
        this.select = "";
        this.from = "";
        this.where = "";
        this.bottom = "";
    }

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public String getBottom() {
        return bottom;
    }

    public void setBottom(String bottom) {
        this.bottom = bottom;
    }

    /**
     * 得到sql子句
     *
     * @param field    字段，如em.id
     * @param idsStr   用逗号隔开的ID字符串
     * @param paramLst 参数
     * @throws Exception 异常
     * @author Cy
     * @date 2019/03/16
     */
    public static String getIntegerEqualOrInSql(String field, String idsStr, List<Object> paramLst) throws Exception {
        String inStr = ",";
        StringBuffer sql = new StringBuffer();
        if (CommonTools.isInteger(idsStr)) {
            sql.append(field).append("=?");
            paramLst.add(Integer.valueOf(idsStr));
        } else {
            String[] orgIdArr = idsStr.split(inStr);
            for (int i = 0; i < orgIdArr.length; i++) {
                sql.append((i == 0 ? field + " IN( " : ",")).append("?");
                paramLst.add(Integer.valueOf(orgIdArr[i]));
            }
            sql.append(") ");
        }
        return new String(sql);
    }

    /**
     * 得到sql子句
     *
     * @param field    字段，如em.id
     * @param idsStr   用逗号隔开的ID字符串
     * @param paramLst 参数
     * @throws Exception 异常
     * @author Cy
     * @date 2019/03/16
     */
    public static String getShortEqualOrInSql(String field, String idsStr, List<Object> paramLst) throws Exception {
        String inStr = ",";
        StringBuffer sql = new StringBuffer();
        if (CommonTools.isInteger(idsStr)) {
            sql.append(field).append("=?");
            paramLst.add(Short.valueOf(idsStr));
        } else {
            String[] orgIdArr = idsStr.split(inStr);
            for (int i = 0; i < orgIdArr.length; i++) {
                sql.append((i == 0 ? field + " IN( " : ",")).append("?");
                paramLst.add(Short.valueOf(orgIdArr[i]));
            }
            sql.append(") ");
        }
        return new String(sql);
    }

    /**
     * 得到sql子句
     *
     * @param field    字段，如em.id
     * @param idsStr   用逗号隔开的ID字符串
     * @param paramLst 参数
     * @throws Exception 异常
     * @author Cy
     * @date 2019/03/16
     */
    public static String getByteEqualOrInSql(String field, String idsStr, List<Object> paramLst) throws Exception {
        String inStr = ",";
        StringBuffer sql = new StringBuffer();
        if (CommonTools.isInteger(idsStr)) {
            sql.append(field).append("=?");
            paramLst.add(Short.valueOf(idsStr));
        } else {
            String[] orgIdArr = idsStr.split(inStr);
            for (int i = 0; i < orgIdArr.length; i++) {
                sql.append((i == 0 ? field + " IN( " : ",")).append("?");
                paramLst.add(Byte.valueOf(orgIdArr[i]));
            }
            sql.append( ") ");
        }
        return new String(sql);
    }

    /**
     * 得到sql子句
     *
     * @param field    字段，如em.id
     * @param idsStr   用逗号隔开的ID字符串
     * @param paramLst 参数
     * @throws Exception 异常
     * @author Cy
     * @date 2019/03/16
     */
    public static String getStringEqualOrInSql(String field, String idsStr, List<Object> paramLst) throws Exception {
        String inStr = ",";
        StringBuilder sql = new StringBuilder();
        if (CommonTools.isInteger(idsStr)) {
            sql.append(field).append("=?");
            paramLst.add(Short.valueOf(idsStr));
        } else {
            String[] orgIdArr = idsStr.split(inStr);
            for (int i = 0; i < orgIdArr.length; i++) {
                sql.append((i == 0 ? field + " IN( " : ",")).append("?");
                paramLst.add(orgIdArr[i]);
            }
            sql.append(") ");

        }
        return new String(sql);
    }

    public String getHSQLString() {
        return select + " " + from + " " + where + " " + bottom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HSqlBox)) {
            return false;
        }
        HSqlBox hSqlBox = (HSqlBox) o;
        return Objects.equals(getSelect(), hSqlBox.getSelect()) &&
                Objects.equals(getFrom(), hSqlBox.getFrom()) &&
                Objects.equals(getWhere(), hSqlBox.getWhere()) &&
                Objects.equals(getBottom(), hSqlBox.getBottom());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getSelect(), getFrom(), getWhere(), getBottom());
    }
}
