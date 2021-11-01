package better_arch.bussiness_logic;

import java.awt.Font;

import better_arch.db_connection.UserDao;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Creates charts representing the results of some QueryRunner queries
 */
public class ChartBuilder {
    private final static String OUTPUT_PNG_PATH = "./charts/";
    private final UserDao userDao;

    private static final int chartWidth = 1920;
    private static final int chartHeight = 1080;

    public ChartBuilder(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * Converts List of strings to JFreeChart dataset
     * @param data List of String.
     *             List[0] -- numeric values for Y axis
     *             List[1] -- string values for X axis
     * @return JFreeChart dataset
     */
    private DefaultCategoryDataset fillDataset(List<String[]> data) {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (String[] row : data) {
            dataset.addValue(Double.parseDouble(row[1]), "default", row[0]);
        }
        return dataset;
    }

    /**
     * Apply default settings to the chart and set up title
     * @param barChart chart to set up
     * @param title title
     */
    private void configureChart(JFreeChart barChart, String title) {
        CategoryPlot plot = barChart.getCategoryPlot();
        CategoryAxis axis = plot.getDomainAxis();

        Font font = new Font("Cambria", Font.BOLD, 25);
        axis.setTickLabelFont(font);
        Font font3 = new Font("Cambria", Font.BOLD, 30);
        barChart.setTitle(new org.jfree.chart.title.TextTitle(title, new java.awt.Font("Cambria", java.awt.Font.BOLD, 40)));

        plot.getDomainAxis().setLabelFont(font3);
        plot.getRangeAxis().setLabelFont(font3);
        CategoryPlot categoryPlot = (CategoryPlot) barChart.getPlot();
        BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();
        renderer.setBarPainter(new StandardBarPainter());
    }

    /**
     * Creates a chart, representing the number of cancelled flights as a function of month
     * @throws IOException if a problem while saving the chart to the filesystem occurred
     */
    public void createBarChart() throws IOException, SQLException {
        Set<User> users = userDao.getAllUsers();
        List<String[]> rawData = new ArrayList<>(users.size());
        for (User user : users) {
            rawData.add(new String[]{user.getName(), user.getAge().toString()});
        }
        DefaultCategoryDataset dataset = fillDataset(rawData);
        String title = "Users";
        String categoryAxis = "Name";
        String valueAxis = "Age";

        JFreeChart barChart = ChartFactory.createBarChart(title, categoryAxis, valueAxis, dataset,
                PlotOrientation.VERTICAL, false, false, false);
        configureChart(barChart, title);
        File file = new File(OUTPUT_PNG_PATH + "chart.png");
        file.getParentFile().mkdirs();
        file.createNewFile();
        ChartUtilities.saveChartAsPNG(file, barChart, chartWidth, chartHeight);
    }
}