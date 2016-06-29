$(function() {
	$("select[name='step']").change(function() {
		reload($(this).val());
	});
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
				colNames : [ 'ID','姓名', '任务名称','开始时间', '结束时间', '投入百分比' ,'备注','里程碑','userId'],
				colModel : [ {
					name : 'id',
					index : 'id',
					width : 70,
					hidden:true
				}, {
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
				}, {
					name:'memo',
					index:"MEMO",
					width:150,
					align:'center',
					editable:true,
					edittype:'textarea',
					editoptions:{rows:"3",cols:"40"}
				},{
					name:'mileStoneId',
					index:'MILE_STONE_ID',
					width:50,
					align:'center',
					editable:true,
					edittype:'select',
					editoptions:{
						dataUrl:'MileStone.do?act=getMilesStone&projectId='+$("input[name='projectId']").val()
					},
					formatter:mileStoneFormatter
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
				rowNum : 1000,
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
	$("#return").click(function(){
		window.location.href="ProjectDistribute.do?act=dl&projectId="+$(":input[name='projectId']").val();
	});
});

function reload(step){
	if(!step){
		step = $("input[name='step']").val();
	}
	$("#notSelectedStaff").setGridParam({url:getNotSelectedUrl(step)}).trigger("reloadGrid");
	$("#selectedStaff").setGridParam({url:getSelectedUrl(step)}).trigger('reloadGrid');
}
function getNotSelectedUrl(step){
	if(!step){
		step = $(":input[name='step']").val();
	}
	return 'ProjectDistribute.do?act=getNotSelectedStuff&projectId='
	+ $("input[name='projectId']").val() + "&step="
	+ step;
}
function getSelectedUrl(step){
	if(!step){
		step = $(":input[name='step']").val();
	}
	return  'ProjectDistribute.do?act=getSelectedStaff&projectId='
	+ $("input[name='projectId']").val() + "&step="
	+ step;
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