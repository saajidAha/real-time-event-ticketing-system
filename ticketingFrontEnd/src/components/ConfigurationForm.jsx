import {useState} from "react";

const ConfigurationForm = () => {
    // state to keep track of configuration form inputs
    const [formInfo, setFormInfo] = useState({
        totalTickets: 0,
        ticketReleaseRate: 0,
        customerRetrevialRate: 0,
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
    // inputs with labels
    return(
        <div className="inline-flex flex-col w-[500px] border-2 text-black bg-white font-sans border-3 border-black rounded-lg ml-2 mt-2 p-4">
            <h2 className="text-2xl font-bold pb-4">
                Simulation Panel (Enter Configuration Parameters)
            </h2>
            <div>
                <label className="inline-block w-60" htmlFor="totaltickets">Total Tickets: </label>
                <input onChange={handleChange} type="text" name="totalTickets" placeholder="Initial number of tickets" className="px-2"/>
            </div>

            <div>
                <label className="inline-block w-60" htmlFor="ticketReleaseRate">Ticket Release Rate: </label>
                <input onChange={handleChange} type="text" name="ticketReleaseRate" placeholder="interval (milliseconds)" className="px-2"/>
            </div>

            <div>
                <label className="inline-block w-60" htmlFor="customerRetrevialRate">Customer Retreival Rate: </label>
                <input onChange={handleChange} type="text" name="customerRetrevialRate" placeholder="interval (milliseconds)" className="px-2"/>
            </div>

            <div>
                <label className="inline-block w-60" htmlFor="maxTicketCapacity">Maximum Ticket Capacity: </label>
                <input onChange={handleChange} type="text" name="maxTicketCapacity" placeholder="value" className="px-2"/>
            </div>


            <div>
                <label className="inline-block w-60" htmlFor="numOfVendors">Number of Vendors:</label>
                <input onChange={handleChange} type="text" name="numOfVendors" placeholder="value" className="px-2"/>
            </div>

            <div>
                <label className="inline-block w-60" htmlFor="numOfCustomers">Number of Customers: </label>
                <input onChange={handleChange} type="text" name="numOfCustomers" placeholder="value" className="px-2"/>
            </div>
        </div>
    )}
export default ConfigurationForm;