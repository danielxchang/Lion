import { useState } from "react";
import ItemRow from "./ItemRow";

const sampleRow = {
  sku: "12345",
  itemName: "Gummies",
  inStock: 10,
  capacity: 30,
};

export default function Challenge() {
  const [totalCost, setTotalCost] = useState(null);
  const [renderedRows, setRenderedRows] = useState(null);
  const [restockAmounts, setRestockAmounts] = useState({});

  const onLowStockClickHandler = () => {
    // TODO: Implement requests out to low stock backend endpoint
    const testRows = [
      <ItemRow
        key={sampleRow.sku}
        {...sampleRow}
        inputChange={onInputChangeHandler}
      />,
    ];
    setRenderedRows(testRows);
  };

  const onReOrderClickHandler = () => {
    // TODO: Implement requests out to reorder backend endpoint
    console.log(restockAmounts);
    setTotalCost(0);
  };

  const onInputChangeHandler = (sku, amount) => {
    setRestockAmounts((prevAmounts) => {
      prevAmounts[sku] = amount;
      return prevAmounts;
    });
  };

  return (
    <>
      <table>
        <thead>
          <tr>
            <td>SKU</td>
            <td>Item Name</td>
            <td>Amount in Stock</td>
            <td>Capacity</td>
            <td>Order Amount</td>
          </tr>
        </thead>
        <tbody>
          {renderedRows}
          {/* 
          TODO: Create an <ItemRow /> component that's rendered for every inventory item. The component
          will need an input element in the Order Amount column that will take in the order amount and 
          update the application state appropriately.
          */}
        </tbody>
      </table>
      {/* TODO: Display total cost returned from the server */}
      <div>
        Total Cost: {totalCost === null ? "TBD" : `${totalCost.toFixed(2)}`}
      </div>
      {/* 
      TODO: Add event handlers to these buttons that use the Java API to perform their relative actions.
      */}
      <button onClick={onLowStockClickHandler}>Get Low-Stock Items</button>
      <button onClick={onReOrderClickHandler}>Determine Re-Order Cost</button>
    </>
  );
}
