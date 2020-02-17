import { Card, Row, Col, Popconfirm, Icon, Button } from "antd";
import { ActivityAuthor } from "../activities/ActivityAuthor";

export function FriendshipRequestCard(props) {
  const handleConfirmFriendship = () => {
    props.onConfirmFriendship(props.friendship);
  }

  const handleRejectFriendship = () => {
    props.onRejectFriendship(props.friendship);
  }

  return (
    <Card>
      <Row>
        <Col span={20}>
          <ActivityAuthor
            user={props.friendship.user}
          />
        </Col>
        <Col span={2}>
          <Popconfirm
            title="Prihvati prijateljstvo?"
            okText="Da"
            okType="danger"
            cancelText="Ne"
            icon={<Icon type="delete" />}
            onConfirm={handleConfirmFriendship}
          >
            <div>
              <Button type="primary" shape="circle" icon="check" size="small" />
            </div>
          </Popconfirm>
        </Col>
        <Col span={2}>
          <Popconfirm
            title="Odbij prijateljstvo?"
            okText="Da"
            okType="danger"
            cancelText="Ne"
            icon={<Icon type="delete" />}
            onConfirm={handleRejectFriendship}
          >
            <div>
              <Button type="danger" shape="circle" icon="close" size="small" />
            </div>
          </Popconfirm>
        </Col>
      </Row>
    </Card>
  );
}