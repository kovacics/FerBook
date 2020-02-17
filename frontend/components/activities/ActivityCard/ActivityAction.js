import { Button, Popconfirm, Icon } from 'antd';
import { inject, observer } from 'mobx-react';

function ActivityActionC({ activity, appStore, onDelete }) {
  const handleConfirmDelete = () => {
    onDelete(activity.id);
  }

  return activity.user.equals(appStore.currentUser) && (
    <Popconfirm
      title="Sigurno želite izbrisati ovu aktivnost?"
      okText="Da"
      okType="danger"
      cancelText="Ne"
      icon={<Icon type="delete" />}
      onConfirm={handleConfirmDelete}
    >
      <div>
        <Button type="ghost" shape="circle" icon="delete" size="small" />
      </div>
    </Popconfirm>
  );
}

export const ActivityAction = inject('appStore')(observer(ActivityActionC));
