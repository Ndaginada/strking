<template>
	<section>
		<!--工具条-->
		<el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
			<el-form :inline="true" :model="filters">
				<el-form-item>
					<el-input v-model="filters.name" placeholder="用户名"></el-input>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" v-on:click="getArticles">查询</el-button>
				</el-form-item>
			</el-form>
		</el-col>

		<!--列表-->
		<template>
			<el-table :data="recommends" highlight-current-row v-loading="loading" style="width: 100%;">
				<el-table-column prop="sid" label="ID" width="150">
				</el-table-column>
				<el-table-column prop="username" label="用户名" width="300" >
				</el-table-column>
				<el-table-column prop="email" label="邮箱" width="300">
				</el-table-column>
				<el-table-column prop="reg_time" label="创建时间" width="300" sortable>
				</el-table-column>
			</el-table>
		</template>

	</section>
</template>
<script>
	import { adminList } from '../../aapi/user';
    import util from "../../common/js/util";
	//import NProgress from 'nprogress'
	export default {
		data() {
			return {
				filters: {
					name: ''
				},
				loading: false,
				recommends: [
				]
			}
		},
		methods: {
			//获取用户列表
			getArticles: function () {
				this.loading = true;
				//NProgress.start();
				adminList(this.filters.name).then((res) => {
                    this.recommends = res.data.data.items;
					this.loading = false;
                    for (let i = 0; i < this.recommends.length; i++) {
                        this.recommends[i].reg_time = util.formatDate.format(new Date(this.recommends[i].reg_time), 'yyyy-MM-dd')
                    }
				});
			}
		},
		mounted() {
			this.getArticles();
		}
	};

</script>

<style scoped>

</style>