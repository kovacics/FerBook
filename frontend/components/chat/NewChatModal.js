import { Modal, Input, Select, Spin } from 'antd';
import { observable, action } from 'mobx';
import { observer, inject } from 'mobx-react';
import React, { Component } from 'react';

import { fetchFriendshipsForUser } from '../../services/friendship';
import { Margin } from '../Margin';

@inject('appStore')
@observer
export class NewChatModal extends Component {
  @observable
  componentState = {
    name: '',
    selectedFriends: [],
    friends: [],
    friendsLoading: false,
  };

  constructor(props) {
    super(props);
    this.fetchFriendships();
  }
  
  async fetchFriendships() {
    this.componentState.friendsLoading = true;
    this.componentState.friends = await fetchFriendshipsForUser(this.props.appStore.currentUser.id);
    this.componentState.friendsLoading = false;
  }

  @action.bound
  handleNameChange(event) {
    this.componentState.name = event.target.value;
  }

  @action.bound
  handleFriendsChange(value) {
    this.componentState.selectedFriends = value;
  }

  @action.bound
  handleOk() {
    this.props.onOk(this.componentState.name, this.componentState.selectedFriends.map((friend) => friend.key));
  }

  render() {
    const { visible, onCancel } = this.props;

    return (
      <Modal
        title="Novi chat"
        visible={visible}
        okText="Stvori chat"
        cancelText="Odustani"
        onOk={this.handleOk}
        onCancel={onCancel}
        destroyOnClose
      >
        <Margin margin="0 0 8px">
          <Input placeholder="Ime chata" onChange={this.handleNameChange} value={this.componentState.name} />
        </Margin>
        <Select
          mode="multiple"
          labelInValue
          placeholder="Odaberi prijatelje"
          notFoundContent={this.componentState.friendsLoading ? <Spin size="small" /> : null}
          filterOption={false}
          style={{ width: '100%' }}
          showSearch={false}
          onChange={this.handleFriendsChange}
          value={this.componentState.selectedFriends}
        >
          {this.componentState.friends.map((friend) => (
            <Option key={friend.id}>
              {friend.username}
            </Option>
          ))}
        </Select>
      </Modal>
    );
  }
}
