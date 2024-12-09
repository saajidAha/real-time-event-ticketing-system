## Real Time Event Ticketing System (OOP CW)
| **Heading**                 | **Description**                                                           |
|-----------------------------|---------------------------------------------------------------------------|
| **Name**                    | Saajid Ahamed                                                             |
| **UoW ID**                  | w2052929                                                                  |
| **IIT ID**                  | 20221921                                                                  |
| **Main Technologies**       | Java (v21.0.5) (2024-10-15 LTS), React.js (v18.3.1), Spring Boot (v3.3.5) |
| **Additional Technologies** | Node.js (v20.18.0) (Required for React.js Frontend)                       |

### How to start CLI & SpringBoot Backend?
#### Intellij IDEA IDE Guide: 
1. Make sure you are located in the parent directory named: "real-time-event-ticketing-system"
2. Locate the sub-directory named: "realtimeTicketingApp" (Contains the CLI bundled with the SpringBoot Backend)
3. Open the sub-directory using IntelliJ IDEA IDE.
4. Click the run button after the project has been initialized by the IDE. (you will see a leaf icon & the name of the directory next to the left of the run button)
5. The project will build and run the CORE JAVA CLI first & based on user input, it will start and stop the SpringBoot backend server automatically.

#### CMD / BASH Terminal Guide (Optional):  
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

### How to start the React.js Frontend GUI?
#### CMD / BASH Terminal Guide:
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


