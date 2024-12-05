import axios from "axios";
import {useState} from "react";

const CreatePool = () => {
    // state to track the form data
    const [formData, setFormData] = useState({});
    // Create a ticket pool
    const createTicketPool = async() => {
        console.log(formData);
        try {
            let response = await axios.post(`http://localhost:8080/tickets/createpool`, formData);
            console.log(response.data);
        }catch (error){
            console.log(error.message);
        }
    }
    // handle the changes of the input text and set the state;
    const handleChange = (event) => {
        let {name, value} = event.target;
        setFormData(prevData => {
            return{
                ...prevData,
                [name] : value
            }
        })
    }
    return(
        <>
            <h2>
                Vendor Ticket Release Section
            </h2>
            <form onSubmit={(event)=>{event.preventDefault()}}>
                <label htmlFor="totalTickets">Total Tickets: </label>
                <input type="text" onChange={handleChange} placeholder="Initial number of tickets" name="totalTickets"/>
                <label htmlFor="maxTicketCapacity">Max Ticket Capacity: </label>
                <input type="text" onChange={handleChange} placeholder="value" name="maxTicketCapacity"/>
                <input type="submit" onClick={createTicketPool} value="Initiialize Ticket Pool" className="cursor-pointer"/>
            </form>
        </>

    )
}
export default CreatePool;