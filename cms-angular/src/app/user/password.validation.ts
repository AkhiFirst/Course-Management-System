import {AbstractControl} from '@angular/forms';
export class PasswordValidation {

    static MatchPassword(AC: AbstractControl) {
        // if(AC.get('password').value!=null && AC.get('confirmPassword').value!=null)
        // {
       let password = AC.get('password').value; // to get value in input tag
       let confirmPassword = AC.get('confirmPassword').value; // to get value in input tag
        if(password != confirmPassword) {
            AC.get('confirmPassword').setErrors( {MatchPassword: true} )
        } else {
            //console.log('true');
            return null
        }
   // }
    }
    
    static MatchUsername(AC: AbstractControl){
        // if(AC.get('username').value!=null && AC.get('confirmUsername').value!=null){
        let username = AC.get('username').value; // to get value in input tag
       let confirmUsername = AC.get('confirmUsername').value; // to get value in input tag
        if(username != confirmUsername) {
            AC.get('confirmUsername').setErrors( {MatchUsername: true} )
        } else {
            //console.log('true');
            return null
        }
    //}
    }
}