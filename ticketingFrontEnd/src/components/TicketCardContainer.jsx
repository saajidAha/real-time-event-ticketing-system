import { useEffect, useState } from "react";
import axios from "axios";
import TicketCard from "./TicketCard.jsx";
import HorizontalCenterContainer from "./HorizontalCenterContainer.jsx";
import Title from "./Title.jsx";
import BlackButton from "./BlackButton.jsx";
import GrayButton from "./GrayButton.jsx";

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
  // reset the ticketpool
  const resetPool = async () => {
    try {
      await axios.post("http://localhost:8080/tickets/resetpool");
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
      <section className="mb-12 w-[95%] max-w-screen-lg pt-12">
        <div className="flex justify-between">
          <Title value="Available Tickets (Live)" />
          <GrayButton value="Reset Pool" action={resetPool} />
        </div>
        <div className="mt-10 grid grid-cols-3 gap-5 md:grid-cols-5 md:gap-12">
          {tickets.toReversed().map((ticket, index) => (
            <TicketCard key={index} className="py-1" id={ticket.ticketID} />
          ))}
          {/* Below is just dummy tickets for the UI*/}
          <TicketCard />
          <TicketCard />
          <TicketCard />
          <TicketCard />
          <TicketCard />
        </div>
      </section>
    </HorizontalCenterContainer>
  );
};
export default TicketCardContainer;
