import "./Hobbies.css";

interface hobbySet {
  id: number;
  hobby: string;
}

interface hobbyProps {
  hobbies: hobbySet[];
}

export function Hobbies({ hobbies }: hobbyProps) {
  return (
    <>
      <div id="container2">
        <div id="heading">Hobbies</div>
        <div>
          {hobbies.map(({ id, hobby }) => (
            <div key={id}>{hobby}</div>
          ))}
        </div>
      </div>
    </>
  );
}
