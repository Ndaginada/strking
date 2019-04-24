<template>
    <section>
        <!--工具条-->
        <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
            <el-form :inline="true" :model="filters">
                <el-form-item>
                    <el-input v-model="filters.name" placeholder="姓名"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" v-on:click="getUsers">查询</el-button>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="handleAdd">新增</el-button>
                </el-form-item>
            </el-form>
        </el-col>

        <!--列表-->
        <el-table :data="recommends" highlight-current-row v-loading="listLoading" @selection-change="selsChange"
                  style="width: 100%;">
            <!--<el-table-column type="selection" width="55">-->
            <!--</el-table-column>-->
            <el-table-column prop="sid" label="用户ID" width="80">
            </el-table-column>
            <el-table-column
                    prop="icon"
                    label="头像"
                    width="180">
                <template slot-scope="scope">
                    <img :src="'http://localhost:4444/'+scope.row.icon" alt="" style="width: 50px;height: 50px">
                </template>
            </el-table-column>
            <el-table-column prop="username" label="用户名" width="120">
            </el-table-column>
            <el-table-column prop="password" label="密码" width="120">
            </el-table-column>
            <el-table-column prop="email" label="邮箱" width="120">
            </el-table-column>
            <el-table-column prop="experience" label="经验值" width="120" sortable>
            </el-table-column>
            <el-table-column prop="reg_time" label="注册时间" width="150" sortable>
            </el-table-column>
            <el-table-column prop="introduce" label="简介" min-width="180">
            </el-table-column>
            <el-table-column label="操作" width="150">
                <template slot-scope="scope">
                    <el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                    <el-button type="danger" size="small" @click="handleDel(scope.$index, scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <!--工具条-->
        <el-col :span="24" class="toolbar">
            <!--<el-button type="danger" @click="batchRemove" :disabled="this.sels.length===0">批量删除</el-button>-->
            <el-pagination layout="prev, pager, next" @current-change="handleCurrentChange" :page-size="this.limit"
                           :total="total" style="float:right;">
            </el-pagination>
        </el-col>

        <!--编辑界面-->
        <el-dialog title="编辑" v-model="editFormVisible" :close-on-click-modal="false">
            <el-form :model="editForm" label-width="80px" :rules="editFormRules" ref="editForm">
                <el-form-item label="头像" prop="icon">
                    <div style="margin: 0">
                        <input class="avatar" name="file" type="file" accept="image/png,image/gif,image/jpeg" @change="img_uplode" style="width: 30%;margin-left: 46%"/>
                        <img  v-if="is_uplode" :src="'http://localhost:4444/'+editForm.icon" id="heard_icon" width="100" height="100">
                        <img v-if="!is_uplode" :src="'http://localhost:4444/'+editForm.icon"  width="100" height="100" >
                    </div>
                </el-form-item>
                <el-form-item label="姓名" prop="username">
                    <el-input v-model="editForm.username" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="邮箱" prop="email">
                    <el-input type="email" v-model="editForm.email" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="简介">
                    <el-input type="textarea" v-model="editForm.introduce"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="editFormVisible = false">取消</el-button>
                <el-button type="primary" @click.native="editSubmit" :loading="editLoading">提交</el-button>
            </div>
        </el-dialog>

        <!--新增界面-->
        <el-dialog title="新增" v-model="addFormVisible" :close-on-click-modal="false">
            <el-form :model="addForm" label-width="80px" :rules="addFormRules" ref="addForm">
                <el-form-item label="用户名" prop="username">
                    <el-input v-model="addForm.username" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-input type="password" v-model="addForm.password" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="邮箱" prop="email">
                    <el-input type="email" v-model="addForm.email" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="权限">
                    <el-radio-group v-model="addForm.userRole">
                        <el-radio  :label="1">普通用户</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="简介">
                    <el-input type="textarea" v-model="addForm.introduce"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="addFormVisible = false">取消</el-button>
                <el-button type="primary" @click.native="addSubmit" :loading="addLoading">提交</el-button>
            </div>
        </el-dialog>
    </section>
</template>

