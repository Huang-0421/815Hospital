<template>
    <div>
        <!-- 用户搜索 -->
        <div class="header">
            <div class="form">
                <el-input v-model="name" placeholder="请输入名字" suffix-icon="el-icon-search" style="width: 200px;"></el-input>
                <el-button type="primary" style="margin-left: 5px;" @click="searchDoctor">查询</el-button>
            </div>

            <div class="group">
                <el-button size="small" type="warning" icon="el-icon-plus" @click="add">添加用户</el-button>
            </div>
        </div>

        <!-- 用户列表 -->
        <div class="listContent">
            <el-table :data="tableData" stripe border style="width: 100%" header-cell-class-name="active-header" cell-class-name="table-center">
                <el-table-column type="selection" width="70"></el-table-column>
                <el-table-column prop="id" label="ID" width="220"></el-table-column>
                <el-table-column prop="phone" label="电话" width="150"></el-table-column>
                <el-table-column prop="name" label="姓名" width="100"></el-table-column>
                <el-table-column prop="age" label="年龄" width="70"></el-table-column>

                <el-table-column prop="gender" label="性别" width="80">
                    <template slot-scope="scope">
                        <el-tag :type="scope.row.gender === 1 ? 'primary' : 'success'" disable-transitions>
                            {{ scope.row.gender === 1 ? '男' : '女' }}
                        </el-tag>
                    </template>
                </el-table-column>

                <el-table-column prop="room" label="科室" width="80"></el-table-column>

                <el-table-column label="操作">
                    <template slot-scope="scope">
                        <el-button size="mini" type="primary" @click="handleEdit(scope.row)" icon="el-icon-edit">编辑</el-button>
                        <el-popconfirm title="确定删除吗？" @confirm="handleDelete(scope.row.id)" style="margin-left: 5px;">
                            <el-button slot="reference" size="small" type="danger">删除</el-button>
                        </el-popconfirm>
                        <el-switch 
                            class="duty-switch" 
                            v-model="scope.row.duty" 
                            :active-value="1" 
                            :inactive-value="0" 
                            active-text="在岗" 
                            inactive-text="休息" 
                            @change="onDutyChange(scope.row)">
                        </el-switch>
                    </template>
                </el-table-column>
            </el-table>

            <Pagination :total="total" :pageSize="pageSize" @getPagination="getPagination"></Pagination>
        </div>

        <!-- 添加用户窗口 -->
        <el-dialog title="添加/编辑用户" :visible.sync="dialogFormVisible">
            <el-form :model="form" :rules="rules" ref="form" label-width="100px" class="demo-ruleForm">
                <el-form-item label="电话" prop="phone">
                    <el-col :span="20">
                        <el-input v-model="form.phone"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="姓名" prop="name">
                    <el-col :span="20">
                        <el-input v-model="form.name"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="年龄" prop="age">
                    <el-col :span="20">
                        <el-input v-model="form.age"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="性别">
                    <el-radio-group v-model="form.gender">
                        <el-radio label="1">男</el-radio>
                        <el-radio label="0">女</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="科室" prop="room">
                    <el-col :span="20">
                        <el-input v-model="form.room"></el-input>
                    </el-col>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取消</el-button>
                <el-button type="primary" @click="save">确定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
import Pagination from "@/components/pagination/Pagination.vue";
import qs from 'qs';

