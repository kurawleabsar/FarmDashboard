The link to the repository is https://github.com/kurawleabsar/FarmDashboard.git

FARM MANAGEMENT SYSTEM USING DRONE

Project Description-

Farm Management System is a project in which agriculture and technology works together with each other to ease the efforts of farmers. Farm Management system is a smart and interactive dashboard integrated with drone control that manages all the task related to farm management at one place. In this we made use of software's such as Javafx ,scene builder and Eclipse IDE throughout the development process of the project. This project greatly uses the help of the drone technology to bring a new change to the old and traditional way of farming methods. Farm management system helps in keeping track of crop growth , yield , crop health ,etc this data is really helpful as it gives insight to the user or farmer for what decision to make for their profit. With the help of proper tracking of resources less waste of resources happen which in turn is cost efficient. With the help of Drone manual labour cost is also decreased. Farm management system is a centralized system where all the management of the farm and task to be done are recorded and kept track on. Our project has a user friendly interface with simplistic design which helps the user to get through it without any trouble at all. Farm Management System has functions like Scan Farm which scans the whole farm or monitors the whole farm greatly reducing time taken compared to the regular monitoring of the farm. It also has functions like GO to item or visit item which can be used to for the drone to monitor a particular item in the farm. It also includes functions like add item to the farm, delete items from the farm ,change the dimension of the farm and so on. Farm management system greatly helps in modernizing farming by automating the system and reducing a huge amount of task through the incorporation of drone technology.


Table Of Content 

- Installation instructions

- Usage


>>>>>INSTALLATION INSTRUCTIONS

1. Download the JavaFX by visiting javaFX downloads page
https://openjfx.io/openjfx-docs/
2. Download the Eclipse IDE by visiting the Eclipse Downloads page
https://www.eclipse.org/downloads/
3. After Eclipse installation, start the Eclipse and set a workspace directory when prompted.
4. In eclipse, Help -> Eclipse Marketplace -> search for e(fx)clipse 3.8.0 -> Install 
   Restart your Eclipse IDE to apply the software changes
5. Install Scene Builder from the website https://gluonhq.com/products/scene-builder/
6. Now, configure javaFX SDK in Eclipse
   Extract the downloaded JavaFX SDK to a location on your computer
   In Eclipse,
Go to Window -> Preferences -> Java -> Build path -> User Libraries -> create a new user library
   Click on the newly created library, click Add External JARs and navigate to the lib folder in the javaFX SDK file. Then add all the JAR files from the lib folder. 
7. Create a random java project in the eclipse.
8. Right click on the project in the Project Explorer.
   Go to Build Path -> Configure build Path -> Add libraries -> user library -> JavaFX and click on finish.
9. Now, set the VM Arguments 
   Go to Run -> Run configurations -> under Arguments tab add the following 
   For Windows this path
   --module-path "\path\to\javafx-sdk-23.0.1\lib" --add-modules javafx.controls,javafx.fxml
   For Linux/Mac this path
   --module-path /path/to/javafx-sdk-23.0.1/lib --add-modules javafx.controls,javafx.fxml
 Replace the path inside " " with the actual path to your JavaFX SDK.
10. Now connect Scene builder to Eclipse
    Go to windows -> preferences -> JavaFX
    In the Scene Builder executable field, browse to the installation location of Scene Builder and select the executable file.
    
    Now create an FXML file
    Right click on the project in the project explorer
    Go to New -> other -> JavaFX -> New FXML Document -> name the file -> finish
    
    Now, right click on the FXML file in Eclipse then select "open with scene builder"
    It will launch the scene builder
    
11. Write the code in the java project file and Run it. 



>>>>>USAGE

In this section, there are instructions mentioned to use the project once installed.

Step 1-After the code runs successfully, you will be able to see farm dashboard which is used to manage farm using integrated drone.

Step 2-Once the farm dashboard is visible, user can add items, delete items, add item containers, delete item containers, scan farm, visit item/item container, change location, change price, change co-ordinates, etc.

Step 3- Click on scan farm to start drone animation.

Step 4- Click on Root and then click on Add_Item_Container, and once Item Container is added, you can add item under the item container. 

Step 5- Once item or item container is added to the visualization area, if item is selected and Go To Item/Container clicked, then the drone will visit that particular item/item container.

For example lets populate our dashboard with some items and item containers.

Step 1- Click on Root-> Add_Item_Container-> Input dialog appears-> Enter name of the container (Barn)->Set price of the item conatiner (0)-> Set the width(300)->height(200)->Xcoordinate(400)->Ycoordinate-> Ok-> The barn item container gets added to the visualization pane

For an exmaple, consider following dimensions to create an dashboard like which is in our PPT.

Item(I)/Item Container(IC) (X, Y, Width, Height)

IC- Barn (400,350,300,200)
IC- Livestock (420,400,100,100)
I- Cow (450,450,25,25)
IC- Milk Storage (550,400,100,100)
IC- Storage Building (50,350,200,200)
I- Tractor (100,400,30,30)
I- Tiller (200,500,40,40)
IC- Water Tank (40,150,100,100)
IC- Crop (350,30,300,300)
I- Soy Crop (400,100,0,0)

This is just an example to use to get all the items/item containers placed in the proper visualization area.

Also there is delete option to delete the item container. 

Also there is option to change the name of the item/item container.

This is how our farm dashboard will work. Thankyou

The link to the repository is https://github.com/kurawleabsar/FarmDashboard.git
