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
            <!--发布-->
            <mt-tab-container-item id="发布">
                <release></release>
            </mt-tab-container-item>

            <!--推荐-->
            <mt-tab-container-item id="推荐">
                <recommend></recommend>
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
            <mt-tab-item id="发布">
                <img v-if="selected==='发布'" slot="icon" src="../images/message_active.png">
                <img v-else slot="icon" src="../images/message.png">
                发布
            </mt-tab-item>
            <mt-tab-item id="推荐">
                <img v-if="selected==='推荐'" slot="icon" src="../images/cart_active.png">
                <img v-else slot="icon" src="../images/cart.png">
                推荐
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
    import recommend from "./home/Recommend.vue"
    import release from "./home/Release.vue"
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
            release,
            me,
            recommend,
        },
        mounted(){

            const selected = this.$route.query.selected;
            console.log(selected);
            if (selected != null){
                this.selected = selected;
            }

        }
    };
</script>

<style scoped lang="less">
    @import "../less/my-home";
</style>