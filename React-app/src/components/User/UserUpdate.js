import React, { Component } from 'react';
import Cookies from 'js-cookie';

class UserUpdate extends Component {
    constructor() {
        super();
        this.state = {
            userId: '', 
            newName: '',
            newRole: 'Client',
            newPassword: '', 
        };
    }

    handleInputChange = (event) => {
        const { name, value } = event.target;
        this.setState({ [name]: value });
    }

    handleSubmit = async (event) => {
        event.preventDefault();
        const { userId, newName, newRole, newPassword } = this.state;


        const tkn = Cookies.get('token'); // Replace 'cookieName' with the actual cookie name
        console.log('Cookie value:', tkn);
        const name = Cookies.get('name');

        try {
            const response = await fetch(`http://localhost:8080/person/update/${userId}`, { 
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer:'+tkn,
                    'Username':name
                },
                body: JSON.stringify({ name: newName, role: newRole, password: newPassword }),
            });

            if (!response.ok) {
                throw new Error('Network response was not ok');
            }

            
        } catch (error) {
            console.error('Error updating user:', error);
        }
    }

    render() {
        const { userId, newName, newRole, newPassword } = this.state;

        return (
            <div>
                <h1>Update User</h1>
                <form onSubmit={this.handleSubmit}>
                    <label>User ID to Update:
                        <input type="text" name="userId" value={userId} onChange={this.handleInputChange} />
                    </label>
                    <br />
                    <label>New Name:
                        <input type="text" name="newName" value={newName} onChange={this.handleInputChange} />
                    </label>
                    <br />
                    <label>New Role:
                        <select name="newRole" value={newRole} onChange={this.handleInputChange}>
                            <option value="Admin">Administrator</option>
                            <option value="Client">Client</option>
                        </select>
                    </label>
                    <br />
                    <label>New Password: {/* Add a password field */}
                        <input type="password" name="newPassword" value={newPassword} onChange={this.handleInputChange} />
                    </label>
                    <br />
                    <button type="submit">Update User</button>
                </form>
            </div>
        );
    }
}

export default UserUpdate;
