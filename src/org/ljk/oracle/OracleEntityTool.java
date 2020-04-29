package org.ljk.oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ljk.utils.FreeMarkerManager;

import template.oracle.ThisOracleClassPath;


/**
 * @author linjiekai
 * OracleEntityTool.java 
 * Oct 22, 2009 11:41:48 AM 
 * 生成oracle的实体类文件
 */
public class OracleEntityTool { 
	
	private static String _package=BeanProperties.sys_package;//实体类包名org.ga.sys.
	private static String web_package=BeanProperties.web_package;//web action 包名org.ga.action
	private static String _tableName=BeanProperties._tableName;//大小写都可以，表名
	private static String path=BeanProperties.path;//存储路径
	private static String tableName_comments=_tableName;//表名注释
	
	private List<String> colNames; // 列名数组
	private List<String> colTypes; // 列名类型数组
	private List<String> comments;//表字段的备注注释 
	private List<Integer> intLengths;//数据的存放长度

	
	private static String sql="select A.column_name,A.data_type,A.nullable,B.comments,a.Data_length,a.COLUMN_ID from " +
			" user_tab_columns A,user_col_comments B where A.Table_Name = B.Table_Name and A.Column_Name = B.Column_Name" +
			" and A.Table_Name = ? order by a.COLUMN_ID asc";
	private static String tabnameSql="select table_name,comments from user_tab_comments where table_name=?";
	
	public OracleEntityTool(){
		colNames=new ArrayList<String>();//字段名称
		colTypes=new ArrayList<String>();//字段类型
		comments=new ArrayList<String>();//字段注释
		intLengths=new ArrayList<Integer>();//字段长度
		try {
			OracleJdbc jdbc = new OracleJdbc();
			Connection conn = jdbc.getConn(); // 得到数据库连接
			//1.获取表单字段相应信息
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, _tableName.toUpperCase());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				colNames.add(rs.getString("column_name"));
				colTypes.add(rs.getString("data_type"));
				comments.add(rs.getString("comments"));
				intLengths.add(rs.getInt("data_length"));
			}
			rs.close();
			
			//2.获取表单名称comments
			PreparedStatement psTabname = conn.prepareStatement(tabnameSql);
			psTabname.setString(1, _tableName.toUpperCase());
			ResultSet rsTable = psTabname.executeQuery();
			if (rsTable.next()) {
				//rs.getString("table_name");
				tableName_comments=rsTable.getString("comments");
				BeanProperties.setTableName_comments(tableName_comments);
			}
			rsTable.close();
			
