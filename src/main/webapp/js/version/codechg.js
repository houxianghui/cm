$(function(){
	$('#grid').jqGrid({
		url:'CodeChg.do?act=getlist&versionId='+$(":input[name=versionId]").val(),
		datatype:'json',
		mtype:'GET',
		colNames:['修改类型','程序名'],
		colModel:[
		          {name:'chgType',index:'CHG_TYPE'},
		          {name:'fileName',index:'FILE_NAME'}
		          ],
		rowNum:30,
		rowList:[10,20,30],
		viewrecords:true,
		gridview : true,
		caption:'程序变更列表',
		jsonReader: { repeatitems : false, id: "0" }
	});
	$("#grid").jqGrid('navGrid','#pager',{edit:false,add:false,del:false});
});