
	function tryToLogin() {
		
				var url = "http://localhost:8080/login?" + "login=" + $("#loginInput").val()+ "&" + "password=" + $("#passInput").val();
				$.ajax({
                    url: url,
                    type: 'POST',
                   success: function(data) {
				 
                   }
                });
				} 
