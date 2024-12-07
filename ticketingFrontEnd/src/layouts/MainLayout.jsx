import Header from "../components/Header.jsx";
import Footer from "../components/Footer.jsx";
import {Outlet} from "react-router-dom";
import TicketDisplay from "../components/TicketDisplay.jsx";
import LogDisplay from "../components/LogDisplay.jsx";

const MainLayout = () => {
    return(
        <>
            <Header />
            <Outlet />
            <TicketDisplay />
            <LogDisplay />
            <Footer />
        </>
    )
}
export default MainLayout;