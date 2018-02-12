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
                <mt-cell title="邮箱 :">
                    <label>
                        <input type="email" v-on:blur="inputCheck" v-model="RegisterForm.email"/>
                    </label>
                </mt-cell>
                <mt-field label="邮箱" placeholder="请输入邮箱" type="email"   v-on:blur="inputCheck"></mt-field>
                <hr/>
                <mt-field label="手机号" placeholder="请输入手机号(选填)" type="tel" v-model="RegisterForm.tel"></mt-field>
                <hr/>
                <mt-field label="自我介绍" placeholder="自我介绍" type="textarea" rows="3"
                          v-model="RegisterForm.intro"></mt-field>
                <hr/>
            </form>
        </div>
        <div class="sub-btn">
            <mt-button type="primary" size="large" >确认注册</mt-button>
        </div>

    </div>
</template>

<script>

    import MtCell from "../../node_modules/mint-ui/packages/cell/src/cell.vue";

    export default {
        components: {MtCell},
        name: 'Register',
        data() {
            return {
                RegisterForm: {
                    username: '',
                    password: '',
                    email: '',
                    tel: '',
                    intro: ''
                }
            }
        },
        methods: {

            inputCheck(){
                var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
                if (!myreg.test(this.RegisterForm.email)) {
                    alert('提示\n\n请输入有效的E_mail！');
                    return false;
                }else {
                    return true;
                }
            }
        }

    };
</script>

<style scoped lang="less">
    @import "../less/my-login";
</style>