import { useRef } from "react";

export default function ItemRow(props) {
  const inputRef = useRef();
  const maxAllowed = props.capacity - props.inStock;

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
      <td>{props.itemName}</td>
      <td>{props.inStock}</td>
      <td>{props.capacity}</td>
      <td>
        <input
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
