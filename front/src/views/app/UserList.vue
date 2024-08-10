<template>
  <div>
    <div class="top-panel">
      <el-card>
        <el-form :model="searchForm" label-width="70px" label-position="right">
          <el-row :gutter="10">
            <el-col :span="5">
              <el-form-item label="加入日期" label-width="70px">
                <el-date-picker v-model="searchForm.joinTimeRange" type="daterange" range-separator="~"
                  start-placeholder="开始日期" end-placeholder="结束日期" value-format="YYYY-MM-DD" @change="loadDataList">
                </el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :span="5">
              <el-form-item label="邮箱" label-width="40px">
                <el-input v-model="searchForm.emailFuzzy" clearable placeholder="支持模糊搜索" @keyup.enter="loadDataList">
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="5">
              <el-form-item label="设备ID" label-width="60px">
                <el-input v-model="searchForm.lastUseDeviceIdFuzzy" clearable placeholder="支持模糊搜索"
                  @keyup.enter="loadDataList">
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
        <template #slotNickName="{ index, row }">
          <span>{{ row.nickName }}</span>
          <span v-if="row.sex == 0">(女)</span>
          <span v-if="row.sex == 1">(男)</span>
        </template>

        <template #slotStatus="{ index, row }">
          <div class="row-op-panel">
            <Badge showType="green" text="启用" v-if="row.status == 1"></Badge>
            <Badge showType="red" text="禁用" v-if="row.status == 0"></Badge>
          </div>
        </template>

        <template #slotOperation="{ index, row }">
          <div class="row-op-panel">
            <a v-has="proxy.PermissionCode.app.app_user_edit" href="javascript:void(0)"
              @click="changeAccountStatus(row)">{{ row.status == 0 ? "启用" : "禁用" }}</a>
          </div>
        </template>
      </Table>
    </el-card>
  </div>
</template>

<script setup>
import { de } from 'suneditor/src/lang'
import { getCurrentInstance, nextTick, ref } from 'vue'
const { proxy } = getCurrentInstance()

const api = {
  loadDataList: '/appUser/loadDataList',
  updateStatus: '/appUser/updateStatus',
}
const searchForm = ref({})

const tableData = ref({})
const tableOptions = ref({})

const columns = [
  {
    label: '邮箱',
    prop: 'email',
    width: 250,
  },
  {
    label: '昵称',
    prop: 'nickName',
    width: 160,
    scopedSlots: 'slotNickName',
  },
  {
    label: '加入时间',
    prop: 'joinTime',
    width: 180,
  },
  {
    label: '最后登录时间',
    prop: 'lastLoginTime',
    width: 180,
  },
  {
    label: '登录设备',
    prop: 'lastUseDeviceId',
  },
  {
    label: '设备品牌',
    prop: 'lastUseDeviceBrand',
    width: 100,
  },
  {
    label: '最后登录IP',
    prop: 'lastLoginIp',
    width: 180,
  },
  {
    label: '状态',
    prop: 'status',
    width: 100,
    scopedSlots: 'slotStatus',
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

  if (searchForm.value.joinTimeRange) {
    params.joinTimeStart = searchForm.value.joinTimeRange[0]
    params.joinTimeEnd = searchForm.value.joinTimeRange[1]
  }
  delete params.joinTimeRange
  let result = await proxy.Request({
    url: api.loadDataList,
    params: params,
  })
  if (!result) {
    return
  }
  Object.assign(tableData.value, result.data)
}

const changeAccountStatus = (data) => {
  let status = data.status == 0 ? 1 : 0
  let info = status == 0 ? '禁用' : '启用'
  proxy.Confirm(`确定要【${info}】【${data.nickName}】吗？`, async () => {
    let result = await proxy.Request({
      url: api.updateStatus,
      params: {
        userId: data.userId,
        status: status,
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
