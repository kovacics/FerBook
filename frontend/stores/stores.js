import { useStaticRendering } from 'mobx-react';

import { AppStore } from './AppStore';
import { isServer } from '../utils/env';

useStaticRendering(isServer);

let store = null;

export default function initializeStore(initialData = { appStore: {} }) {
  if (isServer) {
    return {
      appStore: new AppStore(initialData.appStore),
    };
  }
  if (store === null) {
    store = {
      appStore: new AppStore(initialData.appStore),
    };
  }

  return store;
}
