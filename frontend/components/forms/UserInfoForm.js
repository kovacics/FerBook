import { Input, Button } from 'antd';
import { useFormik } from 'formik';
import React from 'react';

import { Margin } from '../Margin';
import { UserProfilePictureUpload } from './UserProfilePictureUpload';

const getInitialValues = (user) => ({
  username: user.username,
  email: user.email,
  firstName: user.firstName,
  lastName: user.lastName,
});

export function UserInfoForm({ user, onSubmit }) {
  const formik = useFormik({
    initialValues: getInitialValues(user),
    onSubmit,
  })

  const handleProfilePictureChange = (id) => {
    formik.setFieldValue('pictureId', id);
  }

  return (
    <form onSubmit={formik.handleSubmit}>
      <UserProfilePictureUpload
        user={user}
        onChange={handleProfilePictureChange}
      />
      <Margin margin="0 0 8px">
        <Input
          name="username"
          value={formik.values.username}
          onChange={formik.handleChange}
        />
      </Margin>
      <Margin margin="0 0 16px">
        <Input
          name="email"
          value={formik.values.email}
          onChange={formik.handleChange}
        />
      </Margin>
      <Margin margin="0 0 8px">
        <Input
          name="firstName"
          value={formik.values.firstName}
          onChange={formik.handleChange}
        />
      </Margin>
      <Margin margin="0 0 24px">
        <Input
          name="lastName"
          value={formik.values.lastName}
          onChange={formik.handleChange}
        />
      </Margin>

      <Button
        block
        size="large"
        type="primary"
        htmlType="submit"
        loading={formik.isSubmitting}
      >
        Spremi
      </Button>
    </form>
  )
}