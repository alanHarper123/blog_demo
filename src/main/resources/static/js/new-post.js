var imageTag = {};
var sortable = false;
function resetImage(){
	$("#masonry_image").attr("src", "images/imagesPlaceholder.png");
}
function createCloseIcon(){
	var colseIcon = document.createElement("img");
	
	colseIcon.setAttribute('src',"/images/closeIcon.png");
	colseIcon.className = "colseIcon";
	colseIcon.setAttribute('title', "click to remove picture");
	colseIcon.onclick = function(event){
		$(colseIcon.parentNode.parentNode.parentNode).fadeOut(300, function() {
			colseIcon.parentNode.parentNode.parentNode.parentNode.removeChild(colseIcon.parentNode.parentNode.parentNode);
		});
	}
	return colseIcon;
}
function CreateNewCloseIcon(){
	var newCloseIcon = document.createElement("i");
	newCloseIcon.setAttribute("class", "newCloseIcon");
	newCloseIcon.setAttribute('title', "click to remove paragraph");
	newCloseIcon.onclick = function(){
		$(newCloseIcon.parentNode).fadeOut(300,function(){
			newCloseIcon.parentNode.parentNode.removeChild(newCloseIcon.parentNode);
		});
	}
	return newCloseIcon;
}
$(document).ready(function() {
	
	var title = document.getElementById("title");
	$("li.date").html((new Date()).toDateString());
	var paragraphs = document.getElementById("paragraphs");
	var containerBricks = $('.masonry');
	title.addEventListener("keyup", function(e) {
		if((e.keyCode||e.which)===13){
			containerBricks.masonry('layout');
		}
		document.getElementById("masonry_title").innerHTML=document.getElementById("title").innerHTML.replace(/<div>/g, "<br>").replace(/<\/div>/g, "");
			
	}, false);
	document.getElementById("masonry_excerpt").addEventListener("keyup", function(e) {
		if((e.keyCode||e.which)===13){
			containerBricks.masonry('layout');
		}
	}, false);
	document.getElementById("addParagraph").onclick = function(event){
		if($("#paragraphTextArea").val()!=""){
			
			var paragraphDiv = document.createElement("div");
			paragraphDiv.setAttribute("class", "paragraphDiv");
			
			var newParagraph = document.createElement("p");
			newParagraph.contentEditable = !sortable;
			newParagraph.setAttribute("class", "newParagraph");
			newParagraph.innerHTML=$("#paragraphTextArea").val().replace(/\n/g, "<br>");
			
			
			paragraphDiv.appendChild(newParagraph);
			paragraphDiv.appendChild(CreateNewCloseIcon());
			
			$("#paragraphTextArea").val("");
			paragraphs.appendChild(paragraphDiv);
		}
	}
	
	document.getElementById("addImageBtn").onclick = function(event){
		$("#imageInput").click();
	}
	document.getElementById("imageInput").onclick = function(event){
		$(this).val("");
	}
	$("#imageInput").change(function() {
		readURL("imageTag",imageInput);
		
	});
	
	document.getElementById("masonry_image_placeholder").onclick = function(){
		$("#masonry_image").click();
	}
	document.getElementById("masonry_image").onclick = function(){
		document.getElementById("masonry_image_placeholder").textContent = "Click to change masonry Image";
		imageTag = $("#masonry_image")[0];
		$("#imageInput").click();
	}
	function readURL(imageTagTemp, input){
		if (input.files && input.files[0]) {
			console.log("wtf1");
            var reader = new FileReader();
            reader.onload = function (e) {
            	if(jQuery.isEmptyObject(window[imageTagTemp])){
            		console.log("wtf2");
            		console.log("success");
            		var paragraphDiv = document.createElement("div");
        			paragraphDiv.setAttribute("class", "paragraphDiv");
        			
					var newParagraph = document.createElement("div");
					var newfigure = document.createElement("figure");
					
					var newImageTag = document.createElement("img");
					newImageTag.setAttribute('src', e.target.result);
					newImageTag.className = "paragraphImage paragraphImageHover";
					newImageTag.setAttribute('title', "click to change picture");
					var figcaptionTag = document.createElement("figcaption");
					figcaptionTag.contentEditable = "true";
					figcaptionTag.style.textAlign = "center";
					
					newfigure.appendChild(createCloseIcon());
					newfigure.appendChild(newImageTag);
					newfigure.appendChild(figcaptionTag);
					
					newParagraph.appendChild(newfigure);
					paragraphDiv.appendChild(newParagraph);
					paragraphs.appendChild(paragraphDiv);
					figcaptionTag.focus();
					newImageTag.onclick = function(event){
						window[imageTagTemp] = newImageTag;
						$("#imageInput").click();
					}
            		
        		}else {
        			console.log("wtf3");
        			
        			window[imageTagTemp].setAttribute('src', "");
        			window[imageTagTemp].setAttribute('src', e.target.result);
        			if(window[imageTagTemp].getAttribute("id") == "masonry_image"){
        				setTimeout(function() {
        					containerBricks.masonry('layout');
        				}, 100);
        				
        			}
        			
			   		window[imageTagTemp] = {};
				}
            	
            };
            
            reader.readAsDataURL(input.files[0]);
 
        }else {
        	console.log("wtf");
        	window[imageTagTemp] = {};
		}

	}
	$("#sortable").on("click", function(e) {
		if(!sortable){
			$("#paragraphs").sortable();
			$("#paragraphs").sortable( "option", "disabled", false );
			$("#sortable").text("Disable Sortable Paragraphs");
			$(".newParagraph").attr("contentEditable", "false");
			$(".newParagraph").css("cursor","move");
			$(".paragraphImage").css("cursor","move");
			$(".paragraphImage").toggleClass("paragraphImageHover");
			$(".paragraphImage").css("pointer-events","none");
			sortable = true;
		}else{
			$("#paragraphs").sortable("disable");
			$(".newParagraph").attr("contentEditable", "true");
			$(".newParagraph").css("cursor","text");
			$("#sortable").text("Enable Sortable Paragraphs");
			$(".paragraphImage").css("pointer-events","auto");
			$(".paragraphImage").css("cursor","pointer");
			$(".paragraphImage").toggleClass("paragraphImageHover");
			sortable = false;
		}
		
	})
	document.getElementById("post").onclick = function(event){
		var paragraphs = [];
		
		var divTags = document.getElementById("paragraphs").childNodes;
		var length = divTags.length;
		var encoder = new TextEncoder();
		for(var i=1; i<length; i++){
			var pTags = divTags[i].childNodes[0];
			if(pTags.firstChild.tagName != "FIGURE"){
				paragraphs.push({text : pTags.innerHTML});
			}else {
				var tempFigure = pTags.firstChild.childNodes;
				if(tempFigure[2].textContent != ""){
					paragraphs.push({image : encoder.encode(tempFigure[1].getAttribute("src")), imageCaption : tempFigure[2].innerHTML})
				}else {
					paragraphs.push({image : encoder.encode(tempFigure[1].getAttribute("src"))})
				}
			}
			
		}
		if(paragraphs.length==0){
			alert("article can not have no paragraphs");
			return;
		}
		var article = {
				title: document.getElementById("masonry_title").innerHTML,
				paragraphs: paragraphs
		}
		var masonryImageSrc = document.getElementById("masonry_image").getAttribute("src");
		if(masonryImageSrc != "images/imagesPlaceholder.png"){
			
			article["image"] = encoder.encode(masonryImageSrc);
		}
		var excerpt = $("#masonry_excerpt").text();
		if(excerpt != ""){
			article["excerpt"] = excerpt;
		}
		if( 'article_id' in window){
			article["id"] = article_id;
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

