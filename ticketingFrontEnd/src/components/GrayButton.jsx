const GrayButton = ({ action, value }) => {
  return (
    <input
      className="h-max w-max cursor-pointer rounded-lg bg-gray-200 px-3 py-1 text-sm"
      onClick={action}
      value={value}
      type="submit"
    />
  );
};
export default GrayButton;
