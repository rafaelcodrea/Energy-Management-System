import React, { Component } from 'react';
import Cookies from 'js-cookie';

class UserList extends Component {
    constructor() {
        super();
        this.state = {
            users: [],
        };
    }
    async componentDidMount() {

        const tkn = Cookies.get('token'); // Replace 'cookieName' with the actual cookie name
        console.log('Cookie value:', tkn);
        const name = Cookies.get('name'); // Replace 'cookieName' with the actual cookie name


        try {
            const response = await fetch(`http://localhost:8080/person`, { 
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
            this.setState({ users: data });
            console.log(this.state.users);
        } catch (error) {
            console.error('Error fetching user data:', error);
        }
    }
    render() {
        const { users } = this.state;

        return (
            <div>
                <h1>User List</h1>
                <ul>
                    {users.map((user) => (
                        <li key={user.id}>{user.name}</li>
                    ))}
                </ul>
            </div>
        );
    }
}

export default UserList;


// import React, { Component } from 'react';

// class UserList extends Component {
//     constructor() {
//         super();
//         this.state = {
//             users: [],
//         };
//     }

//     async componentDidMount() {
//         // Replace with the actual URL of your user microservice
//         fetch('http://localhost:8080/person') // Example URL
//             .then((response) => {
//                 if (!response.ok) {
//                     throw new Error('Network response was not ok');
//                 }
//                 this.state.users=response.json();
//                 console.log(this.state.users);
//             })
//             .then((data) => {
//                 this.setState({ users: data });
//             })
//             .catch((error) => {
//                 console.error('Error fetching user data:', error);
//             });
//     }

//     render() {
//         const { users } = this.state;

//         return (
//             <div>
//                 <h1>User List</h1>
//                 <ul>
//                     {users.map((user) => (
//                         <li key={user.id}>{user.name}</li>
//                     ))}
//                 </ul>
//             </div>
//         );
//     }
// }

// export default UserList;



// // import React, { Component } from 'react';
// // import axiosJsonp from 'axios-jsonp';
// // import jsonp from 'jsonp';


// // class UserList extends Component {
// //     constructor() {
// //         super();
// //         this.state = {
// //             users: [],
// //         };
// //     }

// //     componentDidMount() {
// //     // Replace with the actual URL of your user microservice
// //     axiosJsonp({
// //         url: 'http://localhost:8080/person', // Example URL
// //     })
// //         .then((response) => {
// //             this.setState({ users: response.data });
// //         })
// //         .catch((error) => {
// //             console.error('Error fetching user data:', error);
// //         });
// // }


// //     render() {
// //         const { users } = this.state;

// //         return (
// //             <div>
// //                 <h1>User List</h1>
// //                 <ul>
// //                     {users.map((user) => (
// //                         <li key={user.id}>{user.name}</li>
// //                     ))}
// //                 </ul>
// //             </div>
// //         );
// //     }
// // }

// // export default UserList;











// // // import React, { Component } from 'react';
// // // import axios from 'axios';

// // // class UserList extends Component {
// // //     constructor() {
// // //         super();
// // //         this.state = {
// // //             users: [],
// // //         };
// // //     }

// // //     componentDidMount() {
// // //         // Replace with the actual URL of your user microservice
// // //         axios.get('http://localhost:8080/person') // Example URL
// // //             .then((response) => {
// // //                 this.setState({ users: response.data });
// // //             })
// // //             .catch((error) => {
// // //                 console.error('Error fetching user data:', error);
// // //             });
// // //     }

// // //     render() {
// // //         const { users } = this.state;

// // //         return (
// // //             <div>
// // //                 <h1>User List</h1>
// // //                 <ul>
// // //                     {users.map((user) => (
// // //                         <li key={user.id}>{user.name}</li>
// // //                     ))}
// // //                 </ul>
// // //             </div>
// // //         );
// // //     }
// // // }

// // // export default UserList;


// // // // import React, { Component } from 'react';
// // // // import axios from 'axios';

// // // // class UserList extends Component {
// // // //     render() {
// // // //         const users = [
            
// // // //         ];
    
// // // //         return (
// // // //             <div>
// // // //                 <h1>User List</h1>
// // // //                 <ul>
// // // //                     {users.map((user) => (
// // // //                         <li key={user.id}>{user.name}</li>
// // // //                     ))}
// // // //                 </ul>
// // // //             </div>
// // // //         );
// // // //     }
    
// // // // }

// // // // export default UserList;
