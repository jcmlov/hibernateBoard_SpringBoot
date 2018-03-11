$(function() {
	$("#commentRegistBtn").click(addComment);
});

function addComment(e) {
	
	e.preventDefault();

	var queryString = $("#commentForm").serialize();
	
	$.ajax({
		type: "post",
		url: "/boardComment/ajax/boardCommentRegist",
		dataType: "json",
		data: queryString,
		error: resultError,
		success: resultSuccess});
}

function resultSuccess(data) {

	var totComments = Number($("#totComments").text()) + 1;
	var html = '';
	
	html += '<div class="col-sm-1">';
	html += 	'<div class="thumbnail">';
	html += 		'<img class="img-responsive user-photo" src="https://ssl.gstatic.com/accounts/ui/avatar_2x.png">';
	html += 	'</div>';
	html += '</div>';
	html += '<div class="col-sm-11">';
	html += 	'<div class="panel panel-default">';
	html += 		'<div class="panel-heading">';
	html += 			'<strong>' + data.register.userId + '</strong>';
	html += 			'<span class="text-muted">' + data.formattedRegistDate + '</span>';
	html += 		'</div>';
	html += 	'<div class="panel-body">' + data.comment + '</div>';
	html += '	</div>';
	html += '</div>';
	
	$(".comment").prepend(html);
	$("#totComments").text(totComments);
	
}

function resultError() {
	
}