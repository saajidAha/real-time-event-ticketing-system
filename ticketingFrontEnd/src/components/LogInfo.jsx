import { useEffect, useState } from "react";
import axios from "axios";

const LogInfo = () => {
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

    useEffect(() => {
        let intervalID = setInterval(fetchData, 1500 ) // Call fetchData when the component mounts
        return () => {clearInterval(intervalID)};
    }, []);

    return (
        <div style={{border:"solid", width:"500px"}}>
            This is the data: { data.map( (item, index) => <h4 key={index}>{item}</h4>) }
            <button onClick={fetchData}>Get Data</button>
        </div>
    );
};

export default LogInfo;
