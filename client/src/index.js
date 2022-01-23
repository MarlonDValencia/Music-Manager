import React from 'react';
import ReactDOM from 'react-dom';
import { Provider } from 'react-redux';
import App from './App';
import store from './store/index.js';

ReactDOM.render(
  <Provider store={store}>
    <App dispatch={store.dispatch}/>
  </Provider>,
  document.getElementById('root')
);