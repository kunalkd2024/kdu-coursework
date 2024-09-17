import React from 'react';
import './Header.scss';
import { useSearchContext } from '../context/SearchContext';

export const Header: React.FC = () => {
  const { searchTerm, setSearchTerm } = useSearchContext();

  const handleSearch = (e: React.ChangeEvent<HTMLInputElement>) => {
    setSearchTerm(e.target.value);
  };

  return (
    <div id="containerHeader">
      <div>
        <h1 id="heading">Item Lister</h1>
      </div>
      <div id="search">
        <input
          type="text"
          placeholder="Search Items..."
          value={searchTerm}
          onChange={handleSearch}
        />
      </div>
    </div>
  );
};
