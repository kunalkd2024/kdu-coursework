import { createAsyncThunk } from "@reduxjs/toolkit";
import { Rooms } from "../types/type";

export const data = [
  {
    id: 1,
    name: "Single Room",
    costPerNight: "5000",
    currency: "INR",
    addOns: [
      {
        name: "Breakfast",
        cost: "300",
        currency: "INR",
      },
      {
        name: "Extra Bed",
        cost: "2000",
        currency: "INR",
      },
    ],
  },
  {
    id: 2,
    name: "Twin Room",
    costPerNight: "7000",
    currency: "INR",
    addOns: [
      {
        name: "Breakfast",
        cost: "300",
        currency: "INR",
      },
      {
        name: "Balcony Unit",
        cost: "2000",
        currency: "INR",
      },
    ],
  },
  {
    id: 3,
    name: "Deluxe",
    costPerNight: "10000",
    currency: "INR",
    addOns: [
      {
        name: "Breakfast",
        cost: "300",
        currency: "INR",
      },
      {
        name: "Balcony Unit",
        cost: "2000",
        currency: "INR",
      },
      {
        name: "Sea Facing",
        cost: "3000",
        currency: "INR",
      },
    ],
  },
  {
    id: 4,
    name: "Presidential Suite",
    costPerNight: "12000",
    currency: "INR",
    addOns: [
      {
        name: "Breakfast",
        cost: "300",
        currency: "INR",
      },
      {
        name: "Pent House Unit",
        cost: "8000",
        currency: "INR",
      },
      {
        name: "Limousine Service",
        cost: "15000",
        currency: "INR",
      },
    ],
  },
];

export const getRooms = createAsyncThunk<Rooms[]>("getRooms", async () => {
  try {
    const response = await fetch(
      "https://kdu-automation.s3.ap-south-1.amazonaws.com/mini-project-apis/assessment-3.json"
    );
    const data = await response.json();
    return data;
  } catch {
    console.log("Error while making API call.");
    throw "Error while making API call.";
  }
});
