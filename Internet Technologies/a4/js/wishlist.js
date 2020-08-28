var myList = localStorage.getItem("listInfo");
console.log(myList);
var old = myList.split("_");
	x = old.length-1;
var text = "Wish List (" + x + ")";
document.getElementById("wl").innerHTML=text;	