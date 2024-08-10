<template>
  <div>
    <div class="top-panel">
      <el-card>
        <el-form :model="searchForm" label-width="70px" label-position="right">
          <el-row :gutter="5">
            <el-col :span="5">
              <el-form-item label="加入日期" label-width="70px">
                <el-date-picker
                  v-model="searchForm.createTimeRange"
                  type="daterange"
                  range-separator="~"
                  start-placeholder="开始日期"
                  end-placeholder="结束日期"
                  value-format="YYYY-MM-DD"
                  @change="loadDataList"
                ></el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :span="5">
              <el-form-item label="最近使用日期" label-width="100px">
                <el-date-picker
                  v-model="searchForm.lastUseTimeRange"
                  type="daterange"
                  range-separator="~"
                  start-placeholder="开始日期"
                  end-placeholder="结束日期"
                  value-format="YYYY-MM-DD"
                  @change="loadDataList"
                ></el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :span="3">
              <el-form-item label="品牌" label-width="40px">
                <el-input
                  class="password-input"
                  v-model="searchForm.deviceBrandFuzzy"
                  clearable
                  placeholder="支持模糊搜索"
                  @keyup.enter.native="loadDataList"
                >
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="5">
              <el-form-item label="设备ID">
                <el-input
                  class="password-input"
                  v-model="searchForm.lastUseDeviceId"
                  clearable
                  placeholder="支持模糊搜索"
                  @keyup.enter.native="loadDataList"
                >
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="4" :style="{ paddingLeft: '10px' }">
              <el-button type="success" @click="loadDataList()"
                >查询
              </el-button>
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
      >
        <template #slotNickName="{ index, row }">
          <span>{{ row.nickName }}</span>
          <span v-if="row.sex == 0">(女)</span>
          <span v-if="row.sex == 1">(男)</span>
        </template>
        <template #slotStatus="{ index, row }">
          <span style="color: red" v-if="row.status == 0">禁用</span>
          <span style="color: green" v-else>启用</span>
        </template>

        <template #slotOperation="{ index, row }">
          <div class="row-op-panel">
            <a
              v-has="proxy.PermissionCode.account.updateStatus"
              href="javascript:void(0)"
              @click="changeAccountStatus(row)"
              >{{ row.status == 0 ? "启用" : "禁用" }}</a
            >
          </div>
        </template>
      </Table>
    </el-card>
  </div>
</template>
<script setup>
import { getCurrentInstance, nextTick, ref } from "vue";

const { proxy } = getCurrentInstance();

const api = {
  loadDataList: "/appUser/loadDeviceList",
};

const tableData = ref({});
const tableOptions = {};
const columns = [
  {
    label: "登录设备",
    prop: "deviceId",
  },
  {
    label: "设备品牌",
    prop: "deviceBrand",
  },
  {
    label: "创建时间",
    prop: "createTime",
  },
  {
    label: "最近使用时间",
    prop: "lastUseTime",
  },
  {
    label: "登录IP",
    prop: "ip",
  },
];

const searchForm = ref({});
const loadDataList = async () => {
  let params = {
    pageNo: tableData.value.pageNo,
    pageSize: tableData.value.pageSize,
  };
  Object.assign(params, searchForm.value);
  if (searchForm.value.createTimeRange) {
    params.createTimeStart = searchForm.value.createTimeRange[0];
    params.createTimeEnd = searchForm.value.createTimeRange[1];
  }
  if (searchForm.value.lastUseTimeRange) {
    params.lastUseTimeStart = searchForm.value.lastUseTimeRange[0];
    params.lastUseTimeEnd = searchForm.value.lastUseTimeRange[1];
  }
  delete params.createTimeRange;
  delete params.lastUseTimeRange;
  let result = await proxy.Request({
    url: api.loadDataList,
    params: params,
  });
  if (!result) {
    return;
  }
  Object.assign(tableData.value, result.data);
};
</script>
<style lang="scss" scoped>
</style>
