/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function loadCompanySpecificData(companyDid,loadEvent) {
	var departmentDid = $('#departments select').val();
    var employeeDid = $('#employeeDid').val();
        
    if(companyDid == -1) {
        $('#departments').attr("disabled","disabled")
    } else {    	
        $('#departments').removeAttr("disabled");
        $.getJSON("loadCompanySpecificData.ajax?companyDid=" + companyDid + "&departmentDid=" + departmentDid + "&employeeDid" + employeeDid + "&loadEvent=" + loadEvent , {
            ajax_command:"loadCompanySpecificData",
            ajax_ieCacheFix:new Date().getTime()
            }, function(json) {

            $('#departments select option').remove();

            if (json.departmentJsonArray != null && json.departmentJsonArray.length > 0) {
                for (x = 0; x < json.departmentJsonArray.length; x++) {
                    $("#departments select").append($('<option></option>').val(json.departmentJsonArray[x].fieldDID).html(json.departmentJsonArray[x].fieldLabel));
                }
            }

            if(json.employeeNumber != null) {
                $('#empNo').val(json.employeeNumber);
            }
            
            if(json.companyEpfNumber != null) {
                $('#companyEPFNumber').val(json.companyEpfNumber);
            }

        });
    }
}

function loadDepartments(companyDid) {
	var departmentDid = $('#departments select').val();
        
    if(companyDid == -1) {
        $('#departments').attr("disabled","disabled")
    } else {    	
        $('#departments').removeAttr("disabled");
        $.getJSON("loadDepartments.ajax?companyDid=" + companyDid + "&departmentDid=" + departmentDid , {
            ajax_command:"loadDepartments",
            ajax_ieCacheFix:new Date().getTime()
            }, function(json) {

            $('#departments select option').remove();

            if (json.departmentJsonArray != null && json.departmentJsonArray.length > 0) {
                for (x = 0; x < json.departmentJsonArray.length; x++) {
                    $("#departments select").append($('<option></option>').val(json.departmentJsonArray[x].fieldDID).html(json.departmentJsonArray[x].fieldLabel));
                }
            }

        });
    }
}

function addChildInfoRow(tableID) {
	
	var x = validateChildInfo($('#childName').val(),$("input[name='childGender']:checked + label").text(),dojo.widget.byId("childDateOfBirth").inputNode.value);
	
	if(x != null && x == 0) {
		var table = document.getElementById(tableID);
		
		if($('#rowId').val().length > 0) {
			var editedIndex = $('#rowId').val();
			var tblcells = table.rows[editedIndex].cells;
			tblcells[0].childNodes[0].value = $('#childName').val();
			tblcells[1].childNodes[0].value = $("input[name='childGender']:checked + label").text();
			tblcells[2].childNodes[0].value = (dojo.widget.byId("childDateOfBirth").inputNode.value).substring(0,10);
			$('#rowId').val('');
		} else {
			var rowCount = (table.rows != null) ? table.rows.length : 0;
			var row = table.insertRow(rowCount);
			var counts=rowCount;
			
			
			var cell1 = row.insertCell(0);
			var chName = document.createElement("input");
			chName.type = "text";
			chName.readOnly = true;
			chName.style.border = 0;
			chName.style.color= "#FF8000";
			chName.name="employeeDTO.children["+counts+"].childName";
			chName.value = $('#childName').val();
			cell1.appendChild(chName);
	
			var cell2 = row.insertCell(1);
			var chGender = document.createElement("input");
			chGender.type = "text";
			chGender.readOnly = true;
			chGender.style.border = 0;
			chGender.style.color= "#FF8000";
			chGender.name="employeeDTO.children["+counts+"].childGender";
			chGender.value = $("input[name='childGender']:checked + label").text();
			cell2.appendChild(chGender);
		    
		    
			cell2.appendChild(chGender);
	
			var cell3 = row.insertCell(2);
			var chDOB = document.createElement("input");
			chDOB.type = "text";
			chDOB.readOnly = true;
			chDOB.style.border = 0;
			chDOB.style.color= "#FF8000";
			chDOB.name="employeeDTO.children["+counts+"].childDateOfBirth";
			chDOB.value = (dojo.widget.byId("childDateOfBirth").inputNode.value).substring(0,10);
			cell3.appendChild(chDOB);
	
			var cell4 = row.insertCell(3);
			var edit = document.createElement("a");
			edit.href = "javascript:editChildInfoRow('childrenTable',"+counts+");";
			edit.title = "Edit Child Information";
	
			var img = document.createElement("img");
			img.src = "../images/edit.gif";
			img.border = 0;
			edit.appendChild(img);
			//var aElemTN = document.createTextNode('Edit');
	        
			cell4.appendChild(edit);
		}
		
		$('#childName').val('');
		$("input[name='childGender']").attr("checked", false);
		dojo.widget.byId("childDateOfBirth").inputNode.value = "";	
	}

}

