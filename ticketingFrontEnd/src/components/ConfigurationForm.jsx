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
    const [capacityErrorMsg, setCapacityErrorMsg] = useState("");
    const [positiveErrorMsg, setPositiveErrorMsg] = useState("");
    const [nonNumericErrorMsg, setNonNumericErrorMsg] = useState("");

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

    const startSimulation = async() => {
        if (validateInput()){
            setCapacityErrorMsg(""); setPositiveErrorMsg(""); setNonNumericErrorMsg("");
            console.log(formInfo);
            try{
                let response = await axios.post("http://localhost:8080/simulate", formInfo)
                console.log(response);
            }catch(error) {
                console.log(error.message);
            }
        }
    }

    const stopSimulation = async() => {
        try{
            await axios.post("http://localhost:8080/tickets/stop")
        }catch(error){
            console.log(error.message);
        }
    }
    const validateInput = () => {
        let validated = true;
        // validate user inputs
        if ( isNaN(formInfo.totalTickets) || isNaN(formInfo.ticketReleaseRate) || isNaN(formInfo.customerRetrievalRate) || isNaN(formInfo.maxTicketCapacity) || isNaN(formInfo.numOfVendors) || isNaN(formInfo.numOfCustomers) ) {
            setNonNumericErrorMsg("Enter only numeric values for the fields.");
            validated = false;
        }
        if (parseInt(formInfo.totalTickets) > parseInt(formInfo.maxTicketCapacity)) {
            setCapacityErrorMsg("Total tickets cannot Exceed Max ticket capacity.");
            validated = false;
        }
        if ( parseInt(formInfo.totalTickets) < 0 || parseInt(formInfo.ticketReleaseRate) < 0 || parseInt(formInfo.customerRetrievalRate) < 0 || parseInt(formInfo.maxTicketCapacity) < 0 || parseInt(formInfo.numOfVendors) < 0 || parseInt(formInfo.numOfCustomers) < 0 ){
            setPositiveErrorMsg("Please enter positive values.");
            validated = false;
        }
        if (parseInt(formInfo.totalTickets) ===0 && parseInt(formInfo.maxTicketCapacity) ===0){
            setPositiveErrorMsg("Total tickets and max ticket capacity cannot be 0 at the same time");
            validated = false;
        }
        return validated;
    }
    // inputs with labels
    return(
        <div
            className="inline-flex flex-col w-[500px] border-2 text-black bg-white font-sans border-3 border-black rounded-lg ml-2 mt-2 p-4">
            <h2 className="text-2xl font-bold pb-4">
                Simulation Panel (Enter Configuration Parameters)
            </h2>
            <form onSubmit={preventDefaultRefresh}> {/*prevent page from refreshing when submit*/}
                <div>
                    <label className="inline-block w-60" htmlFor="totaltickets">Total Tickets: </label>
                    <input onChange={handleChange} required type="text" name="totalTickets"
                           placeholder="Initial number of tickets" className="px-2"/>
                </div>

                <div>
                    <label className="inline-block w-60" htmlFor="ticketReleaseRate">Ticket Release Rate: </label>
                    <input onChange={handleChange} required type="text" name="ticketReleaseRate"
                           placeholder="interval (milliseconds)" className="px-2"/>
                </div>
                <div>
                    <label className="inline-block w-60" htmlFor="customerRetrievalRate">Customer Retreival
                        Rate: </label>
                    <input onChange={handleChange} required type="text" name="customerRetrievalRate"
                           placeholder="interval (milliseconds)" className="px-2"/>
                </div>
                <div>
                    <label className="inline-block w-60" htmlFor="maxTicketCapacity">Maximum Ticket Capacity: </label>
                    <input onChange={handleChange} required type="text" name="maxTicketCapacity" placeholder="value"
                           className="px-2"/>
                </div>
                <div>
                    <label className="inline-block w-60" htmlFor="numOfVendors">Number of Vendors:</label>
                    <input onChange={handleChange} required type="text" name="numOfVendors" placeholder="value"
                           className="px-2"/>
                </div>
                <div>
                    <label className="inline-block w-60" htmlFor="numOfCustomers">Number of Customers: </label>
                    <input onChange={handleChange} required type="text" name="numOfCustomers" placeholder="value"
                           className="px-2"/>
                </div>
                <input onClick={startSimulation} className="rounded-full text-white font-medium bg-green-700 border-2 px-3 mt-2 cursor-pointer" value="Start" type="submit"/>
                <input onClick={stopSimulation} className="rounded-full text-white font-medium bg-red-700 border-2 px-3 mt-2 cursor-pointer" value="Stop" type="submit"/>
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
export default ConfigurationForm;