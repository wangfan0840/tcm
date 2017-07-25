package tcm.com.gistone.controller;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tcm.com.gistone.database.entity.Bookinfo;
import tcm.com.gistone.database.entity.Directory;
import tcm.com.gistone.database.entity.Section;
import tcm.com.gistone.database.entity.SpecialBook;
import tcm.com.gistone.database.mapper.BookinfoMapper;
import tcm.com.gistone.database.mapper.DirectoryMapper;
import tcm.com.gistone.database.mapper.KnowledgeMapper;
import tcm.com.gistone.database.mapper.SectionMapper;
import tcm.com.gistone.database.mapper.SpecialBookMapper;
import tcm.com.gistone.database.mapper.ThemeMapper;
import tcm.com.gistone.database.mapper.WordMapper;
import tcm.com.gistone.database.mapper.WordRelationMapper;
import tcm.com.gistone.database.mapper.WsRelationMapper;
import tcm.com.gistone.util.ClientUtil;
import tcm.com.gistone.util.EdatResult;
import tcm.com.gistone.util.ExcelUtil;
import tcm.com.gistone.util.LogUtil;

@RestController
@RequestMapping
public class BookController  {
	@Autowired
	private SectionMapper sm;
	@Autowired
	private BookinfoMapper bm;
	@Autowired
	private KnowledgeMapper km;
	@Autowired
	private SpecialBookMapper sbm;
	@Autowired
	private WordMapper wm;
	@Autowired
	private DirectoryMapper dm;
	@Autowired
	private WsRelationMapper wsm;
	@Autowired
	private WordRelationMapper wrm;
	@Autowired
	private ThemeMapper tm;
	@Autowired
	BookinfoMapper bim;
	@RequestMapping(value = "/book/recordBook", method = RequestMethod.POST)
	public EdatResult recordBookinfo(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		ClientUtil.SetCharsetAndHeader(request, response);
		JSONObject json = JSONObject.fromObject(request.getParameter("data"));
		long t1 = System.currentTimeMillis();
		System.out.println(t1);
		Bookinfo bookinfo = new Bookinfo();
		SpecialBook sb = new SpecialBook();
		String file_dir = "D:/中医古籍/中医古籍知识库-示例数据/示例数据/本草便读/书目信息.xlsx";
		Workbook book = null;
		long id = 1;
		long specialId = 1;
		try {
			book = ExcelUtil.getExcelWorkbook(file_dir);
			Sheet sheet = book.getSheetAt(0);
			if (sheet != null) {

				/*
				 * Long id =
				 * Long.parseLong(ExcelUtil.getCellValue(sheet.getRow(0)
				 * .getCell(1)));
				 */
				Bookinfo bookinfo1 = bim.selectByPrimaryKey(id);
				if (bookinfo != null) {
					return EdatResult.build(0, "该编号以存在");
				}
				String name = ExcelUtil
						.getCellValue(sheet.getRow(1).getCell(1));
				bookinfo.setBookId(id);
				bookinfo.setBookName(name);
				bookinfo.setApposeName(ExcelUtil.getCellValue(sheet.getRow(2)
						.getCell(1)));
				bookinfo.setSection(ExcelUtil.getCellValue(sheet.getRow(3)
						.getCell(1)));
				bookinfo.setAuthor(ExcelUtil.getCellValue(sheet.getRow(4)
						.getCell(1)));
				bookinfo.setType(ExcelUtil.getCellValue(sheet.getRow(5)
						.getCell(1)));
				bookinfo.setBookVersion(ExcelUtil.getCellValue(sheet.getRow(6)
						.getCell(1)));
				bookinfo.setLocation(ExcelUtil.getCellValue(sheet.getRow(7)
						.getCell(1)));
				bookinfo.setBookAbstract(ExcelUtil.getCellValue(sheet.getRow(8)
						.getCell(1)));
				bookinfo.setFeature(ExcelUtil.getCellValue(sheet.getRow(9)
						.getCell(1)));
				bookinfo.setDynasty(ExcelUtil.getCellValue(sheet.getRow(10)
						.getCell(1)));
				bookinfo.setFinishYear(ExcelUtil.getCellValue(sheet.getRow(11)
						.getCell(1)));
				bookinfo.setCopyState(ExcelUtil.getCellValue(sheet.getRow(12)
						.getCell(1)));
				bookinfo.setNotes(ExcelUtil.getCellValue(sheet.getRow(13)
						.getCell(1)));
				bm.insert(bookinfo);
				sb.setBookId(id);
				sb.setSpecialId(specialId);
				sbm.insert(sb);
				long t3 = System.currentTimeMillis();
				System.out.println(t3 - t1);
				// recordSection(id);
				// getWsRelation(id);
				// recordDirectory();
				// recordImages(id,name);
				// getDirectory(id, name);
				long t2 = new Date().getTime();
				System.out.println("total:" + (t2 - t3));

			}
		} catch (Exception e) {
			e.printStackTrace();
			return EdatResult.build(0, "插入失败");
		} finally {
			book.close();
		}
		return EdatResult.build(0, "插入成功");
	}

