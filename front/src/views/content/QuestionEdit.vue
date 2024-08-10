<template>
  <Dialog
    :show="dialogConfig.show"
    :title="dialogConfig.title"
    :buttons="dialogConfig.buttons"
    :showCancel="true"
    @close="dialogConfig.show = false"
    width="90%"
  >
    <el-form
      :model="formData"
      :rules="rules"
      ref="formDataRef"
      label-width="80px"
      @submit.prevent
    >
      <el-row :gutter="10">
        <el-col :span="12">
          <el-form-item label="标题" prop="title">
            <el-input :maxLength="10" v-model="formData.title" />
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="分类" prop="categoryId">
            <CategorySelect
              v-model="formData.categoryId"
              :type="0"
            ></CategorySelect>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="难度" prop="difficultyLevel">
            <el-rate v-model="formData.difficultyLevel"></el-rate>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="10">
        <el-col :span="12">
          <el-form-item label="问题" prop="question">
            <SunEditor v-model="formData.question"></SunEditor>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="答案分析" prop="answerAnalysis">
            <SunEditor v-model="formData.answerAnalysis"></SunEditor>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
  </Dialog>
</template>

<script setup>
import { ref, reactive, getCurrentInstance, nextTick } from "vue";
const { proxy } = getCurrentInstance();

const api = {
  saveQuestion: "/questionInfo/saveQuestionInfo",
};

const dialogConfig = ref({
  show: false,
  title: "编辑八股文",
  buttons: [
    {
      type: "primary",
      text: "确定",
      click: (e) => {
        submitForm();
      },
    },
  ],
});

const formData = ref({});
const formDataRef = ref();

const validateRePass = (rule, value, callback) => {
  if (value !== formData.value.password) {
    callback(new Error(rule.message));
  } else {
    callback();
  }
};

const rules = {
  title: [{ required: true, message: "请输入标题" }],
  categoryId: [{ required: true, message: "请选择分类" }],
  difficultyLevel: [{ required: true, message: "请输选择难度" }],
  answerAnalysis: [{ required: true, message: "请输入答案解析" }],
};

const showEdit = (data) => {
  dialogConfig.value.show = true;
  nextTick(() => {
    formDataRef.value.resetFields();
    formData.value = Object.assign({}, data || {});
  });
};

defineExpose({
  showEdit,
});

const emit = defineEmits(["reload"]);
const submitForm = async () => {
  formDataRef.value.validate(async (valid) => {
    if (!valid) {
      return;
    }
    let params = {};
    Object.assign(params, formData.value);

    let result = await proxy.Request({
      url: api.saveQuestion,
      params,
    });
    if (!result) {
      return;
    }
    dialogConfig.value.show = false;
    proxy.Message.success("保存成功");
    emit("reload");
  });
};
</script>

<style lang="scss" scoped>
</style>
