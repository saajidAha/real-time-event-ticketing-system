import { Outlet } from "react-router-dom";
import NavBar from "../components/NavBar.jsx";
import Footer from "../components/Footer.jsx";
import TicketCardContainer from "../components/TicketCardContainer.jsx";

const MainLayout = () => {
  return (
    <>
      <NavBar />
      <Outlet />
      <TicketCardContainer />
      <Footer />
    </>
  );
};
export default MainLayout;
