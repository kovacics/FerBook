import { Avatar, Card, Typography } from 'antd';
import React from 'react';

import { Margin } from '../Margin';

export function ChatList({ chats, activeChat, onSelectChat }) {
  return chats.map((chat) => (
    <Margin margin="0 0 16px" key={chat.id}>
      <Card
        hoverable
        onClick={() => onSelectChat(chat)}
      >
        <Avatar />&nbsp;&nbsp;&nbsp;&nbsp;
        <Typography.Text>
          {chat.name}
        </Typography.Text>
      </Card>
    </Margin>
  ));
}
