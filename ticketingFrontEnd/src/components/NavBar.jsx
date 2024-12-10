import {NavLink} from "react-router-dom";
// NavBar to display all of the route links for smooth page navigation
const NavBar = () => {
    return(
        <div className="flex flex-row justify-evenly py-2 bg-gray-700 ">
            <NavLink to='/' className=" rounded  text-white font-semibold">Simulation Page</NavLink>
            <NavLink to='/customer' className=" rounded  text-white font-semibold">Customer Page</NavLink>
            <NavLink to='/vendor' className=" rounded  text-white font-semibold">Vendor Page</NavLink>
            <NavLink to='/ticketpool' className=" ounded  text-white font-semibold">TicketPool Initialization</NavLink>
        </div>
    )
}
export default NavBar;