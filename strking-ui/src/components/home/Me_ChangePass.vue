<template>
    <div>
        <mt-cell>
            <mt-header fixed title="Strking" class="heard">
                <router-link :to="{path:'/home',query:{selected:'我的'}}" slot="left">
                    <mt-button icon="back">返回</mt-button>
                </router-link>
            </mt-header>
        </mt-cell>

        <mt-field type="password" label="新密码" placeholder="请输入新密码" v-model="newpass"></mt-field>
        <hr>
        <mt-field type="password" label="确认密码" placeholder="请确认密码" v-model="enter_pass"></mt-field>
        <hr>
        <mt-field type="password" label="旧密码" placeholder="请输入旧密码" v-model="pass"></mt-field>
        <hr>
        <mt-field label="验证码" v-model="auth_code" placeholder="请输入验证码">
            <mt-button v-on:click="sendCodeSubmit" style="height:45px;width:100px;font-size: 1rem" plain>获取邮箱验证码
            </mt-button>
        </mt-field>
        <mt-button size="large" type="primary" style="margin-top: 55px" v-on:click="submit">确认修改</mt-button>

    </div>
</template>

<style scoped lang="less">
    @import "../../less/my-me";
</style>
<script>
    import MtButton from "../../../node_modules/mint-ui/packages/button/src/button.vue";
    import {MessageBox} from 'mint-ui';
    import {sendCode} from "../../api/email"
    import {changePass} from "../../api/user"
    import RespStatus from "../../api/base/RespStatus";
    import MtField from "../../../node_modules/mint-ui/packages/field/src/field.vue";

    export default {
        name: 'Me_ChangePass',

        data() {
            return {
                pass: '',
                newpass: '',
                enter_pass: '',
                auth_code: '',
                true_code: ''
            }
        },

        props: {},

        components: {
            MtField,
            MtButton
        },

        mounted() {

        },

        watch: {},

        computed: {},

        filters: {},

        methods: {

            sendCodeSubmit() {
                sendCode(window.localStorage.getItem("strking_user_id")).then((res) => {
                    if (res.data.data != null) {
                        this.true_code = res.data.data;
                    }
                })
            },

            submit() {
                if (this.newpass == '' || this.newpass == null) {
                    MessageBox.alert("密码不能为空！");
                    return false;
                }
                if (this.newpass !== this.enter_pass) {
                    MessageBox.alert("输入的密码不一致！");
                    return false;
                }
                if (this.auth_code == '' || this.auth_code == null) {
                    MessageBox.alert("请输入验证码！");
                    return false;
                }
                if (this.auth_code !== this.true_code) {
                    MessageBox.alert("验证码错误！");
                    return false;
                }

                changePass(this.pass, this.newpass).then(res => {
                    console.log(res);
                    if (res.data.code == RespStatus.OK.code) {
                        MessageBox.alert('修改成功!').then(() => {
                            this.$router.push({path: '/home', query: {selected: '我的'}});
                        });
                    }
                    if (res.data.code == RespStatus.BAD_REQUEST.code) {
                        MessageBox.alert("密码输入错误！");
                    }

                }).catch((err) => {
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
</script>