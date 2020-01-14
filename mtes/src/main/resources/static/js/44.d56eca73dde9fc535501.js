webpackJsonp([44],{"8ndO":function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var r=a("awzD"),n=a("ruZT"),o=a("J0+h"),i=o(r.a,n.a,null,null,null);t.default=i.exports},awzD:function(e,t,a){"use strict";var r=a("3cXf"),n=a.n(r);t.a={name:"entEnergy",data:function(){return{entData:[],energyIdOptions:[],energyTypeOptions:[{dataText:"购入能源",dataValue:"1"},{dataText:"产出能源",dataValue:"2"},{dataText:"工艺产出能源",dataValue:"3"},{dataText:"自产自用能源",dataValue:"4"}],searchForm:{formdata:{},currentPage:1,pageSize:20},tableList:{},colsData:[{label:"企业名称",prop:"entId",align:"left",hidden:!1,sortable:!0,displace:{}},{label:"能源类型标准名称",prop:"energyId",align:"left",sortable:!0,displace:{}},{label:"自定义能源名称",prop:"energyName",align:"left",sortable:!0},{label:"能源种类",prop:"energyType",align:"left",sortable:!0,displace:{1:"购入能源",2:"产出能源",3:"工艺产出能源",4:"自产自用能源"}}],dialogVisible:!1,addForm:{entId:"",energyName:"",energyId:"",energyType:""},rules:this.eachValidate({entId:{required:{required:!0,trigger:"change"}},energyName:{maxString:"20"},energyId:{required:{required:!0,trigger:"change"}},energyType:{required:{required:!0,trigger:"change"}}})}},computed:{title:function(){return this.addForm.id?"编辑企业需采集的能源资料表":"新增企业需采集的能源资料表"},urlType:function(){return this.addForm.id?"put":"post"}},watch:{},methods:{getEnt:function(){var e=this;this.$ajax.get("rest/enterprise/list").then(function(t){if(t.data.status)if(e.entData=t.data.records,t.data.records.length>1){var a={};e.$each(t.data.records,function(e){a[e.id]=e.name}),e.colsData[0].displace=a}else e.colsData[0].hidden=!0})},getTbList:function(){var e=this;this.$ajax.get("rest/entEnergy/page",{params:this.searchForm,loadingTarget:this.$refs.table}).then(function(t){t.data.status&&(e.tableList=t.data)})},getEnergyIdInfo:function(){var e=this;this.$ajax.get("rest/entEnergy/energyId/get").then(function(t){if(t.data.status){e.energyIdOptions=t.data.records;var a={};e.$each(t.data.records,function(e){a[e.dataValue]=e.dataText}),e.colsData[1].displace=a}})},changeEnergyName:function(){var e=["购入","产出","工艺产出","自产自用"],t=this.energyIdOptions;if(this.addForm.energyId&&this.addForm.energyType){for(var a="",r=0;r<t.length;r++)t[r].dataValue==this.addForm.energyId&&(a=t[r].dataText);this.addForm.energyName=a+"("+e[this.addForm.energyType]+")"}},editTb:function(e){this.dialogVisible=!0,this.addForm=JSON.parse(n()(e))},closeDialog:function(e){this.$nextTick(function(){this.$refs[e].resetFields(),this.addForm={entId:"",energyName:"",energyId:"",energyType:""}})},saveDialog:function(e){var t=this;this.$refs[e].validate(function(e){e&&t.$ajax[t.urlType]("rest/entEnergy",t.addForm).then(function(e){e.data.status?(t.$message.success(e.data.answerMsg),t.dialogVisible=!1,t.getTbList()):t.$message.error(e.data.answerMsg)})})},delTb:function(e){var t=this;this.$confirm("是否删除该记录?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){t.$ajax.delete("rest/entEnergy",{params:{id:e.id}}).then(function(e){e.data.status?(t.$message.success(e.data.answerMsg),t.getTbList()):t.$message.error(e.data.answerMsg)})})}},mounted:function(){this.getEnergyIdInfo(),this.getEnt(),this.getTbList()}}},ruZT:function(e,t,a){"use strict";var r=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("da-manage-page",[a("div",{attrs:{slot:"button"},slot:"button"},[a("el-button",{attrs:{icon:"my-zengjia"},on:{click:function(t){e.dialogVisible=!0}}},[e._v("新增")])],1),e._v(" "),a("el-form",{attrs:{slot:"search",inline:!0,model:e.searchForm.formdata},slot:"search"},[a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:e.getTbList}},[e._v("刷新")])],1)],1),e._v(" "),a("da-table",{ref:"table",attrs:{slot:"content",colums:e.colsData,"table-list":e.tableList,"search-data":e.searchForm,showSelection:!1},on:{callBack:e.getTbList},slot:"content"},[a("el-table-column",{attrs:{slot:"operation",property:"operation",label:"操作",width:"150",align:"center",fixed:"right"},slot:"operation",scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{size:"small"},on:{click:function(a){e.editTb(t.row)}}},[e._v("编辑")]),e._v(" "),a("el-button",{attrs:{size:"small"},on:{click:function(a){e.delTb(t.row)}}},[e._v("删除")])]}}])})],1)],1),e._v(" "),a("da-dialog",{attrs:{title:e.title,visible:e.dialogVisible},on:{"update:visible":function(t){e.dialogVisible=t},close:function(t){e.closeDialog("addForm")}}},[a("el-form",{ref:"addForm",attrs:{"label-width":"120px",model:e.addForm,rules:e.rules}},[a("el-form-item",{directives:[{name:"show",rawName:"v-show",value:e.entData.length>1,expression:"entData.length>1"}],attrs:{label:"企业名称",prop:"entId"}},[a("el-select",{attrs:{filterable:"",size:"medium",placeholder:"请选择"},model:{value:e.addForm.entId,callback:function(t){e.$set(e.addForm,"entId",t)},expression:"addForm.entId"}},e._l(e.entData,function(e,t){return a("el-option",{key:t,attrs:{label:e.name,value:e.id}})}))],1),e._v(" "),a("el-form-item",{attrs:{label:"能源名称",prop:"energyId"}},[a("el-select",{attrs:{filterable:"",placeholder:"请选择"},on:{change:e.changeEnergyName},model:{value:e.addForm.energyId,callback:function(t){e.$set(e.addForm,"energyId",t)},expression:"addForm.energyId"}},e._l(e.energyIdOptions,function(e){return a("el-option",{key:e.dataValue,attrs:{label:e.dataText,value:e.dataValue}})}))],1),e._v(" "),a("el-form-item",{attrs:{label:"能源种类",prop:"energyType"}},[a("el-select",{attrs:{filterable:"",placeholder:"请选择"},on:{change:e.changeEnergyName},model:{value:e.addForm.energyType,callback:function(t){e.$set(e.addForm,"energyType",t)},expression:"addForm.energyType"}},e._l(e.energyTypeOptions,function(e){return a("el-option",{key:e.dataValue,attrs:{label:e.dataText,value:parseInt(e.dataValue)}})}))],1),e._v(" "),a("el-form-item",{attrs:{label:"自定义能源名称",prop:"energyName"}},[a("el-input",{attrs:{"auto-complete":"off"},model:{value:e.addForm.energyName,callback:function(t){e.$set(e.addForm,"energyName",t)},expression:"addForm.energyName"}})],1)],1),e._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{type:"primary"},on:{click:function(t){e.saveDialog("addForm")}}},[e._v("确 定")]),e._v(" "),a("el-button",{on:{click:function(t){e.dialogVisible=!1}}},[e._v("取 消")])],1)],1)],1)},n=[],o={render:r,staticRenderFns:n};t.a=o}});
//# sourceMappingURL=44.d56eca73dde9fc535501.js.map