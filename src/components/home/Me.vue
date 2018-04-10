<template>
    <div>
        <!--<mt-cell title="头像">-->
            <!--<input class="avatar" name="file" type="file" accept="image/png,image/gif,image/jpeg" @change="img_uplode"/>-->
            <!--<img v-if="is_uplode" :src="'http://localhost:4444/'+UserInfo.icon" id="heard_icon" width="100" height="100">-->
        <!--</mt-cell>-->
        <div class="userInfo_heard" style="background-image: url('../../images/backgroud.jpg')">
            <img :src ="'http://localhost:4444/'+UserInfo.icon"  class="head_icon">
            <h3 style="text-align: center">{{UserInfo.username}}</h3>
            <h5 style="text-align: center;">等级：<mt-badge size="small">{{UserInfo.user_leave}}</mt-badge></h5>
            <h5 style="text-align: center;color: dimgrey">{{UserInfo.introduce}}</h5>
        </div>

        <router-link :to="{path:'/changeInfo'}"   >
            <mt-button plain class="menu_button">修改个人信息</mt-button>
        </router-link>
        <router-link :to="{path:'/changePass'}">
            <mt-button plain class="menu_button" >修改密码</mt-button>
        </router-link>

        <mt-button type="danger" size="large" @click="logout()" style="margin-top: 15px">退出登录</mt-button>
    </div>
</template>

<style scoped lang="less">
    @import "../../less/my-me";
</style>

<script>
    import MtButton from "../../../node_modules/mint-ui/packages/button/src/button.vue";
    import MtCell from "../../../node_modules/mint-ui/packages/cell/src/cell.vue";
    import {userInfo} from "../../api/user"
    export default {
        name: 'Me',

        data() {
            return {
                UserInfo:{
                    id: null,
                    username: '用户名',
                    password: '',
                    icon: '',
                    email: '',
                    introduce: '',
                    experience:'',
                    user_leave:'',
                },

                imageUrl: 'http://localhost:4444/img/headIcon_default.png',
            }
        },

        props: {},

        components: {
            MtCell,
            MtButton},

        mounted() {
            const user_id = window.localStorage.getItem("strking_user_id");
            //调用获取用户信息接口
            userInfo(user_id).then(res=>{
                this.UserInfo.username = res.data.data.username;
                this.UserInfo.icon = res.data.data.icon;
                this.UserInfo.email = res.data.data.email;
                this.UserInfo.introduce = res.data.data.introduce;
                this.UserInfo.password = res.data.data.password;
                this.UserInfo.experience = res.data.data.experience;

                this.UserInfo.user_leave = parseInt(this.UserInfo.experience / 100);
            })
        },

        watch: {},

        computed: {},

        filters: {},

        methods: {
            logout(){
                window.localStorage.clear();
                this.$router.push({path:'/login'})
            },
        }
    }
</script>