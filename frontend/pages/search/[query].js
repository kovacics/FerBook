import { inject, observer } from 'mobx-react';
import React, { Fragment } from 'react';

import { fetchUsersByQuery } from '../../services/user';
import { User } from '../../stores/models/user';
import { redirect } from '../../utils/redirect';
import { Typography } from 'antd';
import { UserCard } from '../../components/search/UserCard';
import { Margin } from '../../components/Margin';
import { Panel } from '../../components/Panel';

function Search(props) {
  const users = props.users.map((user) => new User(user));
  const foundUsers = users.length > 0;

  return (
    <Panel maxWidth="800px">
      {foundUsers ? (
        <Fragment>
          <Margin margin="0 0 40px">
            <Typography.Title level={2}>
              Pronašli smo sljedeće korisnike za upit "{props.query}"
            </Typography.Title>
          </Margin>
          <div>
            {users.map((user) => (
              <Margin
                margin="0 0 20px"
                key={user.id}
              >
                <UserCard
                  user={user}
                />
              </Margin>
            ))}
          </div>
        </Fragment>
      ) : (
        <Typography.Title level={2}>
          Nije pronađen nijedan korisnik za upit "{props.query}"
        </Typography.Title>
      )}
    </Panel>
  );
}

Search.getInitialProps = async (context) => {
  const { appStore } = context.mobxStore;
  const { query } = context.query;

  if (!appStore.isLoggedIn) {
    await redirect(context, '/login');
  }

  const users = await fetchUsersByQuery(query);

  return {
    users,
    query,
  };
}

export default inject('appStore')(observer(Search));
