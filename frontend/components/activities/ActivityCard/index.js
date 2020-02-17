import { Card, Typography, Carousel } from 'antd';
import { inject, observer } from 'mobx-react';
import React from 'react';

import { Margin } from '../../Margin';
import { reactions } from '../../../stores/models/reaction';
import { ActivityReaction } from './ActivityReaction';
import { ActivityAuthor } from '../ActivityAuthor';
import { ActivityAction } from './ActivityAction';
import { createReaction, deleteReaction } from '../../../services/reaction';
import Link from 'next/link';

function ActivityCardC({ activity, onDelete, ...props }) {
  const handleAddReaction = async (reactionText) => {
    const reaction = await createReaction(props.appStore, activity, reactionText);
    activity.reactions.push(reaction);
  };

  const handleRemoveReaction = async (reactionText) => {
    const reactionToDelete = activity
      .reactionsFor(reactionText)
      .find((reaction) => reaction.user.equals(props.appStore.currentUser));

    if (reactionToDelete) {
      await deleteReaction(props.appStore, reactionToDelete.id);
      activity.reactions = activity.reactions
        .filter((reaction) => reaction.id !== reactionToDelete.id);
    }
  };

  return (
    <Margin margin="0 0 24px">
      <Card
        actions={reactions.map((reaction) => (
          <ActivityReaction
            activity={activity}
            reaction={reaction}
            onAdd={handleAddReaction}
            onRemove={handleRemoveReaction}
          />
        ))}
        hoverable
        title={<ActivityAuthor user={activity.user} />}
        extra={!props.hideAction && <ActivityAction activity={activity} onDelete={onDelete} />}
      >
        <Typography.Title level={4}>
          {activity.context}
        </Typography.Title>

        {activity.pictures.length > 0 && (
          <Carousel>
            {activity.pictures.map((picture) => (
              <div key={picture.id}>
                <div
                  style={{
                    backgroundImage: `url(${picture.pictureUrl})`,
                    backgroundSize: 'cover',
                    backgroundPosition: '50% 50%',
                    width: '100%',
                    height: '100%',
                    fontSize: '0',
                  }}
                >&nbsp;</div>
              </div>
            ))}
          </Carousel>
        )}

        <Margin margin="16px 0 0">
          <Link
            href="/activities/[id]"
            as={`/activities/${activity.id}`}
          >
            <a>
              {activity.commentsCount} komentara
            </a>
          </Link>
        </Margin>
      </Card>
    </Margin>
  );
}

export const ActivityCard = inject('appStore')(observer(ActivityCardC));
