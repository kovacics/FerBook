import React from 'react';

export function Margin(props) {
  return (
    <div
      style={{
        margin: props.margin,
      }}
    >
      {props.children}
    </div>
  );
}
