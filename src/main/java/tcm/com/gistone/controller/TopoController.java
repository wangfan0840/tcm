package tcm.com.gistone.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tcm.com.gistone.database.config.GetBySqlMapper;
import tcm.com.gistone.database.entity.Theme;
import tcm.com.gistone.database.entity.Word;
import tcm.com.gistone.database.mapper.ThemeMapper;
import tcm.com.gistone.database.mapper.WordMapper;
import tcm.com.gistone.database.mapper.WordRelationMapper;
import tcm.com.gistone.database.mapper.WsRelationMapper;
import tcm.com.gistone.util.ClientUtil;
import tcm.com.gistone.util.EdatResult;

/**
 * Created by qiang on 2017/7/14.
 */

@RestController
@RequestMapping
public class TopoController {
	@Autowired
	private GetBySqlMapper gm;
	@Autowired
	private WordMapper wm;
	@Autowired
	WordRelationMapper wrm;
	@Autowired
	ThemeMapper tm;
	@Autowired
	WsRelationMapper wsm;

	@RequestMapping(value = "/getRelationWithBookId", method = RequestMethod.POST)
	public EdatResult getRelationWithBookId(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			ClientUtil.SetCharsetAndHeader(request, response);
			JSONObject data = JSONObject.fromObject(request
					.getParameter("data"));
			String type = "zf";
			String keyWord = "甘草";
			long bookId = 1;
			Word word = wm.selectByWord(keyWord);
			String sql = "SELECT COUNT(DISTINCT tb_ws_relation.word_id) as total, word_type  "
					+ "FROM tb_ws_relation join (SELECT section_id "
					+ " FROM tb_ws_relation "
					+ " WHERE tb_ws_relation.word_id = ( "
					+ " SELECT word_id "
					+ " FROM tb_word "
					+ " WHERE word = '"
					+ keyWord
					+ "' )) b on tb_ws_relation.section_id=b.section_id where book_id= "
					+ bookId + " GROUP BY word_type;";
			List<Map> result = new ArrayList<>();
			result = gm.findRecords(sql);
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			for (Map tem : result) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("num", tem.get("total").toString());
				map.put("type", (String) tem.get("word_type"));
				list.add(map);
			}
			return EdatResult.build(0, "success", list);
		} catch (Exception e) {
			e.printStackTrace();
			return EdatResult.build(1, "fail");
		}
	}

	@RequestMapping(value = "/getRelatedWordsWithBookId", method = RequestMethod.POST)
	public EdatResult getRelatedWordsWithBookId(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			// ClientUtil.SetCharsetAndHeader(request, response);
			// Map<String, Object> data = (Map) requestDate.get("data"
			Map map = new HashMap();
			String targetType = "zf";// 匹配类
			String keyWord = "李时珍";// 搜索词
			Word word = wm.selectByWord(keyWord);

			long wordId = word.getWordId();// 搜索词id
			String wordType = word.getThemeType();// 搜索词类
			long tid1 = tm.selectIdByShort(wordType);// 搜索词类id
			long tid2 = tm.selectIdByShort(targetType);// 匹配词类i
			long bookId = 1;
			map.put("type", targetType);
			String sql = "SELECT DISTINCT tb_ws_relation.word_id as id , tb_ws_relation.word_type as type "
					+ "FROM tb_ws_relation join (SELECT section_id  FROM tb_ws_relation "
					+ " WHERE tb_ws_relation.word_id ="
					+ wordId
					+ ") b on tb_ws_relation.section_id=b.section_id where tb_ws_relation.book_id="
					+ bookId
					+ " and tb_ws_relation.word_type='"
					+ targetType
					+ "';";
			List<Map> result = new ArrayList<>();
			result = gm.findRecords(sql);// 搜索类匹配结果
			if (result.size() < 1) {
				return EdatResult.build(0, "没查到");
			}
			List<Map> list = new ArrayList<>();
			for (Map tem : result) {
				Map<String, Object> map1 = new HashMap();
				Word w = wm.selectByPrimaryKey((Long) tem.get("id"));
				int num = getNum(bookId, wordId, w.getWordId());
				map1.put("word", w.getWord());
				map1.put("id", (Long) tem.get("id"));
				map1.put("num", num);
				list.add(map1);
			}
			map.put("datas", list);
			return EdatResult.build(0, "success", map);
		} catch (Exception e) {
			e.printStackTrace();
			return EdatResult.build(1, "fail");
		}
	}

	@RequestMapping(value = "/getRelation", method = RequestMethod.POST)
	public EdatResult getRelation(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String keyWord = "甘草";
			Word word = wm.selectByWord(keyWord);
			String wordType = word.getThemeType();
			int type = tm.selectIdByShort(wordType).intValue();
			long wordId = word.getWordId();
			List<Map> result = new ArrayList<>();
			for (int i = 1; i < 10; i++) {
				if (type < i) {
					Map map = new HashMap();
					List<Map> list = wrm.selectByWord(type, i, wordId);
					int num = list.size();
					if (num > 0) {
						Theme theme = tm.selectByPrimaryKey((long) i);
						map.put("name", theme.getThemeName());
						map.put("short", theme.getThemeShort());
						map.put("num", num);
						map.put("datas", list);
						result.add(map);
					}
				} else if (type > i) {
					Map map = new HashMap();
					List<Map> list = wrm.selectByAnoWord(i, type, wordId);
					int num = list.size();
					if (num > 0) {
						Theme theme = tm.selectByPrimaryKey((long) i);
						map.put("name", theme.getThemeName());
						map.put("short", theme.getThemeShort());
						map.put("num", num);
						map.put("datas", list);
						result.add(map);
					}
				}
			}
			return EdatResult.build(0, "success", result);
		} catch (Exception e) {
			e.printStackTrace();
			return EdatResult.build(1, "fail");
		}

	}

	public int getNum(long bookId, long word1, long word2) {
		String sql = "select a.numa,b.numb from (select section_id,word_num as numa from tb_ws_relation where book_id="
				+ bookId
				+ " and word_id ="
				+ word1
				+ " )a"
				+ " join (select section_id,word_num as numb from tb_ws_relation where book_id ="
				+ bookId
				+ " and word_id = "
				+ word2
				+ ")b on a.section_id =b.section_id ";
		List<Map> result = new ArrayList<>();
		result = gm.findRecords(sql);// 搜索类匹配结果
		int num = 0;
		for (Map map : result) {
			int numa = Integer.parseInt(map.get("numa").toString());
			int numb = Integer.parseInt(map.get("numb").toString());
			if (numa < numb) {
				num += numa;
			} else {
				num += numb;
			}
		}
		return num;
	}

	@RequestMapping(value = "/word/getRelatedSection", method = RequestMethod.POST)
	public EdatResult getRelatedSection(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			ClientUtil.SetCharsetAndHeader(request, response);
			JSONObject data = JSONObject.fromObject(request
					.getParameter("data"));
			List<Map> result = new ArrayList<>();
			String sql = "SELECT  count(word_num)as total ,section_id as sectionId  FROM demo.tb_ws_relation where word_id = 711 group by section_id order by total desc limit 5;";
			result = gm.findRecords(sql);
			return EdatResult.ok( result);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return EdatResult.build(1, "fail");
		}
	}

	@RequestMapping(value = "/word/getSectionWithTwoWords", method = RequestMethod.POST)
	public EdatResult getSectionWithTwoWords(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			ClientUtil.SetCharsetAndHeader(request, response);
			JSONObject data = JSONObject.fromObject(request
					.getParameter("data"));
			long wordId1 = 5368;
			long wordId2 = 5443;
			List<Map> result = new ArrayList<>();
			String sql = "select a.section_id as sectionId ,a.word_num as num1,b.word_num as num2 from (SELECT * FROM demo.tb_ws_relation where word_id= "
					+ wordId1
					+ ")a join (SELECT * FROM demo.tb_ws_relation where word_id= "
					+ wordId2 + " )b on a.section_id = b.section_id limit 5 ;";
			result = gm.findRecords(sql);
			return EdatResult.ok(result);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return EdatResult.build(1, "fail");
		}
	}
}
