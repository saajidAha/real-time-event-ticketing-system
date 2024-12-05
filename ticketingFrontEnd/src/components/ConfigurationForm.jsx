import {useState} from "react";
import axios from "axios";

const ConfigurationForm = () => {
    // state to keep track of configuration form inputs
    const [formInfo, setFormInfo] = useState({
        totalTickets: 0,
        ticketReleaseRate: 0,
        customerRetrievalRate: 0,
        maxTicketCapacity: 0,
        numOfVendors: 0,
        numOfCustomers: 0
    });

    // updates state whenever user input is updated in the input fields
    const handleChange = (event) => {
        const {name, value} = event.target;

        setFormInfo(prevState => {
            return ({
                ...prevState,
                [name] : value
            });
        })
    }
    // prevents the default page refresh of the form when submission
    const preventDefaultRefresh = (event) => {
        event.preventDefault();
    }

    const submitToBackend =
        // ()=>{console.log(formInfo)}
        async() => {
        try{
            let response = await axios.post("http://localhost:8080/simulate", formInfo)
            console.log(response);
        }catch(error){
            console.log(error.message);
        }
    }
    // inputs with labels
    return(
        <div className="inline-flex flex-col w-[500px] border-2 text-black bg-white font-sans border-3 border-black rounded-lg ml-2 mt-2 p-4">
            <h2 className="text-2xl font-bold pb-4">
                Simulation Panel (Enter Configuration Parameters)
            </h2>
            <form onSubmit={preventDefaultRefresh}> {/*prevent page from refreshing when submit*/}
                <div>
                    <label className="inline-block w-60" htmlFor="totaltickets">Total Tickets: </label>
                    <input onChange={handleChange} required type="text" name="totalTickets" placeholder="Initial number of tickets" className="px-2"/>
                </div>

                <div>
                    <label className="inline-block w-60" htmlFor="ticketReleaseRate">Ticket Release Rate: </label>
                    <input onChange={handleChange} required type="text" name="ticketReleaseRate" placeholder="interval (milliseconds)" className="px-2"/>
                </div>
                <div>
                    <label className="inline-block w-60" htmlFor="customerRetrievalRate">Customer Retreival Rate: </label>
                    <input onChange={handleChange} required type="text" name="customerRetrievalRate" placeholder="interval (milliseconds)" className="px-2"/>
                </div>
                <div>
                    <label className="inline-block w-60" htmlFor="maxTicketCapacity">Maximum Ticket Capacity: </label>
                    <input onChange={handleChange} required type="text" name="maxTicketCapacity" placeholder="value" className="px-2"/>
                </div>
                <div>
                    <label className="inline-block w-60" htmlFor="numOfVendors">Number of Vendors:</label>
                    <input onChange={handleChange} required type="text" name="numOfVendors" placeholder="value" className="px-2"/>
                </div>
                <div>
                    <label className="inline-block w-60" htmlFor="numOfCustomers">Number of Customers: </label>
                    <input onChange={handleChange} required type="text" name="numOfCustomers" placeholder="value" className="px-2"/>
                </div>
                <input onClick={submitToBackend} className="rounded border-black border-2 px-3 mt-2 cursor-pointer" value="Simulate" type="submit"/>
            </form>
        </div>
    )}
export default ConfigurationForm;