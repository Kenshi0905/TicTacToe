import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
    int boardwidth = 600;
    int boardheight = 650; // fifty for text panel

    JFrame frame = new JFrame();

    TicTacToe() {
        frame.setVisible(true);
        frame.setSize(boardwidth, boardheight);
    }
}