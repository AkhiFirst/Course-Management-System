import {AbstractControl} from '@angular/forms';
export class UsernameValidation {

    static MatchUsername(AC: AbstractControl) {
       let username = AC.get('username').value; // to get value in input tag
       let confirmUsername = AC.get('confirmUsername').value; // to get value in input tag
        if(username != confirmUsername) {
            console.log('false');
            AC.get('confirmUsername').setErrors( {MatchUsername: true} )
        } else {
            //console.log('true');
            return null
        }
    }
}