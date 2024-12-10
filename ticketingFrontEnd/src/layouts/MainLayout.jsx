import Header from "../components/Header.jsx";
import {Outlet} from "react-router-dom";
import TicketDisplay from "../components/TicketDisplay.jsx";
import LogDisplay from "../components/LogDisplay.jsx";
import NavBar from "../components/NavBar.jsx";

const MainLayout = () => {
    return(
        <>
            <Header />
            <NavBar />
            <Outlet />
            <TicketDisplay />
            <LogDisplay />
        </>
    )
}
export default MainLayout;