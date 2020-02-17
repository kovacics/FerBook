import React from 'react';

export function Panel(props) {
  return (
    <div className="panel">
      {props.children}

      <style>{`
        .panel {
          max-width: ${props.maxWidth || '500px'};
          margin: 0 auto;
        }
      `}</style>
    </div>
  );
}
