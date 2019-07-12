<template>
<div>
     <Affix :offset-top="0" style="margin-top:-5%;">
        <Row>
        <Col span="24">  
         <Card >
          <p v-if="!this.$route.query.id">创建问卷          
             
          </p> 
           <p v-if="this.$route.query.id">编辑问卷          
             
          </p> 
             <Button style="position:absolute;right:35px;top:7px;"  icon="ios-add" type="info" @click="goList">我发布的问卷</Button>
          <span style="position:absolute;right:10px;top:15px;color:#57a3f3"> {{name}}</span>
          <!-- style="position:fixed;right:10px;top:7px;"  -->
        </Card>
        </Col>       
    </Row>
    </Affix>
    <Row>
    <Card v-if="!ishave">
            <p slot="title" >请输入问卷信息</p>
             调查标题：<Input v-model="question.title" placeholder="请输入调查标题" style="width: 300px" />
             <br> <br>
             调查背景：<Input v-model="question.tips" type="textarea" placeholder="请输入调查背景" style="width: 300px" />
            <br>  <br>
            <Button type="info" @click="createQuestionnaire" v-if="!this.$route.query.id">保存</Button>
              <Button type="info" @click="updateThis" v-if="this.$route.query.id">保存</Button>
            <br>
        </Card>
        <Card v-if="ishave">
            <p slot="title" >问卷信息</p>
             调查标题：{{question.title}}
             <br> <br>
             调查背景：{{question.tips}}
            <br>  <br>
        </Card>
        <span v-if="address">问卷地址:
            <a :href="address" @click="goAddress">{{address}}</a></span>
    </Row>
    <Row style="margin-top:5%;">

         <Col span="4"> </Col>
        <Col span="4" style="margin-left:10%">
        <div style="margin-top:15%;">
         <Tag color="default">题目类型选择</Tag><br><br>
          <Button @click="chooseType('radio')" type="info">单选题</Button><br>
          <br>
          <Button @click="chooseType('check')"  type="warning">多选题</Button><br><br>
          <Button @click="chooseType('answer')"  type="success">问答题</Button><br>
          </div>
            </Col>
            <Col span="12">
        <Card v-show="radio || check">
            <p slot="title" v-show="radio">请输入单选题题目</p>
            <p slot="title" v-show="check">请输入多选题题目</p>
            <br>
             题目内容：<Input v-model="questions.tips" placeholder="请输入题目内容" style="width: 300px" />
              <br> <br>
          <table style="border:1px solid #f1f1f1;margin-left:20%;">
              <thead  style="border:1px solid #f1f1f1;">
                  <tr>
                      <th class="text-center" style="width: 50px;">序号</th> 
                      <th class="text-center" style="width: 110px;">操作</th> 
                      <th class="text-center" style="width: 110px;">选项序列</th> 
                      <th class="text-center" style="width: 110px;">选项内容</th> 
                       </tr>
                      </thead> 
                      <tbody  style="border:1px solid #f1f1f1;" v-for="(item,index) in radioListData" :key="index">
                          <tr>
                        <td class="text-center" style="line-height: 29.6px;">{{index+1}}</td> 
                        <td role="gridcell" aria-describedby="dataGrid2_operating" style="text-align: center; min-width: 95px; line-height: 29.6px;">
                            <a href="javascript:void(0);">
                                <Icon type="md-copy"  @click="copyRow"/>
                                </a> 
                                <a href="javascript:void(0);">
                                <Icon type="md-add" @click="addRow"/>
                                    </a> 
                                <Icon type="md-backspace" @click="deleteRow(index)"/>
                                    </td> 
                                    <td><input type="text" v-model="item.choose"  onfocus="this.select()"  autocomplete="off" class="form-control"></td> 
                                    <td><input type="text"  v-model="item.answer"  onfocus="this.select()"  maxlength="20" autocomplete="off" class="form-control"></td> 
                                    </tr>
                                    </tbody></table>
                                    <br>
                                    <Button @click="save()" type="info">保存</Button><br>
        </Card>
         <Card v-show="answer">
            <p slot="title" >请输入问答题题目</p>
             题目内容：<Input v-model="title" placeholder="请输入题目内容" style="width: 300px" />
             <br> <br>
             回答提示：<Input v-model="value" placeholder="请输入提示内容" style="width: 300px" />
            <br><br>
            <Button type="info" @click="saveTips()">保存</Button><br>
        </Card>
        
            </Col>
        <Col span="4">  
        <input type="hidden"/>     
        </Col>       
    </Row>
        <Card style="">
          <p>已有问题                      
          </p> 
     <Card style="width:320px;display:inline-flex;margin:2px;" v-for="(i,index) in questionList" :key="i.id" >
        <div style="text-align:center" @click="edit(i.id,i.type)">
           <Tag color="primary" v-if="i.type=='radio'">单选题</Tag>
           <Tag color="warning" v-if="i.type=='check'">多选题</Tag>
           <Tag color="success" v-if="i.type=='answer'">自主问答题</Tag>
            <h3 style="color:#2db7f5">{{index+1}}.{{i.tips}}</h3>
             <Button type="error" style="position:absolute;right:10px;top:12px;" @click="deleteThis(i.id)">删除</Button><br>
        </div>
    </Card>
    
          <!-- style="position:fixed;right:10px;top:7px;"  -->
        </Card>
    </div>
