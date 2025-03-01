import axios from "axios";
import { useState } from "react";
// form to initialize and reset ticket pool
const TicketPoolForm = () => {
  // state to track the form data
  const [formData, setFormData] = useState({});
  const [capacityErrorMsg, setCapacityErrorMsg] = useState("");
  const [positiveErrorMsg, setPositiveErrorMsg] = useState("");
  const [nonNumericErrorMsg, setNonNumericErrorMsg] = useState("");

  // send request to create a ticket pool based on the collected form info after validation of user inputs
  const createTicketPool = async () => {
    if (validateInput()) {
      setCapacityErrorMsg("");
      setPositiveErrorMsg("");
      setNonNumericErrorMsg("");
      console.log(formData);
      // send request to create ticket pool
      try {
        let response = await axios.post(
          `http://localhost:8080/tickets/createpool`,
          formData,
        );
        console.log(response.data);
      } catch (error) {
        console.log(error.message);
      }
    }
  };
  // validate user inputs and set the state of error msgs to display them accordingly
  const validateInput = () => {
    let validated = true;
    // validate user inputs
    if (isNaN(formData.totalTickets) || isNaN(formData.maxTicketCapacity)) {
      setNonNumericErrorMsg("Enter only numeric values for the fields.");
      validated = false;
    }
    if (
      parseInt(formData.totalTickets) > parseInt(formData.maxTicketCapacity)
    ) {
      setCapacityErrorMsg("Total tickets cannot Exceed Max ticket capacity.");
      validated = false;
    }
    if (
      parseInt(formData.totalTickets) < 0 ||
      parseInt(formData.maxTicketCapacity) < 0
    ) {
      setPositiveErrorMsg("Please enter positive values.");
      validated = false;
    }
    if (
      parseInt(formData.totalTickets) === 0 &&
      parseInt(formData.maxTicketCapacity) === 0
    ) {
      setPositiveErrorMsg(
        "Total tickets and max ticket capacity cannot be 0 at the same time",
      );
      validated = false;
    }
    return validated;
  };

  // reset the ticketpool
  const resetPool = async () => {
    try {
      await axios.post("http://localhost:8080/tickets/resetpool");
    } catch (error) {
      console.log(error.message);
    }
  };

  // handle the changes of the input text and set the state;
  const handleChange = (event) => {
    let { name, value } = event.target;
    setFormData((prevState) => {
      return {
        ...prevState,
        [name]: value,
      };
    });
  };

  return (
    // <div className="inline-flex flex-col">
    <div className="border-3 ml-2 mt-2 inline-flex w-[500px] flex-col rounded-lg border-2 border-black bg-white p-4 text-black">
      <h2 className="pb-4 text-2xl font-bold">
        <span className="text-red-700">Ticket Pool</span> Initialization Form
      </h2>
      <form
        onSubmit={(event) => {
          event.preventDefault();
        }}
      >
        <div>
          <label htmlFor="totalTickets" className="inline-block w-60">
            Total Tickets:{" "}
          </label>
          <input
            type="text"
            onChange={handleChange}
            placeholder="Initial number of tickets"
            name="totalTickets"
            className=""
          />
        </div>
        <div>
          <label htmlFor="maxTicketCapacity" className="inline-block w-60">
            Max Ticket Capacity:{" "}
          </label>
          <input
            type="text"
            onChange={handleChange}
            placeholder="value"
            name="maxTicketCapacity"
          />
        </div>
        <input
          onClick={createTicketPool}
          className="mt-2 cursor-pointer rounded-full border-2 bg-green-700 px-3 font-medium text-white"
          value="Initialize"
          type="submit"
        />
        <input
          onClick={resetPool}
          className="mt-2 cursor-pointer rounded-full border-2 bg-red-700 px-3 font-medium text-white"
          value="RESET Ticket Pool"
          type="submit"
        />
      </form>
      <h2 className="w-[300px] text-[14px] font-bold text-red-700">
        {capacityErrorMsg}
      </h2>
      <h2 className="w-[300px] text-[14px] font-bold text-red-700">
        {positiveErrorMsg}
      </h2>
      <h2 className="w-[300px] text-[14px] font-bold text-red-700">
        {nonNumericErrorMsg}
      </h2>
    </div>
  );
};
export default TicketPoolForm;
