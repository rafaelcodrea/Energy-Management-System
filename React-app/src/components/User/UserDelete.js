import React, { Component } from 'react';
import Cookies from 'js-cookie';

class UserDelete extends Component {
    constructor() {
        super();
        this.state = {
            userId: '',
        };
    }

    handleInputChange = (event) => {
        const { name, value } = event.target;
        this.setState({ [name]: value });
    }

    handleSubmit = async (event) => {
        event.preventDefault();
        const { userId } = this.state;

        const tkn = Cookies.get('token'); // Replace 'cookieName' with the actual cookie name
        console.log('Cookie value:', tkn);
        const name = Cookies.get('name');
        try {
            const response = await fetch(`http://localhost:8080/person/delete/${userId}`, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer:'+tkn,
                    'Username':name
                }
            });

            if (!response.ok) {
                throw new Error('Network response was not ok');
            }

           

        } catch (error) {
            console.error('Error deleting user:', error);
        }
    }

    render() {
        const { userId } = this.state;

        return (
            <div>
                <h1>Delete User</h1>
                <form onSubmit={this.handleSubmit}>
                    <label>User ID to Delete:
                        <input type="text" name="userId" value={userId} onChange={this.handleInputChange} />
                    </label>
                    <br />
                    <button type="submit">Delete User</button>
                </form>
            </div>
        );
    }
}

export default UserDelete;
