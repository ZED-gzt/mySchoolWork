<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="员工" prop="userName">
        <el-input
          v-model="queryParams.userName"
          placeholder="请输入员工"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="工资年" prop="salaryYear">
        <el-input
          v-model="queryParams.salaryYear"
          placeholder="请输入工资年"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="工资月" prop="salaryMonth">
        <el-input
          v-model="queryParams.salaryMonth"
          placeholder="请输入工资月"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间" prop="createDate">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.createDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:salary:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:salary:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:salary:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:salary:export']"
        >导出</el-button>
      </el-col>
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="salaryList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键Id" align="center" prop="id" />
      <el-table-column label="员工" align="center" prop="userName" />
      <el-table-column label="出勤天数" align="center" prop="attendDay" />
      <el-table-column label="考核系数" align="center" prop="assessNum" />
      <el-table-column label="基本工资" align="center" prop="basicSalary" />
      <el-table-column label="绩效工资" align="center" prop="meritSalary" />
      <el-table-column label="应发工资" align="center" prop="oughtTotalSalary" />
      <el-table-column label="应发基本工资" align="center" prop="oughtBasicSalary" />
      <el-table-column label="应发绩效工资" align="center" prop="oughtMeritSalary" />
      <el-table-column label="补助" align="center" prop="grants" />
      <el-table-column label="其他补助" align="center" prop="otherGrants" />
      <el-table-column label="应扣工资合计" align="center" prop="oughtdeductSalary" />
      <el-table-column label="考勤扣款" align="center" prop="attendPay" />
      <el-table-column label="个人养老" align="center" prop="personAgePay" />
      <el-table-column label="公司承担养老" align="center" prop="companyAgePay" />
      <el-table-column label="个人医保" align="center" prop="personMedialPay" />
      <el-table-column label="公司承担医保" align="center" prop="companyMedialPay" />
      <el-table-column label="个人失业险" align="center" prop="personUnemployPay" />
      <el-table-column label="公司承担失业险" align="center" prop="companyUnemployPay" />
      <el-table-column label="个人公积金" align="center" prop="personReserveFund" />
      <el-table-column label="公司承担公积金" align="center" prop="companyReserveFund" />
      <el-table-column label="餐费" align="center" prop="mealPay" />
      <el-table-column label="个人税" align="center" prop="personTax" />
      <el-table-column label="实发工资" align="center" prop="realTotalSalary" />
      <el-table-column label="工资年" align="center" prop="salaryYear" />
      <el-table-column label="工资月" align="center" prop="salaryMonth" />
      <el-table-column label="创建时间" align="center" prop="createDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:salary:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:salary:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改工资对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="员工" prop="userName">
          <el-input v-model="form.userName" placeholder="请输入员工" />
        </el-form-item>
        <el-form-item label="出勤天数" prop="attendDay">
          <el-input v-model="form.attendDay" placeholder="请输入出勤天数" />
        </el-form-item>
        <el-form-item label="考核系数" prop="assessNum">
          <el-input v-model="form.assessNum" placeholder="请输入考核系数" />
        </el-form-item>
        <el-form-item label="基本工资" prop="basicSalary">
          <el-input v-model="form.basicSalary" placeholder="请输入基本工资" />
        </el-form-item>
        <el-form-item label="绩效工资" prop="meritSalary">
          <el-input v-model="form.meritSalary" placeholder="请输入绩效工资" />
        </el-form-item>
        <el-form-item label="应发工资" prop="oughtTotalSalary">
          <el-input v-model="form.oughtTotalSalary" placeholder="请输入应发工资" />
        </el-form-item>
        <el-form-item label="应发基本工资" prop="oughtBasicSalary">
          <el-input v-model="form.oughtBasicSalary" placeholder="请输入应发基本工资" />
        </el-form-item>
        <el-form-item label="应发绩效工资" prop="oughtMeritSalary">
          <el-input v-model="form.oughtMeritSalary" placeholder="请输入应发绩效工资" />
        </el-form-item>
        <el-form-item label="补助" prop="grants">
          <el-input v-model="form.grants" placeholder="请输入补助" />
        </el-form-item>
        <el-form-item label="其他补助" prop="otherGrants">
          <el-input v-model="form.otherGrants" placeholder="请输入其他补助" />
        </el-form-item>
        <el-form-item label="应扣工资合计" prop="oughtdeductSalary">
          <el-input v-model="form.oughtdeductSalary" placeholder="请输入应扣工资合计" />
        </el-form-item>
        <el-form-item label="考勤扣款" prop="attendPay">
          <el-input v-model="form.attendPay" placeholder="请输入考勤扣款" />
        </el-form-item>
        <el-form-item label="个人养老" prop="personAgePay">
          <el-input v-model="form.personAgePay" placeholder="请输入个人养老" />
        </el-form-item>
        <el-form-item label="公司承担养老" prop="companyAgePay">
          <el-input v-model="form.companyAgePay" placeholder="请输入公司承担养老" />
        </el-form-item>
        <el-form-item label="个人医保" prop="personMedialPay">
          <el-input v-model="form.personMedialPay" placeholder="请输入个人医保" />
        </el-form-item>
        <el-form-item label="公司承担医保" prop="companyMedialPay">
          <el-input v-model="form.companyMedialPay" placeholder="请输入公司承担医保" />
        </el-form-item>
        <el-form-item label="个人失业险" prop="personUnemployPay">
          <el-input v-model="form.personUnemployPay" placeholder="请输入个人失业险" />
        </el-form-item>
        <el-form-item label="公司承担失业险" prop="companyUnemployPay">
          <el-input v-model="form.companyUnemployPay" placeholder="请输入公司承担失业险" />
        </el-form-item>
        <el-form-item label="个人公积金" prop="personReserveFund">
          <el-input v-model="form.personReserveFund" placeholder="请输入个人公积金" />
        </el-form-item>
        <el-form-item label="公司承担公积金" prop="companyReserveFund">
          <el-input v-model="form.companyReserveFund" placeholder="请输入公司承担公积金" />
        </el-form-item>
        <el-form-item label="餐费" prop="mealPay">
          <el-input v-model="form.mealPay" placeholder="请输入餐费" />
        </el-form-item>
        <el-form-item label="个人税" prop="personTax">
          <el-input v-model="form.personTax" placeholder="请输入个人税" />
        </el-form-item>
        <el-form-item label="实发工资" prop="realTotalSalary">
          <el-input v-model="form.realTotalSalary" placeholder="请输入实发工资" />
        </el-form-item>
        <el-form-item label="工资年" prop="salaryYear">
          <el-input v-model="form.salaryYear" placeholder="请输入工资年" />
        </el-form-item>
        <el-form-item label="工资月" prop="salaryMonth">
          <el-input v-model="form.salaryMonth" placeholder="请输入工资月" />
        </el-form-item>
        <el-form-item label="创建时间" prop="createDate">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.createDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择创建时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listSalary, getSalary, delSalary, addSalary, updateSalary, exportSalary } from "@/api/system/salary";

