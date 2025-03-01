import { useEffect, useState } from "react";
import axios from "axios";
// Display real time ticket data
const TicketDisplay = () => {
  // state array to keep track of incoming tickets information
  const [tickets, setTickets] = useState([]);
  // fetch tickets info from the backend
  const fetchTickets = async () => {
    try {
      let response = await axios.get("http://localhost:8080/tickets");
      setTickets(response.data);
      console.log(response.data);
    } catch (error) {
      console.log(error.message);
    }
  };
  // fetch ticket info every 500 milliseconds
  useEffect(() => {
    let intervalID = setInterval(fetchTickets, 500);
    return () => {
      clearInterval(intervalID);
    };
  }, []);
  return (
    <>
      <div className="border-3 ml-2 mt-2 inline-flex min-h-[300px] w-[300px] flex-col rounded-lg border-2 border-black bg-white p-4 text-black">
        <h2 className="text-center text-2xl font-bold">
          <span className="text-red-700">Live</span> Available Tickets{" "}
          <span className="text-red-700">{tickets.length}</span>
        </h2>
        <ul className="list-decimal px-5 text-[16px] text-black">
          {tickets.map((ticket, index) => (
            <li key={index} className="py-1">
              {ticket.ticketID}
            </li>
          ))}
        </ul>
      </div>
    </>
  );
};
export default TicketDisplay;
