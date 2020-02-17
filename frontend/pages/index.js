import React from 'react'
import Link from 'next/link';
import { Typography, Icon } from 'antd'

export default function Home() {
  return (
    <div>
      <Typography.Title
        style={{ marginBottom: '16px' }}
      >
        Dobrodošli na FerBook! <Icon type="team" /><br />
      </Typography.Title>
      <Typography.Title level={2}>
        FerBook je zajednica na kojoj studenti FER-a mogu komunicirati,<br />
        sklapati prijateljstva, izmijenjivati informacije, ...
      </Typography.Title>

      <Typography.Title level={3}>
        Možeš se&nbsp;
        <Link href="login">
          <a>prijaviti</a>
        </Link> ili&nbsp;
        <Link href="register">
          <a>stvoriti račun</a>
        </Link>
      </Typography.Title>
    </div>
  );
}
