const HorizontalCenterContainer = ({ children, styles = "" }) => {
  return (
    <section className={`flex w-full justify-center ${styles}`}>
      {children}
    </section>
  );
};
export default HorizontalCenterContainer;
