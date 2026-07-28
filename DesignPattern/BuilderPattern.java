package DesignPattern;

public class BuilderPattern{
public static void main(String[] args) {

    Employee e1= new Employee.EmployeeBuilder()
                             .setAddress("Stark Towers")
                             .setEmployeeName("Karthikeyan")
                             .setGrade("C1")
                             .setDept("Engineering")
                             .setEmailId("starkpotts@gmail.com")
                             .setContactNumber("1234567890")
                             .build();
    System.out.println(e1);

    }
    public static class Employee{

        private int id;
        private String employeeName;
        private String grade;
        private String role;
        private String dept;
        private String emailId;
        private String contactNumber; 
        private Boolean maritalStatus;
        private String partnerName; 
        private String address;

        

        @Override
        public String toString() {
            return "Employee [id=" + id + ", employeeName=" + employeeName + ", grade=" + grade + ", role=" + role
                    + ", dept=" + dept + ", emailId=" + emailId + ", contactNumber=" + contactNumber
                    + ", maritalStatus=" + maritalStatus + ", partnerName=" + partnerName + ", address=" + address
                    + "]";
        }
        private Employee(){}
        private Employee(EmployeeBuilder builder) {
            this.id = builder.id;
            this.employeeName = builder.employeeName;
            this.grade = builder.grade;
            this.role = builder.role;
            this.dept = builder.dept;
            this.emailId = builder.emailId;
            this.contactNumber = builder.contactNumber;
            this.maritalStatus = builder.maritalStatus;
            this.partnerName = builder.partnerName;
            this.address = builder.address;
        }
        public static class EmployeeBuilder{
            
        private int id;
        private String employeeName;
        private String grade;
        private String role;
        private String dept;
        private String emailId;
        private String contactNumber; 
        private Boolean maritalStatus;
        private String partnerName; 
        private String address;

        public EmployeeBuilder setId(int id) {
            this.id = id;
            return this;
        }
        public EmployeeBuilder setEmployeeName(String employeeName) {
            this.employeeName = employeeName;
            return this;
        }
        public EmployeeBuilder setGrade(String grade) {
            this.grade = grade;
            return this;
        }
        public EmployeeBuilder setRole(String role) {
            this.role = role;
            return this;
        }
        public EmployeeBuilder setDept(String dept) {
            this.dept = dept;
            return this;
        }
        public EmployeeBuilder setEmailId(String emailId) {
            this.emailId = emailId;
            return this;
        }
        public EmployeeBuilder setContactNumber(String contactNumber) {
            this.contactNumber = contactNumber;
            return this;
        }
        public EmployeeBuilder setMaritalStatus(Boolean maritalStatus) {
            this.maritalStatus = maritalStatus;
            return this;
        }
        public EmployeeBuilder setPartnerName(String partnerName) {
            this.partnerName = partnerName;
            return this;
        }
        public EmployeeBuilder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Employee build(){
            return new Employee(this);
        }

        }

        
        

    }
}