$("#subsys").jqGrid({
	url:'',
	dataType:'json',
	mType:'GET',
	colNames : [ '子系统', '前一版本号', '新版本号'],
	colModel : [ {
		name : 'sysName',
		index : 'SYS_NAME',
		width : 90,
		editable:true,
		edittype:'select',
		editoptions:{}
	}, {
		name : 'preVersion',
		index : 'PRE_VERSION',
		width : 70,
		editable:true
	},{
		name:'nextVersion',
		index:'NEXT_VERSION',
		width: 70,
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
	viewrecords : true,
	width : 500,
	height : 300,
	gridview : true,
	caption : '子系统版本',
	editurl:'',
});
$('#add').click(function(){
	jQuery("#subsys").jqGrid('editGridRow',"new",{height:280,reloadAfterSubmit:true});
});
$("#edit").click(function(){
	var gr = jQuery("#editgrid").jqGrid('getGridParam','selrow'); 
	if( gr != null ) 
		jQuery("#editgrid").jqGrid('editGridRow',gr,{height:280,reloadAfterSubmit:false});
	else
		alert("请选择行");
});