webpackJsonp([29],{NCJU:function(a,e,t){"use strict";var l=function(){var a=this,e=a.$createElement,t=a._self._c||e;return t("div",[t("da-panel",{attrs:{"padding-content":"tr","no-head":!0}},[t("div",{staticClass:"alarmhistory-padb-10"},[t("label",[a._v("告警部门：")]),a._v(" "),t("da-radio-select",{attrs:{maxNum:1,"option-lists":a.alarmDepartmentList,label:"name","value-key":"id"},on:{change:a.changeAlarmDepartment},model:{value:a.alarmDepartment,callback:function(e){a.alarmDepartment=e},expression:"alarmDepartment"}}),a._v(" "),t("label",[a._v("时间：")]),a._v(" "),t("da-time-select",{attrs:{"time-type":a.timeType,"show-time":[3,5,8]},on:{"update:timeType":function(e){a.timeType=e},change:a.changeAlarmTime},model:{value:a.alarmTime,callback:function(e){a.alarmTime=e},expression:"alarmTime"}}),a._v(" "),t("da-radio-select",{staticClass:"alarmhistory-padl-10",attrs:{maxNum:3,"option-lists":a.attentionAlarmList,label:"name","value-key":"id"},on:{change:a.changeAttentionAlarm},model:{value:a.attentionAlarm,callback:function(e){a.attentionAlarm=e},expression:"attentionAlarm"}})],1),a._v(" "),t("div",{staticClass:"inblock alarmhistory-padb-10"},[t("label",[a._v("告警类型：")]),a._v(" "),t("da-radio-select",{attrs:{maxNum:1,"option-lists":a.alarmCategoryList,label:"name","value-key":"id"},on:{change:a.changeAlarmCategory},model:{value:a.alarmCategory,callback:function(e){a.alarmCategory=e},expression:"alarmCategory"}}),a._v(" "),t("label",[a._v("告警设备：")]),a._v(" "),t("da-radio-select",{attrs:{maxNum:1,"option-lists":a.alarmEquipmentList,label:"name","value-key":"id"},on:{change:a.changeAlarmEquipment},model:{value:a.alarmEquipment,callback:function(e){a.alarmEquipment=e},expression:"alarmEquipment"}})],1),a._v(" "),t("div",{staticClass:"inblock alarmhistory-padb-10"},[t("label",[a._v("告警级别：")]),a._v(" "),t("da-radio-select",{attrs:{maxNum:1,"option-lists":a.alarmLevelList,label:"name","value-key":"id"},on:{change:a.changealArmLevel},model:{value:a.alarmLevel,callback:function(e){a.alarmLevel=e},expression:"alarmLevel"}}),a._v(" "),t("label",[a._v("是否确认：")]),a._v(" "),t("da-radio-select",{attrs:{maxNum:1,"option-lists":a.alarmConfirmList,label:"name","value-key":"id"},on:{change:a.changeAlarmConfirm},model:{value:a.alarmConfirm,callback:function(e){a.alarmConfirm=e},expression:"alarmConfirm"}}),a._v(" "),t("span",{staticClass:"alarmhistory-padl-10"},[t("button",{staticClass:"el-button el-button--primary",attrs:{type:"button"},on:{click:a.search}},[t("span",[a._v("查询")])])])],1)]),a._v(" "),t("da-panel",{ref:"panelCount",attrs:{title:"告警信息详细清单",width:"100%",height:"auto","padding-content":"tblr","min-height":"200px",padding:"t"}},[t("da-table",{staticClass:"analysis-table",attrs:{colums:a.alarmDetailcolumnData,"front-page":!0,"table-list":a.alarmDetailtable}},[t("el-table-column",{attrs:{slot:"operation",property:"operation",label:"详细",width:"150",align:"center",fixed:"right"},slot:"operation",scopedSlots:a._u([{key:"default",fn:function(e){return[t("el-button",{attrs:{size:"small"},on:{click:function(t){a.detail(e.row)}}},[a._v("查看")])]}}])})],1)],1),a._v(" "),t("da-dialog",{attrs:{title:"告警详情",visible:a.dialogVisible},on:{"update:visible":function(e){a.dialogVisible=e}}},[t("el-form",{ref:"diaAlarmDetail",attrs:{"label-width":"120px",model:a.diaAlarmDetail}},[t("el-form-item",{attrs:{label:"告警类型",prop:"alarmGroupName"}},[t("el-input",{attrs:{"auto-complete":"off"},model:{value:a.diaAlarmDetail.alarmGroupName,callback:function(e){a.$set(a.diaAlarmDetail,"alarmGroupName",e)},expression:"diaAlarmDetail.alarmGroupName"}})],1),a._v(" "),t("el-form-item",{attrs:{label:"告警详情",prop:"alarmDetail"}},[t("el-input",{attrs:{"auto-complete":"off",type:"textarea"},model:{value:a.diaAlarmDetail.alarmDetail,callback:function(e){a.$set(a.diaAlarmDetail,"alarmDetail",e)},expression:"diaAlarmDetail.alarmDetail"}})],1),a._v(" "),t("el-form-item",{attrs:{label:"所属部门",prop:"orgId"}},[t("el-input",{attrs:{"auto-complete":"off"},model:{value:a.diaAlarmDetail.orgId,callback:function(e){a.$set(a.diaAlarmDetail,"orgId",e)},expression:"diaAlarmDetail.orgId"}})],1),a._v(" "),t("el-form-item",{attrs:{label:"计量名称",prop:"meterName"}},[t("el-input",{attrs:{"auto-complete":"off"},model:{value:a.diaAlarmDetail.meterName,callback:function(e){a.$set(a.diaAlarmDetail,"meterName",e)},expression:"diaAlarmDetail.meterName"}})],1),a._v(" "),t("el-form-item",{attrs:{label:"数据项名称",prop:"value"}},[t("el-input",{attrs:{"auto-complete":"off"},model:{value:a.diaAlarmDetail.value,callback:function(e){a.$set(a.diaAlarmDetail,"value",e)},expression:"diaAlarmDetail.value"}})],1),a._v(" "),t("el-form-item",{attrs:{label:"告警时间",prop:"alarmTime"}},[t("el-date-picker",{attrs:{type:"date",format:"yyyy-MM-dd"},model:{value:a.diaAlarmDetail.alarmTime,callback:function(e){a.$set(a.diaAlarmDetail,"alarmTime",e)},expression:"diaAlarmDetail.alarmTime"}})],1),a._v(" "),t("el-form-item",{attrs:{label:"是否确认",prop:"isConfirm"}},[t("el-input",{attrs:{"auto-complete":"off"},model:{value:a.confirmOrNot,callback:function(e){a.confirmOrNot=e},expression:"confirmOrNot"}})],1)],1)],1)],1)},i=[],r={render:l,staticRenderFns:i};e.a=r},Sq85:function(a,e,t){var l=t("tCLC");"string"==typeof l&&(l=[[a.i,l,""]]),l.locals&&(a.exports=l.locals);t("FIqI")("465c5559",l,!0,{})},ayhS:function(a,e,t){"use strict";var l=t("3cXf"),i=t.n(l),r=t("yt7g");e.a={data:function(){return{allAlarmData:[],alarmTime:"",timeType:3,beginTime:"",finishTime:"",alarmDepartment:"",selectDepartment:"",alarmDepartmentList:[],attentionAlarm:"",selectAttentionAlarm:"",selectAlarmCode:"",attentionAlarmList:[{id:0,name:"所有的告警"},{id:1,name:"我关注的告警"}],alarmLevelList:[{name:"全部",id:0},{name:"一类",id:1},{name:"二类",id:2},{name:"三类",id:3},{name:"四类",id:4}],selectAlarmLevel:"",alarmLevel:"",alarmCategory:"",alarmCategoryList:[],selectAlarmCategory:"",alarmEquipment:"",selectAlarmEquipment:"",alarmEquipmentList:[],alarmConfirm:"",selectAlarmConfirm:"",alarmConfirmList:[{name:"全部",id:0},{name:"已确认",id:1},{name:"未确认",id:2}],alarmDetailcolumnData:[{label:"告警类型",prop:"alarmGroupName"},{label:"告警详情",prop:"alarmDetail",subStr:!0,popover:!0},{label:"所属部门",prop:"orgId",displace:{}},{label:"计量点名称",prop:"meterName"},{label:"数据项名称",prop:"value"},{label:"告警时间",prop:"alarmTime",format:"YYYY-MM-DD"},{label:"是否确认",prop:"isConfirm",displace:{0:"未确认",1:"确认"}}],alarmDetailtable:[],diaAlarmDetail:{},dialogVisible:!1,confirmOrNot:""}},methods:{inintData:function(){this.finishTime=this.$moment(new Date,"YYYY-MM-DD"),this.beginTime=this.finishTime,this.getAlarmDepartment(),this.getAlarmEquipment(),this.getAttentionAlarm(),this.getExceptionCountList()},loadData:function(){this.getAlarmDetailtable()},changeAlarmTime:function(){if(3==this.timeType)this.beginTime=this.alarmTime,this.finishTime=this.alarmTime;else if(5==this.timeType){var a=t.i(r.f)(this.alarmTime,5);this.beginTime=a[0],this.finishTime=a[1]}else{var e=t.i(r.f)(this.alarmTime,8);this.beginTime=e[0],this.finishTime=e[1]}},getAlarmDepartment:function(){var a=this;this.$ajax.get("rest/analysisData/depart",{params:{title:"查询部门列表",orgTypes:null,parentIds:null}}).then(function(e){if(e.data.status){var t=[];a.$each(e.data.records,function(a){t.push({name:a.name,idValue:a.id})}),t=[{name:"全部",idValue:""}].concat(t),a.$each(t,function(e,t){a.alarmDepartmentList.push({name:e.name,idValue:e.idValue,id:t})});a.$each(e.data.records,function(a){t.push({name:a.name,orgId:a.id})});var l=void 0,l={};t.forEach(function(a){l[a.orgId]=a.name}),a.alarmDetailcolumnData[2].displace=l}})},changeAlarmDepartment:function(){for(var a=0;a<this.alarmDepartmentList.length;a++)if(this.alarmDepartment==this.alarmDepartmentList[a].id){this.selectDepartment=this.alarmDepartmentList[a].idValue;break}},getAttentionAlarm:function(){var a=this;this.$ajax.get("rest/analysisData/alarmItem",{params:{title:"关注的告警",userIds:null,alarmGroups:null,receiveTypes:1,isAlarmGroup:null,isReceiveType:!0}}).then(function(e){if(e.data.status)for(var t=0;t<e.data.records.length;t++)t<e.data.records.length?a.selectAttentionAlarm+=e.data.records[t].id+",":a.selectAttentionAlarm+=e.data.records[t].id})},changeAttentionAlarm:function(){0==this.attentionAlarm?this.selectAlarmCode="":this.selectAlarmCode=this.selectAttentionAlarm},getExceptionCountList:function(){var a=this;this.$ajax.get("rest/analysisData/alarmGroup",{params:{title:"查询告警种类"}}).then(function(e){if(e.data.status){var t=[];a.$each(e.data.records,function(a){t.push({name:a.name,idValue:a.id,value:0})}),a.exceptionCountList=t,t=[{name:"全部",idValue:"",value:0}].concat(t),a.$each(t,function(e,t){a.alarmCategoryList.push({name:e.name,idValue:e.idValue,id:t})}),a.selectAlarmCategory=a.alarmCategoryList[0].idValue}})},changealArmLevel:function(){switch(this.alarmLevel){case 0:this.selectAlarmLevel="";break;case 1:this.selectAlarmLevel="1";break;case 2:this.selectAlarmLevel="2";break;case 3:this.selectAlarmLevel="3";break;case 4:this.selectAlarmLevel="4"}},changeAlarmCategory:function(){for(var a=0;a<this.alarmCategoryList.length;a++)if(this.alarmCategory==this.alarmCategoryList[a].id){this.selectAlarmCategory=this.alarmCategoryList[a].idValue;break}},getAlarmEquipment:function(){var a=this;this.$ajax.get("rest/analysisData/equip",{params:{title:"查询设备",orgTypes:null,parentIds:null}}).then(function(e){if(e.data.status){var t=[];a.$each(e.data.records,function(a){t.push({name:a.name,idValue:a.id})}),t=[{name:"全部",idValue:""}].concat(t),a.$each(t,function(e,t){a.alarmEquipmentList.push({name:e.name,idValue:e.idValue,id:t})})}})},changeAlarmEquipment:function(){for(var a=0;a<this.alarmEquipmentList.length;a++)if(this.alarmEquipment==this.alarmEquipmentList[a].id){this.selectAlarmEquipment=this.alarmEquipmentList[a].idValue;break}},changeAlarmConfirm:function(){switch(this.alarmConfirm){case 0:this.selectAlarmConfirm=null;break;case 1:this.selectAlarmConfirm=!0;break;case 2:this.selectAlarmConfirm=!1}},getAlarmDetailtable:function(){var a=this;this.$ajax.get("rest/analysisData/alarmData",{params:{title:"告警详细信息",userIds:null,alarmLevels:this.selectAlarmLevel,alarmGroups:this.selectAlarmCategory,alarmCodes:this.selectAlarmCode,isConfirm:this.selectAlarmConfirm,receiveTypes:null,orgIds:this.selectDepartment,equipIds:this.selectAlarmEquipment,productIds:null,alarmItems:null,startTime:this.beginTime,endTime:this.finishTime,isCount:!1,countCycle:"0",groupBys:"101001100"}}).then(function(e){e.data.status&&(a.alarmDetailtable=e.data.records)})},detail:function(a){this.dialogVisible=!0,this.diaAlarmDetail=JSON.parse(i()(a)),this.confirmOrNot=0==this.diaAlarmDetail.isConfirm?"未确认":"确认"},search:function(){this.loadData()}},mounted:function(){this.inintData(),this.loadData()}}},tCLC:function(a,e,t){e=a.exports=t("acE3")(!0),e.push([a.i,".alarmhistory-padb-10[data-v-fcfc1e76]{padding-bottom:10px}.alarmhistory-padb-10>label[data-v-fcfc1e76],.alarmhistory-padl-10[data-v-fcfc1e76]{padding-left:10px}","",{version:3,sources:["C:/vscode/ysems_webapp/src/views/platform/running/alarmHistorySearch.vue"],names:[],mappings:"AACA,uCACE,mBAAqB,CACtB,AAID,oFACE,iBAAmB,CACpB",file:"alarmHistorySearch.vue",sourcesContent:["\n.alarmhistory-padb-10[data-v-fcfc1e76] {\r\n  padding-bottom: 10px;\n}\n.alarmhistory-padb-10 > label[data-v-fcfc1e76] {\r\n  padding-left: 10px;\n}\n.alarmhistory-padl-10[data-v-fcfc1e76] {\r\n  padding-left: 10px;\n}\r\n"],sourceRoot:""}])},wC8c:function(a,e,t){"use strict";function l(a){t("Sq85")}Object.defineProperty(e,"__esModule",{value:!0});var i=t("ayhS"),r=t("NCJU"),n=t("J0+h"),m=l,s=n(i.a,r.a,m,"data-v-fcfc1e76",null);e.default=s.exports}});
//# sourceMappingURL=29.df708e5db4f82ace3254.js.map