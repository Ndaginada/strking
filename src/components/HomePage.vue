<template>
    <div>
        <mt-cell>
            <mt-header fixed title="Strking" class="heard"></mt-header>
        </mt-cell>

        <mt-tab-container v-model="selected">
            <!--主页-->
            <mt-tab-container-item id="主页">
                <article_index></article_index>
            </mt-tab-container-item>
            <!--消息-->
            <mt-tab-container-item id="关注">
                <h1>关注</h1>
            </mt-tab-container-item>

            <!--商城-->
            <mt-tab-container-item id="发现">
                <h1>发现</h1>
            </mt-tab-container-item>

            <!--我的-->
            <mt-tab-container-item id="我的">
                <me></me>

            </mt-tab-container-item>
        </mt-tab-container>

        <mt-cell>
        <mt-tabbar v-model="selected" fixed>
            <mt-tab-item id="主页">
                <img v-if="selected==='主页'" slot="icon" src="../images/home_active.png">
                <img v-else slot="icon" src="../images/home.png">
                主页
            </mt-tab-item>
            <mt-tab-item id="关注">
                <img v-if="selected==='关注'" slot="icon" src="../images/message_active.png">
                <img v-else slot="icon" src="../images/message.png">
                关注
            </mt-tab-item>
            <mt-tab-item id="发现">
                <img v-if="selected==='发现'" slot="icon" src="../images/cart_active.png">
                <img v-else slot="icon" src="../images/cart.png">
                发现
            </mt-tab-item>
            <mt-tab-item id="我的">
                <img v-if="selected==='我的'" slot="icon" src="../images/me_active.png">
                <img v-else slot="icon" src="../images/me.png">
                我的
            </mt-tab-item>
        </mt-tabbar>
        </mt-cell>
    </div>
</template>

<script>
    import {test_token} from "../api/home"
    import article_index from "./home/ArticleIndex.vue"
    import me from "./home/Me.vue"
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
        components:{
            article_index,
            me
        },
        mounted(){

            const selected = this.$route.query.selected;
            if (selected != null){
                this.selected = selected;
            }

        }
    };
</script>

<style scoped lang="less">
    @import "../less/my-home";
</style>