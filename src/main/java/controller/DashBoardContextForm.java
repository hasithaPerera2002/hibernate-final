package controller;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import service.RoomsService;
import service.ServiceFactory;
import service.ServiceTypes;
import service.StudentService;

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
    private BarChart<?, ?> stChart;
    public  void initialize(){
        int studentCount=studentService.getTotalCount();
        int roomCount=roomsService.getTotalCount();
        int pendingCount=studentService.getUnPaidCount();
        System.out.println(roomCount);
        lblRoomCount.setText(String.valueOf(roomCount));
        lblStudentCount.setText(String.valueOf(studentCount));
        lblPendingCount.setText(String.valueOf(pendingCount));
    }

}
