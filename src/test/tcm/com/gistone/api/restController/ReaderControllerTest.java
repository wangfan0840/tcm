package tcm.com.gistone.api.restController;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


/**
 * Created by wangfan on 2017/7/22.
 */

@RunWith(SpringRunner.class)
@SpringBootTest

public class ReaderControllerTest {
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private  ReaderController readerController;

    MockMvc mockMvc;
    @Before
    public void setup(){
       /*
        * MockMvcBuilders使用构建MockMvc对象.
        */
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();//建议使用这种
    }
    @Test
    public void getTree() throws Exception {

        //发送请求
//        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.get("/tcm/api/reader/getDirectory")
//                .accept(MediaType.APPLICATION_JSON).param("bookId","1"));

                ResultActions resultActions = this.mockMvc
                        .perform(MockMvcRequestBuilders.get("/api/reader/getTree").param("bookId","1"));
        MvcResult mvcResult = resultActions.andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====客户端获得反馈数据:\n" + result);
    }

}