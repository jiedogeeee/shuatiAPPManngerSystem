<template>
  <div>
    <div class="top-panel">
      <el-card>
        <el-form :model="searchForm" label-width="70px" label-position="right">
          <el-row>
            <el-col :span="5">
              <el-form-item label="标题" label-width="50px">
                <el-input v-model="searchForm.titleFuzzy" clearable placeholder="支持模糊搜索" @keyup.enter="loadDataList">
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="5">
              <el-form-item label="分类" label-width="60px">
                <CategorySelect :type="1" v-model="searchForm.categoryId" @change="loadDataList"></CategorySelect>
              </el-form-item>
            </el-col>
            <el-col :span="5">
              <el-form-item label="难度" label-width="50px">
                <el-select clearable placeholder="请选择难度" v-model="searchForm.difficultyLevel">
                  <el-option :value="1" label="一星"></el-option>
                  <el-option :value="2" label="二星"></el-option>
                  <el-option :value="3" label="三星"></el-option>
                  <el-option :value="4" label="四星"></el-option>
                  <el-option :value="5" label="五星"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="5">
              <el-form-item label="问题类型" label-width="80px">
                <el-select clearable placeholder="请选择类型" v-model="searchForm.questionType">
                  <el-option :value="item.value" :label="item.text" v-for="item in questionTypeList"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row class="second-row">
            <el-col :span="5">
              <el-form-item label="状态" label-width="50px">
                <el-select clearable placeholder="请选择状态" v-model="searchForm.status">
                  <el-option :value="0" label="待发布"></el-option>
                  <el-option :value="1" label="已发布"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="5">
              <el-form-item label="创建人" label-width="60px">
                <el-input v-model="searchForm.createUserNameFuzzy" clearable placeholder="支持模糊搜索"
                  @keyup.enter="loadDataList">
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="14" :style="{ paddingLeft: '10px' }">
              <el-button-group>
                <el-button type="success" @click="loadDataList()">查询
                </el-button>
                <el-button type="primary" @click="showEdit()" v-has="proxy.PermissionCode.examQuestion.edit">新增问题
                </el-button>
                <el-button type="primary" @click="importData()" v-has="proxy.PermissionCode.examQuestion.import">批量导入
                </el-button>
                <el-button :disabled="selectRowList.length == 0" type="primary" @click="postQuestionBatch"
                  v-has="proxy.PermissionCode.examQuestion.post">批量发布
                </el-button>
                <el-button :disabled="selectRowList.length == 0" type="danger" @click="delQuestionBatch"
                  v-has="proxy.PermissionCode.examQuestion.del_batch">批量删除
                </el-button>
              </el-button-group>
            </el-col>
          </el-row>
        </el-form>
      </el-card>
    </div>
    <el-card class="table-data-card">
      <Table ref="tableInfoRef" :columns="columns" :fetch="loadDataList" :dataSource="tableData" :options="tableOptions"
        :extHeight="tableOptions.extHeight" :selected="selectedHandler" @rowSelected="rowSelected">
        <template #slotTitle="{ index, row }">
          <span class="a-link" @click="showDetailHandler(row)">{{
            row.title
          }}</span>
        </template>

        <template #slotDifficultyLevel="{ index, row }">
          <el-rate v-model="row.difficultyLevel" :disabled="true"></el-rate>
        </template>

        <template #slotQuestionType="{ index, row }">
          {{ QUESTION_TYPE[row.questionType] }}
        </template>

        <template #slotStatus="{ index, row }">
          <Badge showType="orange" text="待发布" v-if="row.status == 0"></Badge>
          <Badge showType="green" text="已发布" v-if="row.status == 1"></Badge>
        </template>

        <template #slotOperation="{ index, row }">
          <div class="row-op-panel">
            <a class="a-link" href="javascript:void(0)" @click="showEdit(row)"
              v-has="proxy.PermissionCode.examQuestion.edit" v-if="
                row.status == 0 &&
                (row.createUserId == userInfo.userId || userInfo.superAdmin)
              ">修改</a>
            <a class="a-link" href="javascript:void(0)" @click="delQuestion(row)"
              v-has="proxy.PermissionCode.examQuestion.del" v-if="
                row.status == 0 &&
                (row.createUserId == userInfo.userId || userInfo.superAdmin)
              ">删除</a>
            <a class="a-link" href="javascript:void(0)" @click="postQuestion(row)"
              v-has="proxy.PermissionCode.examQuestion.post" v-if="row.status == 0">发布</a>
            <a class="a-link" href="javascript:void(0)" @click="cancelPostQuestion(row)"
              v-has="proxy.PermissionCode.examQuestion.post" v-if="row.status == 1">取消发布</a>
          </div>
        </template>
      </Table>
    </el-card>

    <ExamQuestionEdit ref="questionEditRef" @reload="loadDataList"></ExamQuestionEdit>

    <ImportData ref="importDataRef" :type="1" @reload="loadDataList"></ImportData>

    <ShowDetail ref="showDetailRef" :showType="2"></ShowDetail>
  </div>
