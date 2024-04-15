import { ActivatedRoute, Router } from '@angular/router';
import { StudentService } from './student.service'
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Student} from './student';
import { Course } from './course';

@Component({
  selector: 'update-student',
  templateUrl: './update-student.component.html',
  styleUrls: ['./update-student.component.css'],
  providers: [StudentService]
})
export class UpdateStudentComponent implements OnInit {

  studentForm!: FormGroup;
  email!: string;
  courses: Course[] = [];

  constructor(private fb: FormBuilder,
    private route: ActivatedRoute,
    private studentService: StudentService,
    private router: Router) { }

  ngOnInit() {
    this.studentForm = this.fb.group({
      name: [''],
      gender: [''],
      email: [''],
      phone: [''],
      courses: []
    })

    this.getCourses();
    ;

    const emailParam = this.route.snapshot.paramMap.get('email');
    this.email = emailParam ? emailParam : '';

    this.studentService.getStudent(this.email).subscribe(student => {
      console.log('Student:', student);
      if (student && student.name) {
        this.studentForm.patchValue({
          name: student.name,
          gender: student.gender,
          email: student.email,
          phone: student.phone
        });
      }
    });
  }

  updateStudent() {
    this.studentService.updateStudent(this.studentForm.value, this.email).subscribe(() => {
      console.log('Student updated successfully');
      this.router.navigate(['/app-student']);
    });
  }

  onCoursesChange(target: EventTarget | null) {
    if (target) {
      const selectedOptions = (target as HTMLSelectElement).selectedOptions;
      const courses = Array.from(selectedOptions).map(option => option.value);
      this.studentForm.get('courses')?.setValue(courses);
    }
  }

  getCourses() {
    this.studentService.getCourses()
    .subscribe(courses => this.courses = courses);
  }
}
