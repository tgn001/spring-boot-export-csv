package com.techgeeknext.util;

import com.techgeeknext.model.Employee;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.constraint.UniqueHashCode;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CSVWriterUtility {

    private static CellProcessor[] getProcessors() {

        final CellProcessor[] processors = new CellProcessor[]{
                new UniqueHashCode(), // employee No (must be unique)
                new NotNull(), // Name
                new NotNull(), // role

                //can be used for date field
                // new FmtDate("dd/MM/yyyy"),

                //for optional fields
                //  new Optional(new FmtBool("Y", "N")),
                // new Optional(),

        };

        return processors;
    }


    public static void employeeDetailReport(HttpServletResponse response, List<Employee> employees) {

        try (ICsvBeanWriter beanWriter = new CsvBeanWriter(response.getWriter(),
                CsvPreference.STANDARD_PREFERENCE)) {

            final String[] header = new String[]{"Id", "Name", "Role"};

            final CellProcessor[] processors = getProcessors();

            //set Header
            beanWriter.writeHeader(header);

            //Set data
            for (Employee emp : employees) {
                beanWriter.write(emp, header, processors);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
