webpackJsonp([38],{"1Jqc":function(a,t,e){"use strict";var o=e("3cXf"),r=e.n(o);t.a={name:"enterprise",data:function(){return{statDayOptions:[{dataText:"今日",dataValue:"今日"},{dataText:"昨日",dataValue:"昨日"}],searchForm:{formdata:{name:"",abbreviation:""},currentPage:1,pageSize:20},tableList:{},colsData:[{label:"企业名称",prop:"name",align:"left",sortable:!0},{label:"企业简称",prop:"abbreviation",align:"left",sortable:!0},{label:"logo",prop:"logo",align:"left",sortable:!0},{label:"图片",prop:"image",align:"left",sortable:!0},{label:"能耗统计开始时间",prop:"statStartTime",align:"left",sortable:!0},{label:"能耗统计日",prop:"statDay",align:"left",sortable:!0},{label:"告警邮件服务邮箱",prop:"alarmEmailAddr",align:"left",sortable:!0}],dialogVisible:!1,addForm:{name:"",logo:"",image:"",abbreviation:"",statStartTime:"",statDay:"",alarmEmailAddr:""},rules:this.eachValidate({name:{required:!0,maxString:"50"},logo:{},image:{},abbreviation:{maxString:"20"},statStartTime:{required:!0},statDay:{maxString:"10"},alarmEmailAddr:{maxString:"50"}})}},computed:{title:function(){return this.addForm.id?"编辑企业资料管理":"新增企业资料管理"},urlType:function(){return this.addForm.id?"put":"post"}},watch:{},methods:{getTbList:function(){var a=this;this.$ajax.get("rest/enterprise/page",{params:this.searchForm}).then(function(t){t.data.status&&(a.tableList=t.data)})},editTb:function(a){this.dialogVisible=!0,this.addForm=JSON.parse(r()(a))},closeDialog:function(a){this.$nextTick(function(){this.$refs[a].resetFields(),this.addForm={name:"",logo:"",image:"",abbreviation:"",statStartTime:"",statDay:"",alarmEmailAddr:""}})},saveDialog:function(a){var t=this;this.$refs[a].validate(function(a){a&&t.$ajax[t.urlType]("rest/enterprise",t.addForm).then(function(a){a.data.status?(t.$message.success(a.data.answerMsg),t.dialogVisible=!1,t.getTbList()):t.$message.error(a.data.answerMsg)})})},delTb:function(a){var t=this;this.$confirm("是否删除该记录?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){t.$ajax.delete("rest/enterprise",{params:{id:a.id}}).then(function(a){a.data.status?(t.$message.success(a.data.answerMsg),t.getTbList()):t.$message.error(a.data.answerMsg)})})}},mounted:function(){this.getTbList()}}},"GQc+":function(a,t,e){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var o=e("1Jqc"),r=e("IWuG"),l=e("J0+h"),i=l(o.a,r.a,null,null,null);t.default=i.exports},IWuG:function(a,t,e){"use strict";var o=function(){var a=this,t=a.$createElement,e=a._self._c||t;return e("div",[e("da-manage-page",[e("div",{attrs:{slot:"button"},slot:"button"},[e("el-button",{attrs:{icon:"my-zengjia"},on:{click:function(t){a.dialogVisible=!0}}},[a._v("新增")])],1),a._v(" "),e("el-form",{attrs:{slot:"search",inline:!0,model:a.searchForm.formdata},slot:"search"},[e("el-form-item",{attrs:{label:"企业名称"}},[e("el-input",{model:{value:a.searchForm.formdata.name,callback:function(t){a.$set(a.searchForm.formdata,"name",t)},expression:"searchForm.formdata.name"}})],1),a._v(" "),e("el-form-item",{attrs:{label:"企业简称"}},[e("el-input",{model:{value:a.searchForm.formdata.abbreviation,callback:function(t){a.$set(a.searchForm.formdata,"abbreviation",t)},expression:"searchForm.formdata.abbreviation"}})],1),a._v(" "),e("el-form-item",[e("el-button",{attrs:{type:"primary"},on:{click:a.getTbList}},[a._v("查询")])],1)],1),a._v(" "),e("da-table",{attrs:{slot:"content",colums:a.colsData,"table-list":a.tableList,"search-data":a.searchForm,showSelection:!1},on:{callBack:a.getTbList},slot:"content"},[e("el-table-column",{attrs:{slot:"operation",property:"operation",label:"操作",width:"150",align:"center",fixed:"right"},slot:"operation",scopedSlots:a._u([{key:"default",fn:function(t){return[e("el-button",{attrs:{size:"small"},on:{click:function(e){a.editTb(t.row)}}},[a._v("编辑")]),a._v(" "),e("el-button",{attrs:{size:"small"},on:{click:function(e){a.delTb(t.row)}}},[a._v("删除")])]}}])})],1)],1),a._v(" "),e("da-dialog",{attrs:{title:a.title,visible:a.dialogVisible},on:{"update:visible":function(t){a.dialogVisible=t},close:function(t){a.closeDialog("addForm")}}},[e("el-form",{ref:"addForm",attrs:{"label-width":"90px",model:a.addForm,rules:a.rules}},[e("el-form-item",{attrs:{label:"企业名称",prop:"name"}},[e("el-input",{attrs:{"auto-complete":"off"},model:{value:a.addForm.name,callback:function(t){a.$set(a.addForm,"name",t)},expression:"addForm.name"}})],1),a._v(" "),e("el-form-item",{attrs:{label:"企业简称",prop:"abbreviation"}},[e("el-input",{attrs:{"auto-complete":"off"},model:{value:a.addForm.abbreviation,callback:function(t){a.$set(a.addForm,"abbreviation",t)},expression:"addForm.abbreviation"}})],1),a._v(" "),e("el-form-item",{attrs:{label:"logo",prop:"logo"}},[e("el-input",{attrs:{"auto-complete":"off"},model:{value:a.addForm.logo,callback:function(t){a.$set(a.addForm,"logo",t)},expression:"addForm.logo"}})],1),a._v(" "),e("el-form-item",{attrs:{label:"图片",prop:"image"}},[e("da-upload",{attrs:{url:"rest/dataUploadfile"},model:{value:a.addForm.image,callback:function(t){a.$set(a.addForm,"image",t)},expression:"addForm.image"}})],1),a._v(" "),e("el-form-item",{attrs:{label:"能耗统计开始时间",prop:"statStartTime"}},[e("el-date-picker",{attrs:{type:"date",placeholder:"选择日期"},model:{value:a.addForm.statStartTime,callback:function(t){a.$set(a.addForm,"statStartTime",t)},expression:"addForm.statStartTime"}})],1),a._v(" "),e("el-form-item",{attrs:{label:"能耗统计日",prop:"statDay"}},[e("el-select",{attrs:{filterable:"",placeholder:"请选择"},model:{value:a.addForm.statDay,callback:function(t){a.$set(a.addForm,"statDay",t)},expression:"addForm.statDay"}},a._l(a.statDayOptions,function(a){return e("el-option",{key:a.dataValue,attrs:{label:a.dataText,value:a.dataValue}})}))],1),a._v(" "),e("el-form-item",{attrs:{label:"告警邮件服务邮箱",prop:"alarmEmailAddr"}},[e("el-input",{attrs:{"auto-complete":"off"},model:{value:a.addForm.alarmEmailAddr,callback:function(t){a.$set(a.addForm,"alarmEmailAddr",t)},expression:"addForm.alarmEmailAddr"}})],1)],1),a._v(" "),e("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[e("el-button",{attrs:{type:"primary"},on:{click:function(t){a.saveDialog("addForm")}}},[a._v("确 定")]),a._v(" "),e("el-button",{on:{click:function(t){a.dialogVisible=!1}}},[a._v("取 消")])],1)],1)],1)},r=[],l={render:o,staticRenderFns:r};t.a=l}});
//# sourceMappingURL=38.a19f255d34c2591015a8.js.map