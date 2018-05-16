import axios from "axios";
import React, {Component} from "react";
import logo from "./logo.svg";
import "./App.css";

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            object: {
                time: []
            }
        };

        this.getEcg1Point = this.getEcg1Point.bind(this);
    }


    getEcg1Point() {
        axios.get('http://localhost:8888/data/getECG1Past', {
            params: {
                id: 1,
                beginning: '2018-05-10T00:00:00.000000000Z',
                ending: '2019-01-01T00:00:00.000000000Z'
            }
        })
            .then((response) => {
                var contentKeys = Object.keys(response.data);


                this.setState({
                    object: {
                        time: contentKeys.map((t) => response.data[t].map((e) => (<div>{e.time}</div>)))

                    }
                })
            });
        /* this.setState({
         object: {
         data: response.data
         }
         }));*/


    }


    render() {

        return (
            <div className="App">
                <header className="App-header">
                    <img src={logo} className="App-logo" alt="logo"/>
                    <h1 className="App-title">Welcome to React</h1>
                </header>
                <p className="App-intro">
                    To get started,{this.state.object.data} edit <code>src/App.js</code> and save to reload.
                </p>

                <button onClick={this.getEcg1Point}>BLABLA</button>
            </div>
        );
    }
}

export default App;
