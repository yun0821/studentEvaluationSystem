package com.cy.common.mail;

import java.io.Serializable;

/**
 * 邮箱配置
 *
 * @author Cy
 * @date 2019/03/06
 */

public class MailProps implements Serializable {

	private static final long serialVersionUID = 1L;

	private Short id; // id唯一标识
	private String sendHost; // 发送邮件代理服务器地址
	private Integer sendPort; // 发送邮件代理服务器端口
	private Integer recevicePort; // 接收邮件代理服务器端口
	private String receviceHost; // 接收邮件代理服务器地址
	private String user; // 发件人邮箱账号
	private String password; // 发件人邮箱密码
	private Boolean isSsl; // 是否使用SSL加密连接，1为使用，0为不使用

	public MailProps() {

	}

	public MailProps(Short id, String sendHost, Integer sendPort, Integer recevicePort, String receviceHost, String user,
                     String password, Boolean isSsl) {
		this.id = id;
		this.sendHost = sendHost;
		this.sendPort = sendPort;
		this.recevicePort = recevicePort;
		this.receviceHost = receviceHost;
		this.user = user;
		this.password = password;
		this.isSsl = isSsl;
	}


	public Short getId() {
		return id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public String getSendHost() {
		return sendHost;
	}

	public void setSendHost(String sendHost) {
		this.sendHost = sendHost;
	}

	public Integer getSendPort() {
		return sendPort;
	}

	public void setSendPort(Integer sendPort) {
		this.sendPort = sendPort;
	}

	public Integer getRecevicePort() {
		return recevicePort;
	}

	public void setRecevicePort(Integer recevicePort) {
		this.recevicePort = recevicePort;
	}

	public String getReceviceHost() {
		return receviceHost;
	}

	public void setReceviceHost(String receviceHost) {
		this.receviceHost = receviceHost;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public Boolean getIsSsl() {
		return isSsl;
	}

	public void setIsSsl(Boolean isSsl) {
		this.isSsl = isSsl;
	}

	@Override
	public String toString() {
		return "MailProps [id = " + id + ",sendHost = " + sendHost + ",sendPort = " + sendPort + ",recevicePort = "
				+ recevicePort + ",receviceHost = " + receviceHost + ",user = " + user + ",password = " + password
				+ ",isSsl = " + isSsl + "]";
	}
}
