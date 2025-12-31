// File 1: HospitalManagement.java (Main Class)
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class HospitalManagement {
    private static final Scanner scanner = new Scanner(System.in);
    private static final PatientDAO patientDAO = new PatientDAO();
    private static final DoctorDAO doctorDAO = new DoctorDAO();
    private static final AppointmentDAO appointmentDAO = new AppointmentDAO();

    public static void main(String[] args) {
        initializeDatabase();
        showMainMenu();
    }

    private static void initializeDatabase() {
        try {
            DatabaseConnection.initialize();
            System.out.println("Database initialized successfully!");
        } catch (SQLException e) {
            System.err.println("Database initialization failed: " + e.getMessage());
            System.exit(1);
        }
    }

    private static void showMainMenu() {
        while (true) {
            System.out.println("\n===== Hospital Management System =====");
            System.out.println("1. Patient Management");
            System.out.println("2. Doctor Management");
            System.out.println("3. Appointment Management");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            switch (getIntInput()) {
                case 1 -> showPatientMenu();
                case 2 -> showDoctorMenu();
                case 3 -> showAppointmentMenu();
                case 0 -> {
                    System.out.println("Exiting system...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    // Patient Management Menu
    private static void showPatientMenu() {
        while (true) {
            System.out.println("\n===== Patient Management =====");
            System.out.println("1. Add New Patient");
            System.out.println("2. View All Patients");
            System.out.println("3. Update Patient");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter choice: ");

            switch (getIntInput()) {
                case 1 -> addPatient();
                case 2 -> viewAllPatients();
                case 3 -> updatePatient();
                case 4 -> { return; }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    // Doctor Management Menu
    private static void showDoctorMenu() {
        while (true) {
            System.out.println("\n===== Doctor Management =====");
            System.out.println("1. Add New Doctor");
            System.out.println("2. View All Doctors");
            System.out.println("3. Update Doctor");
            System.out.println("4. Delete Doctor");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter choice: ");

            switch (getIntInput()) {
                case 1 -> addDoctor();
                case 2 -> viewAllDoctors();
                case 3 -> updateDoctor();
                case 4 -> deleteDoctor();
                case 5 -> { return; }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    // Appointment Management Menu
    private static void showAppointmentMenu() {
        while (true) {
            System.out.println("\n===== Appointment Management =====");
            System.out.println("1. Create New Appointment");
            System.out.println("2. View All Appointments");
            System.out.println("3. Update Appointment");
            System.out.println("4. Delete Appointment");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter choice: ");

            switch (getIntInput()) {
                case 1 -> addAppointment();
                case 2 -> viewAllAppointments();
                case 3 -> updateAppointment();
                case 4 -> deleteAppointment();
                case 5 -> { return; }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    // CRUD Operations
    private static void addPatient() {
        System.out.print("Enter patient name: ");
        String name = scanner.nextLine();
        System.out.print("Enter patient age: ");
        int age = getIntInput();
        System.out.print("Enter patient gender: ");
        String gender = scanner.nextLine();
        System.out.print("Enter patient address: ");
        String address = scanner.nextLine();

        try {
            Patient patient = new Patient(name, age, gender, address);
            patientDAO.addPatient(patient);
            System.out.println("Patient added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding patient: " + e.getMessage());
        }
    }

    private static void viewAllPatients() {
        try {
            List<Patient> patients = patientDAO.getAllPatients();
            patients.forEach(System.out::println);
        } catch (SQLException e) {
            System.out.println("Error retrieving patients: " + e.getMessage());
        }
    }

    private static void updatePatient() {
        System.out.print("Enter patient ID to update: ");
        int id = getIntInput();
        System.out.print("Enter new name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new age: ");
        int age = getIntInput();
        System.out.print("Enter new gender: ");
        String gender = scanner.nextLine();
        System.out.print("Enter new address: ");
        String address = scanner.nextLine();

        try {
            Patient patient = new Patient(id, name, age, gender, address);
            patientDAO.updatePatient(patient);
            System.out.println("Patient updated successfully!");
        } catch (SQLException e) {
            System.out.println("Error updating patient: " + e.getMessage());
        }
    }

    private static void addDoctor() {
        System.out.print("Enter doctor name: ");
        String name = scanner.nextLine();
        System.out.print("Enter specialization: ");
        String specialization = scanner.nextLine();

        try {
            Doctor doctor = new Doctor(name, specialization);
            doctorDAO.addDoctor(doctor);
            System.out.println("Doctor added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding doctor: " + e.getMessage());
        }
    }

    private static void viewAllDoctors() {
        try {
            List<Doctor> doctors = doctorDAO.getAllDoctors();
            doctors.forEach(System.out::println);
        } catch (SQLException e) {
            System.out.println("Error retrieving doctors: " + e.getMessage());
        }
    }

    private static void updateDoctor() {
        System.out.print("Enter doctor ID to update: ");
        int id = getIntInput();
        System.out.print("Enter new name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new specialization: ");
        String specialization = scanner.nextLine();

        try {
            Doctor doctor = new Doctor(id, name, specialization);
            doctorDAO.updateDoctor(doctor);
            System.out.println("Doctor updated successfully!");
        } catch (SQLException e) {
            System.out.println("Error updating doctor: " + e.getMessage());
        }
    }

    private static void deleteDoctor() {
        System.out.print("Enter doctor ID to delete: ");
        int id = getIntInput();
        try {
            doctorDAO.deleteDoctor(id);
            System.out.println("Doctor deleted successfully!");
        } catch (SQLException e) {
            System.out.println("Error deleting doctor: " + e.getMessage());
        }
    }

    private static void addAppointment() {
        System.out.print("Enter patient ID: ");
        int patientId = getIntInput();
        System.out.print("Enter doctor ID: ");
        int doctorId = getIntInput();
        Date date = getDateInput();

        try {
            Appointment appointment = new Appointment(patientId, doctorId, date);
            appointmentDAO.addAppointment(appointment);
            System.out.println("Appointment added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding appointment: " + e.getMessage());
        }
    }

    private static void viewAllAppointments() {
        try {
            List<Appointment> appointments = appointmentDAO.getAllAppointments();
            appointments.forEach(System.out::println);
        } catch (SQLException e) {
            System.out.println("Error retrieving appointments: " + e.getMessage());
        }
    }

    private static void updateAppointment() {
        System.out.print("Enter appointment ID to update: ");
        int id = getIntInput();
        System.out.print("Enter new patient ID: ");
        int patientId = getIntInput();
        System.out.print("Enter new doctor ID: ");
        int doctorId = getIntInput();
        Date date = getDateInput();

        try {
            Appointment appointment = new Appointment(id, patientId, doctorId, date);
            appointmentDAO.updateAppointment(appointment);
            System.out.println("Appointment updated successfully!");
        } catch (SQLException e) {
            System.out.println("Error updating appointment: " + e.getMessage());
        }
    }

    private static void deleteAppointment() {
        System.out.print("Enter appointment ID to delete: ");
        int id = getIntInput();
        try {
            appointmentDAO.deleteAppointment(id);
            System.out.println("Appointment deleted successfully!");
        } catch (SQLException e) {
            System.out.println("Error deleting appointment: " + e.getMessage());
        }
    }

    // Utility Methods
    private static int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    private static Date getDateInput() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        while (true) {
            System.out.print("Enter date (yyyy-MM-dd): ");
            String dateStr = scanner.nextLine();
            try {
                return sdf.parse(dateStr);
            } catch (ParseException e) {
                System.out.println("Invalid date format. Try again.");
            }
        }
    }
}