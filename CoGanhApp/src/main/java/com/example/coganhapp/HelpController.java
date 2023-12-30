package com.example.coganhapp;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.Objects;

public class HelpController {

    @FXML
    private TextArea guideText;
    @FXML
    private TextArea guideText2;

    @FXML
    protected void menuButtonEnterEffect(MouseEvent e) {
        if(e.getTarget() instanceof HBox box) {
            box.setStyle("-fx-background-color: #3a3153;-fx-background-radius: 2em");
        }
    }

    @FXML
    protected void menuButtonLeaveEffect(MouseEvent e) {
        if(e.getTarget() instanceof HBox box) {
            box.setStyle("-fx-background-color:  #5f43b2");
        }
    }

    @FXML
    protected void clickExitButton() {
        Platform.exit();
    }

    @FXML
    protected void clickStartButton(MouseEvent event) throws IOException {
        Parent root =  FXMLLoader.load(Objects.requireNonNull(getClass().getResource("GameUI.fxml")));
        Node source = (Node) event.getSource();
        source.getScene().setRoot(root);
    }

    @FXML
    public void initialize() {
        guideText.setText("""
                Cờ Gánh (hay Cờ Chém), là một trò chơi thuộc thể loại chiến thuật cực kỳ thú vị, đòi hỏi sự tư duy, kỹ năng, và sự sáng tạo. Nguồn gốc của trò chơi cờ gánh bắt nguồn từ tỉnh Quảng Nam, Việt Nam. Thú vị hơn hết, cờ gánh được sáng tạo từ bộ môn cờ vây, cờ tướng.
                Tuy nhiên, điểm khác biệt lớn là trên bàn cờ gánh là không phân biệt các "tầng lớp" quân như: quân xe, quân chốt, quân vua, quân hậu,... mà thay vào đó, người chơi chính là vị tướng duy nhất.
                Nhiều thông tin cho rằng, người Quảng Nam đã giản lược cách chơi, kết hợp với các bộ môn cờ khác để tạo ra trò chơi cờ gánh độc nhất cho dân tộc mình.\s
                Tương tự với các trò chơi khác, cờ gánh ban đầu được chơi với các vật dụng khá đơn giản và dễ tìm thấy như: vỏ nghêu, viên sỏi, bởi lẽ thế mà trò chơi rất dân gian, dễ tiếp cận đến mọi người. Đến thời điểm hiện tại, trò chơi cờ gánh đã phổ biến rộng rãi hơn, thậm chí đã phát triển thành các game Online được đông đảo bạn trẻ đón nhận.
                Số người chơi: 2 người
                """);
        guideText.setEditable(false);
        guideText2.setText("""
                Khi tham gia trò chơi, mỗi người chơi được chia 8 quân cờ, có màu sắc (hoặc nhận dạng) khác với quân cờ của đối phương. Mỗi người chơi sẽ thực hiện việc di chuyển quân cờ của mình đến một trong những giao điểm lân cận trên bàn cờ vuông. Di chuyển có thể theo đường ngang, đứng hoặc đường chéo, như đã được thể hiện trên bàn cờ. Điều quan trọng là các điểm đó phải còn trống, tức là không có quân cờ nào ở đó.
                               
                Mục tiêu của trò chơi là người chơi sẽ phải đổi hết màu (hoặc nhận dạng) của các quân cờ đối thủ thành màu và nhận dạng quân cờ của mình, khiến họ không còn quân cờ nào để đi.\s
                               
                Trò chơi sẽ kết thúc khi một trong hai người chơi không còn quân cờ nào để di chuyển. Người chiến thắng sẽ là người nắm trong tay 16 quân cờ.
                               
                Các trường hợp sẽ gặp khi chơi cờ gánh:
                               
                1. Gánh
                Khi một người chơi di chuyển một quân cờ của họ đến vị trí giữa hai quân cờ của đối phương (và hai quân của đối phương này nằm hai bên, tạo thành một đường thẳng), thì hai quân cờ của đối phương tại những vị trí này được coi là bị "gánh" và sau đó được thay đổi màu sắc hoặc các đặc điểm nhận dạng.
                               
                Điều quan trọng là người chơi phải chủ động và thực hiện nước đi bằng cách di chuyển quân cờ của họ vào giữa hai quân cờ của đối phương, và không thể thực hiện nước đi "gánh" trong lúc đối phương đang thực hiện lượt đi của họ.
                """);
        guideText2.setEditable(false);
    }
}
