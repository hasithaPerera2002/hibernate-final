package controller;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import service.RoomsService;
import service.ServiceFactory;
import service.ServiceTypes;
import service.StudentService;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.Locale;

public class DashBoardContextForm {
    private StudentService studentService= ServiceFactory.getInstance().getService(ServiceTypes.STUDENT_SERVICE);
    private RoomsService roomsService= ServiceFactory.getInstance().getService(ServiceTypes.ROOM_SERVICE);
    @FXML
    private AnchorPane dashboardContext2;

    @FXML
    private Label lblStudentCount;

    @FXML
    private Label lblRoomCount;

    @FXML
    private Label lblPendingCount;

    @FXML
    private BarChart<String,Double> stChart;
    public  void initialize(){
        int studentCount=studentService.getTotalCount();
        int roomCount=roomsService.getTotalCount();
        int pendingCount=studentService.getUnPaidCount();
        System.out.println(roomCount);
        lblRoomCount.setText(String.valueOf(roomCount));
        lblStudentCount.setText(String.valueOf(studentCount));
        lblPendingCount.setText(String.valueOf(pendingCount));

        setChart();
    }

    private void setChart() {
        HashMap<String, Double> monthlyIncome = studentService.getMonthlyIncome();
        XYChart.Series series1 = new XYChart.Series<>();
        series1.setName(String.valueOf(LocalDate.now().getYear()));

        for (int i = 0; i < 12; i++) {
            String en = Month.of(i + 1).getDisplayName(TextStyle.FULL, new Locale("en"));
            Double aDouble = monthlyIncome.get(String.valueOf(i + 1));
            if (aDouble!=null) {
                series1.getData().add(new XYChart.Data<>(en,aDouble));
            }else {
                series1.getData().add(new XYChart.Data<>(en,0.0));

            }

        }
        stChart.getData().add(series1);


    }

}
