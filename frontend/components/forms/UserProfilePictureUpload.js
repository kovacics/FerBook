import { Upload, Button, Icon } from 'antd';
import React, { Component } from 'react';
import { UPLOAD_URL } from '../../services/api';

export class UserProfilePictureUpload extends Component {
  state = {
    fileList: [
      {
        uid: '-1',
        status: 'done',
        url: '',
      },
    ],
  };

  constructor(props) {
    super(props);

    this.state.fileList[0].url = props.user.profilePictureUrl;
  }

  handleChange = info => {
    let fileList = [...info.fileList];

    fileList = fileList.slice(-1);

    fileList = fileList.map(file => {
      if (file.response) {
        file.url = file.response.url;
      }
      return file;
    });

    if (info.file.status === 'done') {
      this.props.onChange(info.file.response);
    }

    if (info.file.status === 'removed') {
      this.props.onChange('');
    }

    this.setState({ fileList });
  };

  render() {
    return (
      <Upload
        listType="picture-card"
        fileList={this.state.fileList}
        onChange={this.handleChange}
        action={UPLOAD_URL}
      >
        <Button>
          <Icon type="upload" /> Upload
        </Button>
      </Upload>
    );
  }
}
