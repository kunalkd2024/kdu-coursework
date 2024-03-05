import { useParams } from "react-router-dom";
import { Header } from "../Utils/Header";
import { useDispatch, useSelector } from "react-redux";
import { AppDispatch, RootState } from "../../redux/store/store";
import { useEffect, useState, useRef } from "react";
import React from "react";
import ArrowUpwardIcon from "@mui/icons-material/ArrowUpward";
import ArrowDownwardIcon from "@mui/icons-material/ArrowDownward";
import { buyStock, sellStock } from "../../redux/slices/UserSlice";
import { addBarToGraph, addToHistory } from "./StockPageUtils";
import SnackbarComponent from "./Snackbar";
import StockDropdown from "./StockDropdown";
import { fetchStocks } from "../../thunks/getStocks";

/**
 * React component representing the stock page.
 *
 * @component StockPage
 * @returns {JSX.Element} A React element representing the stock page component.
 */
export function StockPage() {
  const { stockSymbol } = useParams();
  const dispatch: AppDispatch = useDispatch();
  const { stocks } = useSelector((state: RootState) => state.stocksData);

  const currentStock = stocks.find(
    (stock) => stock.stock_symbol === stockSymbol
  );
  const basePrice = currentStock?.base_price;

  const [price, setPrice] = useState<number | undefined>(basePrice);
  const [prevPrice, setPrevPrice] = useState<number | undefined>(basePrice);
  const [percentageChange, setPercentageChange] = useState<number>(0.0);
  const [quantity, setQuantity] = useState<number>();
  const { walletBalance, owned } = useSelector(
    (state: RootState) => state.userData
  );

  useEffect(() => {
    dispatch(fetchStocks());
  }, [dispatch, stockSymbol]);

  useEffect(() => {
    const interval = setInterval(async () => {
      const newPrice =
        Math.floor(Math.random() * 500) + (basePrice ? basePrice : 0);
      setPrice(newPrice);
      addBarToGraph(price, percentageChange >= 0, basePrice);
      setPrevPrice(price ? price : basePrice);
      let change = price! - prevPrice!;
      change = change / prevPrice!;
      change = change * 100;
      setPercentageChange(change ? change : 0);
    }, 2000);

    return () => clearInterval(interval);
  }, [basePrice, price, prevPrice]);

  const ownedQuantity = owned
    .filter((stock) => stock.stock.stock_name === currentStock?.stock_name)
    .reduce((total, stock) => total + stock.quantity, 0);

  const [snackbarOpen, setSnackbarOpen] = useState(false);
  const [snackbarMessage, setSnackbarMessage] = useState("");
  const historySectionRef = useRef<HTMLDivElement>(null);

  const handleSnackbarClose = () => {
    setSnackbarOpen(false);
  };

  const handleBuy = (quantity: number) => {
    if (!currentStock || !basePrice || quantity <= 0) return;
    if (walletBalance < quantity * basePrice) {
      setSnackbarMessage("Not enough balance");
      setSnackbarOpen(true);
      return;
    }
    dispatch(
      buyStock({
        stock: currentStock,
        quantity,
        type: "buy",
        status: "pass",
      })
    );
    addToHistory(quantity, "BUY", historySectionRef);
  };

  const handleSell = (quantity: number) => {
    if (!currentStock || !basePrice || quantity <= 0) return;
    if (ownedQuantity < quantity) {
      setSnackbarMessage("You don't own this stock or have enough quantity");
      setSnackbarOpen(true);
      return;
    }
    dispatch(
      sellStock({
        stock: currentStock,
        quantity,
        type: "sell",
        status: "pass",
      })
    );
    addToHistory(quantity, "SELL", historySectionRef);
  };

  return (
    <div
      style={{ display: "flex", flexDirection: "column", minHeight: "97vh" }}
    >
      <Header />
      <div
        style={{
          flex: "1",
          display: "grid",
          gridTemplateColumns: "auto 25%",
          gap: "20px",
          marginTop: "20px",
          paddingLeft: "30px",
        }}
      >
        <div style={{ display: "flex", flexDirection: "column" }}>
          <div style={{ display: "flex", height: "50px", gap: "10px" }}>
            <div
              style={{
                flex: "35%",
                border: "1px solid black",
                height: "100%",
                display: "grid",
                gridTemplateColumns: "25% auto",
              }}
            >
              <StockDropdown stocks={stocks} currentStock={currentStock} />
            </div>
            <div
              style={{
                flex: "22%",
                border: "1px solid black",
                height: "100%",
                display: "grid",
                gridTemplateColumns: "30% 50% 20%",
                alignContent: "center",
                justifyContent: "center",
                alignItems: "center",
                justifyItems: "center",
                padding: "0 5px",
              }}
            >
              <div>Price</div>
              <div
                style={{
                  color: percentageChange >= 0 ? "#2f9e44" : "#e03131",
                  display: "flex",
                  alignItems: "center",
                }}
              >
                {price}
                {percentageChange >= 0 ? (
                  <ArrowUpwardIcon
                    style={{ color: "#2f9e44", marginBottom: "4px" }}
                  />
                ) : (
                  <ArrowDownwardIcon
                    style={{ color: "#e03131", marginBottom: "4px" }}
                  />
                )}
              </div>
              <div style={{ fontSize: "small" }}>
                {percentageChange.toFixed(2)}%
              </div>
            </div>
            <div
              style={{
                flex: "13%",
                border: "1px solid black",
                height: "100%",
                display: "grid",
                alignItems: "center",
                justifyItems: "center",
              }}
            >
              <input
                type="number"
                placeholder="Enter Quantity"
                style={{
                  paddingLeft: "15px",
                  height: "30px",
                  border: "none",
                  outline: "none",
                  textAlign: "center",
                }}
                value={quantity}
                onChange={(e) => setQuantity(parseInt(e.target.value))}
              />
            </div>
            <div
              style={{
                flex: "4%",
                border: "1px solid #2f9e44",
                height: "100%",
                display: "grid",
                alignItems: "center",
                justifyItems: "center",
                fontSize: "small",
                backgroundColor: "#b2f2bb",
                color: "#2f9e44",
                cursor: "pointer",
              }}
              onClick={() => handleBuy(quantity ? quantity : 0)}
            >
              BUY
            </div>
            <div
              style={{
                flex: "5%",
                border: "1px solid #e03131",
                height: "100%",
                display: "grid",
                alignItems: "center",
                justifyItems: "center",
                fontSize: "small",
                backgroundColor: "#ffc9c9",
                color: "#e03131",
                cursor: "pointer",
              }}
              onClick={() => handleSell(quantity ? quantity : 0)}
            >
              SELL
            </div>
          </div>
          <div
            className="stockgraph"
            style={{
              border: "1px solid black",
              overflowY: "auto",
              position: "relative",
              marginTop: "20px",
            }}
          >
            <div
              style={{
                display: "grid",
                gridTemplateColumns: "repeat(auto-fill, minmax(100px, 1fr))",
                gridAutoRows: "125px",
                zIndex: 0,
              }}
            >
              {[...Array(4)].map((_, rowIndex) => (
                <React.Fragment key={rowIndex}>
                  {[...Array(10)].map((_, colIndex) => (
                    <div
                      key={`${rowIndex}-${colIndex}`}
                      style={{
                        border: "1px dotted rgba(0, 0, 0, 0.5)",
                      }}
                    ></div>
                  ))}
                </React.Fragment>
              ))}
            </div>
            <div
              className="graphBars"
              style={{
                position: "absolute",
                bottom: 0,
                left: 0,
                display: "flex",
                alignItems: "end",
                zIndex: 1,
              }}
            ></div>
          </div>
          <div
            style={{
              display: "flex",
              justifyContent: "space-between",
              width: "100%",
            }}
          >
            {[...Array(11)].map((_, index) => (
              <div
                key={`scale-${index}`}
                style={{ width: "35px", textAlign: "left", fontSize: "1rem" }}
              >
                {index * 100}
              </div>
            ))}
          </div>
        </div>
        <div style={{ display: "flex", flexDirection: "column", gap: "20px" }}>
          <div
            style={{
              height: "55%",
              border: "1px solid black",
              overflowY: "auto",
              maxHeight: "377px",
            }}
            ref={historySectionRef}
          >
            <div
              style={{
                padding: "10px",
                position: "fixed",
                backgroundColor: "white",
                width: "21.8%",
                paddingBottom: "0",
              }}
            >
              History
            </div>
            <div className="historySection" style={{ marginTop: "50px" }}></div>
          </div>
          <div
            style={{
              height: "45%",
              border: "1px solid black",
              padding: "10px",
              display: "flex",
              flexDirection: "column",
              gap: "10px",
            }}
          >
            <div>
              <span>Sagun bought 500 Zomato</span>
              <br />
              <span style={{ fontSize: "smaller" }}>10:00 AM</span>
            </div>
            <div>
              <span>Aakash bought 500 Zomato</span>
              <br />
              <span style={{ fontSize: "smaller" }}>10:00 AM</span>
            </div>
            <div>
              <span>Amey bought 500 Zomato</span>
              <br />
              <span style={{ fontSize: "smaller" }}>10:00 AM</span>
            </div>
          </div>
        </div>
        <SnackbarComponent
          open={snackbarOpen}
          message={snackbarMessage}
          onClose={handleSnackbarClose}
        />
      </div>
    </div>
  );
}
