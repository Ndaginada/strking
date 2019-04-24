<template>
    <section>
        <!--工具条-->
        <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
            <el-form :inline="true" :model="filters">
                <el-form-item>
                    <el-input v-model="filters.name" placeholder="文章标题"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-select v-model="filters.type" placeholder="选择类型" clearable>
                        <el-option v-for="t in article_type" :key="t.sid" :label="t.content" :value="t.sid"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" v-on:click="getArticles">查询</el-button>
                </el-form-item>
            </el-form>
        </el-col>

        <!--列表-->
        <template>
            <el-table :data="articles" highlight-current-row v-loading="loading" style="width: 100%;">
                <el-table-column type="expand">
                    <template slot-scope="props">
                        <el-form label-position="left" inline class="demo-table-expand">
                            <el-form-item label="文章内容：">
                                <p style="word-break: break-all">{{ props.row.content }}</p>
                            </el-form-item>
                        </el-form>
                    </template>
                </el-table-column>
                <el-table-column prop="sid" label="ID" width="80">
                </el-table-column>
                <el-table-column
                        prop="title_img"
                        label="标题图片"
                        width="180">
                    <template slot-scope="scope">
                        <img :src="'http://localhost:4444/'+scope.row.title_img" alt=""
                             style="width: 88px;height: 50px">
                    </template>
                </el-table-column>
                <el-table-column prop="title" label="文章标题" width="200">
                </el-table-column>
                <el-table-column prop="status" label="文章状态" width="150" sortable>
                    <template slot-scope="scope">
                        <el-tag type="danger" v-if="scope.row.status == 2">精品</el-tag>
                        <el-tag type="info" v-if="scope.row.status == 1">普通</el-tag>
                    </template>
                </el-table-column>
                <el-table-column prop="type_content" label="文章分类" width="200">
                </el-table-column>
                <el-table-column prop="author_name" label="作者" width="150" sortable>
                </el-table-column>
                <el-table-column prop="send_time" label="发表时间" width="200" sortable>
                </el-table-column>
                <el-table-column prop="browse_num" label="浏览量" width="100" sortable>
                </el-table-column>
                <el-table-column prop="collect_num" label="收藏量" width="100" sortable>
                </el-table-column>
                <el-table-column label="操作" width="200">
                    <template slot-scope="scope">
                        <el-button type="warning" size="small" v-if="scope.row.status == 1"
                                   @click="clickChangeArticleStatus(scope.row.sid,2)">加精
                        </el-button>
                        <el-button type="info" size="small" v-if="scope.row.status == 2"
                                   @click="clickChangeArticleStatus(scope.row.sid,1)">取消加精
                        </el-button>
                        <el-button type="danger" size="small" @click="clickDelArticle(scope.row.sid)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>

            <!--工具条-->
            <el-col :span="24" class="toolbar">
                <el-pagination layout="prev, pager, next" @current-change="articleHandleCurrentChange"
                               :page-size="this.limit"
                               :total="total" style="float:right;">
                </el-pagination>
            </el-col>
        </template>

    </section>
</template>
<script>
    import {articleList, changeArticleStatus, delArticle} from '../../aapi/article';
    import {typeList} from '../../aapi/type';
    import util from "../../common/js/util";
    import RespStatus from "../../aapi/base/RespStatus";
    //import NProgress from 'nprogress'
    export default {
        data() {
            return {
                filters: {
                    name: '',
                    type: ''
                },
                loading: false,
                limit: 20,
                offset: 0,
                total: 0,
                page: 1,
                articles: [],
                article_type: []
            }
        },
        methods: {
            clickDelArticle(article_id) {
                this.$confirm('确认删除该记录吗?', '提示', {
                    type: 'warning'
                }).then(() => {
                    delArticle(article_id).then(res => {
                        if (res.data.code === RespStatus.OK.code) {
                            this.$message({
                                message: '操作成功!',
                                type: 'success'
                            });
                        } else {
                            this.$message({
                                message: '操作失败!',
                                type: 'error'
                            });
                        }
                        this.getArticles();
                    })
                })
            },
            clickChangeArticleStatus(article_id, status) {
                this.$confirm('确认操作吗?', '提示', {
                    type: 'warning'
                }).then(() => {
                    changeArticleStatus(article_id, status).then(res => {
                        if (res.data.code === RespStatus.OK.code) {
                            this.$message({
                                message: '操作成功!',
                                type: 'success'
                            });
                        } else {
                            this.$message({
                                message: '操作失败!',
                                type: 'error'
                            });
                        }
                        this.getArticles();
                    })
                })
            },
            articleHandleCurrentChange(val) {
                this.page = val;
                this.getArticles();
            },
            //获取文章列表
            getArticles: function () {
                this.loading = true;
                this.offset = (this.page - 1) * this.limit;
                //NProgress.start();
                articleList(this.filters.name, this.filters.type, this.limit, this.offset).then((res) => {
                    console.log(res.data.data);
                    this.loading = false;
                    this.articles = res.data.data.items;
                    this.total = res.data.data.totalCount;

                    for (let i = 0; i < this.articles.length; i++) {
                        this.articles[i].send_time = util.formatDate.format(new Date(this.articles[i].send_time), 'yyyy-MM-dd')
                    }
                });
            }
        },
        mounted() {
            this.getArticles();
            typeList().then(res => {
                this.article_type = res.data.data;
            })
        }
    };

</script>

<style scoped>

</style>