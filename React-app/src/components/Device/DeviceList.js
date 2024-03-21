import React, { Component } from 'react';
import Cookies from 'js-cookie';
import SockJS from 'sockjs-client'
import Stomp from 'stompjs'
class DeviceList extends Component {
    constructor() {
        super();
        this.state = {
            devices: [],
            modalText: '',
            showModal: false,
        };
    }

    connect = () => {
        const socket = new SockJS('http://localhost:8089/websocket-example');
        const stompClient = Stomp.over(socket);
        var userId = Cookies.get("id");



        stompClient.connect({}, frame => {
          console.log('Connected: ' + frame);
    
          stompClient.subscribe('/topic/user.' + userId, message => {
            const notificationMessage = JSON.parse(message.body);
            this.setState({ showModal: true });
            this.setState({ modalText: notificationMessage.message });
          });
        });
      }

    async componentDidMount() {
        try {
            var userId = Cookies.get("id");
            const tkn = Cookies.get('token'); // Replace 'cookieName' with the actual cookie name
            console.log('Cookie value:', tkn);
            const name = Cookies.get('name'); // Replace 'cookieName' with the actual cookie name

            const response = await fetch('http://localhost:8081/device/getDevices/' + userId, { 
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer:'+tkn,
                    'Username':name
                }
            });
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            const data = await response.json();
            console.log(data); // Log the data to the console
            this.setState({ devices: data });
        } catch (error) {
            console.error('Error fetching device data:', error);
            this.setState({ showModal: true }); // Show modal on error
        }
        this.connect();
    }

    toggleModal = () => {
        this.setState(prevState => ({ showModal: !prevState.showModal }));
    }

    render() {
        const { devices, showModal, modalText } = this.state;

        return (
            <div>
                <h1>Device List</h1>
                <ul>
                    {devices.map((device, index) => (
                        <li key={`${device.id}-${index}`}>{device.address}</li>
                    ))}
                </ul>

                {showModal && (
                    <div>
                        <p>{modalText}</p>
                        <button onClick={this.toggleModal}>Close</button>
                    </div>
                )}
            </div>
        );
    }
}

export default DeviceList;
