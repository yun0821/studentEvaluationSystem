webpackJsonp([15],{"1AoG":function(e,t,a){"use strict";var r=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("es-transfer",{attrs:{data:e.dataLists,"is-edit-scale":e.baseAttr.isEditScale,titles:e.baseAttr.titles,"subitem-id":e.paramsAttr,"subitem-attr":e.baseAttr.getPramsAttr,"value-key":e.baseAttr.valueKey,label:e.baseAttr.label},on:{defaultMeter:e.defaultMeter},model:{value:e.selectedLists,callback:function(t){e.selectedLists=t},expression:"selectedLists"}})],1)},s=[],i={render:r,staticRenderFns:s};t.a=i},"5Es0":function(e,t,a){"use strict";var r=a("yt7g");t.a={props:{value:{type:Array},subitemId:{type:[Number,String]},data:{type:Array,default:function(){return[]}},label:{type:String,default:"name"},subitemAttr:{type:String},valueKey:{type:String,default:"id"},checked:{type:String,default:"checked"},titles:{type:Array,default:function(){return["未关联表计","已关联表计"]}},isEditScale:{type:Boolean,default:!0}},data:function(){return{rightCheck:[],selected:[],dataToObj:{},moved:[],isClickShow:!1,transferForm:{calculateType:1,scale:"1"},fmRules:this.eachValidate({calculateType:{required:{required:!0,trigger:"change",message:"必选"}},scale:{min:0,required:{required:!0,number:!0}}})}},watch:{data:function(){var e=this,t={},s=[],i=[];this.$each(this.data,function(a){t[a[e.valueKey]]=a,1===a[e.checked]&&(s.push(a[e.valueKey]),i.push(a))}),this.selected=s,this.dataToObj=t,this.rightCheck=[],this.$emit("defaultMeter",a.i(r.l)(i)),this.$emit("input",i)}},computed:{isSelectedRight:function(){return 0===this.rightCheck.length&&(this.isClickShow=!1),this.isClickShow&&this.rightCheck.length>0}},methods:{renderFunc:function(e,t){var a=[t[this.label]];return t.scale&&t.calculateType&&a.push(e("el-button",{class:{"pull-right":!0,"mar-lr-5":!0,"trans-btn":!0},props:{type:t.calculateType>0?"success":"warning",size:"mini",round:!0},domProps:{innerHTML:(t.calculateType>0?"+":"-")+t.scale}})),e("span",a)},handleChange:function(e,t,a){var r=this;"right"===t?(this.isClickShow=!0,this.rightCheck=this.rightCheck.concat(a),this.isEditScale&&this.$nextTick(function(){r.transferForm={calculateType:1,scale:"1"}}),this.moved=a):this.rightCheck=[];var s=[];this.$each(this.selected,function(e){r.dataToObj[e][r.subitemAttr]=r.subitemId,s.push(r.dataToObj[e])}),this.$emit("input",s)},changeScale:function(){var e=this;this.$refs.transform.validate(function(t){t&&e.$each(e.moved,function(t){e.dataToObj[t].calculateType=e.transferForm.calculateType,e.dataToObj[t].scale=e.transferForm.scale,e.isClickShow=!1})})},resetData:function(){var e=this;this.isClickShow=!0,this.$nextTick(function(){e.transferForm={calculateType:1,scale:"1"}})},rightCheckChange:function(e,t,a){this.moved=e,this.rightCheck=e}}}},"9HKv":function(e,t,a){"use strict";var r=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("da-manage-page",[a("div",{attrs:{slot:"button"},slot:"button"},[a("el-button",{attrs:{icon:"my-zengjia"},on:{click:function(t){e.dialogVisible=!0}}},[e._v("新增")])],1),e._v(" "),a("el-form",{attrs:{slot:"search",inline:!0,model:e.searchForm.formdata},slot:"search"},[a("el-form-item",{attrs:{label:"分项名称"}},[a("el-input",{model:{value:e.searchForm.formdata.name,callback:function(t){e.$set(e.searchForm.formdata,"name",t)},expression:"searchForm.formdata.name"}})],1),e._v(" "),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:e.getTbList}},[e._v("查询")])],1)],1),e._v(" "),a("da-table",{ref:"table",attrs:{slot:"content",colums:e.colsData,"table-list":e.tableList,"search-data":e.searchForm,showSelection:!1},on:{callBack:e.getTbList},slot:"content"},[a("el-table-column",{attrs:{slot:"operation",property:"operation",label:"操作",width:"200",align:"center",fixed:"right"},slot:"operation",scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{size:"small"},on:{click:function(a){e.editTb(t.row)}}},[e._v("编辑")]),e._v(" "),a("el-button",{attrs:{size:"small"},on:{click:function(a){e.getTransfer("energySubitemMeter",t.row)}}},[e._v("关联表计")]),e._v(" "),a("el-button",{attrs:{size:"small"},on:{click:function(a){e.delTb(t.row)}}},[e._v("删除")])]}}])})],1)],1),e._v(" "),a("da-dialog",{attrs:{title:e.title,visible:e.dialogVisible,size:"450px"},on:{"update:visible":function(t){e.dialogVisible=t},close:function(t){e.closeDialog("addForm")}}},[a("el-form",{ref:"addForm",attrs:{"label-width":"90px",model:e.addForm,rules:e.rules}},[a("el-form-item",{attrs:{label:"分项名称",prop:"name"}},[a("el-input",{attrs:{"auto-complete":"off"},model:{value:e.addForm.name,callback:function(t){e.$set(e.addForm,"name",t)},expression:"addForm.name"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"顺序",prop:"showSort"}},[a("el-input",{attrs:{"auto-complete":"off"},model:{value:e.addForm.showSort,callback:function(t){e.$set(e.addForm,"showSort",t)},expression:"addForm.showSort"}})],1)],1),e._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{type:"primary"},on:{click:function(t){e.saveDialog("addForm")}}},[e._v("确 定")]),e._v(" "),a("el-button",{on:{click:function(t){e.dialogVisible=!1}}},[e._v("取 消")])],1)],1),e._v(" "),a("da-dialog",{attrs:{title:"关联表计",visible:e.energySubitemMeterDialogVisible,size:"650px"},on:{"update:visible":function(t){e.energySubitemMeterDialogVisible=t}}},[a("relev-transfer",{ref:"energySubitemMeter",attrs:{visible:e.energySubitemMeterDialogVisible,"params-attr":e.energySubitemId,"base-attr":e.meterAttr},on:{"update:visible":function(t){e.energySubitemMeterDialogVisible=t}}}),e._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{type:"primary"},on:{click:function(t){e.saveTransferDialog("energySubitemMeter")}}},[e._v("确 定")]),e._v(" "),a("el-button",{on:{click:function(t){e.energySubitemMeterDialogVisible=!1}}},[e._v("取 消")])],1)],1)],1)},s=[],i={render:r,staticRenderFns:s};t.a=i},"Z3+a":function(e,t,a){"use strict";var r=a("j2Yv"),s=a("yt7g");t.a={components:{esTransfer:r.a},props:{visible:{type:Boolean,default:!1},baseAttr:{type:Object,default:function(){return{dataUrl:"rest/enterpriseOrg/orgMeter",getPramsAttr:"orgId",saveUrl:"rest/enterpriseOrg/orgMeter",valueKey:"meterId",label:"meterName",titles:["未关联表计","已关联表计"],isEditScale:!0}}},paramsAttr:{type:[String,Number],default:"382"}},data:function(){return{defaultSelectLists:[],selectedLists:[],dataLists:[]}},methods:{getData:function(e){var t=this,a={};a[this.baseAttr.getPramsAttr]=this.paramsAttr,this.$ajax.get(this.baseAttr.dataUrl,{params:a}).then(function(e){e.data.status&&(t.dataLists=e.data.records)})},defaultMeter:function(e){this.defaultSelectLists=e},saveData:function(){var e=this;if(this.selectedLists.some(function(e){return null===e.calculateType}))return this.$message.error("尚有关联的表计未定义比例，请填写或重置该表计。"),!1;var t=[],r=this.baseAttr.valueKey,i=a.i(s.i)(this.defaultSelectLists,this.selectedLists,r,function(a,s){return e.defaultSelectLists.some(function(e){return a[r]===e[r]})&&e.selectedLists.some(function(e){return a[r]===e[r]})&&!t.some(function(e){return a[r]===e[r]})&&t.push(a),s.some(function(e){return a[r]===e[r]})});this.$ajax.post(this.baseAttr.saveUrl,{saveList:i.saveList,updateList:t,deleteList:i.deleteList}).then(function(t){e.$emit("update:visible",!1)})}},mounted:function(){}}},aO4Z:function(e,t,a){"use strict";var r=a("Z3+a"),s=a("1AoG"),i=a("J0+h"),n=i(r.a,s.a,null,null,null);t.a=n.exports},gclE:function(e,t,a){"use strict";var r=a("3cXf"),s=a.n(r),i=(a("yt7g"),a("aO4Z"));t.a={name:"energySubitem",components:{relevTransfer:i.a},data:function(){return{searchForm:{formdata:{name:""},currentPage:1,pageSize:20},tableList:{},colsData:[{label:"分项名称",prop:"name",align:"left",sortable:!0},{label:"顺序",prop:"showSort",align:"left",sortable:!0}],dialogVisible:!1,addForm:{name:"",showSort:""},rules:this.eachValidate({name:{required:!0,maxString:"40"},showSort:{}}),energySubitemId:"",energySubitemMeterDialogVisible:!1,meterAttr:{dataUrl:"rest/energySubitem/energySubitemMeter",getPramsAttr:"energySubitemId",saveUrl:"rest/energySubitem/energySubitemMeter",valueKey:"meterId",label:"meterName",titles:["未关联表计","已关联表计"],isEditScale:!0}}},computed:{title:function(){return this.addForm.id?"编辑分项资料管理":"新增分项资料管理"},urlType:function(){return this.addForm.id?"put":"post"}},watch:{},methods:{getTbList:function(){var e=this;this.$ajax.get("rest/energySubitem/page",{params:this.searchForm,loadingTarget:this.$refs.table}).then(function(t){t.data.status&&(e.tableList=t.data)})},editTb:function(e){this.dialogVisible=!0,this.addForm=JSON.parse(s()(e))},closeDialog:function(e){this.$nextTick(function(){this.$refs[e].resetFields(),this.addForm={name:"",showSort:""}})},saveDialog:function(e){var t=this;this.$refs[e].validate(function(e){e&&t.$ajax[t.urlType]("rest/energySubitem",t.addForm).then(function(e){e.data.status?(t.$message.success(e.data.answerMsg),t.dialogVisible=!1,t.getTbList()):t.$message.error(e.data.answerMsg)})})},delTb:function(e){var t=this;this.$confirm("是否删除该记录?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){t.$ajax.delete("rest/energySubitem",{params:{id:e.id}}).then(function(e){e.data.status?(t.$message.success(e.data.answerMsg),t.getTbList()):t.$message.error(e.data.answerMsg)})})},getTransfer:function(e,t){var a=this;this.energySubitemId=t.id,this[e+"DialogVisible"]=!0,this.$nextTick(function(){a.$refs[e].getData(t)})},saveTransferDialog:function(e){this.$refs[e].saveData()}},mounted:function(){this.getTbList()}}},j2Yv:function(e,t,a){"use strict";function r(e){a("uhKB")}var s=a("5Es0"),i=a("kVY8"),n=a("J0+h"),l=r,o=n(s.a,i.a,l,"data-v-3b6e2a01",null);t.a=o.exports},kVY8:function(e,t,a){"use strict";var r=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"transfer-wrap"},[a("el-transfer",{attrs:{data:e.data,props:{key:e.valueKey,label:e.label},"render-content":e.renderFunc,"right-default-checked":e.rightCheck,titles:e.titles,"button-texts":["撤销","新增"]},on:{change:e.handleChange,"right-check-change":e.rightCheckChange},model:{value:e.selected,callback:function(t){e.selected=t},expression:"selected"}},[e.isEditScale?a("template",{slot:"right-footer"},[a("el-button",{staticClass:"transfer-footer mar-lr-5",attrs:{size:"mini",disabled:0===e.rightCheck.length},on:{click:e.resetData}},[e._v("重置数据项")]),e._v(" "),a("small",{staticClass:"pull-right pad-10"},[e._v("*右侧显示计算比例")])],1):e._e(),e._v(" "),a("p",{attrs:{slot:"left-footer"},slot:"left-footer"})],2),e._v(" "),e.isEditScale?a("el-form",{directives:[{name:"show",rawName:"v-show",value:e.isSelectedRight,expression:"isSelectedRight"}],ref:"transform",staticClass:"transfer-fm",attrs:{inline:!0,model:e.transferForm,rules:e.fmRules}},[a("el-form-item",{attrs:{label:"计算类型",prop:"calculateType"}},[a("el-radio-group",{model:{value:e.transferForm.calculateType,callback:function(t){e.$set(e.transferForm,"calculateType",t)},expression:"transferForm.calculateType"}},[a("el-radio",{attrs:{label:1}},[e._v("+")]),e._v(" "),a("el-radio",{attrs:{label:-1}},[e._v("-")])],1)],1),e._v(" "),a("el-form-item",{attrs:{label:"比例",prop:"scale"}},[a("el-input",{model:{value:e.transferForm.scale,callback:function(t){e.$set(e.transferForm,"scale",t)},expression:"transferForm.scale"}})],1),e._v(" "),a("el-form-item",[a("el-button",{on:{click:e.changeScale}},[e._v("确定")])],1)],1):e._e()],1)},s=[],i={render:r,staticRenderFns:s};t.a=i},pgBw:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var r=a("gclE"),s=a("9HKv"),i=a("J0+h"),n=i(r.a,s.a,null,null,null);t.default=n.exports},uhKB:function(e,t,a){var r=a("xADa");"string"==typeof r&&(r=[[e.i,r,""]]),r.locals&&(e.exports=r.locals);a("FIqI")("9324b49a",r,!0,{})},xADa:function(e,t,a){t=e.exports=a("acE3")(!0),t.push([e.i,".transfer-wrap[data-v-3b6e2a01]{position:relative}.transfer-fm[data-v-3b6e2a01]{position:absolute;top:0;left:0;width:100%;height:100%;text-align:center;background-color:hsla(0,0%,100%,.9);z-index:1;display:flex;align-items:center;border:1px solid #eee;padding:18px;box-shadow:2px 1px 2px 2px #ddd}","",{version:3,sources:["C:/vscode/ysems_webapp/src/views/platform/template/transform.vue"],names:[],mappings:"AACA,gCACE,iBAAmB,CACpB,AACD,8BACE,kBAAmB,AACnB,MAAO,AACP,OAAQ,AACR,WAAY,AACZ,YAAa,AACb,kBAAmB,AACnB,oCAA2C,AAC3C,UAAW,AACX,aAAc,AACd,mBAAoB,AACpB,sBAAuB,AACvB,aAAc,AACd,+BAAiC,CAClC",file:"transform.vue",sourcesContent:["\n.transfer-wrap[data-v-3b6e2a01] {\r\n  position: relative;\n}\n.transfer-fm[data-v-3b6e2a01] {\r\n  position: absolute;\r\n  top: 0;\r\n  left: 0;\r\n  width: 100%;\r\n  height: 100%;\r\n  text-align: center;\r\n  background-color: rgba(255, 255, 255, 0.9);\r\n  z-index: 1;\r\n  display: flex;\r\n  align-items: center;\r\n  border: 1px solid #eee;\r\n  padding: 18px;\r\n  box-shadow: 2px 1px 2px 2px #ddd;\n}\r\n"],sourceRoot:""}])}});
//# sourceMappingURL=15.4ca7bd9bdf3972ecd28a.js.map