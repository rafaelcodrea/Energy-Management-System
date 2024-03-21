import React from 'react';
import UserList from './components/User/UserList';
import DeviceList from './components/Device/DeviceList';
import DeviceDelete from './components/Device/DeviceDelete';
import DeviceCreate from './components/Device/DeviceCreate';
import DeviceUpdate from './components/Device/DeviceUpdate';
import UserCreate from './components/User/UserCreate';
import UserDelete from './components/User/UserDelete';
import UserUpdate from './components/User/UserUpdate';
import ChatComponent from './components/User/Chat';
import ChatAdmin from './components/User/ChatAdmin';


import Login from './components/Login';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import HomePage from './components/HomePage'; // Import the HomePage component

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<HomePage />} /> {}
                <Route path="/users" element={<UserList />} />
                <Route path="/createuser" element={<UserCreate />} />

                <Route path="/chat" element={<ChatComponent />} />

                <Route path="/chatadmin" element={<ChatAdmin />} />


                <Route path="/deleteuser" element={<UserDelete />} />
                <Route path="/updateuser" element={<UserUpdate />} />
                <Route path="/devices" element={<DeviceList />} />
                <Route path="/deletedevice" element={<DeviceDelete />} />
                <Route path="/createdevice" element={<DeviceCreate />} />
                <Route path="/updatedevice" element={<DeviceUpdate />} />
                <Route path="/login" element={<Login />} />
            </Routes>
        </Router>
    );
}

export default App;













// import React from 'react';
// import UserList from './components/User/UserList';
// import DeviceList from './components/Device/DeviceList';
// import DeviceDelete from './components/Device/DeviceDelete';
// import DeviceCreate from './components/Device/DeviceCreate';
// import DeviceUpdate from './components/Device/DeviceUpdate';



// import { BrowserRouter as Router, Route,Routes} from 'react-router-dom';
// // import DeviceList from './components/Device/DeviceList';
// import UserCreate from './components/User/UserCreate';
// import UserDelete from './components/User/UserDelete';
// import UserUpdate from './components/User/UserUpdate';



// function App() {
//     return (
//         <Router>
//             {
//               <Routes>
//               <Route path="/users" element={<UserList />} />
//               <Route path="/createuser" element={<UserCreate />} />
//               <Route path="/deleteuser" element={<UserDelete />} />
//               <Route path="/updateuser" element={<UserUpdate />} />


//               <Route path="/devices" element={<DeviceList />} />
//               <Route path="/deletedevice" element={<DeviceDelete />} />
//               <Route path="/createdevice" element={<DeviceCreate />} />
//               <Route path="/updatedevice" element={<DeviceUpdate />} />



//             </Routes>
            
            
            
//             /* <Switch>
//                 <Route path="/users" component={UserList} />
//                 <Route path="/devices" component={DeviceList} />
//             </Switch> */}
//         </Router>
//     );
// }

// export default App;
