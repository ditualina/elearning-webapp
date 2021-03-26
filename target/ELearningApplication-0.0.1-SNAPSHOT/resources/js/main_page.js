function toggle(id) {
	ul = "ul_" + id;
	img = "img_" + id;
	ulElement = document.getElementById(ul);
	imgElement = document.getElementById(img);
	if (ulElement) {
		if (ulElement.className == 'closed') {
			ulElement.className = "open";
			imgElement.src = "resources/images/opened.gif";
		} else {
			ulElement.className = "closed";
			imgElement.src = "resources/images/closed.gif";
		}
	}
}

var clicks = 0;

function onClicks(id) {
	clicks += 1;
	$.ajax({
		type : "GET",
		url : "saveViewNumber.html",
		data : {
			"clicks" : clicks,
			"id" : id
		}
	});
}

function countViews(id) {
	clicks += 1;
	$.ajax({
		type : "GET",
		url : "saveViewNumber.html",
		data : {
			"clicks" : clicks,
			"id" : id
		},
		success: function(response){
			alert(response);
		}
	});
}

function onDownload(id) {
	$.ajax({
		type : "GET",
		url : "saveDownloads.html",
		data : {
			"id" : id
		}
	});
}

function onUploadFile(id) {
	$.ajax({
		type : "GET",
		url : "upload.html",
		data : {
			"id" : id
		}
	});
}
