
$(document).ready(function() {
	$.ajax({
		type: "POST",
		contentType: "application/json",
		accept: 'text/plain',
		url: window.location + "/api/article/save",
		data: JSON.stringify(article,function(k, v) {
		    if (v instanceof  Uint8Array) {
		        return Array.apply([], v);
		    }
		    return v;
		}),
		dataType: 'text',
		success: function(result){
			alert(result);
		},
		error: function(e){
			alert("Error!");
			console.log("ERROR: ",e);
		}
	});
})
