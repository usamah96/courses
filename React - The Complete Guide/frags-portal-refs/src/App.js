import React, { useState } from "react";
import TypeOne from "./components/fragments/TypeOne";
import TypeTwo from "./components/fragments/TypeTwo";
import TypeThree from "./components/fragments/TypeThree";

import AddUser from "./components/Users/AddUser";
import UsersList from "./components/Users/UsersList";
import Wrapper from "./components/wrapper/Wrapper";

function App() {
  const [usersList, setUsersList] = useState([]);

  const addUserHandler = (uName, uAge) => {
    setUsersList((prevUsersList) => {
      return [
        ...prevUsersList,
        { name: uName, age: uAge, id: Math.random().toString() },
      ];
    });
  };

  return (
    <Wrapper>
      <AddUser onAddUser={addUserHandler} />
      <UsersList users={usersList} />
      <div style={{ textAlign: "center" }}>
        <h1 style={{ color: "white" }}>----Fragments----</h1>
        <TypeOne />
        <TypeTwo />
        <TypeThree />
      </div>
    </Wrapper>
  );
}

export default App;
