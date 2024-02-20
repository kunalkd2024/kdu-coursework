import React from "react";
import "./Header.css";

interface HeaderProps {
  onSearch: (term: string) => void;
}

const Header: React.FC<HeaderProps> = ({ onSearch }) => {
  return (
    <div id="containerHeader">
      <div>
        <h1 id="heading">Item Lister</h1>
      </div>
      <div id="search">
        <input
          type="text"
          placeholder="Search Items..."
          onChange={(e) => onSearch(e.target.value)}
        />
      </div>
    </div>
  );
};

export default Header;
