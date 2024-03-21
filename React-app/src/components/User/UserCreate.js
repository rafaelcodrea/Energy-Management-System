import React, { Component } from 'react';
import Cookies from 'js-cookie';

class UserCreate extends Component {
    constructor() {
        super();
        this.state = {
            name: '',
            role: 'Client',
            password: '', // Add a password field
        };
    }

    handleInputChange = (event) => {
        const { name, value } = event.target;
        this.setState({ [name]: value });
    }

    handleSubmit = async (event) => {
        event.preventDefault();
        const { name, role, password } = this.state;

        const tkn = Cookies.get('token'); // Replace 'cookieName' with the actual cookie name
        console.log('Cookie value:', tkn);

        try {
            const response = await fetch('http://localhost:8080/person/allow', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer:'+tkn,
                    'Username':name
                },
                body: JSON.stringify({ name, role: role, password }), 
            });

            if (!response.ok) {
                throw new Error('Network response was not ok');
            }

            

        } catch (error) {
            console.error('Error creating user:', error);
        }
    }

    render() {
        const { name, role, password } = this.state;

        return (
            <div>
                <h1>Create User</h1>
                <form onSubmit={this.handleSubmit}>
                    <label>Name:
                        <input type="text" name="name" value={name} onChange={this.handleInputChange} />
                    </label>
                    <br />
                    <label>Role:
                        <select name="role" value={role} onChange={this.handleInputChange}>
                            <option value="Admin">Administrator</option>
                            <option value="Client">Client</option>
                        </select>
                    </label>
                    <br />
                    <label>Password: {}
                        <input type="password" name="password" value={password} onChange={this.handleInputChange} />
                    </label>
                    <br />
                    <button type="submit">Create User</button>
                </form>
            </div>
        );
    }
}

export default UserCreate;
