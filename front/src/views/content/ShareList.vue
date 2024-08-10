<template>
  <div>
    <div class="top-panel">
      <el-card>
        <el-form :model="searchForm" label-width="70px" label-position="right">
          <el-row>
            <el-col :span="5">
              <el-form-item label="标题" label-width="55px">
                <el-input
                  v-model="searchForm.titleFuzzy"
                  clearable
                  placeholder="支持模糊搜索"
                  @keyup.enter="loadDataList"
                >
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="5">
              <el-form-item label="状态" label-width="50px">
                <el-select
                  clearable
                  placeholder="请选择状态"
                  v-model="searchForm.status"
                >
                  <el-option :value="0" label="待发布"></el-option>
                  <el-option :value="1" label="已发布"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="5">
              <el-form-item label="创建人" label-width="55px">
                <el-input
                  v-model="searchForm.createUserNameFuzzy"
                  clearable
                  placeholder="支持模糊搜索"
                  @keyup.enter="loadDataList"
                >
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="9" :style="{ paddingLeft: '10px' }">
              <el-button-group>
                <el-button type="success" @click="loadDataList()"
                  >查询
                </el-button>
                <el-button
                  type="primary"
                  @click="showEdit()"
                  v-has="proxy.PermissionCode.share.edit"
                  >新增分享
                </el-button>
                <el-button
                  :disabled="selectRowList.length == 0"
                  type="primary"
                  @click="postQuestionBatch"
                  v-has="proxy.PermissionCode.share.post"
                  >批量发布
                </el-button>
                <el-button
                  :disabled="selectRowList.length == 0"
                  type="danger"
                  @click="delQuestionBatch"
                  v-has="proxy.PermissionCode.share.del_batch"
                  >批量删除
                </el-button>
              </el-button-group>
            </el-col>
          </el-row>
        </el-form>
      </el-card>
    </div>
    <el-card class="table-data-card">
      <Table
        ref="tableInfoRef"
        :columns="columns"
        :fetch="loadDataList"
        :dataSource="tableData"
        :options="tableOptions"
        :extHeight="tableOptions.extHeight"
        :selected="selectedHandler"
        @rowSelected="rowSelected"
      >
        <template #slotCover="{ index, row }">
          <Cover
            v-if="row.coverType == 0"
            title="无封面"
            bgColor="#ddd"
            :width="100"
            :height="100"
          ></Cover>
          <Cover
            v-if="row.coverType == 1"
            :width="200"
            :height="100"
            :cover="row.coverPath"
          ></Cover>
          <Cover
            v-if="row.coverType == 2"
            :width="100"
            :height="100"
            :cover="row.coverPath"
          ></Cover>
        </template>

        <template #slotTitle="{ index, row }">
          <span class="a-link" @click="showDetailHandler(row)">{{
            row.title
          }}</span>
        </template>

        <template #slotStatus="{ index, row }">
          <Badge showType="orange" text="待发布" v-if="row.status == 0"></Badge>
          <Badge showType="green" text="已发布" v-if="row.status == 1"></Badge>
        </template>

        <template #slotOperation="{ index, row }">
          <div class="row-op-panel">
            <a
              class="a-link"
              href="javascript:void(0)"
              @click="showEdit(row)"
              v-has="proxy.PermissionCode.share.edit"
              v-if="
                row.status == 0 &&
                (row.createUserId == userInfo.userId || userInfo.superAdmin)
              "
              >修改</a
            >
            <a
              class="a-link"
              href="javascript:void(0)"
              @click="delQuestion(row)"
              v-has="proxy.PermissionCode.share.del"
              v-if="
                row.status == 0 &&
                (row.createUserId == userInfo.userId || userInfo.superAdmin)
              "
              >删除</a
            >
            <a
              class="a-link"
              href="javascript:void(0)"
              @click="postQuestion(row)"
              v-has="proxy.PermissionCode.share.post"
              v-if="row.status == 0"
              >发布</a
            >
            <a
              class="a-link"
              href="javascript:void(0)"
              @click="cancelPostQuestion(row)"
              v-has="proxy.PermissionCode.share.post"
              v-if="row.status == 1"
              >取消发布</a
            >
          </div>
        </template>
      </Table>
    </el-card>

    <ShareEdit ref="shareEditRef" @reload="loadDataList"></ShareEdit>

    <ShowDetail ref="showDetailRef" :showType="3"></ShowDetail>
  </div>
