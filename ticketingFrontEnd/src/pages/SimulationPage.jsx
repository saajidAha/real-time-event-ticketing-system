import ConfigurationForm from "../components/ConfigurationForm.jsx";
import LogDisplay from "../components/LogDisplay.jsx";
import HorizontalCenterContainer from "../components/HorizontalCenterContainer.jsx";
// page to display all simulator related components
const SimulationPage = () => {
  return (
    <HorizontalCenterContainer>
      <section className="mt-42 flex w-[95%] max-w-screen-lg flex-col gap-8 md:mt-28 md:flex-row md:justify-between md:gap-0">
        <ConfigurationForm />
        <LogDisplay />
      </section>
    </HorizontalCenterContainer>
  );
};
export default SimulationPage;
