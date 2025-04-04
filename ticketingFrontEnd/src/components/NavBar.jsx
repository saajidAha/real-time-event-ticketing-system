import logo from "/logo3.1.png";
import HorizontalCenterContainer from "./HorizontalCenterContainer.jsx";
import { NavLink } from "react-router-dom";

const NavBar = () => {
  return (
    <HorizontalCenterContainer styles="bg-white/30 backdrop-blur-md shadow-lg fixed top-0 z-10 py-4 md:py-0 rounded-b-xl">
      <nav className="flex w-full max-w-screen-lg flex-col items-center gap-2 md:flex-row md:justify-between">
        <img src={logo} alt="logo" className="h-20 rounded object-contain" />
        <div className="flex w-full justify-around text-black md:justify-end md:gap-12 md:pr-4">
          <div className="hover:text-black">
            <NavLink to="/">Simulation</NavLink>
          </div>
          <div className="hover:text-black">
            <NavLink to="/ticketpool">Initialize</NavLink>
          </div>
          <div className="hover:text-black">
            <NavLink to="/customer">Purchase</NavLink>
          </div>
          <div className="hover:text-black">
            <NavLink to="/vendor">Release</NavLink>
          </div>
        </div>
      </nav>
    </HorizontalCenterContainer>
  );
};
export default NavBar;
