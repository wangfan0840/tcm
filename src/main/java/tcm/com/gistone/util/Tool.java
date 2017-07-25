package tcm.com.gistone.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



public class Tool {

	public static String md5(String str) throws NoSuchAlgorithmException,
			UnsupportedEncodingException {
		MessageDigest md5 = null;
		md5 = MessageDigest.getInstance("MD5");
		char[] charArray = str.toCharArray();
		byte[] byteArray = new byte[charArray.length];
		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
			byte[] md5Bytes = md5.digest(byteArray);
			StringBuffer hexValue = new StringBuffer();
			for (int i = 0; i < md5Bytes.length; i++) {
				int val = ((int) md5Bytes[i]) & 0xff;
				if (val < 16)
					hexValue.append("0");
				hexValue.append(Integer.toHexString(val));
			}
		return hexValue.toString();

	}
	public static String md5Tostr(String md5) throws NoSuchAlgorithmException,
	UnsupportedEncodingException {
		return null;
	}
	  /**
	 *@返回类型:String
	 *@参数：@param inStr
	 *@参数：@return
	 *@创建人：hanguojing
	 *@修改时间:2012-12-18 上午10:54:12
	 *@方法描述:加密算法
	*/
	public static String convertMD5(String inStr){  
		  char[] a = inStr.toCharArray();  
		  for (int i = 0; i < a.length; i++){  
		      a[i] = (char) (a[i] ^ 't');  
		    }  
		     String s = new String(a);  
		      return s;  
	} 
	/**
	 *@返回类型:String
	 *@参数：@param inStr
	 *@参数：@return
	 *@修改时间:2013-1-14 下午03:12:30
	 *@方法描述:加密后解密
	*/
	public static String JM(String inStr) {
	  char[] a = inStr.toCharArray();
	  for (int i = 0; i < a.length; i++) {
	   a[i] = (char) (a[i] ^ 't');
	  }
	  String k = new String(a);
	  return k;
	 }

	
//	public static void main(String agrs[]) throws NoSuchAlgorithmException, UnsupportedEncodingException{
//		String pwd ="1";
//		String md5str = Tool.md5(pwd);
//		System.out.println(md5str);
//		md5str=convertMD5(md5str);
//		System.out.println("加密:"+md5str);
//		String jmstr=Tool.JM(md5str);
//		System.out.println("解密："+jmstr);
//	}
/*	public static void main(String agrs[]) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		String pwd ="1";
		String md5str = Tool.md5(pwd);
		System.out.println(md5str);
		md5str=convertMD5(md5str);
		System.out.println("加密:"+md5str);
		String jmstr=Tool.JM(md5str);
		System.out.println("解密："+jmstr);
	}
>>>>>>> .r1898
*/
	

}
