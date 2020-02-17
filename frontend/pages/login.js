import { Typography } from 'antd';
import { inject, observer } from 'mobx-react';
import { useRouter } from 'next/router';
import React from 'react';

import { Panel } from '../components/Panel';
import { LoginForm } from '../components/forms/LoginForm';
import { login } from '../services/user';
import { notify } from '../utils/notifications';

function Login(props) {
  const router = useRouter();

  const handleSubmit = async (payload, actions) => {
    try {
      await login(props.appStore, payload);
      notify('success', 'Uspješno ste se prijavili');
      router.push('/home');
    } catch (response) {
      if (response) {
        actions.setErrors(response);
      } else {
        // TODO add error notification
      }
    }
  };

  return (
    <Panel>
      <Typography.Title level={2}>
        Prijava
      </Typography.Title>
      <LoginForm
        onSubmit={handleSubmit}
      />
    </Panel>
  );
}

export default inject('appStore')(observer(Login));
