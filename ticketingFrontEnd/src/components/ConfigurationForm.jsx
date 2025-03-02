import { useState } from "react";
import axios from "axios";
import HorizontalCenterContainer from "./HorizontalCenterContainer.jsx";
import TextField from "./TextField.jsx";
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
  // state to keep track of errors
  const [capacityErrorMsg, setCapacityErrorMsg] = useState("");
  const [positiveErrorMsg, setPositiveErrorMsg] = useState("");
  const [nonNumericErrorMsg, setNonNumericErrorMsg] = useState("");

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
    if (validateInput()) {
      setCapacityErrorMsg("");
      setPositiveErrorMsg("");
      setNonNumericErrorMsg("");
      console.log(formInfo);
      try {
        let response = await axios.post(
          "http://localhost:8080/simulate",
          formInfo,
        );
        console.log(response);
      } catch (error) {
        console.log(error.message);
      }
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
  // validates user inputs and sets error message state accordingly
  const validateInput = () => {
    let validated = true;
    // validate user inputs
    if (
      isNaN(formInfo.totalTickets) ||
      isNaN(formInfo.ticketReleaseRate) ||
      isNaN(formInfo.customerRetrievalRate) ||
      isNaN(formInfo.maxTicketCapacity) ||
      isNaN(formInfo.numOfVendors) ||
      isNaN(formInfo.numOfCustomers)
    ) {
      setNonNumericErrorMsg("Enter only numeric values for the fields.");
      validated = false;
    }
    if (
      parseInt(formInfo.totalTickets) > parseInt(formInfo.maxTicketCapacity)
    ) {
      setCapacityErrorMsg("Total tickets cannot Exceed Max ticket capacity.");
      validated = false;
    }
    if (
      parseInt(formInfo.totalTickets) < 0 ||
      parseInt(formInfo.ticketReleaseRate) < 0 ||
      parseInt(formInfo.customerRetrievalRate) < 0 ||
      parseInt(formInfo.maxTicketCapacity) < 0 ||
      parseInt(formInfo.numOfVendors) < 0 ||
      parseInt(formInfo.numOfCustomers) < 0
    ) {
      setPositiveErrorMsg("Please enter positive values.");
      validated = false;
    }
    if (
      parseInt(formInfo.totalTickets) === 0 &&
      parseInt(formInfo.maxTicketCapacity) === 0
    ) {
      setPositiveErrorMsg(
        "Total tickets and max ticket capacity cannot be 0 at the same time",
      );
      validated = false;
    }
    return validated;
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
          name="totaltickets"
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
          <input
            onClick={stopSimulation}
            className="cursor-pointer rounded-full bg-gray-200 px-3 font-medium text-black"
            value="Stop"
            type="submit"
          />
          <input
            onClick={startSimulation}
            className="cursor-pointer rounded-full bg-black px-3 font-medium text-white"
            value="Start"
            type="submit"
          />
        </div>
      </form>
    </section>
  );
};
export default ConfigurationForm;

// inputs with labels
// return (
//       <div className="mt-44 inline-flex w-[95%] flex-col rounded-lg border-2 border-3 border-black bg-white p-4 text-black">
//         <h2 className="text-center text-2xl font-bold">Simulation Panel</h2>
//         <h2 className="pb-4 text-center text-2xl font-bold"></h2>
//         <form onSubmit={preventDefaultRefresh}>
//           {" "}
//           {/*prevent page from refreshing when submit*/}
//           <div>
//             <label className="inline-block w-60" htmlFor="totaltickets">
//               Total Tickets:{" "}
//             </label>
//             <input
//               onChange={handleChange}
//               required
//               type="text"
//               name="totalTickets"
//               // // placeholder="Initial nos of tickets"
//               className="px-2"
//             />
//           </div>
//           <div>
//             <label className="inline-block w-60" htmlFor="ticketReleaseRate">
//               Ticket Release Rate:{" "}
//             </label>
//             <input
//               onChange={handleChange}
//               required
//               type="text"
//               name="ticketReleaseRate"
//               // placeholder="interval (milliseconds)"
//               className="px-2"
//             />
//           </div>
//           <div>
//             <label
//               className="inline-block w-60"
//               htmlFor="customerRetrievalRate"
//             >
//               Customer Retreival Rate:{" "}
//             </label>
//             <input
//               onChange={handleChange}
//               required
//               type="text"
//               name="customerRetrievalRate"
//               // placeholder="interval (milliseconds)"
//               className="px-2"
//             />
//           </div>
//           <div>
//             <label className="inline-block w-60" htmlFor="maxTicketCapacity">
//               Maximum Ticket Capacity:{" "}
//             </label>
//             <input
//               onChange={handleChange}
//               required
//               type="text"
//               name="maxTicketCapacity"
//               // placeholder="value"
//               className="px-2"
//             />
//           </div>
//           <div>
//             <label className="inline-block w-60" htmlFor="numOfVendors">
//               Number of Vendors:
//             </label>
//             <input
//               onChange={handleChange}
//               required
//               type="text"
//               name="numOfVendors"
//               // placeholder="value"
//               className="px-2"
//             />
//           </div>
//           <div>
//             <label className="inline-block w-60" htmlFor="numOfCustomers">
//               Number of Customers:{" "}
//             </label>
//             <input
//               onChange={handleChange}
//               required
//               type="text"
//               name="numOfCustomers"
//               // placeholder="value"
//               className="px-2"
//             />
//           </div>
//           <input
//             onClick={startSimulation}
//             className="mt-2 cursor-pointer rounded-full border-2 bg-green-700 px-3 font-medium text-white"
//             value="Start"
//             type="submit"
//           />
//           <input
//             onClick={stopSimulation}
//             className="mt-2 cursor-pointer rounded-full border-2 bg-red-700 px-3 font-medium text-white"
//             value="Stop"
//             type="submit"
//           />
//         </form>
//         <h2 className="w-[300px] text-[14px] font-bold text-red-700">
//           {capacityErrorMsg}
//         </h2>
//         <h2 className="w-[300px] text-[14px] font-bold text-red-700">
//           {positiveErrorMsg}
//         </h2>
//         <h2 className="w-[300px] text-[14px] font-bold text-red-700">
//           {nonNumericErrorMsg}
//         </h2>
//       </div>
//     </HorizontalCenterContainer>
//   );
// };
// export default ConfigurationForm;
