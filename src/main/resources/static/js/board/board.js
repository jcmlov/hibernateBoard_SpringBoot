$(function() {
	$("#commentRegistBtn").click(addComment);
});

function addComment(e) {
	
	e.preventDefault();

	var queryString = $("#commentForm").serialize();
	console.log("queryString : " + queryString);
	
	$.ajax({
		type: "post",
		url: "/boardComment/ajax/boardCommentRegist",
		dataType: "json",
		data: queryString,
		error: resultError,
		success: resultSuccess});
}

function resultSuccess(data) {
	console.log(data);
}

function resultError() {
	
}