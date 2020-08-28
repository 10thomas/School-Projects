function inList(text2)
{
	var temp = "Added to Wishlist";
	var myList = localStorage.listInfo;
	var list = myList.split("_");
	for(var x = 0; x < list.length; x++)
	{
		if(list[x] === text2)
		{
			document.getElementById(text2).disabled = true;
			document.getElementById(text2).value = temp;
			document.getElementById(text2).innerHTML=temp;	
		}
	}
}

function addToStorage(text)
{
        var old = localStorage.getItem("listInfo");
        if(old === null) 
        {
		old = "";
     	}
	localStorage.setItem("listInfo", old + text);
	window.location.reload();
}