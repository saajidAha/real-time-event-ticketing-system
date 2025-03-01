import { useEffect, useState } from "react";
import axios from "axios";
// Displays all the log information that is being tracked by the backend
const LogDisplay = () => {
  // array to keep track of incoming log updates
  const [data, setData] = useState([]);
  // request backend for log data
  const fetchData = async () => {
    try {
      const response = await axios.get("http://localhost:8080/logs");
      setData(response.data);
    } catch (error) {
      console.log(error.message);
    }
  };

  // sends request to backend to clear the logs
  const clearLogs = async () => {
    try {
      await axios.put("http://localhost:8080/logs");
    } catch (error) {
      console.log(error.message);
    }
  };
  // fetches logs every 1 second using periodic polling
  useEffect(() => {
    let intervalID = setInterval(fetchData, 1000); // Call fetchData when the component mounts
    return () => {
      clearInterval(intervalID);
    };
  }, []);

  return (
    <div className="mt-2 ml-2 inline-flex min-h-[300px] w-[500px] flex-col rounded-lg border-2 border-3 border-black bg-white p-4 text-black">
      <h2 className="text-center text-2xl font-bold">
        <span className="text-red-700">Live</span> logs from backend
        <button
          onClick={clearLogs}
          className="ml-4 cursor-pointer rounded-full border-2 bg-red-700 px-3 text-[15px] font-medium text-white"
        >
          Clear
        </button>
      </h2>
      <ul className="list-disc px-5 text-[16px] text-black">
        {data.map((item, index) => (
          <li key={index} className="py-1">
            {item}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default LogDisplay;
