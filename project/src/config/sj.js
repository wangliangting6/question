/**
 * 公共JS类
 */
var s = s || {}
import Vue from 'vue' //vue核心库
import configParams from './config'//配置文件库


import { Message, Modal } from 'iview'
import 'iview/dist/styles/iview.css';
 

// //全局的静态属性  s.v.'key'
s.v = configParams;
s.Modal = Modal;

/*******************************************************************************【提示】 toast，loading  *********************************************************************************/
/**
 * 成功提示
 * @param {提示国际化的key} tips 
 */
s.success = function (tips,time) {
    //修改源码：\node_modules\iview\dist\iview.js
    //19511  styles['z-index'] = 1010 + this.tIndex;    修改 1010 为 10010
    if(!tips){
       tips = 'base.tips.success'
    }
    
    Message.success({
        content: tips,
        duration: time?time:2  //秒
    })
}
/**
 * 红色错误
 * @param {提示} tips 
 */
s.error = function (tips,time) {
    if(!tips){
        tips = 'base.tips.error';
    }
    Message.error({
        content: tips,
        duration: time?time:2  //秒
    })
}
/**
 * 黄色警告
 * @param {提示} tips 
 */
s.warn = function (tips,time) {
    if(!tips){
        tips = 'base.tips.warn';
    }
    Message.warning({
        content:tips,
        duration: time?time:2  //秒
    })
}
/**
 * 白色 取消x的图标
 * @param {提示} tips 
 */
s.cancel = function (tips,time) {
    if(!tips){
        tips = 'base.tips.cancel';
    }
    Message.warning({
        content: tips,
        duration: time?time:2  //秒
    })
}
/**
 * 纯文本的提示
 * @param {提示} tips 
 */
s.text = function (tips,time) {
    if(!tips){
        tips = 'base.tips.text';
    }
    Message.info({
        content: tips,
        duration: time?time:2  //秒
    })
}
s.render = function (config){
    Message.info(config)
} 

/**
 * 加载 提示
 * @param {提示} tips 
 */
s.loading = function (tips) {
    if(!tips){
        tips = 'base.tips.loading';
    }
    Message.loading({
        content: tips,
        duration: 0
    })
}

/**
 * 关闭toast 或者 loading
 */
s.close = function () {
    Message.destroy()
}

/********************************************************************************【弹框】alert  confirm   ********************************************************************************/
/**
 * 
 * @param {标题} title 
 * @param {内容} content 
 * @param {显示事件} onShow 
 * @param {关闭事件} onHide 
 */
s.alert = function (title, content, onShow, onHide) {
	if(window.screen.availWidth>600){
//
//        Modal.confirm({
//            title: title,
//            content: content,
//            okText:s.t('base.button.okText'),
//            cancelText:s.t('base.button.cancelText'),//
//            onOk() {
//                if (onConfirm) {
//                    onConfirm()
//                }
//            },
//            onCancel() {
//                if (cancel) {
//                    cancel()
//                }
//            }
//        });

    }else{
//        Dialog.alert({
//            title: title,
//            message: content,
//            zIndex:10000,
//            showCancelButton: true,
//            confirmButtonText: s.t('base.button.okText'),
//            cancelButtonText: s.t('base.button.cancelText'),//
//        }).then(() => {
//            if (onConfirm) {
//                onConfirm()
//            }
//        }).catch(() => {
//            if (cancel) {
//                cancel()
//            }
//        });
    }
}

/**
 * 
 * @param {标题} title 
 * @param {内容} content 
 * @param {确认回调函数} onConfirm 
 * @param {取消回调} onCancel 
 */
s.confirm = function (title, content, onConfirm,cancel) {
    //修改源码：\node_modules\iview\dist\iview.js
    //  9339行 为var prefixCls = 'ivu-btn';   ivu-btn 为 btn
    //  19869 行 为         name: 'Modal', 的  z-index: 10000
     
    if(window.screen.availWidth>600){

        Modal.confirm({
            title: title,
            content: content,
            okText:'确认',
            cancelText:'取消',//
            onOk() {
                if (onConfirm) {
                    onConfirm()
                }
            },
            onCancel() {
                if (cancel) {
                    cancel()
                }
            }
        });

    }else{
        Dialog.alert({
            title: title,
            message: content,
            zIndex:10000,
            showCancelButton: true,
            confirmButtonText:'确认',
            cancelButtonText: '取消',//
        }).then(() => {
            if (onConfirm) {
                onConfirm()
            }
        }).catch(() => {
            if (cancel) {
                cancel()
            }
        });
    }
}


