package tcm.com.gistone.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能: 数据返回对象
 */
public class TreeRespVO
{
     private List<Item> data = new ArrayList<Item>();
     public List<Item> getData()
    {
          return data ;
    }
     public void setData(List<Item> data )
    {
          this .data = data;
    }




     
     
}