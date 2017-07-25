package tcm.com.gistone.controller;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tcm.com.gistone.database.config.GetBySqlMapper;
import tcm.com.gistone.database.entity.UserBook;
import tcm.com.gistone.database.mapper.UserBookMapper;
import tcm.com.gistone.util.ClientUtil;
import tcm.com.gistone.util.EdatResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by qiang on 2017/7/19.
 */
@RestController
@RequestMapping
public class UserBookController {
    @Autowired
    UserBookMapper ubm;
    @Autowired
    GetBySqlMapper gm;

    @RequestMapping(value = "userBook/insertUserBook", method = RequestMethod.POST)
    public EdatResult insertUserBook(HttpServletRequest request,
                                    HttpServletResponse response) {
        try {
            ClientUtil.SetCharsetAndHeader(request, response);
            JSONObject data = JSONObject.fromObject(request
                    .getParameter("data"));
            long userId = data.getLong("userId");
            long bookId = data.getLong("bookId");
            String sql = "select * from tb_user_book where user_id = "+userId+" and book_id = "+bookId ;
            List<Map> result = new ArrayList<>();
            result = gm.findRecords(sql);
            if(result.size()>0){
                return EdatResult.build(1, "已有相同记录");
            }
            UserBook  userBook= new UserBook();
            userBook.setBookId(bookId);
            userBook.setUserId(userId);
            ubm.insert(userBook);
            return EdatResult.ok();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return EdatResult.build(1, "fail");
        }
    }

    @RequestMapping(value = "userBook/getBookByUser", method = RequestMethod.POST)
    public EdatResult getBookByUser(HttpServletRequest request,
                                 HttpServletResponse response) {
        try {
            ClientUtil.SetCharsetAndHeader(request, response);
            JSONObject data = JSONObject.fromObject(request
                    .getParameter("data"));
            long id = data.getLong("userId");
            String sql = "select * from tb_user_book where user_id = "+id;
            List<Map> result = new ArrayList<>();
            result = gm.findRecords(sql);
            return EdatResult.build(0, "success", result);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return EdatResult.build(1, "fail");
        }

    }
    @RequestMapping(value = "userBook/getUsersByBook", method = RequestMethod.POST)
    public EdatResult getUsersByBook(HttpServletRequest request,
                                    HttpServletResponse response) {
        try {
            ClientUtil.SetCharsetAndHeader(request, response);
            JSONObject data = JSONObject.fromObject(request
                    .getParameter("data"));
            long id = data.getLong("bookId");
            String sql = "select * from tb_user_book where book_id = "+id;
            List<Map> result = new ArrayList<>();
            result = gm.findRecords(sql);
            return EdatResult.build(0, "success", result);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return EdatResult.build(1, "fail");
        }
    }

    @RequestMapping(value = "userBook/deleteUserBook", method = RequestMethod.POST)
    public EdatResult deleteUserBook(HttpServletRequest request,
                                     HttpServletResponse response) {
        try {
            ClientUtil.SetCharsetAndHeader(request, response);
            JSONObject data = JSONObject.fromObject(request
                    .getParameter("data"));
            long id = data.getLong("id");
            String sql = " delete from tb_user_book where id = "+id;
            gm.delete(sql);
            return EdatResult.build(0, "success");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return EdatResult.build(1, "fail");
        }

    }
}
