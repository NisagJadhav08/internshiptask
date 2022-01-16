import java.io.*;
import java.sql.*;
import java.util.Scanner;

 class StudentCRUD{
    public static void main(String args[]){
        try{
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/student","postgres","password");
            Statement st = conn.createStatement();
            ResultSet rs = null;
            Scanner sc = new Scanner(System.in);
            if(conn!= null){
                System.out.println("connected");
            }
            else{
                System.out.println("not conncted");
            }

            System.out.println("Menu: 1. Insert \n 2. Update  \n 3. Delete \n 4. Get a list of all Student \n 5. Get student info by id");
            System.out.println("Enter your choice:");
            int choice = sc.nextInt();
            switch(choice)
            {
            // insert student data into student table
            case 1: System.out.println("Enter Student Name");
            String name = sc.next();

            System.out.println("Enter Roll No");
            int roll_no = sc.nextInt();

            System.out.println("Enter Students Total Marks");
            float marks = sc.nextFloat();
            String insertQuery = "insert into student(name,roll_no,marks)values('" + name + "'," + roll_no + "," + marks + ")";
            st.executeUpdate(insertQuery);
            break;
            //update student data into Student table
            case 2: System.out.println("Enter Roll No to update data");
                    int roll_no_update = sc.nextInt();
                    System.out.println("Updation keys: 1.Name 2. Marks");

                    int key = sc.nextInt();

                    if(key==1){
                        System.out.println("Enter new name");
                        String update_name = sc.next();

                        String updateNameQuery = "update student set name = '" + update_name + "' where roll_no = " + roll_no_update;
                        st.executeUpdate(updateNameQuery);
                    }
                    if(key==2){
                         System.out.println("Enter marks to update");
                        float update_marks = sc.nextFloat();

                        String updateMarksQuery = "update student set marks = " + update_marks + "where roll_no = " + roll_no_update;
                        st.executeUpdate(updateMarksQuery);
                    }
            break;
            
            //Delete student data from student table
            case 3: System.out.println("Enter Student id to delete data");
                    int id = sc.nextInt();

                    String deleteQuery = "delete from student where id = " + id;
                    st.executeUpdate(deleteQuery);
                    System.out.println("student data deleted successfully");
                    break;
            
            //Get a list of all Student
            case 4: System.out.println("student list");
            String selectQuery = "select * from student";
            rs = st.executeQuery(selectQuery);
            System.out.println("id \t\t Name \t\t Roll No \t\t Marks");
            while(rs.next()){
                System.out.println(rs.getInt(1) + "\t\t" + rs.getString(2) + "\t\t" + rs.getInt(3) + "\t\t" +rs.getFloat(4));
            }
            break;

            //Get one student information by entering id of student
            case 5: System.out.println("Enter student id");
                    int sid = sc.nextInt();

                    String getSelectQuery = "select * from student where id = " + sid;
                    ResultSet rs1 = st.executeQuery(getSelectQuery);

                    System.out.println("id \t\t Name \t\t Roll No \t\t Marks");
            while(rs1.next()){
                System.out.println(rs1.getInt(1) + "\t\t" + rs1.getString(2) + "\t\t" + rs1.getInt(3) + "\t\t" +rs1.getFloat(4));
            }
            break;
            default: break;
            }
            st.close();
            conn.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}