/******************************************************************************** axios 拦截器和请求 ********************************************************************************/
import axios from 'axios' //ajax请求的 内容
//取消请求
let pending = []
let CancelToken = axios.CancelToken
let removePending = (config, f) => {
    let flagUrl = config.url + '&' + config.method + '&' + Date.parse(new Date()) / 100
    if (pending.indexOf(flagUrl) !== -1) {
        if (f) {
            f() // 执行取消操作
        } else {
            pending.splice(pending.indexOf(flagUrl), 1)// 把这条记录从数组中移除
        }
    } else {
        if (f) {
            pending.push(flagUrl)
        }
    }
}
axios.defaults.baseURL = configParams.apiPath;
//设置请求超时时间
axios.defaults.timeout = 5000


//开始请求设置，发起拦截处理 config代表发起请求的参数实体
axios.interceptors.request.use(config => {
  

   

    //请求拦截器
    config.cancelToken = new CancelToken((c) => {
        removePending(config, c) 
    })

	config.headers["Content-Type"] ="application/json;charset=UTF-8";
    return config;  //添加这一行
}, error => {
    return error;
    // return Promise.reject(error)
})

//请求到结果的拦截处理
axios.interceptors.response.use(data => {
    // console.log(data) //统一打印返回值
    //返回请求的正确结果
    return data.data;
}, error => {
    if (error && error.message && error.message.indexOf('timeout') != -1) {
        s.error('timeout')
    }

    if (error.response) {
        return error.response.data;
    }
   
})


//将axios的get方法绑定到S上面
s.get = function (url, params, callback) {
    // s.loading()
    return new Promise((resolve, reject) => {
        axios.get(url, { params: params })
            .then(res => {
                // resolve(res)
                // s.close();
                callback(res)
            }).catch(err => {
                // s.close();
                // callback(err)
                reject(err)
            })
    })
}

//将axios的post方法绑定到S上面
s.post = function (url, params, callback) {
    s.loading()
    
    return new Promise((resolve, reject) => {
        axios.post(url, params)
            .then(res => {
                console.log(url)
                //resolve(res)
                s.close();
                callback(res)
            }).catch(err => {
                s.close();
                // callback(err)
                reject(err)
            })
    })
}


//将axios的patch方法绑定到S上面
s.patch = function (url, params, callback) {
    s.loading()
    return new Promise((resolve, reject) => {
        axios.patch(url, params)
            .then(res => {
                // resolve(res)
                s.close();
                callback(res)
            }).catch(err => {
                s.close();
                // callback(err)
                reject(err)
            })
    })
}
//将axios的put方法绑定到S上面
s.put = function (url, params, callback) {
    s.loading()
    return new Promise((resolve, reject) => {
        axios.put(url, params)
            .then(res => {
                // resolve(res)
                s.close();
                callback(res)
            }).catch(err => {
                s.close();
                // callback(err)
                reject(err)
            })
    })
}
//将axios的del方法绑定到S上面  删除的id 在url后 例如：/api/users/123  删除id为123的用户
s.del = function (url, callback) {
    s.loading()
    return new Promise((resolve, reject) => {
        axios.delete(url, {})
            .then(res => {
                // resolve(res)
                s.close();
                callback(res)
            }).catch(err => {
                s.close();
                // callback(err)
                reject(err)
            })
    })
}
//将axios的delete方法绑定到S上面
s.delete = function (url, params, callback) {
    s.loading()
    return new Promise((resolve, reject) => {
        axios.delete(url, params)
            .then(res => {
                // resolve(res)
                s.close();
                callback(res)
            }).catch(err => {
                s.close();
                // callback(err)
                reject(err)
            })
    })
}




export default s
