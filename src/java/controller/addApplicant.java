/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.EmployeeDAO;
import dao.ScheduleDAO;
import entity.Criteria;

import entity.EmployeeJobInfo;
import entity.EmployeePersonalInfo;
import entity.EmployeePhysicalInfo;
import entity.Schedule;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Blob;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author John San Agustin
 */
@WebServlet("/addApplicantion")
@MultipartConfig(maxFileSize = 16177215) 
public class addApplicant extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
       
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(addApplicant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

 /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      try {
            EmployeePersonalInfo personalInfo = new EmployeePersonalInfo();
            EmployeePhysicalInfo physicalInfo = new EmployeePhysicalInfo();
            EmployeeJobInfo jobInfo = new EmployeeJobInfo();
            
            InputStream inputStream = null; 
            InputStream inputStream2 = null; 
         
            Part applicantResume = request.getPart("applicantResume");
            
            Part applicantPhoto = request.getPart("applicantPhoto");
            if (applicantResume != null) {
            
            System.out.println(applicantResume.getName());
            System.out.println(applicantResume.getSize());
            System.out.println(applicantResume.getContentType());
             
            
            inputStream = applicantResume.getInputStream();
            }
            
            if (applicantPhoto != null) {
            
            System.out.println(applicantPhoto.getName());
            System.out.println(applicantPhoto.getSize());
            System.out.println(applicantPhoto.getContentType());
             
            
            inputStream2 = applicantPhoto.getInputStream();
            }
            
            personalInfo.setLastName(request.getParameter("applicantLastName"));
            personalInfo.setFirstName(request.getParameter("applicantFirstName"));
            personalInfo.setMiddleName(request.getParameter("applicantMiddleName"));
            personalInfo.setNickname(request.getParameter("applicantNickname"));
            
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String birthDate = request.getParameter("BirthDate");
            String driverslicensedate;
            String securitylicensedate;
            String TINdate;
            
            
            System.out.println(birthDate);
            Date date;
            date = format.parse(birthDate);
            java.sql.Date sql = new java.sql.Date(date.getTime());
            personalInfo.setBirthday(sql);
            
            java.sql.Date dateInputted = new java.sql.Date(Calendar.getInstance().getTime().getTime());

            personalInfo.setDateInput(dateInputted);
            
            personalInfo.setAge(Integer.parseInt(request.getParameter("applicantAge")));
            personalInfo.setSex(request.getParameter("applicantSex"));
            personalInfo.setReligion(request.getParameter("applicantReligion"));
            personalInfo.setCellphoneNo(request.getParameter("applicantCellNo")); 
            personalInfo.setTelephoneNo(request.getParameter("applicantTelNo")); 
            personalInfo.setEducation(request.getParameter("applicantEducation"));
            personalInfo.setCity(request.getParameter("applicantCity"));          
            
            physicalInfo.setHeight(Integer.parseInt(request.getParameter("applicantHeight")));
            physicalInfo.setWeight(Integer.parseInt(request.getParameter("applicantWeight")));
            physicalInfo.setSears(request.getParameter("applicantDistinguishingMarks"));
            physicalInfo.setPhysicalDefects(request.getParameter("applicantPhysicalDefects"));
            physicalInfo.setIllnesses(request.getParameter("applicantRecentSeriousIllness"));
            physicalInfo.setBodyBuild(request.getParameter("buildRadios"));
            physicalInfo.setSkinColor(request.getParameter("skinRadios"));
            physicalInfo.setStateOfHealth(request.getParameter("healthRadios"));
            
            jobInfo.setDriversLicense(request.getParameter("applicantDriversLicenseNo"));
            
            driverslicensedate = request.getParameter("driverslicenseexpirydate");
            date = format.parse(driverslicensedate);
            java.sql.Date sql2 = new java.sql.Date(date.getTime());
            jobInfo.setDriversLicenseExpDate(sql2);
            
            jobInfo.setLicenseNo(request.getParameter("applicantSecurityLicenseNo"));
            
            jobInfo.setLicenseType(request.getParameter("applicantSecurityLicenseType"));
            
            
            securitylicensedate = request.getParameter("securitylicenseexpirydate");
            date = format.parse(securitylicensedate);
            java.sql.Date sql3 = new java.sql.Date(date.getTime());
            jobInfo.setLicenseExpDate(sql3);
            
            jobInfo.setTINno(request.getParameter("applicantTINNo"));
            
            
            TINdate = request.getParameter("applicantTINexpirydate");
            date = format.parse(TINdate);
            java.sql.Date sql4 = new java.sql.Date(date.getTime());
            jobInfo.setTINdate(sql4);
            
            personalInfo.setMarriageStatus(request.getParameter("marriageStatus"));
            
            jobInfo.setTrainingAttended(request.getParameter("applicantTrainings"));
            jobInfo.setInclusiveDate(request.getParameter("applicantEmployerDate1"));
            jobInfo.setFormerJob(request.getParameter("applicantEmployerJob1"));
            jobInfo.setFormerEmployer(request.getParameter("applicantEmployerName1"));
            jobInfo.setReasonForLeaving(request.getParameter("applicantEmployerReason1"));
            
            
            EmployeeDAO employeeDAO = new EmployeeDAO();
            Criteria criteria = employeeDAO.getCriteria();
            
            long difference = sql3.getTime()-dateInputted.getTime();
            long daysDifference = TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS)+1;
            
            int training = 0;
            String trainingAttended = request.getParameter("applicantTrainings");
            
            switch(trainingAttended){
                case "Pre-licensing Training": training = 1;break;
                case "Others": training = 2;break;
            }
            
            int trainingNeeded = 0;
            String trainingRequirement = criteria.getTrainingAttended();
            
            switch(trainingRequirement){
                case "Pre-licensing Training": trainingNeeded = 1;break;
                case "Others": trainingNeeded = 2;break;
            }
            
             System.out.println(trainingAttended);
                System.out.println(trainingRequirement);
            
            if(!(personalInfo.getAge() >= criteria.getMinAge() && personalInfo.getAge() <= criteria.getMaxAge())){
                personalInfo.setStatus("Not Qualified");
                personalInfo.setDetails("Age");
            }else if(!(physicalInfo.getHeight() >= criteria.getMinHeight())){
                personalInfo.setStatus("Not Qualified");
                personalInfo.setDetails("Height");
            }
            else if(!(physicalInfo.getWeight() >= criteria.getMinWeight() && (physicalInfo.getWeight() <= criteria.getMaxWeight()))){
                personalInfo.setStatus("Not Qualified");
                personalInfo.setDetails("Weight");
            }
            else if(trainingNeeded == 2 && training != 2){
                personalInfo.setStatus("Not Qualified");
                personalInfo.setDetails("Training Defeciency");
                
            }
             else if(!(daysDifference >= 365)){
                personalInfo.setStatus("Not Qualified");
                personalInfo.setDetails("Nearing License Expiry");
                
                
                
            }
            else{
                
                personalInfo.setStatus("Interview Scheduled");
            }
  
            boolean successful = employeeDAO.inputPersonalInfo(personalInfo,inputStream,inputStream2);
            boolean successful2 = employeeDAO.inputJobInfo(jobInfo);
            boolean successful3 = employeeDAO.inputPhysicalInfo(physicalInfo);
            
            if(personalInfo.getStatus().equalsIgnoreCase("Interview Scheduled")){
                int employeeID = employeeDAO.getEmployeeID();
                System.out.println(employeeID);
                ScheduleDAO scheduleDAO = new ScheduleDAO();
                Schedule schedule = new Schedule();
                
                schedule.setEmployeeID(employeeID);
                scheduleDAO.inputSchedule(schedule);
                
            }
            
            
            
            
            
            if (successful == true && successful2 == true && successful3 == true){
                ServletContext context= getServletContext();
                RequestDispatcher rd= context.getRequestDispatcher("/dashboard.jsp");
                HttpSession session = request.getSession();
                session.setAttribute("", personalInfo);
                session.setAttribute("", jobInfo);
                session.setAttribute("", physicalInfo);
                rd.forward(request, response);
                System.out.println("successful");
            }else{
                ServletContext context= getServletContext();
                RequestDispatcher rd= context.getRequestDispatcher("/add-applicant.jsp");
                rd.forward(request, response);
            }
        } catch (ParseException ex) {
            Logger.getLogger(addApplicant.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
