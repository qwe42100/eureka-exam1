<template>
  <el-main>
    <el-form :inline="true" :model="queryForm" class="demo-form-inline">
      <el-form-item label="商品名称">
        <el-input v-model="queryForm.name" placeholder="商品名称"></el-input>
      </el-form-item>

      <el-form-item label="是否上架">
        <el-select v-model="queryForm.upper_status" placeholder="请选择是否上架">
          <el-option label="上架" value=0></el-option>
          <el-option label="下架" value=1></el-option>
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="onSubmit">查询</el-button>
      </el-form-item>
    </el-form>
  <el-table
    ref="multipleTable"
    :data="tableData"
    tooltip-effect="dark"
    style="width: 100%"
    @selection-change="handleSelectionChange">
    <el-table-column
      type="selection"
      width="55">
    </el-table-column>
    <el-table-column
      label="编号"
      prop="id"
      width="120">
    </el-table-column>
    <el-table-column
      prop="name"
      label="姓名"
      width="120">
    </el-table-column>
    <el-table-column
      prop="num"
      label="商品数量"
      width="120">
    </el-table-column>
    <el-table-column
      prop="price"
      label="商品价格"
      width="120">
    </el-table-column>
    <el-table-column
      label="商品图片"
      width="120">
      <template slot-scope="scope">
      <img :src="scope.row.imge_url" style="height: 36px;">
      </template>
    </el-table-column>
    <el-table-column
      prop="upper_status"
      label="是否上架"
      width="120">
      <template slot-scope="scope">
        {{scope.row.upper_status==0?'上架':'下架'}}
      </template>
    </el-table-column>
    <el-table-column
      prop="factory_date"
      label="出厂日期"
      width="120">
    </el-table-column>
    <el-table-column
      label="操作"
      width="100">
      <template slot-scope="scope">
        <el-button @click="detail(scope.row.id)" type="text" size="small">查看</el-button>
        <el-button @click="edit(scope.row.id)" type="text" size="small">编辑</el-button>
        <el-button @click="del(scope.row.id)" type="text" size="small">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
    <el-pagination
      layout="prev, pager, next"
      @current-change="getPage"
      :page-count="pages">
    </el-pagination>

    <!--添加-->
    <el-dialog
      title="添加/编辑"
      :visible.sync="dialogVisible"
      width="30%"
      :before-close="handleClose">
      <span>
        <el-form :inline="true" :model="saveForm" class="demo-form-inline">
  <el-form-item label="商品名称">
    <el-input v-model="saveForm.name" placeholder="商品名称"></el-input>
  </el-form-item>
  <el-form-item label="商品数量">
 <el-input v-model="saveForm.num" placeholder="商品数量"></el-input>
  </el-form-item>
  <el-form-item label="商品价格">
 <el-input v-model="saveForm.price" placeholder="商品价格"></el-input>
  </el-form-item>
          <el-form-item label="图片上传">
        <el-upload
          class="avatar-uploader"
          action="http://127.0.0.1/shop/upload"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload">
          <img v-if="saveForm.imge_url" :src="saveForm.imge_url" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
  </el-form-item>
 <el-form-item label="是否上架">
        <el-select v-model="saveForm.upper_status" placeholder="请选择是否上架">
          <el-option label="上架" value="0"></el-option>
          <el-option label="下架" value="1"></el-option>
        </el-select>
      </el-form-item>
  <el-form-item label="出厂日期">
 <div class="block">
    <el-date-picker
      v-model="saveForm.factory_date"
      type="date" format="yyyy-MM-dd"
      placeholder="选择日期">
    </el-date-picker>
  </div>
  </el-form-item>
