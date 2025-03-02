const SubmitButton = ({ value, action, bgColor }) => {
  return (
    <input
      onClick={action}
      className="mt-2 cursor-pointer rounded-full border-2 px-3 font-medium text-white"
      value={value}
      type="submit"
    />
  );
};
export default SubmitButton;
