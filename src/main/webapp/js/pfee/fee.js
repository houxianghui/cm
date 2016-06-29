$(function() {
	$("input[name='year']").change(function() {
		reload($(this).val());
	});
	$("select[name='budgetKind']").change(function() {
		reload($(this).val());
	});
	$("select[name='depart']").change(function() {
		reload($(this).val());
	});
	$("select[name='feeKind']").change(function() {
		reload($(this).val());
	});
	$("#selectedStaff").jqGrid(
			{
				url :getSelectedUrl(),
				datatype : 'json',
				mtype : 'GET',
				colNames : [ 'ID','明细', '01','02','03','04','05','06','07','08','09','10','11','12','费用类别'],
				colModel : [ {
					name : 'budgetId',
					index : 'BUDGET_ID',
					width : 70,
					hidden:true,
					editable:true
				},{
					name : 'projectNo',
					index : 'PROJECT_NO',
					width:150,
					editable:true,
					edittype:'select',
					editoptions:{
						dataUrl:'ProjectMaintain.do?act=makeProjectSelect'
					},
					formatter:projectFormatter
				},{
					name : 'feeAmt01',
					index : 'FEE_AMT01',
					width : 70,
					align : 'right',
					editable:true
				},{
					name : 'feeAmt02',
					index : 'FEE_AMT02',
					width : 70,
					align : 'right',
					editable:true
				},{
					name : 'feeAmt03',
					index : 'FEE_AMT03',
					width : 70,
					align : 'right',
					editable:true
				},{
					name : 'feeAmt04',
					index : 'FEE_AMT04',
					width : 70,
					align : 'right',
					editable:true
				},{
					name : 'feeAmt05',
					index : 'FEE_AMT05',
					width : 70,
					align : 'right',
					editable:true
				},{
					name : 'feeAmt06',
					index : 'FEE_AMT06',
					width : 70,
					align : 'right',
					editable:true
				},{
					name : 'feeAmt07',
					index : 'FEE_AMT07',
					width : 70,
					align : 'right',
					editable:true
				},{
					name : 'feeAmt08',
					index : 'FEE_AMT08',
					width : 70,
					align : 'right',
					editable:true
				},{
					name : 'feeAmt09',
					index : 'FEE_AMT09',
					width : 70,
					align : 'right',
					editable:true
				},{
					name : 'feeAmt10',
					index : 'FEE_AMT10',
					width : 70,
					align : 'right',
					editable:true
				},{
					name : 'feeAmt11',
					index : 'FEE_AMT11',
					width : 70,
					align : 'right',
					editable:true
				},{
					name : 'feeAmt12',
					index : 'FEE_AMT12',
					width : 70,
					align : 'right',
					editable:true
				},{
					name : 'feeKind',
					index : 'FEE_KIND',
					width : 100,
					align : 'right',
					editable:true ,
					edittype:"select",editoptions:{value:result},
					formatter:map7001Formatter
				}],
				jsonReader : {
					id : "budgetId",
					repeatitems : false
				},
				multiselect : false,
				pager : $('#selectedPager'),
				rowNum : '30',
				rowList : [ 30, 60, 90 ],
				sortname : 'id',
				sortorder : 'asc',
				viewrecords : true,
				width : 1024,
				height : 360,
				gridview : true,
				caption : '预算信息',
				editurl:getSelectedEditUrl(),
				
				onSelectRow:function(id){
					if(lastsel!=id){
						$("#selectedStaff").jqGrid('saveRow',lastsel,function(result){
							var obj = $.parseJSON(result.responseText);
							if(obj.msg!= "success"){
								alert('权限不足或部门预算小于项目总预算');
							}
							lastsel = null;
							reload();
						});
					}
					if(id){
						jQuery('#selectedStaff').jqGrid('restoreRow',lastsel);
						jQuery('#selectedStaff').jqGrid('editRow',id);
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
					$("#selectedStaff").jqGrid('saveRow',lastsel,function(result){
						var obj = $.parseJSON(result.responseText);
						if(obj.msg!= "success"){
							alert('权限不足或关键域为空');
						}
						reload();
						lastsel=null;
					});
				},
				position:"first"
			});
	$(":button").attr("class","Button");//.button();
	$("#add").click(function(){
			count=count-1;
			if(lastsel){
				$("#selectedStaff").jqGrid('saveRow',lastsel,function(result){
					var obj = $.parseJSON(result.responseText);
					if(obj.msg!= "success"){
						alert('保存失败,请检查权限');
					}
					reload();
				});
			}
			lastsel=count;
			var datarow = {budgetId:"",memo:"", feeAmt01:'',feeAmt02:'',feeAmt03:'',feeAmt04:'',feeAmt05:'',feeAmt06:'',feeAmt07:'',feeAmt08:'',feeAmt09:'',feeAmt10:'',feeAmt11:'',feeAmt12:'',feeKind:''}; 
			jQuery("#selectedStaff").jqGrid('addRowData',count,datarow);
			jQuery("#selectedStaff").jqGrid("editRow",""+count);
	});
	$("#remove").click(function(){
		var id = lastsel;
		if (id.length == 0) {
			alert("请选择记录");
			return;
		}
		$.post("BudgetInfo.do?act=delete",{	
			budgetId:id,oper:'oper'
			},
			function(result){
				var obj = $.parseJSON(result);
				if(obj.msg=='success'){
					reload();
				}else{
					alert(obj.msg);
				}
			}
		);
	});
	$("#return").click(function(){
		window.location.href='BudgetInfo.do?act=list';
	});
});

function reload(budgetKind){
	$("#selectedStaff").setGridParam({url:getSelectedUrl(budgetKind)}).trigger('reloadGrid');
}
function getSelectedUrl(budgetKind){
	var depart = $(":input[name='depart']").val();
	var feeKind = $(":input[name='feeKind']").val();
	var budgetKind = $(":input[name='budgetKind']").val();
	var year = $("input[name='year']").val();
	return  "BudgetInfo.do?act=getSelectedStaff&depart="
	+ depart + "&feeKind="
	+ feeKind + "&budgetKind="
	+ budgetKind+ "&year="
	+ year;
}
function getSelectedEditUrl(){
	return 'BudgetInfo_UTF.do?act=update';
}
var lastsel;
var count=0;