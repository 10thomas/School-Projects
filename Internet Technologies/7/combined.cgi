#!/usr/bin/python
import random #Need the 'random' module
#I left out the cgitb stuff (but you can add it back in, if you want)
print "Content-Type: text/html"
print #btw, this line tells the browser the header's done
#Btw, since we're using a string, be careful
# of backslashes.
print """<html>
<head><title>Broccoli University</title>
<link rel="stylesheet" type="text/css" href="grandstyle.css">
<style>
"""
#Let's put the subdomain style as an embedded!
sdsfile=open("subdomainstyle.css","r")
sds=sdsfile.read()
print sds
print """
</style>
</head>
<body>
"""
#Next, the other pieces!
print open('top.dealie','r').read()
print open('body.body','r').read()
print open('bottom.stuff','r').read()
print "</body></html>"