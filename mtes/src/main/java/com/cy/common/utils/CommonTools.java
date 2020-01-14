package com.cy.common.utils;

import com.cy.common.basics.MATCH;
import com.cy.common.basics.SearchCondition;
import com.cy.common.Constant;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLDecoder;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * 公用通用的简单函数工具
 *
 * @author Cy
 * @date 2019/03/06
 * https://blog.csdn.net/wu1226419614/article/details/72673686
 */
public final class CommonTools {

    private static final Logger logger = LogManager.getLogger(CommonTools.class);

    private static SecureRandom random = new SecureRandom();

    public static final Pattern MAIL_PATTERN = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");

    public static final Pattern MOBILE_PATTERN = Pattern.compile("^1[3|4|5|7|8][0-9]\\d{8}$");

    public static final Pattern NAME_PATTERN = Pattern.compile("^[\\u4E00-\\u9FBF][\\u4E00-\\u9FBF(.|·)]{0,13}[\\u4E00-\\u9FBF]$");

    public static final Pattern NICKNAME_PATTERN = Pattern.compile("^((?!\\d{5})[\\u4E00-\\u9FBF(.|·)|0-9A-Za-z_]){2,11}$");

    public static final Pattern PASSWORD_PATTERN = Pattern.compile("^[\\s\\S]{6,30}$");

    public static final Pattern CODE_PATTERN = Pattern.compile("^0\\d{2,4}$");

    public static final Pattern POSTCODE_PATTERN = Pattern.compile("^\\d{6}$");

    public static final Pattern ID_PATTERN = Pattern.compile("^\\d{6}(\\d{8}|\\d{11})[0-9a-zA-Z]$");

    public static final Pattern BANK_CARD_PATTERN = Pattern.compile("^\\d{16,30}$");

    public enum SortType {
        ASC, /* 升序 */
        DESC/* 降序 */
    }

    /**
     * 生成6位随机数字, 用于手机短信验证码.
     *
     * @return 6位随机数字
     */
    public static int random() throws Exception {
        int x = Math.abs(random.nextInt(899999));

        return x + 100000;
    }

    /**
     * 对url字符串进行编码.
     *
     * @param url 要编码的url字符串
     * @return 编码后的字符串
     */
    public static String urlEncoder(String url) throws UnsupportedEncodingException {
        if (StringUtils.isEmpty(url)) {
            return null;
        }
        return java.net.URLEncoder.encode(url, "utf-8");
    }

    /**
     * 对url字符串进行解码.
     *
     * @param url 要解码的url字符串
     * @return 解码后的字符串
     */
    public static String urlDecoder(String url) throws UnsupportedEncodingException {
        if (StringUtils.isEmpty(url)) {
            return null;
        }
        return URLDecoder.decode(url, "UTF-8");
    }

    /**
     * 验证字符串是不是邮箱.
     *
     * @param email 要验证的邮箱
     * @return 是否正确邮箱
     */
    public static boolean validateEmail(String email) throws Exception {
        if (StringUtils.isEmpty(email)) {
            return false;
        }
        Matcher m = MAIL_PATTERN.matcher(email);
        return m.matches();
    }

    /**
     * 验证字符串是不是手机号.
     *
     * @param mobile 要验证的手机号
     * @return 是否正确手机号
     */
    public static boolean validateMobile(String mobile) throws Exception {
        if (StringUtils.isEmpty(mobile)) {
            return false;
        }
        Matcher m = MOBILE_PATTERN.matcher(mobile);
        return m.matches();
    }
    /**
     * 验证姓名是否有效.
     *
     * @param name 要验证的姓名
     * @return 是否正确姓名
     */
    public static boolean validateName(String name) throws Exception {
        if (StringUtils.isEmpty(name) || name.replaceAll("[^.·]", "").length() > 1) {
            return false;
        }
        Matcher m = NAME_PATTERN.matcher(name);
        return m.matches();
    }

    /**
     * 验证昵称是否有效.
     *
     * @param nickname 要验证的昵称
     * @return 是否正确昵称
     */
    public static boolean validateNickname(String nickname) throws Exception {

        //规则 不能包含5个数字 允许中英文和数字 2-11位
        if (StringUtils.isEmpty(nickname) || nickname.replaceAll("[^0-9]", "").length() > 4) {
            return false;
        }
        Matcher m = NICKNAME_PATTERN.matcher(nickname);
        return m.matches();
    }

