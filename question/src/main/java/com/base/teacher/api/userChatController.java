package com.base.teacher.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.base.teacher.config.common.ApiResult;
import com.base.teacher.config.common.QueryCondition;
import com.base.teacher.config.common.QueryCondition.Criteria;
import com.base.teacher.config.common.base.BaseController;
import com.base.teacher.config.utils.IpUtils;
import com.base.teacher.config.utils.StringUtils;
import com.base.teacher.questionnaire.entity.Questionnaire;
import com.base.teacher.questionnaire.entity.QuestionnaireIp;
import com.base.teacher.questionnaire.entity.User;
import com.base.teacher.questionnaire.service.QuestionnaireIpService;
import com.base.teacher.questionnaire.service.QuestionnaireService;
import com.base.teacher.questionnaire.service.UserService;

@RestController
@RequestMapping(value = "/api/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class userChatController extends BaseController{

	@Autowired
	private UserService userService;
	@Autowired
	private QuestionnaireService questionnaireService;
	@Autowired
	private QuestionnaireIpService questionnaireIpService;
	/***
	 * 注册
	 * @return
	 */
	@PostMapping("regist")
	public ApiResult regist(@RequestBody User user) {
		user.preInsert();
		userService.insert(user);
		User users=userService.getById(user.getId());
		if(users!=null) {
			return insertSuccess(users);
		}else {
			return insertAttributeNotRequirements("");
		}
	}
	
	/***
	 * 登陆
	 * @return
	 */
	@PostMapping("login")
	public ApiResult login(String username,String password) {
		QueryCondition qc=new QueryCondition();
		Criteria c=qc.createCriteria();
		c.andEqualTo("username", username);
		c.andEqualTo("password", password);
		User user=userService.getByCondition(qc);
		if(user!=null) {
			return selectSuccess(user);
		}
		return selectNotFound();
	}
	
	/***
	 * 查询自己创建的问卷
	 * @return
	 */
	@GetMapping("myQuestion")
	public ApiResult getMyQuestin(String userId) {
		QueryCondition qc=new QueryCondition();
		Criteria c=qc.createCriteria();
		c.andEqualTo("user_id", userId);
		List<Questionnaire> qList=questionnaireService.findByCondition(qc);
		if(qList!=null && !qList.isEmpty()) {
			return selectSuccess(qList);
		}
		return selectNotFound();
	}
	
	/***
	 * 通过问卷ID和IP地址查询答题前是否重复答题
	 * @param request
	 * @param id
	 * @return
	 */
	
	@GetMapping("getIp")
	public ApiResult getIP(HttpServletRequest request,String id) {
	String ip=IpUtils.getIp2(request);
	QueryCondition qc=new QueryCondition();
	Criteria c=qc.createCriteria();
	c.andEqualTo("questionnaire_id", id);
	c.andEqualTo("ip_address", ip);
	QuestionnaireIp qi=	questionnaireIpService.getByCondition(qc);
	if(qi!=null) {
		return insertDataRepeat();
	}else {
		return insertSuccess("");
	}
	}
}
