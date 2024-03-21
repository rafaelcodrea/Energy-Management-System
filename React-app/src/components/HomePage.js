import React from 'react';

function HomePage() {
    return (
        <div>
            <h1>Welcome to the Energy Management System</h1>
            <div>
                <h2>User Management</h2>
                <ul>
                    <li><a href="/users">User List</a></li>
                    <li><a href="/createuser">Create User</a></li>
                    <li><a href="/deleteuser">Delete User</a></li>
                    <li><a href="/updateuser">Update User</a></li>
                </ul>
            </div>
            <div>
                <h2>Device Management</h2>
                <ul>
                    <li><a href="/devices">Device List</a></li>
                    <li><a href="/deletedevice">Delete Device</a></li>
                    <li><a href="/createdevice">Create Device</a></li>
                    <li><a href="/updatedevice">Update Device</a></li>
                </ul>
            </div>
        </div>
    );
}

export default HomePage;
