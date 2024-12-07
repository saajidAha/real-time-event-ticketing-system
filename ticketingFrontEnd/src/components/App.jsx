import {createBrowserRouter, RouterProvider} from "react-router-dom";
import MainLayout from "../layouts/MainLayout.jsx";

const App = () => {
    // createBrowserRouter specifies the paths and what should be displayed in them;
    const router = createBrowserRouter([
        {
            path: "/",
            element: <MainLayout />,
            children: [
                {
                    path: "/" ,
                    element: <h1>Home Page</h1>
                },
                {
                    path: "/customer",
                    element: <h1>Customer Page</h1>
                },
                {
                    path:"/vendor",
                    element: <h1>Vendor Page</h1>
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