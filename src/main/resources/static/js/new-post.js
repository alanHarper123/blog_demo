
$(document).ready(function() {
	var title = document.getElementById("title");
	title.contentEditable = "true";
	$("li.date").html((new Date()).toDateString());
	var paragraphs = document.getElementById("paragraphs");
	document.getElementById("addTitleBtn").onclick = function(event){
		title.innerHTML = $("#titleTextArea").val();
		$("#titleTextArea").hide();
		$("#addTitleBtn").hide();
	};
	document.getElementById("addParagraph").onclick = function(event){
		var newParagraph = document.createElement("p");
		newParagraph.contentEditable = "true";
		newParagraph.innerHTML=$("#paragraphTextArea").val().replace(/\n/g, "<br>");
		$("#paragraphTextArea").val("");
		paragraphs.appendChild(newParagraph);
	}
	var imageTag = {};
	document.getElementById("addImageBtn").onclick = function(event){
		$("#imageInput").click();
	}
	var imageInput = document.getElementById("imageInput");
	$("#imageInput").change(function() {
		readURL("imageTag",imageInput);
	});
	
	function readURL(imageTagTemp, input){
		if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
            	if(jQuery.isEmptyObject(window[imageTagTemp])){
            		console.log("success");
					var newParagraph = document.createElement("p");
					var newfigure = document.createElement("figure");
					var newImageTag = document.createElement("img");
					newImageTag.setAttribute('src', e.target.result);
					var figcaptionTag = document.createElement("figcaption");
					figcaptionTag.contentEditable = "true";
					figcaptionTag.style.textAlign = "center";
					newfigure.appendChild(newImageTag);
					newfigure.appendChild(figcaptionTag);
					
					newParagraph.appendChild(newfigure);
					paragraphs.appendChild(newParagraph);
					figcaptionTag.focus();
					newImageTag.onclick = function(event){
						window[imageTagTemp] = newImageTag;
						$("#imageInput").click();
					}
            		
        		}else {
        			window[imageTagTemp].setAttribute('src', "");
        			window[imageTagTemp].setAttribute('src', e.target.result);
				}
            	
            };
            
            reader.readAsDataURL(input.files[0]);
        }
		window[imageTagTemp] = {};
	}

	document.getElementById("post").onclick = function(event){
		var paragraphs = [];
		
		var pTags = document.getElementById("paragraphs").childNodes;
		var length = pTags.length;
		var encoder = new TextEncoder();
		for(var i=1; i<length; i++){
			if(pTags[i].childElementCount == 0){
				paragraphs.push({text : pTags[i].innerHTML});
			}else {
				var tempFigure = pTags[i].firstChild.childNodes;
				paragraphs.push({image : encoder.encode(tempFigure[0].getAttribute("src")), imageCaption : tempFigure[1].textContent})
			}
		}

		var article = {
				title: document.getElementById("title").innerHTML,
				paragraphs: paragraphs
		}

		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$(document).ajaxSend(function(e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});
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
	}
})

