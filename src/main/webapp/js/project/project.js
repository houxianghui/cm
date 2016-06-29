$(function(){
	
	$("input[name*='Date']").focusin(function(){
		new Calendar().show(this);
	});
	$("input[name*='Manager']").attr("class","Textfield");
	$("input[name*='Date']").attr("class","Textfield").attr("size","8").attr("readOnly",true);
	$("input").attr("size","40");
	checkHide();
	checkScale();
	$(":input[name='projectClass']").change(function(){
		checkHide();
		checkScale();
	});

	$.get('VersionHis.do?act=getOpenVersion&versionId='+$("#versionId").val(),function(data){
		$(":input[name='versionId']").append(data);
	});
	$.get('ScaleDef.do?act=getScales&scaleId='+$("#scaleId").val(),function(data){
		$(":input[name='scaleId']").append(data);
	});
	$.get('ProductDef.do?act=getCheckbox&selected='+$("#selectedProducts").val(),function(data){
		$("#productIds").append(data);
	});
	$.get('ProjectMaintain.do?act=getSubSysOptions&selected='+$("#selected").val(),function(data){
		$("#subSys").append(data).css({"padding-left":"13px","padding-top":"5px","padding-bottom":"5px","width":455});
	});
});
function checkScale(){
	var type = $(":input[name='projectClass']").val();
	var match = false;
	$.each(["P","E","F","KH"],function(i,n){
		if(type == n){
			match= true;
		}
	});
	if(match){
		$("#scale").show();
	}else{
		$("#scale").hide();
	}
}
function checkHide(){
	var type = $(":input[name='projectClass']").val();
	var match = false;
	$.each(["S","A","MT","WP","KH"],function(i,n){
		if(type == n){
			match= true;
		}
	});
	if(match){
		$("#hide").show();
	}else{
		$("#hide").hide();
	}
	
}

function doCommit(){
	var s = prompt('请输入修改原因','');
	if(s == null || s==""){
		alert("请输入修改原因");
		return;
	}
	document.forms[0].reason.value=s;
	document.forms[0].submit();
}	