<template>
    <div class="mylogin">
        <div class="logo">
            <img src="../images/LOGO.png">
        </div>

        <mt-field label="用户名" placeholder="请输入用户名" v-model="loginForm.username"></mt-field>
        <mt-field label="密码" placeholder="请输入密码" type="password" v-model="loginForm.password"></mt-field>
        <div class="login-btn">
            <mt-button type="primary" v-on:click="checkUser">登录</mt-button>
            <router-link to="/register">
                <mt-button type="primary">注册</mt-button>
            </router-link>
        </div>

    </div>
</template>

<script>
    import axios from 'axios'


    export default {
        name: 'Login',
        data() {
            return {
                loginForm: {
                    username: '',
                    password: '',
                },
                clearEye: true,
                remember: true,
            };
        },
        methods: {
            checkUser() {
                axios.post('http://localhost:3333/microservice-provider-user/user/',this.loginForm.username,this.loginForm.password).then(function (response) {
                    console.log(response)
                });
//                 axios.get('http://localhost:3333/microservice-provider-user/user/1')
//                .then(function (response) {
//                    console.log(response);
//                })
//                .catch(function (error) {
//                    console.log(error);
//                });
//                console.log("11111111111111")
            }
        }
    };


</script>

<style scoped lang="less">
    @import "../less/my-login";
</style>