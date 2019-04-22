export class User {
    firstName: String;
    lastName: String;
    email: String;
    deptName: String;
    username: String;
    password: String;
    id: String;
    userExists: Boolean;
    validUser: Boolean;
    constructor() {
        this.firstName = '';
        this.lastName = '';
        this.email='';
        this.deptName='';
        this.username = '';
        this.password = '';
        this.id = '';
        this.userExists = false;
        this.validUser = false;
    }
}
