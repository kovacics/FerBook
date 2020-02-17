import Link from 'next/link';
import React, { Fragment } from 'react';
import { observer, inject } from 'mobx-react';

import { GuestUserActions } from './GuestUserActions';
import { LoggedInUserActions } from './LoggedInUserActions';
import { Search } from './Search';

function NavigationC(props) {
  return (
    <Fragment>
      <header>
        <div className="logo-wrapper">
          <Link href={props.appStore.isLoggedIn ? '/home' : '/'}>
            <a className="logo">
              FerBook
            </a>
          </Link>
        </div>
        {props.appStore.isLoggedIn ? (
          <div className="search-wrapper">
            <Search />
          </div>
        ) : null}
        <div className="actions-wrapper">
          {props.appStore.isLoggedIn ? (
            <LoggedInUserActions />
          ) : (
            <GuestUserActions />
          )}
        </div>
      </header>

      <style jsx>{`
        header {
          padding: 16px 24px;
          border-bottom: 2px solid var(--theme-color);
          display: flex;
          align-items: center;
        }
        
        .logo-wrapper {
          flex-grow: 1;
        }

        .search-wrapper {
          width: 500px;
        }

        .actions-wrapper {
          flex-grow: 1;
          text-align: right;
        }

        .logo {
          color: var(--theme-color);
          font-weight: bold;
          font-size: 24px;
          flex-grow: 1;
        }
      `}</style>
    </Fragment>
  );
}

export const Navigation = inject('appStore')(observer(NavigationC));
