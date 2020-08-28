#!/usr/bin/python
from pymongo import MongoClient
username='tt13fa' #Change this!
passwd='5618293' #Change this!
client=MongoClient('mongodb://'+username+':'+passwd+'@127.0.0.1/'+username)
db=client[username]
print "Content-Type: text/html"
print
print "<html>"
print "<body>"
#text = "Test 2"
#db.wishlist.insert({'unit': text})
c=db.wishlist.find({'unit':{'$ne':'mathematical'}},{'_id':False,'unit':True})
print "<table border='1'>"
print "<tr><th>Unit</th></tr>"
print "<script>"
print "var myList = localStorage.listInfo;"
print "var list = myList.split('_');"
print "for(var x = 0; x < list.length; x++)"
print "{"
print "if(list[x] != '')"
print "document.write('<tr><td>' + list[x] + '</td></tr>');"
print "}"
print "</script>"
for record in c:
 print "<tr><td>"+record['unit']+"</td></tr>"
print "</table>"
print "</body>"
print "<?php"
print "$mytext = $_POST['list'];"
print "echo $mytext;"
print "?>"
print "</html>"