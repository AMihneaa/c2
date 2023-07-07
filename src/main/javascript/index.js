/*
import ReactDOM from "react-dom"
import { App } from "./App"
const app = document.getElementById("app")
ReactDOM.render(<App />, app)*/

import React from 'react';
import ReactDOM from 'react-dom/client';

import App from "./App";

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <React.StrictMode>
        <App/>
    </React.StrictMode>
);