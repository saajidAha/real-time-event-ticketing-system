import {useEffect, useState} from "react";
import axios from "axios";

const TicketDisplay = () => {
    const [tickets, setTickets] = useState([]);

    const fetchTickets = async() => {
        try{
            let response = await axios.get("http://localhost:8080/tickets");
            setTickets(response.data);
            console.log(response.data);
        }catch (error){
            console.log(error.message);
        }
    }

    useEffect( ()=>{
        let intervalID = setInterval(fetchTickets, 500);
        return () => {clearInterval(intervalID)};
    }, [] )
  return(
      <>
          <div className="inline-flex flex-col w-[300px] border-2 text-black bg-white border-3 border-black rounded-lg ml-2 mt-2 p-4 min-h-[300px]">
              <h2 className="font-bold text-2xl text-center">
                  <span className="text-red-700">Live</span> Available Tickets <span className="text-red-700">{tickets.length}</span>
              </h2>
              <ul className="text-black text-[16px] list-decimal px-5">
                  { tickets.map( (ticket, index)=> <li key={index} className="py-1">{ticket.ticketID}</li> )}
              </ul>
          </div>
      </>
  )
}
export default TicketDisplay;