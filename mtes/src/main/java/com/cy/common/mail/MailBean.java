package com.cy.common.mail;


import java.util.Date;
import java.util.List;

/**
 * 发送邮件信息
 *
 * @author Cy
 * @date 2019/03/06
 */
public class MailBean {

	private String id; // 32位UUID
	private String user;//发件人名字
	private String receiveName; // 收件人邮箱

	private String ccReceiver; // 抄送人
	private String bccReceiver; // 密送人
	private String subject; // 邮件主题
	private String content; // 邮件正文
	private Date timingTime;//定时时间
	private Date time;//操作时间
	private String picture; //正文图片
	public String getPicture(){
		return picture;
	}
	public void setPicture(String picture){
		this.picture = picture;
	}
	public Date getTime(){
		return time;
	}
	public void setTime(Date time){
		this.time = time;
	}
	public Date getTimingTime() {
		return timingTime;
	}

	public void setTimingTime(Date timingTime) {
		this.timingTime = timingTime;
	}

	private String filename; // 附件的文件名
	private boolean isOne = true; // 单发还是群发

	private List<String> receiverEmailList; // 收件人邮箱列表
	private String[] contentId; // 插入正文图片的contend-id
	private String[] imageCatalog; // 插入正文图片的路径

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	public String getReceiveName() {
		return receiveName;
	}

	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}

	public String[] getContentId() {
		return contentId;
	}

	public void setContentId(String[] contentId) {
		this.contentId = contentId;
	}

	public String[] getImageCatalog() {
		return imageCatalog;
	}

	public void setImageCatalog(String[] imageCatalog) {
		this.imageCatalog = imageCatalog;
	}

	public String getCcReceiver() {
		return ccReceiver;
	}

	public void setCcReceiver(String ccReceiver) {
		this.ccReceiver = ccReceiver;
	}

	public String getBccReceiver() {
		return bccReceiver;
	}

	public void setBccReceiver(String bccReceiver) {
		this.bccReceiver = bccReceiver;
	}

	public List<String> getReceiverEmailList() {
		return receiverEmailList;
	}

	public void setReceiverEmailList(List<String> receiverEmailList) {
		this.receiverEmailList = receiverEmailList;
	}

	public boolean getIsOne() {
		return isOne;
	}

	public void setIsOne(boolean isOne) {
		this.isOne = isOne;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	@Override
	public String toString() {
		return "MailBean [id=" + id + ", receiveName=" + receiveName + ", ccReceiver=" + ccReceiver + ", bccReceiver="
				+ bccReceiver + ", subject=" + subject + ", content=" + content + ", timingTime=" + timingTime
				+ ", time=" + time + ", filename=" + filename + "]";
	}

}
