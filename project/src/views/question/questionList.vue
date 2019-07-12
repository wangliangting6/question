<template>
<div>
    <Affix :offset-top="0" style="margin-top:-5%;">
        <Row>
        <Col span="24">  
         <Card >
          <p>问卷调查系统             
              
          </p> 
          <!-- style="position:fixed;right:10px;top:7px;"  -->
      <Button style="position:absolute;right:10px;top:7px;" icon="ios-add" type="default" @click="createQuestion">发布我的问卷</Button>
      <Button style="position:absolute;right:155px;top:7px;"  icon="ios-add" type="info" @click="myQuestion">我发布的问卷</Button>
        </Card>
        </Col>       
    </Row>
    </Affix>
    <Row>
        <Col span="20" style="margin-left:8%;margin-top:3%;">  
         <Card >
          <p slot="title">{{question.title}}             
              
          </p>
        <span>{{question.tips}}</span>
        <div style="display:table;" v-for="(item,index) in question.questionList" :key="index">
            <p v-if="item.type=='radio' || item.type=='check'" style="color:#57c5f7;text-align:left;font-size:16px;">{{index+1-answerList.length}}.{{item.tips}}</p>
        <RadioGroup v-if="item.type=='radio'" @on-change="getValue"  style="float:left;">
            <Radio v-for="(a,index1) in item.chooseList" :label="a.questionId+'wen|'+a.choose" :key="index1">
                <span>{{a.choose}}.{{a.answer}}</span>
            </Radio>          
        </RadioGroup>
          <CheckboxGroup style=""  v-if="item.type=='check'" @on-change="getValue">
            <Checkbox  v-for="(a,index1) in item.chooseList" :label="a.questionId+'wen|'+a.choose" :key="index1" >{{a.choose}}.{{a.answer}}</Checkbox>
        </CheckboxGroup>
            
        </div>
        <Divider>问答题</Divider>
         <div style="display:table;" v-for="(s,index) in answerList" :key="s.questioId">
        <p style="color:#57c5f7;text-align:left;font-size:16px;">{{index+1}}.{{s.tips}}</p>
        <Input   type="textarea" :placeholder="s.placeholder" v-model="s.answer" style="width: 300px" />
         </div>
        </Card>
        </Col>       
    </Row>
    <br>
     <Button  type="info" @click="toAnswer()">确认答题</Button>
    </div>
</template>
<script>
export default {
  data () {
    return {
       question:{},
        answer:[],
        answerList:[]
    }
  },methods:{
      input(id,value){
          console.log(value)
      },
    getValue(value){
        var a={}
        if(value instanceof Array){
            var id=""
            var choose=''
            
            for(var i=0,len=value.length;i<len;i++){ 
                id=value[i].split('wen|')[0]    
                choose=value[i].split('wen|')[1]            
            }
         a={
            questionnaireId:this.$route.query.id,
            questioId:id,
            answer:choose
        }
        }else{
        a={
            questionnaireId:this.$route.query.id,
            questioId:value.split('wen|')[0],
            answer:value.split('wen|')[1]
        }
        var b=-1;
        for(var i=0,len=this.answer.length;i<len;i++){ 
               if(a.questioId==this.answer[i].questioId){
                   b=i
               }             
            }
            console.log(b)
        if(b!=-1){
        this.answer.splice(b,1)
        }
        }
        this.answer.push(a)
         console.log(this.answer)
    },
    toAnswer(){
        
        if(this.answerList.length>0){
            for(var i=0;i<this.answerList.length;i++){
                 this.answer.push(this.answerList[i])
            }
             //this.answer.push(this.answerList)
        }
       console.log(this.answer)
        //return false;
        var that=this;
        s.get('/api/getIp?id='+this.$route.query.id,{},function(data){
             if(data && data.success){
            s.post('/api/answerList',that.answer,function(data1){
            if(data1 && data1.success){
                s.success('答题成功')
            }
             })
             }else{
                 s.error('同一个IP不能重复答题')
             }
        })
       
    },
    loadQuestion(id){
        var that=this;
         that.question=[]
         that.answerList=[]
        s.get('/api/questionnaire?id='+id,{},function(data){
            that.question=data.data
            if(data && data.success){
                if(data.data.questionList){
                    var list=data.data.questionList
                    for(var i=0,len=list.length;i<len;i++){
                        if(list[i].type=='answer'){
                            var a={
                                questioId:list[i].id,
                                questionnaireId:that.$route.query.id,
                                answer:'',
                                tips:list[i].tips
                            }
                            that.answerList.push(a)
                        }
                    }
                }
            }
        })
    },
    createQuestion(){
     this.$router.push('/createQuestion')
  },
myQuestion(){
    this.$router.push('/myQuestion')
}
  },
  mounted(){
      this.loadQuestion(this.$route.query.id)
  },

}
</script>
