$(function() {
	$("#saveE").click(function(){
		reload($(this).val());
	});
	$("#selectedStaff").jqGrid(
			{
				url :getSelectedUrl(),
				datatype : 'json',
				mtype : 'GET',
				colNames : [ '序号','关联报销单号','出发日期','出发地点','到达日期','到达地点', '交通工具','单据张数','金额'],
				colModel : [ {
					name : 'tdetailId',
					index : 'TDETAIL_ID',
					width : 70,
					hidden:true,
					editable:true
				}, {
					name : 'expensesId',
					index : 'EXPENSES_ID',
					width:150,
					hidden:true,
					editable:true
				},{
					name : 'fromDate',
					index : 'FROM_DATE',
					width : 70,
					align : 'right',
					editable:true
				},{
					name : 'travelFrom',
					index : 'TRAVEL_FROM',
					width : 70,
					align : 'right',
					editable:true,
					edittype:"select",editoptions:{value:result7004},
					formatter:map7004Formatter
				},{
					name : 'toDate',
					index : 'TO_DATE',
					width : 70,
					align : 'right',
					editable:true
				},{
					name : 'travelTo',
					index : 'TRAVEL_TO',
					width : 70,
					align : 'right',
					editable:true,
					edittype:"select",editoptions:{value:result7004},
					formatter:map7004Formatter
				},{
					name : 'transportMode',
					index : 'TRANSPORT_MODE',
					width : 70,
					align : 'right',
					editable:true,
					edittype:"select",editoptions:{value:result},
					formatter:tranFormatter
				},{
					name : 'invoiceno',
					index : 'INVOICENO',
					width : 70,
					align : 'right',
					editable:true
				},{
					name : 'fee',
					index : 'FEE',
					width : 70,
					align : 'right',
					editable:true
				}],
				jsonReader : {
					id : "tdetailId",
					repeatitems : false
				},
				multiselect : false,
				pager : $('#selectedPager'),
				rowNum : '30',
				rowList : [ 30, 60, 90 ],
				sortname : 'id',
				sortorder : 'asc',
				viewrecords : true,
				width : 525,
				height : 360,
				gridview : true,
				caption : '差旅费信息',
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
	$("#add").click(function(){
			count=count-1;
			if(lastsel){
				$("#selectedStaff").jqGrid('saveRow',lastsel,function(result){
					var obj = $.parseJSON(result.responseText);
					if(obj.msg!= "success"){
						alert(obj.msg);
					}
					reload();
				});
			}
			lastsel=count;
			var datarow = {tdetailId:'',expensesId:$("input[name='expensesId']").val(),fromDate:'',travelFrom:'',toDate:'',travelTo:'',transportMode:$("input[name='result']").val(),invoiceno:'',fee:''}; 
			jQuery("#selectedStaff").jqGrid('addRowData',count,datarow);
			jQuery("#selectedStaff").jqGrid("editRow",""+count,true,pickDate);
	});
	$("#remove").click(function(){
		var id = lastsel;
		if (id.length == 0) {
			alert("请选择记录");
			return;
		}
		$.post("TravelDetail.do?act=delete",{	
			tdetailId:id,oper:'oper'
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
	$("#notselectedStaff").jqGrid(
			{
				url :getnotselectedUrl(),
				datatype : 'json',
				mtype : 'GET',
				colNames : [ '序号','关联报销单号','日期','时间', '报销原因','始发地-目的地','单据张数','金额'],
				colModel : [ {
					name : 'taxiId',
					index : 'TAXI_ID',
					width : 70,
					hidden:true,
					editable:true
				}, {
					name : 'expensesId',
					index : 'EXPENSES_ID',
					width:150,
					hidden:true,
					editable:true
				},{
					name : 'taxiDate',
					index : 'TAXI_DATE',
					width : 70,
					align : 'right',
					editable:true
				},{
					name : 'taxiTime',
					index : 'TAXI_TIME',
					width : 70,
					align : 'right',
					editable:true
				},
				{
					name : 'taxiReason',
					index : 'TAXI_REASON',
					width : 70,
					align : 'right',
					editable:true
				},{
					name : 'taxiPath',
					index : 'TAXI_PATH',
					width : 70,
					align : 'right',
					editable:true
				},{
					name : 'taxiInvoiceno',
					index : 'TAXI_INVOICENO',
					width : 70,
					align : 'right',
					editable:true
				},{
					name : 'taxiAmt',
					index : 'TAXI_AMT',
					width : 70,
					align : 'right',
					editable:true
				}],
				jsonReader : {
					id : "taxiId",
					repeatitems : false
				},
				multiselect : false,
				pager : $('#notselectedPager'),
				rowNum : '30',
				rowList : [ 30, 60, 90 ],
				sortname : 'id',
				sortorder : 'asc',
				viewrecords : true,
				width : 525,
				height : 360,
				gridview : true,
				caption : '出租车信息',
				editurl:getnotselectedEditUrl(),
				
				onSelectRow:function(id){
					if(notlastsel!=id){
						$("#notselectedStaff").jqGrid('saveRow',notlastsel,function(result){
							var obj = $.parseJSON(result.responseText);
							if(obj.msg!= "success"){
								alert(obj.msg);
							}
							notlastsel = null;
							reload();
						});
					}
					if(id){
						jQuery('#notselectedStaff').jqGrid('restoreRow',notlastsel);
						jQuery('#notselectedStaff').jqGrid('editRow',id,true,notpickDate);
						notlastsel=id;
					}
				}
			}).jqGrid('navGrid', '#notselectedPager', {
				edit : false,
				add : false,
				del : false,
				search:false
			}).jqGrid('navButtonAdd',"#notselectedPager",{
				caption:'保存',
				buttonicon:'ui-icon-disk',
				onClickButton:function(){
					$("#notselectedStaff").jqGrid('saveRow',notlastsel,function(result){
						var obj = $.parseJSON(result.responseText);
						if(obj.msg!= "success"){
							alert(obj.msg);
						}
						reload();
						notlastsel=null;
					});
				},
				position:"first"
			});
	$(":button").attr("class","Button");//.button();
	$("#notadd").click(function(){
			count=count-1;
			if(notlastsel){
				$("#notselectedStaff").jqGrid('saveRow',notlastsel,function(result){
					var obj = $.parseJSON(result.responseText);
					if(obj.msg!= "success"){
						alert(obj.msg);
					}
					reload();
				});
			}
			notlastsel=count;
			var datarow = {taxiId:'',expensesId:$("input[name='expensesId']").val(),taxiDate:'',taxiTime:'',taxiReason:'',taxiPath:'',taxiInvoiceno:'',taxiAmt:''}; 
			jQuery("#notselectedStaff").jqGrid('addRowData',count,datarow);
			jQuery("#notselectedStaff").jqGrid("editRow",""+count,true,notpickDate);
	});
	$("#notremove").click(function(){
		var id = notlastsel;
		if (id.length == 0) {
			alert("请选择记录");
			return;
		}
		$.post("TaxiInfo.do?act=delete",{	
			taxiId:id,oper:'oper'
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
});

var count=0;

function reload(expensesId){
	$("#selectedStaff").setGridParam({url:getSelectedUrl(expensesId)}).trigger('reloadGrid');
	$("#notselectedStaff").setGridParam({url:getnotselectedUrl(expensesId)}).trigger('reloadGrid');
}
function getnotselectedUrl(expensesId){
	var expensesId = $("input[name='expensesId']").val();
	return  'TaxiInfo.do?act=getSelectedStaff&expensesId='+ expensesId;
}
function getnotselectedEditUrl(){
	return 'TaxiInfo_UTF.do?act=update';
}
var notlastsel;
function notpickDate(id){
	var obj = $("#"+id+"_taxiDate");
	obj.click(function(){
		new Calendar().show($(this).context);
	});
}
function getSelectedUrl(expensesId){
	var expensesId = $("input[name='expensesId']").val();
	return  'TravelDetail.do?act=getSelectedStaff&expensesId='+ expensesId;
}
function getSelectedEditUrl(){
	return 'TravelDetail_UTF.do?act=update';
}
var lastsel;
function pickDate(id){
	var obj = $("#"+id+"_fromDate","#selectedStaff");
	obj.click(function(){
		new Calendar().show($(this).context);
	});
	$("#"+id+"_toDate","#selectedStaff").click(function(){
		new Calendar().show($(this).context);
	});
}
