import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Student } from './student';
import { Course } from './course';
import { StudentFormValue } from './student-form';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  private studentApiUrl = 'http://localhost:8090/api/student-details';
  private coursesApiUrl = 'http://localhost:8090/api/course-details';

  constructor(private http: HttpClient) { }

  getStudents(): Observable<Student[]> {
    return this.http.get<Student[]>(this.studentApiUrl);
  }

  getStudent(email: string): Observable<Student> {
    return this.http.get<Student>(`${this.studentApiUrl}/${email}`);
  }

  addStudent(student: StudentFormValue): Observable<Student> {
    // const studentToSubmit = {
    //   ...student,
    //   courses: student.courses.map(courseId => ({ id: courseId }))
    // };
    return this.http.post<Student>(this.studentApiUrl, student);
  }

  updateStudent(student: Student, email: string): Observable<Student> {
    return this.http.put<Student>(`${this.studentApiUrl}/${email}`, student);
  }

  deleteStudent(email: string): Observable<void> {
    const url = `${this.studentApiUrl}/${email}`;
    return this.http.delete<void>(url);
  }

  searchStudents(searchTerm: string): Observable<Student[]> {
    const params = new HttpParams().set('name', searchTerm);
    return this.http.get<Student[]>(`${this.studentApiUrl}/search`, { params });
  }

  getCourses(): Observable<Course[]> {
    return this.http.get<Course[]>(this.coursesApiUrl);
  }
}
