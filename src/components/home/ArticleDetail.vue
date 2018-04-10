<template>
    <div>
        <mt-cell>
            <mt-header fixed title="Strking" class="heard">
                <router-link to="/home" slot="left">
                    <mt-button icon="back">返回</mt-button>
                </router-link>
            </mt-header>
        </mt-cell>

        <div class="title">
            <h1>{{Article.title}}</h1>
            <span>{{Article.send_time}} </span>
            <span>——{{author_name}}</span>
        </div>
        <img :src="'http://localhost:4444/'+Article.title_img" height="200" width="100%" style="margin-top: 10px">
        <div class="context">

            <p>{{Article.content}}</p>
        </div>
        <hr>
        <div class="revert">
            <div v-if="this.Comment.length == 0">
                <h2 style="text-align: center;margin: 2rem">还没有人回复呢</h2>
            </div>

            <div class="revert_detail" v-for="c in Comment">
                <mt-cell :title="c.user_name" style="border: 3em black" >
                    <img slot="icon" :src="'http://localhost:4444/'+c.user_icon" width="50" height="50">
                    <span>{{c.send_time}}</span>
                </mt-cell>
                <mt-badge size="small" v-if="c.user_id == Article.author_id">作者回复</mt-badge>
                <p style="text-indent: 8%;font-size: medium">{{c.content}}</p>
                <div @click="click_zan()" v-if="!is_zan">
                    <img src="../../images/zan.png" width="20" height="20" style="margin-left: 80%"/>
                    <span>{{c.praise_num}}</span>
                </div>
                <div v-else>
                    <img src="../../images/zan_active.png" width="20" height="20" style="margin-left: 80%"/>
                    <span>{{c.praise_num}}</span>
                </div>
                <hr>
            </div>
        </div>
        <div>
            <mt-cell title="回复" style="background: coral">
                <img slot="icon" src="../../images/comment.png" width="24" height="24">
                <input style="height: 2rem" v-model="comment_content"/>
                <mt-button v-on:click="send_comment">发送</mt-button>
            </mt-cell>
        </div>
    </div>
</template>

<script>

    import MtCell from "../../../node_modules/mint-ui/packages/cell/src/cell.vue";
    import {MessageBox} from 'mint-ui';
    import {articleDetails} from "../../api/article"
    import {addComment, articleCommentList} from "../../api/comment"
    import {userInfo} from "../../api/user"
    import MtField from "../../../node_modules/mint-ui/packages/field/src/field.vue";
    import MtButton from "../../../node_modules/mint-ui/packages/button/src/button.vue";
    import RespStatus from "../../api/base/RespStatus";
    import MtBadge from "../../../node_modules/mint-ui/packages/badge/src/badge.vue";

    export default {
        name: 'ArticleDetail',

        data() {
            return {
                is_zan: '',
                num_zan: 0,
                author_name: '1',
                Article: {
                    sid: '',
                    content: '',
                    title_img: '',
                    send_time: null,
                    browse_num: '',
                    author_id: '',
                    status: '',
                    type_id: 1,
                    title: '',
                },
                comment_content: '',
                Comment: []
            }
        },

        props: {},

        components: {
            MtBadge,
            MtButton,
            MtField,
            MtCell
        },

        watch: {},

        computed: {},

        filters: {},

        methods: {
            click_zan() {
                this.is_zan = true;
                this.num_zan++;

            },
            send_comment() {
                //发送回复
                addComment(this.Article.sid, this.comment_content).then(res => {
                    if (res.data.code == RespStatus.OK.code) {
                        MessageBox.alert('回复成功!').then(() => {
                            location.reload();
                        });
                    } else {
                        MessageBox.alert('发布失败!');
                    }
                })
            }
        },
        mounted() {
            this.Indicator.open({
                text: '加载中...',
                spinnerType: 'triple-bounces'
            });
            articleDetails(this.$route.query.article_id).then(res => {
                this.Indicator.close();
                if (res.data.code === 1) {
                    this.Article = res.data.data;
                    //时间戳转换
                    var date = new Date(this.Article.send_time);
                    var y = 1900 + date.getYear();
                    var m = "0" + (date.getMonth() + 1);
                    var d = "0" + date.getDate();
                    var s = "0" + date.getHours();
                    var f = "0" + date.getMinutes();
                    this.Article.send_time = y + "-" + m.substring(m.length - 2, m.length) + "-" + d.substring(d.length - 2, d.length) + " "
                        + s.substring(s.length - 2, s.length) + ":" + f.substring(f.length - 2, f.length);

                    userInfo(this.Article.author_id).then(res => {
                        this.author_name = res.data.data.username;
                    })
                } else {
                    this.Toast('请求失败');
                }
            }).catch((err) => {
                this.Indicator.close();
                console.log(err);
            });
            articleCommentList(this.$route.query.article_id).then(res => {

                if (res.data.code === 1) {
                    this.Comment = res.data.data.items;

                    for (let i = 0; i < this.Comment.length; i++) {
                        let date = new Date(this.Comment[i].send_time);
                        var m = "0" + (date.getMonth() + 1);
                        var d = "0" + date.getDate();

                        var H = date.getHours();
                        var M = date.getMinutes();

                        this.Comment[i].send_time = m.substring(m.length - 2, m.length) + "-" +
                            d.substring(d.length - 2, d.length)+" "+ H + ":" + M;

                    }
                } else {
                    this.Toast('请求失败');
                }
            })
        }
    }
</script>

<style scoped lang="less">
    @import "../../less/my-article-detail";
</style>