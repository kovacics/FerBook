import { Typography } from 'antd';
import { ErrorMessage as FormikErrorMessage } from 'formik';
import React from 'react';

export function ErrorMessage({ name }) {
  return (
    <Typography.Paragraph type="danger">
      <FormikErrorMessage name={name} />
    </Typography.Paragraph>
  );
} 
