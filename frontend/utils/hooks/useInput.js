import { useState } from 'react';

export function useInput(defaultValue = '') {
  const [value, setValue] = useState(defaultValue);

  const handleValueChange = (value) => {
    if (typeof value === "string") {
      setValue(value);
    } else {
      setValue(event.target.value);
    }
  }

  return [
    value,
    handleValueChange,
  ];
}