function editChildInfoRow(tableID,rowIndex) {

	var table = document.getElementById(tableID);
	var tblcells = table.rows[rowIndex].cells;
	$('#rowId').val(rowIndex);
	$('#childName').val(tblcells[0].childNodes[0].value);
	if(tblcells[1].childNodes[0].value == 'Male') {
		$("input[name='childGender'][value='M']").attr("checked", "checked");
	} else if(tblcells[1].childNodes[0].value == 'Female') {
		$("input[name='childGender'][value='F']").attr("checked", "checked");
	}
	dojo.widget.byId("childDateOfBirth").inputNode.value = tblcells[2].childNodes[0].value;
	
	//table.deleteRow(rowIndex);
	
}

function validateChildInfo(name,gender,dob) {
	var retVal = 0; // default valid
	if(name.length == 0) {$('#childName_errorText').val("Enter Name");$('#div_childName_Error').show();retVal=1;}else {$('#childName_errorText').val('');$('#div_childName_Error').hide();}
	if(gender.length == 0) {$('#childGender_errorText').val("Enter Gender");$('#div_childGender_Error').show();retVal=1;}else {$('#childGender_errorText').val('');$('#div_childGender_Error').hide();}
	if(dob.length == 0) {$('#childDateOfBirth_errorText').val("Enter Date of Birth");$('#div_childDateOfBirth_Error').show();retVal=1;}else{$('#childDateOfBirth_errorText').val('');$('#div_childDateOfBirth_Error').hide()}
	
	return retVal;
}


////////
function addContactInfoRow(tableID) {
	
	var x = validateContactInfo($('#contactName').val(),$('#contactNumber').val(),$('#relationship').val());
	
	if(x != null && x == 0) {
		var table = document.getElementById(tableID);
		
		if($('#rowId_ice').val().length > 0) {
			var editedIndex = $('#rowId_ice').val();
			var tblcells = table.rows[editedIndex].cells;
			tblcells[0].childNodes[0].value = $('#contactName').val();
			tblcells[1].childNodes[0].value = $('#contactNumber').val();
			tblcells[2].childNodes[0].value = $('#relationship').val();
			$('#rowId_ice').val('');
		} else {
			var rowCount = (table.rows != null) ? table.rows.length : 0;
			var row = table.insertRow(rowCount);
			var counts=rowCount;
			
			var cell1 = row.insertCell(0);
			var conName = document.createElement("input");
			conName.type = "text";
			conName.readOnly = true;
			conName.style.border = 0;
			conName.style.color= "#FF8000";
			conName.name="employeeDTO.emergencyContacts["+counts+"].contactName";
			conName.value = $('#contactName').val();
			cell1.appendChild(conName);
	
			var cell2 = row.insertCell(1);
			var conNumber = document.createElement("input");
			conNumber.type = "text";
			conNumber.readOnly = true;
			conNumber.style.border = 0;
			conNumber.style.color= "#FF8000";
			conNumber.name="employeeDTO.emergencyContacts["+counts+"].contactNumber";
			conNumber.value = $('#contactNumber').val();
			cell2.appendChild(conNumber);
	
			var cell3 = row.insertCell(2);
			var conRelation = document.createElement("input");
			conRelation.type = "text";
			conRelation.readOnly = true;
			conRelation.style.border = 0;
			conRelation.style.color= "#FF8000";
			conRelation.name="employeeDTO.emergencyContacts["+counts+"].relationship";
			conRelation.value = $('#relationship').val();
			cell3.appendChild(conRelation);
	
			var cell4 = row.insertCell(3);
			var edit = document.createElement("a");
			edit.href = "javascript:editContactInfoRow('contactTable',"+counts+");";
			edit.title = "Edit Contact Information";
	
			var img = document.createElement("img");
			img.src = "../images/edit.gif";
			img.border = 0;
			edit.appendChild(img);
			//var aElemTN = document.createTextNode('Edit');
	        
			cell4.appendChild(edit);
		}
		
		$('#contactNumber').val('');
		$('#contactName').val('');
		$('#relationship').val('');
	}

}

