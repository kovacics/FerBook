import { Menu, Dropdown, Icon } from 'antd';
import { inject, observer } from 'mobx-react';
import Link from 'next/link';
import { useRouter } from 'next/router';

import { logout } from '../../services/user';

function LoggedInUserActionsC(props) {
  const router = useRouter();

  const handleLogout = async () => {
    await router.push('/');
    logout(props.appStore);
  }

  const handleChat = () => router.push('/chat');

  const handlePreferences = () => router.push('/preferences');

  return (
    <Dropdown.Button
      overlay={(
        <ActionsMenu
          onLogout={handleLogout}
          onChat={handleChat}
          onPreferences={handlePreferences}
        />
      )}
      icon={<Icon type="user" />}
      size="large"
    >
      <Link
        href="/profiles/[id]"
        as={`/profiles/${props.appStore.currentUser.id}`}
        passHref
      >
        <a>
          {props.appStore.currentUser.fullName}
        </a>
      </Link>
    </Dropdown.Button>
  );
}

function ActionsMenu(props) {
  return (
    <Menu>
      <Menu.Item
        key="chat"
        onClick={props.onChat}
      >
        <Icon type="message" />
        Chat
      </Menu.Item>
      <Menu.Item
        key="preferences"
        onClick={props.onPreferences}
      >
        <Icon type="setting" />
        Postavke
      </Menu.Item>
      <Menu.Item
        key="logout"
        onClick={props.onLogout}
      >
        <Icon type="user" />
        Odjava
      </Menu.Item>
    </Menu>
  );
}

export const LoggedInUserActions = inject('appStore')(observer(LoggedInUserActionsC));
