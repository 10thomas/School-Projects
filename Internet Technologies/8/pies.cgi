#!/usr/bin/python
from pymongo import MongoClient
username='tt13fa' #Change this!
passwd='5618293' #Change this!
client=MongoClient('mongodb://'+username+':'+passwd+'@127.0.0.1/'+username)
db=client[username]
print "Content-Type: text/html"
print
print "<html><body>My favourite pie is "
print db.pies.find_one()['flavour']
print "!</body></html>"
print "<html><body><table border='1'>"
print "<tr><th>Flavour</th><th>Crust</th></tr>"
c=db.pies.find({'flavour':{'$ne':'mathematical'}},{'_id':False,'flavour':True,'crust':True})
for record in c:
 print "<tr><td>"+record['flavour']+"</td><td>"+record['crust']+"</td></tr"
print "</table></body></html>"