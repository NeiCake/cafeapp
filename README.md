# cafeapp
LATEST VERSION HAS IN-MEMORY DB ;)
If you are reviewing this, please pull/download the latest version. Thanks!


Adding purchases provides no feedback since I am very bad at front end. If it fails, you'll see an error 500 being thrown (check dev console, F12 in chrome) and if you don't get that, the transaction probably went though properly. Check db.

There is very little protection against bad data (you can add negative amount of purchase to add to product stock, for example) and some other "why would anyone ever do this" things that I've had no time to deal with
Also, discounts are put on the purchase but I'm not actually "applying them", yes (just a tiny bit of math to add there but it requires some safety like percentages within limits and stuff)

Tomcat container is embedded and will need port 8080 to run.  Access it by localhost:8080 

Can be run by running App.java or building a jar using maven after you've imported the project to your IDE
application.properies to set your database stuff (user/pass/db)
Don't forget to create the schema in your db to match "cafe" or whatever else you wanna call it in the properties file
