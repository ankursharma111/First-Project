package controllers;

import models.*;

import java.util.List;

public class GameController {

    public Game createGame(int dimension, List<Player> players){

        Game game = Game.getBuilder()
                .setDimension(dimension)
                .setPlayers(players)
                .build();

        return game;
    }

    public void undo(Game game){

        Move lastMove = game.getMoves().getLast();

        game.getMoves().remove(lastMove);


        Board newBoard = new Board(game.getBoard().getBoard().size());

        Board existingBoard = game.getBoard();

        int dim = game.getBoard().getBoard().size();

        int row = lastMove.getCell().getRow();
        int col = lastMove.getCell().getCol();

        for(int i=0; i<dim; i++){
            for(int j=0; j<dim; j++){

                if(i==row && j==col){
                    newBoard.getBoard().get(i).get(j).setCellState(CellState.EMPTY);
                    newBoard.getBoard().get(i).get(j).setPlayer(null);
                }
                else{

                    Cell cell1 = existingBoard.getBoard().get(i).get(j);

                    newBoard.getBoard().get(i).get(j).setCellState(cell1.getCellState());
                    newBoard.getBoard().get(i).get(j).setPlayer(cell1.getPlayer());
                }
            }
        }

        game.setBoard(newBoard);






    }

    public void executeNextMove(Game game){
        game.makeNextMove();
    }





    public GameStatus getGameStatus(Game game){
        return game.getGameStatus();
    }

    public void displayBoard(Game game){
        game.displayBoard();
    }


    public Player getWinner(Game game) {

        return game.getWinner();
    }
}
