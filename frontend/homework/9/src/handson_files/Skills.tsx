import "./Skills.css";

interface skillSet {
  id: number;
  skill: string;
}

interface skillProps {
  skills: skillSet[];
}

export function Skills({ skills }: skillProps) {
  return (
    <>
      <div id="container1">
        <div id="heading">Skills</div>
        <div>
          {skills.map(({ id, skill }) => (
            <div key={id}>{skill}</div>
          ))}
        </div>
      </div>
    </>
  );
}
