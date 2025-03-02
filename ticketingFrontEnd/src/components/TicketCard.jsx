const TicketCard = ({ id = "#SAMPLE 001", title = "Ticket" }) => {
  return (
    <section className="flex w-full flex-col items-center rounded-lg shadow-lg">
      <div className="w-full rounded-t-lg bg-black px-2 text-center text-white">
        {title}
      </div>
      <div>ID:</div>
      <div>{id}</div>
    </section>
  );
};
export default TicketCard;
