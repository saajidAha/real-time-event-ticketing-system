import {NavLink} from "react-router-dom";
// NavBar to display all of the route links for smooth page navigation
const NavBar = () => {
    const applyNavClickStyle = ({ isActive }) => ({
        color: isActive ? "yellow" : "white",
    })
    return(
        <div className="flex flex-row justify-evenly py-2 bg-gray-700 ">
            <NavLink to='/' className=" rounded  text-white font-semibold" style={applyNavClickStyle}>Simulation Page</NavLink>
            <NavLink to='/customer' className=" rounded  text-white font-semibold" style={applyNavClickStyle}>Customer Page</NavLink>
            <NavLink to='/vendor' className=" rounded  text-white font-semibold" style={applyNavClickStyle}>Vendor Page</NavLink>
            <NavLink to='/ticketpool' className=" ounded  text-white font-semibold" style={applyNavClickStyle}>TicketPool Initialization</NavLink>
        </div>
    )
}
export default NavBar;