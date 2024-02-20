import React, { useState, ChangeEvent } from "react";
import "./Search.css";

type HandleSearchType = (searchTerm: string) => void;

interface SearchProps {
  handleSearch: HandleSearchType;
}

export const Search: React.FC<SearchProps> = ({ handleSearch }) => {
  const [searchTerm, setSearchTerm] = useState("");

  const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
    setSearchTerm(e.target.value);
    handleSearch(e.target.value);
  };

  return (
    <input
      type="text"
      placeholder="Search..."
      value={searchTerm}
      onChange={handleChange}
    />
  );
};