    /**
     * 验证密码格式是否有效.
     *
     * @param password 要验证的密码
     * @return 是否正确密码格式
     */
    public static boolean validatePassword(String password) throws Exception {
        if (StringUtils.isEmpty(password)) {
            return false;
        }
        Matcher m = PASSWORD_PATTERN.matcher(password);
        return m.matches();
    }

    /**
     * 验证区号是否有效.
     *
     * @param code 要验证的区号
     * @return 是否正确身份证
     */
    public static boolean validateCode(String code) throws Exception {
        if (StringUtils.isEmpty(code)) {
            return false;
        }
        Matcher m = CODE_PATTERN.matcher(code);
        return m.matches();
    }

    /**
     * 验证邮政编码是否有效.
     *
     * @param postcode 要验证的邮政编码
     * @return 是否正确邮政编码
     */
    public static boolean validatePostcode(String postcode) throws Exception {
        if (StringUtils.isEmpty(postcode)) {
            return false;
        }
        Matcher m = POSTCODE_PATTERN.matcher(postcode);
        return m.matches();
    }

    /**
     * 验证银行卡是否有效.
     *
     * @param bankCardNumber 要验证的银行卡号
     * @return 是否正确银行卡号
     */
    public static boolean validateBankCardNumber(String bankCardNumber) throws Exception {
        if (StringUtils.isEmpty(bankCardNumber)) {
            return false;
        }
        Matcher m = BANK_CARD_PATTERN.matcher(bankCardNumber);
        return m.matches();
    }

    /**
     * 获取客户端IP地址.
     *
     * @param request request请求
     * @return ip地址
     */
    public static String getIpAddr(HttpServletRequest request) throws Exception {
        String ip = request.getHeader("Cdn-Src-Ip");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (StringUtils.hasText(ip)) {
            return StringUtils.tokenizeToStringArray(ip, ",")[0];
        }
        return "";
    }

    /**
     * 获取当前系统时间,以java.sql.Timestamp类型返回.
     *
     * @return 当前时间
     */
    public static Timestamp getTimestamp() throws Exception {
        Timestamp d = new Timestamp(System.currentTimeMillis());
        return d;
    }

    /**
     * 生成32位编码,不含横线
     *
     * @return uuid串
     */
    public static String getUUID() throws Exception {
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
        return uuid.toUpperCase();
    }

    /**
     * 生成MD5编码
     *
     * @param data 要编码的字符串
     * @return 加密后的字符串
     */
    public static String md5(String data) throws Exception {
        return md5(data, 1);
    }


    /**
     * 生成MD5编码
     *
     * @param data 要编码的字符串
     * @param time 加密次数
     * @return 加密后的字符串
     */
    public static String md5(String data, int time) throws Exception {
        byte[] bytes = data == null ? new byte[0] : data.getBytes("UTF-8");

        while (time-- > 1) {
            bytes = DigestUtils.md5Digest(bytes);
        }

        return DigestUtils.md5DigestAsHex(bytes).toUpperCase();
    }

    /**
     * 空字符串转为null
     *
     * @param object 要规则化的对象
     * @param <T>    对象类型
     * @return 规则化后的对象
     */
    public static <T> T normalizeBlankStringFields(T object) throws Exception {
        if (object == null) {
            return null;
        }

        PropertyUtilsBean utils = BeanUtilsBean.getInstance().getPropertyUtils();
        Stream.of(utils.getPropertyDescriptors(object)).forEach(pd -> {
            try {
                Object value = utils.getNestedProperty(object, pd.getName());
                if (value == null) {
                    return;
                }
                if (!StringUtils.hasText(value.toString())) {
                    utils.setNestedProperty(object, pd.getName(), null);
                }
            } catch (Throwable e) {
            }
        });

        return object;
    }

    /**
     * 通过身份证获取性别
     *
     * @param idNumber 身份证号
     * @return 返回性别, 0 保密 , 1 男 2 女
     */
    public static Integer getGenderByIdNumber(String idNumber) throws Exception {

        int gender = 0;

        if (idNumber.length() == 15) {
            gender = Integer.parseInt(String.valueOf(idNumber.charAt(14))) % 2 == 0 ? 2 : 1;
        } else if (idNumber.length() == 18) {
            gender = Integer.parseInt(String.valueOf(idNumber.charAt(16))) % 2 == 0 ? 2 : 1;
        }

        return gender;

    }

    /**
     * 通过身份证获取生日
     *
     * @param idNumber 身份证号
     * @return 返回生日, 格式为 yyyy-MM-dd 的字符串
     */
    public static String getBirthdayByIdNumber(String idNumber) throws Exception {

        String birthday = "";

        if (idNumber.length() == 15) {
            birthday = "19" + idNumber.substring(6, 8) + "-" + idNumber.substring(8, 10) + "-" + idNumber.substring(10, 12);
        } else if (idNumber.length() == 18) {
            birthday = idNumber.substring(6, 10) + "-" + idNumber.substring(10, 12) + "-" + idNumber.substring(12, 14);
        }

        return birthday;

    }


