webpackJsonp([35],{"2XGO":function(t,e,a){"use strict";var o=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("da-manage-page",[a("div",{attrs:{slot:"button"},slot:"button"},[a("el-button",{attrs:{icon:"my-zengjia"},on:{click:function(e){t.dialogVisible=!0}}},[t._v("新增")])],1),t._v(" "),a("el-form",{attrs:{slot:"search",inline:!0,model:t.searchForm.formdata},slot:"search"},[a("el-form-item",{attrs:{label:"类型名称"}},[a("el-input",{model:{value:t.searchForm.formdata.name,callback:function(e){t.$set(t.searchForm.formdata,"name",e)},expression:"searchForm.formdata.name"}})],1),t._v(" "),a("el-form-item",{attrs:{label:"父类型"}},[a("da-select-tree",{attrs:{data:t.targetTypeData,defaultExpandAll:!0},model:{value:t.searchForm.formdata.parentId,callback:function(e){t.$set(t.searchForm.formdata,"parentId",e)},expression:"searchForm.formdata.parentId"}})],1),t._v(" "),a("el-form-item",{attrs:{label:"节点类型"}},[a("el-select",{model:{value:t.searchForm.formdata.nodeTypes,callback:function(e){t.$set(t.searchForm.formdata,"nodeTypes",e)},expression:"searchForm.formdata.nodeTypes"}},[a("el-option",{attrs:{label:"全部",value:"null"}}),t._v(" "),a("el-option",{attrs:{label:"实例节点",value:"1"}}),t._v(" "),a("el-option",{attrs:{label:"分类节点",value:"0"}})],1)],1),t._v(" "),a("el-form-item",{attrs:{label:"统计内容类型"}},[a("el-select",{attrs:{"auto-complete":"off",filterable:"",size:"medium",placeholder:"请选择"},on:{change:t.changeQueryContentType},model:{value:t.searchForm.formdata.contentTypes,callback:function(e){t.$set(t.searchForm.formdata,"contentTypes",e)},expression:"searchForm.formdata.contentTypes"}},t._l(t.staticContentData,function(t,e){return a("el-option",{key:e,attrs:{label:t.staticTypeName,value:t.staticType}})}))],1),t._v(" "),a("el-form-item",{attrs:{label:"统计内容"}},[a("el-select",{attrs:{"auto-complete":"off",filterable:"",size:"medium",placeholder:"请选择"},model:{value:t.searchForm.formdata.contentId,callback:function(e){t.$set(t.searchForm.formdata,"contentId",e)},expression:"searchForm.formdata.contentId"}},t._l(t.staticContentIdData,function(t,e){return a("el-option",{key:e,attrs:{label:t.name,value:t.id}})}))],1),t._v(" "),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:t.getTbList}},[t._v("查询")])],1)],1),t._v(" "),a("da-treeGrid",{ref:"typeGrid",attrs:{slot:"content",columns:t.colsData,"tree-structure":!0,"data-source":t.tableList,showSelection:!0,setTreeHeight:"true"},slot:"content"},[a("el-table-column",{attrs:{slot:"operation",property:"operation",label:"操作",width:"150",align:"center",fixed:"right"},slot:"operation",scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-button",{attrs:{size:"small"},on:{click:function(a){t.editTb(e.row)}}},[t._v("编辑")]),t._v(" "),a("el-button",{attrs:{size:"small"},on:{click:function(a){t.delTb(e.row)}}},[t._v("删除")])]}}])})],1)],1),t._v(" "),a("da-dialog",{attrs:{title:t.title,visible:t.dialogVisible},on:{"update:visible":function(e){t.dialogVisible=e},close:function(e){t.closeDialog("addForm")}}},[a("el-form",{ref:"addForm",attrs:{"label-width":"100px",model:t.addForm,rules:t.rules}},[a("el-form-item",{attrs:{label:"类型名称",prop:"name"}},[a("el-input",{attrs:{"auto-complete":"off"},model:{value:t.addForm.name,callback:function(e){t.$set(t.addForm,"name",e)},expression:"addForm.name"}})],1),t._v(" "),a("el-form-item",{attrs:{label:"父类型",prop:"parentId"}},[a("da-select-tree",{attrs:{data:t.targetTypeData,defaultExpandAll:!0},model:{value:t.addForm.parentId,callback:function(e){t.$set(t.addForm,"parentId",e)},expression:"addForm.parentId"}})],1),t._v(" "),a("el-form-item",{attrs:{label:"节点类型",prop:"nodeTypes"}},[a("el-radio-group",{model:{value:t.addForm.nodeTypes,callback:function(e){t.$set(t.addForm,"nodeTypes",e)},expression:"addForm.nodeTypes"}},t._l(t.nodeTypesOptions,function(e){return a("el-radio",{key:e.dataValue,attrs:{label:parseInt(e.dataValue)}},[t._v(t._s(e.dataText))])}))],1),t._v(" "),a("el-form-item",{attrs:{label:"单位类型",prop:"targetUnitType"}},[a("el-select",{attrs:{"auto-complete":"off",filterable:"",size:"medium",placeholder:"请选择"},on:{change:t.changeUnitType},model:{value:t.addForm.targetUnitType,callback:function(e){t.$set(t.addForm,"targetUnitType",e)},expression:"addForm.targetUnitType"}},t._l(t.unitTypeData,function(t,e){return a("el-option",{key:e,attrs:{label:t.unitTypeName,value:t.unitType}})}))],1),t._v(" "),a("el-form-item",{attrs:{label:"指标单位",prop:"targetUnit"}},[a("el-select",{attrs:{"auto-complete":"off",filterable:"",size:"medium",placeholder:"请选择"},model:{value:t.addForm.targetUnit,callback:function(e){t.$set(t.addForm,"targetUnit",e)},expression:"addForm.targetUnit"}},t._l(t.unitNameData,function(t,e){return a("el-option",{key:e,attrs:{label:t.unitName,value:t.unitValue}})}))],1),t._v(" "),a("el-form-item",{attrs:{label:"显示顺序",prop:"showSort"}},[a("el-input",{attrs:{"auto-complete":"off"},model:{value:t.addForm.showSort,callback:function(e){t.$set(t.addForm,"showSort",e)},expression:"addForm.showSort"}})],1),t._v(" "),a("el-form-item",{attrs:{label:"显示位数",prop:"displayPrecisions"}},[a("el-input",{attrs:{"auto-complete":"off"},model:{value:t.addForm.displayPrecisions,callback:function(e){t.$set(t.addForm,"displayPrecisions",e)},expression:"addForm.displayPrecisions"}})],1),t._v(" "),a("el-form-item",{attrs:{label:"正常使用",prop:"states"}},[a("el-radio-group",{model:{value:t.addForm.states,callback:function(e){t.$set(t.addForm,"states",e)},expression:"addForm.states"}},t._l(t.statesOptions,function(e){return a("el-radio",{key:e.dataValue,attrs:{label:parseInt(e.dataValue)}},[t._v(t._s(e.dataText))])}))],1),t._v(" "),a("el-form-item",{attrs:{label:"系统默认类型",prop:"defaultType"}},[a("el-radio-group",{model:{value:t.addForm.defaultType,callback:function(e){t.$set(t.addForm,"defaultType",e)},expression:"addForm.defaultType"}},t._l(t.defaultTypeOptions,function(e){return a("el-radio",{key:e.dataValue,attrs:{label:parseInt(e.dataValue)}},[t._v(t._s(e.dataText))])}))],1),t._v(" "),a("el-form-item",{attrs:{label:"统计内容类型",prop:"contentTypes"}},[a("el-select",{attrs:{"auto-complete":"off",filterable:"",size:"medium",placeholder:"请选择"},on:{change:t.changeContentType},model:{value:t.addForm.contentTypes,callback:function(e){t.$set(t.addForm,"contentTypes",e)},expression:"addForm.contentTypes"}},t._l(t.staticContentData,function(t,e){return a("el-option",{key:e,attrs:{label:t.staticTypeName,value:parseInt(t.staticType)}})}))],1),t._v(" "),a("el-form-item",{attrs:{label:"统计内容",prop:"contentId"}},[a("el-select",{attrs:{"auto-complete":"off",filterable:"",size:"medium",placeholder:"请选择"},model:{value:t.addForm.contentId,callback:function(e){t.$set(t.addForm,"contentId",e)},expression:"addForm.contentId"}},t._l(t.staticContentIdData,function(t,e){return a("el-option",{key:e,attrs:{label:t.name,value:parseInt(t.id)}})}))],1)],1),t._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{type:"primary"},on:{click:function(e){t.saveDialog("addForm")}}},[t._v("确 定")]),t._v(" "),a("el-button",{on:{click:function(e){t.dialogVisible=!1}}},[t._v("取 消")])],1)],1)],1)},r=[],n={render:o,staticRenderFns:r};e.a=n},"2xhI":function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var o=a("TNp6"),r=a("2XGO"),n=a("J0+h"),s=n(o.a,r.a,null,null,null);e.default=s.exports},TNp6:function(t,e,a){"use strict";var o=a("yt7g");e.a={name:"targetType",data:function(){return{targetTypeData:[],staticContentData:[],staticContentIdData:[],unitTypeData:[],unitNameData:[],nodeTypesOptions:[{dataText:"实例节点",dataValue:"1"},{dataText:"分类节点",dataValue:"0"}],statesOptions:[{dataText:"正常",dataValue:"1"},{dataText:"停用",dataValue:"0"}],defaultTypeOptions:[{dataText:"是",dataValue:"1"},{dataText:"否",dataValue:"0"}],searchForm:{formdata:{name:"",parentId:"",nodeTypes:"",contentTypes:"",contentId:""}},tableList:[],colsData:[{label:"类型名称",prop:"name",align:"left",width:"280px",sortable:!0},{label:"父类型",prop:"parentId",align:"left",sortable:!0,displace:{}},{label:"节点类型",prop:"nodeTypes",align:"left",sortable:!0,displace:{1:"实例节点",0:"分类节点",null:"全部"}},{label:"指标单位",prop:"targetUnit",align:"left",sortable:!0},{label:"单位类型",prop:"targetUnitType",align:"left",displace:{},sortable:!0},{label:"显示顺序",prop:"showSort",align:"left",sortable:!0},{label:"显示位数",prop:"displayPrecisions",align:"left",sortable:!0},{label:"正常使用",prop:"states",align:"left",displace:{0:"停用",1:"正常"},sortable:!0},{label:"系统默认类型",prop:"defaultType",align:"left",displace:{0:"否",1:"是"},sortable:!0},{label:"统计内容类型",prop:"contentTypes",align:"left",displace:{},sortable:!0}],dialogVisible:!1,addForm:{name:"",parentId:"",nodeTypes:1,targetUnit:"",targetUnitType:"",showSort:"99",displayPrecisions:"2",states:1,defaultType:1,contentTypes:"",contentId:""},rules:this.eachValidate({name:{required:!0,maxString:"50"},parentId:{required:{required:!0,trigger:"change"}},nodeTypes:{},targetUnit:{maxString:"20"},targetUnitType:{maxString:"20"},showSort:{required:!0},displayPrecisions:{},states:{required:!0},defaultType:{},contentTypes:{},contentId:{}})}},computed:{title:function(){return this.addForm.id?"编辑指标类型":"新增指标类型"},urlType:function(){return this.addForm.id?"put":"post"}},watch:{},methods:{getTbList:function(){var t=this;this.$ajax.get("rest/targetType/tree",{params:this.searchForm,loadingTarget:this.$refs.typeGrid}).then(function(e){e.data.status&&(t.tableList=e.data.records)})},editTb:function(t){return this.dialogVisible=!0,console.log(t),this.addForm={name:t.name,parentId:t.parentId,nodeTypes:t.nodeTypes,targetUnit:t.targetUnit,targetUnitType:t.targetUnitType,showSort:t.showSort,displayPrecisions:t.displayPrecisions,states:t.states,defaultType:t.defaultType,contentTypes:t.contentTypes,contentId:t.contentId},!1},closeDialog:function(t){this.$nextTick(function(){this.$refs[t].resetFields(),this.addForm={name:"",parentId:"",nodeTypes:1,targetUnit:"",targetUnitType:"",showSort:"",displayPrecisions:"",states:1,defaultType:1,contentTypes:"",contentId:""}})},saveDialog:function(t){var e=this;this.$refs[t].validate(function(t){t&&(e.findTargetTypeTree(!0),e.$ajax[e.urlType]("rest/targetType",e.addForm).then(function(t){t.data.status?(e.$message.success(t.data.answerMsg),e.dialogVisible=!1,e.getTbList()):e.$message.error(t.data.answerMsg)}))})},delTb:function(t){var e=this;this.$confirm("是否删除该记录?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){e.$ajax.delete("rest/targetType",{params:{id:t.id}}).then(function(t){t.data.status?(e.$message.success(t.data.answerMsg),e.getTbList()):e.$message.error(t.data.answerMsg)})})},findStaticType:function(){var t=this,e=this;e.$ajax({url:"rest/targetType/staticType",method:"get"}).then(function(r){if(r.data.status){e.staticContentData=r.data.records;var n={};a.i(o.j)(r.data.records,"staticType","staticTypeName",n),t.colsData[9].displace=n}})},findTargetTypeTree:function(t){var e=this,r=this;r.$ajax({url:"rest/targetData/targetTypeTree",method:"get"}).then(function(n){if(n.data.status&&(r.targetTypeData=n.data.records,void 0!=t)){var s={};a.i(o.j)(n.data.records,"id","name",s),e.colsData[1].displace=s}})},getContentId:function(t){var e=this,a=this,o=null;o=1===t?this.addForm.contentTypes:this.searchForm.formdata.contentTypes,a.$ajax({url:"rest/targetType/contentId",method:"get",params:{contentTypes:o},noLoading:!0}).then(function(t){t.data.status&&(a.staticContentIdData=t.data.records,e.addForm.contentId=e.addForm.contentId)})},getUnitType:function(){var t=this,e=this;e.$ajax({url:"rest/targetType/unitType",method:"get"}).then(function(r){if(r.data.status){e.unitTypeData=r.data.records;var n={};a.i(o.j)(r.data.records,"unitType","unitTypeName",n),t.colsData[4].displace=n}})},getUnit:function(){var t=this;t.$ajax({url:"rest/targetType/unitName",method:"get",params:{unitType:this.addForm.targetUnitType}}).then(function(e){e.data.status&&(t.unitNameData=e.data.records)})},changeContentType:function(){this.getContentId(1)},changeUnitType:function(){this.getUnit()},changeQueryContentType:function(){this.searchForm.formdata.contentId="",this.getContentId(2)}},mounted:function(){this.getTbList(),this.findStaticType(),this.findTargetTypeTree(!0),this.getUnitType()}}}});
//# sourceMappingURL=35.b53c3721e75b0d6b23d5.js.map