import java.util.Date;
public class Appointment {
    private int id;
    private int patientId;
    private int doctorId;
    private Date date;

    public Appointment(int patientId, int doctorId, Date date) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
    }

    public Appointment(int id, int patientId, int doctorId, Date date) {
        this(patientId, doctorId, date);
        this.id = id;
    }

    public int getId() { return id; }
    public int getPatientId() { return patientId; }
    public int getDoctorId() { return doctorId; }
    public Date getDate() { return date; }

    public String toString() {
        return id + " | Patient ID: " + patientId + " | Doctor ID: " + doctorId + " | Date: " + date;
    }
}
