package tcm.com.gistone.util;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 正则验证帮助类
 * @author WangShanxi
 * @version v.0.1
 * @date 2017年4月20日
 */
public class RegUtil {
	private static ObjectMapper mapper=new ObjectMapper();
	
	/**
	 * 参数验证   如果不需要指定某验证 给null值
	 * @param param	 要验证的参数
	 * @param clazz	 要转换的类型
	 * @param regEx  正则表达式
	 * @param isIgnore  正则是否忽略大小写
	 * @return 返回true 代表参数符合   false 不符合
	 */
	public static boolean CheckParameter(Object param,String clazz,String regEx,boolean isIgnore){
		//判断是否为空
		if(param==null) return false;
		//判断是否要判断目标类型
		if(clazz!=null){
			//是否是指定的目标类型
			if(clazz.equals("Integer")){
				try {  
					Integer.parseInt(param.toString());  
			    } catch (Exception e) {  
			         return false;  
			    }  
			}
			//是否是指定的目标类型
			if(clazz.equals("Short")){
				try {  
					Short.parseShort(param.toString());  
			    } catch (Exception e) {  
			         return false;  
			    }  
			}
			if(clazz.equals("String")){
				try {  
					param.toString();  
			    } catch (Exception e) {  
			         return false;  
			    }  
			}
			if(clazz.equals("Double")){
				try {  
					Double d=Double.parseDouble(param.toString());
					if(!param.toString().contains(".")) return false;
			    } catch (Exception e) {  
			         return false;  
			    }  
			}
			if(clazz.equals("Float")){
				try {  
					Float d=Float.parseFloat(param.toString());
					if(!param.toString().contains(".")) return false;
			    } catch (Exception e) {  
			         return false;  
			    }   
			}
			if(clazz.equals("Long")){
				try {  
					Long.parseLong(param.toString());  
			    } catch (Exception e) {  
			         return false;  
			    }  
			}
			if(clazz.equals("Date")){
				try {  
					DateUtil.StrtoDateYMD(param.toString());  
			    } catch (Exception e) {  
			         return false;  
			    }  
			}
			if(clazz.equals("List")){
				try { 
					List list=mapper.readValue(param.toString(), List.class);
					if(list==null||list.size()==0){
						return false;
					}
			    } catch (Exception e) {  
			         return false;  
			    } 
			}
			if(clazz.equals("Map")){
				try { 
					Map map=mapper.readValue(param.toString(), Map.class);
					if(map==null||map.size()==0){
						return false;
					}
			    } catch (Exception e) {  
			         return false;  
			    } 
			}
			if(clazz.equals("Set")){
				try { 
					Set set=mapper.readValue(param.toString(), Set.class);
					if(set==null||set.size()==0){
						return false;
					}
			    } catch (Exception e) {  
			         return false;  
			    } 
			}
		}
		//是否包含正则表达式
		if(regEx!=null){
			Pattern pattern=null;
			//是否忽略大小写
			if(isIgnore){
				pattern = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
			}else{
				pattern = Pattern.compile(regEx);
			}
			Matcher matcher = pattern.matcher(param.toString());
			if(!matcher.matches()) return false;
		}
		return true;
	}
	
}