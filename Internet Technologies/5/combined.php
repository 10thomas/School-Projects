#!/usr/bin/php-cgi


<?php


$disclaimer=isset($_COOKIE['disclaimer'])?"":"nertz";


$disclaimer=isset($_COOKIE['disclaimer'])?"none":"block";


setcookie('disclaimer','sure',time()+60*60*2,"/","www.cosc.brocku.ca");


$styles=array("grandstyle.css","subdomainstyle.css");


?>


<html>

<head>

<title>PHP Array</title>

</head>



<?php

session_start();

if (isset($_POST['nickname'])) 
{
 
$_SESSION['nickname']=$_POST['nickname'];
 
header('Location: ./combined.php');
 
exit;

}

?>

<html>
<body>

<?php

$nick=isset($_SESSION['nickname'])?
$_SESSION['nickname']:'friendo';

echo "Most recently added; $nick!", '<br>';



?>

<form action="#" method="post">List of heroes:
<input type='text' name='nickname'/>

<input type='submit' value='Add to List'/>

</form>

</body>
</html>