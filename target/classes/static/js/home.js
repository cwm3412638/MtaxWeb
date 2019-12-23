 $(function () {
	 /*
	 * 按钮事件
	 * */
	 //企业介绍
	 $('.btn_info').click(function(){
        window.location.href = "page/info.html";
	 })
	 
	 /*
	 * 获取省市区数据
	 * */
	var html = "";

	$("#input_city").append(html);
	$("#input_area").append(html);
	$.each(pdata,function(idx,item){
		if (parseInt(item.level) == 0) {
			html += "<option value="+item.code+" >"+ item.names +"</option> ";
		}
	});
	$("#input_province").append(html);

	$("#input_province").change(function(){
		if ($(this).val() == "") return;
		$("#input_city option").remove();
		$("#input_area option").remove();
		//var code = $(this).find("option:selected").attr("exid");
		var code = $(this).find("option:selected").val();
		code = code.substring(0,2);
		var html = "<option value=''>--请选择--</option>";
		$("#input_area option").append(html);
		$.each(pdata,function(idx,item){
			if (parseInt(item.level) == 1 && code == item.code.substring(0,2)) {
				html +="<option value="+item.code+" >"+ item.names +"</option> ";
			}
		});
		$("#input_city ").append(html);
	});

	$("#input_city").change(function(){
		if ($(this).val() == "") return;
		$("#input_area option").remove();
		var code = $(this).find("option:selected").val();
		code = code.substring(0,4);
		var html = "<option value=''>--请选择--</option>";
		$.each(pdata,function(idx,item){
			if (parseInt(item.level) == 2 && code == item.code.substring(0,4)) {
				html +="<option value="+item.code+" >"+ item.names +"</option> ";
			}
		});
		$("#input_area ").append(html);
	});
});