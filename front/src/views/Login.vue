<template>
  <div class="login-body">
    <div class="bg"></div>
    <div class="login-panel">
      <el-form
        class="login-form"
        :model="formData"
        :rules="rules"
        ref="formDataRef"
        @submit.prevent
      >
        <div class="login-title">EasyJob管理后台</div>
        <!--input输入-->
        <el-form-item prop="phone">
          <el-input
            size="large"
            clearable
            placeholder="请输入手机号"
            v-model.trim="formData.phone"
          >
            <template #prefix>
              <span class="iconfont icon-phone"></span>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input
            show-password
            type="password"
            size="large"
            clearable
            placeholder="请输入密码"
            v-model.trim="formData.password"
          >
            <template #prefix>
              <span class="iconfont icon-password"></span>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item prop="checkCode">
          <div class="check-code-panel">
            <el-input
              size="large"
              clearable
              placeholder="请输入验证码"
              v-model.trim="formData.checkCode"
            >
              <template #prefix>
                <span class="iconfont icon-checkcode"></span> </template
            ></el-input>
            <img
              :src="checkCodeUrl"
              class="check-code"
              @click="changeCheckCode"
            />
          </div>
        </el-form-item>
        <el-form-item>
          <div class="rememberme-panel">
            <el-checkbox v-model="formData.rememberMe">记住我</el-checkbox>
          </div>
        </el-form-item>
        <el-form-item>
          <el-button
            type="primary"
            class="op-btn"
            @click="doSubmit"
            size="large"
            >登录</el-button
          >
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive, getCurrentInstance, nextTick, onMounted } from "vue";
import { useRouter } from "vue-router";
const { proxy } = getCurrentInstance();
import md5 from "js-md5";
const router = useRouter();

const api = {
  checkCode: "/api/checkCode",
  login: "/login",
};

//验证码
const checkCodeUrl = ref(null);
const changeCheckCode = () => {
  checkCodeUrl.value = `${api.checkCode}?time=${new Date().getTime()}`;
};

const formData = ref({});
const formDataRef = ref();
const rules = {
  phone: [{ required: true, message: "请输入手机号" }],
  password: [{ required: true, message: "请输入密码" }],
  checkCode: [{ required: true, message: "请输入图片验证码" }],
};

//登录
const doSubmit = () => {
  formDataRef.value.validate(async (valid) => {
    if (!valid) {
      return;
    }
    let params = {};
    Object.assign(params, formData.value);

    let cookieLoginInfo = proxy.VueCookies.get("loginInfo");
    let cookiePassword =
      cookieLoginInfo == null ? null : cookieLoginInfo.password;
    if (params.password !== cookiePassword) {
      params.password = md5(params.password);
    }
    let result = await proxy.Request({
      url: api.login,
      params,
      errorCallback: () => {
        changeCheckCode();
      },
    });
    if (!result) {
      return;
    }
    if (params.rememberMe) {
      const loginInfo = {
        phone: params.phone,
        password: params.password,
        rememberMe: params.rememberMe,
      };
      proxy.VueCookies.set("loginInfo", loginInfo, "7d");
    } else {
      proxy.VueCookies.remove("loginInfo");
    }
    proxy.Message.success("登录成功");
    sessionStorage.setItem("userInfo", JSON.stringify(result.data));
    let firstMenu = result.data.menuList[0];
    if (firstMenu.children.length > 0) {
      firstMenu = firstMenu.children[0];
    }
    router.push(firstMenu.menuUrl);
  });
};

onMounted(() => {
  init();
});
const init = () => {
  nextTick(() => {
    changeCheckCode();
    formDataRef.value.resetFields();
    formData.value = {};
    const cookieLoginInfo = proxy.VueCookies.get("loginInfo");
    if (cookieLoginInfo) {
      formData.value = cookieLoginInfo;
    }
  });
};
</script>

<style lang="scss" scoped>
.login-body {
  height: calc(100vh);
  background-size: cover;
  background: url("../assets/login_bg.jpg");
  display: flex;
  .bg {
    flex: 1;
    background-size: cover;
    background-position: center;
    background-size: 800px;
    background-repeat: no-repeat;
    background-image: url("../assets/login_img.png");
  }
  .login-panel {
    width: 430px;
    margin-right: 15%;
    margin-top: calc((100vh - 500px) / 2);
    .login-form {
      padding: 25px;
      background: #fff;
      border-radius: 5px;
      .login-title {
        text-align: center;
        font-size: 18px;
        font-weight: bold;
        margin-bottom: 20px;
      }
      .rememberme-panel {
        width: 100%;
      }
      .op-btn {
        width: 100%;
      }
    }
  }

  .check-code-panel {
    width: 100%;
    display: flex;
    .check-code {
      margin-left: 5px;
      cursor: pointer;
    }
  }
}
</style>
