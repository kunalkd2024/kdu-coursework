import { useDispatch, useSelector } from "react-redux";
import { AppDispatch, RootState } from "../../redux/store/store";
import { Header } from "../Utils/Header";
import { useEffect, useState } from "react";
import { fetchPortfolioTransactions } from "../../thunks/getPortfolioTransactions";
import { CircularProgress, Button } from "@mui/material";
import { createUseStyles } from "react-jss";
import Filter from "./Filter";

export function Portfolio() {
  const dispatch: AppDispatch = useDispatch();
  const { transactions: allTransactions, loading } = useSelector(
    (state: RootState) => state.portfolioData
  );
  const initialDateCount = 5;
  const [visibleDateCount, setVisibleDateCount] = useState(initialDateCount);
  const [visibleTransactions, setVisibleTransactions] =
    useState(allTransactions);

  useEffect(() => {
    const delay = setTimeout(() => {
      dispatch(fetchPortfolioTransactions());
    }, 1500);

    return () => clearTimeout(delay);
  }, []);

  const groupedTransactions: { [date: string]: any[] } = {};
  visibleTransactions.forEach((transaction) => {
    const date = new Date(transaction.timestamp).toLocaleDateString(undefined, {
      day: "numeric",
      month: "short",
      year: "numeric",
    });
    if (!groupedTransactions[date]) {
      groupedTransactions[date] = [];
    }
    groupedTransactions[date].push(transaction);
  });

  const handleFilterChange = (filteredTransactions: any[]) => {
    setVisibleTransactions(filteredTransactions);
  };

  const handleLoadMore = () => {
    setVisibleDateCount((prevCount) => prevCount + initialDateCount);
  };

  const classes = useStyles();

  return (
    <div>
      <Header />
      {loading === "pending" ? (
        <div
          style={{
            display: "flex",
            justifyContent: "center",
            marginTop: "50px",
          }}
        >
          <CircularProgress size={80} />
        </div>
      ) : (
        <div>
          <div className={classes.mainSection}>
            <Filter
              transactions={allTransactions}
              onFilterChange={handleFilterChange}
            />
            <div className={classes.transactionsSection}>
              <div>
                {Object.entries(groupedTransactions)
                  .slice(0, visibleDateCount)
                  .map(([date, visibleTransactions]) => (
                    <div key={date} style={{ marginTop: "30px" }}>
                      <div className={classes.date}>{date}</div>
                      {visibleTransactions
                        .sort((a, b) => {
                          const timeA = new Date(a.timestamp).getTime();
                          const timeB = new Date(b.timestamp).getTime();
                          return timeB - timeA;
                        })
                        .map((transaction, index) => (
                          <div key={index} className={classes.transactionRow}>
                            <div>
                              <p className={classes.transactionInfo}>
                                {transaction.stock_name}
                              </p>
                            </div>
                            <p
                              className={classes.transactionInfo}
                              style={{ color: "black" }}
                            >
                              {transaction.stock_symbol}
                            </p>
                            <p className={classes.transactionInfo}>
                              ₹{transaction.transaction_price}
                            </p>
                            <p className={classes.transactionInfo}>
                              {new Date(
                                transaction.timestamp
                              ).toLocaleTimeString([], {
                                hour: "numeric",
                                minute: "2-digit",
                                hour12: true,
                              })}
                            </p>
                            <div className={classes.transactionInfo}>
                              {transaction.status === "Passed" ? (
                                <div
                                  className={`${classes.dot} ${classes.passedDot}`}
                                ></div>
                              ) : (
                                <div
                                  className={`${classes.dot} ${classes.failedDot}`}
                                ></div>
                              )}
                            </div>
                          </div>
                        ))}
                    </div>
                  ))}
              </div>
            </div>
          </div>
          {Object.keys(groupedTransactions).length > visibleDateCount && (
            <div className={classes.loadMoreButton}>
              <Button
                variant="contained"
                onClick={handleLoadMore}
                style={{ backgroundColor: "#1971c2" }}
              >
                Load More
              </Button>
            </div>
          )}
        </div>
      )}
    </div>
  );
}

const useStyles = createUseStyles({
  mainSection: {
    display: "grid",
    gridTemplateColumns: "2fr",
    paddingLeft: "30rem",
    marginTop: "1rem",
  },
  transactionsSection: {
    maxWidth: "92%",
  },
  transactionInfo: {
    margin: 0,
    fontSize: "smaller",
  },
  date: {
    margin: 0,
    fontSize: "1rem",
    borderBottom: "1px dashed #8b8b8c",
    color: "#8b8b8c",
    padding: "10px 0",
  },
  transactionRow: {
    display: "grid",
    gridTemplateColumns: "33% 20% 35% 10% 2.5%",
    padding: "1.2rem 2px",
    borderBottom: "1px solid #5f5f60",
    color: "#5f5f60",
  },
  loadMoreButton: {
    display: "flex",
    justifyContent: "center",
    marginTop: "20px",
  },
  dot: {
    height: "10px",
    width: "10px",
    borderRadius: "50%",
    display: "inline-block",
    marginRight: "5px",
  },
  passedDot: {
    backgroundColor: "#6bb97a",
  },
  failedDot: {
    backgroundColor: "#e76d6d",
  },
});
