import { Header } from "./handson_files/Header";
import MainSection from "./handson_files/MainSection";
import "./App.scss";

export function TodoApp() {
  return (
    <div id="containerApp">
      <Header />
      <MainSection />
    </div>
  );
}

export default TodoApp;
