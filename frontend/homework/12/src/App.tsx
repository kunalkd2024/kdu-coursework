// App.tsx
import React from 'react';
import './App.scss';
import { SearchContextProvider } from './context/SearchContext';
import { TodoContextProvider } from './context/TodoContext';
import { Header } from './handson_files/Header';
import { MainSection } from './handson_files/MainSection';

const App: React.FC = () => {
  return (
    <div id="containerApp">
      <SearchContextProvider>
        <TodoContextProvider>
          <Header />
          <MainSection />
        </TodoContextProvider>
      </SearchContextProvider>
    </div>
  );
};

export default App;
