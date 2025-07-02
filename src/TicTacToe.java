import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
    int boardwidth = 600;
    int boardheight = 650; // fifty for text panel

    JFrame frame = new JFrame("Tic Tac Toe");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    JButton[][] board = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;

    boolean gameOver = false;
    int turns = 0; 


    TicTacToe() {
        frame.setVisible(true);
        frame.setSize(boardwidth, boardheight);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //closes the program when window is closed
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.GRAY);
        textLabel.setForeground(Color.WHITE);
        textLabel.setFont(new Font("Verdana", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic Tac Toe");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.GRAY);
        frame.add(boardPanel, BorderLayout.CENTER);

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                JButton tile = new JButton();
                board[r][c] = tile;
                boardPanel.add(tile);

                tile.setBackground(Color.DARK_GRAY);
                tile.setForeground(Color.WHITE);
                tile.setFont(new Font("Verdana", Font.BOLD, 120));
                

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (gameOver) return; //if game is over, do nothing
                        JButton tile = (JButton) e.getSource(); //casting type to JButton to get rid of errors
                        if (tile.getText() == ""){
                            tile.setText(currentPlayer);
                            turns++;
                            checkWinner();
                            if(!gameOver){
                                currentPlayer = currentPlayer.equals(playerX) ? playerO : playerX; //switch players
                                textLabel.setText(currentPlayer + "'s turn");
                            }
                        }
                    }
                });
            }

        }

    }
    void checkWinner() {
        //horizontal check
        for (int r = 0; r < 3; r++) {
            if (board[r][0].getText() == "") continue;

            if (board[r][0].getText().equals(board[r][1].getText()) && board[r][0].getText().equals(board[r][2].getText())) {
                gameOver = true;
                for (int c = 0; c < 3; c++) {
                    board[r][c].setForeground(Color.GREEN); //winner tile color changes
                }
                textLabel.setText(board[r][0].getText() + " wins!");
                return;
            }
        }
        //vertical check
        for (int c = 0; c < 3; c++) {
            if (board[0][c].getText() == "") continue;

            if (board[0][c].getText().equals(board[1][c].getText()) && board[0][c].getText().equals(board[2][c].getText())) {
                gameOver = true;
                for (int r = 0; r < 3; r++) {
                    board[r][c].setForeground(Color.GREEN); //winner tile color changes
                }
                textLabel.setText(board[0][c].getText() + " wins!");
                return;
            }
        }
        //diagonal check
        if (board[0][0].getText() != "" && board[0][0].getText().equals(board[1][1].getText()) && board[0][0].getText().equals(board[2][2].getText())) {
            gameOver = true;
            for (int i = 0; i < 3; i++) {
                board[i][i].setForeground(Color.GREEN); //winner tile color changes
            }
            textLabel.setText(board[0][0].getText() + " wins!");
            return;
        }
        //anti-diagonal check
        if (board[0][2].getText() != "" && board[0][2].getText().equals(board[1][1].getText()) && board[0][2].getText().equals(board[2][0].getText())) {
            gameOver = true;
            for (int i = 0; i < 3; i++) {
                board[i][2 - i].setForeground(Color.GREEN); //winner tile color changes
            }
            textLabel.setText(board[0][2].getText() + " wins!");
            return;
        }
        //tie check
        if (turns == 9) {
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    setTie(board[r][c]);
                }
            }
            gameOver = true;
        }
    }
    void setTie(JButton tile) {
        tile.setForeground(Color.YELLOW); //tie tile color changes
        textLabel.setText("It's a tie!");
        
    }
}