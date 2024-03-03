import React, { useState, useEffect } from "react";
import {
  Button,
  Checkbox,
  TextField,
  FormControlLabel,
  FormGroup,
  Typography,
  FormControl,
} from "@mui/material";
import { PortfolioTransactionAPI } from "../../types/portfolioTransactionTypes";
import { createUseStyles } from "react-jss";

interface FilterProps {
  transactions: PortfolioTransactionAPI[];
  onFilterChange: (filteredTransactions: PortfolioTransactionAPI[]) => void;
}

const Filter: React.FC<FilterProps> = ({ transactions, onFilterChange }) => {
  const classes = useStyles();
  const [searchTerm, setSearchTerm] = useState("");
  const [startDate, setStartDate] = useState("");
  const [endDate, setEndDate] = useState("");
  const [showPassed, setShowPassed] = useState(false);
  const [showFailed, setShowFailed] = useState(false);
  const [selectedCompanies, setSelectedCompanies] = useState<string[]>([]);
  const [companiesToShow, setCompaniesToShow] = useState<string[]>([]);
  const companiesPerLoad = 8;

  useEffect(() => {
    const uniqueCompanies = Array.from(
      new Set(transactions.map((transaction) => transaction.stock_name))
    ).sort();
    const initialCompanies = uniqueCompanies.slice(0, companiesPerLoad);
    setCompaniesToShow(initialCompanies);
  }, [transactions, companiesPerLoad]);

  useEffect(() => {
    const filteredTransactions = transactions
      .filter((transaction) => {
        const passCondition = showPassed
          ? transaction.status === "Passed"
          : true;
        const failCondition = showFailed
          ? transaction.status === "Failed"
          : true;
        const dateCondition =
          (!startDate ||
            new Date(transaction.timestamp) >= new Date(startDate)) &&
          (!endDate || new Date(transaction.timestamp) <= new Date(endDate));
        const searchCondition =
          transaction.stock_name
            .toLowerCase()
            .includes(searchTerm.toLowerCase()) ||
          transaction.stock_symbol
            .toLowerCase()
            .includes(searchTerm.toLowerCase());
        const companyCondition =
          selectedCompanies.length === 0 ||
          selectedCompanies.includes(transaction.stock_name);
        return (
          passCondition &&
          failCondition &&
          dateCondition &&
          searchCondition &&
          companyCondition
        );
      })
      .sort(
        (a, b) =>
          new Date(b.timestamp).getTime() - new Date(a.timestamp).getTime()
      );

    onFilterChange(filteredTransactions);
  }, [
    searchTerm,
    startDate,
    endDate,
    showPassed,
    showFailed,
    selectedCompanies,
    transactions,
    onFilterChange,
  ]);

  const handleSearchChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setSearchTerm(event.target.value);
  };

  const handleCheckboxChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const { name, checked } = event.target;
    if (name === "passed") {
      setShowPassed(checked);
    } else if (name === "failed") {
      setShowFailed(checked);
    }
  };

  const handleCompanyChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const { value, checked } = event.target;
    if (checked) {
      setSelectedCompanies((prevSelected) => [...prevSelected, value]);
    } else {
      setSelectedCompanies((prevSelected) =>
        prevSelected.filter((company) => company !== value)
      );
    }
  };

  const handleClearAll = () => {
    setSearchTerm("");
    setStartDate("");
    setEndDate("");
    setShowPassed(false);
    setShowFailed(false);
    setSelectedCompanies([]);
  };

  const handleScroll = (event: React.UIEvent<HTMLDivElement>) => {
    const bottom =
      event.currentTarget.scrollHeight - event.currentTarget.scrollTop ===
      event.currentTarget.clientHeight;
    if (bottom) {
      const currentLength = companiesToShow.length;
      const nextCompanies = transactions
        .map((transaction) => transaction.stock_name)
        .filter((company) => !selectedCompanies.includes(company))
        .slice(currentLength, currentLength + companiesPerLoad);
      setCompaniesToShow((prevCompanies) => [
        ...prevCompanies,
        ...nextCompanies,
      ]);
    }
  };

  return (
    <div className={classes.root}>
      <div className={classes.row}>
        <Typography variant="h6" className={classes.heading}>
          Filters
        </Typography>
        <Button onClick={handleClearAll} className={classes.button}>
          Clear All
        </Button>
      </div>
      <div className={classes.row}>
        <TextField
          label="Search for a Stock"
          variant="outlined"
          value={searchTerm}
          onChange={handleSearchChange}
          style={{ width: "100%" }}
        />
      </div>
      <div className={classes.row} style={{ paddingBottom: "10px" }}>
        <FormControl className={classes.formControl}>
          <TextField
            label="Start Date"
            type="date"
            variant="outlined"
            value={startDate}
            style={{ paddingRight: "10px" }}
            onChange={(e) => setStartDate(e.target.value)}
            InputLabelProps={{
              shrink: true,
            }}
            InputProps={{
              placeholder: "",
            }}
          />
        </FormControl>
        <FormControl className={classes.formControl}>
          <TextField
            label="End Date"
            type="date"
            variant="outlined"
            style={{ paddingLeft: "10px" }}
            value={endDate}
            onChange={(e) => setEndDate(e.target.value)}
            InputLabelProps={{
              shrink: true,
            }}
            InputProps={{
              placeholder: "",
            }}
          />
        </FormControl>
      </div>
      <div className={classes.row} style={{ padding: "0 20px" }}>
        <FormGroup>
          <FormControlLabel
            control={
              <Checkbox
                checked={showPassed}
                onChange={handleCheckboxChange}
                name="passed"
              />
            }
            label="Passed"
          />
          <FormControlLabel
            control={
              <Checkbox
                checked={showFailed}
                onChange={handleCheckboxChange}
                name="failed"
              />
            }
            label="Failed"
          />
        </FormGroup>
      </div>
      <div className={classes.row} style={{ borderBottom: "none" }}>
        <div className={classes.scrollableList} onScroll={handleScroll}>
          <FormGroup>
            {companiesToShow.map((company) => (
              <FormControlLabel
                key={company}
                control={
                  <Checkbox
                    checked={selectedCompanies.includes(company)}
                    onChange={handleCompanyChange}
                    value={company}
                  />
                }
                label={company}
              />
            ))}
          </FormGroup>
        </div>
      </div>
    </div>
  );
};

export default Filter;

const useStyles = createUseStyles({
  root: {
    border: "2px solid black",
    position: "fixed",
    borderRadius: "30px",
    backgroundColor: "#e9ecef",
    marginTop: "30px",
    top: 100,
    left: 80,
    paddingBottom: "10px",
    zIndex: 999,
    width: "23%",
    color: "#838586",
  },
  row: {
    display: "flex",
    alignItems: "center",
    borderBottom: "1px solid black",
    padding: "10px 20px",
  },
  heading: {
    flex: "1",
    color: "black",
  },
  button: {
    marginLeft: "auto",
    "&:hover": {
      backgroundColor: "none",
    },
  },
  formControl: {
    width: "100%",
  },
  scrollableList: {
    maxHeight: "200px",
    overflowY: "auto",
    border: "1px solid #ccc",
    borderRadius: "4px",
    padding: "5px",
  },
});
