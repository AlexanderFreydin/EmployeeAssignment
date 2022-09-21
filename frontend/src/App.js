import logo from './logo.svg';
import './App.css';
import React, { useState } from "react";

class App extends React.Component {
  render() {
    return (
        <EmployeeList/>
    )
  }
}

class EmployeeList extends React.Component{
    constructor(props) {
        super(props);
        this.state = {employees: []};
        this.updateTable = this.updateTable.bind(this);
        this.deleteEmployee = this.deleteEmployee.bind(this);
    }

    async componentDidMount() {
        const response = await fetch('/api/employees');
        const body = await response.json();
        this.setState({employees: body});
    }

    updateTable() {
        console.log("Table update")
        this.componentDidMount();
    }

     deleteEmployee(empID){
        fetch('/api/employees/'+empID, {
            method: 'DELETE'
        }).then(res => {
            this.updateTable();
        });
    }

    // editEmployee(emp){
    //     fetch('/api/employees/'+emp.id, {
    //         method: 'PUT',
    //         body: {
    //
    //         }
    //     }).then(res => {
    //         this.updateTable();
    //     });
    // }



  render() {
    const employees = this.state.employees.map(employee =>
        <Employee key={employee.id} employee={employee} deleteEmployeeCallback = {this.deleteEmployee} updateTableCallback={this.updateTable}/>
    );

    return (
        <div className="container">
            <div className="table">
                <div className="table-wrapper">
                    <div className="table-title">
                        <div className="row">
                            <div className="col-sm-8"><h2>Employee <b>Details</b></h2></div>
                            <div className="col-sm-4">

                            </div>
                        </div>
                    </div>
                    <table className="table table-striped table-hover table-bordered">
                        <thead>
                        <tr>
                            <th>Id </th>
                            <th>first name</th>
                            <th>last name</th>
                            <th>email</th>
                            <th>Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        {employees}
                        </tbody>
                    </table>
                </div>
            </div>
            <CreationForm updateTableCallback = {this.updateTable}/>
        </div>


    )
  }
}

// class Employee extends React.Component{
function Employee({deleteEmployeeCallback, updateTableCallback, employee}) {
    // editMode = true;

    // const deleteEmployee =  (empID) => {
    //     // console.log(empID);
    //
    //     fetch('/api/employees/'+empID, {
    //         method: 'DELETE'
    //     }).then(res => {
    //         updateTableCallback();
    //     });
    // }


    return (
        <tr>
            {/*<td>{(()=>{*/}
            {/*    if (editMode){*/}
            {/*        return (*/}
            {/*            <td><input name="empId" placeholder={employee.id}/></td>*/}
            {/*        )*/}
            {/*    }*/}
            {/*    else {*/}
            {/*        return (*/}
            {/*            <td>{employee.id}</td>*/}
            {/*        )*/}
            {/*    }*/}
            {/*})()}</td>*/}
          <td>{employee.id}</td>
          <td>{employee.firstName}</td>
          <td>{employee.lastName}</td>
          <td>{employee.email}</td>
            <td>
                <a href="#" className="edit" title="Edit" data-toggle="tooltip"><i
                    className="material-icons">&#xE254;</i></a>
                <a key={employee.id} href="#" className="delete" title="Delete" data-toggle="tooltip" onClick={() => deleteEmployeeCallback(employee.id)}><i
                    className="material-icons">&#xE872;</i></a>
            </td>
        </tr>
    )

}

function CreationForm({updateTableCallback}) {
    const [inputs, setInputs] = useState({});

    const handleChange = (event) => {
        const name = event.target.name;
        const value = event.target.value;
        setInputs(values => ({...values, [name]: value}))
    }

    const handleSubmit =  (event) => {
        event.preventDefault();
        // console.log(inputs);

        fetch('/api/employees', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(inputs)
        }).then(res => {
            updateTableCallback();
        });
    }

    return (
    <form onSubmit={handleSubmit}>
        <div className="bold">Add Employee</div>

        <div className="form-group">
            <input type="text" className="form-control m-2" name="firstName" placeholder="First Name" onChange={handleChange}/>
        </div>
        <div className="form-group">
            <input type="text" className="form-control m-2" name="lastName" placeholder="Last Name" onChange={handleChange}/>
        </div>
        <div className="form-group">
            <input type="email" className="form-control m-2" name="email" aria-describedby="emailHelp"
                   placeholder="Email" onChange={handleChange}/>
        </div>
        <button type="submit" className="btn btn-primary m-2">Submit</button>
    </form>
    )
}

export default App;
