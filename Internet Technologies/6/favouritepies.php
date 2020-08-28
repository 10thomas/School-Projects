#!/usr/bin/php-cgi

<?php

//In this case, there's little point in bothering with the HTML parts

$pies=array('Lucina','Hector','Grima');

for($x = 0; $x < 6; $x++)
{
	echo $pies[$x];

}
?>
