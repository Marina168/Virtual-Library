import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from './AppNavbar';

class BookEdit extends Component {

  emptyItem = {
    title: '',
    authors: '',
    publisher: '',
    published: '',
    genre: '',
    summary: ''
    
  };

  constructor(props) {
    super(props);
    this.state = {
      item: this.emptyItem
    };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  async componentDidMount() {
    if (this.props.match.params.id !== 'new') {
        console.log("Heiii" + this.props.match.params.id);
      const book = await (await fetch(`/api/library/book/${this.props.match.params.id}`)).json();
      console.log("Booook"+this.book);
      this.setState({item: book});
    }
  }

  handleChange(event) {
    const target = event.target;
    const value = target.value;
    const name = target.name;
    let item = {...this.state.item};
    item[name] = value;
    this.setState({item});
  }

  async handleSubmit(event) {
    event.preventDefault();
    const {item} = this.state;

    await fetch('/api/library' + (item.id ? '/' + item.id : ''), {
      method: (item.id) ? 'PUT' : 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(item),
    });
    this.props.history.push('/books');
  }

  render() {
    const {item} = this.state;
    console.log("Id-ul"+item.id)
    const title = <h2>{item.id ? 'Edit Book' : 'Add Book'}</h2>;

    return <div>
      <AppNavbar/>
      <Container>
        {title}
        <Form onSubmit={this.handleSubmit}>
          <FormGroup>
            <Label for="title">Title</Label>
            <Input type="text" name="title" id="title" value={item.title || ''}
                   onChange={this.handleChange} autoComplete="title"/>
          </FormGroup>
          <FormGroup>
            <Label for="publisher">Publisher</Label>
            <Input type="text" name="publisher" id="publisher" value={item.publisher|| ''}
                   onChange={this.handleChange} autoComplete="publisher"/>
          </FormGroup>
          <FormGroup>
            <Label for="published">Publisher</Label>
            <Input type="text" name="published" id="published" value={item.published|| ''}
                   onChange={this.handleChange} autoComplete="published"/>
          </FormGroup>
          <FormGroup>
            <Label for="genre">Genre</Label>
            <Input type="text" name="genre" id="genre" value={item.genre|| ''}
                   onChange={this.handleChange} autoComplete="genre"/>
          </FormGroup>
          <FormGroup>
            <Label for="summary">Summary</Label>
            <Input type="text" name="summary" id="summary" value={item.summary|| ''}
                   onChange={this.handleChange} autoComplete="summary"/>
          </FormGroup> 
          <FormGroup>
            <Button color="primary" type="submit">Save</Button>{' '}
            <Button color="secondary" tag={Link} to="/books">Cancel</Button>
          </FormGroup>
        </Form>
      </Container>
    </div>
  }
}

export default withRouter(BookEdit);