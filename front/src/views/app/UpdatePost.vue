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
      label-width="100px"
      @submit.prevent
    >
      <!--input输入-->
      <el-form-item label="版本号" prop="version">
        {{ formData.version }}
      </el-form-item>
      <!-- 单选 -->
      <!-- 单选 -->
      <el-form-item label="更新类型" prop="status">
        <el-radio-group v-model="formData.status">
          <el-radio :label="0">取消发布</el-radio>
          <el-radio :label="1">灰度发布</el-radio>
          <el-radio :label="2">全网发布</el-radio>
        </el-radio-group>
      </el-form-item>
      <!--input输入-->
      <el-form-item
        label="灰度设备ID"
        prop="grayscaleDevice"
        v-if="formData.status == 1"
      >
        <div class="tag-panel">
          <el-tag
            v-for="(tag, index) in formData.grayscaleDevice"
            :key="tag"
            closable
            @close="closeTag(index)"
            :type="tag.type"
            class="tag"
            >{{ tag }}</el-tag
          >
          <div class="tag input" v-if="showInput">
            <el-input
              size="small"
              clearable
              placeholder="请输入设备ID"
              v-model="tagInput"
              @blur="addDeviceId"
              @keyup.enter="addDeviceId"
            ></el-input>
          </div>
          <div class="tag" v-else>
            <el-button type="primary" size="small" @click="showInputHandler"
              >新增</el-button
            >
          </div>
        </div>
      </el-form-item>
    </el-form>
  </Dialog>
</template>

<script setup>
import { ref, reactive, getCurrentInstance, nextTick } from "vue";
const { proxy } = getCurrentInstance();

const api = {
  postUpdate: "/app/postUpdate",
};

const dialogConfig = ref({
  show: false,
  title: "发布更新",
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
  status: [{ required: true, message: "请选择发布状态" }],
};

const showEdit = (data) => {
  dialogConfig.value.show = true;
  nextTick(() => {
    formDataRef.value.resetFields();
    formData.value = {
      id: data.id,
      version: data.version,
      status: data.status,
      grayscaleDevice: data.grayscaleDevice
        ? data.grayscaleDevice.split(",")
        : [],
    };
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
    const grayscaleDevice = params.grayscaleDevice.join(",");
    delete params.updateDescList;
    let result = await proxy.Request({
      url: api.postUpdate,
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

const showInput = ref(false);
const tagInput = ref();
const addDeviceId = () => {
  if (tagInput.value) {
    formData.value.grayscaleDevice.push(tagInput.value);
  }
  tagInput.value = "";
  showInput.value = false;
};

const showInputHandler = () => {
  showInput.value = true;
};

const closeTag = () => {
  formData.value.grayscaleDevice.splice(index, 1);
};
</script>

<style lang="scss" scoped>
.tag-panel {
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  .tag {
    margin: 0px 5px 5px 0px;
  }
  .input {
    width: 150px;
  }
}
</style>
