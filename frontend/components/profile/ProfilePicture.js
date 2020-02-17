import React from 'react';

export function ProfilePicture(props) {
  return (
    <div
      style={{
        paddingBottom: '100%',
        width: '100%',
        backgroundImage: `url(${props.src})`,
        backgroundSize: 'cover',
        backgroundPosition: '50% 50%',
        border: '5px solid var(--theme-color)',
        borderRadius: '50%',
      }}
    />
  );
}
