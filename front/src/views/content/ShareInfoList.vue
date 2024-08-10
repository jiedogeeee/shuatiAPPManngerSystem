<template>
  <div>
    <div class="top-panel">
      <el-card>
        <el-form :model="searchForm" label-width="70px" label-position="right">
          <el-row>
            <el-col :span="4">
              <el-form-item label="标题" label-width="40px">
                <el-input
                  class="password-input"
                  v-model="searchForm.titleFuzzy"
                  clearable
                  placeholder="支持模糊搜索"
                  @keyup.enter="loadDataList"
                >
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="3">
              <!-- 下拉框 -->
              <el-form-item label="状态" prop="" :style="{ width: '100%' }">
                <el-select
                  clearable
                  placeholder="请选择状态"
                  v-model="searchForm.status"
                  @change="loadDataList"
                >
                  <el-option :value="0" label="待发布"></el-option>
                  <el-option :value="1" label="已发布"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="3">
              <el-form-item label="创建人" prop="" :style="{ width: '100%' }">
                <el-input
                  class="password-input"
                  v-model="searchForm.createUserNameFuzzy"
                  clearable
                  placeholder="支持模糊搜索"
                  @keyup.enter="loadDataList"
                >
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="11" :style="{ paddingLeft: '10px' }">
              <el-button-group>
                <el-button type="success" @click="loadDataList()"
                  >查询
                </el-button>
                <el-button
                  type="primary"
                  @click="showEdit()"
                  v-has="proxy.PermissionCode.share.edit"
                  >新增文章
                </el-button>
                <el-button
                  type="primary"
                  :disabled="selectRowList.length == 0"
                  @click="postShareBatch"
                  v-has="proxy.PermissionCode.share.post"
                  >批量发布</el-button
                >
                <el-button
                  type="danger"
                  :disabled="selectRowList.length == 0"
                  @click="delShareBatch"
                  v-has="proxy.PermissionCode.share.del_batch"
                  >批量删除</el-button
                >
              </el-button-group>
            </el-col>
          </el-row>
        </el-form>
      </el-card>
    </div>
    <el-card class="table-data-card">
      <Table
        :columns="columns"
        :fetch="loadDataList"
        :dataSource="tableData"
        :options="tableOptions"
        @rowSelected="rowSelected"
        :selected="selectedHandler"
      >
        <!--封面-->
        <template #slotCover="{ index, row }">
          <Cover
            v-if="row.coverType == 0"
            title="无封面"
            bgColor="#979696"
            :width="100"
            :height="100"
          ></Cover>
          <Cover
            :cover="row.coverPath"
            :width="200"
            v-if="row.coverType == 1"
          ></Cover>
          <Cover
            :cover="row.coverPath"
            :width="100"
            :height="100"
            v-if="row.coverType == 2"
          ></Cover>
        </template>
        <template #slotTitle="{ index, row }">
          <a
            href="javascript:void(0)"
            class="a-link"
            @click="showDetail(row)"
            >{{ row.title }}</a
          >
        </template>
        <template #slotDifficultyLevel="{ index, row }">
          <el-rate v-model="row.difficultyLevel" :disabled="true" />
        </template>
        <template #slotStatus="{ index, row }">
          <Badge showType="orange" text="待发布" v-if="row.status == 0"></Badge>
          <Badge showType="green" text="已发布" v-if="row.status == 1"></Badge>
        </template>
        <template #slotOperation="{ index, row }">
          <div class="row-op-panel">
            <a
              href="javascript:void(0)"
              @click="showEdit(row)"
              v-if="
                (row.status == 0 && row.createUserId == userInfo.userId) ||
                userInfo.superAdmin
              "
              v-has="proxy.PermissionCode.share.edit"
              >修改</a
            >
            <a
              href="javascript:void(0)"
              @click="delShare(row)"
              v-if="
                row.status == 0 &&
                (row.createUserId == userInfo.userId || userInfo.superAdmin)
              "
              v-has="proxy.PermissionCode.share.del"
              >删除</a
            >
            <a
              href="javascript:void(0)"
              @click="postShare(row)"
              v-if="row.status == 0"
              v-has="proxy.PermissionCode.share.post"
              >发布</a
            >
            <a
              href="javascript:void(0)"
              @click="cancelPostShare(row)"
              v-if="row.status == 1"
              v-has="proxy.PermissionCode.share.post"
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
const api = {
  loadDataList: "/shareInfo/loadDataList",
  delShare: "/shareInfo/delShare",
  delShareBatch: "/shareInfo/delShareBatch",
  postShare: "/shareInfo/postShare",
  cancelPostShare: "/shareInfo/cancelPostShare",
};
//用户信息
const userInfo = ref(JSON.parse(sessionStorage.getItem("userInfo")) || {});

const tableData = ref({});
const tableOptions = {
  selectType: "checkbox",
};
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

const searchForm = ref({});

//批量选择
const selectRowList = ref([]);
const rowSelected = (rows) => {
  selectRowList.value = rows.map((item) => {
    return item.shareId;
  });
};

const selectedHandler = (row, index) => {
  return row.status == 0;
};

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
const showEdit = (data) => {
  shareEditRef.value.showEditShare(data);
};

//删除
const delShare = (data) => {
  proxy.Confirm(`确定要删除【${data.title}】吗？`, () => {
    delShareDone(data.shareId, api.delShare);
  });
};

const delShareBatch = () => {
  proxy.Confirm(`确定要删除这${selectRowList.value.length}条记录吗？`, () => {
    delShareDone(selectRowList.value.join(","), api.delShareBatch);
  });
};

const delShareDone = async (shareIds, url) => {
  let result = await proxy.Request({
    url: url,
    params: {
      shareIds,
    },
  });
  if (!result) {
    return;
  }
  proxy.Message.success("删除成功");
  loadDataList();
};

//发布
const postShare = (data) => {
  proxy.Confirm(`确定要发布【${data.title}】吗？`, () => {
    postShareDone(data.shareId);
  });
};

const postShareBatch = () => {
  proxy.Confirm(`确定要发布这${selectRowList.value.length}条记录吗？`, () => {
    postShareDone(selectRowList.value.join(","));
  });
};

const postShareDone = async (shareIds) => {
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

//取消发布
const cancelPostShare = (data) => {
  proxy.Confirm(`确定要取消发布【${data.title}】吗？`, async () => {
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
const showDetail = (row) => {
  showDetailRef.value.show(row.shareId, searchForm.value);
};
</script>
<style lang="scss" scoped>
.check-span-item {
  float: left;
  margin-right: 10px;
  line-height: 20px;
}
</style>
