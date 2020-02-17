import { observer, inject } from 'mobx-react';
import React, { Component } from 'react';

import { redirect } from '../../utils/redirect';
import { fetchActivity } from '../../services/activity';
import { Panel } from '../../components/Panel';
import { Activity } from '../../stores/models/activity';
import { ActivityCard } from '../../components/activities/ActivityCard';
import { ActivityCommentCard } from '../../components/activities/ActivityCommentCard';
import { ActivityCommentForm } from '../../components/forms/ActivityCommentForm';
import { createComment } from '../../services/comment';
import { action } from 'mobx';
import { Timeline } from 'antd';
import { Margin } from '../../components/Margin';

@inject('appStore')
@observer
export default class ActivityPage extends Component {
  static async getInitialProps(context) {
    const { appStore } = context.mobxStore;
    const { id } = context.query;
  
    if (!appStore.isLoggedIn) {
      await redirect(context, '/login');
    }
  
    const activity = await fetchActivity(id);
  
    if (!activity.id) {
      await redirect(context, '/home');
    }
  
    return {
      activity,
    };
  }

  activity;

  constructor(props) {
    super(props);

    this.activity = new Activity(this.props.activity);
  }

  @action.bound
  async handleSubmitComment(content) {
    const comment = await createComment(this.props.appStore, this.activity, content);
    this.activity.comments.unshift(comment);
    this.forceUpdate();
  }

  render() {
    return (
      <Panel>
        <ActivityCard
          activity={this.activity}
          hideAction
        />

        <Margin margin="0 0 48px">
          <ActivityCommentForm
            onSubmit={this.handleSubmitComment}
          />
        </Margin>

        <Timeline>
          {this.activity.comments.map((comment) => (
            <Timeline.Item
              key={comment.id}
              color="var(--theme-color)"
            >
              <ActivityCommentCard
                comment={comment}
              />
            </Timeline.Item>
          ))}
        </Timeline>
        <div>

        </div>
      </Panel>
    );
  }
}
