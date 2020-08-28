#!/usr/bin/python
import random #Need the 'random' module
import cgi, os #For environment data and helpers
#Let's get the HTTP header out of the way:
print "Content-Type: text/html"
print
#Let's build the top and bottom of the page first:
start="<html><head><title>Neat!</title></head><body>"
end="</body>" #Note: we aren't printing it yet!
#Next, the dynamic bit:
randval=random.randrange(1,101) #Exclusive on end
addy=os.environ['REMOTE_ADDR']
msg="Welcome home!" if "139.57" in addy else "New phone; who dis?"
msg="Quoth the raven: "+msg
page=start+\
 "Your lucky number is "+str(randval)+"<br/>"+ \
 msg +\
 "<br/><br/>Debugging:" + \
 cgi.escape(str(os.environ)) +\
 end
#Let's see the result!
print page