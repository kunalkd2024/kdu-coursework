import React from "react";
import AddItem from "./AddItem";
import Items from "./Items";
import "./MainSection.scss";

const MainSection: React.FC = () => {
  return (
    <div className="containerMainSec">
      <AddItem />
      <Items />
    </div>
  );
};

export default MainSection;
