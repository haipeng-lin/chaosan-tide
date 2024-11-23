<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="节日类别id" prop="festivalCategoryId">
        <el-input
          v-model="queryParams.festivalCategoryId"
          placeholder="请输入节日类别id"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="节日名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入节日名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="描述" prop="description">
        <el-input
          v-model="queryParams.description"
          placeholder="请输入描述"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="节日时间" prop="time">
        <el-input
          v-model="queryParams.time"
          placeholder="请输入节日时间"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="举办地点" prop="location">
        <el-input
          v-model="queryParams.location"
          placeholder="请输入举办地点"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="主办单位" prop="organizer">
        <el-input
          v-model="queryParams.organizer"
          placeholder="请输入主办单位"
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
      <el-form-item label="审批状态" prop="approvalStat">
        <el-input
          v-model="queryParams.approvalStat"
          placeholder="请输入审批状态"
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
          v-hasPermi="['cultural:culturalFestival:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['cultural:culturalFestival:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['cultural:culturalFestival:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['cultural:culturalFestival:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="culturalFestivalList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="节日类别id" align="center" prop="festivalCategoryId" />
      <el-table-column label="节日名称" align="center" prop="name" />
      <el-table-column label="描述" align="center" prop="description" />
      <el-table-column label="节日时间" align="center" prop="time" />
      <el-table-column label="举办地点" align="center" prop="location" />
      <el-table-column label="封面" align="center" prop="coverImg" width="100">
        <template #default="scope">
          <image-preview :src="scope.row.coverImg" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="主办单位" align="center" prop="organizer" />
      <el-table-column label="1-置顶，0-不置顶" align="center" prop="isTop" />
      <el-table-column label="1-推荐，0-不推荐" align="center" prop="isRecommend" />
      <el-table-column label="审批状态" align="center" prop="approvalStat" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['cultural:culturalFestival:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['cultural:culturalFestival:remove']">删除</el-button>
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

    <!-- 添加或修改节日对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="culturalFestivalRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="节日类别id" prop="festivalCategoryId">
          <el-input v-model="form.festivalCategoryId" placeholder="请输入节日类别id" />
        </el-form-item>
        <el-form-item label="节日名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入节日名称" />
        </el-form-item>
        <el-form-item label="描述" prop="description">
          <el-input v-model="form.description" placeholder="请输入描述" />
        </el-form-item>
        <el-form-item label="节日时间" prop="time">
          <el-input v-model="form.time" placeholder="请输入节日时间" />
        </el-form-item>
        <el-form-item label="举办地点" prop="location">
          <el-input v-model="form.location" placeholder="请输入举办地点" />
        </el-form-item>
        <el-form-item label="封面" prop="coverImg">
          <image-upload v-model="form.coverImg"/>
        </el-form-item>
        <el-form-item label="主办单位" prop="organizer">
          <el-input v-model="form.organizer" placeholder="请输入主办单位" />
        </el-form-item>
        <el-form-item label="1-置顶，0-不置顶" prop="isTop">
          <el-input v-model="form.isTop" placeholder="请输入1-置顶，0-不置顶" />
        </el-form-item>
        <el-form-item label="1-推荐，0-不推荐" prop="isRecommend">
          <el-input v-model="form.isRecommend" placeholder="请输入1-推荐，0-不推荐" />
        </el-form-item>
        <el-form-item label="审批状态" prop="approvalStat">
          <el-input v-model="form.approvalStat" placeholder="请输入审批状态" />
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

<script setup name="CulturalFestival">
import { listCulturalFestival, getCulturalFestival, delCulturalFestival, addCulturalFestival, updateCulturalFestival } from "@/api/cultural/culturalFestival";

const { proxy } = getCurrentInstance();

const culturalFestivalList = ref([]);
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
    festivalCategoryId: null,
    name: null,
    description: null,
    time: null,
    location: null,
    coverImg: null,
    organizer: null,
    isTop: null,
    isRecommend: null,
    approvalStat: null,
  },
  rules: {
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询节日列表 */
function getList() {
  loading.value = true;
  listCulturalFestival(queryParams.value).then(response => {
    culturalFestivalList.value = response.rows;
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
    festivalCategoryId: null,
    name: null,
    description: null,
    time: null,
    location: null,
    coverImg: null,
    organizer: null,
    isTop: null,
    isRecommend: null,
    approvalStat: null,
    delFlag: null,
    createBy: null,
    createTime: null,
    updateBy: null,
    updateTime: null
  };
  proxy.resetForm("culturalFestivalRef");
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
  title.value = "添加节日";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getCulturalFestival(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改节日";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["culturalFestivalRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateCulturalFestival(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addCulturalFestival(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除节日编号为"' + _ids + '"的数据项？').then(function() {
    return delCulturalFestival(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('cultural/culturalFestival/export', {
    ...queryParams.value
  }, `culturalFestival_${new Date().getTime()}.xlsx`)
}

getList();
</script>
