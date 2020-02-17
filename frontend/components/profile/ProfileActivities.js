import { Typography } from 'antd';
import { inject, observer } from 'mobx-react';
import React, { Fragment } from 'react';

import { NewActivityForm } from '../activities/NewActivityForm';
import { Margin } from '../Margin';
import { ActivityCard } from '../activities/ActivityCard';

function ProfileActivitiesC(props) {
  return (
    <Fragment>
      {props.showInput && (
        <Margin margin="0 0 48px">
          <NewActivityForm
            onSubmit={props.onCreateActivity}
          />
        </Margin>
      )}

      {props.activities.length ? (
        props.activities.map((activity) => (
          <ActivityCard
            activity={activity}
            onDelete={props.onDeleteActivity}
            key={activity.id}
          />
        ))
      ) : (
        <Typography.Title level={4}>
          Ovaj korisnik još nema aktivnosti
        </Typography.Title>
      )}
    </Fragment>
  );
}

export const ProfileActivities = inject('appStore')(observer(ProfileActivitiesC));
