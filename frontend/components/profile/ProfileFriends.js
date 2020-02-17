import { Typography } from 'antd';
import React from 'react';

import { FriendshipCard } from '../friendships/FriendshipCard';
import { Margin } from '../Margin';

export function ProfileFriends(props) {
  if (!props.friends.length) {
    return (
      <Typography>
        Nemate prijatelja
      </Typography>
    );
  }

  return props.friends.map((friend) => (
    <Margin
      margin="0 0 16px"
      key={friend.id}
    >
      <FriendshipCard
        friend={friend}
        showActions={props.showActions}
      />
    </Margin>
  ));
}
