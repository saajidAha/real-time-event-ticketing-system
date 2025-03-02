const BlackButton = ({ value, action }) => {
  return (
    <input
      onClick={action}
      className="h-max w-max cursor-pointer rounded-lg bg-black px-3 py-1 text-sm text-white"
      value={value}
      type="submit"
    />
  );
};
export default BlackButton;
