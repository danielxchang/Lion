import { useRef } from "react";
import classes from "./ItemRow.module.css";

export default function ItemRow(props) {
  const inputRef = useRef();
  const maxAllowed = props.capacity - props.stock;

  const onChangeHandler = () => {
    if (+inputRef.current.value > maxAllowed) {
      inputRef.current.value = maxAllowed;
    } else if (+inputRef.current.value < 0) {
      inputRef.current.value = 0;
    }
    props.inputChange(props.sku, +inputRef.current.value);
  };

  return (
    <tr>
      <td>{props.sku}</td>
      <td>{props.name}</td>
      <td>{props.stock}</td>
      <td>{props.capacity}</td>
      <td>
        <input
          className={classes.orderInput}
          type="number"
          min="0"
          max={`${maxAllowed}`}
          ref={inputRef}
          onChange={onChangeHandler}
        />
      </td>
    </tr>
  );
}
