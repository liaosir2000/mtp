package com.coal.mtp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(name = "warn")
public class Warn extends EntityId {

	@Column(name = "form_id")
	private Long formId;
	@Column(name = "person_id")
	private String personId;
	@Column(name = "person_name")
	private String personName;
	@Column(name = "person_email")
	private String personEmail;
	@Column(name = "content")
	private String content;
	@Column(name = "info_id")
	private Long infoId;
	@Column(name = "info_name")
	private String infoName;
	@Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
	private DateTime createTime;
	private boolean solved;
	private String result;
	@Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
	private DateTime resultTime;
	public Long getFormId() {
		return formId;
	}
	public void setFormId(Long formId) {
		this.formId = formId;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String persionName) {
		this.personName = persionName;
	}
	public String getPersonEmail() {
		return personEmail;
	}
	public void setPersonEmail(String persionEmail) {
		this.personEmail = persionEmail;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getInfoId() {
		return infoId;
	}
	public void setInfoId(Long infoId) {
		this.infoId = infoId;
	}
	public String getInfoName() {
		return infoName;
	}
	public void setInfoName(String infoName) {
		this.infoName = infoName;
	}
	public DateTime getCreateTime() {
		return createTime;
	}
	public void setCreateTime(DateTime createTime) {
		this.createTime = createTime;
	}
	public boolean isSolved() {
		return solved;
	}
	public void setSolved(boolean solved) {
		this.solved = solved;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public DateTime getResultTime() {
		return resultTime;
	}
	public void setResultTime(DateTime resultTime) {
		this.resultTime = resultTime;
	}
}
