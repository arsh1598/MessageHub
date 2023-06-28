import axios from "axios";
import React from "react";
import { useState } from "react";
import { useEffect } from "react";

const List = ({ id, showMessages, setCurrent }) => {
  const [users, setUsers] = useState([]);
  useEffect(() => {
    getContacts();
  }, []);
  const getContacts = async () => {
    await axios
      .get(`/messagehub/rest/contacts/${id}`)
      .then((response) => {
        setUsers(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const handleClick = async (user) => {
    setCurrent(user.id);
    const messageRequest = {
      senderId: id,
      receiverId: user.id,
    };
    await showMessages(messageRequest);
  };

  return (
    <>
      {users.map((user) => (
        <li key={user.id} onClick={() => handleClick(user)}>
          {user.name}
        </li>
      ))}
    </>
  );
};

export default List;
