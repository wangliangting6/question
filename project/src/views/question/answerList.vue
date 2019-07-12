<template>
    <div>
          <Affix :offset-top="0" style="margin-top:-5%;">
        <Row>
        <Col span="24">  
         <Card >
           <p >查看问卷情况          
          </p> 
             <Button style="position:absolute;right:35px;top:7px;"  type="info" @click="go">返回</Button>
          <span style="position:absolute;right:10px;top:15px;color:#57a3f3" @click="logout"> {{name}}</span>
          <!-- style="position:fixed;right:10px;top:7px;"  -->
        </Card>
        </Col>       
    </Row>
    </Affix>
    <Card style="">
          <p>{{Questionnaire.title}}                  
          </p> 
          <p>{{Questionnaire.tips}}                  
          </p>
      <Card style="width:320px;display:inline-flex;margin:2px;" v-for="(i,index) in Questionnaire.questionList" :key="i.id" >
        <div style="text-align:center">
           <Tag color="primary" >{{index+1}}.{{i.tips}}</Tag>
            <p style="color:#2db7f5" v-if="i.type=='check' || i.type=='radio'" v-for="(item,index1) in i.answerList" :key="index1"  >
                选项:{{item.answer}} 选择次数{{item.count}}
            </p>
            <p style="color:#2db7f5" v-if="i.type=='answer'" v-for="(item,index1) in i.answerList" :key="index1"  >
                回答:{{item.answer}} 
            </p>
        </div>
    </Card> 
        </Card>
    </div>
</template>
<script>
export default {
    data () {
        return{
            Questionnaire:{},
             name:localStorage.getItem("name"),
        }
    },
    methods:{
        logout(){
            var that=this;
            s.confirm("退出登录",this.name+"确定要退出登录吗？",function(){
                localStorage.clear();
                that.$router.push("/login")
            })
        },
        go(){
            this.$router.go(-1)
        },
        getDetail(id){
            var that=this;
            s.get('/api/getDetail?id='+id,{},function(data){
                if(data && data.success){
                    that.Questionnaire=data.data
                   // console.log(data.data)
                }else{
                    that.Questionnaire={}
                }
            })
        }
    },
    mounted(){
         if(!localStorage.getItem("userId")){
          this.$router.push('/login')
      }
        this.getDetail(this.$route.query.id)
    }
}
</script>
