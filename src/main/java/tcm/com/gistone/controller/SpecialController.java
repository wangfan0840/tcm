package tcm.com.gistone.controller;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tcm.com.gistone.database.config.GetBySqlMapper;
import tcm.com.gistone.database.mapper.SpecialMapper;
import tcm.com.gistone.util.ClientUtil;
import tcm.com.gistone.util.EdatResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by qiang on 2017/7/18.
 */
@RestController
@RequestMapping
public class SpecialController {
    @Autowired
    SpecialMapper sm;
    @Autowired
    private GetBySqlMapper gm;
    @RequestMapping(value = "knowledge/getAllSpecial", method = RequestMethod.GET)
    public EdatResult getAllSpecial(HttpServletRequest request,
                                    HttpServletResponse response){
        try {
            ClientUtil.SetCharsetAndHeader(request, response);
            JSONObject data = JSONObject.fromObject(request
                    .getParameter("data"));
            List<Map> specials = selectAllSpecial();
            Map map = new HashMap();
            map.put("data", specials);
            return EdatResult.build(0, "success", map);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return EdatResult.build(1, "fail");
        }
    }
    private List<Map> selectAllSpecial(){
        String sql="select * from tb_special;";
        List<Map> result = new ArrayList<>();
        result = gm.findRecords(sql);
        return result;
    }
}
