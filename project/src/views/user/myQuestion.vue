<template>
<div>
       <Affix :offset-top="0" style="margin-top:-5%;">
        <Row>
        <Col span="24">  
         <Card >
          <p>我发布的问卷 
          </p> 
          <!-- style="position:fixed;right:10px;top:7px;"  -->
        </Card>
        </Col>       
    </Row>
    </Affix>
    <Row>
        <Col span="20" style="margin-left:8%;">  
        <div v-for="(item,index) in question" :key="index" >
         <Card  style="margin-top:3%;">
          <p slot="title">{{item.title}} 
          </p>
        <span>{{item.tips}}</span>
        <br>
        <br>
         <Button type="default" @click="goDetail(item.id)" >查看</Button>
        <Button type="info" @click="edit(item.id)" >编辑</Button>
         <Button type="error" @click="deletThis(item.id)" >删除</Button>
        </Card>
         <Divider />
        </div>
        
        </Col>       
    </Row>
    </div>
</template>
<script>
export default {
  data () {
    return {
       question:[         
        ]
    }
  },methods:{
      deletThis(id){
          var that=this;
          s.delete('/api/Questions?id='+id,{},function(data){
              if(data && data.success){
                  s.success('删除成功')
                  that.getQuestion();
              }
          })
      },
    goDetail(id){
        this.$router.push('/answerList?id='+id)
    },
    edit(id){
    this.$router.push('/createQuestion?id='+id)
    },
    getQuestion(){
        var that=this;
        s.get('/api/myQuestion?userId='+localStorage.getItem("userId"),{},function(data){
            if(data && data.success){
                that.question=data.data
            }else{
                that.question=[]
            }
        })
    }
  },
  mounted(){
      if(!localStorage.getItem("userId")){
          this.$router.push('/login')
      }
      this.getQuestion()
  }

}
</script>