<script>
    import util from '../../common/js/util'
    //import NProgress from 'nprogress'
    //import {getUserListPage, removeUser, batchRemoveUser, editUser, addUser} from '../../api/api';
    import {userList, delUser, addUser, changeInfo} from '../../aapi/user';
    import RespStatus from '../../aapi/base/RespStatus';
    import ElForm from "../../../node_modules/element-ui/packages/form/src/form.vue";
    import ElFormItem from "../../../node_modules/element-ui/packages/form/src/form-item.vue";
    import {uplode_img} from "../../aapi/home";

    export default {
        components: {
            ElFormItem,
            ElForm},
        data() {
            return {
                filters: {
                    name: ''
                },
                recommends: [],
                total: 0,
                page: 1,
                listLoading: false,
                limit: 20,
                offset: 0,
                sels: [],//列表选中列
                is_uplode: false,
                editFormVisible: false,//编辑界面是否显示
                editLoading: false,
                editFormRules: {
                    email: [
                        {type: "email",  message: '请输入正确的邮箱地址', trigger: 'blur'}
                    ]
                },
                //编辑界面数据
                editForm: {
                    sid:'',
                    username:'',
                    email:'',
                    icon:'',
                    introduce:''
                },

                addFormVisible: false,//新增界面是否显示
                addLoading: false,
                addFormRules: {
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
                //新增界面数据
                addForm: {
                    username: '',
                    password: '',
                    email: '',
                    introduce: '',
                    userRole: 1
                }

            }
        },
        methods: {
            //头像上传
            img_uplode(e){
                let file = e.target.files[0];
                let param = new FormData(); //创建form对象
                let file_name = 'kkai' + file.name;
                param.append('file',file,file_name);//通过append向form对象添加数据
                //param.append('chunk','0');//添加form表单中其他数据
                console.log(param.get('file'));
                //axios向后端发送请求
                uplode_img(param).then(res=>{
                    if (res.data.data != null || res.data.data != ''){
                        this.is_uplode = true;
                        this.editForm.icon = 'img/headIcon_'+file_name;    //修改图片
                    }
                });
            },
            handleCurrentChange(val) {
                this.page = val;
                this.getUsers();
            },
            //获取用户列表
            getUsers() {
//				let para = {
//					page: this.page,
//					name: this.filters.name
//				};

                this.offset = (this.page - 1) * this.limit;
                this.listLoading = true;
                //NProgress.start();
                userList(this.filters.name, this.limit, this.offset).then(res => {
                    this.total = res.data.data.totalCount;
                    this.recommends = res.data.data.items;
                    for (let i = 0; i < this.recommends.length; i++) {
                        this.recommends[i].reg_time = util.formatDate.format(new Date(this.recommends[i].reg_time), 'yyyy-MM-dd')
                    }
                    this.listLoading = false;
                });
            },
            //删除
            handleDel: function (index, row) {
                this.$confirm('确认删除该记录吗?', '提示', {
                    type: 'warning'
                }).then(() => {
                    this.listLoading = true;
                    //NProgress.start();

                    delUser(row.sid).then(res => {
                        this.listLoading = false;
                        //NProgress.done();
                        if (res.data.code === RespStatus.OK.code) {
                            this.$message({
                                message: '删除成功',
                                type: 'success'
                            });
                        } else {
                            this.$message({
                                message: '删除失败',
                                type: 'error'
                            });
                        }
                        this.getUsers();
                    });
                });
            },
            //显示编辑界面
            handleEdit: function (index, row) {
                this.editFormVisible = true;
                this.editForm = Object.assign({}, row);
            },
            //显示新增界面
            handleAdd: function () {
                this.addFormVisible = true;
                this.addForm = {
                    username: '',
                    password: '',
                    email: '',
                    introduce: '',
                    userRole: 1
                };
            }
            ,
            //编辑
            editSubmit: function () {
                this.$refs.editForm.validate((valid) => {
                    if (valid) {
                        this.$confirm('确认提交吗？', '提示', {}).then(() => {
                            this.editLoading = true;
                            //NProgress.start();
                            let para = Object.assign({}, this.editForm);
                            changeInfo(para.sid,para.username,para.email,para.icon,para.introduce).then(res=>{
                                console.log(res);
                                this.editLoading = false;
                                if (res.data.code === RespStatus.OK.code){
                                    this.$message({
                                        message: '修改成功!',
                                        type: 'success'
                                    });
                                }
                                if (res.data.code === RespStatus.BAD_REQUEST.code) {
                                    this.$message({
                                        message: '用户名已存在!',
                                        type: 'error'
                                    });
                                }
                                this.$refs['editForm'].resetFields();
                                this.editFormVisible = false;
                                this.getUsers();
                            });
//                            editUser(para).then((res) => {
//                                this.editLoading = false;
//                                //NProgress.done();
//                                this.$message({
//                                    message: '提交成功',
//                                    type: 'success'
//                                });
//                                this.$refs['editForm'].resetFields();
//                                this.editFormVisible = false;
//                                this.getUsers();
//                            });
                        });
                    }
                });
            }
            ,
            //新增
            addSubmit: function () {
                this.$refs.addForm.validate((valid) => {
                    if (valid) {
                        this.$confirm('确认提交吗？', '提示', {}).then(() => {
                            this.addLoading = true;
                            //NProgress.start();
                            let para = Object.assign({}, this.addForm);
                            addUser(para.username,para.password,para.email,para.introduce,para.userRole).then((res) => {

                                this.addLoading = false;
                                //NProgress.done();
                                if (res.data.code === RespStatus.OK.code){
                                    this.$message({
                                        message: '提交成功',
                                        type: 'success'
                                    });
                                }
                                if (res.data.code === RespStatus.BAD_REQUEST.code){
                                    this.$message({
                                        message: '用户名已存在',
                                        type: 'error'
                                    });
                                }
                                this.$refs['addForm'].resetFields();
                                this.addFormVisible = false;
                                this.getUsers();
                            });
                        });
                    }
                });
            },
            selsChange: function (sels) {
                this.sels = sels;
            }
            ,
            //批量删除
            batchRemove: function () {
                var ids = this.sels.map(item => item.id).toString();
                this.$confirm('确认删除选中记录吗？', '提示', {
                    type: 'warning'
                }).then(() => {
                    this.listLoading = true;
                    //NProgress.start();
                    let para = {ids: ids};
                    batchRemoveUser(para).then((res) => {
                        this.listLoading = false;
                        //NProgress.done();
                        this.$message({
                            message: '删除成功',
                            type: 'success'
                        });
                        this.getUsers();
                    });
                }).catch(() => {

                });
            }
        }
        ,
        mounted() {
            this.getUsers();
        }
    }

</script>

<style scoped>

</style>