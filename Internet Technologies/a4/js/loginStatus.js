var test = localStorage.getItem('username');
if(test == null)
{
	document.getElementById("lgn").value = "Login";
	document.getElementById("lgn").innerHTML = "Login";
}
else
{
	document.getElementById("lgn").value = "Logout";
	document.getElementById("lgn").innerHTML = "Logout";
}