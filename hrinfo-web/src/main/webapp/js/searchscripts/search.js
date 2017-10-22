function loadSearchByDepartments(companyDid) {
	var departmentDid = $('#searchbydepartments select').val();
        
    if(companyDid == -1) {
        $('#searchbydepartments').attr("disabled","disabled")
    } else {    	
        $('#searchbydepartments').removeAttr("disabled");
        $.getJSON("loadDepartments.ajax?companyDid=" + companyDid + "&departmentDid=" + departmentDid , {
            ajax_command:"loadDepartments",
            ajax_ieCacheFix:new Date().getTime()
            }, function(json) {

            $('#searchbydepartments select option').remove();

            if (json.departmentJsonArray != null && json.departmentJsonArray.length > 0) {
                for (x = 0; x < json.departmentJsonArray.length; x++) {
                    $("#searchbydepartments select").append($('<option></option>').val(json.departmentJsonArray[x].fieldDID).html(json.departmentJsonArray[x].fieldLabel));
                }
            }

        });
    }
}