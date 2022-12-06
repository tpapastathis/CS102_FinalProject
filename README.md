# CS102_FinalProject
Data Structures Final Project - Simple Time Card Program
  
SIMPLE TIME CARD PROGRAM    
Author: Ted Papastathis   
CS102: Data Structures Final Project    
Instructor: Saba Jamalian     
Date Finished: 12/06/2022     



INSTRUCTIONS:

Start by using Employee Mode. Enter a test name and save the data by clicking punch-in, submit, punch-out, and submit again.      
This will allow the program to generate the two text documents required for storing Shift Objects.      

Next, Enter Employee Mode (Default Code: 1234) and define a Vacation Day in the format "mm/dd/yyyy".    
After submitting the defined Vacation Day, the final text document will be created.     

After this, you will be free to use the program as you please!


This program satisfies the following User Statements given by Saba Jamalian:


1. As an employee, I would like to be able to punch in, punch out, type my name, and submit. 

2. As a manager, I want to view and manually override punch-in and punch-out data for a given employee in the program.

3. As a manager, I would like to audit the total working time for each employee in a given time frame. 

4. As a manager, I would like to define available vacation days for my employees. Employees then are able to request vacation days. 


The Source Code is provided, and MainClass.java is the initial window call. A Runnable JAR File is also provided.     
The Current Default Manager Password is "1234", and this can be easily changed in EnterManagerCode.java.    
This project is my first major project in Java, and I am aware that the code is a bit disorganized.     
For the purposes of this program, many global variables were used, going against OOP Principles.    
Many static methods were also used due to the way I learned JFrames and other Swing Components.     
I was taught to create JFrames by extending the class, but I'm aware that this is malpractice.    
I was also never taught JavaFX, so Swing was used.    
