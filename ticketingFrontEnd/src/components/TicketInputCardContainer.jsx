import Title from "./Title.jsx";
import TextField from "./TextField.jsx";
import BlackButton from "./BlackButton.jsx";

const TicketInputCardContainer = ({ title, label, action, changeFunction }) => {
  return (
    <section className="flex flex-col md:w-[48%]">
      <Title value={title} />
      <form
        className="flex w-full flex-col gap-6 rounded-lg py-3 shadow-lg"
        onSubmit={(event) => event.preventDefault()}
      >
        <TextField label={label} changeFunction={changeFunction} />
        <BlackButton value="Confirm" action={action} />
      </form>
    </section>
  );
};
export default TicketInputCardContainer;
