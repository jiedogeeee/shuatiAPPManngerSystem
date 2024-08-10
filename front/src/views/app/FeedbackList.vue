<template>
  <div>
    <div class="top-panel">
      <el-card>
        <el-form :model="searchForm" label-width="70px" label-position="right">
          <el-row :gutter="10">
            <el-col :span="5">
              <el-form-item label="创建日期" label-width="70px">
                <el-date-picker v-model="searchForm.createTimeRange" type="daterange" range-separator="~"
                  start-placeholder="开始日期" end-placeholder="结束日期" value-format="YYYY-MM-DD" @change="loadDataList">
                </el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :span="5">
              <!-- 下拉框 -->
              <el-form-item label="状态">
                <el-select clearable placeholder="请选择状态" v-model="searchForm.status">
                  <el-option :value="0" label="待回复"></el-option>
                  <el-option :value="1" label="已回复"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="5">
              <el-form-item label="创建人" label-width="60px">
                <el-input v-model="searchForm.nickNameFuzzy" clearable placeholder="支持模糊搜索" @keyup.enter="loadDataList">
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="4" :style="{ paddingLeft: '10px' }">
              <el-button type="success" @click="loadDataList()">查询
              </el-button>
            </el-col>
          </el-row>
        </el-form>
      </el-card>
    </div>
    <el-card class="table-data-card">
      <Table ref="tableInfoRef" :columns="columns" :fetch="loadDataList" :dataSource="tableData" :options="tableOptions"
        :extHeight="tableOptions.extHeight">
        <template #slotStatus="{ index, row }">
          <div class="row-op-panel">
            <Badge showType="green" text="已回复" v-if="row.status == 1"></Badge>
            <Badge showType="red" text="待回复" v-if="row.status == 0"></Badge>
          </div>
        </template>

        <template #slotOperation="{ index, row }">
          <div class="row-op-panel">
            <a v-has="proxy.PermissionCode.app.app_feedback_reply" href="javascript:void(0)"
              @click="showReply(row)">回复</a>
          </div>
        </template>
      </Table>
    </el-card>
    <FeedbackReply ref="feedbackReplyRef" @reload="loadDataList"></FeedbackReply>
  </div>
</template>

<script setup>
import FeedbackReply from './FeedbackReply.vue'
import { getCurrentInstance, nextTick, ref } from 'vue'
const { proxy } = getCurrentInstance()

const api = {
  loadDataList: '/appFeedback/loadFeedback',
  replyFeedback: '/appFeedback/replyFeedback',
}
const searchForm = ref({})

const tableData = ref({})
const tableOptions = ref({})

const columns = [
  {
    label: '问题',
    prop: 'content',
  },
  {
    label: '昵称',
    prop: 'nickName',
    width: 150,
  },
  {
    label: '回复状态',
    prop: 'status',
    scopedSlots: 'slotStatus',
    width: 150,
  },
  {
    label: '创建时间',
    prop: 'createTime',
    width: 200,
  },
  {
    label: '操作',
    prop: 'operation',
    width: 100,
    scopedSlots: 'slotOperation',
  },
]

const tableInfoRef = ref()

const loadDataList = async () => {
  let params = {
    pageNo: tableData.value.pageNo,
    pageSize: tableData.value.pageSize,
  }
  Object.assign(params, searchForm.value)

  if (searchForm.value.createTimeRange) {
    params.createTimeStart = searchForm.value.createTimeRange[0]
    params.createTimeEnd = searchForm.value.createTimeRange[1]
  }
  delete params.createTimeRange
  let result = await proxy.Request({
    url: api.loadDataList,
    params: params,
  })
  if (!result) {
    return
  }
  Object.assign(tableData.value, result.data)
}

const feedbackReplyRef = ref()
const showReply = (data) => {
  feedbackReplyRef.value.show(data.feedbackId)
}
</script>

<style lang="scss" scoped>
</style>
