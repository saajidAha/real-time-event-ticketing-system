import HorizontalCenterContainer from "./HorizontalCenterContainer.jsx";
import Icon from "./Icon.jsx";
import logo from "/logo3.1.png";
// import { faCopyright } from "@fortawesome/free-solid-svg-icons";
// import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

const Footer = () => {
  const date = new Date();
  return (
    <HorizontalCenterContainer styles="rounded-xl shadow-xl z-20">
      <footer className="mb-4 flex w-full max-w-screen-lg flex-col items-center justify-between md:mb-0 md:flex-row-reverse">
        <Icon iconSrc={logo} />
        <div className="flex flex-col items-center justify-around gap-2">
          <div className="text-center">
            {/*<FontAwesomeIcon icon={faCopyright} className="text-purple-800" />{" "}*/}
            Copyright Saajid Ahamed
          </div>
          <div>All Rights Reserved.</div>
          <div>
            {date.getDate()} {date.toLocaleString("en-US", { month: "long" })}{" "}
            {date.getFullYear()}
          </div>
        </div>
      </footer>
    </HorizontalCenterContainer>
  );
};
export default Footer;
