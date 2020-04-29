package org.ljk.oracle.main;

import java.sql.SQLException;

import org.ljk.oracle.BeanProperties;
import org.ljk.oracle.OracleEntityTool;
import org.ljk.oracle.OracleJdbc;

public class CreateClassMain {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws Exception {
		
//		D:\workspace\gzxjcda\project\org\ga
		BeanProperties.set_package("org.ga.escape");//包名
		BeanProperties.set_tableName("ESCAPE_PERSON_CLUE_INFO");//数据库表名
 		BeanProperties.setPath("E:\\zdsc");//生成的类存放的路径
 		OracleJdbc.dbUrl="jdbc:oracle:thin:@HBSOFT:1521:CDA";
		OracleJdbc.theUser="gzxjcda";
		OracleJdbc.thePw="gzxjcda";
		 
		OracleEntityTool.main(new String[1]);
 
		
		System.err.println(BeanProperties.getEntityName()+"  CreateSuccessfuly!! in--"+BeanProperties.getPath());
		System.exit(0);

	}

}
