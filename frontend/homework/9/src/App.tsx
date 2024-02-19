import "./App.css";
import { getData } from "./data/data";
import { InfoCard } from "./handson_files/InfoCard";

function App() {
  const data = getData();

  return (
    <div className="container">
      <InfoCard data = {data} />
    </div>
  )
  
}

export default App;
