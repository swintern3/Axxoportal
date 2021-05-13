<%@ include file="/init.jsp"%>


<portlet:resourceURL var="addUserURL"></portlet:resourceURL>
<div>

	<input type="text" id="screenname" placeholder="Enter Screen Name" class="scrname"> 
	<input type="text" id="fname" placeholder="Enter First Name" name="firstname"><br> 
	<input type="text" id="lname" placeholder="Enter Last Name" name="lastname"><br> 
	<input type="text" id="email" placeholder="Enter Email ID" name="mail"><br>
	<input type="text" id="pass" placeholder="Password" class="password"><br>
	<input type="text" id="repass" placeholder="Re-enter Password" class="rpass"><br>
		
	<button id="myBtn" onclick="addUser()">Add</button>

</div>
<script>

function addUser(){
	console.log("started creating new user!");
	var screenName = $('#screenname').val();
	var fName = $('#fname').val();
	var lName= $('#lname').val();
	var eMail= $('#email').val();
	var p1= $('#pass').val();
	var p2= $('#repass').val();
	if(p1!=p2)
		{
		alert("Password Does Not Match");
		document.getElementById("pass").focus();
		document.getElementById("repass").focus();
		}
	var postData = {
			'<portlet:namespace/>screenname': screenName,
			'<portlet:namespace/>fname': fName,
			'<portlet:namespace/>lname': lName,
			'<portlet:namespace/>email': eMail,
			'<portlet:namespace/>pass': p1
			};
	AUI().use('aui-io-request',function(A){
		A.io.request('${addUserURL}',{
			method: 'post',
			data: postData,
			on: {
					 success: function(){   					
						 var responseText = this.get('responseData');
						   var responseJson = JSON.parse(responseText);
						   
						   console.log(responseJson);
					},
					error: function(err) {
						console.log("error::",err);
					}
			}
		});
	});
	
	
	
}

</script>