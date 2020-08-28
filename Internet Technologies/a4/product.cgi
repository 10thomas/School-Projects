#!/usr/bin/python
from pymongo import MongoClient
import cgi
username='tt13fa' #Change this!
passwd='5618293' #Change this!
client=MongoClient('mongodb://'+username+':'+passwd+'@127.0.0.1/'+username)
db=client[username]
print "Content-Type: text/html"
print
print "<html>"

print "<header>"

print open('header.html','r').read()

print "</header>"

print "<body onload=" +  "onLoadFunction()" + ">"

#Our script will go here: 
fs=cgi.FieldStorage()

if 'product' in fs:

	product=fs['product'].value

	#First, let's request the record:

	rec=db.products.find_one({'unit':product})
	print "<title>" + rec['unit'] + "</title>"

	#If the code is wrong, we could have None

	if rec==None:
		print "Ha ha. Nice try.</body></html>"
		exit()
	#If we're still here, we're good to go!
	
img3 = "pictures/" + str(rec['unit']) + ".png"

print "<table>"

print "<tr>"

print "<td colspan='2'>"

print "<img src =" +  img3 + " id='productImg' style='width:300px;height:500px;'/>"

print "</td>"

print "</tr>"

print "<tr>"

print "<th>Name</th>"

print "<td id='thisUnit'>" + rec['unit'] + "</td>"

print "</tr>"
	
print "<tr>"

print "<th>Tier</th>"

print "<td>" + rec['tier'] + "</td>"

print "</tr>"

print "</table>"

print "<script src='js/addItem.js'></script>"
print "<button id = 'test' + onclick=" + "myFunction()" + ">"
print "Add To Wishlist"
print "</button>"

print "<script>"
print "function myFunction(){"
print "my_Var = document.getElementById('thisUnit').innerHTML"
print "var myList = localStorage.listInfo;"
print "if (myList == null)"
print "{"
print "console.log('Is empty');"
print "addToStorage(my_Var + '_');"
print "}"
print "else"
print "{"
print "var list = myList.split('_');"
print "var isValid = 'true';"
print "for(var x = 0; x < list.length; x++)"
print "{"
print "if(list[x] == my_Var)"
print "{"
print "isValid = 'false';"
print "}"
print "}"
print "if (isValid == 'true')"
print "{addToStorage(my_Var + '_');}"
print "}"
print "}"

print "function onLoadFunction()"
print "{"
print "var myList = localStorage.listInfo;"
print "my_Var = document.getElementById('thisUnit').innerHTML"
print "var temp = 'Added To Wishlist';"
print "var list = myList.split('_');"
print "for(var x = 0; x < list.length; x++)"
print "{"
print "if(list[x] == my_Var)"
print "{"
print "document.getElementById('test').disabled = true;"
print "document.getElementById('test').value = temp;"
print "document.getElementById('test').innerHTML= temp;"
print "}}"
print "console.log('On Load');"
print "}"

print "</script>"



print "</body></html>"
