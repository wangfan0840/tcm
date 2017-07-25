package tcm.com.gistone.util;


import java.util.List;

public class Item {

    private long id;
    /**
     * 节点的名字
     */
    private String text;


    private int sectionId;

    private int number;
//
//     /**
//     * 节点的类型："item":文件  "folder":目录
//     */
//     private String type ;

    private  boolean leaf;

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

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}