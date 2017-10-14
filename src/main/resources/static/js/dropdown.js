function showCobiINew() {
	showCobiI("candidates/cobiInterviewers?valid=true");
}
function showCobiIEdit() {
	showCobiI("cobiInterviewers?valid=false"); 
}
function showCobiI(link) {
	var platform = $("#platform").val();
	$('#cobiInterviewer').html(""); 
	$.get(link + "&mediaType=json&platformName=" + platform, {
		ajax : 'true',
		 async: false,
	}, function(data) {
	 	var html = '<option value="">Select one</option>';
		var len = data.length;
		for (var i = 0; i < len; i++) {
			html += '<option value="' + data[i].id + '">' + data[i].email
					+ '</option>';
		}
		$('#cobiInterviewer').html(html);
	}); 
}
function showCobiIFirstTime(email) {
	var platform = $("#platform").val();
	$('#cobiInterviewer').html(""); 
	$.get("cobiInterviewers?valid=false&mediaType=json&platformName=" + platform, {
		ajax : 'true',
		 async: false,
	}, function(data) {
	 	var html = '<option value="">Select one</option>';
		var len = data.length;
		for (var i = 0; i < len; i++) {
			html += '<option value="' + data[i].id + '">' + data[i].email
					+ '</option>';
		}
		$('#cobiInterviewer').html(html);  
	 	$('#cobiInterviewer').val(email).attr("selected", "selected");
		 
	}); 
}