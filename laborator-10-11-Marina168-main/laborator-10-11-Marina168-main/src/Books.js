/* eslint-disable no-unused-vars */
import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';
import 'reactjs-popup/dist/index.css';

class Books extends Component { 

  constructor(props) {
    super(props);
    this.state = {books: [], isLoading: true};
    this.remove = this.remove.bind(this);
    //const[isOpen,setIsOpen]=UseState(false);
  }

  componentDidMount() {
    this.setState({isLoading: true});

    fetch('api/library/all')
      .then(response => response.json())
      .then(data => this.setState({books: data, isLoading: false}));
  }

  async remove(id) {
    await fetch(`/api/library/${id}`, {
      method: 'DELETE',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(() => {
      let updatedBooks = [...this.state.books].filter(i => i.id !== id);
      this.setState({books: updatedBooks});
    });
  }

  render() {
    const {books, isLoading} = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }

    const bookList = books.map(book => {
      //const title = `${book.address || ''}`;
      return <tr key={book.id}>
        <td style={{whiteSpace: 'nowrap'}}>{book.title}</td>
        <td style={{whiteSpace: 'nowrap'}}>{book.authors.name}</td>
        <td style={{whiteSpace: 'nowrap'}}>{book.publisher}</td>
        <td style={{whiteSpace: 'nowrap'}}>{book.published}</td>
        <td style={{whiteSpace: 'nowrap'}}>{book.genre}</td>
        <td style={{whiteSpace: 'nowrap'}}>{book.summary}</td>
        <td>
          <ButtonGroup>
            <Button size="sm" color="primary" tag={Link} to={"/books/" + book.id}>Edit</Button>
            <Button size="sm" color="danger" onClick={() => this.remove(book.id)}>Delete</Button>
          </ButtonGroup>
        </td>
      </tr>
    });

    return (
      <div>
        <AppNavbar/>
        <Container fluid>
          <div className="float-right">
            <Button color="success" tag={Link} to="/books/new">Add Book</Button>
          </div>
          <h3>Books</h3>
          <Table className="mt-4">
            <thead>
            <tr>
              <th width="20%">Title</th>
              <th width="20%">Author</th>
              <th width="20%">Publisher</th>
              <th width="20%">Published</th>
              <th width="20%">Genre</th>
              <th width="20%">Summary</th>
              <th width="10%">Actions</th>
            </tr>
            </thead>
            <tbody>
            {bookList}
            </tbody>
          </Table>
        </Container>
      </div>
    );
  }
}

export default Books;