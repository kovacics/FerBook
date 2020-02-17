import { Layout, Typography, Button, Row } from 'antd';
import { observable, action } from 'mobx';
import { observer, inject } from 'mobx-react';
import React, { Component, Fragment } from 'react';

import { ChatList } from '../components/chat/ChatList';
import { ChatContent } from '../components/chat/ChatContent';
import { redirect } from '../utils/redirect';
import { createChat, fetchChatsForUser } from '../services/chat';
import { NewChatModal } from '../components/chat/NewChatModal';
import { ChatSocket } from '../services/chat-socket';

@inject('appStore')
@observer
export default class ChatPage extends Component {
  static async getInitialProps(context) {
    const { appStore } = context.mobxStore;

    if (!appStore.isLoggedIn) {
      await redirect(context, '/login');
    }

    return {};
  }

  @observable
  componentState = {
    activeChat: null,
    newChatModalOpen: false,
    chats: [],
  };

  socket;

  constructor(props) {
    super(props);

    this.fetchChats();
  }

  @action.bound
  handleSelectChat(chat) {
    const { appStore } = this.props;

    this.componentState.activeChat = chat;
    this.socket = new ChatSocket(appStore.currentUser, chat.id, this.handleReceiveMessage);
  }

  @action.bound
  handleSubmitMessage(content) {
    this.socket.sendMessage(content);
  }

  @action.bound
  handleReceiveMessage(message) {
    this.componentState.activeChat.addMessage(message);
    this.forceUpdate();
  }

  @action.bound
  handleOpenNewChatModal() {
    this.componentState.newChatModalOpen = true;
  }

  @action.bound
  handleCloseNewChatModal() {
    this.componentState.newChatModalOpen = false;
  }

  @action.bound
  async handleCreateChat(name, userIds) {
    await createChat(this.props.appStore, name, userIds);
    this.componentState.newChatModalOpen = false;
  }

  @action.bound
  async fetchChats() {
    this.componentState.chats = await fetchChatsForUser(this.props.appStore.currentUser.id);
  }
  
  render() {
    const {
      activeChat,
    } = this.componentState;

    return (
      <Fragment>
        <Row type="flex" justify="space-between">
          <Typography.Title level={2}>
            Chat
          </Typography.Title>
          <Button type="primary" onClick={this.handleOpenNewChatModal}>
            Novi chat
          </Button>
        </Row>
        <Layout
          style={{
            padding: '24px 0',
            background: '#fff',
            height: '550px',
            maxHeight: '100%',
          }}
          hasSider
        >
          <Layout.Sider
            width={300}
            style={{
              background: '#fff',
              borderRight: '1px solid rgba(0,0,0,0.1)',
              paddingRight: '24px',
              overflow: 'auto',
            }}
          >
            <ChatList
              chats={this.componentState.chats}
              activeChat={activeChat}
              onSelectChat={this.handleSelectChat}
            />
          </Layout.Sider>
          <Layout.Content
            style={{
              padding: '0 0 0 24px',
              height: '100%',
              overflow: 'auto',
            }}
          >
            {activeChat ? (
              <ChatContent
                chat={activeChat}
                onSubmitMessage={this.handleSubmitMessage}
              />
            ) : null}
          </Layout.Content>
        </Layout>
        <NewChatModal
          visible={this.componentState.newChatModalOpen}
          onOk={this.handleCreateChat}
          onCancel={this.handleCloseNewChatModal}
        />
      </Fragment>
    );
  }
}
