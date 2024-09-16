import React, { useEffect, useState } from "react";
import "./App.scss";
import { QuoteApi } from "./types/Quote";
import { Quote } from "./Quotes";

function App() {
  const [allQuotes, setAllQuotes] = useState<QuoteApi[]>([]);
  const [quotes, setQuotes] = useState<QuoteApi[]>([]);
  const [filterList, setFilterList] = useState<string[]>([]);

  const handleTagRemove = (tag: string) => {
    setFilterList(filterList.filter((item) => item !== tag));
  };

  const handleTagClick = (tag: string) => {
    if (!filterList.includes(tag)) setFilterList([...filterList, tag]);
  };

  useEffect(() => {
    if (filterList.length === 0) {
      setQuotes(allQuotes);
    } else {
      const filtered = allQuotes.filter((quote) => {
        return filterList.some((tag) => quote.tags.includes(tag));
      });
      setQuotes(filtered);
    }
  }, [allQuotes, filterList]);

  const fetchData = () => {
    fetch("https://api.quotable.io/quotes/random?limit=3")
      .then((response) => response.json())
      .then((data: QuoteApi[]) => {
        setAllQuotes(data);
      });
  };

  const fetchNewQuote = () => {
    fetch("https://api.quotable.io/quotes/random?limit=1")
      .then((response) => response.json())
      .then((data: QuoteApi[]) => {
        setAllQuotes([...data, ...allQuotes]);
      });
  };

  useEffect(() => {
    fetchData();
  }, []);

  return (
    <div className="containerApp">
      <button id="addQuote" onClick={() => fetchNewQuote()}>
        NEW QUOTE
      </button>
      <div className="filterList">
        <br />
        Filters &nbsp;&nbsp;&nbsp;
        {filterList.map((tag, index) => (
          <span key={index}>
            {tag}&nbsp;
            <button onClick={() => handleTagRemove(tag)}>X</button>
          </span>
        ))}
      </div>
      {quotes.map((quote) => {
        return (
          <Quote
            key={quote._id}
            quote={quote}
            handleTagClick={handleTagClick}
          />
        );
      })}
    </div>
  );
}

export default App;
