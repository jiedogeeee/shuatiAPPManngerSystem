<template>
  <div>
    <div class="top-panel">
      <el-card>
        <el-form :model="searchForm" label-width="70px" label-position="right">
          <el-row :gutter="10">
            <el-col :span="5">
              <el-form-item label="加入日期" label-width="70px">
                <el-date-picker v-model="searchForm.createTimeRange" type="daterange" range-separator="~"
                  start-placeholder="开始日期" end-placeholder="结束日期" value-format="YYYY-MM-DD" @change="loadDataList">
                </el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :span="5">
              <el-form-item label="最近使用日期" label-width="100px">
                <el-date-picker v-model="searchForm.lastUseTimeRange" type="daterange" range-separator="~"
                  start-placeholder="开始日期" end-placeholder="结束日期" value-format="YYYY-MM-DD" @change="loadDataList">
                </el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :span="5">
              <el-form-item label="品牌" label-width="40px">
                <el-input v-model="searchForm.deviceBrandFuzzy" clearable placeholder="支持模糊搜索"
                  @keyup.enter="loadDataList">
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="5">
              <el-form-item label="设备ID" label-width="60px">
                <el-input v-model="searchForm.deviceIdFuzzy" clearable placeholder="支持模糊搜索" @keyup.enter="loadDataList">
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
      </Table>
    </el-card>
  </div>
</template>

<script setup>
import { de } from 'suneditor/src/lang'
import { getCurrentInstance, nextTick, ref } from 'vue'
const { proxy } = getCurrentInstance()

const api = {
  loadDataList: '/appUser/loadDeviceList',
}
const searchForm = ref({})

const tableData = ref({})
const tableOptions = ref({})

const columns = [
  {
    label: '登录设备',
    prop: 'deviceId',
  },
  {
    label: '设备品牌',
    prop: 'deviceBrand',
  },
  {
    label: '创建时间',
    prop: 'createTime',
  },
  {
    label: '最近使用时间',
    prop: 'lastUseTime',
  },
  {
    label: '登录IP',
    prop: 'ip',
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

  if (searchForm.value.lastUseTimeRange) {
    params.lastUseTimeStart = searchForm.value.lastUseTimeRange[0]
    params.lastUseTimeEnd = searchForm.value.lastUseTimeRange[1]
  }
  delete params.createTimeRange
  delete params.lastUseTimeRange

  let result = await proxy.Request({
    url: api.loadDataList,
    params: params,
  })
  if (!result) {
    return
  }
  Object.assign(tableData.value, result.data)
}
</script>

<style lang="scss" scoped>
</style>
