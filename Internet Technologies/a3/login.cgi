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
print "<body>"
print "<script>"
print "temp = document.getElementById('userId').value;"
print "</script>"
print "<h1>" + temp + "</h1>"
print "<body></html>"
