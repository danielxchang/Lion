import { useState } from "react";
import ItemRow from "./ItemRow";
import styles from "./Challenge.module.css";

export default function Challenge() {
  const [totalCost, setTotalCost] = useState(0);
  const [renderedRows, setRenderedRows] = useState(null);
  const [restockAmounts, setRestockAmounts] = useState(null);

  const onLowStockClickHandler = () => {
    fetch("http://localhost:4567/low-stock")
      .then((res) => res.json())
      .then((result) => {
        const items = result.items;
        const itemRows = items.map((item) => (
          <ItemRow
            key={item.sku}
            {...item}
            inputChange={onInputChangeHandler}
          />
        ));
        const restockItems = items.reduce((prevObj, item) => {
          prevObj[item.sku] = 0;
          return prevObj;
        }, {});

        setRenderedRows(itemRows);
        setRestockAmounts(restockItems);
      })
      .catch((err) => console.log(err));
  };

  const onReOrderClickHandler = () => {
    if (!restockAmounts) {
      alert(
        'You must first click "Get Low-Stock Items" and input order amounts!'
      );
      return;
    }

    const data = {
      items: Object.keys(restockAmounts).map((sku) => {
        return {
          sku,
          quantity: restockAmounts[sku],
        };
      }),
    };

    fetch("http://localhost:4567/restock-cost", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
    })
      .then((res) => res.json())
      .then((result) => {
        setTotalCost(result.totalCost);
      })
      .catch((err) => console.log(err));
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
          {renderedRows &&
            (renderedRows.length > 0 ? (
              renderedRows
            ) : (
              <tr>
                <td colSpan="5">NO LOW-STOCK ITEMS!</td>
              </tr>
            ))}
        </tbody>
        <tfoot>
          <tr>
            <td colSpan="5" className={styles.total}>
              Total Cost: ${`${totalCost.toFixed(2)}`}
            </td>
          </tr>
        </tfoot>
      </table>
      <div className={styles.btnGroup}>
        <button
          className={`${styles.lowStockBtn} ${styles.btn}`}
          onClick={onLowStockClickHandler}
        >
          Get Low-Stock Items
        </button>
        <button
          className={`${styles.reorderBtn} ${styles.btn}`}
          onClick={onReOrderClickHandler}
        >
          Determine Re-Order Cost
        </button>
      </div>
    </>
  );
}
