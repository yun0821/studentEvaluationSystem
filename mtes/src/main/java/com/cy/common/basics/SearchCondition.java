package com.cy.common.basics;

import com.cy.common.utils.CommonTools;
import com.cy.common.utils.StringTools;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * SQL/HQL查询条件类
 *
 * @author Cy
 * @date 2019/03/01
 */
public class SearchCondition {
    private static final Logger logger = LogManager.getLogger(SearchCondition.class);

    private String paramName;          //参数名称（字段名称或字段别名）
    private MATCH match;               //匹配方式（如>\>=）
    private ValueDataType valueType;   //字段值的数据类型
    private Object paramValues;        //参数/字段的值


    /**
     * 构造函数，包含所有的支持参数
     *
     * @param paramName   参数名称（字段名称或字段别名）
     * @param match       匹配方式（如>\>=）
     * @param valueType   字段值的数据类型
     * @param paramValues 参数/字段的值
     * @author Cy
     * @date 2019/03/01
     */
    public SearchCondition(String paramName, MATCH match, ValueDataType valueType, Object paramValues) {
        this.paramName = paramName;
        this.match = match;
        this.valueType = valueType;
        this.paramValues = paramValues;
    }

    /**
     * 构造函数
     *
     * @param paramName   参数名称（字段名称或字段别名）
     * @param match       匹配方式（如>\>=）
     * @param paramValues 参数/字段的值
     * @author Cy
     * @date 2019/03/01
     */
    public SearchCondition(String paramName, MATCH match, Object paramValues) {
        this.paramName = paramName;
        this.match = match;
        this.valueType = ValueDataType.Null;
        this.paramValues = paramValues;
    }

    /**
     * 构造函数,所有条件为等值查询（=）
     *
     * @param paramName   匹配方式（如>\>=）
     * @param paramValues 参数/字段的值
     * @author Cy
     * @date 2019/03/01
     */
    public SearchCondition(String paramName, Object paramValues) {
        this.paramName = paramName;
        this.match = MATCH.NULL;
        this.valueType = ValueDataType.Null;
        this.paramValues = paramValues;
    }


    /**
     * 构造函数，包含所有的支持参数
     *
     * @param paramName   参数名称（字段名称或字段别名）
     * @param match       匹配方式（如>\>=）
     * @param valueType   字段值的数据类型
     * @param paramValues 参数/字段的值
     * @author Cy
     * @date 2019/03/01
     */
    public SearchCondition(String paramName, MATCH match, String valueType, Object paramValues) {
        this.paramName = paramName;
        this.match = match;
        String str = StringTools.firstToUpperCase(valueType.toLowerCase());
        this.valueType = ValueDataType.valueOf(str);
        this.paramValues = paramValues;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public MATCH getMatch() {
        return match;
    }

    public void setMatch(MATCH match) {
        this.match = match;
    }

    public ValueDataType getValueType() {
        return valueType;
    }

    public void setValueType(ValueDataType valueType) {
        this.valueType = valueType;
    }

    public Object getParamValues() {
        return paramValues;
    }

    public void setParamValues(Object paramValues) {
        this.paramValues = paramValues;
    }

    @Override
    public String toString() {
        return "SearchCondition{" +
                "paramName='" + paramName + '\'' +
                ", match=" + match +
                ", valueType=" + valueType +
                ", paramValues=" + paramValues +
                '}';
    }

    /**
     * @param formString formString
     * @return java.util.List<SearchCondition>
     * @author Cy
     * @date 2019/03/01
     */
    public static List<SearchCondition> getSearchConditionLst(String formString) {
        return getSearchConditionLst(formString, null);
    }

    /**
     * 根据查询字符串生成查询条件
     *
     * @param paramString 以查询字段作为key，以对应查询值作为value的json字符串
     * @param likeFields  模糊查询字段fie
     * @return java.util.List<SearchCondition>
     * @author Cy
     * @date 2019/03/01
     */
    public static List<SearchCondition> getSearchConditionLst(String paramString, String likeFields) {
        if (paramString == null || paramString.isEmpty()) {
            return null;
        }
        List<SearchCondition> list = new ArrayList<>();
        try {
            Map<String, Object> map = CommonTools.jsonStringToMap(paramString);
            if (map == null) {
                return null;
            }
            if (likeFields == null || StringUtils.isEmpty(likeFields)) {
                for (String key : map.keySet()) {
                    if (!StringUtils.isEmpty(map.get(key))) {
                        list.add(new SearchCondition(key, map.get(key)));
                    }
                }
            } else {
                likeFields = "," + likeFields + ",";
                for (String field : map.keySet()) {
                    if (map.get(field) == null || StringUtils.isEmpty(map.get(field))) {
                        continue;
                    }
                    if (likeFields.contains("," + field + ",")) {
                        list.add(new SearchCondition(field, MATCH.LIKE, map.get(field)));
                    } else {
                        list.add(new SearchCondition(field, map.get(field)));
                    }
                }
            }
        } catch (Exception e) {
            logger.error("SearchCondition类获取查询条件" + paramString + "时发生错误。", e);
            return null;
        }
        return list;
    }

    /**
     * 根据查询字符串生成查询条件
     *
     * @param paramString   以查询字段作为key，以对应查询值作为value的json字符串
     * @param fieldMatchStr 以查询字段作为key，以匹配方式作为value的json字符串
     * @return java.util.List<SearchCondition>
     * @author Cy
     * @date 2019/03/01
     */
    public static List<SearchCondition> getSearchConditionLstByMatch(String paramString, String fieldMatchStr) {
        if (fieldMatchStr == null || fieldMatchStr.isEmpty()) {
            return getSearchConditionLst(paramString);
        }
        List<SearchCondition> list = new ArrayList<>();
        try {
            Map<String, Object> map = CommonTools.jsonStringToMap(paramString);
            if (map == null) {
                return null;
            }
            Map<String, Object> macthMap = CommonTools.jsonStringToMap(fieldMatchStr);
            for (String field : map.keySet()) {
                if (map.get(field) == null || StringUtils.isEmpty(map.get(field))) {
                    continue;
                }
                if (macthMap.get(field) == null || StringUtils.isEmpty(macthMap.get(field))) {
                    list.add(new SearchCondition(field, map.get(field)));
                } else {
                    String matchStr = macthMap.get(field).toString();
                    MATCH mType = MATCH.valueOf(matchStr);
                    list.add(new SearchCondition(field, mType, map.get(field)));
                }
            }
        } catch (Exception e) {
            logger.error("SearchCondition类获取查询条件" + paramString + "时发生错误。", e);
            return null;
        }
        return list;
    }

}
