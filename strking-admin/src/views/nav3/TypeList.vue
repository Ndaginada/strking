<template>
	<section>
		<!--工具条-->
		<el-col :span="24" class="toolbar" style="padding-bottom: 0px;" >
			<el-form :inline="true" ref="addTypeForm" :model="addTypeForm" :rules="addTypeFormRules">
				<el-form-item prop="name">
					<el-input v-model="addTypeForm.name" placeholder="标题"></el-input>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" v-on:click="clickAddType">添加</el-button>
				</el-form-item>
			</el-form>
		</el-col>

		<!--列表-->
		<template>
			<el-table :data="types" highlight-current-row v-loading="loading" style="width: 100%;">

				<el-table-column prop="type_id" label="ID" width="300">
				</el-table-column>
				<el-table-column prop="type_content" label="分类" width="300" >
				</el-table-column>
				<el-table-column prop="under_num" label="分类下文章数量" width="300" sortable>
				</el-table-column>
				<el-table-column label="操作" width="300">
					<template slot-scope="scope">
						<el-button type="danger" size="small" @click="clickDelRecommend(scope.row.type_id)">删除</el-button>
					</template>
				</el-table-column>
			</el-table>
		</template>
		<!--工具条-->
		<!--<el-col :span="24" class="toolbar">-->
			<!--<el-pagination layout="prev, pager, next" @current-change="recommendHandleCurrentChange"-->
						   <!--:page-size="this.limit"-->
						   <!--:total="total" style="float:right;">-->
			<!--</el-pagination>-->
		<!--</el-col>-->
	</section>
</template>
<script>
    import { typeList,countUnderArticle,delType,addType} from '../../aapi/type';
    import util from "../../common/js/util";
    import RespStatus from "../../aapi/base/RespStatus";
    //import NProgress from 'nprogress'
    export default {
        data() {
            return {
                addTypeForm: {
                    name: ''
                },
                loading: false,
                types: [
                ],
                addTypeFormRules: {
                    name: [
                        {required: true, message: '请输入标题', trigger: 'blur'}
                    ]
                },
            }
        },
        methods: {
            clickAddType(){
                this.$refs.addTypeForm.validate((valid) => {
                    if (valid) {
                        this.$confirm('确认添加该记录吗?', '提示', {
                            type: 'warning'
                        }).then(() => {
                            addType(this.addTypeForm.name).then(res => {
                                if (res.data.code === RespStatus.OK.code) {
                                    this.$message({
                                        message: '添加成功!',
                                        type: 'success'
                                    });
                                } else {
                                    this.$message({
                                        message: '添加失败!',
                                        type: 'error'
                                    });
                                }
                                this.getTypes();
                            })
                        })
                    }
                })


			},
            clickDelRecommend(sid){
                this.$confirm('确认删除该记录吗?', '提示', {
                    type: 'warning'
                }).then(() => {
                    delType(sid).then(res => {
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
                        this.getTypes();
                    })
                })
            },
//            recommendHandleCurrentChange(val) {
//                this.page = val;
//                this.getTypes();
//            },

            getTypes: function () {
                this.loading = true;

                countUnderArticle().then((res) => {
                    console.log(res);
                    this.types = res.data.data;
                    this.loading = false;
                });
            }
        },
        mounted() {
            this.getTypes();
        }
    };

</script>

<style scoped>

</style>