import { useEffect, useState } from "react";
import axios from "axios";

const Home = () => {
    const [data, setData] = useState([]);

    const fetchData = async () => {
        try {
            const response = await axios.get('http://localhost:8080/simulate');
            console.log(response.data);
            setData(response.data);
        } catch (error) {
            console.log(error.message);
        }
    };

    useEffect(() => {
        setInterval(fetchData, 1000 )// Call fetchData when the component mounts
    }, []);

    return (
        <>
            This is the data: { data.map( (item, index) => <h4 key={index}>{item}</h4>) }
            <button onClick={fetchData}>Get Data</button>
        </>
    );
};

export default Home;
