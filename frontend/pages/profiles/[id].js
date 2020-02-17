import { Row, Col, Typography, Button } from 'antd';
import { inject, observer } from 'mobx-react';
import React, { useState, Fragment } from 'react';

import { fetchUser } from '../../services/user';
import { redirect } from '../../utils/redirect';
import { User } from '../../stores/models/user';
import { ProfilePicture } from '../../components/profile/ProfilePicture';
import { Margin } from '../../components/Margin';
import { fetchActivitiesByUser, createActivity, deleteActivity } from '../../services/activity';
import { ProfileActivities } from '../../components/profile/ProfileActivities';
import { Activity } from '../../stores/models/activity';
import { fetchFriendshipsForUser, fetchFriendshipRequestsForUser, confirmFriendship, rejectFriendship, createFriendship } from '../../services/friendship';
import { ProfileFriends } from '../../components/profile/ProfileFriends';
import { ProfileFriendshipRequests } from '../../components/profile/ProfileFriendshipRequests';
import { Friendship } from '../../stores/models/friendship';

function Profile(props) {
  const { appStore } = props;

  const user = new User(props.user);
  const [activities, setActivities] = useState(props.activities.map((activity) => new Activity(activity)));
  const [friends, setFriends] = useState(props.friends.map((friend) => new User(friend)));
  const [friendshipRequests, setFriendshipRequests] = useState(props.friendshipRequests.map((friendship) => new Friendship(friendship)));

  const handleCreateActivity = async (value, attachments) => {
    const activity = await createActivity(props.appStore, value, attachments);
    setActivities([activity, ...activities]);
  };

  const handleDeleteActivity = async (id) => {
    await deleteActivity(id);
    setActivities(activities.filter((activity) => activity.id !== id));
  };

  const handleConfirmFriendship = async (friendship) => {
    await confirmFriendship(friendship.id);
    setFriends([...friends, friendship.user]);
    setFriendshipRequests(friendshipRequests.filter((friendshipRequest) => friendshipRequest.id !== friendship.id));
  }

  const handleRejectFriendship = async (friendship) => {
    await rejectFriendship(friendship.id);
    setFriendshipRequests(friendshipRequests.filter((friendshipRequest) => friendshipRequest.id !== friendship.id));
  }

  const onSendFriendshipRequest = async () => {
    await createFriendship(appStore, user);
  }

  const isUserCurrent = appStore.isLoggedIn && user.equals(appStore.currentUser);

  const showAddFriendButton = !isUserCurrent && friends.every((friend) => !friend.equals(appStore.currentUser));

  return (
    <Row gutter={48}>
      <Col span={4}>
        <Margin margin="0 0 16px">
          <ProfilePicture
            src={user.profilePictureUrl}
          />
        </Margin>
        <Typography.Title>
          {user.fullName}
        </Typography.Title>
      </Col>
      <Col span={1} />
      <Col span={9}>
        <ProfileActivities
          showInput={isUserCurrent}
          activities={activities}
          onCreateActivity={handleCreateActivity}
          onDeleteActivity={handleDeleteActivity}
        />
      </Col>
      <Col span={3} />
      <Col span={7}>
        <Margin margin="0 0 24px">
          <Typography.Title level={3}>
            Prijatelji
          </Typography.Title>
          <ProfileFriends
            friends={friends}
            showActions={isUserCurrent}
          />
        </Margin>

        {isUserCurrent && (
          <Fragment>
            <Typography.Title level={3}>
              Zahtjevi za prijateljstvom
            </Typography.Title>
            <ProfileFriendshipRequests
              friendships={friendshipRequests}
              onConfirmFriendship={handleConfirmFriendship}
              onRejectFriendship={handleRejectFriendship}
            />
          </Fragment>
        )}

        {showAddFriendButton && (
          <Button
            block
            onClick={onSendFriendshipRequest}
          >
            Pošalji zahtjev za prijateljstvom
          </Button>
        )}
      </Col>
    </Row>
  );
}

Profile.getInitialProps = async (context) => {
  const { appStore } = context.mobxStore;
  const { id } = context.query;

  if (!appStore.isLoggedIn) {
    await redirect(context, '/login');
  }

  const user = await fetchUser(id);

  if (!user.id) {
    await redirect(context, '/home');
  }

  const activities = await fetchActivitiesByUser(user.id);
  const friends = await fetchFriendshipsForUser(user.id);
  const friendshipRequests = await fetchFriendshipRequestsForUser(user.id);

  return {
    user,
    activities,
    friends,
    friendshipRequests,
  };
}

export default inject('appStore')(observer(Profile));
