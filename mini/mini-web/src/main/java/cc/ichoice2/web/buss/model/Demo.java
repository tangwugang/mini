package cc.ichoice2.web.buss.model;

import cc.ichoice.minidao.db.annotation.Entity;
import cc.ichoice.minidao.db.annotation.Table;

import java.util.Date;

@Entity
@Table(value="sys_user")
public class Demo {
	private String id;//主键
	private String createBy;//创建者
	private String createName;//创建者名
	private Date createDate;//创建时间
	private Date updateDate;//更新时间
	private String updateBy;//更新者
	private String updateName;//更新者名
	
	
	private String userName;//   用户名
	private String realName;//   真实姓名
	private String userKey;//   用户验证唯一标示
	private String password;//   用户密码
	private Short activitiSync;//   是否同步工作流引擎
	private Short status = Short.valueOf("1");//   状态3：在线,2：离线,0：禁用,1:可用
	private byte[] signature;//   签名文件
	private String signatureFile;//   签名文件名
	private String mobilePhone;//   手机
	private String officePhone;//   办公电话
	private String email;//   邮箱
	private String idCard;//   身份证号
	private Short sex;//   性别
	
	private String barnNo;// 分配的仓房编号
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public String getUpdateName() {
		return updateName;
	}
	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}
	public String getUserName() {
	    return this.userName;
	}
	public void setUserName(String userName) {
	    this.userName=userName;
	}
	public String getRealName() {
	    return this.realName;
	}
	public void setRealName(String realName) {
	    this.realName=realName;
	}
	public String getUserKey() {
	    return this.userKey;
	}
	public void setUserKey(String userKey) {
	    this.userKey=userKey;
	}
	public String getPassword() {
	    return this.password;
	}
	public void setPassword(String password) {
	    this.password=password;
	}
	public Short getActivitiSync() {
	    return this.activitiSync;
	}
	public void setActivitiSync(Short activitiSync) {
	    this.activitiSync=activitiSync;
	}
	public Short getStatus() {
	    return this.status;
	}
	public void setStatus(Short status) {
	    this.status=status;
	}
	public byte[] getSignature() {
		return signature;
	}
	public void setSignature(byte[] signature) {
		this.signature = signature;
	}
	public String getSignatureFile() {
	    return this.signatureFile;
	}
	public void setSignatureFile(String signatureFile) {
	    this.signatureFile=signatureFile;
	}
	public String getMobilePhone() {
	    return this.mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
	    this.mobilePhone=mobilePhone;
	}
	public String getOfficePhone() {
	    return this.officePhone;
	}
	public void setOfficePhone(String officePhone) {
	    this.officePhone=officePhone;
	}
	public String getEmail() {
	    return this.email;
	}
	public void setEmail(String email) {
	    this.email=email;
	}
	public String getIdCard() {
	    return this.idCard;
	}
	public void setIdCard(String idCard) {
	    this.idCard=idCard;
	}
	public Short getSex() {
	    return this.sex;
	}
	public void setSex(Short sex) {
	    this.sex=sex;
	}
	public String getBarnNo() {
		return barnNo;
	}
	public void setBarnNo(String barnNo) {
		this.barnNo = barnNo;
	}
	
	
	

}
