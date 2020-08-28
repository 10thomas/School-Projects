#!/usr/bin/php-cgi
<?php
if (!array_key_exists("selection",$_REQUEST)) die();
//Let's just preload the entire file:
$data=file_get_contents($_REQUEST['selection']);
//This might be another case where line endings matter!
$lines=explode("\n",$data);
foreach ($lines as $line) {
 if (strlen($line)==0) continue;
 $pair=explode(",",$line);
 echo "<tr><td>".$pair[0]."</td><td>".$pair[1]."</td></tr>";
}
?>