</template>

<script setup>
import ShowDetail from "@/components/content/ShowDetail.vue";
import ShareEdit from "./ShareEdit.vue";
import { getCurrentInstance, nextTick, ref } from "vue";
const { proxy } = getCurrentInstance();

const userInfo = ref(
  JSON.parse(sessionStorage.getItem("userInfo")) || { menuList: [] }
);

const api = {
  loadDataList: "/shareInfo/loadDataList",
  delShare: "/shareInfo/delShare",
  delShareBatch: "/shareInfo/delShareBatch",
  postShare: "/shareInfo/postShare",
  cancelPostShare: "/shareInfo/cancelPostShare",
};
const searchForm = ref({});

const tableData = ref({});
const tableOptions = ref({
  selectType: "checkbox",
  extHeight: "105",
});

const columns = [
  {
    label: "ID",
    prop: "shareId",
    width: 80,
  },
  {
    label: "封面",
    prop: "cover",
    scopedSlots: "slotCover",
    width: 220,
  },
  {
    label: "标题",
    prop: "title",
    scopedSlots: "slotTitle",
  },
  {
    label: "创建人",
    prop: "createUserName",
    width: 150,
  },
  {
    label: "创建时间",
    prop: "createTime",
    width: 180,
  },
  {
    label: "发布状态",
    prop: "status",
    scopedSlots: "slotStatus",
    width: 90,
  },
  {
    label: "操作",
    width: 130,
    scopedSlots: "slotOperation",
  },
];

const tableInfoRef = ref();

const loadDataList = async () => {
  let params = {
    pageNo: tableData.value.pageNo,
    pageSize: tableData.value.pageSize,
  };
  Object.assign(params, searchForm.value);
  let result = await proxy.Request({
    url: api.loadDataList,
    params: params,
  });
  if (!result) {
    return;
  }
  Object.assign(tableData.value, result.data);
};

const shareEditRef = ref();
const showEdit = (data = {}) => {
  shareEditRef.value.showEdit(Object.assign({}, data));
};

//导入
const importDataRef = ref();
const importData = () => {
  importDataRef.value.showImport();
};

const selectedHandler = (row, index) => {
  return row.status == 0;
};

//已经选中
const selectRowList = ref([]);
const rowSelected = (rows) => {
  selectRowList.value = rows.map((item) => {
    return item.shareId;
  });
};

//删除
const delQuestion = (data) => {
  proxy.Confirm(`确定要删除【${data.title}】吗？`, async () => {
    delQuestionDone({ shareIds: data.shareId }, api.delShare);
  });
};

const delQuestionBatch = async (params, url) => {
  proxy.Confirm(`确定要删除这${selectRowList.value.length}条记录吗？`, () => {
    delQuestionDone(
      { shareIds: selectRowList.value.join(",") },
      api.delShareBatch
    );
  });
};

const delQuestionDone = async (params, url) => {
  let result = await proxy.Request({
    url: url,
    params: params,
  });
  if (!result) {
    return;
  }
  proxy.Message.success("删除成功");
  loadDataList();
};

//发布
const postQuestion = (data) => {
  proxy.Confirm(`确定要发布【${data.title}】吗？`, () => {
    postQuestionDone(data.shareId);
  });
};

const postQuestionBatch = (params, url) => {
  proxy.Confirm(`确定要发布这${selectRowList.value.length}条记录吗？`, () => {
    postQuestionDone(selectRowList.value.join(","));
  });
};

const postQuestionDone = async (shareIds) => {
  let result = await proxy.Request({
    url: api.postShare,
    params: {
      shareIds,
    },
  });
  if (!result) {
    return;
  }
  proxy.Message.success("发布成功");
  loadDataList();
};

const cancelPostQuestion = (data) => {
  proxy.Confirm(`确定要取消发布${data.title}吗？`, async () => {
    let result = await proxy.Request({
      url: api.cancelPostShare,
      params: {
        shareIds: data.shareId,
      },
    });
    if (!result) {
      return;
    }
    proxy.Message.success("取消发布成功");
    loadDataList();
  });
};

const showDetailRef = ref();
const showDetailHandler = (data) => {
  showDetailRef.value.showDetail(data.shareId, searchForm.value);
};
</script>

<style lang="scss" scoped>
</style>
