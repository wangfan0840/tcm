package tcm.com.gistone.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tcm.com.gistone.database.config.GetBySqlMapper;
import tcm.com.gistone.database.entity.Knowledge;
import tcm.com.gistone.database.mapper.BookinfoMapper;
import tcm.com.gistone.database.mapper.DirectoryMapper;
import tcm.com.gistone.database.mapper.KnowledgeMapper;
import tcm.com.gistone.database.mapper.SectionMapper;
import tcm.com.gistone.database.mapper.ThemeMapper;
import tcm.com.gistone.database.mapper.WordMapper;
import tcm.com.gistone.database.mapper.WsRelationMapper;
import tcm.com.gistone.util.ClientUtil;
import tcm.com.gistone.util.EdatResult;
import tcm.com.gistone.util.ExcelUtil;

@RestController
@RequestMapping
public class KnowledgeController {

	@Autowired
	SectionMapper sm;
	@Autowired
	private BookinfoMapper bm;
	@Autowired
	private KnowledgeMapper km;
	@Autowired
	private WordMapper wm;
	@Autowired
	private DirectoryMapper dm;
	@Autowired
	private WsRelationMapper wsm;
	@Autowired
	private ThemeMapper tm;
	@Autowired
	private GetBySqlMapper gm;
	
	public Map<String, String> selectByName(String name){
		String sql="select * from tb_knowledge where knowledge_name ='"+name+"'";
		List<Map> result = new ArrayList<>();
		result = gm.findRecords(sql);
		return result.get(0);
		
	}
	
