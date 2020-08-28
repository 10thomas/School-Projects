function delay(x)
{
	setTimeout(function() 
	{
		randomColor();
	}, x*1000);
	
}

function randomColor()
{
	document.body.style.backgroundColor = Math.floor(Math.random() * 255);
	delay(0.5);
}