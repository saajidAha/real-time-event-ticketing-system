import {createBrowserRouter, RouterProvider} from "react-router-dom";
import MainLayout from "../layouts/MainLayout.jsx";
import CustomerPage from "../pages/CustomerPage.jsx";
import VendorPage from "../pages/VendorPage.jsx";
import SimulationPage from "../pages/SimulationPage.jsx";

const App = () => {
    // createBrowserRouter specifies the paths and what should be displayed in them;
    const router = createBrowserRouter([
        {
            path: "/",
            element: <MainLayout />,
            children: [
                {
                    path: "/" ,
                    element: <SimulationPage />
                },
                {
                    path: "/customer",
                    element: <CustomerPage />
                },
                {
                    path:"/vendor",
                    element: <VendorPage />
                }
            ]
        }
    ]);

    return (
        <>
            {/*RouterProvider displays whatever is specified in the browserrouter*/}
            <RouterProvider router={router}/>
        </>
    )
}
export default App;