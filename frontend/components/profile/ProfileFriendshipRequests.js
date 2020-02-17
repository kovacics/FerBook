import React from 'react';
import { Typography } from 'antd';
import { FriendshipRequestCard } from '../friendships/FriendshipRequestCard';
import { Margin } from '../Margin';

export function ProfileFriendshipRequests(props) {
  if (!props.friendships.length) {
    return (
      <Typography>
        Nema novih zahtjeva za prijateljstvom
      </Typography>
    );
  }

  return props.friendships.map((friendship) => (
    <Margin
      margin="0 0 16px"
      key={friendship.id}
    >
      <FriendshipRequestCard
        friendship={friendship}
        onConfirmFriendship={props.onConfirmFriendship}
        onRejectFriendship={props.onRejectFriendship}
      />
    </Margin>
  ));
}