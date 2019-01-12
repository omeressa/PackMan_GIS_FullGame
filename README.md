# OOP_EX2-Ex4
This Github project is an educational example for using git & github as part of the Object Oriented Programming course in Ariel University. The main focus of this project is to allow an online suppoer of Ex2, Ex3, Ex4 which are all related to a GIS like system.

# Project
this was the first stage of developing this project, we will contenue developing the project for 2 more stages.

# Project Overview
This is a Geographic Information System (GIS), we convert a Csv file into a kml file
the csv file contains a list of wifi points with their information
first we identified the elements of the gps point,added the right functions for Coords package
then we wrote the kml/csv/multiCsv classes
after reading the csv file and making it a kml file, we can uplode the file on apps such as google earth and see these gps points

# reference
https://www.mobility-services.in.tum.de/?p=2335   for Coords
http://www.ntu.edu.sg/home/ehchua/programming/java/j4a_gui.html
https://stackoverflow.com/questions/1375729/search-for-file-in-directory-with-multiple-directories

 # Ex 3
 for Ex3 we made new package named Game, this package contain some classes that we need to make the Packman Game
 
 # Game
 1) Fruit class : this class got all the data needed for one fruit
 2) Packman ; this class got all the data needed for one packman
 3) Map : map class got the cordanaites and Ariel1 plus helping functions such as point gps to pixel 
 4) Path : in this class we got the path one packman go through fruits, time and distance
 5) ShortPathAlgo : as it says in the name a class to get the shortest path for ea packman
 6) Path2Kml : empty class
 7) Game this class contain the game, cvs reader , csv writer and kml writer as wanted in ex3
 8) run : main class for running the game
 9) Compare : helping class
 
 in addition to game class we also added a new class to another package:
 # GUI
 MyFrame : the frame of te game, icons, menu and its action
 the game works fine and make readable file on google earth
 
 # note:
 sry about the poor comits and late updating, this was becuase of my old project that i worked this Ex3 on , i did something wrong that changed the java project and made it normal folder so i had to delete the riposity and make a new one 
