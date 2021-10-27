import React, { Component } from 'react';
import './App.css';
import Home from './Home';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import  Books from './Books.js';
import BookEdit from './BookEdit';

class App extends Component {
  render() {
    return (
      <Router>
        <Switch>
          <Route path='/' exact={true} component={Home}/>
          <Route path='/books' exact={true} component={Books}/>
          <Route path='/books/:id'  component={BookEdit}/>
        </Switch>
      </Router>
    )
  }
}

export default App;