	@RequestMapping(value = "book/getBookinfo", method = RequestMethod.POST)
	public EdatResult getBookinfo(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			ClientUtil.SetCharsetAndHeader(request, response);
			JSONObject data = JSONObject.fromObject(request
					.getParameter("data"));

			long bookId = data.getLong("bookId");
			// long bookId=1;
			Bookinfo bookinfo = bim.selectByPrimaryKey(bookId);
			Map map = new HashMap();
			map.put("data", bookinfo);
			return EdatResult.build(1, "success", map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return EdatResult.build(0, "fail");
		}
	}

	@RequestMapping(value = "book/getDirectory", method = RequestMethod.POST)
	public EdatResult getDirectory(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			ClientUtil.SetCharsetAndHeader(request, response);
			JSONObject data = JSONObject.fromObject(request
					.getParameter("data"));
			long bookId = data.getLong("bookId");
			List<Directory> list = dm.selectByBookId(bookId);
			/*
			 * for(Directory tem:list){
			 *
			 * }
			 */
			Map map = new HashMap();
			map.put("datas", list);
			return EdatResult.build(1, "success", map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return EdatResult.build(0, "fail");
		}
	}

	@RequestMapping(value = "book/getContent", method = RequestMethod.POST)
	public EdatResult getContent(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			ClientUtil.SetCharsetAndHeader(request, response);
			JSONObject data = JSONObject.fromObject(request
					.getParameter("data"));
			long bookId = data.getLong("bookId");
			String str = "";
			List<Section> list = sm.selectByBookId(bookId);
			for (Section sec : list) {
				str += sec.getSectionContent();
			}
			Map map = new HashMap();
			map.put("data", str);
			return EdatResult.build(1, "查询成功", map);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return EdatResult.build(0, "查询失败");
		}
	}

	@RequestMapping(value = "book/recordSection", method = RequestMethod.POST)
	public void recordSection(HttpServletRequest request,
							  HttpServletResponse response) throws IOException {
		long id = 1;
		// String file_dir = "D:/zydata/饮膳正要-全文.xlsx";
		String file_dir = "D:/中医古籍/中医古籍知识库-示例数据/示例数据/本草便读/全文数据-1024本草便读.xlsx";
		Workbook book = null;
		try {
			book = ExcelUtil.getExcelWorkbook(file_dir);
			Sheet sheet = book.getSheetAt(0);
			int firstRowNum = sheet.getFirstRowNum();
			int lastRowNum = sheet.getLastRowNum();
			int startPos=0;
			int endPos=0;
			// 循环除了第一行的所有行
			for (int rowNum = firstRowNum + 1; rowNum <= lastRowNum; rowNum++) {
				// 获得当前行
				Row row = sheet.getRow(rowNum);
				if (row == null || row.getCell(0) == null
						|| ExcelUtil.getCellValue(row.getCell(0)) == "") {
					continue;
				} else {
					String content=ExcelUtil
							.getCellValue(row.getCell(2));
					int len=content.length();
					endPos+=len;
					Section sec = new Section();
					// 循环当前行

					sec.setBookId(id);
					sec.setSectionContent(content);
					sec.setSectionType(ExcelUtil.getCellValue(row.getCell(0))
							.split("<")[1].split(">")[0].trim());
					sec.setSectionTitle(ExcelUtil.getCellValue(row.getCell(1))
							.trim());
					sec.setStartPos(startPos);
					sec.setEndPos(endPos);
					sm.insert(sec);
					startPos=endPos+1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			book.close();
		}

	}

	public void recordImages(HttpServletRequest request,
			HttpServletResponse response) throws DocumentException {
		long id = 1;
		List<Section> list = sm.selectByBookId(1101);
		SAXReader reader = new SAXReader();
		File file = new File("src/jming.xml");
		Document document = reader.read(file);
		Element root = document.getRootElement();
		// Element head = root.element("head");
		Element group = root.element("group");
		List<Element> pics = group.elements();
		int listsize = list.size();
		for (int i = 0; i < listsize; i++) {
			Section sec = list.get(i);
			String[] tem = sec.getSectionTitle().split("/");
			String url = "";
			Set set = new HashSet();
			for (Element ele : pics) {
				Element keys = ele.element("COMMENT");
				if (keys == null) {
					continue;
				}
				String kstr = keys.getText();
				if (kstr.indexOf(tem[tem.length - 1]) != -1) {
					Element Url = ele.element("file_URL");
					url = Url.getTextTrim();
					set.add(url);
				}
			}
			int num = set.size();
			if (num == 1) {
				System.out.println("1:" + url);
				sec.setImageUrl(url);
				sm.updateByPrimaryKey(sec);
			} else if (num > 1) {
				System.out.println(num);
				int start = i - num + 1;
				int end = i + num - 1;
				if (i < num) {
					start = 0;
				} else if (i + num > listsize) {
					end = listsize;
				}
				for (int j = start; j < end; j++) {
					String str = "";
					for (int m = 0; m < num; m++) {
						Section sec1 = list.get(j + m);
						String[] tem1 = sec1.getSectionTitle().split("/");
						if (num - m > 1) {
							str += tem1[tem1.length - 1] + " ";
						} else {
							str += tem1[tem1.length - 1];
						}
					}
					Set set1 = new HashSet();
					for (Element ele : pics) {
						Element keys1 = ele.element("COMMENT");
						if (keys1 == null) {
							continue;
						}
						String kstr1 = keys1.getText();
						if (kstr1.indexOf(str) != -1) {
							Element Url1 = ele.element("file_URL");
							url = Url1.getTextTrim();
							set1.add(url);
							continue;
						}
					}
					if (set1.size() > 0) {
						System.out.println("2::" + url);
						sec.setImageUrl(url);
						sm.updateByPrimaryKey(sec);
					}
				}
			}

		}
	}

	public void recordDirectory() throws IOException, DocumentException {
		long t1 = System.currentTimeMillis();
		Long id = (long) 1;
		List<Section> list = sm.selectByBookId(id);
		List<String> list1 = new ArrayList<String>();
		List<String> result = new ArrayList<String>();
		for (Section sec : list) {
			String title = sec.getSectionTitle();
			Long secId = sec.getSectionId();
			String[] arr = title.split("\\\\");
			int len = arr.length;
			if (len == 2) {
				String str = arr[1].trim() + "," + arr[1].trim() + "," + 1
						+ "," + secId;
				result.add(str);
			} else if (len == 3) {
				String str = arr[1].trim() + "," + arr[1].trim() + "," + 1;
				String str1 = arr[1].trim() + "," + arr[2].trim() + "," + 2
						+ "," + secId;
				list1.add(str);
				result.add(str1);
			} else if (len == 4) {
				String str = arr[1].trim() + "," + arr[1].trim() + "," + 1;
				String str1 = arr[1].trim() + "," + arr[2].trim() + "," + 2;
				String str2 = arr[2].trim() + "," + arr[3].trim() + "," + 3
						+ "," + secId;

				list1.add(str);
				list1.add(str1);
				result.add(str2);
			} else if (len == 5) {
				String str = arr[1].trim() + "," + arr[1].trim() + "," + 1;
				String str1 = arr[1].trim() + "," + arr[2].trim() + "," + 2;
				String str2 = arr[2].trim() + "," + arr[3].trim() + "," + 3;
				String str3 = arr[3].trim() + "," + arr[4].trim() + "," + 4
						+ "," + secId;
				list1.add(str);
				list1.add(str1);
				list1.add(str2);
				result.add(str3);
			} else if (len == 6) {
				String str = arr[1].trim() + "," + arr[1].trim() + "," + 1;
				String str1 = arr[1].trim() + "," + arr[2].trim() + "," + 2;
				String str2 = arr[2].trim() + "," + arr[3].trim() + "," + 3;
				String str3 = arr[3].trim() + "," + arr[4].trim() + "," + 4;
				String str4 = arr[4].trim() + "," + arr[5].trim() + "," + 5
						+ "," + secId;

				list1.add(str);
				list1.add(str1);
				list1.add(str2);
				list1.add(str3);
				result.add(str4);
			} else if (len == 7) {
				String str = arr[1].trim() + "," + arr[1].trim() + "," + 1;
				String str1 = arr[1].trim() + "," + arr[2].trim() + "," + 2;
				String str2 = arr[2].trim() + "," + arr[3].trim() + "," + 3;
				String str3 = arr[3].trim() + "," + arr[4].trim() + "," + 4;
				String str4 = arr[4].trim() + "," + arr[5].trim() + "," + 5;
				String str5 = arr[5].trim() + "," + arr[6].trim() + "," + 6
						+ "," + secId;
				list1.add(str);
				list1.add(str1);
				list1.add(str2);
				list1.add(str3);
				list1.add(str4);
				result.add(str5);
			} else {
				System.out.println(arr.length);
			}
		}
		List<String> list2 = new ArrayList<>();
		for (String tem : list1) {
			if (!list2.contains(tem.trim())) {
				list2.add(tem.trim());
			}
		}
		for (String temp : result) {
			String[] arry = temp.split(",");
			Directory dir = new Directory();
			dir.setBookId(id);
			dir.setParentName(arry[0]);
			dir.setName(arry[1]);
			dir.setLevel(Integer.parseInt(arry[2]));
			dir.setSectionId(Long.parseLong(arry[3]));
			dm.insert(dir);
		}
		for (String temp : list2) {
			String[] arry = temp.split(",");
			Directory dir = new Directory();
			dir.setBookId(id);
			dir.setParentName(arry[0]);
			dir.setName(arry[1]);
			dir.setLevel(Integer.parseInt(arry[2]));
			dm.insert(dir);
		}
		List<Directory> listDir = dm.selectByBookId(id);
		for (Directory tem : listDir) {
			List<Long> li = dm.selectByName(tem.getParentName(), id);
			tem.setParentId(li.get(0));
			dm.updateByPrimaryKeySelective(tem);
		}
		long t2 = System.currentTimeMillis();
		System.out.println(t2 - t1);
	}



}
