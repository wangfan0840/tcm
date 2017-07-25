package tcm.com.gistone.controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tcm.com.gistone.database.config.GetBySqlMapper;
import tcm.com.gistone.database.entity.User;
import tcm.com.gistone.database.mapper.UserMapper;
import tcm.com.gistone.util.ClientUtil;
import tcm.com.gistone.util.EdatResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 用户控制类
 * 
 * @author WangShanxi
 * @date 2017年6月12日
 * @version v0.0.1
 */
@RestController
@RequestMapping
public class UserController {
	@Autowired
	UserMapper userMapper;
	@Autowired
	GetBySqlMapper gm;
	@ResponseBody
	@RequestMapping(value = "user/createUser", method = RequestMethod.POST)
	public EdatResult createUser(HttpServletRequest request,
								   HttpServletResponse response) {
		try {
			ClientUtil.SetCharsetAndHeader(request, response);
			System.out.println(request
					.getParameter("data"));
			JSONObject data = JSONObject.fromObject(request
					.getParameter("data"));
		/*	String regEx = "^[a-zA-Z]+[a-zA-Z0-9_]{5,14}$";
			if(RegUtil.CheckParameter(userName,"String",regEx,falsse)){
				return EdatResult.build(1, "账号格式不正确");
			}*/
			String loginName = data.getString("loginName");
			if(null==loginName||loginName.length()<6){
				return EdatResult.build(1,"请输入正确格式的登录名");
			}
			String sql = "select * from tb_user where login_name = " + loginName;
			List<Map> result = new ArrayList<>();
			result = gm.findRecords(sql);
			if(result.size()>0){
				return EdatResult.build(1,"登录名重复");
			}

			String userName = data.getString("userName");
			//	String password = data.getString("password");
			String tel = data.getString("tel");
			String mail = data.getString("mail");
			Date d = new Date();
			System.out.println(d);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateNowStr = sdf.format(d);
			if(null == userName || userName == ""){
				return EdatResult.build(1,"请输入正确格式的用户名");
			}
			int level = data.getInt("level");
			User user = new User();
			user.setLoginName(loginName);
			user.setUserType(level);
			user.setUserName(userName);
			user.setUserPwd("123456");
			user.setTel(tel);
			user.setEmail(mail);
			user.setCreateTime(dateNowStr);
			user.setStatus(1);
			userMapper.insert(user);
			return EdatResult.ok();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return EdatResult.build(1, "fail");
		}
	}

