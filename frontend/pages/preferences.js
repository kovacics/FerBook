import { Typography } from 'antd';
import { observer, inject } from 'mobx-react';
import React from 'react';

import { redirect } from '../utils/redirect';
import { Panel } from '../components/Panel';
import { UserInfoForm } from '../components/forms/UserInfoForm';
import { updateUser } from '../services/user';
import { notify } from '../utils/notifications';

function Preferences({ appStore }) {
  const handleSubmit = async (payload, actions) => {
    await updateUser(appStore, payload);
    notify('success', 'Uspješno ste ažurirali postavke!');

    console.log(appStore.currentUser);
  }

  return (
    <Panel>
      <Typography.Title level={2}>
        Postavke
      </Typography.Title>

      <UserInfoForm
        user={appStore.currentUser}
        onSubmit={handleSubmit}
      />
    </Panel>
  );
}

Preferences.getInitialProps = async (context) => {
  const { appStore } = context.mobxStore;

  if (!appStore.isLoggedIn) {
    await redirect(context, '/login');
  }

  return {};
}

export default inject('appStore')(observer(Preferences));
