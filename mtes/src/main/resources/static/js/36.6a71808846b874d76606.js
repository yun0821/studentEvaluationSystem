webpackJsonp([36],{Ic02:function(t,e,a){"use strict";var r=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("da-manage-page",[a("el-form",{attrs:{slot:"search",inline:!0,model:t.searchForm.formdata},slot:"search"},[a("el-form-item",{attrs:{label:"登录账号"}},[a("el-input",{model:{value:t.searchForm.formdata.account,callback:function(e){t.$set(t.searchForm.formdata,"account",e)},expression:"searchForm.formdata.account"}})],1),t._v(" "),a("el-form-item",{attrs:{label:"用户姓名"}},[a("el-input",{model:{value:t.searchForm.formdata.userName,callback:function(e){t.$set(t.searchForm.formdata,"userName",e)},expression:"searchForm.formdata.userName"}})],1),t._v(" "),a("el-form-item",{attrs:{label:"登录时间"}},[a("el-input",{model:{value:t.searchForm.formdata.time,callback:function(e){t.$set(t.searchForm.formdata,"time",e)},expression:"searchForm.formdata.time"}})],1),t._v(" "),a("el-form-item",{attrs:{label:"IP地址"}},[a("el-input",{model:{value:t.searchForm.formdata.ipAddress,callback:function(e){t.$set(t.searchForm.formdata,"ipAddress",e)},expression:"searchForm.formdata.ipAddress"}})],1),t._v(" "),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:t.getTbList}},[t._v("查询")])],1)],1),t._v(" "),a("da-table",{ref:"table",attrs:{slot:"content",colums:t.colsData,"table-list":t.tableList,"search-data":t.searchForm,showSelection:!1},on:{callBack:t.getTbList},slot:"content"})],1),t._v(" "),a("da-dialog",{attrs:{title:t.title,visible:t.dialogVisible},on:{"update:visible":function(e){t.dialogVisible=e},close:function(e){t.closeDialog("addForm")}}},[a("el-form",{ref:"addForm",attrs:{"label-width":"90px",model:t.addForm,rules:t.rules}}),t._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{type:"primary"},on:{click:function(e){t.saveDialog("addForm")}}},[t._v("确 定")]),t._v(" "),a("el-button",{on:{click:function(e){t.dialogVisible=!1}}},[t._v("取 消")])],1)],1)],1)},o=[],s={render:r,staticRenderFns:o};e.a=s},V9sk:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var r=a("t867"),o=a("Ic02"),s=a("J0+h"),l=s(r.a,o.a,null,null,null);e.default=l.exports},t867:function(t,e,a){"use strict";e.a={name:"logLogin",data:function(){return{searchForm:{formdata:{account:"",userName:"",time:"",ipAddress:""},currentPage:1,pageSize:20},tableList:{},colsData:[{label:"登录账号",prop:"account",align:"left",sortable:!0},{label:"用户姓名",prop:"userName",align:"left",sortable:!0},{label:"登录时间",prop:"time",align:"left",format:"YYYY-MM-DD HH:mm:ss",sortable:!0},{label:"IP地址",prop:"ipAddress",align:"left",sortable:!0},{label:"浏览器信息",prop:"browser",align:"left",sortable:!0},{label:"IP归属地",prop:"place",align:"left",sortable:!0}],dialogVisible:!1,addForm:{},rules:this.eachValidate({})}},computed:{title:function(){return this.addForm.id?"编辑系统日志管理":"新增系统日志管理"},urlType:function(){return this.addForm.id?"put":"post"}},watch:{},methods:{getTbList:function(){var t=this;this.$ajax.get("rest/logLogin/page",{params:this.searchForm,loadingTarget:this.$refs.table}).then(function(e){e.data.status&&(t.tableList=e.data)})},closeDialog:function(t){this.$nextTick(function(){this.$refs[t].resetFields(),this.addForm={}})},saveDialog:function(t){var e=this;this.$refs[t].validate(function(t){t&&e.$ajax[e.urlType]("rest/logLogin",e.addForm).then(function(t){t.data.status?(e.$message.success(t.data.answerMsg),e.dialogVisible=!1,e.getTbList()):e.$message.error(t.data.answerMsg)})})}},mounted:function(){this.getTbList()}}}});
//# sourceMappingURL=36.6a71808846b874d76606.js.map