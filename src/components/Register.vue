<template>

    <div class="myregister">

        <mt-header title="注册" class="myregister">
            <router-link to="/login" slot="left">
                <mt-button icon="back">返回</mt-button>
            </router-link>
        </mt-header>
        <div class="formdata">
            <form>
                <mt-field label="用户名" placeholder="请输入用户名" v-model="RegisterForm.username"></mt-field>
                <hr/>
                <mt-field label="密码" placeholder="请输入密码" type="password" v-model="RegisterForm.password"></mt-field>
                <hr/>
                <!--<mt-cell title="邮箱 :">-->
                    <!--<label>-->
                        <!--<input type="email"  />-->
                    <!--</label>-->
                <!--</mt-cell>-->
                <mt-field v-if="email_check_state" label="邮箱" placeholder="请输入邮箱" type="email" v-model="RegisterForm.email" @blur.native.capture="inputCheck" state="success"></mt-field>
                <mt-field v-if="!email_check_state" label="邮箱" placeholder="请输入邮箱" type="email" v-model="RegisterForm.email" @blur.native.capture="inputCheck" state="warning"></mt-field>
                <hr/>
                <!--<mt-field label="手机号" placeholder="请输入手机号(选填)" type="tel" v-model="RegisterForm.tel"></mt-field>-->
                <!--<hr/>-->
                <mt-field label="自我介绍" placeholder="自我介绍" type="textarea" rows="3"
                          v-model="RegisterForm.intro"></mt-field>
                <hr/>
            </form>
        </div>
        <div class="sub-btn">
            <mt-button type="primary" size="large" v-on:click="subRegister">确认注册</mt-button>
        </div>

    </div>
</template>

<script>

    import MtCell from "../../node_modules/mint-ui/packages/cell/src/cell.vue";

    import { MessageBox } from 'mint-ui';

    export default {
        components: {MtCell},
        name: 'Register',
        data() {
            return {
                RegisterForm: {
                    username: '',
                    password: '',
                    email: '',
                    intro: ''
                },
                email_check_state:false,
            }
        },
        methods: {

            inputCheck(){
                var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
                if (!myreg.test(this.RegisterForm.email)) {
                    this.email_check_state=false;
                }else {
                    this.email_check_state=true;
                }
            },

            subRegister(){
                //前端输入校验
                if (this.RegisterForm.username==''){
                    MessageBox.alert("用户名不能为空");
                    return false;
                }else {
                    var arr = new Array();
                    arr = this.RegisterForm.username.split(" ");
                    if(arr.length != 1){
                        MessageBox.alert("用户名中不能含有空格！");
                        return false;
                    }
                }
                if (this.RegisterForm.password==''){
                    MessageBox.alert("密码不能为空");
                    return false;
                }else {
                    var arr = new Array();
                    arr = this.RegisterForm.password.split(" ");
                    if(arr.length != 1){
                        MessageBox.alert("密码中不能含有空格！");
                        return false;
                    }
                }
                if (this.email_check_state == false){
                    MessageBox.alert('邮箱格式错误');
                    return false;
                }

                //注册api调用

            }
        },
    };


</script>

<style scoped lang="less">
    @import "../less/my-login";
</style>