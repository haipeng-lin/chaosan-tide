<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="文化专题id" prop="culturalTopicId">
        <el-input
          v-model="queryParams.culturalTopicId"
          placeholder="请输入文化专题id"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="标签id1" prop="tagId1">
        <el-input
          v-model="queryParams.tagId1"
          placeholder="请输入标签id1"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="标签id2" prop="tagId2">
        <el-input
          v-model="queryParams.tagId2"
          placeholder="请输入标签id2"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="标签id3" prop="tagId3">
        <el-input
          v-model="queryParams.tagId3"
          placeholder="请输入标签id3"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="封面" prop="coverImg">
        <el-input
          v-model="queryParams.coverImg"
          placeholder="请输入封面"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="1-置顶，0-不置顶" prop="isTop">
        <el-input
          v-model="queryParams.isTop"
          placeholder="请输入1-置顶，0-不置顶"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="1-推荐，0-不推荐" prop="isRecommend">
        <el-input
          v-model="queryParams.isRecommend"
          placeholder="请输入1-推荐，0-不推荐"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['cultural:culturalTopicContent:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['cultural:culturalTopicContent:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['cultural:culturalTopicContent:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['cultural:culturalTopicContent:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="culturalTopicContentList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="文化专题id" align="center" prop="culturalTopicId" />
      <el-table-column label="标签id1" align="center" prop="tagId1" />
      <el-table-column label="标签id2" align="center" prop="tagId2" />
      <el-table-column label="标签id3" align="center" prop="tagId3" />
      <el-table-column label="封面" align="center" prop="coverImg" />
      <el-table-column label="内容" align="center" prop="content" />
      <el-table-column label="1-置顶，0-不置顶" align="center" prop="isTop" />
      <el-table-column label="1-推荐，0-不推荐" align="center" prop="isRecommend" />
      <el-table-column label="显示状态" align="center" prop="status" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['cultural:culturalTopicContent:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['cultural:culturalTopicContent:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改文化专题内容对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="culturalTopicContentRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="文化专题id" prop="culturalTopicId">
          <el-input v-model="form.culturalTopicId" placeholder="请输入文化专题id" />
        </el-form-item>
        <el-form-item label="标签id1" prop="tagId1">
          <el-input v-model="form.tagId1" placeholder="请输入标签id1" />
        </el-form-item>
        <el-form-item label="标签id2" prop="tagId2">
          <el-input v-model="form.tagId2" placeholder="请输入标签id2" />
        </el-form-item>
        <el-form-item label="标签id3" prop="tagId3">
          <el-input v-model="form.tagId3" placeholder="请输入标签id3" />
        </el-form-item>
        <el-form-item label="封面" prop="coverImg">
          <el-input v-model="form.coverImg" placeholder="请输入封面" />
        </el-form-item>
        <el-form-item label="内容">
          <editor v-model="form.content" :min-height="192"/>
        </el-form-item>
        <el-form-item label="1-置顶，0-不置顶" prop="isTop">
          <el-input v-model="form.isTop" placeholder="请输入1-置顶，0-不置顶" />
        </el-form-item>
        <el-form-item label="1-推荐，0-不推荐" prop="isRecommend">
          <el-input v-model="form.isRecommend" placeholder="请输入1-推荐，0-不推荐" />
        </el-form-item>
        <el-form-item label="删除标志" prop="delFlag">
          <el-input v-model="form.delFlag" placeholder="请输入删除标志" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="CulturalTopicContent">
import { listCulturalTopicContent, getCulturalTopicContent, delCulturalTopicContent, addCulturalTopicContent, updateCulturalTopicContent } from "@/api/cultural/culturalTopicContent";

const { proxy } = getCurrentInstance();

const culturalTopicContentList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    culturalTopicId: null,
    tagId1: null,
    tagId2: null,
    tagId3: null,
    coverImg: null,
    content: null,
    isTop: null,
    isRecommend: null,
    status: null,
  },
  rules: {
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询文化专题内容列表 */
function getList() {
  loading.value = true;
  listCulturalTopicContent(queryParams.value).then(response => {
    culturalTopicContentList.value = response.rows;
    total.value = response.total;
    loading.value = false;
  });
}

// 取消按钮
function cancel() {
  open.value = false;
  reset();
}

// 表单重置
function reset() {
  form.value = {
    id: null,
    culturalTopicId: null,
    tagId1: null,
    tagId2: null,
    tagId3: null,
    coverImg: null,
    content: null,
    isTop: null,
    isRecommend: null,
    status: null,
    delFlag: null,
    createBy: null,
    createTime: null,
    updateBy: null,
    updateTime: null
  };
  proxy.resetForm("culturalTopicContentRef");
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1;
  getList();
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef");
  handleQuery();
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.id);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加文化专题内容";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getCulturalTopicContent(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改文化专题内容";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["culturalTopicContentRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateCulturalTopicContent(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addCulturalTopicContent(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          getList();
        });
      }
    }
  });
}

/** 删除按钮操作 */
function handleDelete(row) {
  const _ids = row.id || ids.value;
  proxy.$modal.confirm('是否确认删除文化专题内容编号为"' + _ids + '"的数据项？').then(function() {
    return delCulturalTopicContent(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('cultural/culturalTopicContent/export', {
    ...queryParams.value
  }, `culturalTopicContent_${new Date().getTime()}.xlsx`)
}

getList();
</script>
