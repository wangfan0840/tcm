package tcm.com.gistone.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Client帮助类
 * @author WangShanxi
 * @version v.0.1
 * @date 2017年2月24日
 */
public class ClientUtil {

	private final static Logger logger = LoggerFactory.getLogger(ClientUtil.class);
	
	private static ContentType contentType = ContentType.APPLICATION_JSON;
	private int timeout;
	
	/**
	 * 设置编码和跨域
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	public static void SetCharsetAndHeader(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		request.setCharacterEncoding("utf-8");
		//response.setContentType("text/json;charset=utf-8");
		//设置跨域
		response.setHeader("Access-Control-Allow-Origin", "*");
		/*response.setHeader("Access-Control-Allow-Headers", "*");
		response.setHeader("Access-Control-Request-Method","*");*/
	}
	
	
	/**
	 * 执行Post请求 带参数
	 * @param url
	 * @param param
	 * @return
	 */
	public static String doPost(String url,String param) {
		// 创建Httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String resultString = "";
		try {
			// 创建Http Post请求
			HttpPost httpPost = new HttpPost(url);
			// 执行http请求
			StringEntity entity = new StringEntity(param,"utf-8");//解决中文乱码问题    
            entity.setContentEncoding("UTF-8");    
            entity.setContentType("application/json");  
			httpPost.setEntity(entity);
			response = httpClient.execute(httpPost);
			resultString = EntityUtils.toString(response.getEntity(), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return resultString;
	}

	/**
	 * 执行Post请求 不带参数
	 * @param url
	 * @return
	 */
	public static String doPost(String url) {
		return doPost(url, null);
	}
	
	public EdatResult doGet(String params, String url, int timeout) {
		this.timeout = timeout;
		url = params == null ? url : (url + "?" + params);
	    return doRequestData(null, Request.Get(url));
	  }
	
	public EdatResult doRequestData(String params, Request request) {
		long start = System.currentTimeMillis();
		Content content = null;
	    Exception exception = null;
	    try {
	      if (params != null) {
	        request = request.bodyString(params, contentType);
	      }
	      content = request
	          .socketTimeout(timeout)
	          .connectTimeout(timeout)
	          .execute()
	          .returnContent();
	    } catch (Exception e) {
	      exception = e;
	    }
	    long cost = System.currentTimeMillis() - start;
	    if (exception == null) {
            logger.info(formatLog("request info", cost, timeout, params, request));
        } else {
            logger.error(formatLog("request failed", cost, timeout, request), exception);
        }
	    EdatResult EdatResult = new EdatResult();
		try {
			Map map = JsonUtil.jsonToObj(content.asString(), Map.class);
			EdatResult.setData(map.get("data"));
			EdatResult.setMsg((String) map.get("result"));
			String code = (String) map.get("code");
			int c = StringUtils.isEmpty(code.trim()) ? 0 : Integer.valueOf(code);
			EdatResult.setStatus(c);
			return EdatResult;
		} catch (IOException e) {
			logger.error("request response convert EdatResult error", e);
			return null;
		}
	}
	
	public static String formatLog(String desc, Object... params) {
	    StringBuilder sb = new StringBuilder();
	    sb.append(desc);
	    sb.append(" - [");
	    int i = 0;
	    for (Object obj : params) {
	      if (i++ > 0) sb.append(",");
	      sb.append(obj);
	    }
	    sb.append("]");
	    return sb.toString();
	  }
}
