import React, { Component } from 'react';
import Cookies from 'js-cookie';

class DeviceCreate extends Component {
    constructor() {
        super();
        this.state = {
            
            address: '',
            description: '',
            maxEnergyH: '',
            userId:'',
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
        const { address, description, maxEnergyH, userId } = this.state;

        try {
            const response = await fetch('http://localhost:8081/device', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer:'+tkn,
                    'Username':name
                },
                body: JSON.stringify({ address,description, maxEnergyH, userId }),
            });

            if (!response.ok) {
                throw new Error('Network response was not ok');
            }

            // Handle the response from the server, such as displaying a success message or redirecting the user.

            // You can use `this.props.history.push('/desired-route')` to redirect the user if needed.

        } catch (error) {
            console.error('Error creating device:', error);
        }
    }

    render() {
        const { address,description, maxEnergyH, userId} = this.state;

        return (
            <div>
                <h1>Create Device</h1>
                <form onSubmit={this.handleSubmit}>
                    
                    <label>Address:
                        <input type="text" name="address" value={address} onChange={this.handleInputChange} />
                    </label>
                    <br />
                    <label>Description:
                        <input type="text" name="description" value={description} onChange={this.handleInputChange} />
                    </label>
                    <br />
                    <label>Max Hourly Energy:
                        <input type="number" name="maxEnergyH" value={maxEnergyH} onChange={this.handleInputChange} />
                    </label>
                    <br />
                    <br />
                    <label>User id:
                        <input type="number" name="userId" value={userId} onChange={this.handleInputChange} />
                    </label>
                    <br />
                    <button type="submit">Create Device</button>
                </form>
            </div>
        );
    }
}

export default DeviceCreate;
