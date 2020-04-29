package org.ljk.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import template.oracle.ThisOracleClassPath;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

public class FreeMarkerManager {
	private static final String osName=System.getProperty("os.name");
	private Configuration cfg;


	public void init(Class class_) throws Exception {

		// 初始化FreeMarker配置
		// 创建一个Configuration实例
		cfg = new Configuration();
		String templatePath=class_.getResource("").getPath();
		// 设置FreeMarker的模版文件位置
		cfg.setDirectoryForTemplateLoading(new File(templatePath));

        cfg.setObjectWrapper(new DefaultObjectWrapper());
        cfg.setDefaultEncoding("UTF-8");

	}
	
	/**
	 * 
	 * @param modelName
	 *            模板名如： 模板.htm
	 * @param fileName
	 *            要生成的文件的文件名如：index.html
	 * @param charet
	 *            生成文件的编码 如：UTF-8
	 * @param distDir 生成目标文件的目录  不需要以 / 开头
	 * @throws Exception
	 */
	public boolean otherProcess(String modelName, String fileName,
			String charet, Map freeMarkerData,String distDir){
		try{
			//检查是否不存在此目录，不存在时创建目录
			String filePathDir=creatDirs(distDir)+"/";
	
			Template t = cfg.getTemplate(modelName);
			Writer out = new OutputStreamWriter(new FileOutputStream(filePathDir
					+ fileName), charet);
			t.process(freeMarkerData, out);
			out.close();
			System.out.println(fileName+"--Successfull................");

			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}

	}
	
	
	/**
	 * 创建目录
	 * 
	 * @param aParentDir
	 *            String
	 * @param aSubDir
	 *            以 / 开头
	 * @return 最终的目录路径
	 */
	public String creatDirs(String distDir) {
		File aSubFile = new File(distDir);
		if (!aSubFile.exists()) {
			aSubFile.mkdirs();
		}			
		return distDir;

	}


	public static void main(String[] args) throws Exception {

	}

}
