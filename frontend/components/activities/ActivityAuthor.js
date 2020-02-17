import { Avatar } from 'antd';
import Link from 'next/link';
import React from 'react';

export function ActivityAuthor(props) {
  return (
    <Link
      href="/profiles/[id]"
      as={`/profiles/${props.user.id}`}
    >
      <a>
        <Avatar
          src={props.user.profilePictureUrl}
          size="small"
        />
        <span
          style={{
            marginLeft: '12px',
          }}
        >
          {props.user.fullName}
        </span>
      </a>
    </Link>
  );
}
