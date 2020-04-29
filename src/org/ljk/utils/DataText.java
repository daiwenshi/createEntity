package org.ljk.utils;

/**
 * @author linjiekai
 * DataText.java
 * 2013-10-31 下午02:20:16 
 */
public class DataText {
	private String colName;// 列名
	private String colType;// 列名类型
	private String comment;// 表字段的备注注释
	private Integer intLength;// 数据的存放长度
	//将字段生成属性首字母小写(列名为实体的属性名)
	private String propColName;
	
	private String initialsLowercase;
	private String initialsUpperCase;

	
	
	public String getInitialsLowercase() {
		return initialsLowercase;
	}

	public void setInitialsLowercase(String initialsLowercase) {
		this.initialsLowercase = initialsLowercase;
	}

	public String getInitialsUpperCase() {
		return initialsUpperCase;
	}

	public void setInitialsUpperCase(String initialsUpperCase) {
		this.initialsUpperCase = initialsUpperCase;
	}

	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

	public String getColType() {
		return colType;
	}

	public void setColType(String colType) {
		this.colType = colType;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getIntLength() {
		return intLength;
	}

	public void setIntLength(Integer intLength) {
		this.intLength = intLength;
	}

	public String getPropColName() {
		return propColName;
	}

	public void setPropColName(String propColName) {
		this.propColName = propColName;
	}

	@Override
	public String toString() {
		return "DataText [colName=" + colName + ", colType=" + colType
				+ ", comment=" + comment + ", initialsLowercase="
				+ initialsLowercase + ", initialsUpperCase="
				+ initialsUpperCase + ", intLength=" + intLength
				+ ", propColName=" + propColName + "]";
	}

}
