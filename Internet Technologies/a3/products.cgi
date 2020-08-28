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
print open('header.html','r').read()
print "<body><table border='1'>"
c=db.products.find({'unit':{'$ne':'mathematical'}},{'_id':False,'unit':True,'tier':True})
for record in c:
	img3 = "pictures/" + record['unit'] + ".png"
	ref = "product.cgi?product=" + record['unit']
	print "<tr><td>"
	print "<a href=" + ref + ">"
	print "<img src =" +  img3 + " id='productImg' style='width:300px;height:500px;'/>"
	print "</a>"
	print "</td></tr>"
print "</table></body></html>"
