<template>
  <div class="app-container">
    <!-- 搜索框 -->
    <el-form :model="queryParams"
             ref="queryRef"
             :inline="true"
             v-show="showSearch"
             label-width="68px">
      <el-form-item label="标签名称"
                    prop="tagName">
        <el-input v-model="queryParams.tagName"
                  placeholder="请输入标签名称"
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
                   v-hasPermi="['cultural:culturalTag:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success"
                   plain
                   icon="Edit"
                   :disabled="single"
                   @click="handleUpdate"
                   v-hasPermi="['cultural:culturalTag:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger"
                   plain
                   icon="Delete"
                   :disabled="multiple"
                   @click="handleDelete"
                   v-hasPermi="['cultural:culturalTag:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning"
                   plain
                   icon="Download"
                   @click="handleExport"
                   v-hasPermi="['cultural:culturalTag:export']">导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch"
                     @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 数据 -->
    <el-table v-loading="loading"
              :data="culturalTagList"
              @selection-change="handleSelectionChange">
      <el-table-column type="selection"
                       width="55"
                       align="center" />
      <el-table-column label="标签名称"
                       align="center"
                       prop="tagName" />
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
        </template></el-table-column>
      <el-table-column label="操作"
                       align="center"
                       class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link
                     type="primary"
                     icon="Edit"
                     @click="handleUpdate(scope.row)"
                     v-hasPermi="['cultural:culturalTag:edit']">修改</el-button>
          <el-button link
                     type="primary"
                     icon="Delete"
                     @click="handleDelete(scope.row)"
                     v-hasPermi="['cultural:culturalTag:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0"
                :total="total"
                v-model:page="queryParams.pageNum"
                v-model:limit="queryParams.pageSize"
                @pagination="getList" />

    <!-- 添加或修改文化标签对话框 -->
    <el-dialog :title="title"
               v-model="open"
               width="500px"
               append-to-body>
      <el-form ref="culturalTagRef"
               :model="form"
               :rules="rules"
               label-width="80px">
        <el-form-item label="标签名称"
                      prop="tagName">
          <el-input v-model="form.tagName"
                    placeholder="请输入标签名称" />
        </el-form-item>
        <el-form-item label="排序"
                      prop="orderNum">
          <el-input v-model="form.orderNum"
                    placeholder="请输入排序" />
        </el-form-item>
        <el-form-item label="删除标志"
                      prop="delFlag">
          <el-input v-model="form.delFlag"
                    placeholder="请输入删除标志" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary"
                     @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="CulturalTag">
import { listCulturalTag, getCulturalTag, delCulturalTag, addCulturalTag, updateCulturalTag } from "@/api/cultural/culturalTag";

const { proxy } = getCurrentInstance();

const culturalTagList = ref([]);
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
    tagName: null,
    orderNum: null,
    status: null,
  },
  rules: {
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询文化标签列表 */
function getList() {
  loading.value = true;
  listCulturalTag(queryParams.value).then(response => {
    culturalTagList.value = response.rows;
    total.value = response.total;
    loading.value = false;
  });
}

// 更改开关
function handleSwitchChange(row) {
  console.log("row:", row);
  updateCulturalTag(row).then((response) => {
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
    tagName: null,
    orderNum: null,
    status: null,
    delFlag: null,
    createBy: null,
    createTime: null,
    updateBy: null,
    updateTime: null
  };
  proxy.resetForm("culturalTagRef");
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
  title.value = "添加文化标签";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getCulturalTag(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改文化标签";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["culturalTagRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateCulturalTag(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addCulturalTag(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除文化标签编号为"' + _ids + '"的数据项？').then(function () {
    return delCulturalTag(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => { });
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('cultural/culturalTag/export', {
    ...queryParams.value
  }, `culturalTag_${new Date().getTime()}.xlsx`)
}

getList();
</script>
