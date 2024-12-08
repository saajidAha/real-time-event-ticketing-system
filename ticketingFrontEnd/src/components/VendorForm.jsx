import axios from "axios";
import {useState} from "react";

const VendorForm = () => {
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
            <div
                className="inline-flex flex-col w-[500px] border-2 text-black bg-white font-sans border-3 border-black rounded-lg ml-2 mt-2 p-4">
                <h2 className="text-2xl font-bold pb-4">
                    Vendor Ticket Release Section
                </h2>
                <form onSubmit={(event) => {
                    event.preventDefault()
                }}>
                    <div>
                        <label htmlFor="ticketID" className="inline-block w-60">Enter Ticket ID: </label>
                        <input type="text" onChange={handleChange} placeholder="ID" />
                    </div>
                    <input onClick={releaseTicket}
                           className="rounded-full text-white font-medium bg-green-700 border-2 px-3 mt-2 cursor-pointer"
                           value="Release Ticket" type="submit"/>
                </form>
            </div>
        </>

    )
}
export default VendorForm;