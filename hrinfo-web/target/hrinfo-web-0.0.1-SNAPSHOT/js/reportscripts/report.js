/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function search()
{
    if($('#boxTwo select option').length == 0) {
        jAlert("<font color='red'><b>Please select the columns to be displayed, before generating the report</b></font>");
    } else {

        document.forms[0].action = "SearchReport.do";
        document.forms[0].submit();
    }
}

function cancel()
{
    document.forms[0].action = "ResetReportSearch.do";
    document.forms[0].submit();
}

function searchRange(val)
{
    if(val == 'dobRange')
    {
        if(document.getElementById('dobRange_check').checked == true)
        {
            document.getElementById('dobRange_chkedValue').value = "yes";
            document.getElementById("dobRange_div").style.visibility = "visible";
            document.getElementById("dobRange_div").style.display = "block";
        } else if(document.getElementById('dobRange_check').checked == false)
        {
            document.getElementById('dateOfBirthTo').value = "";
            document.getElementById('dobRange_chkedValue').value = "no";
            document.getElementById("dobRange_div").style.visibility = "hidden";
            document.getElementById("dobRange_div").style.display = "none";
        }
    }

    if(val == 'djRange')
    {
        if(document.getElementById('djRange_check').checked == true)
        {
            document.getElementById('jdRange_chkedValue').value = "yes";
            document.getElementById("djRange_div").style.visibility = "visible";
            document.getElementById("djRange_div").style.display = "block";
        } else if(document.getElementById('djRange_check').checked == false)
{
            document.getElementById('joinDateTo').value = "";
            document.getElementById('jdRange_chkedValue').value = "no";
            document.getElementById("djRange_div").style.visibility = "hidden";
            document.getElementById("djRange_div").style.display = "none";
        }
    }

    if(val == 'confDateRange')
    {
        if(document.getElementById('confDateRange_check').checked == true)
        {
            document.getElementById('confdRange_chkedValue').value = "yes";
            document.getElementById("confDateRange_div").style.visibility = "visible";
            document.getElementById("confDateRange_div").style.display = "block";
        } else if(document.getElementById('confDateRange_check').checked == false)
{
            document.getElementById('confDateTo').value = "";
            document.getElementById('confdRange_chkedValue').value = "no";
            document.getElementById("confDateRange_div").style.visibility = "hidden";
            document.getElementById("confDateRange_div").style.display = "none";
        }
    }

    if(val == 'dueConfDateRange')
    {
        if(document.getElementById('dueConfDateRange_check').checked == true)
        {
            document.getElementById('dueConfdRange_chkedValue').value = "yes";
            document.getElementById("dueConfDateRange_div").style.visibility = "visible";
            document.getElementById("dueConfDateRange_div").style.display = "block";
        } else if(document.getElementById('dueConfDateRange_check').checked == false)
{
            document.getElementById('dueConfDateTo').value = "";
            document.getElementById('dueConfdRange_chkedValue').value = "no";
            document.getElementById("dueConfDateRange_div").style.visibility = "hidden";
            document.getElementById("dueConfDateRange_div").style.display = "none";
        }
    }
}

function abc() {

    document.forms[0].action = "loadPage.action";
    document.forms[0].submit();

}

function addItemsToBox() 
{

	var eleBoxOne = document.getElementById('box1');
	var eleBoxTwo = document.getElementById('box2');

	var boxOneSelVals = "";
	var boxTwoSelVals = "";

	for (var i = 0; i < eleBoxOne.length; i++) {

		if (boxOneSelVals != "") 
		{
			boxOneSelVals = boxOneSelVals + ',' + eleBoxOne.options[i].value;
		} 
		else {
			boxOneSelVals = eleBoxOne.options[i].value;
		}

    } 

	for (var i = 0; i < eleBoxTwo.length; i++) {

		if (boxTwoSelVals != "") 
		{
			boxTwoSelVals = boxTwoSelVals + ',' + eleBoxTwo.options[i].value;
		} 
		else {
			boxTwoSelVals = eleBoxTwo.options[i].value;
		}

    } 
		
	$.ajax({
			type : 'POST',
			url : 'testAjax.action',	
			data: { boxOneSelectedValues : boxOneSelVals, boxTwoSelectedValues: boxTwoSelVals},
			success : function(data){
			//alert(data);
			}
	});

}
