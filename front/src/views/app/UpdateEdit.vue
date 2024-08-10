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
        <el-input
          clearable
          placeholder="eg:1.0.0"
          v-model="formData.version"
        ></el-input>
      </el-form-item>
      <!-- 单选 -->
      <el-form-item label="文件" prop="fileName" class="file-select">
        <div class="file-name">{{ formData.fileName }}</div>
        <el-upload
          name="file"
          :show-file-list="false"
          accept=".apk,.wgt"
          :multiple="false"
          :http-request="selectFile"
        >
          <el-button type="primary" size="small">选择文件</el-button>
        </el-upload>
      </el-form-item>
      <!-- 单选 -->
      <el-form-item label="更新类型" prop="updateType">
        <el-radio-group v-model="formData.updateType">
          <el-radio :label="1">局部热更新</el-radio>
          <el-radio :label="0">全更新</el-radio>
        </el-radio-group>
      </el-form-item>
      <!--input输入-->
      <el-form-item label="更新内容" class="update-form-item">
        <div
          class="update-desc-item"
          v-for="(item, index) in formData.updateDescList"
        >
          <!--input输入-->
          <el-form-item
            :prop="'updateDescList.' + index + '.title'"
            :rules="{
              required: true,
              message: '更新内容不能为空',
            }"
          >
            <div class="update-desc">
              <div class="num">{{ index + 1 }}</div>
              <div class="input">
                <el-input
                  clearable
                  placeholder="请输入更新内容"
                  v-model="item.title"
                ></el-input>
              </div>
              <div
                class="iconfont icon-add"
                v-if="index == 0"
                @click="addLine"
              ></div>
              <div
                class="iconfont icon-reduce"
                v-if="index > 0"
                @click="delLine(index)"
              ></div>
            </div>
          </el-form-item>
        </div>
      </el-form-item>
    </el-form>
  </Dialog>
</template>

<script setup>
import { ref, reactive, getCurrentInstance, nextTick } from "vue";
const { proxy } = getCurrentInstance();

const api = {
  saveUpdate: "/app/saveUpdate",
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

const selectFile = (file) => {
  file = file.file;
  formData.value.file = file;
  formData.value.fileName = file.name;
};

const addLine = () => {
  formData.value.updateDescList.push({
    title: "",
  });
};

const delLine = (index) => {
  formData.value.updateDescList.splice(index, 1);
};

const formData = ref({});
const formDataRef = ref();

const rules = {
  version: [
    { required: true, message: "请输入版本号" },
    { validator: proxy.Verify.version, message: "版本号只能是数字和点" },
  ],
  fileName: [{ required: true, message: "请选择更新文件" }],
  updateType: [{ required: true, message: "请选择更新类型" }],
};

const showEdit = (data) => {
  dialogConfig.value.show = true;
  nextTick(() => {
    formDataRef.value.resetFields();
    if (data.id != null) {
      data.updateDescList = data.updateDescArray.map((item) => {
        return { title: item };
      });
      data.fileName = data.version + (data.updateType == 0 ? ".apk" : ".wgt");
    } else {
      data.updateDescList = [{ title: "" }];
    }
    formData.value = Object.assign({}, data);
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

    const updateDescArray = params.updateDescList.map((item) => {
      return item.title;
    });

    params.updateDesc = updateDescArray.join("|");
    delete params.updateDescList;
    let result = await proxy.Request({
      url: api.saveUpdate,
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
.file-select {
  display: flex;
  .file-name {
    color: #409eff;
    margin-right: 10px;
  }
}
.update-form-item {
  margin-bottom: 0px;
  .update-desc-item {
    width: 100%;
    margin-bottom: 15px;
    .update-desc {
      width: 100%;
      display: flex;
      .num {
        width: 15px;
        margin-right: 2px;
      }
      .input {
        flex: 1;
      }
      .iconfont {
        cursor: pointer;
        margin-left: 10px;
        text-align: right;
      }
    }
  }
}
</style>
