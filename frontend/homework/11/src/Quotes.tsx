import { QuoteApi } from "./types/Quote";
import "./Quotes.scss";

interface quoteProps {
  quote: QuoteApi;
  handleTagClick: (tag: string) => void;
}

export function Quote({ quote, handleTagClick }: quoteProps) {
  const handleClick = (tag: string) => {
    handleTagClick(tag);
  };
  return (
    <div className="quote">
      <h2>{quote.content}</h2>
      <span className="details">~{quote.author}</span>
      <span id="date" className="details">{quote.dateAdded}</span>
      <div className="tags">
        {quote.tags.map((t) => {
          return (
            <span key={t} onClick={() => handleClick(t)}>
              {t}
            </span>
          );
        })}
      </div>
    </div>
  );
}
