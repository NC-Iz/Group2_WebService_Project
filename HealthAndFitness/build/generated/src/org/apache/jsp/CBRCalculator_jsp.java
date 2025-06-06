package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class CBRCalculator_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write('\r');
      out.write('\n');

    String name = (String) session.getAttribute("name");
    Double weight = (Double) session.getAttribute("weight");

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"UTF-8\">\r\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n");
      out.write("    <title>Calories Burned Calculator</title>\r\n");
      out.write("    <!-- Bootstrap 5 CSS -->\r\n");
      out.write("    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css\" rel=\"stylesheet\">\r\n");
      out.write("    <style>\r\n");
      out.write("        .calculator-card {\r\n");
      out.write("            max-width: 600px;\r\n");
      out.write("            margin: 2rem auto;\r\n");
      out.write("        }\r\n");
      out.write("    </style>\r\n");
      out.write("</head>\r\n");
      out.write("<body class=\"bg-light\">\r\n");
      out.write("    <div class=\"container py-5\">\r\n");
      out.write("        <div class=\"card calculator-card shadow\">\r\n");
      out.write("            <div class=\"card-header bg-primary text-white\">\r\n");
      out.write("                <h2 class=\"mb-0 text-center\">Calories Burned Calculator</h2>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"card-body\">\r\n");
      out.write("                <form action=\"HealthFitnessClient\" method=\"post\">\r\n");
      out.write("                    <input type=\"hidden\" name=\"formType\" value=\"cbr\">\r\n");
      out.write("\r\n");
      out.write("                    <!-- Name -->\r\n");
      out.write("                    <div class=\"mb-3\">\r\n");
      out.write("                        <label for=\"name\" class=\"form-label\">Name:</label>\r\n");
      out.write("                        <input type=\"text\" class=\"form-control\" id=\"name\" name=\"name\" \r\n");
      out.write("                               value=\"");
      out.print( name != null ? name : "" );
      out.write("\" required>\r\n");
      out.write("                    </div>\r\n");
      out.write("\r\n");
      out.write("                    <!-- Weight -->\r\n");
      out.write("                    <div class=\"mb-3\">\r\n");
      out.write("                        <label for=\"weight\" class=\"form-label\">Weight (kg):</label>\r\n");
      out.write("                        <input type=\"number\" class=\"form-control\" id=\"weight\" name=\"weight\" step=\"0.1\" min=\"1\" \r\n");
      out.write("                               value=\"");
      out.print( weight != null ? weight : "" );
      out.write("\" required>\r\n");
      out.write("                    </div>\r\n");
      out.write("\r\n");
      out.write("                    <!-- Activity -->\r\n");
      out.write("                    <div class=\"mb-3\">\r\n");
      out.write("                        <label for=\"activity\" class=\"form-label\">Activity:</label>\r\n");
      out.write("                        <select class=\"form-select\" id=\"activity\" name=\"activity\" required>\r\n");
      out.write("                            <option value=\"\" disabled selected>Please choose an activity</option>\r\n");
      out.write("                            <option value=\"walking\">Walking</option>\r\n");
      out.write("                            <option value=\"running\">Running</option>\r\n");
      out.write("                            <option value=\"cycling\">Cycling</option>\r\n");
      out.write("                            <option value=\"swimming\">Swimming</option>\r\n");
      out.write("                            <option value=\"weightlifting\">Weightlifting</option>\r\n");
      out.write("                        </select>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    \r\n");
      out.write("                    <div class=\"mb-3\">\r\n");
      out.write("                        <label for=\"intensity\" class=\"form-label\">Intensity:</label>\r\n");
      out.write("                        <select class=\"form-select\" id=\"intensity\" name=\"intensity\" required>\r\n");
      out.write("                            <option value=\"\" disabled selected>Select intensity</option>\r\n");
      out.write("                            <option value=\"low\">Low</option>\r\n");
      out.write("                            <option value=\"moderate\">Moderate</option>\r\n");
      out.write("                            <option value=\"high\">High</option>\r\n");
      out.write("                        </select>\r\n");
      out.write("                    </div>\r\n");
      out.write("\r\n");
      out.write("                    <!-- Duration -->\r\n");
      out.write("                    <div class=\"mb-4\">\r\n");
      out.write("                        <label for=\"duration\" class=\"form-label\">Duration (minutes):</label>\r\n");
      out.write("                        <input type=\"number\" class=\"form-control\" id=\"duration\" name=\"duration\" step=\"1\" min=\"1\" required>\r\n");
      out.write("                    </div>\r\n");
      out.write("\r\n");
      out.write("                    <!-- Submit Button -->\r\n");
      out.write("                    <div class=\"d-grid\">\r\n");
      out.write("                        <button type=\"submit\" class=\"btn btn-primary btn-lg\">Calculate Calories Burned</button>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </form>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <!-- Bootstrap JS Bundle with Popper -->\r\n");
      out.write("    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js\"></script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
