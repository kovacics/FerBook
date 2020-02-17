import { Typography } from 'antd';
import { useRouter } from 'next/router';
import React from 'react';

import { Panel } from '../components/Panel';
import { RegisterForm } from '../components/forms/RegisterForm';
import { register } from '../services/user';
import { notify } from '../utils/notifications';

export default function Register() {
  const router = useRouter();

  const handleSubmit = async (payload, actions) => {
    try {
      await register(payload);
      notify('success', 'Uspješno ste se registrirali', 'Sada se možete prijaviti');
      router.push('/login');
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
        Registracija
      </Typography.Title>
      <RegisterForm
        onSubmit={handleSubmit}
      />
    </Panel>
  );
}
