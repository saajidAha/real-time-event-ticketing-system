const BlackButton = ({ value, action }) => {
  return (
    <input
      onClick={action}
      className="cursor-pointer self-start rounded-full bg-black px-3 text-white"
      value={value}
      type="submit"
    />
  );
};
export default BlackButton;
