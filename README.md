# COMP1161 Object Oriented Programming Project
This project is an implementation of a System for National Identification(SNID)  
It involves 3 major components  

1. The SNIDDb for handling the creation and modification of files  
2. The SNIDApp for handling the creation and processing of the data
3. The TextUI (Which will be implemented into a gui) for the front-end  

# How to compile
Change directories to the src folder and type the following command:    

javac -d ../bin "the package name"/*.java    
or  
javac -d ../bin data/*.java && javac -d ../bin app/*.java (if you want to compile the app and data package at the same time)

# How to run(The SNIDApp)
In the src type the following command  

java -cp ../bin app.SNIDApp
