package tcm.com.gistone.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Clob;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;



import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Json帮助类
 * @author WangShanxi
 * @version v.0.1
 * @date 2017年2月28日
 */
public class JsonUtil {

	private static ObjectMapper MAPPER = new ObjectMapper();
	
	public final static ObjectMapper mapper = new ObjectMapper();
	
	public final static ObjectMapper mapper2 =mapper.enable(SerializationFeature.INDENT_OUTPUT, SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS);
	/**
	 * 将Clob转换成Object对象
	 * @param clob
	 * @return
	 * @throws SQLException 
	 */
	public static Object clobToObject(Clob clob) throws Exception{
		String string=clob.getSubString(1, (int) clob.length());
		return MAPPER.readValue(string, Object.class);
	}
	/**
	 * 将String转换成指定对象
	 * @param clob
	 * @return
	 * @throws SQLException 
	 */
	public static Object strToClass(String str,Class clazz) throws Exception{
		return MAPPER.readValue(str, clazz);
	}
	
	

	  public static <T> T read(Path p, Class<T> clz) throws IOException {
	    return mapper.readValue(Files.readAllBytes(p), clz);
	  }


	  public static <T> T read(String s, Class<T> clz) throws IOException {
	    return read(Paths.get(s), clz);
	  }

	  public static <T> T read(byte[] a, Class<T> clz) throws IOException {
	    return mapper.readValue(a, clz);
	  }

	  public static <T> T clone(T o) {
	    try {
	      JsonNode node = mapper.valueToTree(o);
	      return mapper.treeToValue(node, (Class<T>) o.getClass());
	    } catch (JsonProcessingException ex) {
	      ex.printStackTrace();
	      return null;
	    }
	  }


	  //
	  // reference URL:
	  //   http://stackoverflow.com/questions/9895041/merging-two-json-documents-using-jackson
	  //
	  public static JsonNode extend(JsonNode mainNode, JsonNode updateNode) {
	    if (updateNode == null) {
	      return mainNode;
	    }
	    if (mainNode == null) {
	      return updateNode;
	    }

	    Iterator<String> fieldNames = updateNode.fieldNames();
	    while (fieldNames.hasNext()) {

	      String fieldName = fieldNames.next();
	      JsonNode jsonNode = mainNode.get(fieldName);

	      if (jsonNode != null && jsonNode.isObject()) {
	        extend(jsonNode, updateNode.get(fieldName));
	      } else {
	        if (mainNode instanceof ObjectNode) {
	          // Overwrite field
	          JsonNode value = updateNode.get(fieldName);
	          //当权限高级的配置文件里未指定值的key-value时，用最底层的
	          if (value.toString().equals("null"))
	            continue;
	          ((ObjectNode) mainNode).set(fieldName, value);
	        }
	      }

	    }

	    return mainNode;
	  }


	  public static JsonNode extend(JsonNode base, JsonNode... nodes) {
	    if (nodes.length == 0) {
	      return base;
	    }

	    JsonNode res = base;
	    for (JsonNode node : nodes) {
	      res = extend(res, node);
	    }
	    return res;
	  }

	  public static JsonNode extend(JsonNode base, Object... objs) {
	    if (objs == null || objs.length == 0) {
	      return base;
	    }

	    JsonNode nodes[] = new JsonNode[objs.length];
	    for (int i = 0; i < objs.length; ++i) {
	      if (objs[i] == null) {
	        nodes[i] = null;
	      } else if (objs[i] instanceof JsonNode) {
	        nodes[i] = (JsonNode) objs[i];
	      } else {
	        nodes[i] = mapper.valueToTree(objs[i]);
	      }
	    }
	    return extend(base, nodes);
	  }


	  public static ObjectMapper getInstance() {
	    DateFormat myDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    mapper2.setDateFormat(myDateFormat);
	    return mapper2;
	  }

	  public static String objToJson(Object object) throws JsonProcessingException {
	    String json = getInstance().writeValueAsString(object);
	    return json;
	  }

	  public static <T> T jsonToList(String json, Class clazz) throws IOException {
	    T list = mapper2.readValue(json, mapper2.getTypeFactory().constructCollectionType(List.class, clazz));
	    return list;
	  }

	  public static <T> T jsonToObj(String json, Class clazz) throws IOException {
	    mapper2.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
	    T t = (T) getInstance().readValue(json, clazz);
	    return t;
	  }

	  public static <T> T readObjFromJsonFile(String file, Class clazz) throws IOException {
	    String str = readFile(file);
	    return jsonToObj(str, clazz);
	  }

	  public static String readFile(String path) throws IOException {
	    String encoding = "UTF-8";
	    byte[] encoded = Files.readAllBytes(Paths.get(path));
	    return new String(encoded, encoding);
	  }
	  
	  public static <T> T jsonToObj2(String json, Class clazz) throws IOException {
		  mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
	     T t = (T) mapper.readValue(json, clazz);
	     return t;
	  }
}