export default {
  name: "Salary",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 工资表格数据
      salaryList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userName: null,
        attendDay: null,
        assessNum: null,
        basicSalary: null,
        meritSalary: null,
        oughtTotalSalary: null,
        oughtBasicSalary: null,
        oughtMeritSalary: null,
        grants: null,
        otherGrants: null,
        oughtdeductSalary: null,
        attendPay: null,
        personAgePay: null,
        companyAgePay: null,
        personMedialPay: null,
        companyMedialPay: null,
        personUnemployPay: null,
        companyUnemployPay: null,
        personReserveFund: null,
        companyReserveFund: null,
        mealPay: null,
        personTax: null,
        realTotalSalary: null,
        salaryYear: null,
        salaryMonth: null,
        createDate: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        userName: [
          { required: true, message: "员工不能为空", trigger: "blur" }
        ],
        attendDay: [
          { required: true, message: "出勤天数不能为空", trigger: "blur" }
        ],
        assessNum: [
          { required: true, message: "考核系数不能为空", trigger: "blur" }
        ],
        basicSalary: [
          { required: true, message: "基本工资不能为空", trigger: "blur" }
        ],
        meritSalary: [
          { required: true, message: "绩效工资不能为空", trigger: "blur" }
        ],
        oughtTotalSalary: [
          { required: true, message: "应发工资不能为空", trigger: "blur" }
        ],
        oughtBasicSalary: [
          { required: true, message: "应发基本工资不能为空", trigger: "blur" }
        ],
        oughtMeritSalary: [
          { required: true, message: "应发绩效工资不能为空", trigger: "blur" }
        ],
        grants: [
          { required: true, message: "补助不能为空", trigger: "blur" }
        ],
        otherGrants: [
          { required: true, message: "其他补助不能为空", trigger: "blur" }
        ],
        oughtdeductSalary: [
          { required: true, message: "应扣工资合计不能为空", trigger: "blur" }
        ],
        attendPay: [
          { required: true, message: "考勤扣款不能为空", trigger: "blur" }
        ],
        personAgePay: [
          { required: true, message: "个人养老不能为空", trigger: "blur" }
        ],
        companyAgePay: [
          { required: true, message: "公司承担养老不能为空", trigger: "blur" }
        ],
        personMedialPay: [
          { required: true, message: "个人医保不能为空", trigger: "blur" }
        ],
        companyMedialPay: [
          { required: true, message: "公司承担医保不能为空", trigger: "blur" }
        ],
        personUnemployPay: [
          { required: true, message: "个人失业险不能为空", trigger: "blur" }
        ],
        companyUnemployPay: [
          { required: true, message: "公司承担失业险不能为空", trigger: "blur" }
        ],
        personReserveFund: [
          { required: true, message: "个人公积金不能为空", trigger: "blur" }
        ],
        companyReserveFund: [
          { required: true, message: "公司承担公积金不能为空", trigger: "blur" }
        ],
        mealPay: [
          { required: true, message: "餐费不能为空", trigger: "blur" }
        ],
        personTax: [
          { required: true, message: "个人税不能为空", trigger: "blur" }
        ],
        realTotalSalary: [
          { required: true, message: "实发工资不能为空", trigger: "blur" }
        ],
        salaryYear: [
          { required: true, message: "工资年不能为空", trigger: "blur" }
        ],
        salaryMonth: [
          { required: true, message: "工资月不能为空", trigger: "blur" }
        ],
        createDate: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
        remark: [
          { required: true, message: "备注不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询工资列表 */
    getList() {
      this.loading = true;
      listSalary(this.queryParams).then(response => {
        this.salaryList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        userName: null,
        attendDay: null,
        assessNum: null,
        basicSalary: null,
        meritSalary: null,
        oughtTotalSalary: null,
        oughtBasicSalary: null,
        oughtMeritSalary: null,
        grants: null,
        otherGrants: null,
        oughtdeductSalary: null,
        attendPay: null,
        personAgePay: null,
        companyAgePay: null,
        personMedialPay: null,
        companyMedialPay: null,
        personUnemployPay: null,
        companyUnemployPay: null,
        personReserveFund: null,
        companyReserveFund: null,
        mealPay: null,
        personTax: null,
        realTotalSalary: null,
        salaryYear: null,
        salaryMonth: null,
        createDate: null,
        remark: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加工资";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getSalary(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改工资";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateSalary(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSalary(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除工资编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delSalary(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有工资数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportSalary(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
