<template>
  <div class="columns py-6">
    <div class="column is-half is-offset-one-quarter">
      <el-card shadow="never">
        <div slot="header" class="has-text-centered has-text-weight-bold">
          Register
        </div>
        <div>
          <el-form
            ref="ruleForm"
            :model="ruleForm"
            status-icon
            :rules="rules"
            label-width="100px"
            class="demo-ruleForm"
          >
            <el-form-item label="username" prop="username">
              <el-input v-model="ruleForm.username" />
            </el-form-item>
            <el-form-item label="password" prop="password">
              <el-input
                v-model="ruleForm.password"
                type="password"
                autocomplete="off"
              />
            </el-form-item>
            <el-form-item label="email" prop="email">
              <el-input v-model="ruleForm.email" autocomplete="off" />
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="submitForm('ruleForm')">
                Submit
              </el-button>
              <el-button @click="resetForm('ruleForm')">Reset</el-button>
            </el-form-item>
          </el-form>
        </div>
      </el-card>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      ruleForm: {
        username: '',
        password: '',
        email: '',
      },
      rules: {
        username: [
          { required: true, message: 'please input username', trigger: 'blur' },
          {
            min: 2,
            max: 50,
            message: 'the length must be between 2 and 50',
            trigger: 'blur',
          },
        ],
        password: [
          { required: true, message: 'please input password', trigger: 'blur' },
          {
            min: 6,
            max: 50,
            message: 'the length must be between 6 and 50',
            trigger: 'blur',
          },
        ],
        email: [
          { required: true, message: 'please input email', trigger: 'blur' },
          {
            type: 'email',
            message: 'please input valid email',
            trigger: 'blur',
          },
        ],
      },
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$http.post('user/register', this.ruleForm).then(() => {
            this.$router.push('login');
          });
        } else {
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
  },
};
</script>
