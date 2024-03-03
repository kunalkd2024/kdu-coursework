import { createUseStyles } from "react-jss";
import { Link } from "react-router-dom";
import dashboardStocksIcon from "../../icons/dashboardStocksIcon.png";

export function Header() {
  const classes = useStyles();

  return (
    <div className={classes.headerContainer}>
      <Link to="/dashboard/explore">
        <img
          src={dashboardStocksIcon}
          alt="Dashboard Stocks Icon"
          className={classes.icon}
        />
      </Link>
      <div className={classes.heading}>KDU Stock Market</div>
      <div className={classes.summarizer}>Summarizer</div>
      <Link style={{ color: "white", textDecoration: "none" }} to="/portfolio">
        <div className={classes.portfolio}>My Portfolio</div>
      </Link>
    </div>
  );
}

const useStyles = createUseStyles({
  headerContainer: {
    display: "grid",
    gridTemplateColumns: "3.5% auto 11% 10%",
    alignItems: "center",
    backgroundColor: "#1971c2",
    color: "white",
    padding: "0.6rem",
    border: "2px solid black",
  },
  icon: {
    display: "grid",
    justifyItems: "center",
    alignItems: "center",
    width: "100%",
    height: "auto",
    maxWidth: "2.5rem",
    cursor: "pointer",
  },
  heading: {
    fontSize: "1.8rem",
  },
  summarizer: {
    fontSize: "1.3rem",
  },
  portfolio: {
    fontSize: "1.5rem",
  },
});