</template>

<script setup>
import { QUESTION_TYPE } from '@/utils/Constants.js'
import ShowDetail from '@/components/content/ShowDetail.vue'
import ExamQuestionEdit from './ExamQuestionEdit.vue'
import { computed, getCurrentInstance, nextTick, ref } from 'vue'
const { proxy } = getCurrentInstance()

const userInfo = ref(
  JSON.parse(sessionStorage.getItem('userInfo')) || { menuList: [] }
)

const questionTypeList = computed(() => {
  const questionTypeArray = []
  for (let item in QUESTION_TYPE) {
    questionTypeArray.push({
      value: item,
      text: QUESTION_TYPE[item],
    })
  }
  return questionTypeArray
})

const api = {
  loadDataList: '/examQuestion/loadDataList',
  delExamQuestion: '/examQuestion/delExamQuestion',
  delExamQuestionBatch: '/examQuestion/delExamQuestionBatch',
  postExamQuestion: '/examQuestion/postExamQuestion',
  cancelPostExamQuestion: '/examQuestion/cancelPostExamQuestion',
}
const searchForm = ref({})

const tableData = ref({})
const tableOptions = ref({
  selectType: 'checkbox',
  extHeight: '105',
})

const columns = [
  {
    label: 'ID',
    prop: 'questionId',
    width: 80,
  },
  {
    label: '标题',
    prop: 'title',
    scopedSlots: 'slotTitle',
  },
  {
    label: '分类',
    prop: 'categoryName',
    width: 150,
  },
  {
    label: '难度',
    prop: 'difficultyLevel',
    width: 130,
    scopedSlots: 'slotDifficultyLevel',
  },
  {
    label: '问题类型',
    prop: 'questionType',
    width: 130,
    scopedSlots: 'slotQuestionType',
  },
  {
    label: '创建人',
    prop: 'createUserName',
    width: 150,
  },
  {
    label: '创建时间',
    prop: 'createTime',
    width: 180,
  },
  {
    label: '发布状态',
    prop: 'status',
    scopedSlots: 'slotStatus',
    width: 90,
  },
  {
    label: '操作',
    width: 130,
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
  let result = await proxy.Request({
    url: api.loadDataList,
    params: params,
  })
  if (!result) {
    return
  }
  Object.assign(tableData.value, result.data)
}

const questionEditRef = ref()
const showEdit = (data = {}) => {
  questionEditRef.value.showEdit(Object.assign({}, data))
}

//导入
const importDataRef = ref()
const importData = () => {
  importDataRef.value.showImport()
}

const selectedHandler = (row, index) => {
  return row.status == 0
}

//已经选中
const selectRowList = ref([])
const rowSelected = (rows) => {
  selectRowList.value = rows.map((item) => {
    return item.questionId
  })
}

//删除
const delQuestion = (data) => {
  proxy.Confirm(`确定要删除【${data.title}】吗？`, async () => {
    delQuestionDone({ questionId: data.questionId }, api.delExamQuestion)
  })
}

const delQuestionBatch = async (params, url) => {
  proxy.Confirm(`确定要删除这${selectRowList.value.length}条记录吗？`, () => {
    delQuestionDone(
      { questionIds: selectRowList.value.join(',') },
      api.delExamQuestionBatch
    )
  })
}

const delQuestionDone = async (params, url) => {
  let result = await proxy.Request({
    url: url,
    params: params,
  })
  if (!result) {
    return
  }
  proxy.Message.success('删除成功')
  loadDataList()
}

//发布
const postQuestion = (data) => {
  proxy.Confirm(`确定要发布【${data.title}】吗？`, () => {
    postQuestionDone(data.questionId)
  })
}

const postQuestionBatch = (params, url) => {
  proxy.Confirm(`确定要发布这${selectRowList.value.length}条记录吗？`, () => {
    postQuestionDone(selectRowList.value.join(','))
  })
}

const postQuestionDone = async (questionIds) => {
  let result = await proxy.Request({
    url: api.postExamQuestion,
    params: {
      questionIds,
    },
  })
  if (!result) {
    return
  }
  proxy.Message.success('发布成功')
  loadDataList()
}

const cancelPostQuestion = (data) => {
  proxy.Confirm(`确定要取消发布${data.title}吗？`, async () => {
    let result = await proxy.Request({
      url: api.cancelPostExamQuestion,
      params: {
        questionIds: data.questionId,
      },
    })
    if (!result) {
      return
    }
    proxy.Message.success('取消发布成功')
    loadDataList()
  })
}

const showDetailRef = ref()
const showDetailHandler = (data) => {
  showDetailRef.value.showDetail(data.questionId, searchForm.value)
}
</script>

<style lang="scss" scoped>
</style>