export default {
    components: {
        Pagination
    },
    data() {
        return {
            tableData: [],
            pageSize: 10,
            total: 0,
            pageNum: 1,
            name: '',
            dialogFormVisible: false,
            form: {
                id: '',
                name: '',
                age: '',
                room: '',
                phone: '',
                gender: '0',
            },
            rules: {
                name: [
                    { required: true, message: '请输入名字', trigger: 'blur' }
                ],
                age: [
                    { required: true, message: '请输入年龄', trigger: 'blur' },
                    { min: 1, max: 3, message: '长度在 1 到 3 个位', trigger: 'blur' },
                    { pattern: /^([1-9][0-9]*){1,3}$/, message: '年龄必须为正整数字', trigger: "blur" },
                ],
                room: [
                    {
                        required: true,
                        message: "请输入有效的科室（内科/外科/妇科/儿科/骨科）",
                        trigger: "blur"
                    },
                    {
                        pattern: /^(内科|外科|妇科|儿科|骨科)$/,
                        message: "科室名称必须是内科、外科、妇科、儿科或骨科",
                        trigger: "blur"
                    },
                ],
                phone: [
                    { required: true, message: "手机号不能为空", trigger: "blur" },
                    { pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/, message: "请输入正确的手机号码", trigger: "blur" }
                ]
            },
            formLabelWidth: '120px',
            departmentMap: {
                1826793026329628674: "内科",
                1826793027415953410: "外科",
                1826793027415953411: "儿科",
                1826793027478867970: "妇产科",
                1826793027478867972: "骨科"
            }
        }
    },
    methods: {
        loadDoctorList(page) {
            this.pageNum = page;
            this.$axios.post('/admin/showdoctor', qs.stringify({
                page: this.pageNum,
                pageSize: this.pageSize,
                name: this.name
            })).then(res => res.data).then(res => {
                if (res.code === 1) {
                    this.tableData = res.data.doctors.map(doctor => ({
                        ...doctor,
                        room: this.departmentMap[doctor.departmentId] || '',
                        duty: doctor.duty  // 确保duty的值正确传递
                    }));
                    this.total = res.data.total;
                } else {
                    this.$message({
                        message: '列表查询失败！',
                        type: 'error'
                    });
                }
            });
        },
        searchDoctor() {
            this.$axios.post('/admin/searchdoc', qs.stringify({
                doctorName: this.name
            })).then(res => res.data).then(res => {
                if (res.code === 1) {
                    this.tableData = res.data.doctors.map(doctor => ({
                        ...doctor,
                        room: this.departmentMap[doctor.departmentId] || '',
                        duty: doctor.duty  // 确保duty的值正确传递
                    }));
                    this.total = res.data.total; // 更新总数
                } else {
                    this.$message({
                        message: '查询失败！',
                        type: 'error'
                    });
                }
            });
        },
        handleEdit(row) {
            this.dialogFormVisible = true;
            this.$nextTick(() => {
                this.form.id = row.id + '';
                this.form.name = row.name + '';
                this.form.age = row.age + '';
                this.form.gender = row.gender + '';
                this.form.room = row.room || '';
                this.form.phone = row.phone;
            });
        },
        save() {
            this.$refs.form.validate(valid => {
                if (valid) {
                    this.$axios.post('/admin/editdoc', qs.stringify({
                        doctorId: this.form.id,
                        doctorName: this.form.name,
                        phone: this.form.phone,
                        age: this.form.age,
                        gender: this.form.gender,
                        departmentName: this.form.room
                    })).then(res => res.data).then(res => {
                        if (res.code == 1) {
                            this.$notify({
                                title: '成功',
                                message: '操作成功！',
                                type: 'success'
                            });
                            this.dialogFormVisible = false;
                            this.loadDoctorList(this.pageNum);
                        } else {
                            this.$message({
                                message: '操作失败！',
                                type: 'error'
                            });
                        }
                    });
                } else {
                    this.$message({
                        message: "数据不能为空",
                        type: 'error'
                    });
                    return false;
                }
            });
        },
        handleDelete(id) {
            this.$axios.post('/admin/deletedoc', qs.stringify({ doctorId: id })).then(res => res.data).then(res => {
                if (res.code == 1) {
                    this.$notify({
                        title: '成功',
                        message: '删除医生信息成功！',
                        type: 'success'
                    });
                    this.loadDoctorList(this.pageNum);
                } else {
                    this.$message({
                        message: '删除医生信息失败！',
                        type: 'error'
                    });
                }
            });
        },
        onDutyChange(user) {
            this.$axios.post('/admin/duty', qs.stringify({ doctorId: user.id })).then(res => res.data).then(res => {
                if (res.code == 1) {
                    this.$notify({
                        title: '成功',
                        message: '修改医生在岗情况成功！',
                        type: 'success'
                    });
                    this.loadDoctorList(this.pageNum);
                } else {
                    this.$message({
                        message: '修改医生在岗情况失败！',
                        type: 'error'
                    });
                }
            });
        },
        getPagination(page) {
            this.loadDoctorList(page);
        },
        resetForm() {
            this.$refs.form.resetFields();
        },
        add() {
            this.dialogFormVisible = true;
            this.resetForm();
        },
    },
    beforeMount() {
        this.loadDoctorList(1);
    }
}
</script>

<style lang="less" scoped>
.header {
    background: #fff;
    padding: 10px;
    margin-bottom: 20px;
    .group {
        border: 1px solid #eee;
        padding: 8px;
    }
}
.listContent {
    background: pink;
    height: 100px;
    /deep/ .active-header {
        color: #333;
        text-align: center;
    }
}
.el-form-item {
    margin-bottom: 20px;
}
.el-input__inner {
    height: 35px;
}
.duty-switch {
    margin-left: 30px;
}
</style>
