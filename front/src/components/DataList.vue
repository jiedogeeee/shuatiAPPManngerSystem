<template>
  <div
    v-if="
      loading != null &&
      !loading &&
      dataSource.list != null &&
      dataSource.list.length == 0
    "
  >
    <no-data msg="空空如也" />
  </div>
  <div v-if="loading" class="skeleton">
    <el-skeleton :rows="2" animated />
  </div>
  <div v-for="item in dataSource.list" v-if="!loading">
    <slot :data="item"></slot>
  </div>
  <div
    class="pagination"
    v-if="showPagination && !loading && dataSource.pageTotal > 1"
  >
    <el-pagination
      v-if="dataSource.pageTotal > 1"
      background
      :total="dataSource.totalCount"
      :current-page.sync="dataSource.pageNo"
      layout="prev, pager, next"
      @current-change="handlePageNoChange"
      style="text-align: right"
      :page-size="15"
    ></el-pagination>
  </div>
</template>

<script setup>
const props = defineProps({
  dataSource: {
    type: Object,
  },
  loading: {
    type: Boolean,
  },
  showPagination: {
    type: Boolean,
    default: true,
  },
});

const emit = defineEmits(["loadData"]);
const handlePageNoChange = (pageNo) => {
  props.dataSource.pageNo = pageNo;
  emit("loadData");
};
</script>

<style lang="scss">
.pagination {
  padding: 10px 0px 5px 10px;
}
.skeleton {
  padding: 15px;
}
</style>
