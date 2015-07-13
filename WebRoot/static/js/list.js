$(document).ready(function(){
	$("a").click(function() {
	 	event.preventDefault();//使a自带的方法失效
		var href = $(this).attr("href");
		$("#postform").attr("action", href).submit();
		return false;
	});
});

function checkall(){
	if($("#checkboxall").is(":checked")){
        $("input[type=checkbox]").attr("checked",true);
    }else{
        $("input[type=checkbox]").attr("checked",false);
    }
}

function deleteBatch(basePath){
	$("#mainForm").attr("action",basePath+"deletbatch.action");
	$("#mainForm").submit();
}