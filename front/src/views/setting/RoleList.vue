<template>
  <div>
    <div class="top-panel">
      <el-card>
        <el-form :model="searchForm" label-width="70px" label-position="right">
          <el-row>
            <el-col :span="5">
              <el-form-item label="角色名称">
                <el-input
                  class="password-input"
                  v-model="searchForm.roleNameFuzzy"
                  clearable
                  placeholder="支持模糊搜索"
                  @keyup.enter.native="loadDataList"
                >
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="5">
              <el-form-item label="描述">
                <el-input
                  class="password-input"
                  v-model="searchForm.roleDescFuzzy"
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
              <el-button
                type="primary"
                @click="showEdit()"
                v-has="proxy.PermissionCode.role.edit"
                >新增角色
              </el-button>
            </el-col>
          </el-row>
        </el-form>
      </el-card>
    </div>
    <el-row
      :gutter="20"
      :style="{ 'margin-top': '10px' }"
      class="table-data-card"
    >
      <el-col :span="18">
        <el-card class="box-card">
          <template #header>
            <span>角色列表</span>
          </template>
          <Table
            ref="tableInfoRef"
            :columns="columns"
            :fetch="loadDataList"
            :dataSource="tableData"
            :options="tableOptions"
            :extHeight="tableOptions.extHeight"
            @row-click="handleRowClick"
          >
            <template #operation="{ index, row }">
              <div class="row-op-panel">
                <a
                  class="a-link"
                  href="javascript:void(0)"
                  @click="showEdit(row)"
                  v-has="proxy.PermissionCode.role.edit"
                  >修改</a
                >
                <a
                  class="a-link"
                  href="javascript:void(0)"
                  @click.prevent="delRole(row)"
                  v-has="proxy.PermissionCode.role.del"
                  >删除</a
                >
              </div>
            </template>
          </Table>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="box-card">
          <template #header>
            <span>菜单信息</span>
            <el-button
              type="primary"
              @click="saveRoleMenu"
              :style="{ float: 'right' }"
              v-has="proxy.PermissionCode.role.edit"
              >保存</el-button
            >
          </template>
          <div class="detail-tree-panel">
            <el-tree
              ref="menuTreeRef"
              node-key="menuId"
              show-checkbox
              :data="treeData"
              default-expand-all
              v-if="treeData.length"
              :props="replaceFields"
              :check-strictly="false"
            />
          </div>
        </el-card>
      </el-col>
    </el-row>
    <RoleEdit
      ref="roleEditRef"
      @reload="loadDataList"
      :treeData="treeData"
    ></RoleEdit>
  </div>
</template>

<script setup>
import RoleEdit from "./RoleEdit.vue";
import { getCurrentInstance, nextTick, ref } from "vue";
const { proxy } = getCurrentInstance();

const api = {
  loadRole: "/settings/loadRoles",
  loadMenu: "/settings/menuList",
  saveRoleMenu: "/settings/saveRoleMenu",
  roleDetail: "/settings/getRoleByRoleId",
  delRole: "/settings/delRole",
};
const searchForm = ref({});

const tableData = ref({});
const tableOptions = ref({
  extHeight: 125,
});

const columns = [
  {
    label: "角色名称",
    prop: "roleName",
  },
  {
    label: "描述",
    prop: "roleDesc",
  },
  {
    label: "创建时间",
    prop: "createTime",
  },
  {
    width: 100,
    label: "操作",
    scopedSlots: "operation",
  },
];

const tableInfoRef = ref();
const currentRow = ref({});

const loadDataList = async () => {
  let params = {
    pageNo: tableData.value.pageNo,
    pageSize: tableData.value.pageSize,
  };
  Object.assign(params, searchForm.value);
  let result = await proxy.Request({
    url: api.loadRole,
    params: params,
  });
  if (!result) {
    return;
  }
  Object.assign(tableData.value, result.data);
  if (
    Object.keys(currentRow.value).length === 0 &&
    result.data.list.length > 0
  ) {
    Object.assign(currentRow.value, result.data.list[0]);
    handleRowClick(currentRow.value);
  }

  tableInfoRef.value.setCurrentRow("roleId", currentRow.value.roleId);
};

//菜单
const replaceFields = ref({
  label: "menuName",
});

const treeData = ref([]);
const loadMenu = async () => {
  let result = await proxy.Request({
    url: api.loadMenu,
  });
  if (!result) {
    return;
  }
  treeData.value = result.data;
};
loadMenu();

const menuTreeRef = ref();
const handleRowClick = async (row) => {
  Object.assign(currentRow.value, row);

  let result = await proxy.Request({
    url: api.roleDetail,
    params: {
      roleId: row.roleId,
    },
  });
  if (!result) {
    return;
  }
  menuTreeRef.value.setCheckedKeys(result.data.menuIds);
};

//保存角色菜单
const saveRoleMenu = async () => {
  let menuIdArray = menuTreeRef.value.getCheckedKeys();
  let halfMenuIdArray = menuTreeRef.value.getHalfCheckedKeys() || [];
  let result = await proxy.Request({
    url: api.saveRoleMenu,
    params: {
      roleId: currentRow.value.roleId,
      menuIds: menuIdArray.join(","),
      halfMenuIds: halfMenuIdArray.join(","),
    },
  });
  if (!result) {
    return;
  }
  proxy.Message.success("保存成功");
};

//删除
const delRole = (data) => {
  proxy.Confirm(`确定要删除【${data.roleName}】吗？`, async () => {
    let result = await proxy.Request({
      url: api.delRole,
      params: {
        roleId: data.roleId,
      },
    });
    if (!result) {
      return;
    }
    proxy.Message.success("保存成功");
    currentRow.value = {};
    loadDataList();
  });
};

const roleEditRef = ref();
const showEdit = (data = {}) => {
  roleEditRef.value.showEdit(Object.assign({}, data));
};
</script>

<style lang="scss" scoped>
.detail-tree-panel {
  height: calc(100vh - 273px);
  overflow: auto;
  width: 100%;
}
</style>
