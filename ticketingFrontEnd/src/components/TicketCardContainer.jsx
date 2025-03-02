import { useEffect, useState } from "react";
import axios from "axios";
import TicketCard from "./TicketCard.jsx";
import HorizontalCenterContainer from "./HorizontalCenterContainer.jsx";

const TicketCardContainer = () => {
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
    <HorizontalCenterContainer>
      <section className="mt-8 mb-12 w-[95%] max-w-screen-lg">
        <div className="font-regular mb-6 text-lg">
          Available Tickets (Live)
        </div>
        <div className="grid grid-cols-3 gap-5 md:grid-cols-5 md:gap-12">
          {tickets.map((ticket, index) => (
            <TicketCard key={index} className="py-1" id={ticket.ticketID} />
          ))}
          <TicketCard id="#A988B4U" />
          <TicketCard id="#A988B4U" />
          <TicketCard id="#A988B4U" />
        </div>
      </section>
    </HorizontalCenterContainer>
  );
};
export default TicketCardContainer;
