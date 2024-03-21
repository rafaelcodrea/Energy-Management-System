import React, { Component } from 'react';
import Cookies from 'js-cookie';

class Login extends Component {
  constructor() {
    super();
    this.state = {
      username: '',
      password: '',
      errorMessage: '',
      loggedIn: false,
    };
  }

  handleInputChange = (event) => {
    const { name, value } = event.target;
    this.setState({
      [name]: value,
      errorMessage: '',
    });
  }

  handleLogin = async () => {
    
    const { username, password } = this.state;


    try {
        const response = await fetch(`http://localhost:8080/person/allow/check/`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ name: username, password: password }),
        });

        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        const data = await response.json();
        console.log(data);

        if (data.role == "Admin"){
          Cookies.set("role", data.role);
          Cookies.set("id", data.id);
          Cookies.set("token", data.jwtTkn);

          Cookies.set("name", data.name);

          console.log("User role:", data.role);
          console.log("User id:", data.id);
          window.location.href = "/";
        }else{
          Cookies.set("role", data.role);
          Cookies.set("id", data.id);
          console.log("User role:", data.role);
          console.log("User id:", data.id);

          Cookies.set("name", data.name);

          Cookies.set("token", data.jwtTkn);
          window.location.href = "/devices";
        }

    } catch (error) {
        console.error('Error updating user:', error);
    }

  }

  render() {
    const { username, password, errorMessage, loggedIn } = this.state;

    return (
      <div>
        <h2>Login</h2>
        {errorMessage && <p style={{ color: 'red' }}>{errorMessage}</p>}
        <div>
          <label htmlFor="username">Username:</label>
          <input
            type="text"
            id="username"
            name="username"
            value={username}
            onChange={this.handleInputChange}
          />
        </div>
        <div>
          <label htmlFor="password">Password:</label>
          <input
            type="password"
            id="password"
            name="password"
            value={password}
            onChange={this.handleInputChange}
          />
        </div>
        <div>
          <button onClick={this.handleLogin}>Login</button>
        </div>
      </div>
    );
  }
}

export default Login;
