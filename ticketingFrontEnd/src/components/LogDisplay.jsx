import { useEffect, useState } from "react";
import axios from "axios";
import GrayButton from "./GrayButton.jsx";
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
    <section className="flex w-full flex-col gap-4 md:w-[48%]">
      <div className="flex justify-between">
        <div className="font-regular text-lg">Real time logs from backend</div>
        <GrayButton action={clearLogs} value="Clear" />
      </div>
      <div className="h-30 scroll-auto shadow-lg md:h-70">
        <ul className="list-disc px-2 text-sm font-light text-black">
          {data.map((item, index) => (
            <li key={index} className="py-1">
              {item}
            </li>
          ))}
        </ul>
      </div>
    </section>
  );
};

export default LogDisplay;
