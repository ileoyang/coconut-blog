<template>
    <div id="user-panel">
        <el-card class="box-card">
            <div slot="header">
                Register
            </div>
            <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="auto" class="demo-ruleForm">
                <el-form-item label="Username" prop="username">
                    <el-input v-model="ruleForm.username"/>
                </el-form-item>
                <el-form-item label="Password" prop="password">
                    <el-input type="password" v-model="ruleForm.password"/>
                </el-form-item>
                <el-form-item label="Email" prop="email">
                    <el-input type="email" v-model="ruleForm.email"/>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitForm('ruleForm')">
                        Submit
                    </el-button>
                    <el-button @click="resetForm('ruleForm')">
                        Reset
                    </el-button>
                </el-form-item>
            </el-form>
        </el-card>
    </div>
</template>

<script>
export default {
    data() {
        return {
            ruleForm: {
                username: "",
                password: "",
                email: ""
            },
            rules: {
                username: [
                    { required: true, message: "please input username", trigger: "blur" },
                    { min: 2, max: 50, message: "the length must be between 2 and 50", trigger: "blur" }
                ],
                password: [
                    { required: true, message: "please input password", trigger: "blur" },
                    { min: 6, max: 50, message: "the length must be between 6 and 50", trigger: "blur" }
                ],
                email: [
                    { required: true, message: "please input email", trigger: "blur" },
                    { type: "email", message: "please input valid email", trigger: "blur" }
                ]
            }
        };
    },
    methods: {
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    this.$http.post("user/register", this.ruleForm).then(() => {
                        this.$router.push("login")
                    })
                } else {
                    return false
                }
            })
        },
        resetForm(formName) {
            this.$refs[formName].resetFields()
        }
    }
}
</script>
