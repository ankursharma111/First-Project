package models;

import exceptions.InvalidGameDimensionException;
import exceptions.InvalidNumberofPlayersException;
import exceptions.InvalidPlayersSymbolException;
import strategies.gamewinningstrategy.GameWinningStrategy;
import strategies.gamewinningstrategy.OrderOneGameWinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Game {




    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private int nextPlayerIndex;
    private GameStatus gameStatus;

    private Player winner;

    private GameWinningStrategy gameWinningStrategy;

    public GameWinningStrategy getGameWinningStrategy() {
        return gameWinningStrategy;
    }

    public void setGameWinningStrategy(GameWinningStrategy gameWinningStrategy) {
        this.gameWinningStrategy = gameWinningStrategy;
    }

    public Player getWinner() {
        return winner;
    }
    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public Game(){}

    public static Builder getBuilder(){
        return new Builder();
    }

    public void displayBoard(){
        board.displayBoard();
    }

    public void makeNextMove(){


        Player playerToMove = players.get(nextPlayerIndex);

        System.out.println("It is " + playerToMove.getName() + "'s turn");
        Move move = playerToMove.decideMove(board);

        //Validate the move

        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        if(board.getBoard().get(row).get(col).getCellState().equals(CellState.EMPTY)){

            board.applyMove(move);
            moves.add(move);

            //Check the Winner

        if(gameWinningStrategy.checkWinner(board,move)){

            gameStatus = GameStatus.ENDED;
            winner = playerToMove;


        }





            nextPlayerIndex = nextPlayerIndex + 1;
            nextPlayerIndex = nextPlayerIndex%(players.size());

        }



    }



    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }




    //Builder Pattern

    public static class Builder{

        private int dimension;
        private List<Player> players;

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        private boolean isValid() throws InvalidNumberofPlayersException, InvalidGameDimensionException {
            if(players.size()!=dimension-1){
                throw new InvalidNumberofPlayersException("Number of players should be 1 less than dimension");

            }
            if(dimension<3){
                throw new InvalidGameDimensionException("Dimension cannot be less than 3");

            }
            return true;
        }

        public Game build(){

            try{
                //before building the game we should validate the game
                isValid();
            } catch (InvalidNumberofPlayersException e) {
                System.out.println(e.getMessage());
                return null;
            } catch (InvalidGameDimensionException e) {
                System.out.println(e.getMessage());
                return null;

            }

            Game game = new Game();
            game.setGameStatus(GameStatus.IN_PROGRESS);
            game.setPlayers(players);
            game.setBoard(new Board(dimension));
            game.setMoves(new ArrayList<>());
            game.setNextPlayerIndex(0);
            game.setGameWinningStrategy(new OrderOneGameWinningStrategy(dimension));

            return game;


        }

    }







}
