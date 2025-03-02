import Header from "../components/Header.jsx";
import { Outlet } from "react-router-dom";
import TicketDisplay from "../components/TicketDisplay.jsx";
import LogDisplay from "../components/LogDisplay.jsx";
import NavBar from "../components/NavBar.jsx";
import Footer from "../components/Footer.jsx";

const MainLayout = () => {
  return (
    <>
      <NavBar />
      <Outlet />
      {/*<TicketDisplay />*/}
      {/*<LogDisplay />*/}
      <Footer />
    </>
  );
};
export default MainLayout;