	@RequestMapping(value = "knowledge/deleteKnowledge", method = RequestMethod.POST)
	public EdatResult deleteKnowkedge(HttpServletRequest request,
									  HttpServletResponse response){
		try {
			ClientUtil.SetCharsetAndHeader(request, response);
			JSONObject data = JSONObject.fromObject(request
					.getParameter("data"));
			km.deleteByPrimaryKey(data.getLong("id"));
			return EdatResult.build(0, "success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return EdatResult.build(1, "fail");
		}
	}
	@RequestMapping(value = "knowledge/updateKnowledge", method = RequestMethod.POST)
	public EdatResult updataKnowledge(HttpServletRequest request,
			HttpServletResponse response){
		try {
			ClientUtil.SetCharsetAndHeader(request, response);
			JSONObject data = JSONObject.fromObject(request
					.getParameter("data"));
			Knowledge knowledge = new Knowledge();
			knowledge.setKnowledgeId(data.getLong("id"));
			knowledge.setBookId((long) 2);
			knowledge.setSectionId((long) 1);
			knowledge.setKnowledgeName(data.getString("name"));
			knowledge.setAlias(data.getString("alias"));
			knowledge.setKnowledgeType(data.getString("type"));
			knowledge
					.setBirthEnv(data.getString("birthEnv"));
			knowledge.setPart(data.getString("part"));
			knowledge.setTaste(data.getString("taste"));
			knowledge.setGas(data.getString("gas"));
			knowledge.setChannelTropism(data.getString("channelTRopism"));
			knowledge.setLift(data.getString("lift"));
			knowledge
					.setToxicity(data.getString("toxicity"));
			knowledge.setBeard(data.getString("beard"));
			knowledge.setMake(data.getString("make"));
			knowledge.setHate("hate");
			knowledge
					.setMurder(data.getString("murder"));
			knowledge.setFear(data.getString("fear"));
			knowledge
					.setAgainst(data.getString("against"));
			knowledge.setMainFunction(data.getString("mainFunction"));
			knowledge
					.setSymptom(data.getString("symptom"));
			knowledge
					.setDisease(data.getString("disease"));
			knowledge.setComponent(data.getString("component"));
			knowledge
					.setUsages(data.getString("usages"));
			knowledge.setProcessing(data.getString("processing"));
			knowledge
					.setHarvest(data.getString("harvest"));
			knowledge
					.setGenuine(data.getString("genuine"));
			knowledge.setIdentification(data.getString("identification"));
			knowledge.setUseLevel(data.getString("useLevel"));
			knowledge.setRecording(data.getString("recording"));
			knowledge.setOther(data.getString("other"));
			knowledge.setTaboo(data.getString("taboo"));
			knowledge.setSideEffect(data.getString("sideEffect"));
			knowledge.setDrugLike(data.getString("drugLike"));
			knowledge.setAttention(data.getString("attention"));
			knowledge
					.setFigure(data.getString("figure"));
			km.updateByPrimaryKey(knowledge);
			return EdatResult.build(0, "success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return EdatResult.build(1, "fail");
		}
		
	}
	
	@RequestMapping(value = "knowledge/recordKnowledge", method = RequestMethod.POST)
	public EdatResult getBookinfo(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			ClientUtil.SetCharsetAndHeader(request, response);
			JSONObject data = JSONObject.fromObject(request
					.getParameter("data"));
			String name=data.getString("name");
			if(selectByName(name)==null){
				return EdatResult.build(0, "名称重复");
			}
			Knowledge knowledge = new Knowledge();
			knowledge.setBookId((long) 2);
			knowledge.setSectionId((long) 1);
			knowledge.setKnowledgeName(name);
			knowledge.setAlias(data.getString("alias"));
			knowledge.setKnowledgeType(data.getString("type"));
			knowledge
					.setBirthEnv(data.getString("birthEnv"));
			knowledge.setPart(data.getString("part"));
			knowledge.setTaste(data.getString("taste"));
			knowledge.setGas(data.getString("gas"));
			knowledge.setChannelTropism(data.getString("channelTRopism"));
			knowledge.setLift(data.getString("lift"));
			knowledge
					.setToxicity(data.getString("toxicity"));
			knowledge.setBeard(data.getString("beard"));
			knowledge.setMake(data.getString("make"));
			knowledge.setHate("hate");
			knowledge
					.setMurder(data.getString("murder"));
			knowledge.setFear(data.getString("fear"));
			knowledge
					.setAgainst(data.getString("against"));
			knowledge.setMainFunction(data.getString("mainFunction"));
			knowledge
					.setSymptom(data.getString("symptom"));
			knowledge
					.setDisease(data.getString("disease"));
			knowledge.setComponent(data.getString("component"));
			knowledge
					.setUsages(data.getString("usages"));
			knowledge.setProcessing(data.getString("processing"));
			knowledge
					.setHarvest(data.getString("harvest"));
			knowledge
					.setGenuine(data.getString("genuine"));
			knowledge.setIdentification(data.getString("identification"));
			knowledge.setUseLevel(data.getString("useLevel"));
			knowledge.setRecording(data.getString("recording"));
			knowledge.setOther(data.getString("other"));
			knowledge.setTaboo(data.getString("taboo"));
			knowledge.setSideEffect(data.getString("sideEffect"));
			knowledge.setDrugLike(data.getString("drugLike"));
			knowledge.setAttention(data.getString("attention"));
			knowledge
					.setFigure(data.getString("figure"));
			km.insert(knowledge);
			return EdatResult.build(0, "success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return EdatResult.build(1, "fail");
		}
	}
	
	@RequestMapping(value = "knowledge/selectKnowledge", method = RequestMethod.POST)
	public EdatResult selectKnowledge(HttpServletRequest request,
									  HttpServletResponse response){
		try {
			ClientUtil.SetCharsetAndHeader(request, response);
			JSONObject data = JSONObject.fromObject(request
					.getParameter("data"));
			String name = data.getString("name");
			Map knowledge=selectByName(name);
			Map map = new HashMap();
			map.put("data",knowledge);
			return EdatResult.build(0, "success",map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return EdatResult.build(1, "fail");
		}
	}


	
	public void recordKnowledge() throws IOException {
		String file_dir = "D:/zydata/中医古籍知识库-示例数据/示例数据/本草便读/知识提取-本草便读.xlsx";
		Workbook book = null;
		try {
			book = ExcelUtil.getExcelWorkbook(file_dir);
			Sheet sheet = book.getSheetAt(0);
			if (sheet == null) {
				System.out.println("数据错误");
				return;
			}
			int firstRowNum = sheet.getFirstRowNum();
			int lastRowNum = sheet.getLastRowNum();

			for (int i = firstRowNum + 2; i < lastRowNum; i++) {
				Row row = sheet.getRow(i);
				if (row != null && ExcelUtil.getCellValue(row.getCell(0)) != "") {
					Knowledge knowledge = new Knowledge();
					knowledge.setBookId((long) 2);
					knowledge.setSectionId((long) 1);
					knowledge.setKnowledgeName(ExcelUtil.getCellValue(row
							.getCell(0)));
					knowledge.setAlias(ExcelUtil.getCellValue(row.getCell(1)));
					knowledge.setKnowledgeType(ExcelUtil.getCellValue(row
							.getCell(2)));
					knowledge
							.setBirthEnv(ExcelUtil.getCellValue(row.getCell(3)));
					knowledge.setPart(ExcelUtil.getCellValue(row.getCell(4)));
					knowledge.setTaste(ExcelUtil.getCellValue(row.getCell(5)));
					knowledge.setGas(ExcelUtil.getCellValue(row.getCell(6)));
					knowledge.setChannelTropism(ExcelUtil.getCellValue(row
							.getCell(7)));
					knowledge.setLift(ExcelUtil.getCellValue(row.getCell(8)));
					knowledge
							.setToxicity(ExcelUtil.getCellValue(row.getCell(9)));
					knowledge.setBeard(ExcelUtil.getCellValue(row.getCell(10)));
					knowledge.setMake(ExcelUtil.getCellValue(row.getCell(11)));
					knowledge.setHate(ExcelUtil.getCellValue(row.getCell(12)));
					knowledge
							.setMurder(ExcelUtil.getCellValue(row.getCell(13)));
					knowledge.setFear(ExcelUtil.getCellValue(row.getCell(13)));
					knowledge
							.setAgainst(ExcelUtil.getCellValue(row.getCell(15)));
					knowledge.setMainFunction(ExcelUtil.getCellValue(row
							.getCell(16)));
					knowledge
							.setSymptom(ExcelUtil.getCellValue(row.getCell(17)));
					knowledge
							.setDisease(ExcelUtil.getCellValue(row.getCell(18)));
					knowledge.setComponent(ExcelUtil.getCellValue(row
							.getCell(19)));
					knowledge
							.setUsages(ExcelUtil.getCellValue(row.getCell(20)));
					knowledge.setProcessing(ExcelUtil.getCellValue(row
							.getCell(21)));
					knowledge
							.setHarvest(ExcelUtil.getCellValue(row.getCell(22)));
					knowledge
							.setGenuine(ExcelUtil.getCellValue(row.getCell(23)));
					knowledge.setIdentification(ExcelUtil.getCellValue(row
							.getCell(24)));
					knowledge.setUseLevel(ExcelUtil.getCellValue(row
							.getCell(25)));
					knowledge.setRecording(ExcelUtil.getCellValue(row
							.getCell(26)));
					knowledge.setOther(ExcelUtil.getCellValue(row.getCell(27)));
					knowledge.setTaboo(ExcelUtil.getCellValue(row.getCell(28)));
					knowledge.setSideEffect(ExcelUtil.getCellValue(row
							.getCell(29)));
					knowledge.setDrugLike(ExcelUtil.getCellValue(row
							.getCell(30)));
					knowledge.setAttention(ExcelUtil.getCellValue(row
							.getCell(31)));
					knowledge
							.setFigure(ExcelUtil.getCellValue(row.getCell(32)));
					km.insert(knowledge);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			book.close();
		}
	}
}
