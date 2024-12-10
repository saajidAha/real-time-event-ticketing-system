import { useEffect, useState } from "react";
import axios from "axios";
// Displays all the log information that is being tracked by the backend
const LogDisplay = () => {
    // array to keep track of incoming log updates
    const [data, setData] = useState([]);
    // request backend for log data
    const fetchData = async () => {
        try {
            const response = await axios.get('http://localhost:8080/logs');
            setData(response.data);
        } catch (error) {
            console.log(error.message);
        }
    };

    // sends request to backend to clear the logs
    const clearLogs = async() => {
        try{
            await axios.put("http://localhost:8080/logs");
        }catch (error){
            console.log(error.message)
        }
    }
    // fetches logs every 1 second using periodic polling
    useEffect(() => {
        let intervalID = setInterval(fetchData, 1000 ) // Call fetchData when the component mounts
        return () => {clearInterval(intervalID)};
    }, []);

    return (
        <div className=" inline-flex flex-col w-[500px] border-2 text-black bg-white border-3 border-black rounded-lg ml-2 mt-2 p-4 min-h-[300px]">
            <h2 className="font-bold text-2xl text-center">
                <span className="text-red-700">Live</span> logs from backend
                <button onClick={clearLogs} className="rounded-full text-white font-medium bg-red-700 border-2 px-3 ml-4 cursor-pointer text-[15px]">Clear</button>
            </h2>
            <ul className="text-black text-[16px] list-disc px-5">
            { data.map( (item, index) => <li key={index} className="py-1">{item}</li>) }
            </ul>
        </div>
    );
};

export default LogDisplay;
