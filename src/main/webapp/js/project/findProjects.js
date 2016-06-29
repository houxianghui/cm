$(function(){
	$("input[name='projectName']").autocomplete({
		
		source:"ProjectMaintain.do?act=findProjects",
		minLength:0,
		autoFill:true,
		focus:function(event,ui){
			$("input[name='projectName']").val(ui.item.projectId+"-"+ui.item.projectName);
			return false;
		},
		select:function(event,ui){
			$("input[name='projectName']").val(ui.item.projectId+"-"+ui.item.projectName);
			$("input[name='projectId']").val(ui.item.projectId);
			return false;
		}
		
	}).data( "autocomplete" )._renderItem = function( ul, item ) {
		return $( "<li>" )
		.data( "item.autocomplete", item )
		.append( "<a>" + item.projectId+"-"+item.projectName +"</a>" )
		.appendTo( ul );
	};
});