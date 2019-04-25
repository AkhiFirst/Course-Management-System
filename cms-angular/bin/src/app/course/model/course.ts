export class Course {
    course_id: String;
    isTA: String;
    title: String;
    semester: String;
    instructorId: Number;
    type:String;
    courseFileName:String;
    constructor() {
        this.course_id='';
        this.isTA = '';
        this.title = '';
        this.semester = '';
        this.instructorId = 0;
        this.type = "Courses";
        this.courseFileName = '';
    }
}
