import axios from "axios";
import { useState } from "react";
import TicketInputCardContainer from "./TicketInputCardContainer.jsx";
// form to initialize and reset ticket pool
const TicketPoolForm = () => {
  // state to track the form data
  const [formData, setFormData] = useState({});

  // send request to create a ticket pool based on the collected form info after validation of user inputs
  const createTicketPool = async () => {
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
    <TicketInputCardContainer
      title="Initialize Ticket Pool"
      label="Initial Ticket Count"
      action={createTicketPool}
      changeFunction={handleChange}
    />
  );
};
export default TicketPoolForm;
