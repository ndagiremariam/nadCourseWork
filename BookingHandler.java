/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/TagHandler.java to edit this template
 */
package org.health.booking;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.JspFragment;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import org.db.connection.DBConnection;

/**
 *
 * @author HP Elitebook 1040
 */
public class BookingHandler extends SimpleTagSupport {

    DBConnection Conn = new DBConnection();
    Connection newConn = Conn.getConnection();

    private String values;
    private String table;

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();

        try {
            // TODO: insert code to write html before writing the body content.
            // e.g.:
            //
            // out.println("<strong>" + attribute_1 + "</strong>");
            // out.println("    <blockquote>");
            String[] newValues = values.split(",");
            //int quantity =   Integer.parseInt(newValues[1]);
            Statement St = newConn.createStatement();
             out.println("Am here man" +newValues[1]+ "another"+newValues[0]+ "The table is" +table);
            //`bookingId`, `vaccineName`, `place`, `date`, `healthCenterName`
            if (newValues.length > 1) {
                String str1 = "INSERT INTO `bookings` SET "
                        + "`vaccineName`='Astra',`place`='Paris',`date`='" + newValues[2]
                        + "',`healthCenterName`='" + newValues[4]
                        + "',`name`='" + newValues[0] + "',`email`='" + newValues[1] + "'";
               out.println(str1);
                St.execute(str1);
                
                out.println("<script type='text/javascript'>alert('" + newValues[0] + " added successfully');</script>");
                out.println("<script type='text/javascript'>window.location='systemadminstrator.jsp'</script>");

            }

            JspFragment f = getJspBody();
            if (f != null) {
                f.invoke(out);
            }

            // TODO: insert code to write html after writing the body content.
            // e.g.:
            //
            // out.println("    </blockquote>");
        } catch (java.io.IOException ex) {
            throw new JspException("Error in BookingHandler tag", ex);
        } catch (SQLException ex) {
            Logger.getLogger(BookingHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setValues(String values) {
        this.values = values;
    }

    public void setTable(String table) {
        this.table = table;
    }

}
