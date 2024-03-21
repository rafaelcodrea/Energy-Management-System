import React, { Component } from 'react';
import Cookies from 'js-cookie';

class DeviceUpdate extends Component {
    constructor() {
        super();
        this.state = {
            deviceId: '', // Device ID of the device to update
            newAddress: '',
            newDescription: '',
            newMaxEnergyH: '',
        };
    }

    handleInputChange = (event) => {
        const { name, value } = event.target;
        this.setState({ [name]: value });
    }

    handleSubmit = async (event) => {
        event.preventDefault();
        const { deviceId, newAddress, newDescription, newMaxEnergyH } = this.state;
        const tkn = Cookies.get('token'); // Replace 'cookieName' with the actual cookie name
        const name = Cookies.get('name');
        console.log('Cookie value:', tkn);
        try {
            const response = await fetch(`http://localhost:8081/device/update/${deviceId}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer:'+tkn,
                    'Username':name
                },
                body: JSON.stringify({ address: newAddress, description: newDescription, maxEnergyH: newMaxEnergyH }),
            });

            if (!response.ok) {
                throw new Error('Network response was not ok');
            }

            // Handle the response from the server, such as displaying a success message or redirecting the user.

            // You can use `this.props.history.push('/desired-route')` to redirect the user if needed.

        } catch (error) {
            console.error('Error updating device:', error);
        }
    }

    render() {
        const { deviceId, newAddress, newDescription, newMaxEnergyH } = this.state;

        return (
            <div>
                <h1>Update Device</h1>
                <form onSubmit={this.handleSubmit}>
                    <label>Device ID to Update:
                        <input type="number" name="deviceId" value={deviceId} onChange={this.handleInputChange} />
                    </label>
                    <br />
                    <label>New Address:
                        <input type="text" name="newAddress" value={newAddress} onChange={this.handleInputChange} />
                    </label>
                    <br />
                    <label>New Description:
                        <input type="text" name="newDescription" value={newDescription} onChange={this.handleInputChange} />
                    </label>
                    <br />
                    <label>New Max Hourly Energy:
                        <input type="number" name="newMaxEnergyH" value={newMaxEnergyH} onChange={this.handleInputChange} />
                    </label>
                    <br />
                    <button type="submit">Update Device</button>
                </form>
            </div>
        );
    }
}

export default DeviceUpdate;
