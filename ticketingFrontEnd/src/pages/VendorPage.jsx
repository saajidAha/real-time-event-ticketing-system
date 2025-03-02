import VendorForm from "../components/VendorForm.jsx";
import HorizontalCenterContainer from "../components/HorizontalCenterContainer.jsx";
import LogRowContainer from "../components/LogRowContainer.jsx";
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
  );
};
export default VendorPage;
