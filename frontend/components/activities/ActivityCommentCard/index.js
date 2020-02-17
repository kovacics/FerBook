import { Comment } from 'antd';
import { formatDistanceToNow } from 'date-fns';
import { hr } from 'date-fns/locale';
import React from 'react';
import { Margin } from '../../Margin';

export function ActivityCommentCard({ comment }) {
  return (
    <Comment
      content={comment.content}
      avatar={comment.user.profilePictureUrl}
      author={comment.user.username}
      datetime={formatDistanceToNow(new Date(comment.createdAt), {
        addSuffix: true,
        locale: hr,
      })}
      style={{
        transform: 'translate(0, -20px)',
        marginBottom: '-20px',
      }}
    />
  )
}
