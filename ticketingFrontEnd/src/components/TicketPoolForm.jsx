import axios from "axios";
import {useState} from "react";

const TicketPoolForm = () => {
    // state to track the form data
    const [formData, setFormData] = useState({});
    const [errorMessage, setErrorMessage] = useState("");
    const [capacityErrorMsg, setCapacityErrorMsg] = useState("");
    const [positiveErrorMsg, setPositiveErrorMsg] = useState("");
    const [nonNumericErrorMsg, setNonNumericErrorMsg] = useState("");

    // Create a ticket pool
    const createTicketPool = async() => {
        if(validateInput()){
            setCapacityErrorMsg(""); setPositiveErrorMsg(""); setNonNumericErrorMsg("");
            console.log(formData);
            setErrorMessage("");
            // send request to create ticket pool
            try {
                let response = await axios.post(`http://localhost:8080/tickets/createpool`, formData);
                console.log(response.data);
            }catch (error){
                console.log(error.message);
            }
        }
    }

    const validateInput = () => {
        let validated = true;
        // validate user inputs
        if ( isNaN(formData.totalTickets) || isNaN(formData.maxTicketCapacity) ) {
            setNonNumericErrorMsg("Enter only numeric values for the fields.");
            validated = false;
        }
        if (parseInt(formData.totalTickets) > parseInt(formData.maxTicketCapacity)) {
            setCapacityErrorMsg("Total tickets cannot Exceed Max ticket capacity.");
            validated = false;
        }
        if ( parseInt(formData.totalTickets) < 0|| parseInt(formData.maxTicketCapacity) < 0 ){
            setPositiveErrorMsg("Please enter positive values.");
            validated = false;
        }
        return validated;
    }

    // handle the changes of the input text and set the state;
    const handleChange = (event) => {
        let {name, value} = event.target;
        setFormData(
            prevState => {
                return {
                    ...prevState,
                    [name]: value
                }
            }
        )
    }

    return(
        <div className="inline-flex flex-col">
            <h2 className="">
                Ticket Pool Initialization Form
            </h2>
            <form onSubmit={(event) => {
                event.preventDefault()
            }} className="w-[300px]">
                <label htmlFor="totalTickets">Total Tickets: </label>
                <input type="text" onChange={handleChange} placeholder="Initial number of tickets" name="totalTickets"/>
                <label htmlFor="maxTicketCapacity">Max Ticket Capacity: </label>
                <input type="text" onChange={handleChange} placeholder="value" name="maxTicketCapacity"/>
                <input type="submit" onClick={createTicketPool} value="Initialize" className="cursor-pointer block"/>
            </form>
            <h2 className="text-red-700 font-bold w-[300px] text-[14px]">
                {capacityErrorMsg}
            </h2>
            <h2 className="text-red-700 font-bold w-[300px] text-[14px]">
                {positiveErrorMsg}
            </h2>
            <h2 className="text-red-700 font-bold w-[300px] text-[14px]">
                {nonNumericErrorMsg}
            </h2>
        </div>

    )
}
export default TicketPoolForm;