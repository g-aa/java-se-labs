package task11_studentslecturers;;

import java.util.HashMap;
import java.util.Map;

public class FacultyData {

    private Map<String, String> m_lecturer;
    private Map<String, Integer> m_student;

    public FacultyData() {
        m_lecturer = new HashMap<String, String>();
        m_student = new HashMap<String, Integer>();
    }

    public boolean setStudent(String name, String age) throws IllegalArgumentException {
        if (!m_student.containsKey(name)) {
            if (name == null || "".equals(name.trim())) {
                throw new IllegalArgumentException("Имя не может принимать пустое значение или null!");
            }

            if (age == null || "".equals(age.trim()))
                throw new IllegalArgumentException("Возраст не может принимать пустое значение или null!");

            try {
                int temp = Integer.parseInt(age);
                if (temp < 0 ) {
                    throw new IllegalArgumentException("Возраст не может принимать отрицательное значение!");
                }
                m_student.put(name, temp);
                return true;
            }
            catch (NumberFormatException e) {
                throw new IllegalArgumentException("В поле возраст было введено не число!");
            }

        }
        return false;
    }

    public boolean setLecturer(String name, String degree) throws IllegalArgumentException {
        if(!m_lecturer.containsKey(name)) {
            if (name == null || "".equals(name.trim())) {
                throw new IllegalArgumentException("Имя не может принимать пустое значение или null!");
            }
            if (degree == null || !("phd".equals(degree.toLowerCase()) || "scd".equals(degree.toLowerCase()))){
                throw new IllegalArgumentException("Параметр \"degree\" может принимать только значения: PhD, ScD!");
            }
            m_lecturer.put(name, degree);
            return true;
        }
        return false;
    }

    public int getStudentCount() {
        return m_student.size();
    }

    public int getLecturerCount() {
        return m_lecturer.size();
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();

        if (m_lecturer.size() != 0){
            buffer.append("Faculty data ").append(m_lecturer.size()).append(" elements :\n");
            for (Map.Entry<String, String> item : m_lecturer.entrySet()) {
                buffer.append("Name: ").append(item.getKey()).append(", degree: ").append(item.getValue()).append("\n");
            }
        }

        if(m_student.size() != 0) {
            buffer.append("Student data:").append(m_student.size()).append(" elements :\n");
            ;
            for (Map.Entry<String, Integer> item : m_student.entrySet()) {
                buffer.append("Name: ").append(item.getKey()).append(", age: ").append(item.getValue()).append("\n");
            }
        }
        return buffer.toString();
    }
}
