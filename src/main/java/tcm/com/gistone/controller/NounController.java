package tcm.com.gistone.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tcm.com.gistone.database.mapper.NounMapper;
import tcm.com.gistone.util.ClientUtil;
import tcm.com.gistone.util.EdatResult;

@RestController
@RequestMapping
public class NounController {
    @Autowired
    private NounMapper nm;
    @RequestMapping(value="noun/recordNoun",method = RequestMethod.POST)
    public EdatResult recordNoun(HttpServletRequest request, HttpServletResponse response){
        try {
            ClientUtil.SetCharsetAndHeader(request, response);
            JSONObject data = JSONObject.fromObject(request
                    .getParameter("data"));

            Map map = new HashMap();

            return EdatResult.build(0, "success", map);

        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return EdatResult.build(1, "fail");
        }
    }
    @RequestMapping(value="noun/getNoun",method = RequestMethod.POST)
    public EdatResult selectNoun(HttpServletRequest request, HttpServletResponse response){
        try {
            ClientUtil.SetCharsetAndHeader(request, response);
            JSONObject data = JSONObject.fromObject(request
                    .getParameter("data"));

            Map map = new HashMap();

            return EdatResult.build(0, "success", map);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return EdatResult.build(1, "fail");
        }
    }
	
}
