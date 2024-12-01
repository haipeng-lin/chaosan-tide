<template>
  <div class="app-container">
    <!-- 查询条件 -->
    <el-form :model="queryParams"
             ref="queryRef"
             :inline="true"
             v-show="showSearch"
             label-width="68px">
      <el-form-item label="标题"
                    prop="title">
        <el-input v-model="queryParams.title"
                  placeholder="请输入标题"
                  clearable
                  @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary"
                   icon="Search"
                   @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh"
                   @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 增删查改按钮 -->
    <el-row :gutter="10"
            class="mb8">
      <el-col :span="1.5">
        <el-button type="primary"
                   plain
                   icon="Plus"
                   @click="handleAdd"
                   v-hasPermi="['cultural:culturalTopic:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success"
                   plain
                   icon="Edit"
                   :disabled="single"
                   @click="handleUpdate"
                   v-hasPermi="['cultural:culturalTopic:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger"
                   plain
                   icon="Delete"
                   :disabled="multiple"
                   @click="handleDelete"
                   v-hasPermi="['cultural:culturalTopic:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning"
                   plain
                   icon="Download"
                   @click="handleExport"
                   v-hasPermi="['cultural:culturalTopic:export']">导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch"
                     @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 查询信息 -->
    <el-table v-loading="loading"
              :data="culturalTopicList"
              @selection-change="handleSelectionChange">
      <el-table-column type="selection"
                       width="55"
                       align="center" />
      <el-table-column label="标题"
                       align="center"
                       prop="title" />
      <el-table-column label="描述"
                       align="center"
                       prop="description"
                       width="1000"
                       height="90" />
      <el-table-column label="封面"
                       align="center"
                       prop="coverImg"
                       width="160">
        <template #default="scope">
          <image-preview :src="scope.row.coverImg"
                         :width="160"
                         :height="90" />
        </template>
      </el-table-column>
      <el-table-column label="排序"
                       align="center"
                       prop="orderNum" />
      <el-table-column label="显示状态"
                       align="center"
                       prop="status">
        <template #default="scope">
          <el-switch v-model="scope.row.status"
                     :active-value="1"
                     :inactive-value="2"
                     @change="handleSwitchChange(scope.row)">
          </el-switch>
        </template>
      </el-table-column>
      <el-table-column label="操作"
                       align="center"
                       class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link
                     type="primary"
                     icon="Edit"
                     @click="handleUpdate(scope.row)"
                     v-hasPermi="['cultural:culturalTopic:edit']">修改</el-button>
          <el-button link
                     type="primary"
                     icon="Delete"
                     @click="handleDelete(scope.row)"
                     v-hasPermi="['cultural:culturalTopic:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0"
                :total="total"
                v-model:page="queryParams.pageNum"
                v-model:limit="queryParams.pageSize"
                @pagination="getList" />

    <!-- 添加或修改文化专题对话框 -->
    <el-dialog :title="title"
               v-model="open"
               width="500px"
               append-to-body>
      <el-form ref="culturalTopicRef"
               :model="form"
               :rules="rules"
               label-width="80px">
        <el-form-item label="标题"
                      prop="title">
          <el-input v-model="form.title"
                    placeholder="请输入标题" />
        </el-form-item>
        <el-form-item label="描述"
                      prop="description">
          <el-input v-model="form.description"
                    type="textarea"
                    placeholder="请输入内容"
                    :autosize="{ minRows: 8 }" />
        </el-form-item>
        <el-form-item label="封面"
                      prop="coverImg">
          <image-upload v-model="form.coverImg" />
        </el-form-item>
        <el-form-item label="排序"
                      prop="orderNum">
          <el-input v-model="form.orderNum"
                    placeholder="请输入排序" />
        </el-form-item>

      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary"
                     @click="submitForm">确
            定</el-button>
          <el-button @click="cancel">取
            消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="CulturalTopic">
import {
  listCulturalTopic,
  getCulturalTopic,
  delCulturalTopic,
  addCulturalTopic,
  updateCulturalTopic,
} from "@/api/cultural/culturalTopic";

const { proxy } = getCurrentInstance();

const culturalTopicList = ref([]);
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
    title: null,
    description: null,
    coverImg: null,
    orderNum: null,
    status: null,
  },
  rules: {},
});

const { queryParams, form, rules } = toRefs(data);

/** 查询文化专题列表 */
function getList() {
  loading.value = true;
  listCulturalTopic(queryParams.value).then((response) => {
    culturalTopicList.value = response.rows;
    total.value = response.total;
    loading.value = false;
  });
}

// 更改开关
function handleSwitchChange(row) {
  console.log("row:", row);
  updateCulturalTopic(row).then((response) => {
    proxy.$modal.msgSuccess("修改成功");
    open.value = false;
    getList();
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
    title: null,
    description: null,
    coverImg: null,
    orderNum: null,
    status: null,
    delFlag: null,
    createBy: null,
    createTime: null,
    updateBy: null,
    updateTime: null,
  };
  proxy.resetForm("culturalTopicRef");
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
  ids.value = selection.map((item) => item.id);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加文化专题";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value;
  getCulturalTopic(_id).then((response) => {
    form.value = response.data;
    open.value = true;
    title.value = "修改文化专题";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["culturalTopicRef"].validate((valid) => {
    if (valid) {
      if (form.value.id != null) {
        updateCulturalTopic(form.value).then((response) => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addCulturalTopic(form.value).then((response) => {
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
  proxy.$modal
    .confirm('是否确认删除文化专题编号为"' + _ids + '"的数据项？')
    .then(function () {
      return delCulturalTopic(_ids);
    })
    .then(() => {
      getList();
      proxy.$modal.msgSuccess("删除成功");
    })
    .catch(() => { });
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download(
    "cultural/culturalTopic/export",
    {
      ...queryParams.value,
    },
    `culturalTopic_${new Date().getTime()}.xlsx`
  );
}

getList();
</script>
