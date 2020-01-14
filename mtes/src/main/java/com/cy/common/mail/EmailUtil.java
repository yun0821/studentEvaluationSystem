package com.cy.common.mail;

import com.sun.mail.util.MailSSLSocketFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 邮件发送工具类
 *
 * @author Cy
 * @date 2019/03/06
 */
public class EmailUtil {

    private static final Logger logger = LogManager.getLogger(EmailUtil.class);

    /**
     * 将中文转为由ASCII字符(对于中文乱码，是因为邮件要求所有的字符必须是ascII字符，
     * 中文字符当然不可以。因此我们只需要把我们的中文全部转码就可以了)
     *
     * @param text 中文字符
     * @return ASCII字符
     * @author Cy
     * @date 2019/03/06
     */
    public String toChinese(String text) {
        try {
            text = MimeUtility.encodeText(new String(text.getBytes(), "UTF-8"), "UTF-8", "B");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("中文转码失败！", e);
        }
        return text;
    }

    /**
     * 发送邮件
     *
     * @param mailConfig 邮箱配置
     * @param mailBean   邮件内容
     * @param operate    0为普通发送  1为抄送 2为密送
     * @return 发送是否成功
     * @author Cy
     * @date 2019/03/06
     */
    public boolean sendEmail(MailProps mailConfig, MailBean mailBean, int operate) {
        String host = mailConfig.getSendHost();
        final String username = mailConfig.getUser();
        final String password = mailConfig.getPassword();
        String file = mailBean.getFilename();
        String to = mailBean.getReceiveName();
        List<String> listMassTo = mailBean.getReceiverEmailList();
        String subject = mailBean.getSubject();
        String content = mailBean.getContent();
        int port = mailConfig.getSendPort();
        String[] imageCatalog = mailBean.getImageCatalog();
        String[] contentId = mailBean.getContentId();
        Properties props = System.getProperties();
        // 设置SMTP的主机
        props.put("mail.smtp.host", host);
        // 需要经过验证
        props.put("mail.smtp.auth", "true");
        // 端口号
        props.put("mail.smtp.port", port);
        //读取超时
        props.put("mail.smtp.timeout", 4000);
        //连接超时
        props.put("mail.smtp.connection.timeout", 3000);
        String liveStr = "smtp.live.com";
        try {
            // 使用SSL连接
            if (mailConfig.getIsSsl()) {
                // javax.mail 1.4.2之后才出现 使用ssl连接
                MailSSLSocketFactory sf = new MailSSLSocketFactory();
                sf.setTrustAllHosts(true);
                props.put("mail.smtp.ssl.enable", "true");
                props.put("mail.smtp.ssl.socketFactory", sf);
            } else if (liveStr.equals(host)) {
//				MailSSLSocketFactory sf = new MailSSLSocketFactory();
//				sf.setTrustAllHosts(true);
//				props.put("mail.smtp.ssl.socketFactory", sf);
                props.put("mail.smtp.starttls.enable", "true");
            } else {
                // javax.mail 1.4.2之后才出现 使用ssl连接
                MailSSLSocketFactory sf = new MailSSLSocketFactory();
                sf.setTrustAllHosts(true);
                props.put("mail.smtp.ssl.enable", "false");
                props.put("mail.smtp.ssl.socketFactory", sf);
            }
            // props.put("mail.smtp.localhost", "PC1001");
            // 设置协议
            props.setProperty("mail.transport.protocol", "smtp");
            // 开启调解模式（启用调试模式可打印客户端与服务器交互过程时一问一答的响应消息）
            props.put("mail.debug", true);

            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
            // 单步调试模式
            session.setDebug(true);
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(username));
            if (operate == 0) {
                // 单发还是群发
                if (mailBean.getIsOne()) {
                    InternetAddress[] address = {new InternetAddress(to)};
                    msg.setRecipients(Message.RecipientType.TO, address);
                } else {
                    int num = listMassTo.size();
                    InternetAddress[] address = new InternetAddress[num];
                    for (int i = 0; i < num; i++) {
                        address[i] = new InternetAddress(listMassTo.get(i));
                    }
                    msg.setRecipients(Message.RecipientType.TO, address);
                }

                //抄送
                if (mailBean.getCcReceiver() != null && !"".equals(mailBean.getCcReceiver())) {
                    String[] ccTo = objectToString(mailBean.getCcReceiver());
                    int num = ccTo.length;
                    InternetAddress[] address = new InternetAddress[num];
                    for (int i = 0; i < num; i++) {
                        address[i] = new InternetAddress(ccTo[i]);
                    }
                    msg.setRecipients(Message.RecipientType.CC, address);
                }

                //密送
                if (mailBean.getBccReceiver() != null && !"".equals(mailBean.getBccReceiver())) {
                    String[] bccTo = objectToString(mailBean.getBccReceiver());
                    int numBcc = bccTo.length;
                    InternetAddress[] addressBcc = new InternetAddress[numBcc];
                    for (int i = 0; i < numBcc; i++) {
                        addressBcc[i] = new InternetAddress(bccTo[i]);
                    }
                    msg.setRecipients(Message.RecipientType.BCC, addressBcc);
                }
            }
//			else if(operate==1){
//				//抄送
//				String[] ccTo = objectToString(mailBean.getCcReceiver());
//				int num = ccTo.length;
//				InternetAddress[] address = new InternetAddress[num];
//				for (int i = 0; i < num; i++) {
//					address[i] = new InternetAddress(ccTo[i]);
//				}
//				msg.setRecipients(Message.RecipientType.CC, address);
//			}else{
//				//密送
//				String[] bccTo = objectToString(mailBean.getBccReceiver());
//				int num = bccTo.length;
//				InternetAddress[] address = new InternetAddress[num];
//				for (int i = 0; i < num; i++) {
//					address[i] = new InternetAddress(bccTo[i]);
//				}
//				msg.setRecipients(Message.RecipientType.BCC, address);
//			}
            msg.setSubject(toChinese(subject));

            //正文是文字和图片混合(不包含图片的可以不添加related参数)
            Multipart mp = new MimeMultipart("related");
            MimeBodyPart mbpContent = new MimeBodyPart();
            mbpContent.setContent(content, "text/html;charset=UTF-8");
            mp.addBodyPart(mbpContent);

            //正文插入图片
            if (imageCatalog != null && imageCatalog.length != 0) {
                //创建图片
                int size = imageCatalog.length;
                for (int i = 0; i < size; i++) {
                    MimeBodyPart img = new MimeBodyPart();
                    FileDataSource fds = new FileDataSource(imageCatalog[i]);
                    DataHandler dh = new DataHandler(fds);
                    img.setDataHandler(dh);
                    //解决出现未知格式bin bug或者文件名中文乱码
                    img.setFileName(toChinese(fds.getName()));
                    //创建图片的一个表示用于显示在邮件中显示
                    img.setContentID(contentId[i]);
                    mp.addBodyPart(img);
                }
            }

            /* 往邮件中添加附件 */
            if (file != null && !"".equals(file)) {
                List<String> files = objectToList(file);
                if (files != null && files.size() > 0) {
                    for (String f : files) {
                        MimeBodyPart mbpFile = new MimeBodyPart();
                        FileDataSource fds = new FileDataSource(f);
                        mbpFile.setDataHandler(new DataHandler(fds));
                        mbpFile.setFileName(toChinese(fds.getName()));
                        mp.addBodyPart(mbpFile);
                    }
                }
            }

            msg.setContent(mp);
            msg.setSentDate(new Date());
            Transport.send(msg);
            // 捕获发送邮件失败的异常类
        } catch (SendFailedException se) {
            // 获取邮箱地址不正确的列表
            Address[] addresses = se.getInvalidAddresses();
            if (addresses != null) {
                for (Address address : addresses) {
                    System.out.println("未发送成功的邮箱：" + address);
                    logger.error("未发送成功的邮箱：" + address);
                }
            }
            return false;
        } catch (Exception me) {
            logger.error("邮件发送失败！", me);
            return false;
        }
        return true;
    }

    /**
     * 格式转换——把["aaa;bbbbbbbb;cccccccc"]格式的Object转成需要的String[] ，元素分别是aaa,bbb,ccc
     *
     * @param object 对象数组
     * @return 字符串数组
     * @author Cy
     * @date 2019/03/06
     */
    public static String[] objectToString(Object object) {
        if (object == null) {
            return null;
        } else {
            String data = String.valueOf(object).replace("；", ";").trim();
            return data.split(";");
        }
    }

    /**
     * 格式转换——把["aaa;bbbbbbbb;cccccccc"]格式的Object转成需要的String[] ，元素分别是aaa,bbb,ccc
     *
     * @param object 对象数组
     * @return 字符串集合
     * @author Cy
     * @date 2019/03/06
     */
    public static List<String> objectToList(Object object) {
        if (object == null) {
            return null;
        }
        String data = String.valueOf(object).replace("；", ";").trim();
        String[] s = data.split(";");
        if (s.length != 0) {
            return Arrays.asList(s);
        }
        return null;
    }

    /**
     * 验证邮箱的有效性
     *
     * @param mailStr 邮箱地址
     * @return true为有效，否则无效
     * @author Cy
     * @date 2019/03/06
     */
    public static Boolean isEmail(String mailStr) {
        //验证是否是有效的邮箱
        String regex = "[a-zA-Z0-9_\\-\\\\.]+@[a-zA-Z0-9]+(\\.(com|cn|org|edu|hk))";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(mailStr);
        return matcher.matches();
    }

    /**
     * 将邮件内容中的<img src = 'cid:1'>提取出来，找出所有的contendId,即为cid后面跟着的id
     *
     * @param mailContent 邮箱内容
     * @return contendId字符串数组
     * @author Cy
     * @date 2019/03/06
     */
    public static String[] stringToContentId(String mailContent) {
        if (mailContent == null) {
            return null;
        }
        String data = mailContent.replace("\"", "'").trim();
        String[] s = data.split("<img");
        if (s.length != 0) {
            int size = s.length;
            String[] contentIds = new String[size - 1];
            // 第一个值是无用数据
            for (int i = 1; i < size; i++) {
                // 获得cid: 后面的值 包括'
                String str = (s[i].split(">")[0]).split(":")[1];
                String contentId = str.substring(0, str.length() - 1);
                contentIds[i - 1] = contentId.trim();
            }
            return contentIds;
        }
        return null;
    }

    /**
     * 将"E_e_ad_jpg"的contendId,转换成对应的图片目录E:e/ad.jpg
     *
     * @param object 需要替换的字符串数组
     * @return 替换后的字符串数组
     * @author Cy
     * @date 2019/03/06
     */
    public static String[] contentIdToCatalog(String[] object) {
        if (object != null && object.length != 0) {
            String[] data = new String[object.length];
            int size = object.length;
            for (int i = 0; i < size; i++) {
                String catalog = object[i].replaceFirst("_", ":/").replace("_", "/");
                String str = catalog.substring(0, catalog.lastIndexOf("/")) + "."
                        + catalog.substring(catalog.lastIndexOf("/") + 1);
                data[i] = str.trim();
            }
            return data;
        } else {
            return null;
        }

    }

    /**
     * 将contentId中的cid全部使用qpEncode编码
     *
     * @param contentId 需要加密的字符串数组
     * @author Cy
     * @date 2019/03/06
     */
    public static void contendIdToQpEncode(String[] contentId) {
        if (contentId != null && contentId.length != 0) {
            int size = contentId.length;
            for (int i = 0; i < size; i++) {
                contentId[i] = qpEncode(contentId[i]);
            }
        }
    }

    /**
     * 将object中的包含的cid全部使用qpEncode编码替换
     *
     * @param object    字符串对象
     * @param contentId 用来替换的字符串数组
     * @return 替换后的字符串
     * @author Cy
     * @date 2019/03/06
     */
    public static String contentToCidQpEncode(String object, String[] contentId) {
        if (object != null && !"".equals(object)) {
            if (contentId != null && contentId.length != 0) {
                for (String string : contentId) {
                    object = object.replace(string, qpEncode(string));
                }
            }
        }
        return object;
    }

    /**
     * 将格式在html/text内容中的“"”，替换成“\"”格式
     *
     * @param object 对象
     * @return 替换后的字符串
     * @author Cy
     * @date 2019/03/06
     */
    public static String objectToStringHtml(Object object) {
        return String.valueOf(object).replace("\"", "\\\\\"").trim();
    }

    /**
     * quoted-printable编码
     *
     * @param content 明文
     * @return 密文
     * @author Cy
     * @date 2019/03/06
     */
    public static String qpEncode(String content) {
        char[] encode = content.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char ch : encode) {
            if (ch == '\n') {
                sb.append("\n");
            } else if ((ch >= '!') && (ch <= '~') && (ch != '=')) {
                sb.append(ch);
            } else if (ch == '=') {
                sb.append("=3D");
            } else {
                String ss = String.valueOf(ch);
                byte[] buf;
                try {
                    buf = ss.getBytes("utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    logger.error("字符串转字节失败，字符串：" + ss, e);
                    continue;
                }
                int len = buf.length;
                if (len == 3) {
                    for (byte c : buf) {
                        String s16 = String.valueOf(Integer.toHexString(c));
                        // 抽取中文字符16进制字节的后两位,也就是=E8等号后面的两位,
                        // 三个代表一个中文字符
                        char c0x6;
                        char c0x7;
                        if (s16.charAt(6) >= 97 && s16.charAt(6) <= 122) {
                            c0x6 = (char) (s16.charAt(6) - 32);
                        } else {
                            c0x6 = s16.charAt(6);
                        }
                        if (s16.charAt(7) >= 97 && s16.charAt(7) <= 122) {
                            c0x7 = (char) (s16.charAt(7) - 32);
                        } else {
                            c0x7 = s16.charAt(7);
                        }
                        sb.append("=").append(c0x6).append(c0x7);
                    }
                }
            }
        }
        return sb.toString();
    }

    /**
     * quoted-printable解码
     *
     * @param encodeStr 密文
     * @return 明文
     * @author Cy
     * @date 2019/03/06
     */
    public static String qpDecode(String encodeStr) {
        if (encodeStr == null) {
            return "";
        }
        try {
            StringBuilder sb = new StringBuilder(encodeStr);
            for (int i = 0; i < sb.length(); i++) {
                if (sb.charAt(i) == '\n' && sb.charAt(i - 1) == '=') {
                    // 解码这个地方也要修改一下
                    // sb.deleteCharAt(i);
                    sb.deleteCharAt(i - 1);
                }
            }
            encodeStr = sb.toString();
            byte[] bytes = encodeStr.getBytes("US-ASCII");
            for (int i = 0; i < bytes.length; i++) {
                byte b = bytes[i];
                if (b != 95) {
                    bytes[i] = b;
                } else {
                    bytes[i] = 32;
                }
            }
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            for (int i = 0; i < bytes.length; i++) {
                int b = bytes[i];
                if (b == '=') {
                    try {
                        int u = Character.digit((char) bytes[++i], 16);
                        int l = Character.digit((char) bytes[++i], 16);
                        if (u == -1 || l == -1) {
                            continue;
                        }
                        buffer.write((char) ((u << 4) + l));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        e.printStackTrace();
                        logger.error("解密失败，密文：" + encodeStr, e);
                    }
                } else {
                    buffer.write(b);
                }
            }
            return new String(buffer.toByteArray(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("解密失败，密文：" + encodeStr, e);
            return "";
        }
    }

    public static void main(String[] args) {
        // Object object =("111@qq.com;22222@qq.com；cccc@qq.com;dd@sina.cn;；");
        // List<String> list = ObjectUtil.objectToList(object);
        // System.out.println(list.size());
        // for (String string : list) {
        // System.out.println(string);
        // }
        // Object object = "<b><font face=\"幼圆\">其<font
        // color=\"#33cccc\">实我也不大清楚问题出在哪里</font></font></b>";
        // System.out.println(objectToStringHtml(object));

        String object = "<div> 我我文档是的撒多爱我<img src='cid:E_三只猫_jpg'></div>";
        String[] str = stringToContentId(object);
        String[] catalog = contentIdToCatalog(str);
        object = contentToCidQpEncode(object, str);
        contendIdToQpEncode(str);
        System.out.println(object);
        for (String string : catalog) {
            System.out.println(string);
        }
        for (String string : str) {
            System.out.println(string);
        }
    }
}
