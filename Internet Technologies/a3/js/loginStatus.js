var test = localStorage.getItem('username');
console.log(test);
if(test == null)
{
	console.log("Login");
	document.getElementById("lgn").value = "Login";
	document.getElementById("lgn").innerHTML = "Login";
}
else
{
	console.log("Logout");
	document.getElementById("lgn").value = "Logout";
	document.getElementById("lgn").innerHTML = "Logout";
}