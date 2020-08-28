#!/usr/bin/php-cgi
<?php
session_start();
if (isset($_POST['nickname'])) {
 $_SESSION['nickname']=$_POST['nickname'];
 header('Location: ./combined.php');
 exit;
}
?>
<html><body>
<?php
$nick=isset($_SESSION['nickname'])?$_SESSION['nickname']:'friendo';
echo "Hello, $nick!";
?>
<form action="#" method="post">So, what do folks call ya?
<input type='text' name='nickname'/>
<input type='submit' value='Yup'/>
</form>
</body></html>