			conn.close();
			// 获取当前日期
			String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar
					.getInstance().getTime());
			///模板
			Map freeMarkerData=new HashMap();
			

			//生成属性
			freeMarkerData.put("createPropStr", processAllAttrs());
			////生成get/set方法
			freeMarkerData.put("createFunctionStr", processAllMethod());

			freeMarkerData.put("now", now);
			//-------------------------------------------------------------------Entity-----------------
			String entityName=initcapTableName(_tableName);
			freeMarkerData.put("entityName", entityName);
			freeMarkerData.put("tableName",_tableName.toUpperCase());
			freeMarkerData.put("package",_package);
			freeMarkerData.put("tableName_comments", tableName_comments);//表名中文解释
			freeMarkerData.put("DB_USER", OracleJdbc.theUser.toUpperCase());
			
			FreeMarkerManager freeMarker=new FreeMarkerManager();
			freeMarker.init(ThisOracleClassPath.class);
			String entityPath=path+"/"+(_package+".entity").replace(".", "/");
			System.out.println(entityPath);
			freeMarker.otherProcess("EntityTemplate.ftl", entityName + ".java", "UTF-8", freeMarkerData, entityPath);
			
			//---------------------------------------------------------------Dao------------------------------------------
			String idType=BeanProperties.oracleType2JavaType(colTypes.get(1));
			freeMarkerData.put("idType", idType);
			String daoPath=path+"/"+(_package+".dao").replace(".", "/");
			freeMarker.otherProcess("DaoTemplate.ftl", entityName + "Dao.java", "UTF-8", freeMarkerData, daoPath);
			
			//--------------------------------------------------------------DaoImpl---------------------------------------
			String daoImplPath=path+"/"+(_package+".daoimpl").replace(".", "/");
			freeMarker.otherProcess("DaoImplTemplate.ftl", entityName + "DaoImpl.java", "UTF-8", freeMarkerData, daoImplPath);
			
			//------------------------------------------------------------Service-----------------------------------------
			String servicePath=path+"/"+(_package+".service").replace(".", "/");
			freeMarker.otherProcess("ServiceTemplate.ftl", entityName + "Service.java", "UTF-8", freeMarkerData, servicePath);
			
			
			//------------------------------------------------------------ServiceImpl------------------------------------
			String Name=initcapColName(_tableName);//首字母小写
			freeMarkerData.put("Name", Name);
			String serviceImplPath=path+"/"+(_package+".serviceimpl").replace(".", "/");
			freeMarker.otherProcess("ServiceImplTemplate.ftl", entityName + "ServiceImpl.java", "UTF-8", freeMarkerData, serviceImplPath);
			
			//-----------------------------------------------------------Action--------------------------------------------
			
			freeMarkerData.put("web_package", web_package);
			String actionPath=path+"/"+(web_package+".action").replace(".", "/");
			freeMarker.otherProcess("ActionTemplate.ftl", entityName + "Action.java", "UTF-8", freeMarkerData, actionPath);
			
			//-----------------------------------------------end------------------------------------------------------------------
		} catch (Exception exx) {
			exx.printStackTrace();
		}finally{
			
		}
	}
	
	/**
	 * @param args
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException {
		OracleEntityTool oe=new OracleEntityTool();
 
		System.err.println("成功生成实体类");

	}

	
	
	/**
	 * 把表名/get/set后半部的属性等字符串的首字母改成大写
	 * 
	 * @param str
	 * @return
	 */
	private String initcapTableName(String str) {
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
	/**
	 * 将字段生成属性首字母小写
	 * 把列名输入字符串的第二"_"后的首字母改成大写
	 * 
	 * @param str "KAI_name"
	 * @return  kaiName
	 */
	private String initcapColName(String ColName) {
		String word[]=ColName.trim().toLowerCase().split("_");
		StringBuffer sb=new StringBuffer();
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
	 * 解析输出属性
	 * 
	 * @return
	 */
	private String processAllAttrs() {
		StringBuffer content=new StringBuffer();
		
		StringBuffer idStr=new StringBuffer();
		StringBuffer otherStr=new StringBuffer();
		StringBuffer mapStr=new StringBuffer();
		for (int i = 0; i < colNames.size(); i++) {
			String attr=initcapColName(colNames.get(i));//属性，第一个字母小写
			String dataType=BeanProperties.oracleType2JavaType(colTypes.get(i));
			if(attr.equals("id")){
				if(null!=comments.get(i))//字段注释
					idStr.append("\t//"+comments.get(i)+"\r\n");//生成注释
				idStr.append("\tprivate " + dataType + " "	+ attr + ";\r\n\r\n");//生成属性
			}else if(dataType.equals("CLOB")){//String 大字段
				if(null!=comments.get(i))//字段注释
					otherStr.append("\t//"+comments.get(i)+" [String 大字段]\r\n");
				otherStr.append("\tprivate String "	+ attr + ";\r\n\r\n");
			}else{
				if(null!=comments.get(i))//字段注释
					otherStr.append("\t//"+comments.get(i)+"\r\n");
				otherStr.append("\tprivate " + dataType + " "	+ attr + ";\r\n\r\n");
				//添加字段的长度信息
				//otherStr.append("\tpublic static int "+ attr + "_Length="+intLengths.get(i)+";\r\n\r\n");
				mapStr.append("\t map.put(\""+colNames.get(i)+"\", "+intLengths.get(i)+");\r\n");
			}
		}
		content.append(idStr.toString());
		content.append(otherStr.toString());
		content.append("\r\n\r\n");//换行隔开
		
/*		需要map信息时,可以打开此处
		content.append("{\nMap map=new HashMap();\n");
		content.append(mapStr.toString());
		content.append("}\n");
		*/
		return content.toString();
	}
	/**
	 * 生成所有的方法
	 * 
	 * @param content
	 */
	private String processAllMethod() {
		StringBuffer content=new StringBuffer();
		StringBuffer idStr=new StringBuffer();
		StringBuffer otherStr=new StringBuffer();
		
		for (int i = 0; i < colNames.size(); i++) {
			String yuanMing=colNames.get(i);//数据表里的字段原名
			String colName=initcapTableName(yuanMing);
			String attr=initcapColName(yuanMing);//属性，第一个字母小写
			String colType=BeanProperties.oracleType2JavaType(colTypes.get(i));
			
			if(attr.equals("id")){//ID主键
				idStr.append("\t@Id\r\n");
				
				idStr.append("\t@GeneratedValue(strategy = GenerationType.AUTO, generator = \"SEQ\")\r\n");
				idStr.append("\t@SequenceGenerator(name = \"SEQ\", sequenceName = \""+_tableName.toUpperCase()+"_SEQ\")\r\n");
				
				idStr.append("\t@Column(name =\""+yuanMing+"\")\r\n");
				idStr.append("\tpublic " + colType + " get"+ colName + "(){\r\n");
				idStr.append("\t\treturn this." + attr + ";\r\n");
				idStr.append("\t}\r\n");
				
				idStr.append("\tpublic void set" + colName + "("+ colType + " " + attr + "){\r\n");
				idStr.append("\t\tthis." + attr + "=" + attr + ";\r\n");
				idStr.append("\t}\r\n\r\n");
			}else{//非主键时生成get/set方法
				if(colType.equals("Date")){//日期时间
					otherStr.append("\t@Temporal(TemporalType.TIMESTAMP)\r\n");
					otherStr.append(getSetString(yuanMing, colType, colName, attr));
				}else if(colType.equals("CLOB")){//String 大字段
					otherStr.append("\t@Lob\r\n");
					otherStr.append("\t@Basic(fetch = FetchType.EAGER)\r\n");
					otherStr.append("\t@Column(name =\""+yuanMing+"\", columnDefinition=\"CLOB\", nullable=true)\r\n");
					otherStr.append("\tpublic String get"+ colName + "(){\r\n");
					otherStr.append("\t\treturn this." + attr + ";\r\n");
					otherStr.append("\t}\r\n");
					
					otherStr.append("\tpublic void set" + colName + "(String " + attr + "){\r\n");
					otherStr.append("\t\tthis." + attr + "=" + attr + ";\r\n");
					otherStr.append("\t}\r\n\r\n");
				}else{//String 字段
					otherStr.append(getSetString(yuanMing, colType, colName, attr));
				}

			}

		}
		
		content.append(idStr.toString());
		content.append(otherStr.toString());
		content.append("\r\n\r\n");//换行隔开
		return content.toString();
	}
	/**
	 * @return 取得get/set的方法字符串
	 */
	private String getSetString(String yuanMing,String colType,String colName,String attr){
		StringBuffer otherStr=new StringBuffer();
		otherStr.append("\t@Column(name =\""+yuanMing+"\")\r\n");
		otherStr.append("\tpublic " + colType + " get"+ colName + "(){\r\n");
		otherStr.append("\t\treturn this." + attr + ";\r\n");
		otherStr.append("\t}\r\n");
		
		otherStr.append("\tpublic void set" + colName + "("+ colType + " " + attr + "){\r\n");
		otherStr.append("\t\tthis." + attr + "=" + attr + ";\r\n");
		otherStr.append("\t}\r\n\r\n");
		return otherStr.toString();
	}
	
	


}