function editContactInfoRow(tableID,rowIndex) {

	var table = document.getElementById(tableID);
	var tblcells = table.rows[rowIndex].cells;
	$('#rowId_ice').val(rowIndex);
	$('#contactName').val(tblcells[0].childNodes[0].value);
	$('#contactNumber').val(tblcells[1].childNodes[0].value);
	$('#relationship').val(tblcells[2].childNodes[0].value);
	
	//table.deleteRow(rowIndex);
	
}

function validateContactInfo(name,number,relationship) {
	var retVal = 0; // default valid
	if(name.length == 0) {$('#contactName_errorText').val("Enter Contact Name");$('#div_contactName_Error').show();retVal=1;}else {$('#contactName_errorText').val('');$('#div_contactName_Error').hide();}
	if(number.length == 0) {
		$('#contactNumber_errorText').val("Enter Contact Number");
		$('#div_contactNumber_Error').show();
		retVal=1;
	} else {
		if(!isNumeric(number)) {
		  $('#contactNumber_errorText').val("Enter a numeric value");
		  $('#contactNumber').val('');
		  $('#div_contactNumber_Error').show();
		  retVal=1;
		} if(number.length < 10) {
			  
			  $('#contactNumber_errorText').val("Enter a 10 digit value");
			  $('#contactNumber').val('');
			  $('#div_contactNumber_Error').show();
			  retVal=1;
		} else {
			$('#contactNumber_errorText').val('');
			$('#div_contactNumber_Error').hide();
		}
	}
	if(relationship.length == 0) {$('#relationship_errorText').val("Enter Relationship");$('#div_relationship_Error').show();retVal=1;}else{$('#relationship_errorText').val('');$('#div_relationship_Error').hide()}
	
	return retVal;
}

/////

function addAllowanceRow(tableID) {	
	var x = validateAllowance($("#allowanceType option:selected").val(),$('#amount').val());
	
	if(x != null && x == 0) {
		var table = document.getElementById(tableID);
		
		if($('#rowId_allowance').val().length > 0) {
			var editedIndex = $('#rowId_allowance').val();
			var tblcells = table.rows[editedIndex].cells;
			tblcells[0].childNodes[0].value = $("#allowance_uniqueDid").val();
			tblcells[1].childNodes[0].value = $("#allowanceType option:selected").val();
			tblcells[2].childNodes[0].value = $("#allowanceType option:selected").text();
			tblcells[3].childNodes[0].value = $('#amount').val();
			$('#rowId_allowance').val('');
		} else {
			var rowCount = (table.rows != null) ? table.rows.length : 0;
			var row = table.insertRow(rowCount);
			var counts=rowCount;
			
			var cell0 = row.insertCell(0);
			var empAllowanceDid = document.createElement("input");
			empAllowanceDid.type = "hidden";
			empAllowanceDid.name="employeeDTO.allowances["+counts+"].employeeAllowanceDid";
			empAllowanceDid.value = $("#allowance_uniqueDid").val();
			cell0.appendChild(empAllowanceDid)
			
			var cell1 = row.insertCell(1);
			var allowanceTypeDid = document.createElement("input");
			allowanceTypeDid.type = "hidden";
			allowanceTypeDid.name="employeeDTO.allowances["+counts+"].allowanceTypeDid";
			allowanceTypeDid.value = $("#allowanceType option:selected").val();
			cell1.appendChild(allowanceTypeDid);
	
			var cell2 = row.insertCell(2);
			var allowanceTypeName = document.createElement("input");
			allowanceTypeName.type = "text";
			allowanceTypeName.readOnly = true;
			allowanceTypeName.style.border = 0;
			allowanceTypeName.style.color= "#FF8000";
			allowanceTypeName.name="employeeDTO.allowances["+counts+"].allowanceType";
			allowanceTypeName.value = $("#allowanceType option:selected").text();
			cell2.appendChild(allowanceTypeName);
					    
			cell2.appendChild(allowanceTypeName);
	
			var cell3 = row.insertCell(3);
			var amount = document.createElement("input");
			amount.type = "text";
			amount.readOnly = true;
			amount.style.border = 0;
			amount.style.color= "#FF8000";
			amount.name="employeeDTO.allowances["+counts+"].amount";
			amount.value = $('#amount').val();
			cell3.appendChild(amount);
	
			var cell4 = row.insertCell(4);
			var edit = document.createElement("a");
			edit.href = "javascript:editAllowanceRow('allowanceTable',"+counts+");";
			edit.title = "Edit Allowance";
	
			var img = document.createElement("img");
			img.src = "../images/edit.gif";
			img.border = 0;
			edit.appendChild(img);
	        
			cell4.appendChild(edit);
		}
		
		$("#allowance_uniqueDid").val('');
		$("#allowanceType").val("-1");
		$('#amount').val('');
	}

}

