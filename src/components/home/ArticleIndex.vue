<template>

    <mt-loadmore :top-method="loadTop" :bottom-method="loadBottom" :bottom-all-loaded="allLoaded" ref="loadmore">
        <mt-swipe :auto="4000" style="height: 200px">
            <mt-swipe-item>
                <img src="../../images/lunbo1.png" class="carousel_img">
            </mt-swipe-item>
            <mt-swipe-item>
                <img src="../../images/lunbo2.png" class="carousel_img">
            </mt-swipe-item>
            <mt-swipe-item>
                <img src="../../images/lunbo3.png" class="carousel_img">
            </mt-swipe-item>
        </mt-swipe>


        <div v-for="n in 10" class="article">
            <div v-on:click="toArticleDetail(articl_id)">
                <img src="../../images/code_1.jpg" class="article_img"/>
                <div class="article_font">ipho0ne X测评：用真正黑科技革新未来{{n}}</div>
            </div>
        </div>

    </mt-loadmore>

</template>

<script>
    export default {
        name: 'ArticleIndex',
        data() {
            return {}
        },
        methods: {
            toArticleDetail(articl_id) {
                this.$router.push({name: "ArticleDetail"})
            }
        }
    }
</script>

<style scoped lang="less">
    @import "../../less/my-article-index";
</style>