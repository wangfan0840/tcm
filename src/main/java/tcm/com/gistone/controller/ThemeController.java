package tcm.com.gistone.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tcm.com.gistone.database.entity.Theme;
import tcm.com.gistone.database.mapper.ThemeMapper;
import tcm.com.gistone.util.ClientUtil;
import tcm.com.gistone.util.EdatResult;

@RestController
@RequestMapping
public class ThemeController {
    @Autowired
    ThemeMapper tm;
    @ResponseBody
    @RequestMapping(value = "knowledge/getAllTheme", method = RequestMethod.GET)
    public EdatResult getTheme(HttpServletRequest request,
                               HttpServletResponse response){
        try {
            ClientUtil.SetCharsetAndHeader(request, response);
      /*      JSONObject data = JSONObject.fromObject(request
                    .getParameter("data"));*/
            List<Theme> themes = tm.selectAll();
            return EdatResult.build(0, "success", themes);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return EdatResult.build(1001, "fail");
        }
    }
    @RequestMapping(value = "knowledge/addTheme", method = RequestMethod.POST)
    public EdatResult updateTheme(HttpServletRequest request,
                               HttpServletResponse response){
        try {
            ClientUtil.SetCharsetAndHeader(request, response);
            JSONObject data = JSONObject.fromObject(request
                    .getParameter("data"));
            List<Theme> themes = tm.selectAll();
            Map map = new HashMap();
            map.put("data", themes);
            return EdatResult.build(0, "success", map);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return EdatResult.build(1, "fail");
        }
    }

}
