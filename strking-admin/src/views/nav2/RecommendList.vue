<template>
	<section>
		<!--工具条-->
		<el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
			<el-form :inline="true" :model="filters">
				<el-form-item>
					<el-input v-model="filters.name" placeholder="标题"></el-input>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" v-on:click="getRecommends">查询</el-button>
				</el-form-item>
			</el-form>
		</el-col>

		<!--列表-->
		<template>
			<el-table :data="recommends" highlight-current-row v-loading="loading" style="width: 100%;">

				<el-table-column prop="sid" label="ID" width="100">
				</el-table-column>
				<el-table-column prop="title" label="标题" width="150" >
				</el-table-column>
				<el-table-column prop="video_url" label="视频地址" width="350">
					<template slot-scope="scope">
						<!--<el-button type="warning" size="small" v-if="scope.row.status == 1"-->
								   <!--@click="clickChangeArticleStatus(scope.row.sid,2)">加精-->
						<!--</el-button>-->
						<a :href="scope.row.video_url">{{scope.row.video_url}}</a>
					</template>
				</el-table-column>
				<el-table-column prop="from_author" label="出自" width="150" >
				</el-table-column>
				<el-table-column prop="add_admin_name" label="创建人" width="150" >
				</el-table-column>
				<el-table-column prop="send_time" label="创建时间" width="150" sortable>
				</el-table-column>
				<el-table-column label="操作" width="200">
					<template slot-scope="scope">
						<el-button type="danger" size="small" @click="clickDelRecommend(scope.row.sid)">删除</el-button>
					</template>
				</el-table-column>
			</el-table>
		</template>
		<!--工具条-->
		<el-col :span="24" class="toolbar">
			<el-pagination layout="prev, pager, next" @current-change="recommendHandleCurrentChange"
						   :page-size="this.limit"
						   :total="total" style="float:right;">
			</el-pagination>
		</el-col>
	</section>
</template>
<script>
    import { recommendList,delRecommend } from '../../aapi/article';
    import util from "../../common/js/util";
    import RespStatus from "../../aapi/base/RespStatus";
    //import NProgress from 'nprogress'
    export default {
        data() {
            return {
                filters: {
                    name: ''
                },
                loading: false,
                recommends: [
                ],
                limit: 20,
                offset: 0,
                total: 0,
                page: 1,
            }
        },
        methods: {
            clickDelRecommend(sid){
                this.$confirm('确认删除该记录吗?', '提示', {
                    type: 'warning'
                }).then(() => {
                    delRecommend(sid).then(res => {
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
                        this.getRecommends();
                    })
                })
			},
            recommendHandleCurrentChange(val) {
                this.page = val;
                this.getRecommends();
            },

            getRecommends: function () {
                this.loading = true;
                this.offset = (this.page - 1) * this.limit;
                recommendList(this.filters.name, this.limit, this.offset).then((res) => {
                    console.log(res);
                    this.recommends = res.data.data.items;
                    this.loading = false;
                    this.total = res.data.data.totalCount;
                    for (let i = 0; i < this.recommends.length; i++) {
                        this.recommends[i].send_time = util.formatDate.format(new Date(this.recommends[i].send_time), 'yyyy-MM-dd')
                    }
                });
            }
        },
        mounted() {
            this.getRecommends();
        }
    };

</script>

<style scoped>

</style>