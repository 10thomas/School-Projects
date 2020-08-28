function delay(x)
{
	setTimeout(function() 
	{
		console.log ('delay ' + x + ' seconds');
		document.body.style.backgroundColor = Math.floor(Math.random() * 255);
	}, x*1000);
	
}

function randomColor()
{
	console.log ('test');
	document.body.style.backgroundColor = Math.floor(Math.random() * 255);
}