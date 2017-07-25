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
import tcm.com.gistone.database.mapper.SpecialMapper;
import tcm.com.gistone.util.ClientUtil;
import tcm.com.gistone.util.EdatResult;

@RestController
@RequestMapping
public class SpecialBookController {
	@Autowired
	private SpecialMapper sm;
	@Autowired
    private GetBySqlMapper gm;
    @RequestMapping(value = "knowledge/getBookBySpecial", method = RequestMethod.GET)
    public EdatResult getAllSpecial(HttpServletRequest request,
                                    HttpServletResponse response){
        try {
            ClientUtil.SetCharsetAndHeader(request, response);
            JSONObject data = JSONObject.fromObject(request
                    .getParameter("data"));
            long specialId=data.getLong("specialId");
            String sql="select book_id from tb_special_book where special_id = "+specialId;
            List<Map> result = new ArrayList<>();
            result = gm.findRecords(sql);
            Map map = new HashMap();
            map.put("data", result);
            return EdatResult.build(0, "success", map);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return EdatResult.build(1, "fail");
        }
    }
}
