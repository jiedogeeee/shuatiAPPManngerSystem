<template>
  <div>
    <div class="top-panel">
      <el-card>
        <el-button
          type="primary"
          @click="showEdit()"
          v-has="proxy.PermissionCode.app.app_carousel_edit"
          >新增轮播图
        </el-button>
      </el-card>
    </div>
    <el-card class="table-data-card">
      <template #header>
        <span>轮播图列表</span>
      </template>
      <Table
        ref="tableInfoRef"
        :columns="columns"
        :fetch="loadDataList"
        :dataSource="tableData"
        :options="tableOptions"
        :extHeight="tableOptions.extHeight"
        :showPagination="false"
      >
        <template #slotImgPath="{ index, row }">
          <Cover :cover="row.imgPath" :width="330" :height="180"></Cover>
        </template>

        <template #slotObjectType="{ index, row }">
          {{ OBJEC_TYPE_MAP[row.objectType] }}
        </template>

        <template #slotObject="{ index, row }">
          <div v-if="row.objectType == 3">{{ row.outerLink }}</div>
          <div v-else>{{ row.objectId }}</div>
        </template>

        <template #slotOperation="{ index, row }">
          <div class="row-op-panel">
            <a
              class="a-link"
              href="javascript:void(0)"
              @click="showEdit(row)"
              v-has="proxy.PermissionCode.app.app_carousel_edit"
              >修改</a
            >
            <a
              class="a-link"
              href="javascript:void(0)"
              v-has="proxy.PermissionCode.app.app_carousel_edit"
              @click="delCarousel(row)"
              >删除</a
            >
            <a
              href="javascript:void(0)"
              v-has="proxy.PermissionCode.app.app_carousel_edit"
              @click="changeSort(index, 'up')"
              :class="[index == 0 ? 'not-allow' : 'a-link']"
              >上移</a
            >
            <a
              href="javascript:void(0)"
              v-has="proxy.PermissionCode.app.app_carousel_edit"
              @click="changeSort(index, 'down')"
              :class="[
                index == tableData.list.length - 1 ? 'not-allow' : 'a-link',
              ]"
              >下移</a
            >
          </div>
        </template>
      </Table>
    </el-card>
    <CarouselEdit ref="carouselEditRef" @reload="loadDataList"></CarouselEdit>
  </div>
</template>

<script setup>
import { OBJEC_TYPE_MAP } from "@/utils/Constants.js";
import CarouselEdit from "./CarouselEdit.vue";
import { getCurrentInstance, nextTick, ref } from "vue";
const { proxy } = getCurrentInstance();

const userInfo = ref(
  JSON.parse(sessionStorage.getItem("userInfo")) || { menuList: [] }
);

const api = {
  loadDataList: "/appCarousel/loadDataList",
  saveCarousel: "/appCarousel/saveCarousel",
  delCarousel: "/appCarousel/delCarousel",
  changeSort: "/appCarousel/changeSort",
};
const searchForm = ref({});

const tableData = ref({});
const tableOptions = ref({
  extHeight: 125,
});

const columns = [
  {
    label: "轮播图",
    prop: "imgPath",
    scopedSlots: "slotImgPath",
  },
  {
    label: "类型",
    prop: "objectType",
    scopedSlots: "slotObjectType",
  },
  {
    label: "主体ID/链接",
    prop: "object",
    scopedSlots: "slotObject",
  },
  {
    label: "操作",
    prop: "operation",
    scopedSlots: "slotOperation",
  },
];

const tableInfoRef = ref();

const loadDataList = async () => {
  let result = await proxy.Request({
    url: api.loadDataList,
  });
  if (!result) {
    return;
  }
  tableData.value.list = result.data;
};

//删除
const delCarousel = (data) => {
  proxy.Confirm(`确定要删除吗？`, async () => {
    let result = await proxy.Request({
      url: api.delCarousel,
      params: {
        carouselId: data.carouselId,
      },
    });
    if (!result) {
      return;
    }
    proxy.Message.success("操作成功");
    loadDataList();
  });
};

const changeSort = async (index, type) => {
  let dataList = tableData.value.list;
  if (
    (type === "down" && index == dataList.length - 1) ||
    (type == "up" && index == 0)
  ) {
    return;
  }
  let temp = dataList[index];
  let number = type == "down" ? 1 : -1;
  dataList.splice(index, 1);

  dataList.splice(index + number, 0, temp);

  let carouselIds = [];
  dataList.forEach((element) => {
    carouselIds.push(element.carouselId);
  });

  let result = await proxy.Request({
    url: api.changeSort,
    params: {
      carouselIds: carouselIds.join(","),
    },
  });
  if (!result) {
    return;
  }
  proxy.Message.success("重新排序成功");
  loadDataList();
};

const carouselEditRef = ref();
const showEdit = (data = {}) => {
  carouselEditRef.value.showEdit(Object.assign({}, data));
};
</script>

<style lang="scss" scoped>
.detail-tree-panel {
  height: calc(100vh - 273px);
  overflow: auto;
  width: 100%;
}
</style>
