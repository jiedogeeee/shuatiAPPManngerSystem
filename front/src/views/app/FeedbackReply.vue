<template>
  <Dialog
    :show="dialogConfig.show"
    :title="dialogConfig.title"
    :buttons="dialogConfig.buttons"
    :showCancel="false"
    @close="dialogConfig.show = false"
    width="700px"
  >
    <div class="feedback-list">
      <div class="feed-item" v-for="data in dataList">
        <div class="feed-content-my" v-if="data.sendType == 1">
          <div class="content-panel">
            <div class="time">{{ data.createTime }}</div>
            <div class="content">{{ data.content }}</div>
          </div>
          <div class="icon">我</div>
        </div>
        <div class="feed-content-other" v-else>
          <div class="icon">{{ data.nickName.substr(0, 1) }}</div>
          <div class="content-panel">
            <div class="nick-name">{{ data.nickName }}</div>
            <div class="time">{{ data.createTime }}</div>
            <div class="content">{{ data.content }}</div>
          </div>
        </div>
      </div>
    </div>
    <div class="send-panel">
      <el-form
        :model="formData"
        :rules="rules"
        ref="formDataRef"
        label-width="80px"
        @submit.prevent
      >
        <!--input输入-->
        <el-form-item prop="content" label-width="0px">
          <el-input
            :rows="4"
            placeholder="请输入回复内容"
            max="500"
            resize="none"
            show-word-limit
            type="textarea"
            v-model="formData.content"
          ></el-input>
        </el-form-item>
        <el-button class="send-btn" type="primary" @click="sendReply"
          >发送</el-button
        >
      </el-form>
    </div>
  </Dialog>
</template>

<script setup>
import { ref, reactive, getCurrentInstance, nextTick } from "vue";
const { proxy } = getCurrentInstance();

const api = {
  loadFeedbackReply: "/appFeedback/loadFeedbackReply",
  replyFeedback: "/appFeedback/replyFeedback",
};

const pFeedbackId = ref(0);
const show = (_pFeedbackId) => {
  dialogConfig.value.show = true;
  pFeedbackId.value = _pFeedbackId;
  loadDataList();
};
defineExpose({
  show,
});

const dataList = ref([]);
const goBottom = ref(false);
const loadDataList = async () => {
  let result = await proxy.Request({
    url: api.loadFeedbackReply,
    params: {
      pFeedbackId: pFeedbackId.value,
    },
  });
  if (!result) {
    return;
  }
  dataList.value = result.data;
  if (goBottom.value) {
    nextTick(() => {
      const feedItems = document.querySelectorAll(".feed-item");
      feedItems[feedItems.length - 1].scrollIntoView();
    });
  }
};

const dialogConfig = ref({
  show: false,
  title: "回复反馈",
});

const formData = ref({});
const formDataRef = ref();

const rules = {
  content: [{ required: true, message: "请输入回复内容" }],
};

const emit = defineEmits(["reload"]);
const sendReply = async () => {
  formDataRef.value.validate(async (valid) => {
    if (!valid) {
      return;
    }
    let params = {};
    Object.assign(params, formData.value);
    params.pFeedbackId = pFeedbackId.value;
    let result = await proxy.Request({
      url: api.replyFeedback,
      params,
    });
    if (!result) {
      return;
    }
    goBottom.value = true;
    loadDataList();
    formDataRef.value.resetFields();
    emit("reload");
  });
};
</script>

<style lang="scss" scoped>
.feedback-list {
  padding: 10px;
  max-height: calc(100vh * 0.6);
  overflow: auto;
  .feed-item {
    margin-bottom: 20px;
    .icon {
      font-size: 13px;
      display: flex;
      border-radius: 10px;
      width: 50px;
      height: 50px;
      background: #9005fa;
      color: #fff;
      align-items: center;
      justify-content: center;
    }
    .content {
      display: inline-block;
      margin-top: 2px;
      padding: 8px;
      color: #474747;
      border-radius: 5px;
      text-align: left;
    }
    .content-panel {
      flex: 1;
      position: relative;
      &::after {
        content: "";
        position: absolute;
        display: block;
        width: 10px;
        height: 10px;
        background: #66f470;
        transform: rotate(45deg);
        border-radius: 2px;
        top: 30px;
      }
    }
    .feed-content-my {
      display: flex;
      padding-left: 60px;
      .icon {
        margin-left: 10px;
      }
      .content-panel {
        text-align: right;
        .time {
          font-size: 13px;
          text-align: right;
        }
        .content {
          background: #66f470;
        }
        &::after {
          right: -4px;
        }
      }
    }

    .feed-content-other {
      display: flex;
      padding-right: 60px;
      .icon {
        margin-right: 10px;
        font-size: 18px;
      }
      .content-panel {
        flex: 1;
        position: relative;
        .nick-name {
          font-size: 13px;
        }
        .time {
          font-size: 13px;
          text-align: left;
        }
        .content {
          background: #dedede;
        }
        &::after {
          top: 45px;
          left: -4px;
          background: #dedede;
        }
      }
    }
  }
}

.send-panel {
  .send-btn {
    float: right;
  }
}
</style>
