#!/usr/bin/python
from pymongo import MongoClient
import random
print "Content-Type: text/html"
print
print "<html>"
print "<h1>"
print "i would like "
print (random.randint(2,15))
print " number of monkeys"
print "</h1>"
print "</html>"
