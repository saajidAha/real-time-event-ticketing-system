import axios from "axios";
import {useState} from "react";
import TicketPoolForm from "./TicketPoolForm.jsx";

const CustomerForm = () => {
    // state to track the id of the ticket
    const [ticketID, setTicketID] = useState("");
    // Buy a single ticket
    const buyTicket = async() => {
            console.log(ticketID);
        try {
            let response = await axios.delete(`http://localhost:8080/tickets/${ticketID}`);
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
            <form onSubmit={(event)=>{event.preventDefault()}} className="inline-block">
                <label htmlFor="ticketID">Enter Ticket ID: </label>
                <input type="text" onChange={handleChange} placeholder="ID"/>
                <input type="submit" onClick={buyTicket} value="Buy ticket" className="cursor-pointer block"/>
            </form>
            <TicketPoolForm />
        </>

    )
}
export default CustomerForm;