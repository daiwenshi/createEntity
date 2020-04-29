package org.ljk.oracle;

/**
 * @author linjiekai
 * 
 * 2012-7-31 下午04:11:33
 */
public class BeanProperties {
	private static String _package="org.general";
	public  static String sys_package=_package+".sys";//如moder 包名
	public  static String web_package=_package+".web";//如web action上的包名
	
	public  static String _tableName="BUSI_COMMON_IMPROT_FILE_MGR";//大小写都可以，表名

	public  static String path="c:/java_auto_class";//可设
	//web访问时的根目录后的模块级别目录
	public static String webPath="project";
	
	//由其它地方自动设置
	public static String tableName_comments;
	
	public static String get_package() {
		return _package;
	}

	public static void set_package(String package1) {
		_package = package1;
		sys_package=_package+".sys";
		web_package=_package+".web";
	}

	public static String get_tableName() {
		return _tableName;
	}

	public static void set_tableName(String tableName) {
		_tableName = tableName;
	}

	
	public static String getTableName_comments() {
		return tableName_comments;
	}

	public static void setTableName_comments(String tableNameComments) {
		tableName_comments = tableNameComments;
	}

	public static String getPath() {
		return path;
	}

	public static void setPath(String path) {
		BeanProperties.path = path;
	}

	public static String getWebPath() {
		return webPath;
	}

	public static void setWebPath(String webPath) {
		BeanProperties.webPath = webPath;
	}

	/**
	 * @return 通过表名字符串的首字母改成大写
	 */
	public static String getEntityName() {
		return initcapTableName(_tableName);
	}

	/**
	 * @return 通过表名字符串首字母小写 把列名输入字符串的第二"_"后的首字母改成大写
	 */
	public static String getInitialsLowercase(){
		return initcapColName(_tableName);
	}
	
	/**
	 * 通过字符串的首字母改成小写 
	 * @param colName  NOK_PIN
	 * @return nokPin
	 */
	public static String getInitialsLowercase(String colName){
		return initcapColName(colName);
	}
	/**
	 * 通过字符串的首字母改成大写 
	 * @param colName NOK_PIN
	 * @return NokPin
	 */
	public static String getInitialsUpperCase(String colName){
		return initcapTableName(colName);
	}
	/**
	 * 将字段生成属性首字母小写 把列名输入字符串的第二"_"后的首字母改成大写
	 * 
	 * @param str
	 *            "KAI_name"
	 * @return kaiName
	 */
	private static String initcapColName(String ColName) {
		String word[] = ColName.trim().toLowerCase().split("_");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < word.length; i++) {
			char[] ch = word[i].toCharArray();
			if (i > 0)
				if (ch[0] >= 'a' && ch[0] <= 'z') {
					ch[0] = (char) (ch[0] - 32);
				}
			sb.append(new String(ch));

		}

		return sb.toString();
	}
	
	/**
	 * 把表名/get/set后半部的属性等字符串的首字母改成大写
	 * 
	 * @param str
	 * @return
	 */
	private static String initcapTableName(String str) {
		String word[]=str.trim().toLowerCase().split("_");
		StringBuffer sb=new StringBuffer();
		for (int i = 0; i < word.length; i++) {
	        char[] ch = word[i].toCharArray();
	        if (ch[0] >= 'a' && ch[0] <= 'z') {
	            ch[0] = (char) (ch[0] - 32);
	        }
	        sb.append(new String(ch));
		}

        return sb.toString();
    }
	
	public static String oracleType2JavaType(String sqlType) {
		if (sqlType.equalsIgnoreCase("INTEGER")
				|| sqlType.equalsIgnoreCase("NUMBER")) {
			return "Long";
		}else if (sqlType.equalsIgnoreCase("NVARCHAR2")
				|| sqlType.equalsIgnoreCase("CHAR")
				|| sqlType.equalsIgnoreCase("VARCHAR2")){
			return "String";
		} else if (sqlType.equalsIgnoreCase("DATE")||sqlType.startsWith("TIMESTAMP")) {
			return "Date";
		} else if(sqlType.equalsIgnoreCase("CLOB")){
			return "CLOB";
		}
		return null;
	}
}
