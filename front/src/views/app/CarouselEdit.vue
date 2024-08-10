<template>
  <Dialog
    :show="dialogConfig.show"
    :title="dialogConfig.title"
    :buttons="dialogConfig.buttons"
    :showCancel="true"
    @close="dialogConfig.show = false"
  >
    <el-form
      :model="formData"
      :rules="rules"
      ref="formDataRef"
      label-width="80px"
      @submit.prevent
    >
      <el-form-item label="轮播图" prop="imgPath">
        <CoverUpload
          v-model="formData.imgPath"
          :width="330"
          :height="180"
          :type="1"
        ></CoverUpload>
      </el-form-item>
      <!-- 单选 -->
      <el-form-item label="类型" prop="objectType">
        <el-radio-group v-model="formData.objectType">
          <el-radio :label="0">分享</el-radio>
          <el-radio :label="1">八股文</el-radio>
          <el-radio :label="2">考题</el-radio>
          <el-radio :label="3">外部链接</el-radio>
        </el-radio-group>
      </el-form-item>

      <!--input输入-->
      <el-form-item
        label="链接地址"
        prop="ourterLink"
        v-if="formData.objectType == 3"
      >
        <el-input
          :maxLength="200"
          clearable
          placeholder="请输入完整的url地址"
          v-model="formData.outerLink"
        ></el-input>
      </el-form-item>
      <el-form-item v-else label="文章ID" prop="objectId">
        <el-input
          clearable
          :maxLength="10"
          placeholder="请输入文章ID"
          v-model="formData.objectId"
        ></el-input>
      </el-form-item>
    </el-form>
  </Dialog>
</template>

<script setup>
import { ref, reactive, getCurrentInstance, nextTick } from "vue";
const { proxy } = getCurrentInstance();

const api = {
  saveCarousel: "/appCarousel/saveCarousel",
};

const dialogConfig = ref({
  show: false,
  title: "新增轮播图",
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

const rules = {
  imgPath: [{ required: true, message: "请选择图片" }],
  objecType: [{ required: true, message: "请选择类型" }],
  objectId: [{ required: true, message: "请选择内容ID" }],
  outerLink: [{ required: true, message: "请输入URL地址" }],
};

const showEdit = (data) => {
  dialogConfig.value.show = true;
  nextTick(() => {
    formDataRef.value.resetFields();
    if (data.carouselId == null) {
      dialogConfig.value.title = "新增轮播图";
      formData.value = {};
    } else {
      dialogConfig.value.title = "修改轮播图";
      formData.value = Object.assign({}, data);
    }
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
      url: api.saveCarousel,
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
