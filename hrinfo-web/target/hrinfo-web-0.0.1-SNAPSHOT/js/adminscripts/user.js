function checkUserName(userName) {
  var userRoleDid = $("#userRoleDid option:selected").val();
  
  if(userRoleDid == -1) {
	  alert('Please Select a Role');
  	   $('#userName').val('');
  } else {
  
	  $.getJSON("checkUserName.ajax?roleDid=" + userRoleDid + "&userName=" + userName, {
	            ajax_command:"checkUserName",
	            ajax_ieCacheFix:new Date().getTime()
	            }, function(json) {
	            	
	            if(json.userNameExistFlag == 'TRUE') {
	            	alert('User Name already taken!');
	            	$('#userName').val('');
	         	    $('#userName').focus();	    
	            }
	   });
 }
}
