const TextField = ({ changeFunction, name, label }) => {
  return (
    <div className="flex">
      <label htmlFor={name} className="w-[50%] font-light">
        {label}:
      </label>
      <input
        onChange={changeFunction}
        required
        type="text"
        name={name}
        className="mr-1 bg-gray-100"
      />
    </div>
  );
};
export default TextField;
