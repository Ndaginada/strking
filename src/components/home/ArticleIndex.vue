<template>

    <mt-loadmore :top-method="loadTop" :bottom-method="loadBottom" :bottom-all-loaded="allLoaded" ref="loadmore">

        <button v-for="t in article_type" v-on:click="articleListForType(t.sid)">{{t.content}}</button>

        <div v-for="a in article" class="article">
            <div v-on:click="toArticleDetail(a.sid)">
                <img :src="'http://localhost:4444/'+a.title_img" class="article_img"/>

                <div class="article_font">
                    <mt-badge size="normal" type="error" v-if="a.status == 2">精品</mt-badge>
                    <mt-badge size="normal" type="success">{{a.type_content}}</mt-badge>  {{a.title}}
                </div>
                <span style="font-size: x-small;color: dimgrey">发送于：{{a.send_time}}</span>
                <span style="font-size: x-small;color: dimgrey">浏览量：{{a.browse_num}}</span>

            </div>
        </div>

    </mt-loadmore>

</template>

<script>
    import {articleList} from '../../api/article'
    import {typeList,getTypeContent} from "../../api/type";
    import MtButton from "../../../node_modules/mint-ui/packages/button/src/button.vue";

    export default {
        components: {MtButton},
        name: 'ArticleIndex',
        data() {
            return {
                article: [],
                article_type:[],
            }
        },
        methods: {
            toArticleDetail(article_id) {
                this.$router.push({name: "ArticleDetail", query: {"article_id": article_id}})
            },
            loadTop() {
                // 加载更多数据
                this.$refs.loadmore.onTopLoaded();
            },
            articleListForType(type_id){
                articleList(type_id).then(res=>{
                    if (res.data.code === 1) {
                        this.article = res.data.data.items;
                        for (let i = 0; i < this.article.length; i++) {
                            let date = new Date(this.article[i].send_time);
                            var y = 1900 + date.getYear();
                            var m = "0" + (date.getMonth() + 1);
                            var d = "0" + date.getDate();

                            this.article[i].send_time = y + "-" + m.substring(m.length - 2, m.length) + "-" + d.substring(d.length - 2, d.length);

                        }
                    } else {
                        this.Toast({
                            message: '网络错误',
                            position: 'bottom',
                            duration: 3500
                        });
                    }
                })
            },
        },
        mounted() {
            this.Indicator.open({
                text: '加载中...',
                spinnerType: 'triple-bounces'
            });
            typeList().then(res=>{
                this.Indicator.close();
                this.article_type = res.data.data;
                articleList().then(res => {
                    this.Indicator.close();
                    console.log(res);
                    if (res.data.code === 1) {
                        this.article = res.data.data.items;
                        for (let i = 0; i < this.article.length; i++) {
                            let date = new Date(this.article[i].send_time);
                            var y = 1900 + date.getYear();
                            var m = "0" + (date.getMonth() + 1);
                            var d = "0" + date.getDate();

                            this.article[i].send_time = y + "-" + m.substring(m.length - 2, m.length) + "-" + d.substring(d.length - 2, d.length);
                        }
                    } else {
                        this.Toast({
                            message: '网络错误',
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
                    console.log(err);
                })
            });


        },
    }
</script>

<style scoped lang="less">
    @import "../../less/my-article-index";
</style>