    /**
     * 通过身份证获取年龄
     *
     * @param idNumber 身份证号
     * @return 返回年龄
     */
    public static Integer getAgeByIdNumber(String idNumber) throws Exception {

        String birthString = getBirthdayByIdNumber(idNumber);
        if (StringUtils.isEmpty(birthString)) {
            return 0;
        }

        return getAgeByBirthString(birthString);

    }

    /**
     * 通过身份证获取年龄
     *
     * @param idNumber     身份证号
     * @param isNominalAge 是否按元旦算年龄，过了1月1日加一岁 true : 是 false : 否
     * @return 返回年龄
     */
    public static Integer getAgeByIdNumber(String idNumber, boolean isNominalAge) throws Exception {

        String birthString = getBirthdayByIdNumber(idNumber);
        if (StringUtils.isEmpty(birthString)) {
            return 0;
        }

        return getAgeByBirthString(birthString, isNominalAge);

    }

    /**
     * 通过生日日期获取年龄
     *
     * @param birthDate 生日日期
     * @return 返回年龄
     */
    public static Integer getAgeByBirthDate(Date birthDate) throws Exception {

        return getAgeByBirthString(new SimpleDateFormat("yyyy-MM-dd").format(birthDate));

    }


    /**
     * 通过生日字符串获取年龄
     *
     * @param birthString 生日字符串
     * @return 返回年龄
     */
    public static Integer getAgeByBirthString(String birthString) throws Exception {

        return getAgeByBirthString(birthString, "yyyy-MM-dd");

    }

    /**
     * 通过生日字符串获取年龄
     *
     * @param birthString  生日字符串
     * @param isNominalAge 是否按元旦算年龄，过了1月1日加一岁 true : 是 false : 否
     * @return 返回年龄
     */
    public static Integer getAgeByBirthString(String birthString, boolean isNominalAge) throws Exception {

        return getAgeByBirthString(birthString, "yyyy-MM-dd", isNominalAge);

    }

    /**
     * 通过生日字符串获取年龄
     *
     * @param birthString 生日字符串
     * @param format      日期字符串格式,为空则默认"yyyy-MM-dd"
     * @return 返回年龄
     */
    public static Integer getAgeByBirthString(String birthString, String format) throws Exception {
        return getAgeByBirthString(birthString, "yyyy-MM-dd", false);
    }


    /**
     * 通过生日字符串获取年龄
     *
     * @param birthString  生日字符串
     * @param format       日期字符串格式,为空则默认"yyyy-MM-dd"
     * @param isNominalAge 是否按元旦算年龄，过了1月1日加一岁 true : 是 false : 否
     * @return 返回年龄
     */
    public static Integer getAgeByBirthString(String birthString, String format, boolean isNominalAge) throws Exception {

        int age = 0;

        if (StringUtils.isEmpty(birthString)) {
            return age;
        }

        if (StringUtils.isEmpty(format)) {
            format = "yyyy-MM-dd";
        }
        Calendar birthday = Calendar.getInstance();
        Calendar today = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        birthday.setTime(sdf.parse(birthString));
        age = today.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);
        if (!isNominalAge) {
            if (today.get(Calendar.MONTH) < birthday.get(Calendar.MONTH) ||
                    (today.get(Calendar.MONTH) == birthday.get(Calendar.MONTH) &&
                            today.get(Calendar.DAY_OF_MONTH) < birthday.get(Calendar.DAY_OF_MONTH))) {
                age = age - 1;
            }
        }

