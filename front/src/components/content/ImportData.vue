<template>
  <div>
    <Dialog
      :show="dialogConfig.show"
      :title="dialogConfig.title"
      :buttons="dialogConfig.buttons"
      width="500px"
      :showCancel="false"
      @close="dialogConfig.show = false"
    >
      <div class="setp">1、下载导入模板</div>
      <div class="content">
        <a
          :href="`/api/file/downloadTemplate?type=${type}`"
          target="_blank"
          class="a-link"
        >
          <span class="iconfont icon-download">下载模板</span>
        </a>
      </div>
      <div class="setp">2、选择选哟导入的文件</div>
      <div class="content">
        <el-upload
          name="file"
          :show-file-list="false"
          accept=".xlsx"
          :multiple="false"
          :http-request="importData"
        >
          <el-button type="primary"
            ><span class="iconfont icon-upload"></span> 上传</el-button
          >
        </el-upload>
        <div class="tips">支持xlsx文件</div>
      </div>
    </Dialog>

    <ImportErrorData ref="importErrorDataRef"></ImportErrorData>
  </div>
</template>

<script setup>
import ImportErrorData from "./ImportErrorData.vue";
import { ref, reactive, getCurrentInstance, nextTick } from "vue";
const { proxy } = getCurrentInstance();

const props = defineProps({
  type: {
    type: Number,
  },
});

const dialogConfig = ref({
  show: false,
  title: "导入数据",
});

const showImport = () => {
  dialogConfig.value.show = true;
};

defineExpose({
  showImport,
});

const api = {
  0: "questionInfo/importQuestion",
  1: "examQuestion/importExamQuestion",
};

const emit = defineEmits(["reload"]);

const importErrorDataRef = ref();
const importData = async (file) => {
  let result = await proxy.Request({
    url: api[props.type],
    params: {
      file: file.file,
    },
  });
  if (!result) {
    return;
  }
  if (result.data.length > 0) {
    importErrorDataRef.value.showData(result.data);
  } else {
    proxy.Message.success("导入成功");
    dialogConfig.value.show = false;
    emit("reload");
  }
};
</script>

<style lang="scss" scoped>
.content {
  margin: 15px 0px;
  display: flex;
  align-items: center;
  font-size: 14px;
  .tips {
    margin-left: 10px;
    font-size: 14px;
    color: #878787;
  }
}
.iconfont {
  margin-right: 5px;
  font-size: 15px;
}
</style>
