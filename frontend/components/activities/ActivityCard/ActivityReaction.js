import { Tooltip } from 'antd';
import { inject, observer } from 'mobx-react';
import React from 'react';

const reactionIconDictionary = {
  LIKE: '👍',
  LOVE: '❤️',
  HAHA: '😀',
  WOW: '🤯',
  SAD: '😟',
  ANGRY: '😠',
}

function ActivityReactionC({ activity, reaction, appStore, onAdd, onRemove }) {
  const reactions = activity.reactionsFor(reaction);
  const reactionsCount = reactions.length;
  const reactionUsers = reactions.map((reactionModel) => `@${reactionModel.user.username}\r\n`);
  const addedByCurrentUser = reactions.some((reactionModel) => reactionModel.user.equals(appStore.currentUser));

  const handleAdd = () => onAdd(reaction);
  const handleRemove = () => onRemove(reaction);

  return reactionsCount ? (
    <Tooltip
      title={reactionUsers}
      placement="bottom"
      overlayStyle={{
        whiteSpace: 'pre',
      }}
    >
      <div
        style={{
          fontWeight: addedByCurrentUser ? 'bold': 'normal',
          color: addedByCurrentUser ? 'black' : 'inherit',
        }}
        onClick={addedByCurrentUser ? handleRemove : handleAdd}
      >
        {reactionIconDictionary[reaction]} {reactionsCount}
      </div>
    </Tooltip>
  ) : (
    <div
      onClick={handleAdd}
    >
      {reactionIconDictionary[reaction]} {reactionsCount}
    </div>
  )
}

export const ActivityReaction = inject('appStore')(observer(ActivityReactionC));
