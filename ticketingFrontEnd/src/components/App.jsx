import LogDisplay from "./LogDisplay.jsx";
import TicketDisplay from "./TicketDisplay.jsx";
import ConfigurationForm from "./ConfigurationForm.jsx";
import Test from "./Test.jsx";
import Customer from "./Customer.jsx";
import Vendor from "./Vendor.jsx";
import CreatePool from "./CreatePool.jsx";
import {createBrowserRouter} from "react-router-dom";

const App = () => {
    const router = createBrowserRouter([]);

    return (
        <>
            {/*<Test />*/}
            <Customer />
            <Vendor />
            <CreatePool />
            <ConfigurationForm />
            <TicketDisplay />
            <LogDisplay />
        </>
    )
}
export default App;