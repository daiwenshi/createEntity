<html xmlns:v="urn:schemas-microsoft-com:vml" xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40">
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="ProgId" content="Excel.Sheet"/>
  <meta name="Generator" content="WPS Office ET"/>
  <link rel="Stylesheet" href="stylesheet.css"/>

 </head>
 <body link="blue" vlink="purple">
 
<#list yblist as ybl>

  <table width="690" border="1" cellpadding="1" cellspacing="1" id="a1" style='width:333pt;border-collapse:collapse;table-layout:fixed;'>
   <col width="71" style='mso-width-source:userset;mso-width-alt:2272;'/>
   <col width="72" style='width:54.00pt;'/>
   <col width="87" style='mso-width-source:userset;mso-width-alt:2784;'/>
   <col width="72" span="3" style='width:54.00pt;'/>
   <tr height="18" style='height:13.50pt;'>
    <td class="xl65" height="18" width="446" colspan="6" style='height:13.50pt;width:200.50pt;border-right:.5pt solid windowtext;border-bottom:.5pt solid windowtext;'>${yblist[ybl_index]}</td>
   </tr>
   <tr height="18" style='height:13.50pt;'>
    <td class="xl66" height="18" style='width:76;border-right:.5pt solid windowtext;border-bottom:.5pt solid windowtext;' x:str>用例编号</td>
    <td class="xl67" style='width:230;border-right:.5pt solid windowtext;border-bottom:.5pt solid windowtext;' x:str>${yblist[ybl_index]}</td>
    <td class="xl67" style='width:76;border-right:.5pt solid windowtext;border-bottom:.5pt solid windowtext;' x:str x:str>B：${bblist[ybl_index]}</td>
    <td class="xl67" style='width:230;border-right:.5pt solid windowtext;border-bottom:.5pt solid windowtext;' x:str x:str>R：${rrlist[ybl_index]}</td>
    <td class="xl67" style='width:76;border-right:.5pt solid windowtext;border-bottom:.5pt solid windowtext;' x:str>S：${sslist[ybl_index]}</td>
    <td class="xl67" style='width:230;border-right:.5pt solid windowtext;border-bottom:.5pt solid windowtext;' x:str></td>
   </tr>
   <tr height="18" style='height:13.50pt;'>
    <td class="xl66" height="18" style='width:76;border-right:.5pt solid windowtext;border-bottom:.5pt solid windowtext;' x:str>所属模块</td>
    <td class="xl67" style='width:230;border-right:.5pt solid windowtext;border-bottom:.5pt solid windowtext;' x:str>${smlist[ybl_index]}</td>
    <td class="xl66" style='width:76;border-right:.5pt solid windowtext;border-bottom:.5pt solid windowtext;' x:str>相关需求</td>
    <td class="xl67" style='width:230;border-right:.5pt solid windowtext;border-bottom:.5pt solid windowtext;' x:str>${xslist[ybl_index]}</td>
    <td class="xl66" style='width:76;border-right:.5pt solid windowtext;border-bottom:.5pt solid windowtext;' x:str>用例类型</td>
    <td class="xl67" style='width:230;border-right:.5pt solid windowtext;border-bottom:.5pt solid windowtext;' x:str>${yllist[ybl_index]}</td>
   </tr>
   <tr height="18" style='height:13.50pt;'>
    <td class="xl66" height="18" style='width:76;border-right:.5pt solid windowtext;border-bottom:.5pt solid windowtext;' x:str>用例标题</td>
    <td class="xl67" colspan="5" style='align:left;width:680;border-right:.5pt solid windowtext;border-bottom:.5pt solid windowtext;' x:str>${ybtlist[ybl_index]}</td>
   </tr>
   <tr height="18" style='height:13.50pt;'>
    <td class="xl66" height="18" style='width:76;border-right:.5pt solid windowtext;border-bottom:.5pt solid windowtext;' x:str>前置条件</td>
    <td class="xl67" colspan="5" style='align:left;width:608;border-right:.5pt solid windowtext;border-bottom:.5pt solid windowtext;' x:str>${qtlist[ybl_index]}</td>
   </tr>
   <tr height="18" style='height:13.50pt;'>
    <td class="xl69" height="18" colspan="2" style='width:230;height:13.50pt;border-right:.5pt solid windowtext;border-bottom:.5pt solid windowtext;' x:str>步骤</td>
    <td class="xl69" colspan="2"	     style='width:230;border-right:.5pt solid windowtext;border-bottom:.5pt solid windowtext;' x:str>预期</td>
    <td class="xl69" colspan="2"             style='width:230;border-right:.5pt solid windowtext;border-bottom:.5pt solid windowtext;' x:str>实际情况</td>
   </tr>
   <tr height="134.67" style='height:101.00pt;mso-height-source:userset;mso-height-alt:2020;'>
    <td class="xl65" height="134.67" colspan="2" style='width:230;height:101.00pt;border-right:.5pt solid windowtext;border-bottom:.5pt solid windowtext;' x:str>${bzlist[ybl_index]}</td>
    <td class="xl65" colspan="2" style='width:230;border-right:.5pt solid windowtext;border-bottom:.5pt solid windowtext;' x:str>${yqlist[ybl_index]}</td>
    <td class="xl65" colspan="2" style='width:230;border-right:.5pt solid windowtext;border-bottom:.5pt solid windowtext;' x:str>${sqlist[ybl_index]}</td>
   </tr>
   <tr height="34.67" style='height:26.00pt;mso-height-source:userset;mso-height-alt:520;'>
    <td class="xl66" height="34.67" style='width:76;border-right:.5pt solid windowtext;border-bottom:.5pt solid windowtext;' x:str>结果</td>
    <td class="xl68" colspan="5" style='width:608;border-right:.5pt solid windowtext;border-bottom:.5pt solid windowtext;' x:str>${jglist[ybl_index]}</td>
   </tr>
   <tr height="18" style='height:13.50pt;'>
    <td class="xl66" height="18" style='height:13.50pt;' x:str>用例状态</td>
    <td class="xl67">${yzlist[ybl_index]}</td>
    <td class="xl66" x:str>由谁创建</td>
    <td class="xl67">${sclist[ybl_index]}</td>
    <td class="xl66" x:str>创建日期</td>
    <td class="xl67">${crlist[ybl_index]}</td>
   </tr>
   <tr height="18" style='height:13.50pt;'>
    <td class="xl66" height="18" style='height:13.50pt;' x:str>用例版本</td>
    <td class="xl67">${ybblist[ybl_index]}</td>
    <td class="xl66" x:str>最后修改者</td>
    <td class="xl67">${zxlist[ybl_index]}</td>
    <td class="xl66" x:str>修改日期</td>
    <td class="xl67">${xrlist[ybl_index]}</td>
   </tr>

  </table>
  <br/>
  </#list>
  
 </body>
</html>