</el-form>
      </span>
      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="save">保存</el-button>
  </span>
    </el-dialog>
    <!--查看-->
    <el-dialog
      title="添加/编辑"
      :visible.sync="dtdialogVisible"
      width="30%"
      :before-close="handleClose">
      <span>
        <el-form :inline="true" :model="detailList" class="demo-form-inline">
  <el-form-item label="商品名称">
    {{detailList.name}}
  </el-form-item><br>

  <el-form-item label="商品数量">
 {{detailList.num}}
  </el-form-item><br>
  <el-form-item label="商品价格">
 {{detailList.price}}
  </el-form-item><br>
          <el-form-item label="图片上传">
  <img :src="detailList.imge_url" style="height: 36px;">
  </el-form-item><br>
  <el-form-item label="是否上架">
        {{detailList.upper_status==0?'上架':'下架'}}
  </el-form-item><br>
  <el-form-item label="出厂日期">
{{detailList.factory_date}}
  </el-form-item><br>
</el-form>
      </span>
      <span slot="footer" class="dialog-footer">
    <el-button @click="dtdialogVisible = false">取 消</el-button>
  </span>
    </el-dialog>
    <el-button type="primary" @click="add">添加</el-button>
  </el-main>
</template>

<script>
  const axios = require("axios");
  const path = "http://127.0.0.1/";
    export default {
        name: "productlist",
        data() {
            return {
                tableData: [],
                multipleSelection: [],
                pages:0,
                queryForm:{
                    pageNo:1,
                    pageSize:3,
                },
                dialogVisible: false,
                saveForm:{},
                detailList:{},
                dtdialogVisible:false,
            }
        },

        methods: {
            //删除
            del(id){
                const self = this ;
                axios({
                    url:path+"shop/delById",
                    method:"get",
                    params:{id:id}
                }).then(res=> {
                    self.$message("删除成功！！！");
                    self.getList();
                })
            },
            //添加
            save(){
                const self = this;
                console.log(this.saveForm)
                axios({
                    url:path+"shop/save",
                    method:"post",
                    data:this.saveForm
                }).then(res=>{
                    self.getList();
                    this.dialogVisible=false;
                    self.$message("添加成功！！！")
                })
            },
            //点击事件
            add(){
                this.dialogVisible=true;
                this.saveForm={imge_url:""};
            },
            handleAvatarSuccess(res, file) {
                this.saveForm.imge_url = res.fileUrl;
            },
            beforeAvatarUpload(file) {
                const isJPG = file.type === 'image/jpeg';
                const isLt2M = file.size / 1024 / 1024 < 2;

                if (!isJPG) {
                    this.$message.error('上传头像图片只能是 JPG 格式!');
                }
                if (!isLt2M) {
                    this.$message.error('上传头像图片大小不能超过 2MB!');
                }
                return isJPG && isLt2M;
            }
            ,
            //列表
            getList(){
                const self = this ;
                axios({
                    url:path+"shop/list",
                    method:"get",
                    params:this.queryForm
                }).then(res=> {
                    self.tableData = res.data.list;
                    self.pages = res.data.pages;
                })

            },
            //分页
            getPage(pageNo){
                this.queryForm.pageNo =pageNo;
                this.getList();

            },
            //查询
            onSubmit() {
                this.queryForm.pageNo = 1,
              this.getList();
            },
            //查看
            detail(id){
                const self = this ;
                this.dtdialogVisible=true;
                axios({
                    url:path+"shop/getById",
                    method:"get",
                    params:{id:id}
                }).then(res=> {
                    self.detailList=res.data;
                })

            },
            //修改
            edit(id){
                const self = this;
                self.dialogVisible=true;
                axios({
                    url:path+"shop/getById",
                    method:"get",
                    params:{id:id},
                }).then(res=>{
                    self.saveForm =res.data;
                    self.saveForm.upper_status=res.data.upper_status+""
                    self.getList();
                })
            },
            toggleSelection(rows) {
                if (rows) {
                    rows.forEach(row => {
                        this.$refs.multipleTable.toggleRowSelection(row);
                    });
                } else {
                    this.$refs.multipleTable.clearSelection();
                }
            },
            handleSelectionChange(val) {
                this.multipleSelection = val;
            },
            handleClose(done) {
                this.$confirm('确认关闭？')
                    .then(_ => {
                        done();
                    })
                    .catch(_ => {});
            }
        }
        ,

        created() {
            this.getList();

        }
    }
</script>

<style>
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>