function editAllowanceRow(tableID,rowIndex) {

	var table = document.getElementById(tableID);
	var tblcells = table.rows[rowIndex].cells;
	$('#rowId_allowance').val(rowIndex);
	$('#allowance_uniqueDid').val(tblcells[0].childNodes[0].value);
	$('#allowanceType').val(tblcells[1].childNodes[0].value);
	$('#amount').val(tblcells[3].childNodes[0].value);	
}

function validateAllowance(allowtype,amt) {
	var retVal = 0; // default valid
	if(allowtype == "-1") {$('#allowance_errorText').val("Enter Allowance Type");$('#div_allowance_Error').show();retVal=1;}else {$('#allowance_errorText').val('');$('#div_allowance_Error').hide();}
	if(amt.length == 0) {
		$('#allowanceAmt_errorText').val("Enter Amount");
		$('#div_allowanceamt_Error').show();
		retVal=1;
	}else {
		if(!isNumeric(amt)) {
			  $('#allowanceAmt_errorText').val("Enter a numeric value");
			  $('#amount').val('');
			  $('#div_allowanceamt_Error').show();
			  retVal=1;
		} else {
			  $('#allowanceAmt_errorText').val('');
			  $('#div_allowanceamt_Error').hide();
		}
	}
	
	return retVal;
}

////

function activateSubPannels(selectedValue) {
	if(selectedValue == 'S') {
		$('#spouseName').val('');
		$('#spouseName').attr("disabled","disabled");
		$("input[name='employeeDTO.spouseGender']").attr("checked", false);
		dojo.widget.byId("spouseDateOfBirth").inputNode.value = "";	
		
		$('#childName').val('');
		$('#childName').attr("disabled","disabled");
		$("input[name='childGender']").attr("checked", false);
		dojo.widget.byId("childDateOfBirth").inputNode.value = "";	
		
		$('#div_spousename_Error').hide();
		$('#div_spousegender_Error').hide();
		$('#div_spousedob_Error1').hide();
		$('#div_spousedob_Error2').hide();
		
		var table = document.getElementById("childrenTable");
		while(table.hasChildNodes())
		{
			table.removeChild(table.firstChild);

		}
		
		$('#spouseInfo').attr("disabled","disabled");
		$('#childrenInfo').attr("disabled","disabled");
	}else if(selectedValue == 'M') {			
		$('#spouseInfo').removeAttr("disabled");
		$('#childrenInfo').removeAttr("disabled");
				
		$('#spouseName').removeAttr("disabled","disabled");
		$('#childName').removeAttr("disabled","disabled");

	}
}


//check usage
function save()
{
    document.forms[0].action = "SaveEmployeeDetails.do";
    document.forms[0].submit();
}


