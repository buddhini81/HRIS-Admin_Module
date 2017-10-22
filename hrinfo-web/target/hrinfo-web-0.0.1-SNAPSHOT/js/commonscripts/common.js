/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function showToolTip(e, msg)
{
    e.title = msg;
}

//check usage
function isNumeric(str) {
	
	var newStr = str.replace(/\./g,"");
	var numbers = /^[0-9]+$/; 
	return (newStr.match(numbers) == null) ? false : true;
}


function numericFilter(txb) {
	//txb.value = txb.value.replace(/[^\0-9]/ig, "");
	var isNumeric = (/^[0-9]+$/.test(txb.value));
	if(!isNumeric) {txb.value = "";}
}

function showPageErrorPopup(pgStatus) {
	if(pgStatus != null && pgStatus == "3") {
    	alert("The Page Has Errors. Please check");
    }else if(pgStatus != null && pgStatus == "5") {
    	alert("An internal error occured. Please try again later");
    }
}

function showPage(url)
{
    document.forms[0].action = url;
    document.forms[0].submit();
}

function showPage1(url)
{
	window.location.href=url;
}

function goToPage(url, paramName, paramVal)
{
	window.location.href=url+"?"+paramName+"="+paramVal;
}

function showHideComments(hiddenFieldId,divId,linkId, imgId) {
	var div = '#'+divId;
	var hiddenField = '#'+hiddenFieldId;
	var newText = $(div).text().replace(/,/gi,' ').replace(/:/gi,' ');
	
	
	if(document.getElementById(divId).style.visibility == "hidden") {
		document.getElementById(divId).style.visibility = "visible";
	    document.getElementById(divId).style.display = "block";
	    document.getElementById(imgId).src="../images/close_comment.jpg";
	    document.getElementById(linkId).title = "Close comment";
	} else if(document.getElementById(divId).style.visibility == "visible") {
		document.getElementById(divId).style.visibility = "hidden";
	    document.getElementById(divId).style.display = "block";
	    document.getElementById(imgId).src="../images/open_comment.jpg";
	    document.getElementById(linkId).title = "Add comment";
		
		if(newText != null && newText != '') {
			if($(hiddenField).val() != null && $(hiddenField).val() != '') {
				var arr = $(hiddenField).val().split(",");
				for (var i=0;i<arr.length;i++)
				{ 
					if(arr[i].split(':')[0] == divId)
					{
						arr[i] = divId+':'+newText;
						$(hiddenField).val(arr.toString());
						break;
					}else{
						$(hiddenField).val(arr.toString()+','+divId+':'+newText);
					}
				}

			} else {
				$(hiddenField).val(divId+':'+newText);
			}
		}

	}
}

function popup_large(url)
{
    var width  = 500;
    var height = 400;
    var left   = (screen.width  - width)/2;
    var top    = (screen.height - height)/2;
    var params = 'width='+width+', height='+height;
    params += ', top='+top+', left='+left;
    params += ', directories=no';
    params += ', location=no';
    params += ', menubar=no';
    params += ', resizable=no';
    params += ', scrollbars=auto';
    params += ', status=no';
    params += ', toolbar=no';

	window.open(url, "myNewWin",params);
    //var a = window.setTimeout("document.forms['packForm'].action='/ebfullfillment/packages/loadItemsFromSupplierOrder.do';document.forms['packForm'].submit();", 150);
    //return false;
}

function popup_small(url)
{
    var width  = 300;
    var height = 150;
    var left   = (screen.width  - width)/2;
    var top    = (screen.height - height)/2;
    var params = 'width='+width+', height='+height;
    params += ', top='+top+', left='+left;
    params += ', directories=no';
    params += ', location=no';
    params += ', menubar=no';
    params += ', resizable=no';
    params += ', scrollbars=auto';
    params += ', status=no';
    params += ', toolbar=no';

	window.open(url, "myNewWin",params);
    //var a = window.setTimeout("document.forms['packForm'].action='/ebfullfillment/packages/loadItemsFromSupplierOrder.do';document.forms['packForm'].submit();", 150);
    //return false;
}

function deleteRow(url) {
	var response = confirm('Are you sure you want to delete this record?');
	if (response) {
		 window.location.href=url;
	}
}

function clearField(field) {
	if(field.value == 0) {
		field.value = '';
	}
}

