<template>
    <div>
        <mt-cell>
            <mt-header fixed title="Strking" class="heard">
                <router-link :to="{path:'/home',query:{selected:'我的'}}" slot="left">
                    <mt-button icon="back">返回</mt-button>
                </router-link>
            </mt-header>
        </mt-cell>

        <mt-cell title="头像" >
        <input class="avatar" name="file" type="file" accept="image/png,image/gif,image/jpeg" @change="img_uplode" style="width: 30%;margin-left: 46%"/>

        <img  v-if="is_uplode" :src="'http://localhost:4444/'+UserInfo.icon" id="heard_icon" width="100" height="100">
        <img v-if="!is_uplode" :src="'http://localhost:4444/'+UserInfo.icon"  width="100" height="100" >
        </mt-cell>
        <hr>
        <mt-cell title="用户名">
            <input type="text"  v-model="UserInfo.username"/>
        </mt-cell>
        <hr>
        <mt-cell title="邮箱">
            <input type="email" v-if="!email_check_state" v-model="UserInfo.email" @blur="emailInputCheck()" style="color: #e67140"/>
            <input type="email" v-if="email_check_state" v-model="UserInfo.email" @blur="emailInputCheck()" />
        </mt-cell>
        <hr>
        <mt-cell title="简介">
            <textarea rows="3" cols="25" maxlength="30" v-model="UserInfo.introduce" ></textarea>
        </mt-cell>
        <hr>
        <mt-button size="large" type="primary" style="margin-top: 35px" @click="submit()">确认修改</mt-button>
        <!--<mt-field label="用户名" placeholder="请输入用户名" v-model="RegisterForm.username"></mt-field>-->


    </div>
</template>

<style scoped lang="less">
    @import "../../less/my-me";
</style>

<script>
    import {userInfo,changeInfo} from "../../api/user"
    import {uplode_img} from "../../api/home"
    import MtCell from "../../../node_modules/mint-ui/packages/cell/src/cell.vue";
    import MtButton from "../../../node_modules/mint-ui/packages/button/src/button.vue";
    import { MessageBox } from 'mint-ui';
    import RespStatus from "../../api/base/RespStatus";
    export default {
        name: 'Me_ChangeInfo',

        data() {
            return {
                UserInfo:{
                    id: null,
                    username: '',
                    password: '',
                    icon: '',
                    email: '',
                    introduce: ''
                },
                is_uplode: false,
                email_check_state:true,
            }
        },

        props: {},

        components: {
            MtButton,
            MtCell},

        mounted() {
            const user_id = window.localStorage.getItem("strking_user_id");
            //调用获取用户信息接口
            userInfo(user_id).then(res=>{
                console.log(res);
                this.UserInfo.username = res.data.data.username;
                this.UserInfo.icon = res.data.data.icon;
                this.UserInfo.email = res.data.data.email;
                this.UserInfo.introduce = res.data.data.introduce;
                this.UserInfo.password = res.data.data.password;
            })
        },

        watch: {},

        computed: {},

        filters: {},

        methods: {
            //头像上传
            img_uplode(e){
                let file = e.target.files[0];
                let param = new FormData(); //创建form对象
                let file_name = 'kkai' + file.name;
                param.append('file',file,file_name);//通过append向form对象添加数据
                //param.append('chunk','0');//添加form表单中其他数据
                console.log(param.get('file'));
                //axios向后端发送请求
                uplode_img(param).then(res=>{
                    if (res.data.data != null || res.data.data != ''){
                        this.is_uplode = true;
                        this.UserInfo.icon = 'img/headIcon_'+file_name;    //修改图片
                    }
                });
            },
            //提交
            submit(){
                //前端输入校验
                if (this.UserInfo.username==''){
                    MessageBox.alert("用户名不能为空");
                    return false;
                }else {
                    var arr = new Array();
                    arr = this.UserInfo.username.split(" ");
                    if(arr.length != 1){
                        MessageBox.alert("用户名中不能含有空格！");
                        return false;
                    }
                }
                if (this.email_check_state == false){
                    MessageBox.alert('邮箱格式错误');
                    return false;
                }
                if (this.UserInfo.introduce.length > 30){
                    MessageBox.alert('简介太长了。。。');
                }
                changeInfo(this.UserInfo.username,this.UserInfo.email,this.UserInfo.icon,this.UserInfo.introduce).then(res=>{
                    console.log(res);
                    if (res.data.code == RespStatus.OK.code){
                        MessageBox.alert('修改成功!').then(()=>{
                            this.$router.push({path:'/home',query:{selected:'我的'}});
                        });
                    }
                    if (res.data.code == RespStatus.BAD_REQUEST.code) {
                        MessageBox.alert('用户名已存在!').then(()=>{
                            this.$router.push({path:'/changeInfo'});
                        })
                    }
                })

            },
            //邮箱检测
            emailInputCheck(){
                var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
                if (!myreg.test(this.UserInfo.email)) {
                    this.email_check_state=false;
                }else {
                    this.email_check_state=true;
                }
            },
        }
    }
</script>