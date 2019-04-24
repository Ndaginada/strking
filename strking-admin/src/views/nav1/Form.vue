<template>
	<el-form ref="form" :model="form" label-width="80px" :rules="addAdminFormRules" @submit.prevent="onSubmit" style="margin:20px;width:60%;min-width:600px;">
		<el-form-item label="用户名" prop="username">
			<el-input v-model="form.username"></el-input>
		</el-form-item>
		<el-form-item label="密码" prop="password">
			<el-input v-model="form.password"></el-input>
		</el-form-item>
		<el-form-item label="邮箱" prop="email">
			<el-input v-model="form.email"></el-input>
		</el-form-item>
		<!--<el-form-item label="活动区域">-->
			<!--<el-select v-model="form.region" placeholder="请选择活动区域">-->
				<!--<el-option label="区域一" value="shanghai"></el-option>-->
				<!--<el-option label="区域二" value="beijing"></el-option>-->
			<!--</el-select>-->
		<!--</el-form-item>-->
		<!--<el-form-item label="活动时间">-->
			<!--<el-col :span="11">-->
				<!--<el-date-picker type="date" placeholder="选择日期" v-model="form.date1" style="width: 100%;"></el-date-picker>-->
			<!--</el-col>-->
			<!--<el-col class="line" :span="2">-</el-col>-->
			<!--<el-col :span="11">-->
				<!--<el-time-picker type="fixed-time" placeholder="选择时间" v-model="form.date2" style="width: 100%;"></el-time-picker>-->
			<!--</el-col>-->
		<!--</el-form-item>-->
		<!--<el-form-item label="即时配送">-->
			<!--<el-switch on-text="" off-text="" v-model="form.delivery"></el-switch>-->
		<!--</el-form-item>-->
		<!--<el-form-item label="活动性质">-->
			<!--<el-checkbox-group v-model="form.type">-->
				<!--<el-checkbox label="美食/餐厅线上活动" name="type"></el-checkbox>-->
				<!--<el-checkbox label="地推活动" name="type"></el-checkbox>-->
				<!--<el-checkbox label="线下主题活动" name="type"></el-checkbox>-->
				<!--<el-checkbox label="单纯品牌曝光" name="type"></el-checkbox>-->
			<!--</el-checkbox-group>-->
		<!--</el-form-item>-->
		<!--<el-form-item label="特殊资源">-->
			<!--<el-radio-group v-model="form.resource">-->
				<!--<el-radio label="线上品牌商赞助"></el-radio>-->
				<!--<el-radio label="线下场地免费"></el-radio>-->
			<!--</el-radio-group>-->
		<!--</el-form-item>-->
		<!--<el-form-item label="活动形式">-->
			<!--<el-input type="textarea" v-model="form.desc"></el-input>-->
		<!--</el-form-item>-->
		<el-form-item>
			<el-button type="primary" v-on:click="onSubmit">立即创建</el-button>
		</el-form-item>
	</el-form>
</template>

<script>
    import {addUser} from '../../aapi/user';
    import RespStatus from "../../aapi/base/RespStatus";
	export default {
		data() {
			return {
				form: {
					username: '',
					password: '',
                    email:''
				},
                addAdminFormRules: {
                    username: [
                        {required: true, message: '请输入姓名', trigger: 'blur'}
                    ],
                    password: [
                        {required: true, message: '请输入密码', trigger: 'blur'}
                    ],
                    email: [
                        {type: "email", required: true, message: '请输入正确的邮箱地址', trigger: 'blur'}
                    ]
                },
			}
		},
		methods: {


			onSubmit() {
                this.$refs.form.validate((valid) => {
                    if (valid) {
                        const introduce = "我是一个管理员";
                        const userRole = 2;
                        addUser(this.form.username,this.form.password,this.form.email,introduce,userRole).then(res=>{
                            if (res.data.code === RespStatus.OK.code){
                                this.$message({
                                    message: '创建成功',
                                    type: 'success'
                                });
                            }
                            if (res.data.code === RespStatus.BAD_REQUEST.code){
                                this.$message({
                                    message: '用户名已存在',
                                    type: 'error'
                                });
                            }
                        })
                    }
                })
			}
		}
	}

</script>