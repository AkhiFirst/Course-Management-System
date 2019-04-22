import {AbstractControl} from '@angular/forms';
export class DropdownValidation {

    static DeptRequired(AC: AbstractControl) {
       let deptName = AC.get('deptName').value; // to get value in input tag
       //let confirmPassword = AC.get('confirmPassword').value; // to get value in input tag
        if(deptName === '') {
            AC.get('deptName').setErrors( {DeptRequired: true} )
        } else {
            return null
        }
    }
}