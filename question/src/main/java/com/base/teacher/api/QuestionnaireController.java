package com.base.teacher.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.base.teacher.config.common.ApiResult;
import com.base.teacher.config.common.IdGen;
import com.base.teacher.config.common.QueryCondition;
import com.base.teacher.config.common.QueryCondition.Criteria;
import com.base.teacher.config.common.base.BaseController;
import com.base.teacher.config.utils.IpUtils;
import com.base.teacher.config.utils.StringUtils;
import com.base.teacher.questionnaire.entity.Questionnaire;
import com.base.teacher.questionnaire.entity.QuestionnaireAnswer;
import com.base.teacher.questionnaire.entity.QuestionnaireChoose;
import com.base.teacher.questionnaire.entity.QuestionnaireIp;
import com.base.teacher.questionnaire.entity.QuestionnaireQuestion;
import com.base.teacher.questionnaire.service.QuestionnaireAnswerService;
import com.base.teacher.questionnaire.service.QuestionnaireChooseService;
import com.base.teacher.questionnaire.service.QuestionnaireIpService;
import com.base.teacher.questionnaire.service.QuestionnaireQuestionService;
import com.base.teacher.questionnaire.service.QuestionnaireService;
import com.github.pagehelper.StringUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@RestController
@RequestMapping(value = "/api/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class QuestionnaireController extends BaseController{

	@Autowired
	private QuestionnaireService questionnaireService;
	@Autowired
	private QuestionnaireQuestionService questionnaireQuestionService;
	@Autowired
	private QuestionnaireChooseService questionnaireChooseService;
	@Autowired
	private QuestionnaireAnswerService questionnaireAnswerService;
	@Autowired
	private QuestionnaireIpService questionnaireIpService;
	/***
	 * 创建问卷
	 * @param questionnaire
	 * @return
	 */
	@PostMapping("questionnaire")
	public ApiResult createQuestionnaire(@RequestBody Questionnaire questionnaire) {
		questionnaire.preInsert();
		int i=questionnaireService.insert(questionnaire);
		if(i>0) {
			return insertSuccess(questionnaire);
		}
		return insertDataRepeat();
	}
	
	/***
	 * 创建问卷题目 传入单个题目
	 * @param questionnaireQuestions
	 * @return
	 */
	@PostMapping("question")
	public ApiResult createQuestion(@RequestBody  @Validated QuestionnaireQuestion questionnaireQuestions) {
		questionnaireQuestions.preInsert();
		int i=questionnaireQuestionService.insert(questionnaireQuestions);
		if(i>0) {
			return insertSuccess(questionnaireQuestions);
		}
		return insertDataRepeat();
	}
	
	/***
	 * 创建问卷题目选项 传入题目选项List
	 * @param questionnaireQuestions
	 * @return
	 */
	@PostMapping("choose")
	public ApiResult createQuestionChoose(@RequestBody  @Validated List<QuestionnaireChoose> questionnaireChooses) {
		String questionId="";
		if(questionnaireChooses!=null && questionnaireChooses.size()>0) {
			List<String> idList=IdGen.getIds(questionnaireChooses.size());
			int i=0;
			for (QuestionnaireChoose questionnaireChoose : questionnaireChooses) {
				questionnaireChoose.preInsert(idList.get(i));
				questionId=questionnaireChoose.getQuestionId();
				i++;
			}
		}
		
		int i=questionnaireChooseService.insertAll(questionnaireChooses,questionId);
		if(i>0) {
			return insertSuccess(questionnaireChooses);
		}
		return insertDataRepeat();
	}
	
	/***
	 * 回答问卷，传入回答的list
	 * @param questionnaireAnswers
	 * @return
	 */
	@PostMapping("answerList")
	public ApiResult replyQuestion(@RequestBody List<QuestionnaireAnswer> questionnaireAnswers,HttpServletRequest request) {
		String questionId="";
		List<QuestionnaireAnswer> answerList=Lists.newArrayList();
		if(questionnaireAnswers!=null && !questionnaireAnswers.isEmpty()) {
			Map<String, StringBuffer> resultMap = new HashMap<>();
			for (QuestionnaireAnswer questionnaireAnswer : questionnaireAnswers) {
				 if (resultMap.containsKey(questionnaireAnswer.getQuestioId())) {
		                //map中存在此id，将数据存放当前key的map中
		                resultMap.get(questionnaireAnswer.getQuestioId()).append(",").append(questionnaireAnswer.getAnswer());
		            } else {
		                //map中不存在，新建key，用来存放数据
		            	StringBuffer sb=new StringBuffer();
		            	sb.append(questionnaireAnswer.getAnswer());
		                resultMap.put(questionnaireAnswer.getQuestioId(), sb);
		            }
				 questionId=questionnaireAnswer.getQuestionnaireId();
			}
		
			int i=0;
			List<String> idList=IdGen.getIds(questionnaireAnswers.size());
			for (String key : resultMap.keySet()) {
				QuestionnaireAnswer qa=new QuestionnaireAnswer();
				qa.preInsert(idList.get(i));
				qa.setQuestioId(key);
				qa.setQuestionnaireId(questionId);
				qa.setAnswer(resultMap.get(key).toString());
				answerList.add(qa);
				//questionnaireAnswer.setQuestioId(questioId);
				i++;
			}
	
			Questionnaire q= questionnaireService.getById(questionId);
			if(q!=null) {
				Questionnaire qs=new Questionnaire();
				if(StringUtils.isNoneBlank(q.getNum())) {
					Integer num=Integer.parseInt(q.getNum());
					Integer numadd=num++;
					qs.setNum(numadd.toString());
				}else {
					qs.setNum("0");
				}
				questionnaireService.update(qs,q.getId());
			}
		}
		String ip=IpUtils.getIp2(request);
		QuestionnaireIp qi=new QuestionnaireIp();
		qi.preInsert();
		qi.setIpAddress(ip);
		qi.setQuestionnaireId(questionId);
		int j=questionnaireAnswerService.insertAll(answerList,qi);
		//int j=questionnaireAnswerService.batchInsert(questionnaireAnswers);
		if(j>0) {
			return insertSuccess(questionnaireAnswers);
		}
		return insertDataRepeat();
	}
	
	/***
	 * 通过问卷ID查询问卷详情
	 * @param id
	 * @return
	 */
	@GetMapping("questionnaire")
	public ApiResult lookQuestionById(String id) {
		Questionnaire q=questionnaireService.getById(id);
		if(q!=null) {
			QueryCondition qc=new QueryCondition();
			Criteria c=qc.createCriteria();
			c.andEqualTo("questionnaire_id", id);
			List<QuestionnaireQuestion> questionList=questionnaireQuestionService.findByCondition(qc);
			if(questionList!=null && !questionList.isEmpty()) {
				//Map<String, List<QuestionnaireChoose>> chooseMap=Maps.newHashMap();
				//StringBuffer sb=new StringBuffer();
				for (QuestionnaireQuestion questionnaireQuestion : questionList) {
					QueryCondition qc1=new QueryCondition();
					Criteria c1=qc1.createCriteria();
					c1.andEqualTo("question_id", questionnaireQuestion.getId());
					c1.andEqualTo("questionnaire_id", id);
					List<QuestionnaireChoose> chooseList=questionnaireChooseService.findByCondition(qc1);
					questionnaireQuestion.setChooseList(chooseList);
					//sb.append(questionnaireQuestion.getId()).append(",");
				}
				//String questionIds=sb.substring(0,sb.length()-1).toString();
				
				/*List<QuestionnaireChoose> newList=Lists.newArrayList();
				if(chooseList!=null && !chooseList.isEmpty()) {
					for (QuestionnaireChoose questionnaireChoose : chooseList) {
						if(questionnaireChoose.getQuestionId().equals(anObject))
						newList.add(questionnaireChoose);
					}
				}*/
				q.setQuestionList(questionList);
			}
			return selectSuccess(q);
		}
		return selectNotFound();
	}
	
	/***
	 * 通过问卷ID查询有哪些题目
	 */
	
	@GetMapping("getQuestions")
	public ApiResult lookQuestions(String id) {
		QueryCondition qc=new QueryCondition();
		Criteria c=qc.createCriteria();
		c.andEqualTo("questionnaire_id", id);
		List<QuestionnaireQuestion> list=questionnaireQuestionService.findByCondition(qc);
		if(list!=null && !list.isEmpty()) {
			return selectSuccess(list);
		}
		return selectNotFound();
	}
	
	/***
	 * 通过问题ID查询选项
	 * @param id
	 * @return
	 */
	@GetMapping("getQuestionsDetail")
	public ApiResult lookQuestionsDetail(String id) {
		QuestionnaireQuestion qq=questionnaireQuestionService.getById(id);
		if(qq!=null) {
			QueryCondition qc=new QueryCondition();
			Criteria c=qc.createCriteria();
			c.andEqualTo("question_id", id);
			List<QuestionnaireChoose> chooseList=questionnaireChooseService.findByCondition(qc);
			if(chooseList!=null && !chooseList.isEmpty()) {
				qq.setChooseList(chooseList);
				return selectSuccess(qq);
			}
		}
		return selectNotFound();
	}
	
	/***
	 * 删除题目
	 * @param id
	 * @return
	 */
	@DeleteMapping("QuestionsDetail")
	public ApiResult deleteQuestionsDetail(String id) {
		QuestionnaireQuestion qq=questionnaireQuestionService.getById(id);
		if(qq!=null) {
			int i=questionnaireQuestionService.deleteById(id);
			return deleteSuccess(i);
		}
		return selectNotFound();
	}
	

	/***
	 * 修改题目
	 * @param id
	 * @return
	 */
	@PatchMapping("QuestionsDetail")
	public ApiResult updateQuestionsDetail(@RequestBody  @Validated QuestionnaireQuestion questionnaireQuestions) {
		QuestionnaireQuestion qq=questionnaireQuestionService.getById(questionnaireQuestions.getId());
		if(qq!=null) {
			int i=questionnaireQuestionService.update(questionnaireQuestions, questionnaireQuestions.getId());
			return updateSuccess(questionnaireQuestions);
		}
		return updateNotFound();
	}
	
	/***
	 * 删除问卷
	 * @param id
	 * @return
	 */
	@DeleteMapping("Questions")
	public ApiResult deleteQuestions(String id) {
		Questionnaire q=questionnaireService.getById(id);
		if(q!=null) {
			int i=questionnaireService.deleteById(id);
			return deleteSuccess(i);
		}
		return selectNotFound();
	}
	
	/***
	 * 修改问卷
	 * @param questionnaireQuestions
	 * @return
	 */
	@PatchMapping("Questions")
	public ApiResult updateQuestionsDetail(@RequestBody  @Validated Questionnaire questionnaire) {
		Questionnaire qq=questionnaireService.getById(questionnaire.getId());
		if(qq!=null) {
			int i=questionnaireService.update(questionnaire, questionnaire.getId());
			return updateSuccess(questionnaire);
		}
		return updateNotFound();
	}
	
	/***
	 * 通过问卷ID查询答题情况
	 * @param id
	 * @return
	 */
	@GetMapping("getDetail")
	public ApiResult lookAnswerById(String id) {
		Questionnaire q= questionnaireService.getById(id);
		QueryCondition qc=new QueryCondition();
		Criteria c=qc.createCriteria();
		c.andEqualTo("questionnaire_id", id);
		List<QuestionnaireQuestion> list=questionnaireQuestionService.findByCondition(qc);
		if(list!=null && !list.isEmpty()) {
			for (QuestionnaireQuestion questionnaireQuestion : list) {
				Map<String, String> params=Maps.newHashMap();
				params.put("questionId", questionnaireQuestion.getId());
				List<QuestionnaireAnswer> answerList=questionnaireAnswerService.selectList("selectAnswer", params);
				questionnaireQuestion.setAnswerList(answerList);
			}
			q.setQuestionList(list);
			return selectSuccess(q);
		}
		return selectNotFound();
	}
}
