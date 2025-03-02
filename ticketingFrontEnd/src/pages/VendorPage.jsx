import VendorForm from "../components/VendorForm.jsx";
import TicketInputCardContainer from "../components/TicketInputCardContainer.jsx";
import HorizontalCenterContainer from "../components/HorizontalCenterContainer.jsx";
import LogRowContainer from "../components/LogRowContainer.jsx";
import CustomerForm from "../components/CustomerForm.jsx";
import LogDisplay from "../components/LogDisplay.jsx";
// page to display all vendor related components
const VendorPage = () => {
  return (
    <HorizontalCenterContainer>
      <LogRowContainer>
        <VendorForm />
        <LogDisplay />
      </LogRowContainer>
    </HorizontalCenterContainer>
    // <div className="inline-flex flex-col">
    //     <VendorForm />
    // </div>
  );
};
export default VendorPage;
