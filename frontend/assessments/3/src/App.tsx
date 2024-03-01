import { useEffect, useState } from "react";
import { AppDispatch } from "./redux/Store";
import "./App.css";
import { useDispatch, useSelector } from "react-redux";
import { data, getRooms } from "./thunks/getRooms";
import { RootState } from "./redux/Store";
import { setError, setLoading, setMessage } from "./redux/RoomSlice";
import { Rooms } from "./types/type";
import { createUseStyles } from "react-jss";

function App() {
  const dispatch: AppDispatch = useDispatch();
  const rooms: Rooms[] = useSelector((state: RootState) => state.rooms.rooms);
  const [roomSelected, setRoomSelected] = useState<string>("None");
  const [addons, setAddons] = useState<string[]>([]);

  useEffect(() => {
    const getData = async () => {
      try {
        const action = await dispatch(getRooms());
        if (getRooms.fulfilled.match(action)) {
          dispatch(setLoading(false));
          dispatch(setMessage("Rooms are loaded"));
        } else {
          dispatch(setMessage("Rooms are not loaded"));
          dispatch(setError("Error fetching data."));
          setLoading(false);
        }
      } catch (error) {
        console.error("Error fetching products", error);
        setError("Error fetching products");
        setLoading(false);
      }
    };
    getData();
  }, [dispatch]);

  const handleOnClick = (name: string) => {
    setRoomSelected(name);
  };

  const useStyles = createUseStyles({
    heading: {
      display: "grid",
      justifyContent: "center",
    },
    roomOption: {
      border: "1px solid black",
      marginRight: "10px",
      marginTop: "10px",
      padding: "20px 100px",
    },
    roomContainer: {
      display: "flex",
      flexDirection: "row",
    },
    label: {
      color: "white",
      backgroundColor: "#f08a5d",
      fontWeight: "900",
      padding: "10px",
    },
    section: {
      paddingBottom: "60px",
    },
    date: {
      border: "1px solid black",
      marginRight: "10px",
      marginTop: "10px",
      padding: "10px",
      paddingRight: "230px",
      fontSize: "10px",
      fontWeight: "800",
    },
    submit: {
      color: "white",
      backgroundColor: "#f08a5d",
      fontWeight: "900",
      padding: "20px 40px",
      width: "100px",
      display: "grid",
      justifyContent: "center",
    },
    roomSelect: {
      border: "1px solid #f08a5d",
      marginRight: "10px",
      marginTop: "10px",
      padding: "20px 100px",
      color: "#f08a5d",
    },
  });

  useEffect(() => {
    console.log(roomSelected);
    const current = data.filter((room) => room.name === roomSelected);
    const adds = current.map(curr => curr.addOns)
  }, [roomSelected]);

  const x = data.filter((room) => room.name === roomSelected);
  console.log(x.map(room => room.addOns));

  const classes = useStyles();
  return (
    <>
      <h3 className={classes.heading}>Hotel Booking</h3>
      <div className={classes.section}>
        <div className={classes.label}>Select Room Type</div>
        <div className={classes.roomContainer}>
          {data.map((room) => {
            return (
              <div
                className={
                  roomSelected === "None"
                    ? classes.roomOption
                    : classes.roomSelect
                }
                onClick={() => handleOnClick(room.name)}
              >
                {room.name}
              </div>
            );
          })}
        </div>
      </div>
      <div className={classes.section}>
        <div className={classes.label}>Select Date</div>
        <div className={classes.roomContainer}>
          <div className={classes.date}>26/03/2023</div>
          <div className={classes.date}>27/03/2023</div>
        </div>
      </div>
      <div className={classes.section}>
        <div className={classes.label}>
          Select additional add ons/ preferences
        </div>
        <div className={classes.roomContainer}>
          <div className={classes.roomOption}>Breakfast</div>
          <div className={classes.roomOption}>Balcony Unit</div>
          <div className={classes.roomOption}>Sea-facing</div>
        </div>
      </div>
      <div>
        <div className={classes.submit}>Submit</div>
      </div>
    </>
  );
}

export default App;
