import Title from "./Title.jsx";
import TextField from "./TextField.jsx";
import SubmitButton from "./SubmitButton.jsx";

const TicketInputCardContainer = ({ title, label, action, changeFunction }) => {
  return (
    <section className="flex flex-col md:w-[48%]">
      <Title value={title} />
      <form
        className="flex w-full flex-col gap-2 py-4 shadow-lg"
        onSubmit={(event) => event.preventDefault()}
      >
        <TextField label={label} changeFunction={changeFunction} />
        <SubmitButton value="Confirm" action={action} />
      </form>
    </section>
  );
};
export default TicketInputCardContainer;
