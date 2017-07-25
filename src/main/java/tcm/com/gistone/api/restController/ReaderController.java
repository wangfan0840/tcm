package tcm.com.gistone.api.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tcm.com.gistone.database.config.GetBySqlMapper;
import tcm.com.gistone.util.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wangfan on 2017/7/22.
 */
@RestController("apiReaderController")
@RequestMapping("api/reader")
public class ReaderController {


    @Autowired
    private GetBySqlMapper getBySqlMapper;


    /**
     * 获取item节点的所有后节点信息
     *
     * @param bookId
     * @param item
     * @return
     */
    private Item childNode(int bookId, Item item) {
        List<Item> nodes = nodes(bookId, item);

        if (nodes.size() > 0) {


            for (Item node : nodes) {

                childNode(bookId, node);

            }
            item.setNodes(nodes);

        }

        return item;
    }

    /**
     * 获取当前节点的下一级节点信息
     *
     * @param bookId
     * @param item
     * @return
     */
    private List<Item> nodes(int bookId, Item item) {
        List<Item> nodes = new ArrayList<>();
        long id = item.getId();
        String sql = "select * from tb_directory where book_id =" + bookId + " and parent_id=" + id;
        List<Map> childList = this.getBySqlMapper.findRecords(sql);
        if (childList.size() > 0) {

            for (Map map : childList) {
                System.out.println(map.toString());
                Item child = new Item();
                child.setId((long) map.get("id"));
                child.setText(map.get("name").toString());
                nodes.add(child);
            }

        }
        return nodes;
    }


    /**
     * 获取bootstrap treeview 的data信息
     *
     * @param bookId
     * @return
     */
    @RequestMapping(value = "getTree", method = RequestMethod.GET)
    public List<Item> getTree(@RequestParam Integer bookId) {
        List<Item> voItemList = new ArrayList<Item>();
        Item root = new Item();
        String sql = "select book_name from tb_bookinfo where book_id =" + bookId;
        String bookName = this.getBySqlMapper.findRecords(sql).get(0).get("book_name").toString();
        root.setText(bookName);
        root.setId(0);
        childNode(1, root);
        voItemList.add(root);
        return voItemList;
    }

    /**
     * 获取bookId对应的book书籍content内容
     *
     * @param bookId
     * @return
     */

    @RequestMapping(value = "getContent", method = RequestMethod.GET)
    public String getContent(@RequestParam Integer bookId) {

        String content = "";
        String sql = "select * from tb_section where book_id =" + bookId;
        List<Map> maps = this.getBySqlMapper.findRecords(sql);

        for (int i = 0; i < maps.size(); i++) {

            Map map = maps.get(i);
            String section = map.get("section_content").toString();
            Pattern p = Pattern.compile("\\s{21}");
            Matcher m = p.matcher(section);
            section = m.replaceAll("");

            section =section.replace("      ","<br/>&emsp;&emsp;");
            section =section.replaceAll("\r\n","<br/>");
            section =section.replaceAll("\r","<br/>");

            if(i==maps.size()-1){
                content = content +"&emsp;&emsp;" +section;
            }else {

                content = content +"&emsp;&emsp;" +section + "<br/>";
            }
        }
        return content;
    }

    @RequestMapping(value = "getBookInfo", method = RequestMethod.GET)
    public Map getBookInfo(@RequestParam Integer bookId) {
        String sql = "select * from tb_bookinfo where book_id =" + bookId;
        return this.getBySqlMapper.findRecords(sql).get(0);
    }


}
