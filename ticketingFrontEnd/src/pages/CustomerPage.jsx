import CustomerForm from "../components/CustomerForm.jsx";
import HorizontalCenterContainer from "../components/HorizontalCenterContainer.jsx";
import LogRowContainer from "../components/LogRowContainer.jsx";
import LogDisplay from "../components/LogDisplay.jsx";
// page to display all customer related components
const CustomerPage = () => {
  return (
    <HorizontalCenterContainer>
      <LogRowContainer>
        <CustomerForm />
        <LogDisplay />
      </LogRowContainer>
    </HorizontalCenterContainer>
  );
};
export default CustomerPage;