function reset()
{
    $.getJSON("resetEmployeeAdd.ajax", {
        ajax_command:"resetEmployeeAdd",
        ajax_ieCacheFix:new Date().getTime()
        }, function(json) {

        });


    $('#fName').val('');
    $('#genderM').removeAttr('checked');
    $('#genderF').removeAttr('checked');
    $('#mName').val('');
    $('#nicNo').val('');
    $('#lName').val('');
    $('#passNo').val('');
    $('#dateOfBirth').val('');
    $('#drvLicenseNo').val('');
    $('#company select').val('-1');
    $('#epfNo').val('');
    $('#departments select').val('-1');
    $('#departments').attr("disabled","disabled")
    $('#dateJoined').val('');
    $('#designation').val('');
    $('#dueConfDate').val('');
    $('#empNo').val('');
    $('#confDate').val('');
    $('#contracttype select').val('-1');
    $('#otherCtype').val('');
    $('#addLine1').val('');
    $('#hPhone').val('');
    $('#addLine2').val('');
    $('#mNumber').val('');
    $('#addLine3').val('');
    $('#email').val('');
    $('#mStatusM').removeAttr('checked');
    $('#mStatusS').removeAttr('checked');
    $('#spouseName').val('');
    $('#sGenderM').removeAttr('checked');
    $('#sGenderF').removeAttr('checked');
    $('#spouseDOB').val('');
    $('#childName1').val('');
    $('#c1GenderM').removeAttr('checked');
    $('#c1GenderF').removeAttr('checked');
    $('#child1DOB').val('');
    $('#childName2').val('');
    $('#c2GenderM').removeAttr('checked');
    $('#c2GenderF').removeAttr('checked');
    $('#child2DOB').val('');
    $('#childName3').val('');
    $('#c3GenderM').removeAttr('checked');
    $('#c3GenderF').removeAttr('checked');
    $('#child3DOB').val('');
    $('#childName4').val('');
    $('#c4GenderM').removeAttr('checked');
    $('#c4GenderF').removeAttr('checked');
    $('#child4DOB').val('');
    $('#childName5').val('');
    $('#c5GenderM').removeAttr('checked');
    $('#c5GenderF').removeAttr('checked');
    $('#child5DOB').val('');
    $('#iceName1').val('');
    $('#iceRel1').val('');
    $('#iceContactNo1').val('');
    $('#iceName2').val('');
    $('#iceRel2').val('');
    $('#iceContactNo2').val('');
    $('#iceName3').val('');
    $('#iceRel3').val('');
    $('#iceContactNo3').val('');
}

function showViewTab()
{
    document.forms[0].action = "LoadEmployeeSearchPage.do";
    document.forms[0].submit();
}

function checkEPFNumber() {
  var companyDid = $('#company select').val();
  var number = $('#epfNumbeer').val();
  
  if(companyDid == -1) {
	  alert('Please Select Company');
  	   $('#epfNumbeer').val('');
  } else {
  
	  $.getJSON("checkEPFNumber.ajax?number=" + number + "&companyDid=" + companyDid, {
	            ajax_command:"checkEPFNumber",
	            ajax_ieCacheFix:new Date().getTime()
	            }, function(json) {
	            	
	            if(json.epfNoExistFlag == 'TRUE') {
	            	alert('EPF Number Already exists !');
	            	$('#epfNumbeer').val('');
	         	    $('#epfNumbeer').focus();	    
	            }
	   });
 }
}

function checkNICNumber() {
	  var number = $('#nicNo').val();

	  $.getJSON("checkNICNumber.ajax?number=" + number, {
		            ajax_command:"checkNICNumber",
		            ajax_ieCacheFix:new Date().getTime()
		            }, function(json) {
		            	
		            if(json.nicNoExistFlag == 'TRUE') {
		            	alert('NIC Number Already exists !');
		            	$('#nicNo').val('');
		         	    $('#nicNo').focus();	    
		            }
	});
	  
}
	
function validateBasicSal() 
{
	if(!isNumeric($('#basicSalary').val())) {
		$('#basicSalary').val('0');
	} else {
		if($('#basicSalary').val().split(".") == null || $('#basicSalary').val().split(".")[0].length > 8) {
			$('#basicSalary').val('0');
			document.getElementById('div_bsal_Error').style.visibility = "visible";
		    document.getElementById('div_bsal_Error').style.display = "block";
		} else {
			document.getElementById('div_bsal_Error').style.visibility = "hidden";
		    document.getElementById('div_bsal_Error').style.display = "none";	
		}
	}
}	

function showOtherContrType(val) 
{
	if(val == '104') {
		document.getElementById("otherContractType").style.visibility = "visible";
	    document.getElementById("otherContractType").style.display = "block";    
	} else {
		document.getElementById("otherContractType").style.visibility = "hidden";
	    document.getElementById("otherContractType").style.display = "none";   
	}
}