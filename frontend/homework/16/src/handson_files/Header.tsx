import { useDispatch } from "react-redux";
import "./Header.scss";
import { search } from "../redux/ToDoListSlice";

export const Header: React.FC = () => {
  const dispatch = useDispatch();

  const handleSearch = (term: string) => {
    dispatch(search(term));
  };

  return (
    <div className="containerHeader">
      <div>
        <h1 id="heading">Item Lister</h1>
      </div>
      <div id="search">
        <input
          type="text"
          placeholder="Search Items..."
          onChange={(e) => handleSearch(e.target.value)}
        />
      </div>
    </div>
  );
}