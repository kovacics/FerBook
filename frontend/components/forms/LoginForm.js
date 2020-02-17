import { Input, Icon, Button } from 'antd';
import { Formik } from 'formik';
import React from 'react';
import { object, string } from 'yup';

import { Margin } from '../Margin';
import { ErrorMessage } from './ErrorMessage';

const validationSchema = object().shape({
    username: string().required('Morate unijeti korisničko ime'),
    password: string().required('Morate unijeti lozinku'),
});

const initialValues = {
    username: '',
    password: '',
};

export function LoginForm(props) {
  return (
    <Formik
      initialValues={initialValues}
      onSubmit={props.onSubmit}
      validationSchema={validationSchema}
    >
      {(formik) => (
        <form onSubmit={formik.handleSubmit}>
          <Margin margin="0 0 8px">
            <Input
              prefix={<Icon type="user" style={{ color: 'rgba(0,0,0,.25)' }} />}
              placeholder="Korisničko ime"
              size="large"
              name="username"
              onChange={formik.handleChange}
              value={formik.values.username}
            />

            <ErrorMessage name="username" />
          </Margin>

          <Margin margin="0 0 24px">
            <Input.Password
              prefix={<Icon type="lock" style={{ color: 'rgba(0,0,0,.25)' }} />}
              placeholder="Lozinka"
              size="large"
              name="password"
              onChange={formik.handleChange}
              value={formik.values.password}
            />

            <ErrorMessage name="password" />
          </Margin>

          <Button
            block
            size="large"
            type="primary"
            htmlType="submit"
            loading={formik.isSubmitting}
          >
            Prijavi me
          </Button>
        </form>
      )}
    </Formik>
  );
}
