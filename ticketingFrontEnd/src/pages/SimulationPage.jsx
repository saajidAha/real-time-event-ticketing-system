import ConfigurationForm from "../components/ConfigurationForm.jsx";
import LogDisplay from "../components/LogDisplay.jsx";
import HorizontalCenterContainer from "../components/HorizontalCenterContainer.jsx";
import LogRowContainer from "../components/LogRowContainer.jsx";
// page to display all simulator related components
const SimulationPage = () => {
  return (
    <HorizontalCenterContainer>
      <LogRowContainer>
        <ConfigurationForm />
        <LogDisplay />
      </LogRowContainer>
    </HorizontalCenterContainer>
  );
};
export default SimulationPage;
