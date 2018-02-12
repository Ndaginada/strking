<template>
    <div>
        <mt-cell>
            <mt-header fixed title="Strking" class="heard"></mt-header>
        </mt-cell>

        <mt-tab-container v-model="selected">
            <!--主页-->
            <mt-tab-container-item id="主页">
                <mt-swipe :auto="4000" style="height: 200px">
                    <mt-swipe-item>
                        <img src="../images/lunbo1.png" class="carousel_img">
                    </mt-swipe-item>
                    <mt-swipe-item>
                        <img src="../images/lunbo2.png" class="carousel_img">
                    </mt-swipe-item>
                    <mt-swipe-item>
                        <img src="../images/lunbo3.png" class="carousel_img">
                    </mt-swipe-item>
                </mt-swipe>
            </mt-tab-container-item>
            <!--消息-->
            <mt-tab-container-item id="消息">
                <h1>消息</h1>
            </mt-tab-container-item>

            <!--商城-->
            <mt-tab-container-item id="商城">
                <h1>商城</h1>
            </mt-tab-container-item>

            <!--我的-->
            <mt-tab-container-item id="我的">
                <h1>{{me}}</h1>
            </mt-tab-container-item>
        </mt-tab-container>

        <mt-tabbar v-model="selected">
            <mt-tab-item id="主页">
                <img v-if="selected==='主页'" slot="icon" src="../images/home_active.png">
                <img v-else slot="icon" src="../images/home.png">
                主页
            </mt-tab-item>
            <mt-tab-item id="消息">
                <img v-if="selected==='消息'" slot="icon" src="../images/message_active.png">
                <img v-else slot="icon" src="../images/message.png">
                消息
            </mt-tab-item>
            <mt-tab-item id="商城">
                <img v-if="selected==='商城'" slot="icon" src="../images/cart_active.png">
                <img v-else slot="icon" src="../images/cart.png">
                商城
            </mt-tab-item>
            <mt-tab-item id="我的">
                <img v-if="selected==='我的'" slot="icon" src="../images/me_active.png">
                <img v-else slot="icon" src="../images/me.png">
                我的
            </mt-tab-item>
        </mt-tabbar>

    </div>
</template>

<script>
    import {test_token} from "../api/home"

    export default {
        name: 'Home',
        data() {
            return {
                selected: '主页',
                me: ''
            };
        },
        methods: {

        },
        mounted(){
//            test_token().then(res=>{
//                this.me = res.data;
//            })
        }
    };
</script>

<style scoped lang="less">
    @import "../less/my-home";
</style>