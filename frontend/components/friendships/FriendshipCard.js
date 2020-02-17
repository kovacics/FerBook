import React from 'react';
import { Row, Col, Card, Popconfirm, Button, Icon } from 'antd';

import { ActivityAuthor } from '../activities/ActivityAuthor';

export function FriendshipCard(props) {
  return (
    <Card>
      <Row>
        <Col span={23}>
          <ActivityAuthor
            user={props.friend}
          />
        </Col>
        {props.showActions && (
          <Col span={1}>
            <Popconfirm
                title="Sigurno želite izbrisati ovog prijatelja?"
                okText="Da"
                okType="danger"
                cancelText="Ne"
                icon={<Icon type="delete" />}
                onConfirm={props.onDeleteFriendship}
              >
                <div>
                  <Button type="ghost" shape="circle" icon="delete" size="small" />
                </div>
              </Popconfirm>
            </Col>
        )}
      </Row>
    </Card>
  );
}
