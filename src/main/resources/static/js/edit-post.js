$(document).ready(function() {
	$("#deleteArticle").click(function() {
		if(confirm("Are you sure to delete this article?")){
			$.ajax({
				type: "GET",
				accept: 'text/plain',
				url: location.protocol + '//' + location.host + "/api/article/delete?id=" + article_id,
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
	$("#editArticle").click(function() {
		$(".newParagraph").attr("contentEditable", "true");
		
		$(".paragraphImage").on("click", function(e) {
			imageTag = this;
			$("#imageInput").click();
		})
		$(".paragraphImage").css("cursor","pointer");
		$(".paragraphImage").toggleClass("paragraphImageHover");
		$(".paragraphImage").attr('title', "click to change picture");
		$(".paragraphImage").each(function(i, element) {
			$(createCloseIcon()).insertBefore(element);
		});
		$(".newParagraph").each(function(i, element) {
			$(CreateNewCloseIcon()).insertBefore(element);
		});
		$(createCloseIcon()).insertBefore();
		$("figcaption").attr("contentEditable", "true");
		$("#paragraphTextArea").parent().removeAttr("hidden");
		$('.masonry').masonry('layout');
		$(this).parent().fadeOut(200);
	})
})