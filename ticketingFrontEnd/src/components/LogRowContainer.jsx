//This container is just for the purpose of containing the log display grid Container with another component. for example the simulation panel and log container, or the ticket purchase component and the log container behave in the same way for responsive designs. hence without writing duplicate code i just created a container out of it.
const LogRowContainer = ({ children }) => {
  return (
    <section className="mt-42 flex w-[95%] max-w-screen-lg flex-col gap-8 md:mt-28 md:flex-row md:justify-between md:gap-0">
      {children}
    </section>
  );
};
export default LogRowContainer;
