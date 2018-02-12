<template>
    <div class="mylogin">
        <div class="logo">
            <img src="../images/LOGO.png">
        </div>
        <mt-cell icon="back" title="用户名 :" class="input">
            <mt-field placeholder="请输入用户名" v-model="loginForm.username"></mt-field>
        </mt-cell>
        <mt-cell icon="back" title="密码 :" class="input">
            <mt-field placeholder="请输入密码" type="password" v-model="loginForm.password"></mt-field>
        </mt-cell>
        <div class="login-btn">
            <mt-button type="primary" v-on:click="checkUser">登录</mt-button>
            <router-link to="/register">
                <mt-button type="primary">注册</mt-button>
            </router-link>
        </div>

    </div>
</template>

<script>
    import {auth_login} from "../api/auth";
    import RespStatus from "../api/base/RespStatus"

    export default {
        name: 'Login',
        data() {
            return {
                loginForm: {
                    username: '',
                    password: '',
                }
            };
        },
        methods: {
            checkUser() {
                if (this.loginForm.username == '' || this.loginForm.password == '') {
                    this.Indicator.close();
                    this.Toast({
                        message: '用户名密码不能为空',
                        position: 'bottom',
                        duration: 3500
                    });
                    return null;
                } else {
                    this.Indicator.open({
                        text: '登录中...',
                        spinnerType: 'triple-bounces'
                    });
                    auth_login(this.loginForm.username, this.loginForm.password).then(res => {

                        if (res.data.code === RespStatus.OK.code) {
                            //获取token，存储在localStorage
                            var token = res.data.data.token_type + ' ' + res.data.data.access_token;
                            console.log(token);
                            window.localStorage.setItem("token",token);

                            this.$router.push({path:'/home'});
                            this.Indicator.close();
                        }
                        if (res.data.code === RespStatus.BAD_USER.code) {
                            this.Indicator.close();
                            this.Toast({
                                message: '用户名密码错误',
                                position: 'bottom',
                                duration: 3500
                            });
                        }
                    }).catch((err) => {
                        this.Indicator.close();
                        this.Toast({
                            message: '网络错误',
                            position: 'bottom',
                            duration: 3500
                        });
                        console.log(err)
                    })
                }

            }
        }
    };


</script>

<style scoped lang="less">
    @import "../less/my-login";
</style>