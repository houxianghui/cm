var currActionId;
var currStep;
var currButton;
function floatButton(){
	$("#return").button().css({"position":"fixed","bottom":0,"right":"50%","font-size":20});
}
$(document).ready(function() {
	floatButton();
	$("#return").click(function(){
		history.back();
	});
	//滚动条事件
	$(window).scroll(function(){
		floatButton();
	});
	
	$("table").attr("class", "dtPanel_Left").attr("align","center");
	$("tr td:nth-child(4)").attr("nowrap");
	load();
	$(":button[name='dist']").attr("onclick","").click(function(){
		currButton = $(this);
		var actionId = $(this).attr("id").split("-");
		currActionId = actionId[0];
		currStep = actionId[1];
		$(":input[name='actionId']").val(actionId[0]);
		$(":input[name='step']").val(actionId[1]);
		reload(currActionId);//按钮的id是actionId-step
		$( "#dialog-form" ).dialog( "open" ).dialog({"title":stepMap.get(actionId[1])+"-"+actionMap.get(actionId[0])});
	});
	$( "#dialog-form" ).dialog({
	      autoOpen: false,
	      height: 'auto',
	      width: 'auto',
	      modal: true,
	      
	      close: function() {
	    	  $.get("ProjectDistribute.do?act=getSelectedStaff",{	
					projectId:$(":input[name='projectId']").val(),
					actionId:$(":input[name='actionId']").val(),
					step:$(":input[name='step']").val(),
					oper:"oper"
				},
				function(result){
					var obj = $.parseJSON(result);
					if(obj){
						currButton.parent().prev().html(obj.msg);
					}
					
				}
			);
	      }
	    });
});

