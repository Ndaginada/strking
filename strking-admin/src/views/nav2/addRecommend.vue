<template>
    <el-form ref="form" :model="form" label-width="150px" :rules="addFormRules">
        <el-form-item label="推荐视频标题" prop="title">
            <el-input v-model="form.title" style="width: 25%"></el-input>
        </el-form-item>
        <el-form-item label="推荐视频地址" prop="video_url">
            <el-input v-model="form.video_url" style="width: 50%"></el-input>
            <br/>
            <span>说明：请复制分享视频的HTML代码</span>
        </el-form-item>
        <el-form-item label="出自" prop="from_author">
            <el-input v-model="form.from_author" style="width: 25%"></el-input>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="onSubmit">立即创建</el-button>
            <el-button>取消</el-button>
        </el-form-item>
    </el-form>
</template>
<script>
    import {addRecommendVideo} from '../../aapi/article';
    import {userInfo} from "../../aapi/user";
    import RespStatus from "../../aapi/base/RespStatus";

    export default {
        data() {
            return {

                form: {
                    title: '',
                    video_url: '',
                    from_author: '',
                    add_author_name: ''
//                    sid ,
//                    add_admin_name,
//                    send_time,
                },
                addFormRules: {
                    title: [
                        {required: true, message: '请输入标题', trigger: 'blur'}
                    ],
                    video_url: [
                        {required: true, message: '请输入视频代码', trigger: 'blur'}
                    ],
                    from_author: [
                        {required: true, message: '请输入出处', trigger: 'blur'}
                    ],
                },
            }
        },
        methods: {
            onSubmit() {
                this.$refs.form.validate((valid) => {
                    if (valid) {
                        this.$confirm('确认添加该记录吗?', '提示', {
                            type: 'warning'
                        }).then(() => {
                            addRecommendVideo(this.form.title, this.form.video_url, this.form.from_author, this.form.add_author_name).then(res => {
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
                            });
                        });
                    }
                })


            }
        },
        mounted() {

            const user_id = window.localStorage.getItem("strking_user_id");
            //调用获取用户信息接口
            userInfo(user_id).then(res => {
                this.form.add_author_name = res.data.data.username;
            })

        }
    }
</script>