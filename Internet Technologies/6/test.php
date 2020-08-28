#!/usr/bin/php-cgi
<html><head><style>
.tab {
border:1px solid black;
display:inline-block;
padding:5px 20px;
}
.selected {
border-bottom:none!important;
}
.spacer {
border-bottom:1px solid black;
display:inline-block;
padding:5px 20px;
}
.enclosure {
display:inline-block;
}
.below {
border-left:1px solid black;
border-bottom:1px solid black;
border-right:1px solid black;
}
table { width:100%; }
th,td { border:1px solid black; }
</style><script>
function selector(sel) {
 req=new XMLHttpRequest();
 req.onreadystatechange=function(){
 if (this.readyState==4 & this.status==200) {
 window.entries.innerHTML=this.response;
 prev=document.getElementsByClassName("selected")[0];
 if (prev) prev.classList.toggle("selected");
 document.getElementById("t_"+sel).classList.toggle("selected");
 }
 };
 req.open("GET","retrieve.php?selection="+sel+".txt");
 req.send();
}

</script></head><body>
<div class='enclosure'><?php
function spacer() {
echo "<div class='spacer'>&nbsp;&nbsp;&nbsp;</div>";
}
spacer();
$categories=glob("*.txt");
foreach ($categories as $type) {
$t=substr($type,0,strpos($type,"."));
echo "<div class='tab' id='t_$t' onclick='selector(\"$t\");'>".$t."</div>";
spacer();
}
?>
<br/>
<div class="below">
<table>
<tr><th>Type</th><th>Score</th></tr>
</table>
<table id="entries"></table>
</div>
</div>
</body></html>