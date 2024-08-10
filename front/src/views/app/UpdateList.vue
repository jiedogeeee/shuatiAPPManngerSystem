<template>
  <div>
    <div class="top-panel">
      <el-card>
        <el-form :model="searchForm" label-width="70px" label-position="right">
          <el-row :gutter="10">
            <el-col :span="5">
              <el-form-item label="发布日期" label-width="70px">
                <el-date-picker v-model="searchForm.createTimeRange" type="daterange" range-separator="~"
                  start-placeholder="开始日期" end-placeholder="结束日期" value-format="YYYY-MM-DD" @change="loadDataList">
                </el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :span="4" :style="{ paddingLeft: '10px' }">
              <el-button type="success" @click="loadDataList()">查询
              </el-button>
              <el-button type="primary" @click="showEdit()" v-has="proxy.PermissionCode.app.app_update_edit">发布版本
              </el-button>
            </el-col>
          </el-row>
        </el-form>
      </el-card>
    </div>
    <el-card class="table-data-card">
      <Table ref="tableInfoRef" :columns="columns" :fetch="loadDataList" :dataSource="tableData" :options="tableOptions"
        :extHeight="tableOptions.extHeight">
        <template #slotUpdateDesc="{ index, row }">
          <div v-for="(item, num) in row.updateDescArray">
            {{ num + 1 }}、{{ item }}
          </div>
        </template>

        <template #slotUpdateType="{ index, row }">
          <div v-if="row.updateType == 0">全部更新</div>
          <div v-if="row.updateType == 1">局部热更新</div>
        </template>

        <template #slotStatus="{ index, row }">
          <div class="row-op-panel">
            <Badge showType="#f56c6c" text="未发布" v-if="row.status == 0"></Badge>
            <Badge showType="#f7ba2a" text="灰度发布" v-if="row.status == 1"></Badge>
            <Badge showType="#529b2e" text="全网发布" v-if="row.status == 2"></Badge>
          </div>
        </template>

        <template #slotOperation="{ index, row }">
          <div class="row-op-panel">
            <a v-has="proxy.PermissionCode.app.app_update_edit" href="javascript:void(0)" @click="showEdit(row)">修改</a>
            <a v-has="proxy.PermissionCode.app.app_update_post" href="javascript:void(0)"
              @click="updatePost(row)">发布</a>
            <a v-has="proxy.PermissionCode.app.app_update_edit" href="javascript:void(0)" @click="del(row)">删除</a>
          </div>
        </template>
      </Table>
    </el-card>
    <UpdateEdit ref="updateEditRef" @reload="loadDataList"></UpdateEdit>

    <UpdatePost ref="updatePostRef" @reload="loadDataList"></UpdatePost>
  </div>
</template>

<script setup>
import UpdateEdit from './UpdateEdit.vue'
import UpdatePost from './UpdatePost.vue'
import { getCurrentInstance, nextTick, ref } from 'vue'
const { proxy } = getCurrentInstance()

const api = {
  loadDataList: '/app/loadDataList',
  delUpdate: '/app/delUpdate',
}
const searchForm = ref({})

const tableData = ref({})
const tableOptions = ref({})

const columns = [
  {
    label: '版本',
    prop: 'version',
  },
  {
    label: '更新内容',
    prop: 'updateDesc',
    scopedSlots: 'slotUpdateDesc',
  },
  {
    label: '发布时间',
    prop: 'createTime',
  },
  {
    label: '更新类型',
    prop: 'updateType',
    scopedSlots: 'slotUpdateType',
  },
  {
    label: '状态',
    prop: 'status',
    scopedSlots: 'slotStatus',
  },
  {
    label: '操作',
    prop: 'operation',
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

const updateEditRef = ref()
const showEdit = (data = {}) => {
  updateEditRef.value.showEdit(data)
}

const updatePostRef = ref()
const updatePost = (data = {}) => {
  updatePostRef.value.showEdit(data)
}

//删除
const del = (data) => {
  proxy.Confirm(`确定要删除【${data.version}】吗？`, async () => {
    let result = await proxy.Request({
      url: api.delUpdate,
      params: {
        id: data.id,
      },
    })
    if (!result) {
      return
    }
    proxy.Message.success('操作成功')
    loadDataList()
  })
}
</script>

<style lang="scss" scoped>
</style>
