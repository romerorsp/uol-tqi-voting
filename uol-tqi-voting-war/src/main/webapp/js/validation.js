$(document).ready(function(){
	$(".votingSubmit").click(function(){
		var list = $("table.options").find("input[type=radio]");
		for(var i = 0; i < list.length; i++){
			if(list[i].checked){
				return true;
			}
		}
		__growl.removeAll();	
		__growl.show([{summary:'Aviso', detail: 'Você precisa marcar uma opção', severity: 0}]);
		event.stopPropagation();
		return false;
//		$(document.getElementById($(".votingSubmit").attr("id")).form)
	});
});