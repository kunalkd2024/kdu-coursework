import { Hobbies } from "./Hobbies";
import { Skills } from "./Skills";
import "./InfoCard.css";

interface skillSet {
  id: number;
  skill: string;
}

interface hobbySet {
  id: number;
  hobby: string;
}

interface data {
  name: string;
  fullName: string;
  qualification: string;
  skills: skillSet[];
  hobbies: hobbySet[];
}

interface dataProps {
  data: data;
}

export function InfoCard({ data }: dataProps) {
  return (
    <>
      <div id="details">
        <span>{data.name}</span>
        <br />
        <span className="detail-item">{data.fullName}</span>
        <br />
        <span>{data.qualification}</span>
      </div>
      <div id="mid-section">
        <div>
          <Skills skills={data.skills} />
        </div>
        <div>
          <Hobbies hobbies={data.hobbies} />
        </div>
      </div>
    </>
  );
}
