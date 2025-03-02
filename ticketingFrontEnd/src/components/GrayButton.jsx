import axios from "axios";

const GrayButton = ({ action, value }) => {
  return (
    <button
      onClick={action}
      className="cursor-pointer self-start rounded-full bg-gray-200 px-3"
    >
      {value}
    </button>
  );
};
export default GrayButton;
