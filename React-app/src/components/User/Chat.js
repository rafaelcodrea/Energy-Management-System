import React, { useState, useEffect } from 'react';
import Cookie from 'js-cookie';
import { over as StompOver } from 'stompjs';
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';
import './App.css';
const ChatComponent = () => {
  const [chatMessages, setChatMessages] = useState([]);
  const [currentMessage, setCurrentMessage] = useState('');
  const [stompSession, setStompSession] = useState(null);
  const [isTyping, setIsTyping] = useState(false);
  const [messageSeen, setMessageSeen] = useState(false);
  const [stompClient, setStompClient] = useState(null);
  const userID = Cookie.get('id');
  const authToken = Cookie.get('jwtToken');

  const onMessageReceived = (msg) => {
    const data = JSON.parse(msg.body);
    switch (data.action) {
      case "MSG":
        setChatMessages(prev => [...prev, { content: data.message, author: 'admin' }]);
        setMessageSeen(false);
        break;
      case "WRITEON":
        setIsTyping(true);
        break;
      case "WRITEOFF":
        setIsTyping(false);
        break;
      case "SEEN":
        setMessageSeen(true);
        break;
      default:
        break;
    }
  };
  


  const handleReceivedMessage = () => {
    if (currentMessage.trim()) {
      setChatMessages(prev => [...prev, { content: currentMessage, author: 'user' }]);
      stompClient.send('/app/chat.send', {}, JSON.stringify({ senderId: userID, action: 'MSG', message: currentMessage }));
      setCurrentMessage('');
      setIsTyping(false);
      setMessageSeen(false);
    }
  };
  const connect = () => {
    if (!stompClient){
        const socket = new SockJS('http://localhost:8088/websocket-example');
        const stompClient = Stomp.over(socket);
    
        stompClient.connect({}, (frame) => {
            console.log('Connected: ' + frame);
            setStompClient(stompClient);
            stompClient.subscribe('/topic/user.'+userID, onMessageReceived);
          
        });
        }
  };


  useEffect(() => {
    

    return () => {
      
    };
  }, [userID, stompClient]);

  const sendMessage = () => {
    if (currentMessage.trim()) {
      setChatMessages(prev => [...prev, { content: currentMessage, author: 'user' }]);
      stompClient.send('/app/chat.send', {}, JSON.stringify({ senderId: userID, action: 'MSG', message: currentMessage }));
      setCurrentMessage('');
      setIsTyping(false);
      setMessageSeen(false);
    }
  };

  


  const onInputChange = (text) => {
    setCurrentMessage(text);
    const action = text ? 'WRITEON' : 'WRITEOFF';
    stompClient.send('/app/chat.send', {}, JSON.stringify({ senderId: userID, action, message: "" }));
  };
  return (
    <div className="chat-container">
      <div className="chat-header">
        Chat
      </div>
      <div style={{ height: '300px', overflowY: 'scroll' }}>
        {chatMessages.map((msg, idx) => (
          <div key={idx} className={`message-bubble ${msg.author === 'user' ? 'user-message' : 'admin-message'}`}>
            <strong>{msg.author === 'user' ? 'You:' : 'Admin:'}</strong> {msg.content}
          </div>
        ))}
        {isTyping && <p>Adminul tasteaza, va rog asteptati.</p>}
      </div>
      <div style={{ marginTop: '10px', padding: '10px' }}>
        <input
          type="text"
          value={currentMessage}
          onChange={(e) => onInputChange(e.target.value)}
          placeholder=" "
          className="message-input"
        />
        {messageSeen && <p>Seen✔✔</p>}
        <button className="send-button" onClick={sendMessage}>Send</button>
        <button onClick={connect}>Connect</button> {/* Consider adding a class for styling if needed */}
      </div>
    </div>
  );
};
//   return (
//     <div>
//       <div style={{ height: '300px', overflowY: 'scroll', border: '1px solid #ccc' }}>
//         {chatMessages.map((msg, idx) => (
//           <div key={idx} style={{ padding: '5px', borderBottom: '1px solid #eee' }}>
//             <strong>{msg.author === 'user' ? 'You:' : 'Admin:'}</strong> {msg.content}
//           </div>
//         ))}
//         {isTyping && <p>Adminul tasteaza, va rog asteptati.</p>}
//       </div>
//       <div style={{ marginTop: '10px' }}>
//         <input
//           type="text"
//           value={currentMessage}
//           onChange={(e) => onInputChange(e.target.value)}
//           placeholder="Type your message..."
//           style={{ marginRight: '10px' }}
//         />
//         {messageSeen && <p>Seen✔✔</p>}
//         <button onClick={sendMessage}>Send</button>
//         <button onClick={connect}>Connect</button>
//       </div>
//     </div>
//   );
// };

export default ChatComponent;
