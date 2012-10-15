	function getPhoto(){
		navigator.camera.getPicture( convertPhoto, function(message) { 
			alert('Get photo failure');
		}, { quality: 50, destinationType: navigator.camera.DestinationType.FILE_URI } );
	}
	
	function convertPhoto(imageURI){
		var image = document.getElementById("img");
	    image.src = imageURI;
	    image.onload = function() { 
	    	var w = image.width, h = image.height;
	
	        cordova.exec(getConvertedData, function (e) {
	            alert('Error in process base64 converting' + e);
	        }, 'Base64Image', 'convert', [imageURI]);
	    };
	}
	
	function getConvertedData( data ){
		alert("Image converting to base64 have been succesful");
		document.getElementById("result").innerHTML = data;
	}
	