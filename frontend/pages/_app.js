import stylesheet from 'antd/dist/antd.min.css';
import { Provider, observer } from 'mobx-react';
import React, { Fragment } from 'react';
import App from 'next/app';
import Head from 'next/head';

import { Navigation } from '../components/Navigation';
import initializeStore from '../stores/stores';
import { isServer } from '../utils/env';

@observer
export default class CustomApp extends App {
  static async getInitialProps(appContext) {
    const mobxStore = initializeStore();
    appContext.ctx.mobxStore = mobxStore;

    const appProps = await App.getInitialProps(appContext);

    return {
      ...appProps,
      initialMobxState: mobxStore,
    };
  }

  constructor(props) {
    super(props);

    this.mobxStore = isServer ? props.initialMobxState : initializeStore(props.initialMobxState);
  }

  render() {
    const { Component, pageProps } = this.props;

    return (
      <div>
        <Head>
          <title>Home</title>
          <link rel='icon' href='/favicon.ico' />
        </Head>
        <style dangerouslySetInnerHTML={{ __html: stylesheet }} />

        <Provider {...this.mobxStore}>
          <Fragment>
            <Navigation />

            <main>
              <Component {...pageProps} />
            </main>
          </Fragment>
        </Provider>

        <style jsx global>{`
          :root {
            --theme-color: #ff6d18;
            --side-margin: 24px;
          }

          * {
            box-sizing: border-box;
          }

          body {
            margin: 0;
            font-family: Arial;
          }

          main {
            padding: 80px var(--side-margin) 0;
          }
          
          p {
            margin-bottom: 0!important;
          }
      
          .ant-carousel .slick-slide {
            text-align: center;
            height: 160px;
            line-height: 160px;
            overflow: hidden;
          }
        `}</style>
      </div>
    );
  }
}
