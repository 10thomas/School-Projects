#!/usr/bin/python
from pymongo import MongoClient
username='tt13fa' #Change this!
passwd='5618293' #Change this!
client=MongoClient('mongodb://'+username+':'+passwd+'@127.0.0.1/'+username)
db=client[username]
print "Content-Type: text/html"
print
print "<html><body>My favourite pie is "
print 
print "</body></html>"
print "<html><body><table border='1'>"
print "<tr><th>Course</th><th>Title</th></tr>"
c=db.courses.find({'code':{'$ne':'mathematical'}},{'_id':False,'code':True,'title':True})
for record in c:
 print "<tr><td>"+record['code']+"</td><td>"+record['title']+"</td></tr"
print "</table></body></html>"