package template.oracle;

/**
 * @author linjiekai
 * 用于取得本类所在包的路径
 * 2012-7-31 下午03:38:08
 */
public class ThisOracleClassPath {
	
	public static void main(String des[]){
		System.out.println(ThisOracleClassPath.class.getResource("").getPath());
	}

}
