import axios from "axios";
import {useState} from "react";

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
            <div
                className="inline-flex flex-col w-[500px] border-2 text-black bg-white border-3 border-black rounded-lg ml-2 mt-2 p-4 justify-between">
                <h2 className="text-2xl font-bold pb-4">
                    <span className="text-red-700">Customer</span> Ticket Purchase Section
                </h2>
                <form onSubmit={(event) => {
                    event.preventDefault()
                }}>
                    <div>
                        <label htmlFor="ticketID" className="inline-block w-60">Enter Ticket ID: </label>
                        <input type="text" onChange={handleChange} placeholder="ID"/>
                    </div>
                    <input onClick={buyTicket}
                           className="rounded-full text-white font-medium bg-green-700 border-2 px-3 mt-2 cursor-pointer"
                           value="Purchase Ticket" type="submit"/>
                </form>
            </div>
        </>

    )
}
export default CustomerForm;