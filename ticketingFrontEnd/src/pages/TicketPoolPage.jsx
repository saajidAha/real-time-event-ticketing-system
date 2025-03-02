import TicketPoolForm from "../components/TicketPoolForm.jsx";
import LogRowContainer from "../components/LogRowContainer.jsx";
import LogDisplay from "../components/LogDisplay.jsx";
import HorizontalCenterContainer from "../components/HorizontalCenterContainer.jsx";
// page to display all ticketpool related components
const TicketPoolPage = () => {
  return (
    <HorizontalCenterContainer>
      <LogRowContainer>
        <TicketPoolForm />
        <LogDisplay />
      </LogRowContainer>
    </HorizontalCenterContainer>
  );
};
export default TicketPoolPage;
