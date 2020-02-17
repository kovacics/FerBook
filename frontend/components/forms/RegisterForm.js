import { Input, Icon, Button } from 'antd';
import { Formik } from 'formik';
import React from 'react';
import { object, string, ref } from 'yup';

import { Margin } from '../Margin';
import { ErrorMessage } from './ErrorMessage';

const validationSchema = object().shape({
  firstName: string().required('Morate unijeti ime'),
  lastName: string().required('Morate unijeti prezime'),
  username: string().required('Morate unijeti korisničko ime'),
  email: string().required('Morate unijeti e-mail').email('Unesite ispravni e-mail'),
  password: string().required('Morate unijeti lozinku'),
  confirmPassword: string()
    .oneOf([ref('password'), ''], 'Lozinke se moraju podudarati')
    .required('Morate ponoviti lozinku'),
});

const initialValues = {
  firstName: '',
  lastName: '',
  username: '',
  email: '',
  password: '',
  confirmPassword: '',
};

export function RegisterForm(props) {
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
              placeholder="Ime"
              size="large"
              name="firstName"
              onChange={formik.handleChange}
              value={formik.values.firstName}
            />
            <ErrorMessage name="firstName" />
          </Margin>
          <Margin margin="0 0 16px">
            <Input
              prefix={<Icon type="user" style={{ color: 'rgba(0,0,0,.25)' }} />}
              placeholder="Prezime"
              size="large"
              name="lastName"
              onChange={formik.handleChange}
              value={formik.values.lastName}
            />
            <ErrorMessage name="lastName" />
          </Margin>
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
          <Margin margin="0 0 16px">
            <Input
              prefix={<Icon type="mail" style={{ color: 'rgba(0,0,0,.25)' }} />}
              type="email"
              placeholder="E-mail"
              size="large"
              name="email"
              onChange={formik.handleChange}
              value={formik.values.email}
            />
            <ErrorMessage name="email" />
          </Margin>
          <Margin margin="0 0 8px">
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
          <Margin margin="0 0 24px">
            <Input.Password
              prefix={<Icon type="lock" style={{ color: 'rgba(0,0,0,.25)' }} />}
              placeholder="Ponovljena lozinka"
              size="large"
              name="confirmPassword"
              onChange={formik.handleChange}
              value={formik.values.confirmPassword}
            />
            <ErrorMessage name="confirmPassword" />
          </Margin>
          <Button
            block
            size="large"
            type="primary"
            htmlType="submit"
            loading={formik.isSubmitting}
          >
            Otvori račun
          </Button>
        </form>
      )}
    </Formik>
  );
}
