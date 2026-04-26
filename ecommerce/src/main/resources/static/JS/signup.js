let user=/*[[${msg}]]*/null

/*console.log(user);*/
console.log(/*[[${msg}]]*/)

console.log("Script tag is working");


function handlerEmail(){
	let email=document.getElementById("email").value;
	if(!email.includes("@gmail.com"))
	{
		document.getElementById("email").style.border="2px solid red";
		document.getElementById("emailmsg").innerHTML="Please enter valid email";
		document.getElementById("emailmsg").style.color="red";
	}
	else{
		document.getElementById("email").style.border="2px solid green";
		document.getElementById("emailmsg").innerHTML="Valid email";
		document.getElementById("emailmsg").style.color="green";
	}
	console.log(email);
}

function handlerPassword(){
	let pass1=document.getElementById("password").value;
	let pass2=document.getElementById("confirmPassword").value;
	if(pass1!=pass2)
	{
		document.getElementById("confirmPassword").style.border="2px solid red";
		document.getElementById("passmsg").innerHTML="Please enter correct password";
		document.getElementById("passmsg").style.color="red";
	}
	else{
		document.getElementById("confirmPassword").style.border="2px solid green";
		document.getElementById("passmsg").innerHTML="Valid password";
		document.getElementById("passmsg").style.color="green";
	}	
}

function handlerAddToCart()
{
	let user=document.getElementById("btn1").value;
	if(user==null)
	{
		document.write("Login first!");
	}
}


/*function checkLoginStatus() {
    fetch('/checkLoginStatus')
        .then(data => {
            console.log("Login status received:", data.loggedIn);
                if (data.loggedIn) {
                    alert("User is logged in");
                } else {
                    alert("User is not logged in. Please log in first.");
            } 
        })
        .catch(error => {
            console.error('Error checking login status:', error);
            // Handle errors here, such as displaying an error message to the user
        });
}
*/

