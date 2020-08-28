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
#Our script will go here: 
fs=cgi.FieldStorage()
if 'admin' in fs:
	admin=fs['admin'].value
	#First, let's request the record:
	rec=db.adminss.find_one({'user':admin})
	print "<h1>" + rec['admin'] + "</h1>"
	#If the code is wrong, we could have None
	if rec==None:
		print "Ha ha. Nice try.</body></html>"
		exit()
	#If we're still here, we're good to go!
print "</body></html>"

