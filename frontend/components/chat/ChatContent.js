import { Comment, Avatar, Form, Button, List, Input, Divider } from 'antd';
import { observer, inject } from 'mobx-react';
import React, { Component, createRef } from 'react';

import { Margin } from '../Margin';

const { TextArea } = Input;

const CommentList = observer(({ comments, user }) => (
  <List
    dataSource={comments}
    itemLayout="horizontal"
    renderItem={props => (
      <Margin margin="0 0 5px">
        <div style={{ textAlign: props.chatUser.username === user.username ? 'right' : 'left' }}>
          <div
            style={{
              backgroundColor: 'rgba(0,0,255,0.03)',
              borderRadius: '5px',
              display: 'inline-block',
              textAlign: 'left',
              padding: '12px',
              whiteSpace: 'pre',
            }}
          >
            {props.chatUser.username !== user.username ? (
              <div
                style={{
                  fontSize: '12px',
                  fontWeight: 'bold',
                }}
              >
                {props.chatUser.username}
              </div>
            ) : null}
            {props.content}
          </div>
        </div>
      </Margin>
    )}
  />
));

const Editor = ({ onChange, onSubmit, value }) => (
  <div>
    <Form.Item style={{ marginBottom: '12px' }}>
      <TextArea
        onChange={onChange}
        value={value}
        autoSize={{ minRows: 2, maxRows: 2 }}
        placeholder="Unesi poruku"
      />
    </Form.Item>
    <Form.Item style={{ marginBottom: '0' }}>
      <Button
        htmlType="submit"
        disabled={!value}
        onClick={onSubmit}
        type="primary"
      >
        Pošalji poruku
      </Button>
    </Form.Item>
  </div>
);

@inject('appStore')
@observer
export class ChatContent extends Component {
  state = {
    value: '',
  };

  listRef;

  constructor(props) {
    super(props);

    this.listRef = createRef();
  }

  handleSubmit = () => {
    if (!this.state.value) {
      return;
    }

    this.props.onSubmitMessage(this.state.value);
  };

  handleChange = e => {
    this.setState({ value: e.target.value });
  };

  componentDidUpdate() {
    if (this.listRef) {
      this.listRef.current.scrollIntoView({ behavior: "smooth" });
    }
  }

  render() {
    const { value } = this.state;
    const { appStore, chat } = this.props;

    return (
      <div
        style={{
          display: 'flex',
          flexDirection: 'column',
          height: '100%',
        }}
      >
        <div
          style={{
            flexGrow: '1',
            maxHeight: '100%',
            overflow: 'auto',
          }}
        >
          {chat.chatMessages.length > 0 && (
            <CommentList
              comments={chat.chatMessages}
              user={appStore.currentUser}
              key={chat.chatMessages.length}
            />
          )}
          <div ref={this.listRef} />
        </div>
        <Divider />
        <div>
          <Comment
            avatar={
              <Avatar
                src={appStore.currentUser.profilePictureUrl}
                alt="Han Solo"
              />
            }
            content={(
              <Editor
                onChange={this.handleChange}
                onSubmit={this.handleSubmit}
                value={value}
              />
            )}
          />
        </div>
      </div>
    );
  }
}
