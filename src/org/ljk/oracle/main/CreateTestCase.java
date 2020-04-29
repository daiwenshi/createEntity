package org.ljk.oracle.main;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.ljk.utils.FreeMarkerManager;

import template.oracle.ThisOracleClassPath;

public class CreateTestCase {

	public static void main(String[] args) throws UnsupportedEncodingException {
		//文件访问路径
//		String url = URLDecoder.decode("http://localhost:8080/file/罪证档案.html","UTF-8");
//		String url = URLEncoder.encode("http://localhost:8080/file/罪证档案.html","utf-8");
		String url ="http://localhost:8080/file/zwappfw.html";
		Document doc;
		StringBuffer strBuffer = new StringBuffer();
		List yblist = new ArrayList(); // 用例编号
		List smlist = new ArrayList(); // 所属模块
		List xslist = new ArrayList(); // 相关需求
		List yllist = new ArrayList(); // 用例类型
		List ybtlist = new ArrayList(); // 用例标题
		List qtlist = new ArrayList(); // 前置条件
		List bzlist = new ArrayList(); // 步骤
		List yqlist = new ArrayList(); // 预期
		List sqlist = new ArrayList(); // 实际情况
		List jglist = new ArrayList(); // 结果
		List yzlist = new ArrayList(); // 用例状态
		List sclist = new ArrayList(); // 由谁创建
		List crlist = new ArrayList(); // 创建日期
		List ybblist = new ArrayList(); // 用例版本
		List zxlist = new ArrayList(); // 最后修改者
		List xrlist = new ArrayList(); // 修改日期
		List bblist = new ArrayList(); // B
		List rrlist = new ArrayList(); // R
		List sslist = new ArrayList(); // S

		try {
			doc = Jsoup.connect(url).get();

			Elements tableLinks = doc.getElementsByTag("table");

			Elements tbodyLinks = tableLinks.get(0).getElementsByTag("tbody");
			for (Element tbodyLink : tbodyLinks) {
				Elements trLinks = tbodyLink.getElementsByTag("tr");
				for (int i = 1; i < trLinks.size(); i++) {

					Elements tdLinks = trLinks.get(i).getElementsByTag("td");
					// String num=""; //title
					yblist.add(tdLinks.get(0).html()); // 用例编号
					smlist.add(tdLinks.get(2).text()); // 所属模块
					xslist.add(tdLinks.get(3).html()); // 相关需求
					yllist.add(tdLinks.get(11).html()); // 用例类型
					ybtlist.add(tdLinks.get(4).text()); // 用例标题
					qtlist.add(tdLinks.get(5).text()); // 前置条件
					bzlist.add(tdLinks.get(6).html()); // 步骤
					yqlist.add(tdLinks.get(7).html()); // 预期
					sqlist.add(tdLinks.get(8).html()); // 实际情况
					jglist.add(tdLinks.get(17).html()); // 结果
					yzlist.add(tdLinks.get(13).html()); // 用例状态
					sclist.add(tdLinks.get(18).html()); // 由谁创建
					crlist.add(tdLinks.get(19).html()); // 创建日期
					ybblist.add(tdLinks.get(22).html()); // 用例版本
					zxlist.add(tdLinks.get(20).html()); // 最后修改者
					xrlist.add(tdLinks.get(21).html()); // 修改日期
					bblist.add(tdLinks.get(14).html()); // B
					rrlist.add(tdLinks.get(15).html()); // R
					sslist.add(tdLinks.get(16).html()); // S

				}

			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("yblist", yblist);
			map.put("smlist", smlist);
			map.put("xslist", xslist);
			map.put("yllist", yllist);
			map.put("ybtlist", ybtlist);
			map.put("qtlist", qtlist);
			map.put("bzlist", bzlist);
			map.put("yqlist", yqlist);
			map.put("sqlist", sqlist);
			map.put("jglist", jglist);
			map.put("yzlist", yzlist);
			map.put("sclist", sclist);
			map.put("crlist", crlist);
			map.put("ybblist", ybblist);
			map.put("zxlist", zxlist);
			map.put("xrlist", xrlist);
			map.put("bblist", bblist);
			map.put("rrlist", rrlist);
			map.put("sslist", sslist);
			String fileName = smlist.get(0) + ".doc";
			//模板名称
			String templateName = "csyl.ftl";
			//生成文件存储路径
//			String savePath = "F:/测试用例/" + smlist.get(0);
			String savePath = "F:/测试用例/" ;
			FreeMarkerManager freeMarker = new FreeMarkerManager();
			freeMarker.init(ThisOracleClassPath.class);
			freeMarker.otherProcess(templateName, fileName, "UTF-8", map, savePath);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
