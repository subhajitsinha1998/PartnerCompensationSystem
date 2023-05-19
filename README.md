**Partner Compensation system**  

**Introduction**
This is an application to maintain compensation of different partners. The compensation users can maintain compensation records. New users can be provided access to the application by admins. For reporting report users can generate compensation report of the plan which are created by compensation user  

**Project structure**  
Microservices: There are two microservices which implements the required functionalities  
1.	User microservice: Used to maintain user details and to validate user against AWS AD.  
2.	Compensation Microservice: Used to create compensation for partners and to generate records.  

User Interface: The entire UI for the application has been built using angular. The Interface is styled using bootstrap, due to which it is fully responsive and optimised up to the smallest screen device available till now. The entire application is not loaded and only required modules are loaded based on requirement which decreases the load time of the application.  
AWS: Aws Cognito has been used for security to validate the user upon login.  

