import axios from "axios";
import { useState } from "react";
// The customer ticket purchase section form
const CustomerForm = () => {
  // state to track the id of the ticket
  const [ticketID, setTicketID] = useState("");
  // send request to backend to buy a single ticket
  const buyTicket = async () => {
    console.log(ticketID);
    try {
      let response = await axios.delete(
        `http://localhost:8080/tickets/${ticketID}`,
      );
      console.log(response.data);
    } catch (error) {
      console.log(error.message);
    }
  };
  // handle the changes of the input text and set the state;
  const handleChange = (event) => {
    let value = event.target.value;
    setTicketID(value);
  };
  return (
    <>
      <div className="border-3 ml-2 mt-2 inline-flex w-[500px] flex-col justify-between rounded-lg border-2 border-black bg-white p-4 text-black">
        <h2 className="pb-4 text-2xl font-bold">
          <span className="text-red-700">Customer</span> Ticket Purchase Section
        </h2>
        {/*prevents full page refresh on submission*/}
        <form
          onSubmit={(event) => {
            event.preventDefault();
          }}
        >
          <div>
            <label htmlFor="ticketID" className="inline-block w-60">
              Enter Ticket ID:{" "}
            </label>
            <input type="text" onChange={handleChange} placeholder="ID" />
          </div>
          <input
            onClick={buyTicket}
            className="mt-2 cursor-pointer rounded-full border-2 bg-green-700 px-3 font-medium text-white"
            value="Purchase Ticket"
            type="submit"
          />
        </form>
      </div>
    </>
  );
};
export default CustomerForm;
