import React from "react";
import "./Messages.css";
import List from "./List";
import AddContact from "./AddContact";
import { useLocation, useNavigate } from "react-router-dom";
import axios from "axios";
import { useState, useRef, useEffect } from "react";
import {
  Dialog,
  DialogTitle,
  DialogActions,
  DialogContent,
} from "@mui/material";
import MessageFooter from "./MessageFooter";

const Messages = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const id = location.state?.id;
  const [contact, setContact] = useState({
    userId: id,
    phone: "",
  });
  var flagToConnect = false;
  const [currentFriend, setCurrentFriend] = useState();
  const [messageResponse, setMessageResponse] = useState([
    {
      id: 1,
      senderId: 1,
      receiverId: id,
      messageBody: "Click on one of your friends to load the chat",
      timestamp: "2023-06-23T11:57:55.824+00:00",
    },
  ]);

  const messageAreaRef = useRef(null);
  useEffect(() => {
    scrollToBottom();
  }, [messageResponse]);

  const scrollToBottom = () => {
    if (messageAreaRef.current) {
      messageAreaRef.current.scrollTop = messageAreaRef.current.scrollHeight;
    }
  };
  const [openDialog, setOpenDialog] = useState(false);

  const handleOpenDialog = () => {
    setOpenDialog(true);
  };

  const handleCloseDialog = () => {
    setOpenDialog(false);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setOpenDialog(false);
    try {
      await axios.post("/messagehub/rest/contacts/create", contact).then(() => {
        window.location.reload();
      });
    } catch (error) {
      console.error(error);
    }
  };

  const showMessages = async (messageRequest) => {
    flagToConnect=false;
    try {
      await axios
        .post("/messagehub/rest/messages", messageRequest)
        .then((response) => {
          setMessageResponse(response.data);
        });
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <>
      <header>
        <button className="add-contact" onClick={handleOpenDialog}>
          Add Contact
        </button>
        <label class="toggle" title="Switch between offline/online mode">
          <input type="checkbox" />
          <span class="slider"></span>
        </label>
        <Dialog open={openDialog} onClose={handleCloseDialog}>
          <DialogTitle>Enter contact number</DialogTitle>
          <DialogContent>
            <AddContact contact={contact} setContact={setContact}></AddContact>
          </DialogContent>
          <DialogActions>
            <button
              onClick={handleCloseDialog}
              style={{ position: "absolute", left: "8px" }}
            >
              Close
            </button>
            <button onClick={handleSubmit}>Submit</button>
          </DialogActions>
        </Dialog>
      </header>
      <div class="clearfix">
        <ul className="left-pane">
          <List
            id={id}
            showMessages={showMessages}
            setCurrent={setCurrentFriend}
          />
        </ul>
        <div className="content">
          <div className="message-area" ref={messageAreaRef}>
            {messageResponse.map((message) => (
              <>
                <span
                  className={`message-item ${
                    message.senderId === id ? "sent" : "received"
                  }`}
                >
                  {message.messageBody}
                </span>
                <br></br>
              </>
            ))}
          </div>
          <div className="input-area">
            <MessageFooter
              senderId={id}
              receiverId={currentFriend}
              flagToConnect={flagToConnect}
              setMessageResponse={setMessageResponse}
            />
          </div>
        </div>
      </div>
      <footer>MessageHub</footer>
    </>
  );
};

export default Messages;
