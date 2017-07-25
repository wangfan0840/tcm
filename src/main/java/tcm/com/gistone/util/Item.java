package tcm.com.gistone.util;

import java.util.List;

public class Item
{

    private long  id ;
     /**
     * 节点的名字
     */
     private String text ;
//
//     /**
//     * 节点的类型："item":文件  "folder":目录
//     */
//     private String type ;

     private List<Item> nodes;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Item> getNodes() {
        return nodes;
    }

    public void setNodes(List<Item> nodes) {
        this.nodes = nodes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}