	@RequestMapping(value = "user/updateUser", method = RequestMethod.POST)
	public EdatResult updateteUser(HttpServletRequest request,
								 HttpServletResponse response) {
		try {
			ClientUtil.SetCharsetAndHeader(request, response);
			JSONObject data = JSONObject.fromObject(request
					.getParameter("data"));
			//String id =  UUID.randomUUID().toString();
			String loginName = data.getString("loginName");
			long id=data.getLong("id");
			int per=data.getInt("per");
			String userName = data.getString("userName");
		//	String password = data.getString("password");
			String tel = data.getString("tel");
			String email = data.getString("email");
			User user = new User();
			user.setId(id);
			user.setLoginName(loginName);
			user.setUserType(per);
			user.setUserName(userName);
			user.setUserPwd("123456");
			user.setTel(tel);
			user.setEmail(email);
			//user.setStatus(1);
			userMapper.updateByPrimaryKey(user);
			return EdatResult.build(0, "success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return EdatResult.build(1, "fail");
		}
		
	}
	@ResponseBody
	@RequestMapping("user/getAllUser")
	public Map getAllUser(HttpServletRequest request,
									  HttpServletResponse response) {
		try {
			ClientUtil.SetCharsetAndHeader(request, response);
			int pageNumber=Integer.parseInt(request
					.getParameter("pageNumber"));
			int pageSize=Integer.parseInt(request
					.getParameter("pageSize"));
			String keyWord = request.getParameter("keyWord");
		/*	JSONObject data = JSONObject.fromObject(request
					.getParameter("data"));*/
			String sql = "select id,login_name, user_name,tel,email,user_type,create_time,permi_time from tb_user where status != 0 and user_name like '%"+keyWord+"%' limit "+pageNumber+","+pageSize ;
			List<Map> result = new ArrayList<>();
			result = gm.findRecords(sql);
			String sql1 = "select count(*) as total from tb_user where status != 0";
			int total= gm.findrows(sql1);
			//List<Map> list = new ArrayList<>();
			Map map = new HashMap();
			map.put("total",total);
			map.put("rows",result);
			map.put("page",pageNumber/pageSize);
			return map;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
	@RequestMapping(value = "user/getUserByType", method = RequestMethod.POST)
	public EdatResult getUserByType(HttpServletRequest request,
								 HttpServletResponse response) {
		try {
			ClientUtil.SetCharsetAndHeader(request, response);
			JSONObject data = JSONObject.fromObject(request
					.getParameter("data"));
			int type = data.getInt("type");
			String sql = "select user_name from tb_user where status!=0 and user_type = "+ type;
			List<Map> result = new ArrayList<>();
			result = gm.findRecords(sql);
			return EdatResult.build(0, "success",result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return EdatResult.build(1, "fail");
		}

	}

	@RequestMapping(value = "user/deleteUser", method = RequestMethod.POST)
	public EdatResult deleteUser(HttpServletRequest request,
								 HttpServletResponse response) {
		try {
			ClientUtil.SetCharsetAndHeader(request, response);
			JSONObject data = JSONObject.fromObject(request
					.getParameter("data"));
			JSONArray array=data.getJSONArray("userId");
			for(int i=0;i<array.size();i++){
				long userId = array.getLong(i);
				String sql="update tb_user set status = 0 where id = "+ userId;
				gm.update(sql);
			}
			return EdatResult.ok();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return EdatResult.build(1, "fail");
		}

	}
	
	@RequestMapping(value = "user/recoverUser", method = RequestMethod.POST)
	public EdatResult recoverUser(HttpServletRequest request,
								 HttpServletResponse response) {
		try {
			ClientUtil.SetCharsetAndHeader(request, response);
			JSONObject data = JSONObject.fromObject(request
					.getParameter("data"));
			JSONArray userIds=data.getJSONArray("userIds");
			for(int i=0;i<userIds.size();i++){
				long id =  userIds.getLong(i);
				String sql="update tb_user set status = 1 where user_id = "+ id;
				gm.update(sql);
			}
			return EdatResult.ok();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return EdatResult.build(1, "fail");
		}

	}
	// @Autowired
	// public TUserMapper tUserMapper;

	/*
	 * @RequestMapping("/user/login") public String login() { return "welcome";
	 * }
	 */

	/**
	 * 用户登录
	 * 
	 * @author WangShanxi
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @return 返回响应结果对象
	 */
	// @RequestMapping("/user/login")
	// @ResponseBody
	// public EdatResult login(@RequestBody Map<String, Object> requestDate,
	// HttpServletRequest request, HttpServletResponse response) {
	// try {
	// // 设置跨域
	// ClientUtil.SetCharsetAndHeader(request, response);
	// Map<String, Object> data = (Map) requestDate.get("data");
	// //定义账号的正则表达式
	// String regEx = "^[a-zA-Z]+[a-zA-Z0-9_]{5,14}$";
	// //获取账号参数
	// Object param=data.get("userAccount");
	// //进行参数判断
	// if(!RegUtil.CheckParameter(param, "String", regEx, false)){
	// LogUtil.getLogger().error("UserController  账号不存在!");
	// return EdatResult.build(1003, "账号不存在!");
	// }
	// //用户账号
	// String userAccount=param.toString();
	// //获取密码参数
	// param=data.get("userPwd");
	// //进行参数判断
	// if(!RegUtil.CheckParameter(param, "String", null, false)){
	// LogUtil.getLogger().error("UserController  密码不正确!");
	// return EdatResult.build(1003, "密码不正确!");
	// }
	// //密码
	// String userPwd=param.toString();
	// //查看当前账号是否存在
	// Integer isExist=tUserMapper.checkUserId(userAccount);
	// //判断如果存在
	// if(isExist>0){
	// //添加条件
	// Map map=new HashMap();
	// map.put("userAccount", userAccount);
	// //进行MD5加密
	// userPwd = Tool.md5(userPwd);
	// map.put("userPwd", userPwd);
	// //查询所有的用户基本信息
	// Map userMap=tUserMapper.login(map);
	// //如果用户账号和密码匹配
	// if(userMap!=null){
	// //将用户的一些基本信息 放到session
	// HttpSession session = request.getSession();
	// session.setAttribute("user", userMap);
	// //添加Log
	// LogUtil.getLogger().info("UserController  登录成功！");
	// //返回结果
	// return EdatResult.ok(userMap);
	// }else{
	// throw new SQLException("用户和密码不匹配!");
	// }
	// }else{
	// throw new SQLException("用户名不存在");
	// }
	// }catch(SQLException e){
	// LogUtil.getLogger().error("UserController  "+e.getMessage(),e);
	// return EdatResult.build(1000,e.getMessage());
	// } catch (Exception e) {
	// LogUtil.getLogger().error("UserController 用户登录异常！",e);
	// return EdatResult.build(1001,"用户登录异常！");
	// }
	// }

	// /**
	// * 修改用户密码
	// */
	// @RequestMapping("/user/updatePassword")
	// public EdatResult updatePassword(@RequestBody Map<String, Object>
	// requestDate,
	// HttpServletRequest request , HttpServletResponse response) throws
	// Exception {
	// try {
	// // 设置跨域
	// ClientUtil.SetCharsetAndHeader(request, response);
	// Map<String, Object> data = (Map) requestDate.get("data");
	// //定义账号的正则表达式
	// String regEx = "^[a-zA-Z]+[a-zA-Z0-9_]{5,14}$";
	// //获取账号参数
	// Object param=data.get("userAccount");
	// //进行参数判断
	// if(!RegUtil.CheckParameter(param, "String", regEx, false)){
	// LogUtil.getLogger().error("UserController-updatePassword  账号为空或出现非法字符!");
	// return EdatResult.build(1003, "账号为空或出现非法字符!");
	// }
	// //用户账号
	// String userAccount=param.toString();
	//
	// //获取密码参数
	// param=data.get("oldPassword");
	// //进行参数判断
	// if(!RegUtil.CheckParameter(param, "String", null, false)){
	// LogUtil.getLogger().error("UserController-updatePassword  旧密码为空或出现非法字符!");
	// return EdatResult.build(1003, "旧密码为空或出现非法字符!");
	// }
	// //用户输入的旧密码
	// String oldPassword= param.toString();
	//
	// param = data.get("newPassword");
	// //进行参数判断
	// if(!RegUtil.CheckParameter(param, "String", null, false)){
	// LogUtil.getLogger().error("UserController-updatePassword  新密码为空或出现非法字符!");
	// return EdatResult.build(1003, "新密码为空或出现非法字符!");
	// }
	// //用户输入的新密码
	// String newPassword = param.toString();
	// //查看当前账号是否存在
	// Integer isExist=tUserMapper.checkUserId(userAccount);
	// //判断如果存在
	// if(isExist>0){
	// //添加条件
	// Map map=new HashMap();
	// map.put("userAccount", userAccount);
	// //进行MD5加密
	// oldPassword = Tool.md5(oldPassword);
	// map.put("userPwd", oldPassword);
	// //查询所有的用户基本信息
	// Map userMap=tUserMapper.login(map);
	// //如果用户账号和密码匹配
	// if(userMap!=null){
	// //新密码加密
	// newPassword = Tool.md5(newPassword);
	// TUser tUserAccount = new TUser();
	// tUserAccount.setUserId(Integer.valueOf(userMap.get("userId").toString()));
	// tUserAccount.setUserPwd(newPassword);
	// //修改密码
	// int result = tUserMapper.updateByPrimaryKeySelective(tUserAccount);
	// if (result>0) {
	// LogUtil.getLogger().info("UserController  密码修改成功！");
	// return EdatResult.ok("密码修改成功");
	// }else {
	// LogUtil.getLogger().info("修改密码失败！");
	// throw new SQLException("修改密码失败");
	// }
	// }else{
	// throw new SQLException("用户和密码不匹配!");
	// }
	// }else{
	// throw new SQLException("用户名不存在");
	// }
	// }catch(SQLException e){
	// LogUtil.getLogger().error("UserController   "+e.getMessage(),e);
	// return EdatResult.build(1000,e.getMessage());
	// } catch (Exception e) {
	// LogUtil.getLogger().error("UserController-updatePassword 系统异常！",e);
	// return EdatResult.build(1001,"系统异常！");
	// }
	// }
	//
	// /**
	// * 取session
	// * @param request
	// * @param response
	// * @throws Exception
	// */
	// @RequestMapping("/user/get_sessionInfo")
	// public EdatResult get_sessionInfo(HttpServletRequest
	// request,HttpServletResponse response) throws Exception{
	// try{
	// // 设置跨域
	// ClientUtil.SetCharsetAndHeader(request, response);
	// HttpSession session = request.getSession();
	// //验证session不为空
	// if(session.getAttribute("user")!=null){
	// //取出用户信息
	// Map<String,String> userInfo = (Map)session.getAttribute("user");
	// LogUtil.getLogger().info("UserController  获取Session成功！");
	// //将信息添加到结果集
	// return EdatResult.ok(userInfo);
	// }else{
	// LogUtil.getLogger().error("UserController  没有Session信息!");
	// return EdatResult.build(1002,"没有Session信息!");
	// }
	// }catch(Exception e){
	// LogUtil.getLogger().error("UserController 获取Session异常！",e);
	// return EdatResult.build(1001,"获取Session异常!");
	// }
	//
	// }
	//
	//
	// /**
	// * 销毁session
	// * @param request
	// * @param response
	// * @throws Exception
	// */
	// @RequestMapping("/user/loginOut")
	// public EdatResult loginOut(HttpServletRequest request,HttpServletResponse
	// response) throws Exception{
	// HttpSession session = request.getSession();
	// try{
	// // 设置跨域
	// ClientUtil.SetCharsetAndHeader(request, response);
	// //销毁Session中的信息
	// session.invalidate();
	// LogUtil.getLogger().info("UserController 用户退出成功！");
	// return EdatResult.ok(1);
	// }catch (Exception e){
	// LogUtil.getLogger().error("UserController 用户退出异常！",e);
	// return EdatResult.build(1001,"UserController 用户退出异常！");
	// }
	// }
}
