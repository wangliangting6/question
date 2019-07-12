<template>

    <Row style="margin-top:15%;">

         <Col span="8"> <div > </div> </Col>
        <Col span="8" style="margin-left:32%;" v-if="isRegist">
          <Card>
              <P slot="title" v-if="type=='login'">账号登陆</P>
              <P slot="title" v-if="type=='regist'">账号注册</P>
                <span  v-if="type=='login'">账号：</span><Input v-if="type=='login'" v-model="username" placeholder="请输入账号" style="width: 300px" />
                 <span  v-if="type=='regist'">用户名：</span><Input v-if="type=='regist'" v-model="name" placeholder="请输入用户名" style="width: 300px" />
                <br>
                <br>
                密码：<Input v-model="password" type="password" placeholder="请输入密码" style="width: 300px" />
                   <br>   <br>
                <Button type="info" @click="login" v-if="type=='login'">登录</Button>
                 <Button type="default" @click="type='regist'" v-if="type=='login'">注册</Button>
                 <Button type="default" @click="type='login'"  v-if="type=='regist'">返回登陆</Button>
                    <Button type="default" @click="regist"  v-if="type=='regist'">确认注册</Button>
            </Card>
          
            </Col>
            <Col span="8" style="margin-left:32%;" v-if="!isRegist">
          <Card>
              <P slot="title">你的账号为{{user.username}}</P>
               <Button type="info" @click="login" >直接登录</Button>
            </Card>
          
            </Col>
        <Col span="8">  
        <input type="hidden"/>     
        </Col>       
    </Row>
</template>
<script>
export default {
  data () {
    return {
     name:'',
     username:'',
     password:'',
     type:'login',
     isRegist:true,
     user:{}
    }
  },methods:{
    login(){
        var that=this;
        if(!this.username){
            s.error("请输入账号")
            return false
        }
         if(!this.password){
            s.error("请输入密码")
            return false
        }
        s.post('/api/login?username='+this.username+"&password="+this.password,{},function(data){
            console.log(data)
            if(data &&data.success){
                 s.success("登陆成功")
                 console.log(data.data)
                 localStorage.setItem("name",data.data.name)
                 localStorage.setItem("userId",data.data.id)
                that.$router.push("/createQuestion")
            }else{
                s.error("账号或密码错误")
            }
        })
      
    },regist(){
         this.type='regist'
         var user={
             name:this.name,
             password:this.password
         }
         var that=this;
        s.post('/api/regist',user,function(data){
            if(data && data.success){
                that.user=data.data
                that.username=data.username
                that.password=data.password
                that.isRegist=false;
                s.success("注册成功")
            }else{
                  s.error(data.message)
            }
        })
       
    }
  }
}
</script>
