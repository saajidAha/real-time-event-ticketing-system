import { NavLink } from "react-router-dom";
// NavBar to display all of the route links for smooth page navigation
const NavBar = () => {
  const applyNavClickStyle = ({ isActive }) => ({
    color: isActive ? "yellow" : "white",
  });
  return (
    <div className="flex flex-row justify-evenly bg-gray-700 py-2">
      <NavLink
        to="/"
        className="rounded font-semibold text-white"
        style={applyNavClickStyle}
      >
        Simulation Page
      </NavLink>
      <NavLink
        to="/customer"
        className="rounded font-semibold text-white"
        style={applyNavClickStyle}
      >
        Customer Page
      </NavLink>
      <NavLink
        to="/vendor"
        className="rounded font-semibold text-white"
        style={applyNavClickStyle}
      >
        Vendor Page
      </NavLink>
      <NavLink
        to="/ticketpool"
        className="ounded font-semibold text-white"
        style={applyNavClickStyle}
      >
        TicketPool Initialization
      </NavLink>
    </div>
  );
};
export default NavBar;
