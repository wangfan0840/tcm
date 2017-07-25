package tcm.com.gistone.controller;

import java.util.Date;
import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tcm.com.gistone.database.entity.Section;
import tcm.com.gistone.database.entity.Theme;
import tcm.com.gistone.database.entity.Word;
import tcm.com.gistone.database.entity.WsRelation;
import tcm.com.gistone.database.mapper.BookinfoMapper;
import tcm.com.gistone.database.mapper.DirectoryMapper;
import tcm.com.gistone.database.mapper.KnowledgeMapper;
import tcm.com.gistone.database.mapper.SectionMapper;
import tcm.com.gistone.database.mapper.SpecialBookMapper;
import tcm.com.gistone.database.mapper.ThemeMapper;
import tcm.com.gistone.database.mapper.WordMapper;
import tcm.com.gistone.database.mapper.WordRelationMapper;
import tcm.com.gistone.database.mapper.WsRelationMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WsController {
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

	public void getWsRelationWithhBook() {
		Long t1 = new Date().getTime();
		List<Theme> themes = tm.selectAll();
		Long id = (long) 2;
		List<Section> sections = sm.selectByBookId(id);
		for (Theme theme : themes) {
			String type = theme.getThemeShort();
			List<Word> words = wm.selectByType(type);
			System.out.println(sections.size() + "/" + words.size());
			for (Section section : sections) {
				String text = section.getSectionContent();
				Long tid = section.getSectionId();
				for (Word word : words) {
					String k = word.getWord();
					String alias=word.getAlias();
					Long kid = word.getWordId();
					int f = 0;
					f = text.indexOf(k);
					int num = 0;
					while (f != -1) {
						num++;
						f = text.indexOf(k, f + 1);
					}
					String[] arry=alias.split(",");
					for (int i=0;i<arry.length;i++){
						int flag=0;
						String tem=arry[i];
						flag=text.indexOf(tem);
						while(flag!=-1){
							num++;
							flag=text.indexOf(tem,flag+1);
						}
					}
					if (num > 0) {
						WsRelation wsRelation = new WsRelation();
						wsRelation.setWordId(kid);
						wsRelation.setSectionId(tid);
						wsRelation.setWordNum(num);
						wsRelation.setBookId(id);
						wsRelation.setWordType(type);
						wsm.insert(wsRelation);
					}
				}
			}
		}
		Long t2 = new Date().getTime();
		System.out.println(t2 - t1);
	}
}
