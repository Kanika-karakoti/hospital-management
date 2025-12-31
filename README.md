# FreakOFit Gym - Full Project
## What is included
- Express backend (routes, controllers)
- Nodemailer email sending (uses Gmail App Password)
- Simple file-based appointment storage (backend/data/appointments.json)
- Frontend pages (index, appointment form, admin dashboard, login)
- CORS enabled

## Setup
1. Open terminal and go to backend folder:
   ```
   cd backend
   ```
2. Install dependencies:
   ```
   npm install
   ```
3. Edit `.env` and set your real values:
   - ADMIN_EMAIL (where admin logs in)
   - ADMIN_PASSWORD (admin password)
   - SMTP_EMAIL (your gmail)
   - SMTP_PASS (your 16-char app password from Google)
4. Start server:
   ```
   npm start
   ```
5. Open in browser:
   - http://localhost:3000  (site)
   - http://localhost:3000/appointment/appointment.html (booking)
   - http://localhost:3000/login.html (admin login)
   - http://localhost:3000/admin-dashboard.html (appointments list; protected by simple login)