function load(){
	$("#notSelectedStaff").jqGrid(
			{
				url : getNotSelectedUrl(),
				datatype : 'json',
				mtype : 'GET',
				colNames : [ '姓名', '所属部门','员工编号', '职位', '级别', '所属地域', "工作年限",
						"IT技能" ],
				colModel : [ {
					name : 'userName',
					index : 'USER_NAME',
					width : 90
				},  {
					name : 'departId',
					index : 'DEPART_ID',
					width : 80,
					align : 'right'
				}, {
					name : 'staffId',
					index : 'STAFF_ID',
					width : 70
				},{
					name : 'post',
					index : 'POST',
					width : 80,
					align : 'right'
				}, {
					name : 'level',
					index : 'LEVEL',
					width : 80,
					align : 'right'
				}, {
					name : 'area',
					index : 'AREA',
					width : 80,
					align : 'right'
				}, {
					name : 'workYears',
					index : 'WORK_YEARS',
					width : 80,
					align : 'right'
				}, {
					name : 'itTech',
					index : 'IT_TECH',
					width : 80,
					align : 'right'
				}],
				cmTemplate : {
					sortable : false
				},
				jsonReader : {
					id : "userId",
					repeatitems : false
				},
				pager : $('#pager'),
				rowNum : 30,
				rowList : [ 10, 20, 30 ],
				sortname : 'USER_NAME',
				sortorder : 'asc',
				viewrecords : true,
				shrinkToFit : false,
				gridview : true,
				caption : '未分配人员',
				multiselect : true,
				height : '300',
				width : '220',
				onSelectRow : function(id) {

				}
			});
	$("#notSelectedStaff").jqGrid('navGrid', '#pager', {
		edit : false,
		add : false,
		del : false
	});

	$("#selectedStaff").jqGrid(
			{
				url :getSelectedUrl(),
				datatype : 'json',
				mtype : 'GET',
				colNames : [ 'ID','ACTION_ID','姓名', '任务名称','开始时间', '结束时间', '投入百分比' ,'模块','备注','userId'],
				colModel : [ {
					name : 'id',
					index : 'id',
					width : 70,
					hidden:true
				},  {
					name : 'actionId',
					index : 'ACTION_ID',
					width : 70,
					editable:true,
					hidden:true
				},{
					name : 'userName',
					index : 'USER_NAME',
					width : 40
				}, {
					name:'taskName',
					index:'TASK_NAME',
					width:150,
					editable:true,
					edittype:'textarea',
					editoptions:{rows:"3",cols:"40"}
				},{
					name : 'startDate',
					index : 'START_DATE',
					width : 70,
					align : 'right',
					editable:true
				}, {
					name : 'endDate',
					index : 'END_DATE',
					width : 70,
					align : 'right',
					editable:true
				}, {
					name : 'percent',
					index : 'PERCENT',
					width : 20,
					align : 'right',
					editable:true
				},{
					name:'moduleId',
					index:'MODULE_ID',
					width:50,
					align:'center',
					editable:true,
					edittype:'select',
					editoptions:{
						dataUrl:'ModuleDef.do?act=getModuleSelect'
					},
					formatter:moduleFormatter
				}, {
					name:'memo',
					index:"MEMO",
					width:150,
					align:'center',
					editable:true,
					edittype:'textarea',
					editoptions:{rows:"3",cols:"40"}
				},{
					name:'userId',
					index:'USER_ID',
					hidden:true,
					editable:true
				}],
				jsonReader : {
					id : "id",
					repeatitems : false
				},
				multiselect : true,
				pager : $('#selectedPager'),
				rowNum : 30,
				rowList : [ 10, 20, 30 ],
				sortname : 'START_DATE',
				sortorder : 'asc',
				viewrecords : true,
				width : 800,
				height : 300,
				gridview : true,
				caption : '已分配人员',
				editurl:getSelectedEditUrl(),
				
				onSelectRow:function(id){
					if(lastsel!=id){
						$("#selectedStaff").jqGrid('saveRow',lastsel,function(result){
							var obj = $.parseJSON(result.responseText);
							if(obj.msg!= "success"){
								alert(obj.msg);
							}
							lastsel = null;
							reload();
						});
					}
					if(id){
						jQuery('#selectedStaff').jqGrid('restoreRow',lastsel);
						jQuery('#selectedStaff').jqGrid('editRow',id,true,pickDate);
						lastsel=id;
					}
				}
			}).jqGrid('navGrid', '#selectedPager', {
				edit : false,
				add : false,
				del : false,
				search:false
			}).jqGrid('navButtonAdd',"#selectedPager",{
				caption:'保存',
				buttonicon:'ui-icon-disk',
				onClickButton:function(){
					if(!lastsel){
						alert('没有数据更改');
						return;
					}
					$("#selectedStaff").jqGrid('saveRow',lastsel,function(result){
						var obj = $.parseJSON(result.responseText);
						if(obj.msg!= "success"){
							alert(obj.msg);
						}
						reload();
						lastsel=null;
					});
				},
				position:"first"
			});
	$(":button").attr("class","Button");//.button();
	$("#add").click(
		function() {
			var id = $("#notSelectedStaff").jqGrid('getGridParam','selarrrow');
			if (id.length == 0) {
				alert("请选择记录");
				return;
			}
			id = id+"";
			$.get("ProjectDistribute.do?act=add",{	
					id:id,
					projectId:$(":input[name='projectId']").val(),
					actionId:$(":input[name='actionId']").val(),
					step:$(":input[name='step']").val()
				},
				function(result){
					var obj = $.parseJSON(result);
					if(obj.msg=='success'){
						$("#notSelectedStaff").trigger("reloadGrid");
						$("#selectedStaff").trigger('reloadGrid');
					}else{
						alert(obj.msg);
					}
				}
			);
		});
	$("#remove").click(function(){
		var id = $("#selectedStaff").jqGrid('getGridParam','selarrrow');
		id=id+"";
		if (id.length == 0) {
			alert("请选择记录");
			return;
		}
		$.post("ProjectDistribute.do?act=delete",{	
				id:id,oper:'oper'
			},
			function(result){
				var obj = $.parseJSON(result);
				if(obj.msg=='success'){
					$("#notSelectedStaff").trigger("reloadGrid");
					$("#selectedStaff").trigger('reloadGrid');
				}else{
					alert(obj.msg);
				}
			}
		);
	});
}

function reload(actionId){
	if(!actionId){
		actionId = $(":input[name='actionId']").val();
	}
	$("#notSelectedStaff").setGridParam({url:getNotSelectedUrl(actionId)}).trigger("reloadGrid");
	$("#selectedStaff").setGridParam({url:getSelectedUrl(actionId)}).trigger('reloadGrid');
}
function getNotSelectedUrl(actionId){
	if(!actionId){
		actionId = $(":input[name='actionId']").val();
	}
	return 'ProjectDistribute.do?act=getNotSelectedStuff&projectId='
	+ $("input[name='projectId']").val() + "&actionId="
	+ actionId;
}
function getSelectedUrl(actionId){
	if(!actionId){
		actionId = $(":input[name='actionId']").val();
	}
	return  'ProjectDistribute.do?act=getSelectedStaff&projectId='
	+ $("input[name='projectId']").val() + "&actionId="
	+ actionId+"&step="+$("input[name='step']").val();
}
function getSelectedEditUrl(){
	return "ProjectDistribute_UTF.do?act=update";
}
var lastsel;
function pickDate(id){
	var obj = $("#"+id+"_startDate","#selectedStaff");
	obj.click(function(){
		new Calendar().show($(this).context);
	});
	$("#"+id+"_endDate","#selectedStaff").click(function(){
		new Calendar().show($(this).context);
	});
}