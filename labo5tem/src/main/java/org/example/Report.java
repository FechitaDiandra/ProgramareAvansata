package org.example;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.exceptions.ShellException;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Report class represents a command to generate and open an HTML report based on repository data.
 * It implements the Command interface, allowing it to be executed as a command.
 */
public class Report implements org.example.Command {

    private final Repository userRepository;

    /**
     * Constructs a Report command with the given repository.
     *
     * @param userRepository the repository from which data will be used to generate the report
     */
    public Report(Repository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Executes the report command by generating and opening an HTML report.
     *
     * @throws ShellException if an error occurs during report generation or opening
     */
    @Override
    public void execute() throws ShellException {
        String htmlContent;
        try {
            htmlContent = generateHtmlReport(userRepository);
            openHtmlReport(htmlContent);
        } catch (IOException e) {
            throw new ShellException("Error trying to open HTML report: " + e.getMessage());
        }
    }

    /**
     * Generates an HTML report based on the data from the repository.
     *
     * @param repository the repository containing data for the report
     * @return the HTML content of the generated report
     * @throws ShellException if an error occurs during report generation
     */
    private String generateHtmlReport(Repository repository) throws ShellException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setClassForTemplateLoading(Shell.class, "/templates");

        try {
            Template template = cfg.getTemplate("report.ftl");
            StringWriter out = new StringWriter();

            List<Person> persons = repository.getUsers();
            List<Document> documents = repository.getDocuments();
            List<Map<String, Object>> personList = new ArrayList<>();
            for (Person person : persons) {
                Map<String, Object> personMap = new HashMap<>();
                personMap.put("id", person.id());
                personMap.put("name", person.name());
                personList.add(personMap);
            }

            List<Map<String, Object>> documentList = new ArrayList<>();
            for (Document document : documents) {
                Map<String, Object> documentMap = new HashMap<>();
                documentMap.put("name", document.name());
                documentList.add(documentMap);
            }

            Map<String, Object> input = new HashMap<>();
            input.put("persons", personList);
            input.put("documents", documentList);
            template.process(input, out);
            return out.getBuffer().toString();
        } catch (IOException | TemplateException e) {
            throw new ShellException("Error trying to create HTML report: " + e.getMessage());
        }
    }

    /**
     * Opens the generated HTML report.
     *
     * @param htmlContent the HTML content of the report
     * @throws IOException if an error occurs while opening the report
     */
    private void openHtmlReport(String htmlContent) throws IOException {
        File tempFile = File.createTempFile("report", ".html");
        Files.writeString(tempFile.toPath(), htmlContent);
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().open(tempFile);
        }
    }
}
