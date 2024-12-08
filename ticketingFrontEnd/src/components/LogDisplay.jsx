import { useEffect, useState } from "react";
import axios from "axios";

const LogDisplay = () => {
    const [data, setData] = useState([]);
    const fetchData = async () => {
        try {
            const response = await axios.get('http://localhost:8080/logs');
            // console.log(response.data);
            setData(response.data);
        } catch (error) {
            console.log(error.message);
        }
    };

    /**
     * clears logs
     * @returns {Promise<void>}
     */
    const clearLogs = async() => {
        try{
            await axios.put("http://localhost:8080/logs");
        }catch (error){
            console.log(error.message)
        }
    }
    useEffect(() => {
        let intervalID = setInterval(fetchData, 1000 ) // Call fetchData when the component mounts
        return () => {clearInterval(intervalID)};
    }, []);

    return (
        <div className=" inline-flex flex-col w-[500px] border-2 text-black bg-white border-3 border-black rounded-lg ml-2 mt-2 p-4 min-h-[300px]">
                {/*<button onClick={fetchData}>Get Data</button>*/}
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
