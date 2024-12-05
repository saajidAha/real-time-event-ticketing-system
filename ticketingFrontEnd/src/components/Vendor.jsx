import axios from "axios";
import {useState} from "react";

const Vendor = () => {
    // state to track the id of the ticket
    const [ticketID, setTicketID] = useState("");
    // Release a specific ticket
    const releaseTicket = async() => {
        console.log(ticketID);
        try {
            let response = await axios.post(`http://localhost:8080/tickets/add`, {ticketID: ticketID});
            console.log(response.data);
        }catch (error){
            console.log(error.message);
        }
    }
    // handle the changes of the input text and set the state;
    const handleChange = (event) => {
        let value = event.target.value;
        setTicketID(value);
    }
    return(
        <>
            <h2>
                Vendor Ticket Release Section
            </h2>
            <form onSubmit={(event)=>{event.preventDefault()}}>
                <label htmlFor="ticketID">Enter Ticket ID: </label>
                <input type="text" onChange={handleChange} placeholder="ID"/>
                <input type="submit" onClick={releaseTicket} value="Release Ticket" className="cursor-pointer"/>
            </form>
        </>

    )
}
export default Vendor;