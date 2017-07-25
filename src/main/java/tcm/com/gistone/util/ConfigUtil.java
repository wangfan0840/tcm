package tcm.com.gistone.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@Component
@Configuration
@PropertySource(value = "classpath:/config.properties")
/**
 * 路径帮助类
 * 
 * @author WangShanxi
 */
public class ConfigUtil {
	// 气象数据zipUrl
	@Value("${zipURL}")
	private String zipURL;

	public String getZipURL() {
		return zipURL;
	}
	
	
}
