const SubmitButton = ({ value, action, bgColor }) => {
  return (
    <input
      onClick={action}
      className="mt-2 cursor-pointer self-start rounded-full border-2 bg-black px-3 text-white"
      value={value}
      type="submit"
    />
  );
};
export default SubmitButton;
