# Real Time Event Ticketing System (OOP CW)
| **Heading**                    | **Description**                                                                                                                                                                                                                                       |
|--------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Name**                       | Saajid Ahamed                                                                                                                                                                                                                                         |
| **UoW ID**                     | w2052929                                                                                                                                                                                                                                              |
| **IIT ID**                     | 20221921                                                                                                                                                                                                                                              |
| **Main Technologies**          | 1. Java (v21.0.5) (2024-10-15 LTS)<br/>2. React.js (v18.3.1) (FRONTEND)<br/>3. Spring Boot (v3.3.5) (BACKEND)                                                                                                                                         |
| **Additional Technologies**    | Node.js (v20.18.0) (Required to run React.js server)                                                                                                                                                                                                  |
| **Bonus Features implemented** | 1. Persistence. Saves transaction data to a database using H2 embedded database (http://localhost:8080/h2-console/)<br/>2. Advanced Synchronization (utilized ReentrantLock)<br/>3. Option to add / remove vendors & cutomers dynamically at runtime. |

## How to start CLI & SpringBoot Backend?
### Intellij IDEA IDE Guide: 
1. Make sure you are located in the parent directory named: "real-time-event-ticketing-system"
2. Locate the sub-directory named: "realtimeTicketingApp" (Contains the CLI bundled with the SpringBoot Backend)
3. Open the sub-directory using IntelliJ IDEA IDE.
4. Click the run button after the project has been initialized by the IDE. (you will see a leaf icon & the name of the directory next to the left of the run button)
5. The project will build and run the CORE JAVA CLI first & based on user input, it will start and stop the SpringBoot backend server automatically.

### CMD / BASH Terminal Guide (Optional):  
1. Make sure you are located in the parent directory named: "real-time-event-ticketing-system" & Open the command line with the path specifed as this specified parent directory.
2. . Run the below commands based on the specific terminal.
    ```bash
   #CMD Terminal (Windows)
   cd ./realTimeTicketingApp
   mvnw spring-boot:run
    ```
   ```bash
   #Bash Terminal (MacOS or other OS)
   cd ./realTimeTicketingApp
   ./mvnw spring-boot:run
    ```

## How to start the React.js Frontend GUI?
### CMD / BASH Terminal Guide:
1. Open the command line located in the path specified as the parent-directory "real-time-event-ticketing-system"
2. Run the below commands to initialize the React.js frontend with the required dependencies and to start the server.
    ```bash
    #CMD / Bash Terminal
    cd ./ticketingFrontEnd
    npm install
    npm run dev
    ```
3. The server will run on port 3000 in this url: http://localhost:3000/
4. After you have completed using, type "q" in the same terminal to close the port that the server is running in.

## Usage Instructions
1. Follow the instructions provided by the CLI / GUI for a smooth intuitive experience.
2. Important note to consider when using the GUI: Before purchasing a ticket as a customer or releasing a ticket as a vendor, Make sure that a ticketpool is initialized by:
   1. Option 1: Filling out the ticketpool initialization form in the GUI.
   2. Option 2: Running a simulation in the GUI by providing the configuration parameters (this will automatically initilize a ticketpool for ticket handling operations)

## Assumptions 
### General:
+ Each Customer thread will only purchase 1 ticket.
+ Each Vendor thread will only release 1 ticket.
+ The number of tickets purchased and released will be based on the numOfCustomers & numOfVendors parameters.
### For Configuration Parameters:
1. totalTickets : The initial number of tickets that the ticketpool will be initialized with. This amount of tickets will increase/ decrease based on vendors releasing tickets and customers purchasing tickets. This ticket amount will always be within the maxTicketCapacity parameter and will not exceed it.
2. maxTicketCapacity: The maximum number of tickets that a ticketpool can hold.
3. customerRetreivalRate: The frequency of the purchase of tickets by the seperate customer threads. (e.g. if 1000 milliseconds is specified, customers will purchase tickets every 1000 milliseconds.)
4. ticketReleaseRate: The frequency of the release of tickets by the seperate vendor threads. (e.g. if 1000 milliseconds is specified, vendors will purchase tickets every 1000 milliseconds.)
5. numOfCustomers (Additional Parameter): The number of customer threads to be created for the simulation 
6. numOfVendors (Additional Parameter): The number of vendor threads to be created for the simulation 
