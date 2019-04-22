export class User {
    userId: String;
    firstName: String;
    lastName: String;
    email: String;
    username: String;
    password: String;
    id: String;
    userExists: Boolean;
    validUser: Boolean;
    constructor() {
        this.userId='';
        this.firstName = '';
        this.lastName = '';
        this.email='';
        this.username = '';
        this.password = '';
        this.id = '';
        this.userExists = false;
        this.validUser = false;
    }
}
