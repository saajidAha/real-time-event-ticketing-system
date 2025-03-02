import CustomerForm from "../components/CustomerForm.jsx";
import HorizontalCenterContainer from "../components/HorizontalCenterContainer.jsx";
import LogRowContainer from "../components/LogRowContainer.jsx";
import TicketPoolForm from "../components/TicketPoolForm.jsx";
import LogDisplay from "../components/LogDisplay.jsx";
// page to display all cusotomer related components
const CustomerPage = () => {
  return (
    <HorizontalCenterContainer>
      <LogRowContainer>
        <CustomerForm />
        <LogDisplay />
      </LogRowContainer>
    </HorizontalCenterContainer>
    // <div className="inline-flex flex-col">
    //     <CustomerForm />
    // </div>
  );
};
export default CustomerPage;
