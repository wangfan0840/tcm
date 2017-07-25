package tcm.com.gistone.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {
	//定义日志类
	private final static Logger logger = LoggerFactory.getLogger(LogUtil.class);
	public static Logger getLogger(){
		return logger;
	}
}
