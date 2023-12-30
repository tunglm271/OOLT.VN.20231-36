package com.example.coganhapp;

import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.*;


public class HelpPopUp {


    public static void display()
    {
        Stage popupwindow=new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Surrender");

        Label label1 = new Label("Guide");
        label1.setFont(Font.font("Arial",FontWeight.BOLD,30));
        TextArea help = new TextArea("""
                Cờ Gánh (hay Cờ Chém), là một trò chơi thuộc thể loại chiến thuật cực kỳ thú vị, đòi hỏi sự tư duy, kỹ năng, và sự sáng tạo. Nguồn gốc của trò chơi cờ gánh bắt nguồn từ tỉnh Quảng Nam, Việt Nam. Thú vị hơn hết, cờ gánh được sáng tạo từ bộ môn cờ vây, cờ tướng.
                                
                Tuy nhiên, điểm khác biệt lớn là trên bàn cờ gánh là không phân biệt các "tầng lớp" quân như: quân xe, quân chốt, quân vua, quân hậu,... mà thay vào đó, người chơi chính là vị tướng duy nhất.
                                
                Nhiều thông tin cho rằng, người Quảng Nam đã giản lược cách chơi, kết hợp với các bộ môn cờ khác để tạo ra trò chơi cờ gánh độc nhất cho dân tộc mình.\s
                                
                Tương tự với các trò chơi khác, cờ gánh ban đầu được chơi với các vật dụng khá đơn giản và 
                dễ tìm thấy như: vỏ nghêu, viên sỏi, bởi lẽ thế mà trò chơi rất dân gian, dễ tiếp cận đến mọi người. Đến thời điểm hiện tại, trò chơi cờ gánh đã phổ biến rộng rãi hơn, thậm chí đã phát triển thành các game Online được đông đảo bạn trẻ đón nhận.
                """);

        Button button1= new Button("OK !");

        button1.setOnAction(e -> popupwindow.close());



        VBox layout= new VBox(10);


        layout.getChildren().addAll(label1,help, button1);

        layout.setAlignment(Pos.CENTER);

        Scene scene1= new Scene(layout, 300, 400);

        popupwindow.setScene(scene1);

        popupwindow.showAndWait();

    }

}

