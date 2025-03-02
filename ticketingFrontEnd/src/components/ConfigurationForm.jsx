import { useState } from "react";
import axios from "axios";
import TextField from "./TextField.jsx";
import BlackButton from "./BlackButton.jsx";
import GrayButton from "./GrayButton.jsx";
// This is the main form for the simulation purposes
const ConfigurationForm = () => {
  // state to keep track of configuration form inputs
  const [formInfo, setFormInfo] = useState({
    totalTickets: 0,
    ticketReleaseRate: 0,
    customerRetrievalRate: 0,
    maxTicketCapacity: 0,
    numOfVendors: 0,
    numOfCustomers: 0,
  });
  // updates state whenever user input is updated in the input fields
  const handleChange = (event) => {
    const { name, value } = event.target;

    setFormInfo((prevState) => {
      return {
        ...prevState,
        [name]: value,
      };
    });
  };
  // prevents the default page refresh of the form when submission
  const preventDefaultRefresh = (event) => {
    event.preventDefault();
  };
  // sends requests to the backend with configuration parameters (after validation)
  const startSimulation = async () => {
    try {
      let response = await axios.post(
        "http://localhost:8080/simulate",
        formInfo,
      );
      console.log(response);
    } catch (error) {
      console.log(error.message);
    }
  };
  // sends request to backend to stop simulation
  const stopSimulation = async () => {
    try {
      await axios.post("http://localhost:8080/tickets/stop");
    } catch (error) {
      console.log(error.message);
    }
  };

  return (
    <section className="flex flex-col md:w-[48%]">
      <div className="font-regular text-lg">Simulation Panel</div>
      <form
        onSubmit={preventDefaultRefresh}
        className="flex w-full flex-col gap-4 py-4 shadow-lg"
      >
        <TextField
          changeFunction={handleChange}
          name="totalTickets"
          label="Initial Ticket Count"
        />
        <TextField
          changeFunction={handleChange}
          name="ticketReleaseRate"
          label="Ticket Release Rate"
        />
        <TextField
          changeFunction={handleChange}
          name="customerRetrievalRate"
          label="Retreival Rate"
        />
        <TextField
          changeFunction={handleChange}
          name="maxTicketCapacity"
          label="Max Ticket Capacity"
        />
        <TextField
          changeFunction={handleChange}
          name="numOfVendors"
          label="Vendor Count"
        />
        <TextField
          changeFunction={handleChange}
          name="numOfCustomers"
          label="Customer Count"
        />
        <div className="flex gap-4">
          <GrayButton action={stopSimulation} value="Stop" />
          <BlackButton action={stopSimulation} value="Start" />
        </div>
      </form>
    </section>
  );
};
export default ConfigurationForm;
