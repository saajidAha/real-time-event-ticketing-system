import axios from "axios";
import { useState } from "react";
// form that will be used for vendors to release specific tickets
const VendorForm = () => {
  // state to track the id of the ticket
  const [ticketID, setTicketID] = useState("");
  // send request to backend release a specific ticket
  const releaseTicket = async () => {
    console.log(ticketID);
    try {
      let response = await axios.post(`http://localhost:8080/tickets/add`, {
        ticketID: ticketID,
      });
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
      <div className="border-3 ml-2 mt-2 inline-flex w-[500px] flex-col rounded-lg border-2 border-black bg-white p-4 text-black">
        <h2 className="pb-4 text-2xl font-bold">
          <span className="text-red-700">Vendor</span> Ticket Release Section
        </h2>
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
            onClick={releaseTicket}
            className="mt-2 cursor-pointer rounded-full border-2 bg-green-700 px-3 font-medium text-white"
            value="Release Ticket"
            type="submit"
          />
        </form>
      </div>
    </>
  );
};
export default VendorForm;