        return age;

    }

    /**
     * 手机号中间四位替换成星号
     *
     * @param mobile 手机号
     * @return mobile 替换后的手机号
     */
    public static String maskMobile(String mobile) throws Exception {
        if (validateMobile(mobile)) {
            return mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        }
        return mobile;
    }

    /**
     * 手机号中间四位自定义替换
     *
     * @param mobile 手机号
     * @param transCode 中间四位目标值 如GXJF 将136GXJF1111
     * @return 替换的手机号
     */
    public static String maskMobile(String mobile, String transCode) throws Exception {
        if (validateMobile(mobile)) {
            transCode = StringUtils.isEmpty(transCode) ? "****" : transCode;
            return mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", String.format("$1%s$2", transCode));
        }
        return mobile;
    }

    /**
     * 邮箱地址加星号
     *
     * @param email 邮箱
     * @return 处理后的邮箱
     */
    public static String maskEmail(String email) throws Exception {
        if (validateEmail(email)) {
            String userName = email.substring(0, email.indexOf("@"));
            int len = userName.length();
            if (len >= 5) {
                int total = len - 3;
                int half = total / 2;
                int start = half;
                int end = len - half;
                if (total % 2 != 0) {
                    end = end - 1;
                }
                StringBuilder sb = new StringBuilder(email);
                for (int i = start; i < end; i++) {
                    sb.setCharAt(i, '*');
                }
                return sb.toString();
            }
        }
        return email;
    }

    /**
     * 账号中间四位自定义替换
     *
     * @param account 账号
     * @return 替换过的账号
     */
    public static String maskTradeAccount(String account) throws Exception {
        return account.replaceAll("(\\d{7})\\d*(\\d{4})", "$1****$2");
    }

    /**
     * 验证是否为日期
     *
     * @param date 日期
     * @return 返回验证的结果
     */
    public static boolean validateDate(String date) throws Exception {
        boolean convertSuccess = true;
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        try {
            format.setLenient(false);
            format.parse(date);
        } catch (ParseException e) {
            convertSuccess = false;
        }
        return convertSuccess;
    }

    /**
     * 获取时间戳,作为递增的ID
     */
    private static final Lock lock = new ReentrantLock();   //锁对象

    public static long getUniqueLong() {
        long l;
        lock.lock();
        try {
            l = System.currentTimeMillis();
        } finally {
            lock.unlock();
        }
        return l;
    }

    /**
     * 解析出url参数中的键值对
     * 如 "index.jsp?Action=del&id=123"，解析出Action:del,id:123存入map中
     *
     * @param URL url地址
     * @return url请求参数部分
     */
    public static String getUrlParams(String URL, String key) throws Exception {
        Map<String, String> mapRequest = new HashMap<String, String>();
        String[] arrSplit;
        String strUrlParam;
        java.net.URL aURL = new URL(URL);
        strUrlParam = aURL.getQuery();
        if (strUrlParam == null) {
            return "";
        }
        arrSplit = strUrlParam.split("[&]");
        for (String strSplit : arrSplit) {
            String[] arrSplitEqual = null;
            arrSplitEqual = strSplit.split("[=]");
            if (arrSplitEqual.length > 1) {
                mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);
            } else {
                if (!StringUtils.isEmpty(arrSplitEqual[0])) {
                    mapRequest.put(arrSplitEqual[0], "");
                }
            }
        }
        if (mapRequest.containsKey(key)) {
            return URLDecoder.decode(mapRequest.get(key), "UTF-8");
        }
        return "";
    }


    /**
     * 生成随机密码
     *
     * @param pwd_len 生成的密码的总长度
     * @return 密码的字符串
     */
    public static String genRandomNum(int pwd_len) throws Exception {
        // 35是因为数组是从0开始的，26个字母+10个数字
        final int maxNum = 36;
        int i; // 生成的随机数
        int count = 0; // 生成的密码的长度
        char[] str = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
                'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        StringBuilder pwd = new StringBuilder();
        Random r = new Random();
        while (count < pwd_len) {
            // 生成随机数，取绝对值，防止生成负数，
            i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1
            if (i >= 0 && i < str.length) {
                pwd.append(str[i]);
                count++;
            }
        }
        return pwd.toString();
    }

    /**
     * 将ISO-8859-1编码的字符串转为UTF-8标准格式
     *
     * @param isoStr 传入ISO-8859-1编码的字符串
     * @return 若isoStr为null则返回null
     * @throws Exception 异常
     */
    public static String Chinese2UTF8InURL(String isoStr) throws Exception {
        return isoStr == null ? null : new String(isoStr.getBytes("ISO-8859-1"), "UTF-8");
    }

    /**
     * 用字符串替换目标字符串中首次出现的指定字符串
     *
     * @param str   目标字符串
     * @param regex 正则表达式
     * @param word  用于替换的字符串
     * @return String
     * @author Cy
     * @date 2019/03/06
     */
    public static String replaceString(String str, String regex, String word) {
        return replaceString(str, regex, word, 0, 0);
    }

    /**
     * 用字符串替换目标字符串中首次出现的指定字符串
     *
     * @param str   目标字符串
     * @param regex 正则表达式
     * @param word  用于替换的字符串
     * @param start 替换时保留不变的前置字符数
     * @param end   替换时保持不变的后置字符数
     * @return String
     * @author Cy
     * @date 2019/03/06
     */
    public static String replaceString(String str, String regex, String word, int start, int end) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            String startChars = str.substring(matcher.start(), matcher.start() + start);
            String endChars = str.substring(matcher.end() - end, matcher.end());
            String key = str.substring(matcher.start(), matcher.end());
            key = changeKeyToNormalKey(key);
            str = str.replaceFirst(key, startChars + word + endChars);
            break;
        }
        return str;
    }

    /**
     * 将包含正则式关键字的字符转义
     *
     * @param str 目标字符串
     * @return String 转义后的字符串
     * @author Cy
     * @date 2019/03/06
     */
    private static String changeKeyToNormalKey(String str) {
        String[] rowArr = {"\\", "$", "(", ")", "*", "+", ".", "[", "]", "?", "^", "{", "}", "|"};
        for (String row : rowArr) {
            if (str.contains(row)) {
                str = str.replace(row, "\\" + row);
            }
        }
        return str;
    }

    /**
     * 判断字符串是否是数字，是返回true,否返回false
     *
     * @param numStr 带数字的字符串
     * @return boolean 是否为数字
     * @author Cy
     * @date 2019/03/16
     */
    public static boolean isNumber(String numStr) throws Exception {
        String regex = "^(-?\\d+)(\\.\\d+)?$";
        return Pattern.compile(regex).matcher(numStr).find();
    }

    /**
     * 判断字符串是否是数字，是返回true,否返回false
     *
     * @param numStr 带数字的字符串
     * @return boolean 是否为数字
     * @author Cy
     * @date 2019/03/16
     */
    public static boolean isInteger(String numStr) throws Exception {
        String regex = "^-?\\d+$";
        return Pattern.compile(regex).matcher(numStr).find();
    }


    /**
     * 判断输入的字符串是否符合Email样式.
     *
     * @param emailStr 传入的字符串
     * @return 是Email样式返回true, 否则返回false
     */
    public static boolean isEmailString(String emailStr) throws Exception {
        Pattern pattern = Pattern.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
        return pattern.matcher(emailStr).matches();
    }

    /**
     * 根据文件名查询文件路径下的所有同名文件
     *
     * @param strPath        文件夹
     * @param searchFileName 文件名
     * @return java.util.List<java.io.File>
     * @author Cy
     * @date 2019/03/16
     */
    public static List<File> searchFileList(String strPath, String searchFileName) {
        List<File> fileList = new ArrayList<>();
        File dir = new File(strPath);
        File[] files = dir.listFiles(); // 该文件目录下文件全部放入数组
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName();
                if (files[i].isDirectory()) { // 判断是文件还是文件夹
                    fileList.addAll(searchFileList(files[i].getAbsolutePath(), searchFileName)); // 获取文件绝对路径
                } else if (fileName.startsWith(searchFileName.trim())) { // 判断文件名是否以.avi结尾
                    fileList.add(files[i]);
                } else {
                    continue;
                }
            }

        }
        return fileList;
    }

    /**
     * 在字符串中查找指定的字符
     *
     * @param str   目标字符串
     * @param regex 正则式
     * @param start 匹配结果中前置需要过滤的字符数
     * @param end   匹配结果中后置需要过滤的字符数
     */
    public static List<String> getStringFromString(String str, String regex, int start, int end) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        List<String> list = new ArrayList<>();
        while (matcher.find()) {
            String key = str.substring(matcher.start() + start, matcher.end() - end);
            list.add(key);
        }
        return list;
    }

    /**
     * 根据类名查找包路径
     *
     * @param strPath   文件夹
     * @param className 类名
     * @return java.lang.String
     * @author Cy
     * @date 2019/03/16
     */
    public static String getPackagePath(String strPath, String className) {
        if (strPath == null || strPath.isEmpty()) {
            File f2 = new File(CommonTools.class.getResource("").getPath());
            String comPath = f2.getAbsolutePath();
            int index = comPath.contains("/com/") ? comPath.indexOf("/com/") : comPath.indexOf("\\com\\");
            comPath = comPath.substring(0, index + "/com/".length() - 1);
            strPath = comPath;
        }
        List<File> fileList = searchFileList(strPath, className);
        if (fileList != null && fileList.size() == 1) {
            String allPath = fileList.get(0).getParent();
            int index = allPath.contains("/com/") ? allPath.indexOf("/com/") : allPath.indexOf("\\com\\");
            String packageStr = allPath.substring(index + 1);
            return packageStr.replaceAll("\\\\", ".").replaceAll("/", ".");
        }
        return null;
    }

    /**
     * 删除文件路径下的所有文件 包括子文件夹
     *
     * @param filepath 绝对路径
     * @return 返回是否成功
     */
    public static boolean deleteAllFile(String filepath) throws Exception {
        boolean rst = false;
        File file = new File(filepath);
        if (!file.isDirectory() || !file.exists()) {
            return rst;
        }
        String[] fileLst = file.list();
        File temp = null;
        for (int i = 0; i < fileLst.length; i++) {
            if (temp.isDirectory()) {
                deleteAllFile(filepath + "/" + fileLst[i]);// 先删除文件夹里面的文件
                deleteFolder(filepath + "/" + fileLst[i]);// 再删除空文件夹
                rst = true;
            }
            if (filepath.endsWith(File.separator)) {
                temp = new File(filepath + fileLst[i]);
            } else {
                temp = new File(filepath + File.separator + fileLst[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
        }
        return rst;
    }

    /**
     * 删除文件夹
     *
     * @param folderPath 绝对路径
     */
    public static void deleteFolder(String folderPath) throws Exception {
        try {
            deleteAllFile(folderPath); // 删除完里面所有内容
            String filePath = folderPath;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            myFilePath.delete(); // 删除空文件夹
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 生成系统唯一识别码的字符串
     */
    public static String getUUIDString() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 自定义信息到INFO日志文件中
     *
     * @param infoStr 自定义信息
     * @author Cy
     * @date 2019/03/16
     */
    public static void logInfo(String infoStr) {
        logger.info(infoStr);
    }

    /**
     * 自定义调试信息到DEBUG日志文件中，包括Java的错误信息Exception
     *
     * @param debugStr 自定义错误信息
     * @author Cy
     * @date 2019/03/16
     */
    public static void logDebug(String debugStr) {
        logger.debug(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss").format(new Date()) + ":" + debugStr);
    }

    /**
     * 自定义debug信息到DEBUG(调试)日志文件中，包括Java的错误信息Exception
     *
     * @param debugStr 自定义错误信息
     * @param e        Java错误对象
     * @author Cy
     * @date 2019/03/16
     */
    public static void logDebug(String debugStr, Exception e) {
        logger.debug(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss").format(new Date()) + ":" + debugStr, e);
    }

    /**
     * 自定义错误信息到ERROR(错误)日志文件中
     *
     * @param errorStr 自定义错误信息
     * @author Cy
     * @date 2019/03/16
     */
    public static void logError(String errorStr) {
        logger.error(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()) + ":" + errorStr);
    }

    /**
     * 自定义错误信息到ERROR(错误)日志文件中，包括Java的错误信息Exception
     *
     * @param errorStr 自定义错误信息
     * @param e        Java错误对象
     * @author Cy
     * @date 2019/03/16
     */
    public static void logError(String errorStr, Exception e) {
        logger.error(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()) + ":" + errorStr, e);
    }

    /**
     * 将JSON字符串转成参数Map（建议最多2级，多级则容易出错）
     *
     * @param json            json对象
     * @param conditionPrefix 查询条件key,默认为fields
     * @param valuePrefix     查询调教值的key，默认为values
     * @return java.util.Map<java.lang.String                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               ,                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               java.lang.Object                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               [                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               ]> Map对象
     * @throws Exception 异常
     * @author Cy
     * @date 2019/03/16
     */
    @SuppressWarnings("unchecked")
    private static Map<String, Object[]> jsonToParamMap(JSONObject json, String conditionPrefix, String valuePrefix) throws Exception {
        if (json == null) {
            return null;
        }
        if (conditionPrefix == null || conditionPrefix.isEmpty()) {
            conditionPrefix = Constant.QUERY_PARAM_MAP_FIELDS_KEY;
        }
        if (valuePrefix == null || valuePrefix.isEmpty()) {
            valuePrefix = Constant.QUERY_PARAM_MAP_VALUES_KEY;
        }
        Map<String, Object[]> map = new HashMap<String, Object[]>();
        Iterator<String> keys = json.keys();
        List<String> keyLst = new ArrayList<>();
        List<Object> valueLst = new ArrayList<>();
        while (keys.hasNext()) {
            String key = keys.next();
            if (key == null || key.isEmpty() || json.get(key) == null || StringUtils.isEmpty(json.get(key))) {
                continue;
            }
            if (json.get(key) instanceof JSONObject) {
                JSONObject json1 = (JSONObject) json.get(key);
                Map<String, Object[]> map1 = jsonToParamMap(json1, conditionPrefix + "_" + key, valuePrefix + "_" + key);
                map.putAll(map1);
            } else {
                //valueLst += "," + String.valueOf(json.get(key)).trim();keyLst += "," + key;
                keyLst.add(key);
                valueLst.add(json.get(key));
            }
        }
        if ( keyLst.size() > 0 && valueLst.size() > 0) {
            String[] params = new String[keyLst.size()];
            keyLst.toArray(params);
            Object[] values = new Object[valueLst.size()];
            valueLst.toArray(values);
            map.put(conditionPrefix, params);
            map.put(valuePrefix, values);
        }
        return map;
    }

    /**
     * 效率优先的JSON字符串（建议使用两级）转为参数数组和参数值数组
     * 参数数组Key以fields_头，值数组Key以values_开头，其中，一级参数数组Key就是fields，值数组Key为values
     * 第二级以其key值作为键的后缀
     *
     * @param jsonString 参数json字符串
     * @return java.util.Map<java.lang.String                                                                                                                               ,                                                                                                                               java.lang.Object                                                                                                                               [                                                                                                                               ]> 参数Map对象
     * @throws Exception 异常
     * @author Cy
     * @date 2019/03/16
     */
    public static Map<String, Object[]> getParamsMap(String jsonString) throws Exception {
        return jsonToParamMap(new JSONObject(jsonString), Constant.QUERY_PARAM_MAP_FIELDS_KEY,
                Constant.QUERY_PARAM_MAP_VALUES_KEY);
    }

    /**
     * Json字符串转Map，通过递归调用方式，可实现多级转化
     *
     * @param jsonString 需转化的json字符串
     * @return Map<String                                                                                                                                                                                                                                                               ,                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               Object>
     * @throws Exception 异常
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> jsonStringToMap(String jsonString) throws Exception {
        if (jsonString == null || jsonString.isEmpty()) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        JSONObject json = new JSONObject(jsonString);
        if (json.length() < 1) {
            return null;
        }
        Iterator<String> it = json.keys();

        while (it.hasNext()) {
            String key = (String) it.next();
            if (json.get(key) != null && !StringUtils.isEmpty(json.get(key))) {
                if (json.get(key) instanceof JSONObject) {
                    Map<String, Object> subMap = jsonStringToMap(String.valueOf(json.get(key)).trim());
                    map.put(key, subMap);
                } else {
                    map.put(key, String.valueOf(json.get(key)).trim());
                }
            }
        }
        return map;
    }

    /**
     * 将Java实体对象转为Map类型
     *
     * @param objLst 需转化的对象
     * @return Map
     * @throws Exception 异常
     * @author Cy
     * @date 2019/03/16
     */
    @SuppressWarnings("unchecked")
    public static List<Map<String, Object>> customerObject2Map(List<?> objLst) throws Exception {
        if (objLst == null || objLst.size() < 1) {
            return null;
        }
        List<Map<String, Object>> rstLst = new ArrayList<Map<String, Object>>();
        if (objLst.get(0) instanceof Map) {
            return (List<Map<String, Object>>) objLst;
        }
        for (Object obj : objLst) {
            Class type = obj.getClass();
            Map<String, Object> objMap = new HashMap<String, Object>();
            BeanInfo beanInfo = Introspector.getBeanInfo(type);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (int i = 0; i < propertyDescriptors.length; i++) {
                PropertyDescriptor descriptor = propertyDescriptors[i];
                String propertyName = descriptor.getName();
                if (!propertyName.equals("class")) {
                    Method readMethod = descriptor.getReadMethod();
                    Object result = readMethod.invoke(obj, new Object[0]);
                    if (result != null) {
                        objMap.put(propertyName, result);
                    } else {
                        objMap.put(propertyName, "");
                    }
                }
            }
            rstLst.add(objMap);
        }
        return rstLst;
    }

    /**
     * 字符串排序
     *
     * @param str       需要排序的字符串
     * @param orderType 排序规则，升序或降序
     * @return java.lang.String 排序后的字符串
     * @author Cy
     * @date 2019/03/16
     */
    public static String sortString(String str, SortType orderType) throws Exception {
        char[] ch = str.toCharArray();
        Boolean isEnd = false;
        char temp;
        do {
            isEnd = false;
            for (int i = 0; i < ch.length - 1; i++) {
                switch (orderType) {
                    case ASC: {
                        if (ch[i] > ch[i + 1]) {
                            temp = ch[i];
                            ch[i] = ch[i + 1];
                            ch[i + 1] = temp;
                            isEnd = true;
                        }
                        break;
                    }
                    case DESC: {
                        if (ch[i] < ch[i + 1]) {
                            temp = ch[i];
                            ch[i] = ch[i + 1];
                            ch[i + 1] = temp;
                            isEnd = true;
                        }
                        break;
                    }
                    default:
                        break;
                }
            }
        } while (isEnd);
        return new String(ch, 0, ch.length);
    }

    /**
     * 字符串排序（默认升序）
     */
    public static String sortString(String str) throws Exception {
        return sortString(str, SortType.ASC);
    }

    /**
     * 获取对象某属性的值
     *
     * @param obj       对象
     * @param fieldName 对象属性名称
     * @return java.lang.Object 属性值
     * @throws Exception 异常
     * @author Cy
     * @date 2019/03/16
     */
    public static Object getObjectPropertyValue(Object obj, String fieldName) throws Exception {
        Class<?> clazz = Class.forName(obj.getClass().getName());
        PropertyDescriptor fieldPd = new PropertyDescriptor(fieldName, clazz);
        Method fieldGetMethod = fieldPd.getReadMethod(); //获取属性的读取方法
        return fieldGetMethod.invoke(obj);
    }

    /**
     * 判断对象是否有某属性
     *
     * @param obj       对象
     * @param fieldName 对象属性名称
     * @return true表示是该对象的属性，false表示不是
     * @throws Exception 异常
     * @author Cy
     * @date 2019/03/16
     */
    public static boolean getFieldIsObjectProperty(Object obj, String fieldName) throws Exception {
        Class<?> clazz = Class.forName(obj.getClass().getName());
        Field[] fields = clazz.getDeclaredFields();
        boolean b = false;
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getName().equals(fieldName)) {
                b = true;
                break;
            }
        }
        return b;
    }

    /**
     * 将formData字符串转为SearchCondition列表
     *
     * @param formData formData参数值
     * @return List<SearchCondition>
     * @throws Exception 异常
     * @author Cy
     * @date 2019/03/06
     */
    public static List<SearchCondition> formDataToSearchCondition(String formData) throws Exception {
        Map<String, Object[]> map = CommonTools.getParamsMap(formData);
        List<SearchCondition> scLst = new ArrayList<>();
        if (StringUtils.isEmpty(map.get(Constant.QUERY_PARAM_MAP_FIELDS_KEY)) ||
                StringUtils.isEmpty(map.get(Constant.QUERY_PARAM_MAP_VALUES_KEY))) {
            scLst = null;
        } else {
            Object[] fields = map.get(Constant.QUERY_PARAM_MAP_FIELDS_KEY);
            Object[] values = map.get(Constant.QUERY_PARAM_MAP_VALUES_KEY);
            int i = 0;
            for (Object fo : fields) {
                String foStr = fo.toString();
                if (!StringUtils.isEmpty(values[i])) {
                    if (foStr.contains("Id")) {
                        SearchCondition sc = new SearchCondition(foStr, MATCH.NULL, values[i]);
                        scLst.add(sc);
                    } else {
                        SearchCondition sc = new SearchCondition(foStr, MATCH.LIKE, values[i]);
                        scLst.add(sc);
                    }
                    i++;
                }
            }
        }
        return scLst;
    }


    /**
     * 根据字符串返回对应日期
     * @param dateStr 传入字符串
     * @return 返回日期
     */
    public static Date getDateFromString(String dateStr) {
        return getDateFromString("yyyy-MM-dd", dateStr);
    }

    /**
     * 根据字符串返回对应日期
     *
     * @param pattern pattern
     * @param dateStr 传入字符串
     * @return 饭后日期
     */
    public static Date getDateFromString(String pattern, String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            if (dateStr != null) {
                return sdf.parse(dateStr);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据日期生成对应的字符串
     * @param date 传入日期
     * @return 返回字符串
     */
    public static String getStringFromDate(Date date) {

        return getStringFromDate("yyyy-MM-dd", date);
    }

    /**
     * 根据日期生成对应的字符串
     * @param pattern pattern
     * @param date 传入日期
     * @return 返回字符串
     */
    public static String getStringFromDate(String pattern, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 获取多少分钟前的时间
     * @param mm 传入分钟
     * @return 获取时间
     */
    public static Date getDateFromHis(int mm) {
        Date date = new Date();
        long time = date.getTime() - mm * 60 * 1000;
        return new Date(time);
    }

    /**
     * 获取两个时间的小时差
     * @param startDate 传入开始时间
     * @param endDate 传入结束时间
     * @return 获取时差
     */
    public static long getHourNum(Date startDate, Date endDate) {
        return (endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60);
    }
}
