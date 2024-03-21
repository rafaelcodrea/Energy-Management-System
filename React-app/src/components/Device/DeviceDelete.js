import React, { Component } from 'react';
import Cookies from 'js-cookie';

class DeviceDelete extends Component {
    constructor() {
        super();
        this.state = {
            deviceId: '', // Device ID of the device to delete
        };
    }

    handleInputChange = (event) => {
        const { name, value } = event.target;
        this.setState({ [name]: value });
    }

    handleSubmit = async (event) => {




        const tkn = Cookies.get('token'); // Replace 'cookieName' with the actual cookie name
        console.log('Cookie value:', tkn);
        const name = Cookies.get('name');

        event.preventDefault();



        const { deviceId } = this.state;

        try {
            const response = await fetch(`http://localhost:8081/device/delete/${deviceId}`, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer:'+tkn,
                    'Username':name
                },
            });

            if (!response.ok) {
                throw new Error('Network response was not ok');
            }

            // Handle the response from the server, such as displaying a success message or redirecting the user.

            // You can use `this.props.history.push('/desired-route')` to redirect the user if needed.

        } catch (error) {
            console.error('Error deleting device:', error);
        }
    }

    render() {
        const { deviceId } = this.state;

        return (
            <div>
                <h1>Delete Device</h1>
                <form onSubmit={this.handleSubmit}>
                    <label>Device ID to Delete:
                        <input type="text" name="deviceId" value={deviceId} onChange={this.handleInputChange} />
                    </label>
                    <br />
                    <button type="submit">Delete Device</button>
                </form>
            </div>
        );
    }
}

export default DeviceDelete;
