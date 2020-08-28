#!/usr/bin/python
import os
import cgi
newbie="Welcome, for the first time!"
friendo="Welcome back!"
def cooklaimer():
	if not os.environ.has_key('HTTP_COOKIE'):
 		print "Set-Cookie: disclaimer" #Yes, printing
 		return newbie
 	else:
 		cookies=os.environ['HTTP_COOKIE'].split(';')
 		for cookie in cookies:
 			if cookie=="disclaimer":
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
				def claimAnt():
 					formica=cgi.FieldStorage().getfirst('ant')
 					if formica!=None:
 						open('antipathy','w').write(formica)
 						return formica
 					else:
 						if os.path.isfile('antipathy'):
 							return open('antipathy','r').read()
 						else:
							return 'radioactive'
				ant=claimAnt()
				print "Content-Type: text/html"
				print
				print "<html><body>"+\
				 "<p>Have you ever heard of %s ants?</p>"%(ant,)+\
				 "<form action='#' method='post'>Whatsyer fav ant?"+\
				 "<input type='text' name='ant'/><input type='submit' value='Yup'/>"+\
				 "</form></body></html>"

 				return friendo
 		print "Set-Cookie: disclaimer"
 		return newbie
print "Content-Type: text/html"
msg=cooklaimer()
print #Again, finishes the HTTP header
print "<html><body>"
print "hi: "+msg
print "</body></html>"