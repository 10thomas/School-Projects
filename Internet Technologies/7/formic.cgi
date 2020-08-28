#!/usr/bin/python
import cgi
import os
def claimAnt():
	formica=cgi.FieldStorage().getfirst('ant')
	if formica!=None:
 		open('antipathy','w').write(formica)
 		print "Location: ./formic.cgi"
 		print
 		exit()
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