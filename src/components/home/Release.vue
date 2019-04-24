<template>
    <div>
        <mt-cell title="标题图片" >
            <input class="avatar" name="file" type="file" accept="image/png,image/gif,image/jpeg" @change="article_img_uplode" style="width: 30%;margin-left: 46%"/>

            <img  v-if="article_img_is_uplode" :src="'http://localhost:4444/'+Article.title_img"  width="100" height="100">
            <img v-if="!article_img_is_uplode" :src="'http://localhost:4444/'+Article.title_img"  width="100" height="100" >
        </mt-cell>
        <hr>
        <mt-field label="标题" placeholder="请输入标题" v-model="Article.title"></mt-field>
        <hr>
       <mt-cell title="分类">
           <select style="margin-right: 50px;width: 200px;text-align: center;font-size: 1rem"  id="article_type">
               <option v-for="t in article_type" :value="t.sid">{{t.content}}</option>
           </select>
       </mt-cell>
        <hr>

        <textarea rows="15" class="editor" placeholder="说点什么吧..."  v-model="Article.content"></textarea>
        <mt-button style="width: 100%" type="primary" plain v-on:click="submit">发表</mt-button>
    </div>
</template>

<style scoped lang="less">
    @import "../../less/my-release";
</style>

<script>

    import MtField from "../../../node_modules/mint-ui/packages/field/src/field.vue";
    import MtCell from "../../../node_modules/mint-ui/packages/cell/src/cell.vue";
    import MtButton from "../../../node_modules/mint-ui/packages/button/src/button.vue";
    import { MessageBox } from 'mint-ui';
    import {uplode_article_img,addArticle} from "../../api/article";
    import {typeList} from "../../api/type";
    import RespStatus from "../../api/base/RespStatus";
    export default {
        name: 'Release',


        data() {
            return {
                Article:{
                    sid:'',
                    content:'',
                    title_img:'' ,
                    send_time:null,
                    browse_num:'' ,
                    author_id:'' ,
                    status:'',
                    type_id:1,
                    title:'',
                },
                article_type:[],
                article_img_is_uplode: false,
            }
        },

        props: {},

        components: {
            MtButton,
            MtCell,
            MtField},

        computed: {

        },
        mounted(){
            typeList().then(res=>{
                this.article_type = res.data.data;
            })
        },

        methods: {
            //头像上传
            article_img_uplode(e){
                let file = e.target.files[0];
                let param = new FormData(); //创建form对象
                let file_name = 'kkai' + file.name;
                param.append('file',file,file_name);//通过append向form对象添加数据
                //param.append('chunk','0');//添加form表单中其他数据
                //axios向后端发送请求
                uplode_article_img(param).then(res=>{
                    if (res.data.data != null || res.data.data != ''){
                        this.article_img_is_uplode = true;
                        this.Article.title_img = 'img/articleTitleImg_'+file_name;    //修改图片
                    }
                });
            },

            submit(){
                const article_type = document.getElementById('article_type').value;
                this.Article.type_id = article_type;
                if (this.Article.content == '') {
                    MessageBox.alert("请填写内容");
                    return false;
                }
                if (this.Article.title == '') {
                    MessageBox.alert("请填写标题");
                    return false;
                }
                if (this.Article.title_img == '') {
                    MessageBox.alert("请选择标题图片");
                    return false;
                }
                addArticle(this.Article.content,this.Article.title,this.Article.title_img,this.Article.type_id).then(res=>{
                    if (res.data.code == RespStatus.OK.code){
                        MessageBox.alert('发布成功!').then(()=>{
                            this.$router.push({path:'/home',query:{selected:'主页'}});
                            location.reload();
                        });
                    }else {
                        MessageBox.alert('发布失败!');
                    }
                })
            }
        },

        watch: {},

        filters: {},


    }
</script>