</template>
<script>
export default {
  data () {
    return {
        address:'',
        radio:false,
        check:false,
        answer:false,
        value:'',
        title:'',
        ishave:false,
        questions:{
            questionnaireId:'',
            type:'',
            tips:''
        },
        question:{
            title:'',
            tips:'',
            userId:localStorage.getItem("userId")
        },
        name:localStorage.getItem("name"),
        radioListData:[{
            choose:'',
            answer:''
        }],
        checkListData:[{
            choose:'',
            answer:''
        }],
        placeholder:'',
        questionList:[],
        update:false
    }
  },methods:{
      goAddress(){
          window.open(this.address)
      },
      goList(){
          this.$router.push('/myQuestion')
      },
      deleteThis(id){
          var that=this;
          s.delete('/api/QuestionsDetail?id='+id,{},function(data){
              if(data && data.success){
                  s.success('删除成功')
                  that.getQuestionList();
              }
          })
      },
      updateThis(){
          var that=this;
            if(!this.question.title){
            s.error("请输入标题")
            return false
          }
          if(!this.question.tips){
              s.error("请输入调查背景")
              return false
          }
          this.question.id=this.$route.query.id
           s.patch('/api/Questions',this.question,function(data){
               if(data && data.success){
                   s.success("修改成功")
                   that.getDetail();
               }
           })
      },
      edit(id,type){
          var that=this;
          s.get('/api/getQuestionsDetail?id='+id,{},function(data){
              if(data && data.success){
                  if(type=='radio' || type=='check'){
                      that.questions=data.data
                     that.radioListData=data.data.chooseList
                  }
              }
          })
          this.chooseType(type,id)
           this.update=true;
      },
      createQuestionnaire(){
          var that=this;
          if(!this.question.title){
              s.error("请输入标题")
              return false
          }
          if(!this.question.tips){
              s.error("请输入调查背景")
              return false
          }
          s.post('/api/questionnaire',this.question,function(data){
              if(data && data.success){
                  s.success("新增成功")
                  that.ishave=true;
                  that.address=window.location.host+"/questionList?id="+data.data.id
                  that.questions.questionnaireId=data.data.id
                 // localStorage.setItem("questionId",data.data.id)
                  that.question=data.data
              }else{
                  that.question={}
              }
          })

          
      },
      saveTips(){
          var that=this;
          var que={
            questionnaireId:this.$route.query.id,
            type:this.questions.type,
            tips:this.title ,
            placeholder:this.value
          }
            s.post('/api/question',que,function(data){
                if(data && data.success){
                    s.success("新增成功")
                    that.getQuestionList();
                }
            })
      },
      save(){
          var that=this;
          if(!this.questions.tips){
              s.error("请输入题目内容")
              return false
          }           
          if(this.update){
            s.patch('/api/QuestionsDetail',this.questions,function(data){
              if(data && data.success){
                  if(that.questions.type!='answer'){
                      for(var i=0,len=that.radioListData.length;i<len;i++){
                          that.radioListData[i].questionnaireId= that.$route.query.id
                          that.radioListData[i].questionId=data.data.id
                      }
                      s.post('/api/choose',that.radioListData,function(data1){
                          if(data1 && data1.success){
                              s.success("修改成功")
                              that.radioListData=[{
                                    choose:'',
                                    answer:''
                                }]
                              that.getQuestionList();
                              }
                      })
                  }
              }
          })
          }else{  
        this.questions.questionnaireId=this.$route.query.id            
         s.post('/api/question',this.questions,function(data){
              if(data && data.success){
                  if(that.questions.type!='answer'){
                      for(var i=0,len=that.radioListData.length;i<len;i++){
                          that.radioListData[i].questionnaireId= that.questions.questionnaireId
                          that.radioListData[i].questionId=data.data.id
                      }
                      s.post('/api/choose',that.radioListData,function(data1){
                          if(data1 && data1.success){
                              s.success("新增成功")
                              that.radioListData=[{
                                    choose:'',
                                    answer:''
                                }]
                              that.getQuestionList();
                              }
                      })
                  }
              }
          })
          }
     
          //this.$router.push('/questionList')
      },
    chooseType(value,id){
      
        this.questions={
            questionnaireId:'',
            type:'',
            tips:''
        }
        this.radioListData=[{
            choose:'',
            answer:''
        }]
        this.update=false
        this.questions.type=value
        if(this.ishave || id || this.$route.query.id){
        if(value=='radio'){
            this.radio=true;
            this.check=false
             this.answer=false
        }else if(value=='check'){
            this.check=true;
            this.radio=false
             this.answer=false
        }else{
            this.answer=true;
            this.radio=false
             this.check=false
        }
        }else{
            if(!id){
            s.error("请先输入问卷信息")
            return false
            }
           
        }
    },
  copyRow(){
      var list= JSON.parse(JSON.stringify(this.radioListData[this.radioListData.length-1]))
      this.radioListData.push(list)
  },
  addRow(){
      var a={
          choose:'',
            answer:'' 
      }
 this.radioListData.push(a)
  },
  deleteRow(index){
      if(this.radioListData.length==1){
          s.error("最后一行不能删除")
          return false
      }else{
    this.radioListData.splice(index,1)
      }
  },
  getQuestionList(){
      var that=this;
      s.get('/api/getQuestions?id='+ this.$route.query.id,{},function(data){
          if(data && data.success){
              that.questionList=data.data
          }else{
            that.questionList=[]
          }
      })
  },
  getDetail(id){
       var that=this;
      s.get('/api/questionnaire?id='+ id,{},function(data){
          if(data && data.success){
              that.question.title=data.data.title
               that.question.tips=data.data.tips
               that.questions.questionnaireId=data.data.id
               that.getQuestionList();
               //that.ishave=true;
          }else{
           // that.questionList=[]
          }
      })
  },
  },
 mounted(){
        if(this.$route.query.id){       
            this.address=window.location.host+"/questionList?id="+this.$route.query.id
            this.questions.questionnaireId=this.$route.query.id
            this.getDetail(this.$route.query.id)   
                 
        }
         if(!localStorage.getItem("userId")){
          this.$router.push('/login')
      }
       // this.getQuestionList();
    },
}
</script>
