import { Button } from 'antd';
import Link from 'next/link';
import React, { Fragment } from 'react';

export function GuestUserActions() {
  return (
    <Fragment>
      <Link href="/login">
        <Button style={{ marginRight: '16px' }}>
          Prijava
        </Button>
      </Link>
      <Link href="/register">
        <Button type="primary">
          Registracija
        </Button>
      </Link>
    </Fragment>
  );
}
