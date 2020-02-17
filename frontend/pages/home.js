import { Row, Col, Typography } from 'antd';
import { inject, observer } from 'mobx-react';
import React, { Fragment } from 'react';

import { fetchActivitiesByUserFriends } from '../services/activity';
import { redirect } from '../utils/redirect';
import { Activity } from '../stores/models/activity';
import { ActivityCard } from '../components/activities/ActivityCard';

function Home(props) {
  const activities = props.activities.map((activity) => new Activity(activity));

  return (
    <Row>
      <Col span={7} />
      <Col span={10}>
        {activities.length ? (
          <Fragment>
            <Typography.Title level={4}>
              Najnovije aktivnosti
            </Typography.Title>
            {activities.map((activity) => (
              <ActivityCard
                activity={activity}
                key={activity.id}
              />
            ))}
          </Fragment>

        ) : (
          <Typography.Title level={4}>
            Nema novih aktivnosti
          </Typography.Title>
        )}
      </Col>
      <Col span={7} />
    </Row>
  );
}

Home.getInitialProps = async (context) => {
  const { appStore } = context.mobxStore;

  if (!appStore.isLoggedIn) {
    await redirect(context, '/login');
  }

  const activities = await fetchActivitiesByUserFriends(appStore.currentUser.id);

  return {
    activities,
  };
}

export default inject('appStore')(observer(Home));
