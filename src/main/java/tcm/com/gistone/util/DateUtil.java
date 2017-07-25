package tcm.com.gistone.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 日期转换帮助类
 * 
 * @author WangShanxi
 * @version v.0.1
 * @date 2017年3月11日
 */
public class DateUtil {

	public final static String DATE_FORMAT = "yyyy-MM-dd";
	private final static Logger logger = LoggerFactory.getLogger(DateUtil.class);

	/**
	 * 
	 * @Description: TODO
	 * @param str
	 * @return 字符串转化成日期 年月日形式 Date
	 * @throws @author
	 *             yanglei
	 * @date 2017年3月21日 下午3:16:47
	 */
	public static Date StrtoDateYMD(String str) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = format.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 
	 * @Description: 字符串转化为时间
	 * @param str
	 * @param pattern
	 * @return Date
	 * @throws @author
	 *             yanglei
	 * @date 2017年3月24日 下午3:57:35
	 */
	public static Date StrtoDateYMD(String str, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = format.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 
	 * @Description: TODO
	 * @param date
	 * @return String 时间的转化 年月日
	 * @throws @author
	 *             yanglei
	 * @date 2017年3月20日 下午7:51:09
	 */
	public static String DATEtoString(Date date, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		String str = format.format(date);
		return str;
	}
}
